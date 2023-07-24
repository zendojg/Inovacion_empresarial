package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.databinding.ModuloSeccion02Binding
import gob.pa.inovacion_empresarial.model.Mob

class FragModuloSecc02 : Fragment() {

    private lateinit var bindingmod2: ModuloSeccion02Binding
    private lateinit var ctx: Context
    private var seecap = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingmod2 = ModuloSeccion02Binding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingmod2.root
    }

    override fun onResume() {
        super.onResume()
        Mob.indiceEnc = Mob.SEC2P21
        if (seecap) fillOut()
        else onAction()
    }

    private fun onAction() {
        with(bindingmod2) {

            lowMod2.setOnClickListener { savedCap() }
        }
    }

    private fun fillOut() {
        val mod2 = Mob.formComp?.capMod
        with(bindingmod2) {
            checkSecc231.isChecked = mod2?.v3check1 == true
            checkSecc232.isChecked = mod2?.v3check2 == true
            checkSecc233.isChecked = mod2?.v3check3 == true
            checkSecc234.isChecked = mod2?.v3check4 == true

        }
        seecap = false
        onAction()
    }

    private fun savedCap() {}
}