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
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.button.MaterialButton
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.databinding.FragSearchMainBinding
import gob.pa.inovacion_empresarial.function.Functions
import gob.pa.inovacion_empresarial.function.Functions.hideKeyboard
import gob.pa.inovacion_empresarial.function.Functions.toEditable
import gob.pa.inovacion_empresarial.model.DVModel
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.view.FormActivity
import gob.pa.inovacion_empresarial.view.MainActivity
import kotlinx.coroutines.launch

class MainFragmentSearch: Fragment() {

    private lateinit var fragSearch: FragSearchMainBinding
    private lateinit var ctx: Context
    private val dvmSearch: DVModel by viewModels()
    private var aDialog: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
    fragSearch = FragSearchMainBinding.inflate(layoutInflater)
    ctx = requireContext()
    return fragSearch.root
    }
    override fun onPause() {
        super.onPause()
        if (aDialog?.isShowing == true){
            aDialog?.dismiss()
        }
    }
    override fun onResume() {
        super.onResume()
        fragSearch.versionsearch.text = Mob.version
        actions()
    }


    private fun actions() {
        fragSearch.txtNControlSearch.setOnEditorActionListener { _, actionID, _ ->
            if (actionID == EditorInfo.IME_ACTION_DONE) {
                controlTxt()
                true
            } else false
        }
        fragSearch.btSearch.setOnClickListener { controlTxt() }
        fragSearch.btcancelSearch.setOnClickListener { viewFind(false) }

    }

    private fun controlTxt() {
        hideKeyboard()
        if (fragSearch.txtNControlSearch.text.isNullOrEmpty()) {
            val alert = Functions.msgMark("Ingrese un N° de Control",Mob.WIDTHBALLON180,ctx)
            alert.showAlignBottom(fragSearch.txtNControlSearch)
            alert.dismissWithDelay(Mob.TIMEBALLON2SEG)
        } else {
            val ncont: Int = try { fragSearch.txtNControlSearch.text.toString().toInt() }
            catch (e: Exception) { 0 }
            if (ncont<=0) {
                val alert = Functions.msgMark("N° de Control inválido",Mob.WIDTHBALLON180,ctx)
                alert.showAlignBottom(fragSearch.txtNControlSearch)
                alert.dismissWithDelay(Mob.TIMEBALLON2SEG)
            } else { getForm(ncont.toString()) }
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
                        Mob.formComp = resp.body
                        Mob.cap1 = resp.body?.cap1
                        Mob.cap2 = resp.body?.cap2
                        Mob.cap3 = resp.body?.cap3
                        Mob.cap4 = resp.body?.cap4
                        Mob.cap5 = resp.body?.cap5
                        Mob.cap6 = resp.body?.cap6
                        Mob.cap7 = resp.body?.cap7
                        Mob.cap8 = resp.body?.cap8
                        Mob.cap9 = resp.body?.cap9
                        Mob.capx = resp.body?.capx

                        println("\n---------CAP1:${Mob.cap1}" +
                                "\n---------CAP2:${Mob.cap2}" +
                                "\n---------CAP3:${Mob.cap3}" +
                                "\n---------CAP4:${Mob.cap4}" +
                                "\n---------CAP5:${Mob.cap5}")
                        viewFind(true)
                    }
                    Mob.CODE401 -> {
                        val bttest = MainActivity().findViewById<Button>(R.id.btMainTest)
                        val waitBar = MainActivity().findViewById<ProgressBar>(R.id.barMain)
                        waitBar.visibility = View.VISIBLE
                        bttest.callOnClick()
                    }
                }
                if (!resp.resp.isNullOrEmpty()) {
                    var errortxt = resp.resp
                    errortxt = errortxt.replace("error", "")
                    errortxt = errortxt.replace(":", "")
                    errortxt = errortxt.replace("{", "")
                    errortxt = errortxt.replace("}", "")
                    errortxt = errortxt.replace("\"", "")
                    Toast.makeText(ctx, errortxt, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun viewFind(check: Boolean) {

        fragSearch.lyContainer.isVisible = check
        fragSearch.btSearch.isVisible = !check
        fragSearch.btdataSearch.isVisible = !check
        fragSearch.btuserSearch.isVisible = !check
        fragSearch.versionsearch.isVisible = !check
        fragSearch.imgSenacytsearch.isVisible = !check

        if (check) {
            with(Mob) {
                val blank = "".toEditable()
                fragSearch.txtLocalSearch.text = cap2?.v05nameLtxt?.toEditable() ?: blank
                fragSearch.txtrazonSearch.text = cap2?.v06razontxt?.toEditable() ?: blank
                fragSearch.txtRUCsearch.text = cap2?.v07ructxt?.toEditable() ?: blank
                fragSearch.txtdirSearch.text = cap2?.v08dirtxt?.toEditable() ?: blank
                fragSearch.txttel1Search.text = cap2?.v09tel1txt?.toEditable() ?: blank
                fragSearch.txttel2Search.text = cap2?.v09tel2txt?.toEditable() ?: blank
            }

        } else {
            fragSearch.txtLocalSearch.text?.clear()
            fragSearch.txtrazonSearch.text?.clear()
            fragSearch.txtRUCsearch.text?.clear()
            fragSearch.txtdirSearch.text?.clear()
            fragSearch.txttel1Search.text?.clear()
            fragSearch.txttel2Search.text?.clear()
        }
        
        
//            Mob.indiceEnc = 1
//            activity?.finish()
//            startActivity(Intent(ctx, FormActivity::class.java))
    }

}
