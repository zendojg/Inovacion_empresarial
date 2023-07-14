package gob.pa.inovacion_empresarial.view

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.adapters.AdapterMain
import gob.pa.inovacion_empresarial.databinding.ActivityMainBinding
import gob.pa.inovacion_empresarial.function.AppCache
import gob.pa.inovacion_empresarial.function.Functions
import gob.pa.inovacion_empresarial.model.DVModel
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.service.room.FormDB
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
        pagerMain.adapter = AdapterMain(Mob.arrMain, supportFragmentManager, lifecycle)

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
        if (AppCache.remGET(this)) {
            Mob.authData = AppCache.loginGET(this)             //----- CARGA TOKEM
            if (Mob.authData?.result?.token.isNullOrEmpty()) {     //----- TOKEN VACIO IR A LOGIN
                Handler(Looper.getMainLooper()).postDelayed({
                    pagerMain.setCurrentItem(Mob.LOGIN0, false)
                    pagerMain.visibility = View.VISIBLE
                    main.barMain.visibility = View.GONE
                }, (Mob.TIME500MS).toLong())
            } else {
                lifecycleScope.launch {
                    validate()
                    pagerMain.setCurrentItem(Mob.INIT01, false)
                    RoomView(dvmMain, this@MainActivity).viewRoom()
                }
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
        val msg: View = layoutInflater.inflate(R.layout.style_msg_alert, null)
        val btpositivo: MaterialButton = msg.findViewById(R.id.btpositivo)
        val btnegativo: MaterialButton = msg.findViewById(R.id.btnegativo)
        val msgT: TextView = msg.findViewById(R.id.msgtitle)
        val msg1: TextView = msg.findViewById(R.id.msg1)
        val msg2: TextView = msg.findViewById(R.id.msg2)

        when (pagerMain.currentItem) {
            Mob.PAGE02, Mob.PAGE03, Mob.PAGE04 -> {
                pagerMain.setCurrentItem(Mob.INIT01,false) }
            else -> {
                btpositivo.text = getString(R.string.proceed)
                btnegativo.text = getString(R.string.close)
                msgT.text = getString(R.string.closeApp)
                msg1.visibility = View.GONE
                msg2.visibility = View.GONE
                dialogBack.setView(msg)

                aDialog = dialogBack.create()
                aDialog?.show()
                btpositivo.icon = ContextCompat.getDrawable(this,R.drawable.img_back)
                btnegativo.icon = ContextCompat.getDrawable(this,R.drawable.img_exit_app)

                btpositivo.setOnClickListener {
                    aDialog?.dismiss()
                }
                btnegativo.setOnClickListener {
                    finishAffinity()
                    this.finish()
                    exitProcess(-1)
                }
            }
        }
    }

    fun validate() { //-------- Validador de TOKEN
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
                        val alert = Functions.msgBallom(
                            "Sesión expirada", Mob.WIDTHBALLON160, this@MainActivity)
                        alert.showAlignBottom(main.btMainTest)
                        alert.dismissWithDelay(Mob.TIMEBALLON4SEG)
                    }
                    else -> {
                        pagerMain.visibility = View.VISIBLE
                        main.barMain.visibility = View.GONE
                    }
                }
            }, (Mob.TIME500MS).toLong())
            Log.i("RESP:","\n--------CODE: ${resp?.code}\n--------MSG: ${resp?.msg}\n")

        }
    }

}