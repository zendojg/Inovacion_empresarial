package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo10Binding
import gob.pa.inovacion_empresarial.model.Mob

class FragEncuestaCap10 : Fragment() {

    private lateinit var bindingcap10: EncuestaCapitulo10Binding
    private lateinit var ctx: Context
    private var seecap = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingcap10 = EncuestaCapitulo10Binding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingcap10.root
    }

    override fun onResume() {
        super.onResume()
        Mob.indiceEnc = Mob.CAPXP19
        if (seecap) fillOut()
        else onAction()
    }

    private fun onAction() {
        with(bindingcap10) {

            lowCap10.setOnClickListener { savedCap() }
        }
    }

    private fun fillOut() {
        val cap10 = Mob.formComp?.capx
        with(bindingcap10) {

            when (cap10?.v66check1) {
                true -> rbtCap10661Si.isChecked = true
                false -> rbtCap10661No.isChecked = true
                else -> rgroupCap10661.clearCheck()
            }
            when (cap10?.v66check2) {
                true -> rbtCap10662Si.isChecked = true
                false -> rbtCap10662No.isChecked = true
                else -> rgroupCap10662.clearCheck()
            }
            when (cap10?.v66check3) {
                true -> rbtCap10663Si.isChecked = true
                false -> rbtCap10663No.isChecked = true
                else -> rgroupCap10663.clearCheck()
            }
            when (cap10?.v66check4) {
                true -> rbtCap10664Si.isChecked = true
                false -> rbtCap10664No.isChecked = true
                else -> rgroupCap10664.clearCheck()
            }
        }
        seecap = false
        onAction()
    }

    private fun savedCap() {}
}