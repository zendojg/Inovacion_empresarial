package gob.pa.inovacion_empresarial.view

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.LinearLayout
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
import gob.pa.inovacion_empresarial.adapters.AdapterPagerForm
import gob.pa.inovacion_empresarial.databinding.ActivityFormBinding
import gob.pa.inovacion_empresarial.function.Functions
import gob.pa.inovacion_empresarial.function.Functions.hideKeyboard
import gob.pa.inovacion_empresarial.model.DVModel
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.service.room.RoomView
import gob.pa.inovacion_empresarial.view.fragments.*
import kotlinx.coroutines.launch


class FormActivity : AppCompatActivity() {

    private lateinit var form: ActivityFormBinding
    private var dialog: AlertDialog? = null
    private val dvmForm: DVModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        form = ActivityFormBinding.inflate(layoutInflater)
        setContentView(form.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        val myAdapter = AdapterPagerForm(supportFragmentManager, lifecycle)
        if (myAdapter.itemCount == 0) {
            myAdapter.addListFragment(Mob.arrEncuestas)
            form.viewpager.adapter = myAdapter
            form.viewpager.isUserInputEnabled = false
        }

    }

    override fun onResume() {
        super.onResume()
        onAction()
    }

    override fun onPause() {
        super.onPause()
        if (dialog?.isShowing == true) {
            dialog?.dismiss()
        }
    }

