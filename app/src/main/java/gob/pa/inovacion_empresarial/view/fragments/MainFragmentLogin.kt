package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
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
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.textfield.TextInputLayout
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.databinding.FragLoginMainBinding
import gob.pa.inovacion_empresarial.function.AppCache
import gob.pa.inovacion_empresarial.function.Functions
import gob.pa.inovacion_empresarial.function.Functions.hideKeyboard
import gob.pa.inovacion_empresarial.function.Functions.toEditable
import gob.pa.inovacion_empresarial.model.DVModel
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelLog
import kotlinx.coroutines.launch
import java.text.DecimalFormat

class MainFragmentLogin: Fragment() {

    private lateinit var fragLogin: FragLoginMainBinding
    private lateinit var ctx: Context
    private val dvmLogin: DVModel by viewModels()
    private var rol = ""

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
        if (pager?.currentItem != null)
            Mob.mainWindow = pager.currentItem
        fragLogin.lbversionLogin.text = Mob.version

        Toast.makeText(ctx, "App de prueba ${Mob.version}", Toast.LENGTH_SHORT).show() //---- Eliminar
        onAction()
    }

    private fun onAction() {
        fragLogin.checkLogin.isChecked = AppCache.remGET(ctx) //----  REMEMBER ACTION
        if (fragLogin.checkLogin.isChecked) {
            fragLogin.txtuserLogin.text = AppCache.userGET(ctx).toEditable()
        }
        fragLogin.checkLogin.setOnClickListener {
            AppCache.remSAVE(ctx, fragLogin.checkLogin.isChecked)
        }
        fragLogin.txtpassLogin.addTextChangedListener(object: TextWatcher { // -- ADD PASSWATCHER
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                fragLogin.txtpasslyLogin.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })

        fragLogin.txtuserLogin.setOnEditorActionListener { _, actionID, _ -> //- LOGIN KEYDONE USER
            if (actionID == EditorInfo.IME_ACTION_DONE)  {
                clickLister()
                true
            } else false
        }
        fragLogin.txtpassLogin.setOnEditorActionListener { _, actionID, _ -> //- LOGIN KEYDONE PASS
            if (actionID == EditorInfo.IME_ACTION_DONE)  {
                clickLister()
                true
            } else false
        }
        fragLogin.btLogin.setOnClickListener { clickLister() }  //--- LOGIN BUTTON

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

                    val user = fragLogin.txtuserLogin.text.toString()
                    if (user.contains("E")) rol = "E"
                    else if (user.contains("S")) rol = "S"
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
            rol = rol
        )
        fragLogin.barLogin.visibility = View.VISIBLE
        screenBlack.setCancelable(false)
        screenBlack.show()

        lifecycleScope.launch {
            val resp = dvmLogin.loginCall(login)
            if (resp != null) {
                if (!resp.msg.isNullOrEmpty()) {
                    Handler(Looper.getMainLooper()).postDelayed({
                        var errortxt = resp.msg
                        if (errortxt.contains("No se encontró datos para la credencial")) {
                            val color = ContextCompat.getColor(ctx, R.color.dark_red)
                            val alert = Functions.msgBallom(
                                "Usuario o Contraseña inválida", Mob.WIDTH180DP, ctx, color)
                            alert.showAlignBottom(fragLogin.btLogin)
                            alert.dismissWithDelay(Mob.TIMELONG2SEG)
                        } else {
                            errortxt = errortxt.replace("error", "")
                            errortxt = errortxt.replace(":", "")
                            errortxt = errortxt.replace("{", "")
                            errortxt = errortxt.replace("}", "")
                            errortxt = errortxt.replace("\"", "")
                            Toast.makeText(ctx, errortxt, Toast.LENGTH_LONG).show()
                        }
                    }, (Mob.TIME800MS))
                } else {
                    when (resp.code) {
                        Mob.CODE200 -> { // --- LOGIN OK
                            Mob.authData = resp.body
                            if (fragLogin.checkLogin.isChecked) {
                                AppCache.loginSAVE(ctx, resp.body)
                                AppCache.userSAVE(ctx, login.user)
                            }
                            Handler(Looper.getMainLooper()).postDelayed({
                                fragLogin.txtuserLogin.text?.clear()
                                fragLogin.txtpassLogin.text?.clear()
                                val pager = activity?.findViewById<ViewPager2>(R.id.viewpagerMain)
                                pager?.setCurrentItem(Mob.INIT01, true)
                            }, (Mob.TIME500MS))
                        }
                        Mob.CODE401 -> {}
                        Mob.CODE403 -> {}
                        Mob.CODE404 -> {}
                        Mob.CODE500 -> {}
                        //----- ADD MORE ERRORS
                    }
                }
            }
            Handler(Looper.getMainLooper()).postDelayed({
                screenBlack.dismiss()
                fragLogin.barLogin.visibility = View.GONE
            }, (Mob.TIME1S))
        }
    }
}
