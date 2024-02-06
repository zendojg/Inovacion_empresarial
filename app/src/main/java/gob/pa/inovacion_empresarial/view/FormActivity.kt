package gob.pa.inovacion_empresarial.view

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
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
import gob.pa.inovacion_empresarial.databinding.MenuHeaderBinding
import gob.pa.inovacion_empresarial.databinding.StyleMsgAlertBinding
import gob.pa.inovacion_empresarial.databinding.StyleMsgObsBinding
import gob.pa.inovacion_empresarial.function.CreateForm
import gob.pa.inovacion_empresarial.function.Functions
import gob.pa.inovacion_empresarial.function.Functions.hideKeyboard
import gob.pa.inovacion_empresarial.function.Functions.toEditable
import gob.pa.inovacion_empresarial.model.DVModel
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.view.fragments.FragEncuestaCap01
import gob.pa.inovacion_empresarial.view.fragments.FragEncuestaCap02o1
import gob.pa.inovacion_empresarial.view.fragments.FragEncuestaCap02o2
import gob.pa.inovacion_empresarial.view.fragments.FragEncuestaCap03
import gob.pa.inovacion_empresarial.view.fragments.FragEncuestaCap04
import gob.pa.inovacion_empresarial.view.fragments.FragEncuestaCap05o1
import gob.pa.inovacion_empresarial.view.fragments.FragEncuestaCap05o2
import gob.pa.inovacion_empresarial.view.fragments.FragEncuestaCap06o1
import gob.pa.inovacion_empresarial.view.fragments.FragEncuestaCap06o2
import gob.pa.inovacion_empresarial.view.fragments.FragEncuestaCap06o3
import gob.pa.inovacion_empresarial.view.fragments.FragEncuestaCap06o4
import gob.pa.inovacion_empresarial.view.fragments.FragEncuestaCap07o1
import gob.pa.inovacion_empresarial.view.fragments.FragEncuestaCap07o2
import gob.pa.inovacion_empresarial.view.fragments.FragEncuestaCap07o3
import gob.pa.inovacion_empresarial.view.fragments.FragEncuestaCap08
import gob.pa.inovacion_empresarial.view.fragments.FragEncuestaCap08end
import gob.pa.inovacion_empresarial.view.fragments.FragEncuestaCap09o1
import gob.pa.inovacion_empresarial.view.fragments.FragEncuestaCap09o2
import gob.pa.inovacion_empresarial.view.fragments.FragEncuestaCap10
import gob.pa.inovacion_empresarial.view.fragments.FragModuloSecc01
import gob.pa.inovacion_empresarial.view.fragments.FragModuloSecc02
import gob.pa.inovacion_empresarial.view.fragments.FragModuloSecc03
import gob.pa.inovacion_empresarial.view.fragments.FragModuloSecc04
import gob.pa.inovacion_empresarial.view.fragments.FragTotalInforme
import kotlinx.coroutines.launch

class FormActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var form: ActivityFormBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    private var dialog: AlertDialog? = null
    private val dvmForm: DVModel by viewModels()
    private val ctx: Context = this
    private var pagerIndex: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        form = ActivityFormBinding.inflate(layoutInflater)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        val myAdapter = AdapterPagerForm(supportFragmentManager, lifecycle)
        if (myAdapter.itemCount == 0) {
            myAdapter.addListFragment(Mob.arrEncuestas)
            form.viewpager.adapter = myAdapter
            form.viewpager.isUserInputEnabled = false
            Mob.infoCap.find { it.indexCap == Mob.MENU_P00 }?.capView = true
        }
        setContentView(form.root)
    }

    override fun onResume() {
        super.onResume()
        drawerLayout  = form.constraintpager
        setSupportActionBar(form.toolbarpager)
        toggle = ActionBarDrawerToggle(this, drawerLayout,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)
        form.navView.setNavigationItemSelectedListener(this)

        val ncontrol = "N° de control: ${Functions.ceroLeft(
            Mob.formComp?.ncontrol ?: "0", Mob.FOR_5_DIGITS)}"
        val headerNav = form.navView.getHeaderView(0)
        val headerbinding = MenuHeaderBinding.inflate(layoutInflater)
        headerNav.findViewById<TextView>(headerbinding.lbname.id)?.text = ncontrol

        onAction()
    }

    override fun onPause() {
        super.onPause()
        if (dialog?.isShowing == true)  dialog?.dismiss()
    }

    private fun onAction() {
        form.apply {
            btDrawerpager.setOnClickListener {
                if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
                    drawerLayout.closeDrawer(GravityCompat.END, true)
                } else  drawerLayout.openDrawer(GravityCompat.END, true)
            }
            if (Mob.authData?.rol == "E")  btsavepager.visibility = View.VISIBLE
             else btsavepager.visibility = View.INVISIBLE

            if (Mob.indiceFormulario != Mob.MENU_P00) {
                viewpager.setCurrentItem(Mob.indiceFormulario, false)
                spinPager(Mob.indiceFormulario)
            }
            btnextpager.setOnClickListener {
                if (viewpager.currentItem == Mob.OBSE_P24) {
                    viewpager.setCurrentItem(Mob.MENU_P00, false)
                } else {
                    if (dialog?.isShowing != true) seeCaps(true)
                }
            }
            btbackpager.setOnClickListener { if (dialog?.isShowing != true) seeCaps(false) }
            btinconpager.setOnClickListener { /* Para ver inconsistencias, el bt esta invisible */}

            viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    val shouldShowViews = (position != Mob.MENU_P00)
                    setVisibility(toolbarpager, shouldShowViews)
                    setVisibility(txvtitlepager2, shouldShowViews)
                    setVisibility(txvsubtitlepager, shouldShowViews)
                    setVisibility(frameLayoutNav, shouldShowViews)
                    spinPager(position)
                }
                private fun setVisibility(view: View, isVisible: Boolean) {
                    view.visibility = if (isVisible) View.VISIBLE else View.GONE
                }
            })
            btobspager.setOnClickListener {
                if (form.viewpager.currentItem != Mob.OBSE_P24 ||
                    form.viewpager.currentItem != Mob.MENU_P00)
                    if (dialog?.isShowing != true) observation(form.viewpager.currentItem)
            }
            btsavepager.setOnClickListener { seeCaps(null) }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        pageSave()
        pagerIndex = Mob.menuToIndexMap[item.itemId] ?: Mob.MENU_P00
        drawerLayout.closeDrawer(GravityCompat.END, true)
        Mob.indiceFormulario = pagerIndex ?: Mob.MENU_P00
        if (pagerIndex == Mob.MENU_P00) onBackPressed()
        else form.viewpager.setCurrentItem(pagerIndex ?: Mob.MENU_P00, false)
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (form.viewpager.currentItem == Mob.MENU_P00 || pagerIndex == Mob.MENU_P00) {
            val mesagePregunta = AlertDialog.Builder(this)
            val bindmsg: StyleMsgAlertBinding = StyleMsgAlertBinding.inflate(layoutInflater)
            val ctx = this
            pagerIndex = null
            with (bindmsg) {
                btpositivo.text = getString(R.string.cancel)
                btnegativo.text = getString(R.string.closeForm)
                msgtitle.text = getString(R.string.aswernCloseApp)
                if (Mob.authData?.rol == "E") msg1.text = getString(R.string.asnwernCloseApp2)
                else msg1.visibility = View.GONE
                msg2.visibility = View.GONE
                btpositivo.icon = ContextCompat.getDrawable(ctx, R.drawable.img_backs)
                btnegativo.icon = ContextCompat.getDrawable(ctx, R.drawable.img_close)
                btnegativo.backgroundTintList =
                    ContextCompat.getColorStateList(ctx, R.color.dark_pink)

                mesagePregunta.setView(bindmsg.root)
                dialog = mesagePregunta.create()
                dialog?.show()

                btpositivo.setOnClickListener { dialog?.dismiss() }
                btnegativo.setOnClickListener {
                    dialog?.dismiss()
                    val intent = Intent(ctx, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or
                            Intent.FLAG_ACTIVITY_CLEAR_TASK  or
                            Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    CreateForm.resetLoad()
                    Handler(Looper.getMainLooper()).postDelayed({
                        finish()
                    }, (Mob.TIME500MS))
                }
            }
        } else if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawer(GravityCompat.END, true)
        } else seeCaps(false)
    }

    private fun observation(position: Int) {
        val mesagePregunta = AlertDialog.Builder(this)

        val bindmsg: StyleMsgObsBinding = StyleMsgObsBinding.inflate(layoutInflater)
        val color: Int
        mesagePregunta.setView(bindmsg.root)
        with(bindmsg) {
            if (Mob.authData?.rol != "E") {
                btsaveobs.isEnabled = false
                txtobs.isEnabled = false
            }

            txvtittleobs.text =
                if (form.viewpager.currentItem < Mob.SEC1_P20) "Encuesta de Innovación en Empresas"
                else "Módulo de Comercio Electrónico"

            txtobs.imeOptions = EditorInfo.IME_ACTION_DONE
            txtobs.setRawInputType(InputType.TYPE_CLASS_TEXT)
            if (position < Mob.SEC1_P20) {
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

            dialog = mesagePregunta.create()
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog?.window?.attributes?.windowAnimations =
                R.style.Animation_Design_BottomSheetDialog
            dialog?.window?.setGravity(Gravity.BOTTOM)
            dialog?.setCancelable(false)
            dialog?.show()

            btsaveobs.setOnClickListener {
                if (form.viewpager.currentItem < Mob.SEC1_P20)
                    Mob.obsEncuesta = txtobs.text.toString()
                 else Mob.obsModulo = txtobs.text.toString()
                dialog?.dismiss()
                hideKeyboard()
            }
            btexitobs.setOnClickListener {
                dialog?.dismiss()
                hideKeyboard()
            }
        }
    }

    private fun pageSave(): List<String> {
        //when (val page = supportFragmentManager.fragments.find { it.isVisible }) { }
        return when (val page = Mob.arrEncuestas.find { it.isVisible }) {
            is FragEncuestaCap01 -> page.savedCap()
            is FragEncuestaCap02o1 -> page.saveCap()
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
            is FragTotalInforme -> page.saveCapInforme()
            else -> ArrayList()
        }
    }

    private fun seeCaps(move: Boolean?) {
        hideKeyboard()
        val colorlb = if (form.viewpager.currentItem < Mob.SEC1_P20) R.color.holo_blue_dark
        else if (form.viewpager.currentItem == Mob.OBSE_P24) R.color.teal_700
        else  R.color.cream_darl
        val listFaltantes: List<String> = pageSave()
        if (move != null && Mob.authData?.rol != "E")  moveTo(move) //--- No muestra inconsistencias
        else if (move != null && listFaltantes.isEmpty())  moveTo(move) //-- mover por el form
        else if (move == null) { //-- Guardar
            lifecycleScope.launch {
                val colorBallom = ContextCompat.getColor(ctx, colorlb)
                val message = if (CreateForm.createSaved(dvmForm, ctx) > 0)  "Formulario guardado"
                else  "Error al guardar"

                val alert = Functions.msgBallom(message, Mob.WIDTH160DP, ctx, colorBallom)
                alert.showAlignBottom(form.txtInfopager)
                alert.dismissWithDelay(Mob.TIMELONG2SEG)
            }
        } else { //-- Inconsistencia
            val mesagePregunta = AlertDialog.Builder(this)
            val bindmsg: StyleMsgAlertBinding = StyleMsgAlertBinding.inflate(layoutInflater)
            bindmsg.apply {
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
        form.apply {
            btobspager.isEnabled = false
            btsavepager.isEnabled = false
            if (move) {
                if (viewpager.currentItem == Mob.CAP8_P15 && Mob.cap8?.v56check == false) {
                    viewpager.setCurrentItem(viewpager.currentItem + 2, false)
                } else
                    if (viewpager.currentItem == Mob.SEC1_P20 && Mob.capMod?.v1check == false) {
                        viewpager.setCurrentItem(
                            viewpager.currentItem + Mob.JUMP_MODULE1, false
                        )
                    } else viewpager.setCurrentItem(
                        viewpager.currentItem + 1, false
                    )
            } else {
                if (viewpager.currentItem == Mob.CAP9_P17 && Mob.cap8?.v56check == false) {
                    viewpager.setCurrentItem(viewpager.currentItem - 2, false)
                } else if (viewpager.currentItem == Mob.OBSE_P24 && Mob.capMod?.v1check == false) {
                    viewpager.setCurrentItem(
                        viewpager.currentItem - Mob.JUMP_MODULE1, false
                    )
                } else viewpager.setCurrentItem(
                    viewpager.currentItem - 1, false
                )
            }
            Handler(Looper.getMainLooper()).postDelayed({
                btobspager.isEnabled = true
                btsavepager.isEnabled = true
            }, (Mob.TIME500MS))
        }
    }

    fun spinPager(position: Int) {
        val colorLetras : Int
        val colorFondo: Int
        with(form){
            when {
                position == Mob.MENU_P00 -> {
                    colorFondo = ContextCompat.getColor(ctx, R.color.celeste)
                    colorLetras = Color.WHITE
                }
                position < Mob.SEC1_P20 -> {
                    btobspager.visibility = View.VISIBLE
                    colorLetras = (Color.WHITE)
                    colorFondo = ContextCompat.getColor(ctx, R.color.holo_blue_dark)
                }
                position == Mob.OBSE_P24 -> {
                    btobspager.visibility = View.INVISIBLE
                    colorLetras = (Color.WHITE)
                    colorFondo = ContextCompat.getColor(ctx, R.color.teal_700)
                }
                else -> {
                    btobspager.visibility = View.VISIBLE
                    colorLetras = (Color.DKGRAY)
                    colorFondo = ContextCompat.getColor(ctx, R.color.cream_pastel)
                }
            }
            btsavepager.setColorFilter(colorLetras)
            btobspager.setColorFilter(colorLetras)
            btDrawerpager.setColorFilter(colorLetras)
            btinconpager.setColorFilter(colorLetras)
            btbackpager.setColorFilter(colorLetras)
            btnextpager.setColorFilter(colorLetras)
            txvtitlepager.setTextColor(colorLetras)
            txvtitlepager2.setTextColor(colorLetras)

            window.statusBarColor = colorFondo
            constraintpager.setBackgroundColor(colorFondo)
            toolbarpager.setBackgroundColor(colorFondo)

            val title = Mob.titleMapTxt[position]?.first
            val title2 = Mob.titleMapTxt[position]?.second
            val subtitle = Mob.titleMapTxt[position]?.third
            txvtitlepager.text = getString(title ?: R.string.cap_test)
            txvtitlepager2.text = getString(title2 ?: R.string.ctxt_test)

            if (position != Mob.OBSE_P24)
                txvsubtitlepager.text = getString(subtitle ?: R.string.subcap_test)
            else if (Mob.formComp?.ncontrol != null) {
                val ncontrol = "${getString(R.string.ncontrol)}  ${
                    Functions.ceroLeft((Mob.formComp?.ncontrol ?: "0"), Mob.FOR_5_DIGITS)}"
                txvsubtitlepager.text = ncontrol
            }

        }
    }
}