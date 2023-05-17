package gob.pa.inovacion_empresarial.view

import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.databinding.ActivityFormBinding
import gob.pa.inovacion_empresarial.databinding.ActivityMainBinding
import gob.pa.inovacion_empresarial.model.Mobject


class FormActivity : AppCompatActivity() {

    private lateinit var formbinding: ActivityFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        formbinding = ActivityFormBinding.inflate(layoutInflater)
        setContentView((formbinding.root))
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        formbinding.versionForm.text = Mobject.version
    }

    override fun onResume() {
        super.onResume()

        actions()
    }

    private fun actions() {
        val ctx = this
        //------------------------------------------------------------------------------------------ Expandir y Contraer
        //------------------------------------------------------------------------------------------ " " Encuesta
        with(formbinding) {
            btEncuestaExpandMain.setOnClickListener {
                if (!linearEncuestaMain.isVisible) { //-- expandir
                    linearEncuestaMain.visibility = View.VISIBLE
                    linearModuloMain.visibility = View.GONE
                    layoutpiemain.visibility = View.GONE
                    btEncuestaExpandMain.setImageDrawable(
                        ContextCompat.getDrawable(ctx, android.R.drawable.arrow_up_float))


                } else { //-- contraer
                    layoutpiemain.visibility = View.VISIBLE
                    linearEncuestaMain.visibility = View.GONE
                    btEncuestaExpandMain.setImageDrawable(
                        ContextCompat.getDrawable(ctx, android.R.drawable.arrow_down_float))
                }
            }
            //-------------------------------------------------------------------------------------- " " Modulo
            btModuloExpandMain.setOnClickListener {
                if (!linearModuloMain.isVisible) { //-- expandir
                    linearModuloMain.visibility = View.VISIBLE
                    linearEncuestaMain.visibility = View.GONE

                    btModuloExpandMain.setImageDrawable(
                        ContextCompat.getDrawable(ctx, android.R.drawable.arrow_up_float))
                    btEncuestaExpandMain.setImageDrawable(
                        ContextCompat.getDrawable(ctx, android.R.drawable.arrow_down_float))
                } else { //-- contraer
                    linearModuloMain.visibility = View.GONE

                    btModuloExpandMain.setImageDrawable(
                        ContextCompat.getDrawable(ctx, android.R.drawable.arrow_down_float))
                }
            }
            //--------------------------------------------------------------------------------------
            //-------------------------------------------------------------------------------------- Comenzar Encuesta
            btEncuestaMain.setOnClickListener { viewpager(0) }
            btcap01main.setOnClickListener { viewpager(0) }
            btcap02main.setOnClickListener { viewpager(1) }
            btcap03main.setOnClickListener { viewpager(3) }
            btcap04main.setOnClickListener { viewpager(4) }
            btcap05main.setOnClickListener { viewpager(5) }
            btcap06main.setOnClickListener { viewpager(7) }
            btcap07main.setOnClickListener { viewpager(11) }
            btcap08main.setOnClickListener { viewpager(14) }
            btcap09main.setOnClickListener { viewpager(15) }
            btcap10main.setOnClickListener { viewpager(17) }
            //-------------------------------------------------------------------------------------- Comenzar Modulo
            btModuloMain.setOnClickListener { viewpager(18) }
            btsec01main.setOnClickListener { viewpager(18) }
            btsec02main.setOnClickListener { viewpager(19) }
            btsec03main.setOnClickListener { viewpager(20) }
            btsec04main.setOnClickListener { viewpager(21) }
            btsec05main.setOnClickListener { viewpager(22) }
        }
    }



    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        when (newConfig.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> {
                when {
                    formbinding.linearEncuestaMain.isVisible -> {
                    }
                    formbinding.linearModuloMain.isVisible -> {
                    }
                    else -> {
                    }
                }
            }
            Configuration.ORIENTATION_LANDSCAPE -> {
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
        Mobject.indiceEnc = pos
        val intent = Intent(this,ViewPager::class.java)
        startActivity(intent)
    }

}