    private fun onAction() {
        with (form) {
            if (Mob.authData?.rol == "E") {
                btsavepager.visibility = View.VISIBLE
                btsavepager.isEnabled = true
            } else {
                btsavepager.visibility = View.INVISIBLE
                btsavepager.isEnabled = false
            }

            if (Mob.indiceFormulario != 0) {
                viewpager.setCurrentItem(Mob.indiceFormulario, false)
                spinPager(Mob.indiceFormulario)
            }
            btnextpager.setOnClickListener {
                if (viewpager.currentItem == Mob.OBSP24) {
                    viewpager.setCurrentItem(0, false)
                } else seeCaps(true)
            }
            btbackpager.setOnClickListener {
                seeCaps(false)
            }
            btmenupager.setOnClickListener {
                viewpager.setCurrentItem(0, false)
            }

            viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    if (position == 0) {
                        toolbarpager.visibility = View.GONE
                        txvtitlepager2.visibility = View.GONE
                        txvsubtitlepager.visibility = View.GONE
                        frameLayoutNav.visibility = View.GONE
                    } else {
                        toolbarpager.visibility = View.VISIBLE
                        txvtitlepager2.visibility = View.VISIBLE
                        txvsubtitlepager.visibility = View.VISIBLE
                        frameLayoutNav.visibility = View.VISIBLE
                    }
                    spinPager(position)
                    //(viewpager.get(0) as RecyclerView).findViewHolderForAdapterPosition(position)
                    //(viewpager[0] as RecyclerView).layoutManager?.findViewByPosition(position)
                }
            })

            btobspager.setOnClickListener { observation(form.viewpager.currentItem) }
            btsavepager.setOnClickListener { seeCaps(null) }
        }
    }

    override fun onBackPressed() {
        if (form.viewpager.currentItem == Mob.MENUP00) {
            val mesagePregunta = AlertDialog.Builder(this)
            val msg: View = layoutInflater.inflate(R.layout.style_msg_alert, null)
            val btpositivo: MaterialButton = msg.findViewById(R.id.btpositivo)
            val btnegativo: MaterialButton = msg.findViewById(R.id.btnegativo)
            val msgT: TextView = msg.findViewById(R.id.msgtitle)
            val msg1: TextView = msg.findViewById(R.id.msg1)
            val msg2: TextView = msg.findViewById(R.id.msg2)

            btpositivo.text = getString(R.string.cancel)
            btnegativo.text = getString(R.string.closeForm)
            msgT.text = getString(R.string.aswernCloseApp)


            if (Mob.authData?.rol == "E") { msg1.text = getString(R.string.asnwernCloseApp2) }
            else { msg1.visibility = View.GONE }
            msg2.visibility = View.GONE
            mesagePregunta.setView(msg)

            val dialog: AlertDialog = mesagePregunta.create()
            dialog.show()
            btpositivo.icon = ContextCompat.getDrawable(this, R.drawable.img_backs)
            btnegativo.icon = ContextCompat.getDrawable(this, R.drawable.img_deleteview)

            btpositivo.setOnClickListener {
                dialog.dismiss()
            }
            btnegativo.setOnClickListener {
                dialog.dismiss()
                startActivity(Intent(this, MainActivity::class.java))
                CreateForm().resetForm()
                Handler(Looper.getMainLooper()).postDelayed({
                    finish()
                }, (Mob.TIME500MS))
            }
        } else form.viewpager.setCurrentItem(
            form.viewpager.currentItem - 1,true)
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
        txtobs.imeOptions = EditorInfo.IME_ACTION_DONE
        txtobs.setRawInputType(InputType.TYPE_CLASS_TEXT)
        if (position < Mob.SEC1P20) {
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
            if (form.viewpager.currentItem < Mob.SEC1P20) { Mob.obsEncuesta = txtobs.text.toString() }
            else { Mob.obsModulo = txtobs.text.toString() }
            dialog.dismiss()
            hideKeyboard()
        }
        btnegativo.setOnClickListener {
            dialog.dismiss()
            hideKeyboard()
        }
    }



    fun seeCaps(move: Boolean?) {
        when (val page = supportFragmentManager.fragments.find { it.isVisible }) {
            is FragEncuestaCap01 ->  page.savedCap()
            is FragEncuestaCap02o1 ->  page.saveCap()
            is FragEncuestaCap02o2 -> page.saveCap()
            is FragEncuestaCap03 -> page.saveCap()
            is FragEncuestaCap04 -> page.saveCap()
            is FragEncuestaCap05o1 -> page.saveCap()
            is FragEncuestaCap05o2 -> page.saveCap()
            is FragEncuestaCap06o1 -> page.saveCap()
            is FragEncuestaCap06o2 -> page.saveCap()
            is FragEncuestaCap06o3 -> page.saveCap()
            is FragEncuestaCap06o4 -> page.saveCap()
            is FragEncuestaCap07o1 -> page.saveCap()
            is FragEncuestaCap07o2 -> page.saveCap()
            is FragEncuestaCap07o3 -> page.saveCap()
            is FragEncuestaCap08 -> page.saveCap()
            is FragEncuestaCap08end -> page.saveCap()
            is FragEncuestaCap09o1 -> page.saveCap()
            is FragEncuestaCap09o2 -> page.saveCap()
            is FragEncuestaCap10 -> page.saveCap()
            is FragModuloSecc01 -> page.saveCap()
            is FragModuloSecc02 -> page.saveCap()
            is FragModuloSecc03 -> page.saveCap()
            is FragModuloSecc04 -> page.saveCap()

        }
        if (move == true) {
            if (form.viewpager.currentItem == Mob.CAP8P15 && Mob.p56stat == false) {
                form.viewpager.setCurrentItem(
                    form.viewpager.currentItem + 2, false)
            } else
                if (form.viewpager.currentItem == Mob.SEC1P20 && Mob.seccON == false) {
                    form.viewpager.setCurrentItem(
                        form.viewpager.currentItem + 4, false)
                } else
                    form.viewpager.setCurrentItem(
                        form.viewpager.currentItem + 1, false)
        } else if (move == false) {
            if (form.viewpager.currentItem == Mob.CAP9P17 && Mob.p56stat == false) {
                form.viewpager.setCurrentItem(
                    form.viewpager.currentItem - 2, false)
            } else
                if (form.viewpager.currentItem == Mob.OBSP24 && Mob.seccON == false) {
                    form.viewpager.setCurrentItem(
                        form.viewpager.currentItem - 4, false)
                } else
                    form.viewpager.setCurrentItem(
                        form.viewpager.currentItem - 1, false)
        } else if (move == null) {
            lifecycleScope.launch {
                val info =
                    RoomView(dvmForm, this@FormActivity).saveForm(CreateForm().createSaved())
                println("----------$info")
            }
            val color = if (form.viewpager.currentItem < Mob.SEC1P20)
                ContextCompat.getColor(this, R.color.holo_blue_dark)
            else ContextCompat.getColor(this, R.color.cream_darl)
            val alert = Functions.msgBallom(
                "Formulario guardado", Mob.WIDTH160DP, this, color
            )
            alert.showAlignBottom(form.txtInfopager)
            alert.dismissWithDelay(Mob.TIMELONG2SEG)
        }
    }


    fun spinPager(position: Int) {
        val colorLetras : Int
        val colorFondo: Int
        val decorView: View = window.decorView
        val ctx = this
        with(form){
            when {
                position == Mob.MENUP00 -> {
                    decorView.systemUiVisibility =
                        decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
                    colorFondo = ContextCompat.getColor(ctx, R.color.celeste)
                    colorLetras = (Color.WHITE)
                }
                position < Mob.SEC1P20 -> {
                    Mob.obsTittle = "Encuesta  de Innovación en Empresas"
                    btobspager.visibility = View.VISIBLE
                    btsavepager.visibility = View.VISIBLE
                    colorLetras = (Color.WHITE)
                    decorView.systemUiVisibility =
                        decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
                    colorFondo = ContextCompat.getColor(ctx, R.color.holo_blue_dark)
                }
                position == Mob.OBSP24 -> {
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
            txvtitlepager.setTextColor(colorLetras)
            txvtitlepager2.setTextColor(colorLetras)

            window.statusBarColor = colorFondo
            constraintpager.setBackgroundColor(colorFondo)
            toolbarpager.setBackgroundColor(colorFondo)
        }
        encuestaTittle(position)
    }

    private fun encuestaTittle(position: Int) {
        with(form){
            when (position) {
                Mob.MENUP00  -> { }
                //----------------
                // --- capitulo 01
                Mob.CAP1P01 -> {
                    txvtitlepager.text = getString(R.string.cap01)
                    txvtitlepager2.text = getString(R.string.ctxt01)
                    txvsubtitlepager.text = getString(R.string.subcap000)
                }
                // --- capitulo 02
                Mob.CAP2P02, Mob.CAP2P03 -> {
                    txvtitlepager.text = getString(R.string.cap02)
                    txvtitlepager2.text = getString(R.string.ctxt02)
                    txvsubtitlepager.text = if(position == Mob.CAP2P02)
                        getString(R.string.subcap021) else  getString(R.string.subcap022)
                }
                // --- capitulo 03
                Mob.CAP3P04 -> {
                    txvtitlepager.text = getString(R.string.cap03)
                    txvtitlepager2.text = getString(R.string.ctxt03)
                    form.txvsubtitlepager.text = getString(R.string.subcap000)
                }
                // --- capitulo 04
                Mob.CAP4P05 -> {
                    txvtitlepager.text = getString(R.string.cap04)
                    txvtitlepager2.text = getString(R.string.ctxt04)
                    txvsubtitlepager.text = getString(R.string.subcap000)
                }
                // --- capitulo 05
                Mob.CAP5P06, Mob.CAP5P07 -> {
                    txvtitlepager.text = getString(R.string.cap05)
                    txvtitlepager2.text = getString(R.string.ctxt05)
                    txvsubtitlepager.text = if (position == Mob.CAP5P06)
                        getString(R.string.subcap051) else getString(R.string.subcap052)
                }
                // ---- capitulo 06
                Mob.CAP6P08, Mob.CAP6P09, Mob.CAP6P10, Mob.CAP6P11 -> {
                    txvtitlepager.text = getString(R.string.cap06)
                    txvtitlepager2.text = getString(R.string.ctxt06)
                    txvsubtitlepager.text = when (position) {
                        Mob.CAP6P08 -> getString(R.string.subcap061)
                        Mob.CAP6P09 -> getString(R.string.subcap062)
                        Mob.CAP6P10 -> getString(R.string.subcap063)
                        else -> getString(R.string.subcap064)
                    }
                }
                // ---- capitulo 07
                Mob.CAP7P12, Mob.CAP7P13, Mob.CAP7P14 -> {
                    txvtitlepager.text = getString(R.string.cap07)
                    txvtitlepager2.text = getString(R.string.ctxt07)
                    txvsubtitlepager.text = when (position) {
                        Mob.CAP7P12 -> getString(R.string.subcap001)
                        Mob.CAP7P13 -> getString(R.string.subcap002)
                        else -> getString(R.string.subcap003)
                    }
                }
                // ---- capitulo 08
                Mob.CAP8P15, Mob.CAP8P16 -> {
                    txvtitlepager.text = getString(R.string.cap08)
                    txvtitlepager2.text = getString(R.string.ctxt08)
                    txvsubtitlepager.text = if (position == Mob.CAP8P15)
                        getString(R.string.subcap001) else getString(R.string.subcap002)
                }
                // ---- capitulo 09
                Mob.CAP9P17, Mob.CAP9P18 -> {
                    txvtitlepager.text = getString(R.string.cap09)
                    txvtitlepager2.text = getString(R.string.ctxt09)
                    txvsubtitlepager.text = if(position == Mob.CAP9P17)
                        getString(R.string.subcap001) else getString(R.string.subcap002)
                }
                // ---- capitulo 10
                Mob.CAPXP19 -> {
                    txvtitlepager.text = getString(R.string.cap10)
                    txvtitlepager2.text = getString(R.string.ctxt10)
                    txvsubtitlepager.text = getString(R.string.subcap000)
                }
                //---------------------
                // ---- seccion 01 ----
                Mob.SEC1P20 -> {
                    txvtitlepager.text = getString(R.string.secc01)
                    txvtitlepager2.text = getString(R.string.stxt01)
                    txvsubtitlepager.text = getString(R.string.subcap000)
                }
                // ---- seccion 02
                Mob.SEC2P21 -> {
                    txvtitlepager.text = getString(R.string.secc02)
                    txvtitlepager2.text = getString(R.string.stxt02)
                    txvsubtitlepager.text = getString(R.string.subcap000)
                }
                // ---- seccion 03
                Mob.SEC3P22 -> {
                    txvtitlepager.text = getString(R.string.secc03)
                    txvtitlepager2.text = getString(R.string.stxt03)
                    txvsubtitlepager.text = getString(R.string.subcap000)
                }
                // ---- seccion 04
                Mob.SEC4P23 -> {
                    txvtitlepager.text = getString(R.string.secc04)
                    txvtitlepager2.text = getString(R.string.stxt04)
                    txvsubtitlepager.text = getString(R.string.subcap000)
                }
                // ---- Informe
                Mob.OBSP24 -> {
                    txvtitlepager.text = getString(R.string.informe)
                    txvtitlepager2.text = getString(R.string.informetxt)
                    var ncontrol: String = getString(R.string.ncontrol)
                    if (Mob.formComp?.ncontrol != null)
                        ncontrol += Mob.formComp?.ncontrol
                    txvsubtitlepager.text = ncontrol
                }
            }
        }
    }
}