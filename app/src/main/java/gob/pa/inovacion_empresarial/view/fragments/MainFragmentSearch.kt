package gob.pa.inovacion_empresarial.view.fragments

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
            btSearch.setOnClickListener {
                //controlTxt()
            }
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
        hideKeyboard()
        val color = ContextCompat.getColor(ctx, R.color.dark_red)
        with(fragSearch) {
            if (txtNControlSearch.text.isNullOrEmpty()) {
                val alert =
                    Functions.msgMark("Ingrese un N° de Control", Mob.WIDTH180DP, ctx, color)
                alert.showAlignBottom(txtNControlSearch)
                alert.dismissWithDelay(Mob.TIMELONG2SEG)
            } else {
                val ncontrolReformat =
                    Functions.ceroLeft(txtNControlSearch.text.toString(), Mob.CEROLEFT)
                txtNControlSearch.text = ncontrolReformat.toEditable()
                val ncont: Int = try {
                    txtNControlSearch.text.toString().toInt()
                } catch (e: java.lang.NumberFormatException) {
                    0
                }
                if (ncont <= 0) {
                    val alert =
                        Functions.msgMark("N° de Control inválido", Mob.WIDTH180DP, ctx, color)
                    alert.showAlignBottom(txtNControlSearch)
                    alert.dismissWithDelay(Mob.TIMELONG2SEG)
                } else {
                    getForm(ncont.toString())
                } //------------
            }
        }
    }

    private fun getForm(ncont: String) {
        val win = AlertDialog.Builder(ctx)
        aDialog = win.create()
        aDialog?.setCancelable(false)
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
                    Mob.CODE401 -> {
                        val bttest = activity?.findViewById<Button>(R.id.btMainTest)
                        val waitBar = activity?.findViewById<ProgressBar>(R.id.barMain)
                        waitBar?.visibility = View.VISIBLE
                        bttest?.callOnClick()
                        if (!resp.resp.isNullOrEmpty()) errorMsg(resp.resp)
                    }
                    Mob.CODE403, Mob.CODE404 -> if (!resp.resp.isNullOrEmpty()) errorMsg(resp.resp)

                    Mob.CODE500 -> {

                        if (!resp.resp.isNullOrEmpty()) {
                            if (resp.resp.length < Mob.LIMITMSG)
                                errorMsg(resp.resp)
                            else errorMsg("Error en el servidor")
                        }
                    }
                }

            }
        }
    }

    private fun errorMsg(resp: String) {
        var errortxt = resp
        errortxt = errortxt.replace("error", "")
        errortxt = errortxt.replace(":", "")
        errortxt = errortxt.replace("{", "")
        errortxt = errortxt.replace("}", "")
        errortxt = errortxt.replace("\"", "")
        val widthtxt = (errortxt.length * Mob.SIZEAUTOCONTROL)
        val color = ContextCompat.getColor(ctx, R.color.dark_red)
        val alert = Functions.msgBallom(errortxt, widthtxt, ctx, color)
        alert.showAlignBottom(fragSearch.versionsearch)
        alert.dismissWithDelay(Mob.TIMELONG4SEG)
    }

    private fun viewFind(check: Boolean) {
        hideKeyboard()
        with (fragSearch){
            lyContainer.isVisible = !check
            btSearch.isVisible = check
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
                Mob.indiceFormulario = 1
                activity?.finish()
                startActivity(Intent(ctx, FormActivity::class.java))
            }
        }
    }

}
