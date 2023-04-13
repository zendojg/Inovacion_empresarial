package gob.pa.inovacion_empresarial.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import gob.pa.inovacion_empresarial.databinding.ActivityMainBinding
import android.content.res.Configuration
import android.graphics.Color
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment


class MainActivity : AppCompatActivity() {

    companion object{
        var indice: Int = 0
    }

    private lateinit var mainbinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView((mainbinding.root))
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


        val decorView: View = window.decorView
        window.statusBarColor = Color.WHITE
        decorView.systemUiVisibility =
            decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        //------------------------------------------------------------------------------------------ Expandir y Contraer
        //------------------------------------------------------------------------------------------ " " Encuesta
        mainbinding.btEncuestaExpandMain.setOnClickListener {
            if (!mainbinding.linearEncuestaMain.isVisible) { //-- expandir
                mainbinding.linearEncuestaMain.visibility = View.VISIBLE
                mainbinding.linearModuloMain.visibility = View.GONE
                mainbinding.linearpiemain.visibility = View.GONE

                mainbinding.btEncuestaExpandMain.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0, android.R.drawable.arrow_up_float, 0)
                mainbinding.btModuloExpandMain.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0, android.R.drawable.arrow_down_float, 0)
            }
            else { //-- contraer
                mainbinding.linearEncuestaMain.visibility = View.GONE
                mainbinding.linearpiemain.visibility = View.VISIBLE
                mainbinding.btEncuestaExpandMain.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0, android.R.drawable.arrow_down_float, 0)
            }
        }
        //------------------------------------------------------------------------------------------ " " Modulo
        mainbinding.btModuloExpandMain.setOnClickListener {
            if (!mainbinding.linearModuloMain.isVisible) { //-- expandir
                mainbinding.linearModuloMain.visibility = View.VISIBLE
                mainbinding.linearEncuestaMain.visibility = View.GONE
                mainbinding.linearpiemain.visibility = View.GONE

                mainbinding.btModuloExpandMain.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0, android.R.drawable.arrow_up_float, 0)
                mainbinding.btEncuestaExpandMain.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0, android.R.drawable.arrow_down_float, 0)
            }
            else { //-- contraer
                mainbinding.linearModuloMain.visibility = View.GONE
                mainbinding.linearpiemain.visibility = View.VISIBLE
                mainbinding.btModuloExpandMain.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0, android.R.drawable.arrow_down_float, 0)
            }
        }
        //------------------------------------------------------------------------------------------
        //------------------------------------------------------------------------------------------ Comenzar Encuesta
        mainbinding.btEncuestaMain.setOnClickListener { viewpager(0) }
        mainbinding.btcap01main.setOnClickListener { viewpager(0) }
        mainbinding.btcap02main.setOnClickListener { viewpager(1) }
        mainbinding.btcap03main.setOnClickListener { viewpager(3) }
        mainbinding.btcap04main.setOnClickListener { viewpager(4) }
        mainbinding.btcap05main.setOnClickListener { viewpager(5) }
        mainbinding.btcap06main.setOnClickListener { viewpager(7) }
        mainbinding.btcap07main.setOnClickListener { viewpager(11) }
        mainbinding.btcap08main.setOnClickListener { viewpager(14) }
        mainbinding.btcap09main.setOnClickListener { viewpager(15) }
        mainbinding.btcap10main.setOnClickListener { viewpager(17) }
        //------------------------------------------------------------------------------------------ Comenzar Modulo
        mainbinding.btModuloMain.setOnClickListener { viewpager(18) }
        mainbinding.btsec01main.setOnClickListener { viewpager(18) }
        mainbinding.btsec02main.setOnClickListener { viewpager(19) }
        mainbinding.btsec03main.setOnClickListener { viewpager(20) }
        mainbinding.btsec04main.setOnClickListener { viewpager(21) }
        mainbinding.btsec05main.setOnClickListener { viewpager(22) }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        when (newConfig.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> {
                when {
                    mainbinding.linearEncuestaMain.isVisible -> {
                        mainbinding.linearpiemain.visibility = View.GONE
                    }
                    mainbinding.linearModuloMain.isVisible -> {
                        mainbinding.linearpiemain.visibility = View.GONE
                    }
                    else -> {
                        mainbinding.linearpiemain.visibility = View.VISIBLE
                    }
                }
            }
            Configuration.ORIENTATION_LANDSCAPE -> {
                mainbinding.linearpiemain.visibility = View.GONE
            }

            Configuration.ORIENTATION_SQUARE -> {
                TODO()
            }
            Configuration.ORIENTATION_UNDEFINED -> {
                TODO()
            }
        }
    }


    private fun viewpager(pos: Int) {
        indice = pos
        val intent = Intent(this,ViewPager::class.java)
        startActivity(intent)
    }

}