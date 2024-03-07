package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.reflect.TypeToken
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.databinding.ModuloTotalInfoBinding
import gob.pa.inovacion_empresarial.databinding.StyleMsgAlertBinding
import gob.pa.inovacion_empresarial.databinding.StyleMsgFormBinding
import gob.pa.inovacion_empresarial.function.AppCache
import gob.pa.inovacion_empresarial.function.CreateBackUp
import gob.pa.inovacion_empresarial.function.CreateForm
import gob.pa.inovacion_empresarial.function.CreateIncon
import gob.pa.inovacion_empresarial.function.Functions
import gob.pa.inovacion_empresarial.function.Functions.aString
import gob.pa.inovacion_empresarial.function.Functions.toEditable
import gob.pa.inovacion_empresarial.model.DVModel
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelCondicion
import gob.pa.inovacion_empresarial.model.ModelForm
import gob.pa.inovacion_empresarial.model.ModelResponse
import gob.pa.inovacion_empresarial.service.room.RoomView
import gob.pa.inovacion_empresarial.view.MainActivity
import kotlinx.coroutines.launch
import java.lang.reflect.Type


class FragTotalInforme : Fragment() {

    private lateinit var bindinginfo: ModuloTotalInfoBinding
    private lateinit var ctx: Context
    private val dvmInforme: DVModel by viewModels()
    private var aDialog: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindinginfo = ModuloTotalInfoBinding.inflate(layoutInflater)
        ctx = requireContext()
        return bindinginfo.root
    }

    override fun onPause() {
        super.onPause()
        if (aDialog?.isShowing == true)  aDialog?.dismiss()
    }

    override fun onResume() {
        super.onResume()
        Mob.indiceFormulario = Mob.OBSE_P24
        fillOut()
    }

    private fun fillOut() {
        val blank = "".toEditable()
        with(bindinginfo) {

            txtInfoObsEncuesta.text = Mob.obsEncuesta?.toEditable() ?: blank
            txtInfoObsModulo.text = Mob.obsModulo?.toEditable() ?: blank
            txtnewRazon.text = Mob.condicion?.newRazon?.toEditable() ?: blank
            txtnewNcontrol.text = Mob.condicion?.newNcontrol?.toEditable() ?: blank
            txtespecifiqueCondicion.text = Mob.condicion?.especifique?.toEditable() ?: blank
        }
        onAction()
    }

    private fun onAction() {
        with(bindinginfo) {
            if (Mob.authData?.rol != "E") {
                btEnd.visibility = View.VISIBLE
                btSendObs.isEnabled = false
                btViewForm.isEnabled = false
                txtCondicion.isEnabled = false
                txtnewRazon.isFocusable = false
                txtnewNcontrol.isFocusable = false
                txtespecifiqueCondicion.isFocusable = false
                txtInfoObsEncuesta.isFocusable = false
                txtInfoObsModulo.isFocusable = false
            }

            if (lbcondicionID.text.isNullOrEmpty()) lbcondicionID.text = Mob.condicionID ?: ""

            val idCondInt = Mob.condicionID?.toIntOrNull()?.takeIf { it > 0 }
            txtCondicion.setText(Mob.arrCondicion.getOrElse(idCondInt ?: 0) { "" }, false)

            with(txtCondicion.text.toString()) {
                when {
                    contains(Mob.arrCondicion[Mob.CONDICION_02]) ||
                            contains(Mob.arrCondicion[Mob.CONDICION_04]) -> {
                        txtespecifiqueCondicionly.visibility = View.GONE
                        txtnewNcontrolly.visibility = View.VISIBLE
                        txtnewRazonly.visibility = View.VISIBLE
                    }
                    contains(Mob.arrCondicion[Mob.CONDICION_08]) -> {
                        txtespecifiqueCondicionly.visibility = View.VISIBLE
                        txtnewNcontrolly.visibility = View.INVISIBLE
                        txtnewRazonly.visibility = View.GONE
                    }
                    else -> {
                        txtespecifiqueCondicionly.visibility = View.GONE
                        txtnewNcontrolly.visibility = View.INVISIBLE
                        txtnewRazonly.visibility = View.INVISIBLE
                    }
                }
            }
            val condicionAdapter = ArrayAdapter(ctx, R.layout.style_box, Mob.arrCondicion)
            condicionAdapter.setDropDownViewResource(R.layout.style_list)
            txtCondicion.apply {
                setAdapter(condicionAdapter)
                setOnItemClickListener { _, _, pos, _ ->
                    Mob.condicionID = "0$pos"
                    lbcondicionID.text = Mob.condicionID
                    when (pos) {
                        Mob.CONDICION_02, Mob.CONDICION_04  -> {
                            txtespecifiqueCondicionly.visibility = View.GONE
                            txtnewNcontrolly.visibility = View.VISIBLE
                            txtnewRazonly.visibility = View.VISIBLE
                        }
                        Mob.CONDICION_08 -> {
                            txtespecifiqueCondicionly.visibility = View.VISIBLE
                            txtnewNcontrolly.visibility = View.INVISIBLE
                            txtnewRazonly.visibility = View.GONE
                        }
                        else -> {
                            txtespecifiqueCondicionly.visibility = View.GONE
                            txtnewNcontrolly.visibility = View.INVISIBLE
                            txtnewRazonly.visibility = View.INVISIBLE
                        }
                    }
                }
            }

            btSendObs.setOnClickListener {
                if (Mob.authData?.rol == "E" && Functions.isOnline(ctx)) {
                    saveCapInforme()
                    sendFormulario(CreateForm.create())
                } else if (!Functions.isOnline(ctx)){
                    val color = ContextCompat.getColor(ctx, R.color.teal_700)
                    msgBallom("Sin red disponible", Mob.WIDTH160DP, color)
                }
            }
            btViewForm.setOnClickListener { reviewForm() }
            btEnd.setOnClickListener { endForm() }
        }
    }

    private fun reviewForm() {
        val inconsistencias = CreateIncon.reviewIncons()
        val reviewCaps = CreateIncon.reviewCaps()
        val msgSend = AlertDialog.Builder(ctx)
        val bindSend: StyleMsgAlertBinding =
            DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.style_msg_alert, null, false)
        msgSend.setView(bindSend.root)
        bindSend.apply {
            msg2.visibility = View.GONE
            msg6.visibility = View.VISIBLE
            msg6.setTextColor(ContextCompat.getColorStateList(ctx, R.color.teal_900))

            btpositivo.text = getString(R.string.done)
            btpositivo.backgroundTintList = ContextCompat.getColorStateList(ctx, R.color.teal_700)
            btpositivo.setTextColor(ContextCompat.getColorStateList(ctx, R.color.white))
            btpositivo.iconTint = ContextCompat.getColorStateList(ctx, R.color.white)
            btnegativo.visibility = View.GONE

            msgtitle.text = getString(R.string.resumenForm)
            when {
                inconsistencias.isNotEmpty() -> {
                    msg1.text = getString(R.string.msgInfoIncoReg)
                    for (list in inconsistencias) {
                        val desc = if (getString(list.third) == "") ""
                        else " - ${getString(list.third)}"
                        val txt = "${msg6.text}${getString(list.first)}${desc}\n\n"
                        msg6.text = txt.toEditable()
                    }
                }
                reviewCaps.isNotEmpty() -> {
                    msg1.text = getString(R.string.msgInfoCapNoPre)
                    for (list in reviewCaps) {
                        val desc = if (getString(list.third) == "") ""
                        else " - ${getString(list.third)}"
                        val txt = "${msg6.text}${getString(list.first)}${desc}\n\n"
                        msg6.text = txt.toEditable()
                    }
                }
                else -> {
                    msg1.text = getString(R.string.msgInfoFormComp)
                    msg6.visibility = View.GONE
                }
            }
            aDialog = msgSend.create()
            aDialog?.setCancelable(false)
            aDialog?.show()
            aDialog?.window?.setGravity(Gravity.CENTER)

            btpositivo.setOnClickListener {
                aDialog?.dismiss()
            }
        }
    }

    private fun sendFormulario(form: ModelForm) {
        val screenBlack = AlertDialog.Builder(ctx)
            .setCancelable(false)
            .create()
        screenBlack.show()

        lifecycleScope.launch {
            bindinginfo.barInforme.visibility = View.VISIBLE
            try {
                val resp = dvmInforme.sendForm(form)
                savedForm(form)

                when (resp.code) {
                    "200" -> {
                        Handler(Looper.getMainLooper()).postDelayed({
                            val respGson: String = try { Gson().toJson(resp.body) as String
                            } catch (e: JsonParseException) { "" }
                            carga(respGson)
                        }, Mob.TIME800MS)
                    }
                    "400" -> msgBallom(getString(R.string.msgInforme400), Mob.WIDTH180DP, R.color.dark_pink)
                    "401" -> msgBallom(getString(R.string.msgInforme401), Mob.WIDTH160DP, R.color.blue_dark)
                    "404" -> msgBallom(getString(R.string.msgInforme404), Mob.WIDTH180DP, R.color.dark_red)
                    "500" -> msgBallom(getString(R.string.msgInforme500), Mob.WIDTH160DP, R.color.dark_red)
                    else -> {
                        if (resp.server.isNullOrEmpty())
                            msgBallom(getString(R.string.msgInformeNoResp), Mob.WIDTH160DP, R.color.teal_700)
                         else Toast.makeText(ctx, resp.server, Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                msgBallom(getString(R.string.msgInformeNoRed), Mob.WIDTH160DP, R.color.teal_700)
            } finally {
                Handler(Looper.getMainLooper()).postDelayed({
                    screenBlack.dismiss()
                    bindinginfo.barInforme.visibility = View.INVISIBLE
                }, Mob.TIME800MS)
            }
        }
    }

    private suspend fun savedForm(form: ModelForm): Long {
        val nameForm: String
        Mob.apply {
            nameForm = "${authData?.user ?: USERTEST}*${Functions.myDate().aString(DATEFORMAT)}"
        }
        AppCache.formSAVE(ctx, form, nameForm)
        CreateBackUp.saved(ctx)
        return RoomView(dvmInforme, ctx).saveForm(CreateForm.createObDB())
    }

    private fun carga(data: String) {
        val msgSend = AlertDialog.Builder(ctx)
        val bindSend: StyleMsgFormBinding =
            DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.style_msg_form, null, false)

        val type: Type = object : TypeToken<ModelResponse?>() {}.type
        val jsonModel: ModelResponse? = Gson().fromJson<ModelResponse>(data, type)

        bindSend.txtmsgStyle.visibility = View.VISIBLE
        if (jsonModel?.poseeIncon == "false") {
            Mob.viewIncon = false
            bindSend.txtmsgStyle.text = getString(R.string.sendSuccess)
            bindSend.txt2styleFly.visibility = View.GONE
        } else  {
            Mob.viewIncon = true
            bindSend.txtmsgStyle.text = getString(R.string.sendSuccessWithIncon)
        }
        val ncont = Functions.ceroLeft(jsonModel?.ncontrol?.toString() ?: "0", Mob.CERO_COUNTLEFT)
        bindSend.txt1styleF.text = try { ncont.toEditable() }
        catch (e: RuntimeException) { "00".toEditable() }

        bindSend.txt2styleF.text = try {
            jsonModel?.inconsistencias?.joinToString("\n\n")?.toEditable() }
        catch (e: RuntimeException) { "".toEditable() }

        Mob.inconsArray = jsonModel?.inconsistencias
        msgSend.setView(bindSend.root)
        aDialog = msgSend.create()
        aDialog?.setCancelable(false)
        aDialog?.show()
        aDialog?.window?.setGravity(Gravity.CENTER)

        bindSend.btEnd.setOnClickListener {
            aDialog?.dismiss()
            endForm()
        }

        bindSend.btCancel.setOnClickListener {
            aDialog?.dismiss()
            bindinginfo.btEnd.visibility = View.VISIBLE
        }
    }

    private fun endForm() {
        bindinginfo.barInforme.visibility = View.VISIBLE
        val screen = AlertDialog.Builder(ctx)
        val screenBlack: AlertDialog = screen.create()
        screenBlack.setCancelable(false)
        screenBlack.show()

        lifecycleScope.launch { CreateForm.createSaved(dvmInforme, ctx) } //-- SAVE FORMULARIO
        Handler(Looper.getMainLooper()).postDelayed({
            val form = activity
            val intent = Intent(ctx, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            CreateForm.resetLoad()
            Handler(Looper.getMainLooper()).postDelayed({
                form?.finish()
                screenBlack.dismiss()
            }, (Mob.TIME800MS))
            bindinginfo.barInforme.visibility = View.INVISIBLE
        }, (Mob.TIME800MS))
    }

    private fun msgBallom(msg: String, width: Int, color: Int) {
        val alert = Functions.msgBallom(msg, width, ctx, color)
        val localization = activity?.findViewById<View>(R.id.txtInfopager)
        if (localization != null)  alert.showAlignBottom(localization)
        else alert.showAlignBottom(bindinginfo.btEnd)
        alert.dismissWithDelay(Mob.TIMELONG4SEG)
    }

    fun saveCapInforme(): List<String> {
        with(bindinginfo) {
            Mob.obsEncuesta = txtInfoObsEncuesta.text.toString().ifEmpty { null }
            Mob.obsModulo = txtInfoObsModulo.text.toString().ifEmpty { null }
            Mob.capMod?.observaciones = Mob.obsModulo

            Mob.condicion = ModelCondicion(
                id = Mob.condicion?.id,
                idcondi = lbcondicionID.text.toString().ifEmpty { null },
                newRazon = txtnewRazon.text.toString().ifEmpty { null },
                otra = Mob.condicion?.otra,
                newNcontrol = txtnewNcontrol.text.toString().ifEmpty { null },
                especifique = txtespecifiqueCondicion.text.toString().ifEmpty { null },
                ncontrol = Mob.condicion?.ncontrol
            )
        }
        return emptyList() // Controlar condiciones 02, 04 y 08 que contenga sus datos
    }
}