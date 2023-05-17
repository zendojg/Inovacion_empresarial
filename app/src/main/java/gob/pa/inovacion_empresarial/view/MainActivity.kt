package gob.pa.inovacion_empresarial.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.adapters.AdapterMain
import gob.pa.inovacion_empresarial.databinding.ActivityMainBinding
import gob.pa.inovacion_empresarial.model.Mobject


class MainActivity : AppCompatActivity() {

    private lateinit var mainbinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView((mainbinding.root))
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        Mobject.version = ("Versión: "+ this.packageManager.getPackageInfo
            (this.packageName,0).versionName)

        mainbinding.viewpagerMain.isUserInputEnabled = false

        val myAdapter = AdapterMain(Mobject.arrMain, supportFragmentManager, lifecycle)

        mainbinding.viewpagerMain.adapter = myAdapter
        mainbinding.viewpagerMain.isUserInputEnabled = false
    }

    override fun onResume() {
        super.onResume()

        val decorView: View = window.decorView
        decorView.systemUiVisibility =
            decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        window.statusBarColor = ContextCompat.getColor(this, R.color.celeste)


    }





}