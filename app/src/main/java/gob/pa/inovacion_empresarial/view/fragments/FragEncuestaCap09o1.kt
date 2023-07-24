package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment

import androidx.core.view.isVisible
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo09Part1Binding
import gob.pa.inovacion_empresarial.function.Functions.hideKeyboard
import gob.pa.inovacion_empresarial.function.Functions.toEditable
import gob.pa.inovacion_empresarial.model.Mob


class FragEncuestaCap09o1 : Fragment() {

    private lateinit var bindingcap9o1: EncuestaCapitulo09Part1Binding
    private lateinit var ctx: Context
    private var seecap = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingcap9o1 = EncuestaCapitulo09Part1Binding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingcap9o1.root
    }

    override fun onResume() {
        super.onResume()
        Mob.indiceEnc = Mob.CAP9P17
        if (seecap) fillOut()
        else onAction()
    }

    private fun onAction() {
        with(bindingcap9o1) {
            if (rbtCap959No.isChecked) {
                layoutCap960.isVisible = false
                layoutCap961.isVisible = false
            }
            txtCap9606Otra.isEnabled = rbtCap9606.isChecked

            rgroupCap959.setOnCheckedChangeListener { _, id ->
                hideKeyboard()
                when (id) {
                    rbtCap959Si.id -> {
                        layoutCap960.isVisible = true
                        layoutCap961.isVisible = true }
                    rbtCap959No.id -> {
                        layoutCap960.isVisible = false
                        layoutCap961.isVisible = false }
                }
            }
            rgroupCap960.setOnCheckedChangeListener { _, id ->
                hideKeyboard()
                when (id) {
                    rbtCap9606.id -> txtCap9606Otra.isEnabled = true
                    else -> txtCap9606Otra.isEnabled = false
                }
            }
            lowCap9o1.setOnClickListener { savedCap() }
        }
    }

    private fun fillOut() {
        val cap9 = Mob.formComp?.cap9
        val blank = "".toEditable()
        with(bindingcap9o1) {
            when (cap9?.v59check) {
                true -> rbtCap959Si.isChecked = true
                false -> rbtCap959No.isChecked = true
                else -> rgroupCap959.clearCheck()
            }
            when (cap9?.v60num) {
                "1", "Panamá" -> rbtCap9601.isChecked = true
                "2", "Centroamérica y el Caribe"  -> rbtCap9602.isChecked = true
                "3", "Estados Unidos"  -> rbtCap9603.isChecked = true
                "4", "Unión Europea"  -> rbtCap9604.isChecked = true
                "5", "Japón"  -> rbtCap9605.isChecked = true
                "6", "Otro"  -> rbtCap9606.isChecked = true
                else -> rgroupCap960.clearCheck()
            }
            txtCap9606Otra.text = cap9?.v60txtotro?.toEditable() ?: blank

            when (cap9?.v61check1) {
                true -> rbtCap9611Si.isChecked = true
                false -> rbtCap9611No.isChecked = true
                else -> rgroupCap9611.clearCheck()
            }
            when (cap9?.v61check2) {
                true -> rbtCap9612Si.isChecked = true
                false -> rbtCap9612No.isChecked = true
                else -> rgroupCap9612.clearCheck()
            }
            when (cap9?.v61check3) {
                true -> rbtCap9613Si.isChecked = true
                false -> rbtCap9613No.isChecked = true
                else -> rgroupCap9613.clearCheck()
            }
            when (cap9?.v61check4) {
                true -> rbtCap9614Si.isChecked = true
                false -> rbtCap9614No.isChecked = true
                else -> rgroupCap9614.clearCheck()
            }
            when (cap9?.v61check5) {
                true -> rbtCap9615Si.isChecked = true
                false -> rbtCap9615No.isChecked = true
                else -> rgroupCap9615.clearCheck()
            }
            when (cap9?.v61check6) {
                true -> rbtCap9616Si.isChecked = true
                false -> rbtCap9616No.isChecked = true
                else -> rgroupCap9616.clearCheck()
            }
            when (cap9?.v61check7) {
                true -> rbtCap9617Si.isChecked = true
                false -> rbtCap9617No.isChecked = true
                else -> rgroupCap9617.clearCheck()
            }
            when (cap9?.v61check8) {
                true -> rbtCap9618Si.isChecked = true
                false -> rbtCap9618No.isChecked = true
                else -> rgroupCap9618.clearCheck()
            }
        }
        seecap = false
        onAction()
    }





    private fun savedCap() {}
}