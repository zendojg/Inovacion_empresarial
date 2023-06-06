package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.textfield.TextInputLayout
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.databinding.FragLoginMainBinding
import gob.pa.inovacion_empresarial.function.AppCache
import gob.pa.inovacion_empresarial.function.Functions
import gob.pa.inovacion_empresarial.function.Functions.Companion.hideKeyboard
import gob.pa.inovacion_empresarial.function.Functions.Companion.toEditable
import gob.pa.inovacion_empresarial.model.DVModel
import gob.pa.inovacion_empresarial.model.MVar
import gob.pa.inovacion_empresarial.model.ModelLog
import kotlinx.coroutines.launch

class MainFragmentLogin: Fragment() {

    private lateinit var fragLogin: FragLoginMainBinding
    private lateinit var ctx: Context
    private val dvmLogin: DVModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
    fragLogin = FragLoginMainBinding.inflate(layoutInflater)
    ctx = requireContext()
    return fragLogin.root
    }

    override fun onResume() {
        super.onResume()
        val pager = activity?.findViewById<ViewPager2>(R.id.viewpagerMain)
        MVar.windNow = pager?.currentItem!!
        fragLogin.lbversionLogin.text = MVar.version
        actions()
    }

    private fun actions() {
        fragLogin.checkLogin.isChecked = AppCache.remGET(ctx)
        if (fragLogin.checkLogin.isChecked) {
            fragLogin.txtuserLogin.text = AppCache.userGET(ctx).toEditable()
        }
        fragLogin.checkLogin.setOnClickListener {
            AppCache.remSAVE(ctx, fragLogin.checkLogin.isChecked)
        }
        fragLogin.txtpassLogin.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                fragLogin.txtpasslyLogin.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })

        fragLogin.txtuserLogin.setOnEditorActionListener { _, actionID, _ ->
            if (actionID == EditorInfo.IME_ACTION_DONE) {
                clickLister()
                true
            } else false
        }
        fragLogin.txtpassLogin.setOnEditorActionListener { _, actionID, _ ->
            if (actionID == EditorInfo.IME_ACTION_DONE) {
                clickLister()
                true
            } else false
        }
        fragLogin.btLogin.setOnClickListener { clickLister() }

    }

    private fun clickLister() {
        hideKeyboard()
        if (Functions.isOnline(ctx)) {
            when {
                fragLogin.txtuserLogin.text.isNullOrEmpty() -> {
                    fragLogin.txtuserLogin.error = getString(R.string.errorUser)
                    fragLogin.txtpassLogin.error = null
                    fragLogin.txtpasslyLogin.endIconMode =
                        TextInputLayout.END_ICON_PASSWORD_TOGGLE
                }
                fragLogin.txtpassLogin.text.isNullOrEmpty() -> {
                    fragLogin.txtpasslyLogin.endIconMode =
                        TextInputLayout.END_ICON_CUSTOM
                    fragLogin.txtpassLogin.error = getString(R.string.errorPass)
                    fragLogin.txtuserLogin.error = null
                }
                else -> {
                    fragLogin.txtpasslyLogin.endIconMode =
                        TextInputLayout.END_ICON_PASSWORD_TOGGLE
                    fragLogin.txtuserLogin.error = null
                    fragLogin.txtpassLogin.error = null
                    login()
                }
            }
        } else Toast.makeText(ctx, "Sin acceso a Internet", Toast.LENGTH_SHORT).show()
    }


    private fun login() {
        val screen = AlertDialog.Builder(ctx)
        val screenBlack: AlertDialog = screen.create()
        val login = ModelLog(
            user = fragLogin.txtuserLogin.text.toString(),
            pass = fragLogin.txtpassLogin.text.toString(),
            rol = "E"
        )
        fragLogin.barLogin.visibility = View.VISIBLE
        screenBlack.setCancelable(false)
        screenBlack.show()

        lifecycleScope.launch {
            val resp = dvmLogin.loginCall(login)
            if (resp!=null) {
                when (resp.code) {
                    200 -> {
                        MVar.authData = resp.body
                        if (fragLogin.checkLogin.isChecked) {
                            AppCache.loginSAVE(ctx, resp.body)
                            AppCache.userSAVE(ctx, login.user)
                        }
                        Handler(Looper.getMainLooper()).postDelayed({
                            fragLogin.txtuserLogin.text?.clear()
                            fragLogin.txtpassLogin.text?.clear()
                            val pager = activity?.findViewById<ViewPager2>(R.id.viewpagerMain)
                            pager?.setCurrentItem(1, true)
                        }, (800).toLong())
                    }
                }
                Handler(Looper.getMainLooper()).postDelayed({
                    if (!resp.resp.isNullOrEmpty()) {
                        var errortxt = resp.resp
                        if (errortxt.contains("No se encontró datos para la credencial")) {
                            val alert = Functions.ballonRED(
                                "Usuario o Contraseña inválida",220,ctx)
                            alert.showAlignBottom(fragLogin.txtpassLogin)
                            alert.dismissWithDelay(2000L)
                        } else {
                            errortxt = errortxt.replace("error", "")
                            errortxt = errortxt.replace(":", "")
                            errortxt = errortxt.replace("{", "")
                            errortxt = errortxt.replace("}", "")
                            errortxt = errortxt.replace("\"", "")
                            Toast.makeText(ctx, errortxt, Toast.LENGTH_LONG).show()
                        }
                    }
                }, (900).toLong())
            }
            Handler(Looper.getMainLooper()).postDelayed({
                screenBlack.dismiss()
                fragLogin.barLogin.visibility = View.GONE
            }, (1000).toLong())
        }
    }
}
