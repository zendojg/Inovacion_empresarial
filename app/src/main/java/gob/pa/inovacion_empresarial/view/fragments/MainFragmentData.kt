package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.databinding.FragUserMainBinding
import gob.pa.inovacion_empresarial.databinding.StyleMsgAlertBinding
import gob.pa.inovacion_empresarial.databinding.StyleMsgFormBinding
import gob.pa.inovacion_empresarial.function.AppCache
import gob.pa.inovacion_empresarial.function.CreateBackUp
import gob.pa.inovacion_empresarial.function.Functions
import gob.pa.inovacion_empresarial.model.DVModel
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelForm
import gob.pa.inovacion_empresarial.service.room.RoomView
import kotlinx.coroutines.launch
import java.lang.reflect.Type
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class MainFragmentData : Fragment() {
    private lateinit var bindingUser: FragUserMainBinding
    private lateinit var ctx: Context
    private val dvmUser: DVModel by viewModels()
    private var aDialog: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingUser = FragUserMainBinding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingUser.root
    }
    override fun onPause() {
        super.onPause()
        if (aDialog?.isShowing == true)  aDialog?.dismiss()
    }
    override fun onResume() {
        super.onResume()
        bindingUser.apply {
            val expira = Mob.authData?.result?.infotoken?.expire?.split("T")
            val rol = when (Mob.authData?.rol) {
                "E" -> "Empadronador"
                "S" -> "Supervisor"
                else -> "Usuario"
            }
            lbROLUser.text = rol
            lbuserUser.text = Mob.authData?.user ?: "Desconocido"
            lbnameUser.text = Mob.authData?.name ?: "Desconocido"
            lbfechaUser.text = expira?.get(0) ?: "0/0/0"

            btExitUser.setOnClickListener { if (aDialog?.isShowing != true) logout() }
            btrenewUser.setOnClickListener { if (aDialog?.isShowing != true) renewData() }
            btbackUser.setOnClickListener {
                val pager = activity?.findViewById<ViewPager2>(R.id.viewpagerMain)
                pager?.setCurrentItem(Mob.INIT01, true)
            }
            btBackUp.setOnClickListener {
                bindingUser.barUser.visibility = View.VISIBLE
                val screen = AlertDialog.Builder(ctx)
                aDialog = screen.create()
                aDialog?.setCancelable(false)
                aDialog?.show()
                lifecycleScope.launch {
                    if (CreateBackUp.saved(ctx)) Handler(Looper.getMainLooper()).postDelayed({
                        bindingUser.barUser.visibility = View.INVISIBLE
                        aDialog?.dismiss()
                        msgBallon("Nuevo respaldo creado")
                    }, (Mob.TIME1S))
                    else {
                        bindingUser.barUser.visibility = View.INVISIBLE
                        aDialog?.dismiss()
                        msgBallon("Error de respaldo!")
                    }
                }

            }
            bindingUser.barUser.visibility = View.VISIBLE
            lifecycleScope.launch { actividad() }
        }
    }

    private fun renewData() {
        val msgLogout = AlertDialog.Builder(ctx)
        val bindSend: StyleMsgFormBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(ctx),
                R.layout.style_msg_form, null, false
            )
        bindSend.apply {
            lbtittlestyleF.text = getString(R.string.renewtittle)
            txtmsgStyle.text = getString(R.string.warningRenew)
            txt1styleF.visibility = View.GONE
            txt2styleFly.visibility = View.GONE
            txt0styleFly.visibility = View.VISIBLE
            txtmsgStyle.visibility = View.VISIBLE

            msgLogout.setView(view)
            msgLogout.setView(bindSend.root)
            aDialog = msgLogout.create()
            aDialog?.setCancelable(false)
            aDialog?.show()

            btEnd.text = getString(R.string.internalupdt)
            btCancel.icon = ContextCompat.getDrawable(ctx, R.drawable.img_backs)
            btEnd.icon = ContextCompat.getDrawable(ctx, R.drawable.img_system_update)
            btEnd.backgroundTintList =
                ContextCompat.getColorStateList(ctx, R.color.holo_blue_dark)

            btCancel.setOnClickListener { aDialog?.dismiss() }
            btEnd.setOnClickListener {
                if (txt0styleF.text.isNullOrEmpty()) {
                    txt0styleFly.error = "Ingrese la clave de acceso"
                } else if (txt0styleF.text.toString() == Mob.pass) {
                    aDialog?.dismiss()
                    bindingUser.barUser.visibility = View.VISIBLE
                    val screen = AlertDialog.Builder(ctx)
                    aDialog = screen.create()
                    aDialog?.setCancelable(false)
                    aDialog?.show()
                    Handler(Looper.getMainLooper()).postDelayed({
                        aDialog?.dismiss()
                        lifecycleScope.launch {
                            when (dvmUser.validate()) {
                                true -> {
                                    RoomView(dvmUser, ctx).viewRoom(true)
                                    msgBallon("Datos actualizados")
                                }
                                false ->  msgBallon("Sesión expirada")
                                else  ->  msgBallon("No es posible actualizar")
                            }
                            activity?.runOnUiThread { bindingUser.barUser.visibility = View.INVISIBLE }
                        }
                    }, (Mob.TIME1S))
                } else { txt0styleFly.error = "Clave incorrecta" }
            }
        }
    }
    private fun msgBallon(msg: String) {
        Handler(Looper.getMainLooper()).postDelayed({
            val alert = Functions.msgBallom(msg, Mob.WIDTH160DP, ctx, R.color.dark_red)
            alert.showAlignBottom(bindingUser.txtwarningUser)
            alert.dismissWithDelay(Mob.TIMELONG2SEG)
        }, (Mob.TIME500MS))
    }

    private fun logout() {
        val msgLogout = AlertDialog.Builder(ctx)
        val bindSend: StyleMsgAlertBinding =
            DataBindingUtil.inflate(LayoutInflater.from(ctx),
                R.layout.style_msg_alert, null, false)
        with (bindSend) {
            msgtitle.text = getString(R.string.closeSesion)
            btpositivo.text = getString(R.string.cancel)
            btnegativo.text = getString(R.string.logout)
            msg1.visibility = View.GONE
            msg2.visibility = View.GONE
            msgLogout.setView(view)
            msgLogout.setView(bindSend.root)
            aDialog = msgLogout.create()
            aDialog?.setCancelable(false)
            aDialog?.show()

            btpositivo.icon = ContextCompat.getDrawable(ctx, R.drawable.img_backs)
            btnegativo.icon = ContextCompat.getDrawable(ctx, R.drawable.img_logout)
            btnegativo.backgroundTintList =
                ContextCompat.getColorStateList(ctx, R.color.dark_pink)
            btpositivo.setOnClickListener { aDialog?.dismiss() }
            btnegativo.setOnClickListener {
                aDialog?.dismiss()
                Mob.authData = null
                AppCache.loginCLEAR(ctx)
                val pager = activity?.findViewById<ViewPager2>(R.id.viewpagerMain)
                pager?.setCurrentItem(Mob.LOGIN0, true)
            }
        }
    }

    private suspend fun actividad() {
        if (!Mob.authData?.user.isNullOrEmpty()) {
            var sendForms = 0
            var notsendForms = 0

            val dateNow: MutableList<String?> = mutableListOf()
            val dateWeek: MutableList<String?> = mutableListOf()
            val dateToday = if(Build.VERSION.SDK_INT >= Mob.VERSION) LocalDate.now() else null

            val roomDMC = RoomView(dvmUser, ctx).getFormsUser(Mob.authData?.user ?: "")
            val retroSERVER = dvmUser.formsGetUser(Mob.authData?.user ?: "")
            val listAsign: Int = retroSERVER?.body?.size ?: 0


            if (!retroSERVER?.body.isNullOrEmpty()) /*-- contador de forms enviados --*/
                for (i in retroSERVER?.body!!) if (i.tieneIncon != null) sendForms += 1

            for (i in roomDMC) { /*-- contador y enlistador de forms en el DMC --*/
                val type: Type = object : TypeToken<ModelForm?>() {}.type
                val listTest = Gson().fromJson<ModelForm>(i.saveForm, type)
                val onlyDate = i.saveDate?.split(" ")?.get(0)


                val dateForm = if(Build.VERSION.SDK_INT >= Mob.VERSION)
                    LocalDate.parse(onlyDate ?: "0-0-0") else null


                /*--
                val semanasDesdeUltimoLunes = if(Build.VERSION.SDK_INT >= Mob.VERSION)
                    ChronoUnit.WEEKS.between(dateForm?.with(DayOfWeek.MONDAY), dateForm).toInt()
                else null*/


                if (dateForm == dateToday && dateForm != null) dateNow += i.saveDate

                if(Build.VERSION.SDK_INT >= Mob.VERSION)
                    if(dateForm?.isAfter(dateToday?.minus(7, ChronoUnit.DAYS)) == true)
                        dateWeek += i.saveDate
                if (listTest.tieneIncon == null) notsendForms += 1 //--- contar no editados
            }
            activity?.runOnUiThread {
                Handler(Looper.getMainLooper()).postDelayed({
                    bindingUser.apply {
                        barUser.visibility = View.INVISIBLE
                        txtnotsendUser.text = notsendForms.toString()
                        txtsendUser.text = sendForms.toString()
                        txtasignFormUser.text = listAsign.toString()
                        txtsavedmUser.text = roomDMC.size.toString()
                        txtcomplettoday.text = dateNow.size.toString()
                        txtcompletweek.text = dateWeek.size.toString()
                    }
                }, (Mob.TIME500MS))
            }
        }
    }
}