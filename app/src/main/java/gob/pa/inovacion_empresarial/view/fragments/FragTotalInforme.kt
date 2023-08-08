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
import gob.pa.inovacion_empresarial.databinding.StyleMsgFormBinding
import gob.pa.inovacion_empresarial.function.Functions
import gob.pa.inovacion_empresarial.function.Functions.toEditable
import gob.pa.inovacion_empresarial.model.DVModel
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelForm
import gob.pa.inovacion_empresarial.model.ModelResponse
import gob.pa.inovacion_empresarial.view.FormActivity
import gob.pa.inovacion_empresarial.view.MainActivity
import kotlinx.coroutines.launch
import java.lang.reflect.Type


class FragTotalInforme : Fragment() {

    private lateinit var bindinginfo: ModuloTotalInfoBinding
    private lateinit var ctx: Context
    private val dvmInforme: DVModel by viewModels()
    private var condID = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindinginfo = ModuloTotalInfoBinding.inflate(layoutInflater)
        ctx = requireContext()
        return bindinginfo.root
    }

    override fun onResume() {
        super.onResume()
        Mob.indiceFormulario = Mob.OBSP24
        fillOut()
    }
//    override fun onPause() {
//        super.onPause()
//    }

    private fun fillOut() {
        with(bindinginfo) {
            txtInfoObsEncuesta.text = Mob.obsEncuesta?.toEditable() ?: "".toEditable()
            txtInfoObsModulo.text = Mob.obsModulo?.toEditable() ?: "".toEditable()
            condID = Mob.formComp?.cond ?: ""
            //txtespecifiqueCondicion.text = "".toEditable()

        }
        onAction()
    }

    private fun onAction() {
        with(bindinginfo) {

            if (Mob.authData?.rol == "E") {
                btSendObs.isEnabled = true
                btSaveObs.isEnabled = true
                btEnd.isEnabled = true
            } else {
                btSendObs.isEnabled = false
                btSaveObs.isEnabled = false
                btEnd.isEnabled = false
            }

            if (lbcondicionID.text.isNullOrEmpty()) lbcondicionID.text = condID

            val idCondInt = try { condID.toInt() -1 }
            catch (e: java.lang.NumberFormatException) { null }
            if (idCondInt != null)
                txtCondicion.setText(Mob.arrCondicion[idCondInt],false)

            with(txtCondicion.text.toString()) {
                when {
                    contains(Mob.arrCondicion[Mob.CONDICION02]) ->
                        txtespecifiqueCondicionly.visibility = View.VISIBLE
                    contains(Mob.arrCondicion[Mob.CONDICION04]) ->
                        txtespecifiqueCondicionly.visibility = View.VISIBLE
                    contains(Mob.arrCondicion[Mob.CONDICION08]) ->
                        txtespecifiqueCondicionly.visibility = View.VISIBLE
                    else -> txtespecifiqueCondicionly.visibility = View.INVISIBLE
                }
            }
            val condicion = ArrayAdapter(ctx, R.layout.style_box2, Mob.arrCondicion)
            condicion.setDropDownViewResource(R.layout.style_list)
            txtCondicion.setAdapter(condicion)
            txtCondicion.setOnItemClickListener { _, _, pos, _ ->
                val idCond = "0"+(pos + 1)
                condID = idCond
                lbcondicionID.text = idCond
                when (pos) {
                    Mob.CONDICION02, Mob.CONDICION04, Mob.CONDICION08 ->
                        txtespecifiqueCondicionly.visibility = View.VISIBLE
                    else -> txtespecifiqueCondicionly.visibility = View.INVISIBLE
                }
            }

            btSendObs.setOnClickListener {
                //----------------------------------- ARMAR condicionEmpadronamiento obj
                senFormulario(CreateForm().create())
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
            bindinginfo.progressBar.visibility = View.VISIBLE
            val resp = dvmInforme.sendForm(form)
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
                        msgBallom("Error en el servidor", Mob.WIDTH180DP, color)
                    }
                    else -> {
                        Toast.makeText(ctx, "${resp.server}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            Handler(Looper.getMainLooper()).postDelayed({
                screenBlack.dismiss()
                bindinginfo.progressBar.visibility = View.INVISIBLE
            }, (Mob.TIME800MS))


        }
    }
    private fun msgBallom(msg: String, width: Int, color: Int) {
        val alert = Functions.msgBallom(msg, width, ctx, color)
        alert.showAlignBottom(bindinginfo.guideline)
        alert.dismissWithDelay(Mob.TIMELONG4SEG)
    }

    private fun carga(data: String) {
        val msgSend = AlertDialog.Builder(ctx)
        val bindSend: StyleMsgFormBinding =
            DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.style_msg_form, null, false)


        val type: Type = object : TypeToken<ModelResponse?>() {}.type
        val objResponse: ModelResponse? = Gson().fromJson<ModelResponse>(data, type)

        bindSend.txt1styleF.text = try {
            objResponse?.ncontrol.toString().toEditable() }
        catch (e: java.lang.RuntimeException) { "00".toEditable() }

        bindSend.txt2styleF.text = try {
            objResponse?.inconsistencias?.joinToString("\n\n")?.toEditable() }
        catch (e: java.lang.RuntimeException) { "".toEditable() }

        Mob.inconsistencias = objResponse?.inconsistencias?.size ?: 0
        msgSend.setView(bindSend.root)
        val dialog: AlertDialog = msgSend.create()
        dialog.setCancelable(false)
        dialog.show()


        dialog.window?.setGravity(Gravity.CENTER)

        bindSend.btEnd.setOnClickListener {
            dialog.dismiss()
            endForm()
        }


        bindSend.btCancel.setOnClickListener {
            dialog.dismiss()
            bindinginfo.btEnd.visibility = View.VISIBLE
        }

    }

    private fun endForm() {
        bindinginfo.progressBar.visibility = View.VISIBLE
        val screen = AlertDialog.Builder(ctx)
        val screenBlack: AlertDialog = screen.create()
        screenBlack.setCancelable(false)
        screenBlack.show()

        val form = activity
        if (form == FormActivity()) {
            val act = form as FormActivity
            act.seeCaps(null)
        }
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(ctx, MainActivity::class.java))
            CreateForm().resetForm()
            Handler(Looper.getMainLooper()).postDelayed({
                form?.finish()
                screenBlack.dismiss()
            }, (Mob.TIME800MS))
            bindinginfo.progressBar.visibility = View.INVISIBLE
        }, (Mob.TIME800MS))
    }

}