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
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.reflect.TypeToken
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.databinding.ModuloTotalInfoBinding
import gob.pa.inovacion_empresarial.databinding.StyleMsgFormBinding
import gob.pa.inovacion_empresarial.function.AppCache
import gob.pa.inovacion_empresarial.function.CreateBackUp
import gob.pa.inovacion_empresarial.function.CreateForm
import gob.pa.inovacion_empresarial.function.Functions
import gob.pa.inovacion_empresarial.function.Functions.aString
import gob.pa.inovacion_empresarial.function.Functions.toEditable
import gob.pa.inovacion_empresarial.model.*
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
        Mob.indiceFormulario = Mob.OBSP24
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
            //txtespecifiqueCondicion.text = "".toEditable()
        }
        onAction()
    }

    private fun onAction() {
        with(bindinginfo) {
            if (Mob.authData?.rol != "E") {
                btSendObs.isEnabled = false
                btViewObs.isEnabled = false
                btEnd.isVisible = true
                txtCondicion.isEnabled = false
                txtnewRazon.isFocusable = false
                txtnewNcontrol.isFocusable = false
                txtespecifiqueCondicion.isFocusable = false
                txtInfoObsEncuesta.isFocusable = false
                txtInfoObsModulo.isFocusable = false
            }

            if (lbcondicionID.text.isNullOrEmpty()) lbcondicionID.text = Mob.condicionID ?: ""

            val idCondInt = try { (Mob.condicionID?.toInt() ?: 0) -1 }
            catch (e: java.lang.NumberFormatException) { null }
            txtCondicion.setText(Mob.arrCondicion[idCondInt ?: 0],false)

            with(txtCondicion.text.toString()) {
                when {
                    contains(Mob.arrCondicion[Mob.CONDICION02]) -> {
                        txtespecifiqueCondicionly.visibility = View.GONE
                        txtnewNcontrolly.visibility = View.VISIBLE
                        txtnewRazonly.visibility = View.VISIBLE
                    }
                    contains(Mob.arrCondicion[Mob.CONDICION04]) -> {
                        txtespecifiqueCondicionly.visibility = View.GONE
                        txtnewNcontrolly.visibility = View.VISIBLE
                        txtnewRazonly.visibility = View.VISIBLE
                    }
                    contains(Mob.arrCondicion[Mob.CONDICION08]) -> {
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
            val condicion = ArrayAdapter(ctx, R.layout.style_box, Mob.arrCondicion)
            condicion.setDropDownViewResource(R.layout.style_list)
            txtCondicion.setAdapter(condicion)
            txtCondicion.setOnItemClickListener { _, _, pos, _ ->
                Mob.condicionID = "0" + (pos + 1)
                lbcondicionID.text = Mob.condicionID
                when (pos) {
                    Mob.CONDICION02 -> {
                        txtespecifiqueCondicionly.visibility = View.GONE
                        txtnewNcontrolly.visibility = View.VISIBLE
                        txtnewRazonly.visibility = View.VISIBLE
                    }
                    Mob.CONDICION04 -> {
                        txtespecifiqueCondicionly.visibility = View.GONE
                        txtnewNcontrolly.visibility = View.VISIBLE
                        txtnewRazonly.visibility = View.VISIBLE
                    }
                    Mob.CONDICION08 -> {
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

            btSendObs.setOnClickListener {
                if (Mob.authData?.rol == "E" && Functions.isOnline(ctx)) {
                    saveCap()
                    senFormulario(CreateForm.create())
                } else if (!Functions.isOnline(ctx)){
                    val color = ContextCompat.getColor(ctx, R.color.dark_red)
                    msgBallom("Sin internet disponible", Mob.WIDTH160DP, color)
                }
            }
            btEnd.setOnClickListener { endForm() }

        }
    }

    private fun senFormulario(form: ModelForm) {
        val screen = AlertDialog.Builder(ctx)
        val screenBlack: AlertDialog = screen.create()
        screenBlack.setCancelable(false)
        screenBlack.show()
        lifecycleScope.launch {
            bindinginfo.barInforme.visibility = View.VISIBLE
            val resp = dvmInforme.sendForm(form)
            savedForm(form)
            with(resp.code) {
                when {
                    contains("java.net.UnknownHostException")  ||
                            contains("java.net.ConnectException")  ||
                            contains("java.net.SocketTimeoutException") -> {
                        val color = ContextCompat.getColor(ctx, R.color.dark_red)
                        msgBallom("Sin respuesta del servidor", Mob.WIDTH180DP, color)
                    }
                    contains("failed to connect to") -> {
                        val color = ContextCompat.getColor(ctx, R.color.dark_red)
                        msgBallom("No es posible conectar al servidor", Mob.WIDTH220DP, color)
                    }
                    contains("200")
                    -> {
                        Handler(Looper.getMainLooper()).postDelayed({
                            val respGson: String = try {
                                Gson().toJson(resp.body) as String
                            } catch (e: JsonParseException) { "" }
                            carga(respGson)
                        }, (Mob.TIME800MS))
                    }
                    contains("400") -> {
                        val color = ContextCompat.getColor(ctx, R.color.dark_red)
                        msgBallom("Error en el cuestionario", Mob.WIDTH180DP, color)
                    }
                    contains("401") -> {
                        val color = ContextCompat.getColor(ctx, R.color.dark_red)
                        msgBallom("Sesión expirada", Mob.WIDTH160DP, color)
                    }
                    contains("404") -> {
                        val color = ContextCompat.getColor(ctx, R.color.dark_red)
                        msgBallom("Formulario no encontrado", Mob.WIDTH180DP, color)
                    }
                    contains("500") -> {
                        val color = ContextCompat.getColor(ctx, R.color.dark_red)
                        msgBallom("Error en el servidor", Mob.WIDTH160DP, color)
                    }
                    else -> {
                        if (resp.server.isNullOrEmpty()) {
                            val color = ContextCompat.getColor(ctx, R.color.dark_red)
                            msgBallom("Fuera de cobertura", Mob.WIDTH160DP, color)
                        }
                        else Toast.makeText(ctx, "${resp.server}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            Handler(Looper.getMainLooper()).postDelayed({
                screenBlack.dismiss()
                bindinginfo.barInforme.visibility = View.INVISIBLE
            }, (Mob.TIME800MS))


        }
    }

    private suspend fun savedForm(form: ModelForm): Long {
        val nameForm: String
        with(Mob) {
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
        if (jsonModel?.poseeIncon == "false"){
            bindSend.txtmsgStyle.text = getString(R.string.sendSuccess)
            bindSend.txt2styleFly.visibility = View.GONE
        } else bindSend.txtmsgStyle.text = getString(R.string.sendSuccessWithIncon)

        val ncont = Functions.ceroLeft(jsonModel?.ncontrol?.toString() ?: "0", Mob.FOR5DIGITS)
        bindSend.txt1styleF.text = try { ncont.toEditable() }
        catch (e: RuntimeException) { "00".toEditable() }

        bindSend.txt2styleF.text = try {
            jsonModel?.inconsistencias?.joinToString("\n\n")?.toEditable() }
        catch (e: RuntimeException) { "".toEditable() }

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

    fun saveCap(): List<String> {
        with(bindinginfo) {
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
        return emptyList()
    }
}