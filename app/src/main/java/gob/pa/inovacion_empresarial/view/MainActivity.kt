package gob.pa.inovacion_empresarial.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton
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
    lateinit var pagerMain: ViewPager2
    private val dvmMain: DVModel by viewModels()
    var aDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        main = ActivityMainBinding.inflate(layoutInflater)
        setContentView((main.root))
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        Mob.version = ("Versión: " + this.packageManager.getPackageInfo
            (this.packageName, 0).versionName)

        pagerMain = main.viewpagerMain
        pagerMain.isUserInputEnabled = false
        pagerMain.adapter = AdapterPagerMain(Mob.arrMain, supportFragmentManager, lifecycle)

        val decorView: View = window.decorView
        decorView.systemUiVisibility =
            decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        window.statusBarColor = ContextCompat.getColor(this, R.color.celeste)
    }
    override fun onPause() {
        super.onPause()
        if (aDialog?.isShowing == true){
            aDialog?.dismiss()
        }
    }

    override fun onResume() {
        super.onResume()
        pagerMain.visibility = View.INVISIBLE
        main.barMain.visibility = View.VISIBLE
        if (AppCache.remGET(this) && Mob.authData?.result?.token.isNullOrEmpty()) {
            Mob.authData = AppCache.loginGET(this)             //----- CARGA TOKEM
            if (Mob.authData?.result?.token.isNullOrEmpty()) {     //----- TOKEN VACIO IR A LOGIN
                Handler(Looper.getMainLooper()).postDelayed({
                    pagerMain.setCurrentItem(Mob.LOGIN0, false)
                    pagerMain.visibility = View.VISIBLE
                    main.barMain.visibility = View.GONE
                }, (Mob.TIME500MS))
            } else {
                lifecycleScope.launch {
                    validate()
                    pagerMain.setCurrentItem(Mob.INIT01, false)
                    RoomView(dvmMain, this@MainActivity).viewRoom()
                }
            }
        } else if (!Mob.authData?.result?.token.isNullOrEmpty()) {     //----- TOKEN VACIO IR A LOGIN
            lifecycleScope.launch {
                validate()
                pagerMain.setCurrentItem(Mob.INIT01, false)
                RoomView(dvmMain, this@MainActivity).viewRoom()
            }
        } else {
            pagerMain.visibility = View.VISIBLE
            main.barMain.visibility = View.GONE
            pagerMain.setCurrentItem(Mob.LOGIN0, false)
        }
        pagerMain.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position != Mob.LOGIN0) validate() //--- CAMBIAR VERIFICADOR A PUNTOS CLAVES ---
                if (position == Mob.INIT01) {
                    lifecycleScope.launch {
                        RoomView(dvmMain, this@MainActivity).viewRoom()
                    }
                }
            }
        })
        main.btMainTest.setOnClickListener { validate() }
    }

    override fun onBackPressed() {
        val dialogBack = AlertDialog.Builder(this)
        val bindmsg: StyleMsgAlertBinding = StyleMsgAlertBinding.inflate(layoutInflater)
        val ctx = this

        if (pagerMain.currentItem == Mob.INIT01) {
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

    fun validate() { //-------- Validador de TOKEN
        val ctx = this
        lifecycleScope.launch {
            val resp = dvmMain.seeToken()
            Handler(Looper.getMainLooper()).postDelayed({
                when (resp?.code) {
                    Mob.CODE200 -> {
                        pagerMain.visibility = View.VISIBLE
                        main.barMain.visibility = View.GONE
                    }
                    Mob.CODE401 -> {
                        pagerMain.visibility = View.VISIBLE
                        main.barMain.visibility = View.GONE
                        pagerMain.setCurrentItem(Mob.LOGIN0, false)
                        val color = ContextCompat.getColor(ctx, R.color.dark_red)
                        val alert = Functions.msgBallom(
                            "Sesión expirada", Mob.WIDTH160DP, ctx, color)
                        alert.showAlignBottom(main.btMainTest)
                        alert.dismissWithDelay(Mob.TIMELONG4SEG)
                    }
                    else -> {
                        pagerMain.visibility = View.VISIBLE
                        main.barMain.visibility = View.GONE
                    }
                }
            }, (Mob.TIME500MS))
            Log.i("RESP:","\n--------CODE: ${resp?.code}\n--------MSG: ${resp?.msg}\n")

        }
    }

}