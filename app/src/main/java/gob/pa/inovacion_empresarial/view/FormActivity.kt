package gob.pa.inovacion_empresarial.view

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.adapters.AdapterPager
import gob.pa.inovacion_empresarial.databinding.ActivityFormBinding
import gob.pa.inovacion_empresarial.function.Functions.hideKeyboard
import gob.pa.inovacion_empresarial.model.Mob


class FormActivity : AppCompatActivity() {

    private lateinit var pagerbinding: ActivityFormBinding
    private var encuesta = Mob.indiceEnc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pagerbinding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(pagerbinding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        val myAdapter = AdapterPager(supportFragmentManager, lifecycle)
        if (myAdapter.itemCount == 0) {
            myAdapter.addListFragment(Mob.arrEncuestas)
            pagerbinding.viewpager.adapter = myAdapter
            pagerbinding.viewpager.isUserInputEnabled = false
        }

    }

    override fun onResume() {
        super.onResume()
        encuesta = Mob.indiceEnc
        actions()
    }

    private fun actions() {
        if (encuesta!= 0) {
            pagerbinding.viewpager.setCurrentItem(encuesta, false)
            spinPager(encuesta)
        }
        pagerbinding.btnextpager.setOnClickListener {
            if (pagerbinding.viewpager.currentItem==23) {
                pagerbinding.viewpager.setCurrentItem(0,false)
            } else pagerbinding.viewpager.setCurrentItem(
                pagerbinding.viewpager.currentItem + 1,false)
        }
        pagerbinding.btbackpager.setOnClickListener {
            pagerbinding.viewpager.setCurrentItem(pagerbinding.viewpager.currentItem - 1,false)
        }
        pagerbinding.btmenupager.setOnClickListener {
            pagerbinding.viewpager.setCurrentItem(0,false)
        }

        pagerbinding.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 0) {
                    pagerbinding.toolbarpager.visibility = View.GONE
                    pagerbinding.consttitlepager2.visibility = View.GONE
                    pagerbinding.linearsubtittlepager.visibility = View.GONE
                    pagerbinding.frameLayoutNav.visibility = View.GONE
                } else {
                    pagerbinding.toolbarpager.visibility = View.VISIBLE
                    pagerbinding.consttitlepager2.visibility = View.VISIBLE
                    pagerbinding.linearsubtittlepager.visibility = View.VISIBLE
                    pagerbinding.frameLayoutNav.visibility = View.VISIBLE
                }
                spinPager(position)
                //(viewpager.get(0) as RecyclerView).findViewHolderForAdapterPosition(position)
                //(viewpager[0] as RecyclerView).layoutManager?.findViewByPosition(position)
            }
        })

        pagerbinding.consttitlepager2.setOnClickListener { resizeTittle() }
        pagerbinding.bttoolresizepager.setOnClickListener { resizeTittle() }
        pagerbinding.linearsubtittlepager.setOnClickListener { resizeSubtittle() }
        pagerbinding.btsubtresizepager.setOnClickListener { resizeSubtittle() }

        pagerbinding.btobspager.setOnClickListener {
            observation(pagerbinding.viewpager.currentItem)
        }
        pagerbinding.btsavepager.setOnClickListener {
            Toast.makeText(applicationContext, "Formulario guardado", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onBackPressed() {
        if (pagerbinding.viewpager.currentItem == 0) {
            val mesagePregunta = AlertDialog.Builder(this)
            val msg: View = layoutInflater.inflate(R.layout.style_msg_alert, null)
            val btpositivo: MaterialButton = msg.findViewById(R.id.btpositivo)
            val btnegativo: MaterialButton = msg.findViewById(R.id.btnegativo)
            val msgT: TextView = msg.findViewById(R.id.msgtitle)
            val msg1: TextView = msg.findViewById(R.id.msg1)
            val msg2: TextView = msg.findViewById(R.id.msg2)


            btpositivo.text = getString(R.string.cancel)
            btnegativo.text = getString(R.string.cerrarAp)
            msgT.text = getString(R.string.closeApp)
            msg1.visibility = View.GONE
            msg2.visibility = View.GONE
            mesagePregunta.setView(msg)

            val dialog: AlertDialog = mesagePregunta.create()
            dialog.show()
            btpositivo.icon = ContextCompat.getDrawable(this, R.drawable.img_back)
            btnegativo.icon = ContextCompat.getDrawable(this, R.drawable.img_exit_app)

            btpositivo.setOnClickListener {
                dialog.dismiss()
            }
            btnegativo.setOnClickListener {
                dialog.dismiss()
                super.onBackPressed()
            }
        } else pagerbinding.viewpager.setCurrentItem(
            pagerbinding.viewpager.currentItem - 1,true)
    }

    private fun resizeTittle() {
        //var tittleheight = 0
        if (pagerbinding.txvtitlepager2.isVisible) {
            pagerbinding.bttoolresizepager.setImageResource(R.drawable.img_expand_more)
            pagerbinding.txvtitlepager2.visibility = View.GONE
            //pagerbinding.consttitlepager2.height.also { tittleheight = it }
            //pagerbinding.consttitlepager2.layoutParams.height = 35
        } else {
            pagerbinding.bttoolresizepager.setImageResource(R.drawable.img_expand_less)
            pagerbinding.txvtitlepager2.visibility = View.VISIBLE
            //pagerbinding.consttitlepager2.layoutParams.height = tittleheight
        }
    }
    private fun resizeSubtittle() {
        if (pagerbinding.txvsubtitlepager.isVisible) {
            pagerbinding.btsubtresizepager.setImageResource(R.drawable.img_expand_more)
            pagerbinding.txvsubtitlepager.visibility = View.GONE
        } else {
            pagerbinding.btsubtresizepager.setImageResource(R.drawable.img_expand_less)
            pagerbinding.txvsubtitlepager.visibility = View.VISIBLE
        }
    }

    private fun observation(position: Int) {
        val mesagePregunta = AlertDialog.Builder(this)
        val msg: View = layoutInflater.inflate(R.layout.window_observaciones, null)
        val btpositivo: Button = msg.findViewById(R.id.btsaveobs)
        val btnegativo: Button = msg.findViewById(R.id.btexitobs)
        val lbobs: TextView = msg.findViewById(R.id.txvtittleobs)
        val txtobs: TextView = msg.findViewById(R.id.txtobs)
        val encabezado: LinearLayout = msg.findViewById(R.id.linearObs)
        val color: Int

        lbobs.text = Mob.obsTittle
        txtobs.imeOptions = EditorInfo.IME_ACTION_DONE;
        txtobs.setRawInputType(InputType.TYPE_CLASS_TEXT);
        if (position<19) {
            color = ContextCompat.getColor(this, R.color.holo_blue_dark)
            txtobs.text = Mob.obsEncuesta
            encabezado.setBackgroundResource(R.drawable.background_shadow_celpast)

        } else {
            color = ContextCompat.getColor(this, R.color.cream_pastel)
            txtobs.text = Mob.obsModulo
            encabezado.setBackgroundResource(R.drawable.background_shadow_pastel)
        }
        btpositivo.backgroundTintList = ColorStateList.valueOf(color)
        btnegativo.backgroundTintList = ColorStateList.valueOf(color)


        mesagePregunta.setView(msg)
        val dialog: AlertDialog = mesagePregunta.create()
        dialog.show()
        dialog.window?.setGravity(Gravity.CENTER)


        btpositivo.setOnClickListener {
            if (pagerbinding.viewpager.currentItem<19) { Mob.obsEncuesta = txtobs.text.toString() }
            else { Mob.obsModulo = txtobs.text.toString() }
            dialog.dismiss()
            hideKeyboard()
        }
        btnegativo.setOnClickListener {
            dialog.dismiss()
            hideKeyboard()
        }
    }





    fun spinPager(position: Int) {
        val colorLetras : Int
        val colorFondo: Int
        val decorView: View = window.decorView
        val ctx = this
        with(pagerbinding){
            when {
                position == 0 -> {
                    decorView.systemUiVisibility =
                        decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
                    colorFondo = ContextCompat.getColor(ctx, R.color.celeste)
                    colorLetras = (Color.WHITE)
                }
                position < 19 -> {
                    Mob.obsTittle = "Encuesta  de Innovación en Empresas"
                    btobspager.visibility = View.VISIBLE
                    btsavepager.visibility = View.VISIBLE
                    colorLetras = (Color.WHITE)
                    decorView.systemUiVisibility =
                        decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
                    colorFondo = ContextCompat.getColor(ctx, R.color.holo_blue_dark)
                }
                position == 23 -> {
                    btobspager.visibility = View.INVISIBLE
                    btsavepager.visibility = View.INVISIBLE
                    colorLetras = (Color.WHITE)
                    decorView.systemUiVisibility =
                        decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
                    colorFondo = ContextCompat.getColor(ctx, R.color.teal_700)
                }
                else -> {
                    Mob.obsTittle = "Módulo de Comercio Electrónico"
                    btobspager.visibility = View.VISIBLE
                    btsavepager.visibility = View.VISIBLE
                    decorView.systemUiVisibility =
                        decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                    colorLetras = (Color.DKGRAY)
                    colorFondo = ContextCompat.getColor(ctx, R.color.cream_pastel)
                }

            }

            btsavepager.setColorFilter(colorLetras)
            btobspager.setColorFilter(colorLetras)
            btmenupager.setColorFilter(colorLetras)
            btbackpager.setColorFilter(colorLetras)
            btnextpager.setColorFilter(colorLetras)
            bttoolresizepager.setColorFilter(colorLetras)

            txvtitlepager.setTextColor(colorLetras)
            txvtitlepager2.setTextColor(colorLetras)

            window.statusBarColor = colorFondo
            constraintpager.setBackgroundColor(colorFondo)
            toolbarpager.setBackgroundColor(colorFondo)

            when (position) {
                1 -> { //--------------------------------------------------------------- capitulo 01
                    txvtitlepager.text = getString(R.string.cap01)
                    txvtitlepager2.text = getString(R.string.ctxt01)
                    txvsubtitlepager.text = getString(R.string.subcap000)
                }
                2 -> { //--------------------------------------------------------------- capitulo 02
                    txvtitlepager.text = getString(R.string.cap02)
                    txvtitlepager2.text = getString(R.string.ctxt02)
                    txvsubtitlepager.text = getString(R.string.subcap021)
                }
                3 -> { //--------------------------------------------------------------- capitulo 02
                    txvtitlepager.text = getString(R.string.cap02)
                    txvtitlepager2.text = getString(R.string.ctxt02)
                    txvsubtitlepager.text = getString(R.string.subcap022)
                }
                4 -> { //--------------------------------------------------------------- capitulo 03
                    txvtitlepager.text = getString(R.string.cap03)
                    txvtitlepager2.text = getString(R.string.ctxt03)
                    pagerbinding.txvsubtitlepager.text = getString(R.string.subcap000)
                }
                5 -> { //--------------------------------------------------------------- capitulo 04
                    txvtitlepager.text = getString(R.string.cap04)
                    txvtitlepager2.text = getString(R.string.ctxt04)
                    txvsubtitlepager.text = getString(R.string.subcap000)
                }
                6 -> { //--------------------------------------------------------------- capitulo 05
                    txvtitlepager.text = getString(R.string.cap05)
                    txvtitlepager2.text = getString(R.string.ctxt05)
                    txvsubtitlepager.text = getString(R.string.subcap051)
                }
                7 -> { //--------------------------------------------------------------- capitulo 05
                    txvtitlepager.text = getString(R.string.cap05)
                    txvtitlepager2.text = getString(R.string.ctxt05)
                    txvsubtitlepager.text = getString(R.string.subcap052)
                }
                8 -> { //--------------------------------------------------------------- capitulo 06
                    txvtitlepager.text = getString(R.string.cap06)
                    txvtitlepager2.text = getString(R.string.ctxt06)
                    txvsubtitlepager.text = getString(R.string.subcap061)
                }
                9 -> { //--------------------------------------------------------------- capitulo 06
                    txvtitlepager.text = getString(R.string.cap06)
                    txvtitlepager2.text = getString(R.string.ctxt06)
                    txvsubtitlepager.text = getString(R.string.subcap062)
                }
                10 -> { //--------------------------------------------------------------- capitulo 06
                    txvtitlepager.text = getString(R.string.cap06)
                    txvtitlepager2.text = getString(R.string.ctxt06)
                    txvsubtitlepager.text = getString(R.string.subcap063)
                }
                11 -> { //-------------------------------------------------------------- capitulo 06
                    txvtitlepager.text = getString(R.string.cap06)
                    txvtitlepager2.text = getString(R.string.ctxt06)
                    txvsubtitlepager.text = getString(R.string.subcap064)
                }
                12 -> { //-------------------------------------------------------------- capitulo 07
                    txvtitlepager.text = getString(R.string.cap07)
                    txvtitlepager2.text = getString(R.string.ctxt07)
                    txvsubtitlepager.text = getString(R.string.subcap001)
                }
                13 -> { //-------------------------------------------------------------- capitulo 07
                    txvtitlepager.text = getString(R.string.cap07)
                    txvtitlepager2.text = getString(R.string.ctxt07)
                    txvsubtitlepager.text = getString(R.string.subcap002)
                }
                14 -> { //-------------------------------------------------------------- capitulo 07
                    txvtitlepager.text = getString(R.string.cap07)
                    txvtitlepager2.text = getString(R.string.ctxt07)
                    txvsubtitlepager.text = getString(R.string.subcap003)
                }
                15 -> { //-------------------------------------------------------------- capitulo 08
                    txvtitlepager.text = getString(R.string.cap08)
                    txvtitlepager2.text = getString(R.string.ctxt08)
                    txvsubtitlepager.text = getString(R.string.subcap000)
                }
                16 -> { //-------------------------------------------------------------- capitulo 09
                    txvtitlepager.text = getString(R.string.cap09)
                    txvtitlepager2.text = getString(R.string.ctxt09)
                    txvsubtitlepager.text = getString(R.string.subcap001)
                }
                17 -> { //-------------------------------------------------------------- capitulo 09
                    txvtitlepager.text = getString(R.string.cap09)
                    txvtitlepager2.text = getString(R.string.ctxt09)
                    txvsubtitlepager.text = getString(R.string.subcap002)
                }
                18 -> { //-------------------------------------------------------------- capitulo 10
                    txvtitlepager.text = getString(R.string.cap10)
                    txvtitlepager2.text = getString(R.string.ctxt10)
                    txvsubtitlepager.text = getString(R.string.subcap000)
                }
                //----------------------------------------------------------------------------------
                19 -> { //--------------------------------------------------------------- seccion 01
                    txvtitlepager.text = getString(R.string.secc01)
                    txvtitlepager2.text = getString(R.string.stxt01)
                    txvsubtitlepager.text = getString(R.string.subcap000)
                }
                20 -> { //--------------------------------------------------------------- seccion 02
                    txvtitlepager.text = getString(R.string.secc02)
                    txvtitlepager2.text = getString(R.string.stxt02)
                    txvsubtitlepager.text = getString(R.string.subcap000)
                }
                21 -> { //--------------------------------------------------------------- seccion 03
                    txvtitlepager.text = getString(R.string.secc03)
                    txvtitlepager2.text = getString(R.string.stxt03)
                    txvsubtitlepager.text = getString(R.string.subcap000)
                }
                22 -> { //--------------------------------------------------------------- seccion 04
                    txvtitlepager.text = getString(R.string.secc04)
                    txvtitlepager2.text = getString(R.string.stxt04)
                    txvsubtitlepager.text = getString(R.string.subcap000)
                }
                23 -> { //--------------------------------------------------------------- Informe
                    txvtitlepager.text = getString(R.string.informe)
                    txvtitlepager2.text = getString(R.string.informetxt)
                    txvsubtitlepager.text = getString(R.string.subcap000)
                }
            }
        }
    }

}