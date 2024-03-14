package gob.pa.inovacion_empresarial.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.adapters.AdapterPagerMain
import gob.pa.inovacion_empresarial.databinding.ActivityMainBinding
import gob.pa.inovacion_empresarial.databinding.StyleMsgAlertBinding
import gob.pa.inovacion_empresarial.function.AppCache
import gob.pa.inovacion_empresarial.function.Functions
import gob.pa.inovacion_empresarial.model.DVModel
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.service.room.RoomView
import kotlinx.coroutines.launch
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {

    private lateinit var main: ActivityMainBinding
    private lateinit var pagerMain: ViewPager2
    private val dvmMain: DVModel by viewModels()
    private var aDialog: AlertDialog? = null
    private val ctx = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        main = ActivityMainBinding.inflate(layoutInflater)
        setContentView((main.root))
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        Mob.version = "Versión: ${packageManager.getPackageInfo(packageName, 0).versionName}"
        pagerMain = main.viewpagerMain
        pagerMain.isUserInputEnabled = false
        pagerMain.adapter = AdapterPagerMain(Mob.arrMain, supportFragmentManager, lifecycle)
        window.statusBarColor = ContextCompat.getColor(this, R.color.celeste)
    }
    override fun onPause() {
        super.onPause()
        if (aDialog?.isShowing == true) aDialog?.dismiss()
    }

    override fun onResume() {
        super.onResume()
        pagerMain.visibility = View.INVISIBLE
        main.barMain.visibility = View.VISIBLE

        fun moveByToken(access: Boolean) {
            val movePage = if (!access) Mob.LOGIN0 else Mob.INIT01
            Handler(Looper.getMainLooper()).postDelayed({
                main.barMain.visibility = View.GONE
                pagerMain.setCurrentItem(movePage, false)
                pagerMain.visibility = View.VISIBLE
            }, (Mob.TIME1S))
        }
        val remember = AppCache.remGET(this)

        if (Mob.authData?.result?.token.isNullOrEmpty() && !remember) moveByToken(false)
        else {
            if (remember) Mob.authData = AppCache.loginGET(this)
            if (Mob.authData?.result?.token.isNullOrEmpty()) moveByToken(false)
            else lifecycleScope.launch {
                fun msgAlert(msg: String, msgWidth: Int) {
                    val color = ContextCompat.getColor(ctx, R.color.dark_red)
                    val alert = Functions.msgBallom(
                        msg, msgWidth, ctx, color)
                    alert.showAlignBottom(main.btMainTest)
                    alert.dismissWithDelay(Mob.TIMELONG4SEG)
                }
                val respServer = dvmMain.seeToken()
                if (!respServer.msg.isNullOrEmpty()) {
                    val mensaje = respServer.msg
                    msgAlert(mensaje, (mensaje.length * 7))
                    moveByToken(true)
                } else when (respServer.code){
                    Mob.CODE200 -> {
                        RoomView(dvmMain, ctx).viewRoom(false)
                        moveByToken(true)
                    }
                    Mob.CODE401 -> {
                        val mensaje = getString(R.string.msgInforme401)
                        msgAlert(mensaje, (mensaje.length * 7))
                        moveByToken(false)
                    }
                    else -> {
                        val mensaje = "Error: ${respServer.code}"
                        msgAlert(mensaje, (mensaje.length * 7))
                    }
                }

            }
        }
        main.btMainTest.setOnClickListener { /* Para pruebas */ }
    }

    override fun onBackPressed() {
        if (pagerMain.currentItem == Mob.INIT01 || pagerMain.currentItem == Mob.LOGIN0) {
            val dialogBack = AlertDialog.Builder(this)
            val bindmsg: StyleMsgAlertBinding = StyleMsgAlertBinding.inflate(layoutInflater)
            with (bindmsg) {
                btpositivo.text = getString(R.string.cancel)
                btnegativo.text = getString(R.string.close)
                msgtitle.text = getString(R.string.closeApp)
                msg1.visibility = View.GONE
                msg2.visibility = View.GONE
                btpositivo.icon = ContextCompat.getDrawable(ctx, R.drawable.img_backs)
                btnegativo.icon = ContextCompat.getDrawable(ctx, R.drawable.img_exit_app)
                btnegativo.backgroundTintList =
                    ContextCompat.getColorStateList(ctx, R.color.dark_pink)

                dialogBack.setView(bindmsg.root)
                aDialog = dialogBack.create()
                aDialog?.show()
                btpositivo.setOnClickListener {
                    aDialog?.dismiss()
                }
                btnegativo.setOnClickListener {
                    finishAffinity()
                    ctx.finish()
                    exitProcess(-1)
                }
            }
        }  else pagerMain.currentItem = Mob.INIT01
    }
}