package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import gob.pa.inovacion_empresarial.databinding.FragSearchMainBinding
import gob.pa.inovacion_empresarial.function.Functions
import gob.pa.inovacion_empresarial.function.Functions.hideKeyboard
import gob.pa.inovacion_empresarial.model.DVModel
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.view.FormActivity
import kotlinx.coroutines.launch

class MainFragmentSearch: Fragment() {

    private lateinit var fragSearch: FragSearchMainBinding
    private lateinit var ctx: Context
    private val dvmSearch: DVModel by viewModels()
    private var dialog: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
    fragSearch = FragSearchMainBinding.inflate(layoutInflater)
    ctx = requireContext()
    return fragSearch.root
    }

    override fun onResume() {
        super.onResume()
        fragSearch.versionForm.text = Mob.version
        actions()
    }


    private fun actions() {
        fragSearch.txtNControlSearch.setOnEditorActionListener { _, actionID, _ ->
            if (actionID == EditorInfo.IME_ACTION_DONE) {
                validate()
                true
            } else false
        }
        fragSearch.bt1main.setOnClickListener { validate() }

    }

    private fun validate() {
        hideKeyboard()
        if (fragSearch.txtNControlSearch.text.isNullOrEmpty()) {
            val alert = Functions.ballonRED("Ingrese un N° de Control",180,ctx)
            alert.showAlignBottom(fragSearch.txtNControlSearch)
            alert.dismissWithDelay(2000L)
        } else {
            val ncont: Int = try { fragSearch.txtNControlSearch.text.toString().toInt() }
            catch (e: Exception) { 0 }
            if (ncont<=0) {
                val alert = Functions.ballonRED("N° de Control inválido",180,ctx)
                alert.showAlignBottom(fragSearch.txtNControlSearch)
                alert.dismissWithDelay(2000L)
            } else { getForm(ncont.toString()) }
        }
    }

    private fun getForm(ncont: String) {
        val win = AlertDialog.Builder(ctx)

        dialog = win.create()
        dialog?.setCancelable(false)

        lifecycleScope.launch {
            val resp = dvmSearch.formGet(ncont)
            if (resp != null) {
                when (resp.code) {
                    200 -> {
                        Mob.formComp = resp.body
                        Mob.indiceEnc = 1
                        startActivity(Intent(ctx, FormActivity::class.java))
                    }


                }
                if (!resp.resp.isNullOrEmpty()) {
                    var errortxt = resp.resp
                    errortxt = errortxt.replace("error", "")
                    errortxt = errortxt.replace(":", "")
                    errortxt = errortxt.replace("{", "")
                    errortxt = errortxt.replace("}", "")
                    errortxt = errortxt.replace("\"", "")
                    Toast.makeText(ctx, errortxt, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}
