package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.databinding.FragUserMainBinding
import gob.pa.inovacion_empresarial.databinding.StyleMsgAlertBinding
import gob.pa.inovacion_empresarial.function.AppCache
import gob.pa.inovacion_empresarial.model.DVModel
import gob.pa.inovacion_empresarial.model.Mob

class MainFragmentData : Fragment() {
    private lateinit var bindingUser: FragUserMainBinding
    private lateinit var ctx: Context
    private val dvmUser: DVModel by viewModels()
    var aDialog: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingUser = FragUserMainBinding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingUser.root
    }

    override fun onResume() {
        super.onResume()

        fillOut()

    }

    private fun fillOut() {
        var rol = ""
        rol = when (Mob.authData?.rol) {
            "E" -> "Empadronador"
            "S" -> "Supervisor"
            else -> "Desconocido?"
        }
        val expira = Mob.authData?.result?.infotoken?.expire?.split("T")
        with(bindingUser) {
            lbROLUser.text = rol
            lbuserUser.text = Mob.authData?.user ?: "Desconocido"
            lbnameUser.text = Mob.authData?.name ?: "Desconocido"
            lbfechaUser.text = expira?.get(0) ?: "0/0/0"
        }

        onAction()
    }

    private fun onAction() {
        with(bindingUser) {
            btExitUser.setOnClickListener { logout() }

            btbackUser.setOnClickListener {
                val pager = activity?.findViewById<ViewPager2>(R.id.viewpagerMain)
                pager?.setCurrentItem(Mob.INIT01, true)
            }
        }
    }

    private fun logout() {
        val msgLogout = AlertDialog.Builder(ctx)
        val bindSend: StyleMsgAlertBinding =
            DataBindingUtil.inflate(LayoutInflater.from(ctx),
                R.layout.style_msg_alert, null, false)
        with (bindSend) {
            btpositivo.text = getString(R.string.back)
            btnegativo.text = getString(R.string.logout)
            msg1.visibility = View.GONE
            msg2.visibility = View.GONE
            msgLogout.setView(view)

            msgLogout.setView(bindSend.root)
            aDialog = msgLogout.create()
            aDialog?.show()

            btpositivo.icon = ContextCompat.getDrawable(ctx, R.drawable.img_back)
            btnegativo.icon = ContextCompat.getDrawable(ctx, R.drawable.img_logout)

            btpositivo.setOnClickListener { aDialog?.dismiss() }
            btnegativo.setOnClickListener {
                aDialog?.dismiss()
                Mob.authData = null
                AppCache.loginCLEAR(ctx)
            }
        }
    }
}