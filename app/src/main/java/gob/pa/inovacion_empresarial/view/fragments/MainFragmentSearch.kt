package gob.pa.inovacion_empresarial.view.fragments

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.databinding.FragSearchMainBinding
import gob.pa.inovacion_empresarial.function.CreateForm
import gob.pa.inovacion_empresarial.function.Functions
import gob.pa.inovacion_empresarial.function.Functions.hideKeyboard
import gob.pa.inovacion_empresarial.function.Functions.toEditable
import gob.pa.inovacion_empresarial.model.DVModel
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.view.FormActivity
import kotlinx.coroutines.launch
import pl.droidsonroids.gif.GifImageView

class MainFragmentSearch : Fragment() {

    private lateinit var fragSearch: FragSearchMainBinding
    private lateinit var ctx: Context
    private val dvmSearch: DVModel by viewModels()
    private var aDialog: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragSearch = FragSearchMainBinding.inflate(layoutInflater)
        ctx = requireContext()
        return fragSearch.root
    }

    override fun onPause() {
        super.onPause()
        fragSearch.txtNControlSearch.text?.clear()
        if (aDialog?.isShowing == true)  aDialog?.dismiss()
    }

    override fun onResume() {
        super.onResume()
        viewFind(true)
        fragSearch.barSearch.visibility = View.INVISIBLE
        fragSearch.versionsearch.text = Mob.version
        onAction()
    }

    private fun onAction() {
        val pager = activity?.findViewById<ViewPager2>(R.id.viewpagerMain)
        with (fragSearch) {
            txtNControlSearch.setOnEditorActionListener { _, actionID, _ ->
                if (actionID == EditorInfo.IME_ACTION_DONE) {
                    controlTxt()
                    true
                } else false
            }
            txtNControllySearch.setStartIconOnClickListener {
                if (!lyContainer.isVisible) { controlTxt() }
            }
            btSearch.setOnClickListener { controlTxt() }
            btcancelSearch.setOnClickListener { viewFind(true) }
            btdataSearch.setOnClickListener {
                pager?.setCurrentItem(Mob.PAGE02, true)
            }
            btformSearch.setOnClickListener {
                pager?.setCurrentItem(Mob.PAGE03, true)
            }
        }
    }

    private fun controlTxt() {
        with(fragSearch) {
            if (txtNControlSearch.text.isNullOrEmpty()) {
                txtNControllySearch.error = "Ingrese un N° de Control"
            } else {
                val ncontrolReformat =
                    Functions.ceroLeft(txtNControlSearch.text.toString(), Mob.FOR_5_DIGITS)
                txtNControlSearch.text = ncontrolReformat.toEditable()
                val ncont: Int = try {
                    txtNControlSearch.text.toString().toInt()
                } catch (e: java.lang.NumberFormatException) {
                    0
                }
                if (ncont <= 0) { txtNControllySearch.error = "N° de Control inválido"
                } else {
                    txtNControllySearch.helperText = "Búsqueda del número de control"
                    getForm(ncont.toString())
                } //------------
            }
            hideKeyboard()
        }
    }

    private fun getForm(ncont: String) {
        val win = AlertDialog.Builder(ctx)
        aDialog = win.create()
        aDialog?.setCancelable(false)
        aDialog?.show()
        fragSearch.barSearch.visibility = View.VISIBLE
        lifecycleScope.launch {
            val resp = dvmSearch.formGet(ncont)
            if (resp != null) {
                fragSearch.barSearch.visibility = View.GONE

                when (resp.code) {
                    Mob.CODE200 -> {
                        CreateForm.createLoad(resp.body)
                        viewFind(false)
                    }
                    Mob.CODE401 -> activity?.runOnUiThread {
                        val bttest = activity?.findViewById<Button>(R.id.btMainTest)
                        bttest?.callOnClick()
                        if (!resp.resp.isNullOrEmpty()) errorMsg("Error: ${resp.code}")
                    }
                    Mob.CODE403, Mob.CODE404 -> {
                        if (!resp.resp.isNullOrEmpty())
                             errorMsg("Error ${resp.code}: ${resp.resp}")
                        else errorMsg("Error: ${resp.code}")
                    }

                    Mob.CODE500 -> {
                        if (!resp.resp.isNullOrEmpty()) {
                            if (resp.resp.length < Mob.LIMITMSG)
                                 errorMsg("Error ${resp.code}: ${resp.resp}")
                            else errorMsg("Error en el servidor (500)")
                        }
                    }
                    else -> { errorMsg("Error: ${resp.code}") }
                }
                aDialog?.dismiss()
            }
        }
    }

    private fun errorMsg(resp: String) {
        var errortxt = resp
        errortxt = errortxt.replace("NumComtrol", "N° de control")
        errortxt = errortxt.replace("error", "")
        errortxt = errortxt.replace(Regex("[:{}\"]"), "")
        val widthtxt = (errortxt.length * Mob.SIZE_AUTOCONTROL)
        val color = ContextCompat.getColor(ctx, R.color.dark_red)
        val alert = Functions.msgBallom(errortxt, widthtxt, ctx, color)
        alert.showAlignBottom(fragSearch.versionsearch)
        alert.dismissWithDelay(Mob.TIMELONG4SEG)
    }

    private fun viewFind(check: Boolean) {
        with (fragSearch){
            lyContainer.isVisible = !check
            //btSearch.isVisible = check
            btdataSearch.isVisible = check
            btformSearch.isVisible = check
            versionsearch.isVisible = check
            imgSenacytsearch.isVisible = check
            txtNControlSearch.isEnabled = check
            when (check) {
                false -> {
                    with(Mob) {
                        val blank = "".toEditable()
                        txtLocalSearch.text = cap2?.v05nameLtxt?.toEditable() ?: blank
                        txtrazonSearch.text = cap2?.v06razontxt?.toEditable() ?: blank
                        txttel1Search.text = cap2?.v09tel1txt?.toEditable() ?: blank
                        txttel2Search.text = cap2?.v09tel2txt?.toEditable() ?: blank
                        txtRUCsearch.text = cap2?.v07ructxt?.toEditable() ?: blank
                        txtdirSearch.text = cap2?.v08dirtxt?.toEditable() ?: blank
                    }
                }
                true -> {
                    txtLocalSearch.text?.clear()
                    txtrazonSearch.text?.clear()
                    txttel1Search.text?.clear()
                    txttel2Search.text?.clear()
                    txtRUCsearch.text?.clear()
                    txtdirSearch.text?.clear()
                }
            }
            btinitSearch.setOnClickListener {
                Mob.indiceFormulario = Mob.CAP1_P01
                activity?.finish()
                val options = ActivityOptions.makeCustomAnimation(ctx,
                        R.animator.slide_in_from_right, R.animator.slide_out_to_left)
                val intent = Intent(ctx, FormActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK  or
                        Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent, options.toBundle())
            }
        }
        hideKeyboard()
    }

}
