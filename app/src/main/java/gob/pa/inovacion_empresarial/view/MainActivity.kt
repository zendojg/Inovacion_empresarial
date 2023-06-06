package gob.pa.inovacion_empresarial.view

import android.content.Context
import android.os.Bundle
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
import gob.pa.inovacion_empresarial.model.DVModel
import gob.pa.inovacion_empresarial.model.MVar
import gob.pa.inovacion_empresarial.service.room.FormDB
import kotlinx.coroutines.launch
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {

    private lateinit var mainbinding: ActivityMainBinding
    private lateinit var pagerMain: ViewPager2
    private val dvmMain: DVModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView((mainbinding.root))
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        MVar.version = ("Versión: "+ this.packageManager.getPackageInfo
            (this.packageName,0).versionName)

        pagerMain = mainbinding.viewpagerMain
        pagerMain.isUserInputEnabled = false

        val myAdapter = AdapterMain(MVar.arrMain, supportFragmentManager, lifecycle)

        pagerMain.adapter = myAdapter
        pagerMain.isUserInputEnabled = false
    }

    override fun onResume() {
        super.onResume()
        MVar.authData = AppCache.loginGET(this)
        if (MVar.authData?.result?.token.isNullOrEmpty()) {

            pagerMain.setCurrentItem(0, false)
            Toast.makeText(this, "Bienvenido", Toast.LENGTH_LONG).show()
        } else {
            lifecycleScope.launch {
                download()
            }
        }

        val decorView: View = window.decorView
        decorView.systemUiVisibility =
            decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        window.statusBarColor = ContextCompat.getColor(this, R.color.celeste)

        pagerMain.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 1)
                    lifecycleScope.launch {
                        download()
                    }
            }
        })


    }
    override fun onBackPressed() {
        val mesagePregunta = AlertDialog.Builder(this)
        val msg: View = layoutInflater.inflate(R.layout.style_msg_alert, null)
        val btpositivo: MaterialButton = msg.findViewById(R.id.btpositivo)
        val btnegativo: MaterialButton = msg.findViewById(R.id.btnegativo)
        val msgT: TextView = msg.findViewById(R.id.msgtitle)
        val msg1: TextView = msg.findViewById(R.id.msg1)
        val msg2: TextView = msg.findViewById(R.id.msg2)

        when {
            pagerMain.currentItem == 4 -> {
                pagerMain.setCurrentItem(1,false)
            }
            pagerMain.currentItem > 1 -> {
                pagerMain.setCurrentItem(pagerMain.currentItem - 1,false)
            }
            else -> {
                btpositivo.text = getString(R.string.cancel)
                btnegativo.text = getString(R.string.cerrarAp)
                msgT.text = getString(R.string.closeApp)
                msg1.visibility = View.GONE
                msg2.visibility = View.GONE
                mesagePregunta.setView(msg)

                val dialog: AlertDialog = mesagePregunta.create()
                dialog.show()
                btpositivo.icon = ContextCompat.getDrawable(this,R.drawable.img_back)
                btnegativo.icon = ContextCompat.getDrawable(this,R.drawable.img_exit_app)

                btpositivo.setOnClickListener {
                    dialog.dismiss()
                }
                btnegativo.setOnClickListener {
                    moveTaskToBack(true)
                    finishAffinity()
                    exitProcess(-1)
                }
            }
        }
    }



    suspend fun download() {
        val result: MutableLiveData<Boolean>? = null
        val roomDB = FormDB.buildDB(this)

        val proRoom = roomDB.dbFormDao().getProvArray()
        val distRoom = roomDB.dbFormDao().getDistVer()
        val correRoom = roomDB.dbFormDao().getCorrVer()
        val lugarRoom = roomDB.dbFormDao().getDistVer()

        if (proRoom.isEmpty()) { roomDB.dbFormDao().insertProv(dvmMain.getProv()) }
        if (distRoom.isEmpty()) { roomDB.dbFormDao().insertDist(dvmMain.getDist()) }
        if (correRoom.isEmpty()) { roomDB.dbFormDao().insertCorre(dvmMain.getCorre()) }
        if (lugarRoom.isEmpty()) { roomDB.dbFormDao().insertLugarP(dvmMain.getLugarP()) }

        pagerMain.setCurrentItem(1, false)
        result?.postValue(true)

    }



    fun validate(ctx: Context, dvModel: DVModel, pager: ViewPager2): LiveData<Boolean>? {
        val result: MutableLiveData<Boolean>? = null
        MVar.authData = AppCache.loginGET(ctx)

        if (MVar.authData?.result?.token.isNullOrEmpty()) {
            pager.setCurrentItem(0, false)
            Toast.makeText(ctx, "Bienvenido", Toast.LENGTH_LONG).show()
            result?.postValue(false)
        } else {
            lifecycleScope.launch {
                val response = dvModel.loginToken()
                with(response) {
                    when {
                        contains("java.net.UnknownHostException:") ||
                                contains("java.net.ConnectException:") ||
                                contains("java.net.SocketTimeoutException:") -> {
                            Toast.makeText(ctx, "No se pudo conectar al servidor",
                                Toast.LENGTH_LONG).show()
                            result?.postValue(false)
                        }
                        contains("200") -> { result?.postValue(true) }
                        contains("400") || contains("401") || contains("500") -> {
                            Toast.makeText(ctx, "Inicie Sesión nuevamente",
                                Toast.LENGTH_SHORT).show()
                            MVar.authData = null
                            AppCache.loginCLEAR(ctx)
                            pager.setCurrentItem(0, false)
                            result?.postValue(false)
                        }
                        else -> {
                            Log.i("----ERROR: ", "-$response-")
                            Toast.makeText(ctx, "Error de conexión", Toast.LENGTH_LONG).show()
                            result?.postValue(false)
                        }
                    }
                }
            }
        }
        return result
    }
}