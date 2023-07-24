package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.databinding.ModuloSeccion03Binding
import gob.pa.inovacion_empresarial.model.Mob

class FragModuloSecc03 : Fragment() {

    private lateinit var bindingmod3: ModuloSeccion03Binding
    private lateinit var ctx: Context
    private var seecap = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingmod3 = ModuloSeccion03Binding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingmod3.root
    }

    override fun onResume() {
        super.onResume()
        Mob.indiceEnc = Mob.CAPXP19
        if (seecap) fillOut()
        else onAction()
    }

    private fun onAction() {
        with(bindingmod3) {

            lowMod3.setOnClickListener { savedCap() }
        }
    }

    private fun fillOut() {
        val mod3 = Mob.formComp?.capMod
        with(bindingmod3) {

            when (mod3?.v4check) {
                true -> rbtSecc034Si.isChecked = true
                false -> rbtSecc034No.isChecked = true
                else -> rgroupSecc034.clearCheck()
            }
            when (mod3?.v4check1a) {
                true -> rbtSecc034ASi.isChecked = true
                false -> rbtSecc034ANo.isChecked = true
                else -> rgroupSecc034A.clearCheck()
            }
            when (mod3?.v4check1b) {
                true -> rbtSecc034BSi.isChecked = true
                false -> rbtSecc034BNo.isChecked = true
                else -> rgroupSecc034B.clearCheck()
            }
            when (mod3?.v4check1c) {
                true -> rbtSecc034CSi.isChecked = true
                false -> rbtSecc034CNo.isChecked = true
                else -> rgroupSecc034C.clearCheck()
            }
            when (mod3?.v4check1d) {
                true -> rbtSecc034DSi.isChecked = true
                false -> rbtSecc034DNo.isChecked = true
                else -> rgroupSecc034D.clearCheck()
            }
            when (mod3?.v4check1e) {
                true -> rbtSecc034ESi.isChecked = true
                false -> rbtSecc034ENo.isChecked = true
                else -> rgroupSecc034E.clearCheck()
            }
        }
        seecap = false
        onAction()
    }

    private fun savedCap() {}
}