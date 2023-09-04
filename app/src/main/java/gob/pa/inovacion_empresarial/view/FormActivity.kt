package gob.pa.inovacion_empresarial.view

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.text.InputType
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.navigation.NavigationView
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.adapters.AdapterPagerForm
import gob.pa.inovacion_empresarial.databinding.ActivityFormBinding
import gob.pa.inovacion_empresarial.databinding.StyleMsgAlertBinding
import gob.pa.inovacion_empresarial.databinding.StyleMsgObsBinding
import gob.pa.inovacion_empresarial.function.CreateForm
import gob.pa.inovacion_empresarial.function.Functions
import gob.pa.inovacion_empresarial.function.Functions.hideKeyboard
import gob.pa.inovacion_empresarial.function.Functions.toEditable
import gob.pa.inovacion_empresarial.model.DVModel
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.service.room.RoomView
import gob.pa.inovacion_empresarial.view.fragments.*
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import kotlin.collections.ArrayList


class FormActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var form: ActivityFormBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    private var dialog: AlertDialog? = null
    private val dvmForm: DVModel by viewModels()
    private val ctx: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        form = ActivityFormBinding.inflate(layoutInflater)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        val myAdapter = AdapterPagerForm(supportFragmentManager, lifecycle)
        if (myAdapter.itemCount == 0) {
            myAdapter.addListFragment(Mob.arrEncuestas)
            form.viewpager.adapter = myAdapter
            form.viewpager.isUserInputEnabled = false
        }
        setContentView(form.root)
    }


    override fun onResume() {
        super.onResume()
        drawerLayout  = form.constraintpager
        toggle = ActionBarDrawerToggle(this, drawerLayout, form.toolbarpager,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        form.navView.setNavigationItemSelectedListener(this)
        onAction()
    }

    override fun onPause() {
        super.onPause()
        if (dialog?.isShowing == true)  dialog?.dismiss()
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
            btbackpager.setOnClickListener { seeCaps(false) }
            btmenupager.setOnClickListener { viewpager.setCurrentItem(0, false) }

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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawer(GravityCompat.END)
        } else
        if (form.viewpager.currentItem == Mob.MENUP00) {
            val mesagePregunta = AlertDialog.Builder(this)
            val bindmsg: StyleMsgAlertBinding = StyleMsgAlertBinding.inflate(layoutInflater)
            val ctx = this
            with (bindmsg) {
                btpositivo.text = getString(R.string.cancel)
                btnegativo.text = getString(R.string.closeForm)
                msgtitle.text = getString(R.string.aswernCloseApp)

                if (Mob.authData?.rol == "E") msg1.text = getString(R.string.asnwernCloseApp2)
                else msg1.visibility = View.GONE
                msg2.visibility = View.GONE
                btpositivo.icon = ContextCompat.getDrawable(ctx, R.drawable.img_backs)
                btnegativo.icon = ContextCompat.getDrawable(ctx, R.drawable.img_deleteview)
                btnegativo.backgroundTintList =
                    ContextCompat.getColorStateList(ctx, R.color.dark_pink)

                mesagePregunta.setView(bindmsg.root)
                dialog = mesagePregunta.create()
                dialog?.show()

                btpositivo.setOnClickListener {
                    dialog?.dismiss()
                }
                btnegativo.setOnClickListener {
                    dialog?.dismiss()
                    startActivity(Intent(ctx, MainActivity::class.java))
                    CreateForm.resetLoad()
                    Handler(Looper.getMainLooper()).postDelayed({
                        finish()
                    }, (Mob.TIME500MS))
                }
            }
        } else seeCaps(false)
    }

    private fun observation(position: Int) {
        val mesagePregunta = AlertDialog.Builder(this)
        val bindmsg: StyleMsgObsBinding = StyleMsgObsBinding.inflate(layoutInflater)
        mesagePregunta.setView(bindmsg.root)
        val color: Int
        with(bindmsg) {
            if (Mob.authData?.rol != "E") {
                btsaveobs.isEnabled = false
                txtobs.isFocusable = false
            }
            txvtittleobs.text = Mob.obsTittle
            txtobs.imeOptions = EditorInfo.IME_ACTION_DONE
            txtobs.setRawInputType(InputType.TYPE_CLASS_TEXT)
            if (position < Mob.SEC1P20) {
                color = ContextCompat.getColor(ctx, R.color.holo_blue_dark)
                txtobs.setText(Mob.obsEncuesta ?: "")
                linearObs.setBackgroundResource(R.drawable.background_shadow_celpast)
            } else {
                color = ContextCompat.getColor(ctx, R.color.cream_pastel)
                txtobs.setText(Mob.obsModulo ?: "")
                linearObs.setBackgroundResource(R.drawable.background_shadow_pastelowl)
            }
            btsaveobs.backgroundTintList = ColorStateList.valueOf(color)
            btexitobs.backgroundTintList = ColorStateList.valueOf(color)
            layoutObs.background = if (form.viewpager.currentItem < Mob.SEC1P20)
                ContextCompat.getDrawable(ctx, R.drawable.background_border_blue) else
                ContextCompat.getDrawable(ctx, R.drawable.background_border_cream)

            dialog = mesagePregunta.create()
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog?.window?.attributes?.windowAnimations =
                R.style.Animation_Design_BottomSheetDialog
            dialog?.window?.setGravity(Gravity.BOTTOM)
            dialog?.setCancelable(false)
            dialog?.show()


            btsaveobs.setOnClickListener {
                if (form.viewpager.currentItem < Mob.SEC1P20) {
                    Mob.obsEncuesta = txtobs.text.toString()
                } else {
                    Mob.obsModulo = txtobs.text.toString()
                }
                dialog?.dismiss()
                hideKeyboard()
            }
            btexitobs.setOnClickListener {
                dialog?.dismiss()
                hideKeyboard()
            }
        }
    }

    fun seeCaps(move: Boolean?) {
        hideKeyboard()
        val colorlb = if (form.viewpager.currentItem < Mob.SEC1P20) R.color.holo_blue_dark
        else  R.color.cream_darl
        var listFaltantes: List<String> = ArrayList()
        when (val page = supportFragmentManager.fragments.find { it.isVisible }) {
            is FragEncuestaCap01 -> listFaltantes = page.savedCap()
            is FragEncuestaCap02o1 -> listFaltantes = page.saveCap()
            is FragEncuestaCap02o2 -> listFaltantes = page.saveCap()
            is FragEncuestaCap03 -> listFaltantes = page.saveCap()
            is FragEncuestaCap04 -> listFaltantes = page.saveCap()
            is FragEncuestaCap05o1 -> listFaltantes = page.saveCap()
            is FragEncuestaCap05o2 -> listFaltantes = page.saveCap()
            is FragEncuestaCap06o1 -> listFaltantes = page.saveCap()
            is FragEncuestaCap06o2 -> listFaltantes = page.saveCap()
            is FragEncuestaCap06o3 -> listFaltantes = page.saveCap()
            is FragEncuestaCap06o4 -> listFaltantes = page.saveCap()
            is FragEncuestaCap07o1 -> listFaltantes = page.saveCap()
            is FragEncuestaCap07o2 -> listFaltantes = page.saveCap()
            is FragEncuestaCap07o3 -> listFaltantes = page.saveCap()
            is FragEncuestaCap08 -> listFaltantes = page.saveCap()
            is FragEncuestaCap08end -> listFaltantes = page.saveCap()
            is FragEncuestaCap09o1 -> listFaltantes = page.saveCap()
            is FragEncuestaCap09o2 -> listFaltantes = page.saveCap()
            is FragEncuestaCap10 -> listFaltantes = page.saveCap()
            is FragModuloSecc01 -> listFaltantes = page.saveCap()
            is FragModuloSecc02 -> listFaltantes = page.saveCap()
            is FragModuloSecc03 -> listFaltantes = page.saveCap()
            is FragModuloSecc04 -> listFaltantes = page.saveCap()
            is FragTotalInforme -> listFaltantes = page.saveCap()
        }

        if (move != null && Mob.authData?.rol != "E") { moveTo(move)//--- No muestra inconsistencias
        } else if (move != null && listFaltantes.isEmpty()) { moveTo(move) //-- mover normal
        } else if (move == null) { //-- Guardar
            lifecycleScope.launch {
                val info = RoomView(dvmForm, ctx).saveForm(CreateForm.createSaved())
                println("----------$info")
            }
            val colorBallom = ContextCompat.getColor(this, colorlb)
            val alert = Functions.msgBallom(
                "Formulario guardado", Mob.WIDTH160DP, this, colorBallom)
            alert.showAlignBottom(form.txtInfopager)
            alert.dismissWithDelay(Mob.TIMELONG2SEG)
        } else  { //-- Inconsistencia
            val mesagePregunta = AlertDialog.Builder(this)
            val bindmsg: StyleMsgAlertBinding = StyleMsgAlertBinding.inflate(layoutInflater)
            with (bindmsg) {
                btpositivo.text = getString(R.string.cancel)
                msgtitle.text = getString(R.string.tittleWarningIncon)
                msg1.text = getString(R.string.msgWarningIncon)
                btnegativo.backgroundTintList = ContextCompat.getColorStateList(ctx, colorlb)
                msg2.visibility = View.GONE
                msg6.isVisible = true
                msg6.text = listFaltantes.toTypedArray().joinToString("\n\n").toEditable()

                mesagePregunta.setView(bindmsg.root)
                dialog = mesagePregunta.create()
                dialog?.show()
                btpositivo.icon = ContextCompat.getDrawable(ctx, R.drawable.img_close)
                if (move) {
                    btnegativo.icon = ContextCompat.getDrawable(ctx, R.drawable.img_forward)
                    btnegativo.text = getString(R.string.Go)
                }
                else {
                    btnegativo.icon = ContextCompat.getDrawable(ctx, R.drawable.img_backs)
                    btnegativo.text = getString(R.string.Back)
                }
                btpositivo.setOnClickListener { dialog?.dismiss() }
                btnegativo.setOnClickListener {
                    dialog?.dismiss()
                    moveTo(move)
                }
            }
        }
    }

    private fun moveTo(move: Boolean) {
        if (move) {
            if (form.viewpager.currentItem == Mob.CAP8P15 &&
                Mob.cap8?.v56check == false) {
                form.viewpager.setCurrentItem(
                    form.viewpager.currentItem + 2, false)
            } else
                if (form.viewpager.currentItem == Mob.SEC1P20 &&
                    Mob.capMod?.v1check == false) {
                    form.viewpager.setCurrentItem(
                        form.viewpager.currentItem + Mob.JUMPMODULE1, false)
                } else
                    form.viewpager.setCurrentItem(
                        form.viewpager.currentItem + 1, false)
        } else {
            if (form.viewpager.currentItem == Mob.CAP9P17 &&
                Mob.cap8?.v56check == false) {
                form.viewpager.setCurrentItem(
                    form.viewpager.currentItem - 2, false)
            } else
                if (form.viewpager.currentItem == Mob.OBSP24 &&
                    Mob.capMod?.v1check == false) {
                    form.viewpager.setCurrentItem(
                        form.viewpager.currentItem - Mob.JUMPMODULE1, false)
                } else
                    form.viewpager.setCurrentItem(
                        form.viewpager.currentItem - 1, false)
        }
    }


    fun spinPager(position: Int) {
        val colorLetras : Int
        val colorFondo: Int
        val decorView: View = window.decorView
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
                    colorLetras = (Color.WHITE)
                    decorView.systemUiVisibility =
                        decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
                    colorFondo = ContextCompat.getColor(ctx, R.color.holo_blue_dark)
                }
                position == Mob.OBSP24 -> {
                    btobspager.visibility = View.INVISIBLE
                    colorLetras = (Color.WHITE)
                    decorView.systemUiVisibility =
                        decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
                    colorFondo = ContextCompat.getColor(ctx, R.color.teal_700)
                }
                else -> {
                    Mob.obsTittle = "Módulo de Comercio Electrónico"
                    btobspager.visibility = View.VISIBLE
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