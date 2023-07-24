package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.databinding.ModuloSeccion01Binding
import gob.pa.inovacion_empresarial.function.Functions.hideKeyboard
import gob.pa.inovacion_empresarial.function.Functions.toEditable
import gob.pa.inovacion_empresarial.model.Mob

class FragModuloSecc01 : Fragment() {


    private lateinit var bindingmod1: ModuloSeccion01Binding
    private lateinit var ctx: Context
    private var seecap = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingmod1 = ModuloSeccion01Binding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingmod1.root
    }

    override fun onResume() {
        super.onResume()
        Mob.indiceEnc = Mob.SEC1P20
        if (seecap) fillOut()
        else onAction()
    }

    private fun onAction() {
        with(bindingmod1) {
            if (rbtSecc011No.isChecked) {
                Mob.moduleContinue = false
                linearSecc012.isVisible = false
            }

            rgroupSecc011.setOnCheckedChangeListener { _, id ->
                hideKeyboard()
                when (id) {
                    rbtSecc011Si.id -> {
                        Mob.moduleContinue = true
                        linearSecc012.isVisible = true
                    }
                    rbtSecc011No.id -> {
                        Mob.moduleContinue = false
                        linearSecc012.isVisible = false
                    }
                }
            }

            if (rbtSecc012Si.isChecked) linearSecc0121.isVisible = true

            rgroupSecc0121.setOnCheckedChangeListener { _, id ->
                hideKeyboard()
                when (id) {
                    rbtSecc012Si.id -> linearSecc0121.isVisible = true
                    rbtSecc012No.id -> linearSecc0121.isVisible = false
                }
            }

            checkSecc012D.setOnClickListener {
                txtSecc012DOtra.isVisible = checkSecc012D.isChecked
            }

            lowMod1.setOnClickListener { savedCap() }
        }
    }

    private fun fillOut() {
        val mod1 = Mob.formComp?.capMod
        val blank = "".toEditable()
        with(bindingmod1) {
            when (mod1?.v1check) {
                true -> rbtSecc011Si.isChecked = true
                false -> rbtSecc011No.isChecked = true
                else -> rgroupSecc011.clearCheck()
            }
            when (mod1?.v2check1) {
                true -> rbtSecc012Si.isChecked = true
                false -> rbtSecc012No.isChecked = true
                else -> rgroupSecc0121.clearCheck()
            }
            checkSecc012A.isChecked = mod1?.v2check1a == true
            checkSecc012B.isChecked = mod1?.v2check1b == true
            checkSecc012C.isChecked = mod1?.v2check1c == true
            checkSecc012D.isChecked = mod1?.v2check1d == true

            txtSecc012DOtra.text = mod1?.v2txtDesc1d?.toEditable() ?: blank

            checkSecc0122.isChecked = mod1?.v2check2 == true
        }
        seecap = false
        onAction()
    }





    private fun savedCap() {}


}