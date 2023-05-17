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
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.adapters.AdapterPager
import gob.pa.inovacion_empresarial.databinding.ActivityViewPagerBinding
import gob.pa.inovacion_empresarial.function.Functions.Companion.hideKeyboard
import gob.pa.inovacion_empresarial.model.Mobject
import kotlinx.android.synthetic.main.activity_view_pager.*


class ViewPager : AppCompatActivity() {

    private lateinit var pagerbinding: ActivityViewPagerBinding
    private val encuesta = Mobject.indiceEnc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pagerbinding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(pagerbinding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        //------------------------------------------------------------------------------------------ Viewpager SLIP event
        val myAdapter = AdapterPager(supportFragmentManager, lifecycle)
        myAdapter.addListFragment(Mobject.arrEncuestas)
        pagerbinding.viewpager.adapter = myAdapter
        pagerbinding.viewpager.isUserInputEnabled = false

    }

    override fun onResume() {
        super.onResume()
        actions()
    }

    private fun actions() {
        if (encuesta!= 0) {
            pagerbinding.viewpager.setCurrentItem(encuesta, false)
            spinPager(encuesta)
        }
        pagerbinding.btnextpager.setOnClickListener {
            if (pagerbinding.viewpager.currentItem==22) {
                onBackPressedDispatcher.onBackPressed()
            } else pagerbinding.viewpager.setCurrentItem(
                pagerbinding.viewpager.currentItem + 1,false)
        }
        pagerbinding.btbackpager.setOnClickListener {
            if (viewpager.currentItem==0) {
                onBackPressedDispatcher.onBackPressed()
            } else viewpager.setCurrentItem(viewpager.currentItem - 1,false) }
        pagerbinding.btmenupager.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                spinPager(position)
                //(viewpager.get(0) as RecyclerView).findViewHolderForAdapterPosition(position)
                //(viewpager[0] as RecyclerView).layoutManager?.findViewByPosition(position)
            }
        })


        var tittleheight = 0 //--------------------------------------------------------------------- Buttons RESIZE
        pagerbinding.consttitlepager2.setOnClickListener { resizeTittle() }
        pagerbinding.bttoolresizepager.setOnClickListener { resizeTittle() }
        pagerbinding.linearsubtittlepager.setOnClickListener { resizeSubtittle() }
        pagerbinding.btsubtresizepager.setOnClickListener { resizeSubtittle() }
        //------------------------------------------------------------------------------------------

        pagerbinding.btobspager.setOnClickListener {
            observation(viewpager.currentItem)
        }
        pagerbinding.btsavepager.setOnClickListener {
            Toast.makeText(applicationContext, "Formulario guardado", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onBackPressed() {
        if (viewpager.currentItem == 0) { onBackPressedDispatcher.onBackPressed() }
        else { viewpager.setCurrentItem(viewpager.currentItem - 1,true)  }
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

        lbobs.text = Mobject.obsTittle
        txtobs.imeOptions = EditorInfo.IME_ACTION_DONE;
        txtobs.setRawInputType(InputType.TYPE_CLASS_TEXT);

        if (position<18) {
            color = ContextCompat.getColor(this, R.color.holo_blue_dark)
            txtobs.text = Mobject.obsEncuesta
            encabezado.setBackgroundResource(R.drawable.background_shadow_celpast)

        } else {
            color = ContextCompat.getColor(this, R.color.cream_pastel)
            txtobs.text = Mobject.obsModulo
            encabezado.setBackgroundResource(R.drawable.background_shadow_pastel)
        }
        btpositivo.backgroundTintList = ColorStateList.valueOf(color)
        btnegativo.backgroundTintList = ColorStateList.valueOf(color)


        mesagePregunta.setView(msg)
        val dialog: AlertDialog = mesagePregunta.create()
        dialog.show()
        dialog.window?.setGravity(Gravity.CENTER)


        btpositivo.setOnClickListener {
            if (viewpager.currentItem<18) { Mobject.obsEncuesta = txtobs.text.toString() }
            else { Mobject.obsModulo = txtobs.text.toString() }
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
                position < 18 -> {
                    Mobject.obsTittle = "Encuesta  de Innovación en Empresas"
                    btobspager.visibility = View.VISIBLE
                    btsavepager.visibility = View.VISIBLE
                    colorLetras = (Color.WHITE)
                    decorView.systemUiVisibility =
                        decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
                    colorFondo = ContextCompat.getColor(ctx, R.color.holo_blue_dark)
                }
                position == 22 -> {
                    btobspager.visibility = View.INVISIBLE
                    btsavepager.visibility = View.INVISIBLE
                    colorLetras = (Color.WHITE)
                    decorView.systemUiVisibility =
                        decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
                    colorFondo = ContextCompat.getColor(ctx, R.color.teal_700)
                }
                else -> {
                    Mobject.obsTittle = "Módulo de Comercio Electrónico"
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
                0 -> { //--------------------------------------------------------------- capitulo 01
                    txvtitlepager.text = getString(R.string.cap01)
                    txvtitlepager2.text = getString(R.string.ctxt01)
                    txvsubtitlepager.text = getString(R.string.subcap000)
                }
                1 -> { //--------------------------------------------------------------- capitulo 02
                    txvtitlepager.text = getString(R.string.cap02)
                    txvtitlepager2.text = getString(R.string.ctxt02)
                    txvsubtitlepager.text = getString(R.string.subcap021)
                }
                2 -> { //--------------------------------------------------------------- capitulo 02
                    txvtitlepager.text = getString(R.string.cap02)
                    txvtitlepager2.text = getString(R.string.ctxt02)
                    txvsubtitlepager.text = getString(R.string.subcap022)
                }
                3 -> { //--------------------------------------------------------------- capitulo 03
                    txvtitlepager.text = getString(R.string.cap03)
                    txvtitlepager2.text = getString(R.string.ctxt03)
                    pagerbinding.txvsubtitlepager.text = getString(R.string.subcap000)
                }
                4 -> { //--------------------------------------------------------------- capitulo 04
                    txvtitlepager.text = getString(R.string.cap04)
                    txvtitlepager2.text = getString(R.string.ctxt04)
                    txvsubtitlepager.text = getString(R.string.subcap000)
                }
                5 -> { //--------------------------------------------------------------- capitulo 05
                    txvtitlepager.text = getString(R.string.cap05)
                    txvtitlepager2.text = getString(R.string.ctxt05)
                    txvsubtitlepager.text = getString(R.string.subcap051)
                }
                6 -> { //--------------------------------------------------------------- capitulo 05
                    txvtitlepager.text = getString(R.string.cap05)
                    txvtitlepager2.text = getString(R.string.ctxt05)
                    txvsubtitlepager.text = getString(R.string.subcap052)
                }
                7 -> { //--------------------------------------------------------------- capitulo 06
                    txvtitlepager.text = getString(R.string.cap06)
                    txvtitlepager2.text = getString(R.string.ctxt06)
                    txvsubtitlepager.text = getString(R.string.subcap061)
                }
                8 -> { //--------------------------------------------------------------- capitulo 06
                    txvtitlepager.text = getString(R.string.cap06)
                    txvtitlepager2.text = getString(R.string.ctxt06)
                    txvsubtitlepager.text = getString(R.string.subcap062)
                }
                9 -> { //--------------------------------------------------------------- capitulo 06
                    txvtitlepager.text = getString(R.string.cap06)
                    txvtitlepager2.text = getString(R.string.ctxt06)
                    txvsubtitlepager.text = getString(R.string.subcap063)
                }
                10 -> { //-------------------------------------------------------------- capitulo 06
                    txvtitlepager.text = getString(R.string.cap06)
                    txvtitlepager2.text = getString(R.string.ctxt06)
                    txvsubtitlepager.text = getString(R.string.subcap064)
                }
                11 -> { //-------------------------------------------------------------- capitulo 07
                    txvtitlepager.text = getString(R.string.cap07)
                    txvtitlepager2.text = getString(R.string.ctxt07)
                    txvsubtitlepager.text = getString(R.string.subcap001)
                }
                12 -> { //-------------------------------------------------------------- capitulo 07
                    txvtitlepager.text = getString(R.string.cap07)
                    txvtitlepager2.text = getString(R.string.ctxt07)
                    txvsubtitlepager.text = getString(R.string.subcap002)
                }
                13 -> { //-------------------------------------------------------------- capitulo 07
                    txvtitlepager.text = getString(R.string.cap07)
                    txvtitlepager2.text = getString(R.string.ctxt07)
                    txvsubtitlepager.text = getString(R.string.subcap003)
                }
                14 -> { //-------------------------------------------------------------- capitulo 08
                    txvtitlepager.text = getString(R.string.cap08)
                    txvtitlepager2.text = getString(R.string.ctxt08)
                    txvsubtitlepager.text = getString(R.string.subcap000)
                }
                15 -> { //-------------------------------------------------------------- capitulo 09
                    txvtitlepager.text = getString(R.string.cap09)
                    txvtitlepager2.text = getString(R.string.ctxt09)
                    txvsubtitlepager.text = getString(R.string.subcap001)
                }
                16 -> { //-------------------------------------------------------------- capitulo 09
                    txvtitlepager.text = getString(R.string.cap09)
                    txvtitlepager2.text = getString(R.string.ctxt09)
                    txvsubtitlepager.text = getString(R.string.subcap002)
                }
                17 -> { //-------------------------------------------------------------- capitulo 10
                    txvtitlepager.text = getString(R.string.cap10)
                    txvtitlepager2.text = getString(R.string.ctxt10)
                    txvsubtitlepager.text = getString(R.string.subcap000)
                }
                //----------------------------------------------------------------------------------
                18 -> { //--------------------------------------------------------------- seccion 01
                    txvtitlepager.text = getString(R.string.secc01)
                    txvtitlepager2.text = getString(R.string.stxt01)
                    txvsubtitlepager.text = getString(R.string.subcap000)
                }
                19 -> { //--------------------------------------------------------------- seccion 02
                    txvtitlepager.text = getString(R.string.secc02)
                    txvtitlepager2.text = getString(R.string.stxt02)
                    txvsubtitlepager.text = getString(R.string.subcap000)
                }
                20 -> { //--------------------------------------------------------------- seccion 03
                    txvtitlepager.text = getString(R.string.secc03)
                    txvtitlepager2.text = getString(R.string.stxt03)
                    txvsubtitlepager.text = getString(R.string.subcap000)
                }
                21 -> { //--------------------------------------------------------------- seccion 04
                    txvtitlepager.text = getString(R.string.secc04)
                    txvtitlepager2.text = getString(R.string.stxt04)
                    txvsubtitlepager.text = getString(R.string.subcap000)
                }
                22 -> { //--------------------------------------------------------------- Informe
                    txvtitlepager.text = getString(R.string.informe)
                    txvtitlepager2.text = getString(R.string.informetxt)
                    txvsubtitlepager.text = getString(R.string.subcap000)
                }
            }
        }
    }

}