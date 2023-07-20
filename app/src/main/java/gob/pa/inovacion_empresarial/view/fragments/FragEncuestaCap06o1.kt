package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo03Binding
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo061InovacionProductosBinding
import gob.pa.inovacion_empresarial.function.Functions.toEditable
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelCap6

class FragEncuestaCap06o1 : Fragment() {

    private lateinit var bindingcap6o1: EncuestaCapitulo061InovacionProductosBinding
    private lateinit var ctx: Context
    private var seecap = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingcap6o1 = EncuestaCapitulo061InovacionProductosBinding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingcap6o1.root
    }

    override fun onResume() {
        super.onResume()
        Mob.indiceEnc = Mob.CAP6P08
        if (seecap) fillOut()
        else onAction()
    }

    private fun onAction() {
        with(bindingcap6o1) {
            lowCap6o1.setOnClickListener { saveCap() }
        }
    }


    private fun fillOut() {
        val cap6 = Mob.formComp?.cap6
        with(bindingcap6o1) {
            fillOut39(cap6)
            when (cap6?.v40check1) {
                true -> rbtCap6401Si.isChecked = true
                false -> rbtCap6401No.isChecked = true
                else -> rgroupCap6401.clearCheck()
            }
            when (cap6?.v40check2) {
                true -> rbtCap6402Si.isChecked = true
                false -> rbtCap6402No.isChecked = true
                else -> rgroupCap6402.clearCheck()
            }
            when (cap6?.v41check) {
                "1" -> rbtCap6411.isChecked = true
                "2" -> rbtCap6412.isChecked = true
                "3" -> rbtCap6413.isChecked = true
                "4" -> rbtCap6414.isChecked = true
                else -> { rgroupCap641.clearCheck() }
            }
        }
        seecap = false
        onAction()
    }

    private fun fillOut39(cap6: ModelCap6?) {
        with(bindingcap6o1) {
            when (cap6?.v39check21o1) {
                true -> rbtCap6391Si2021.isChecked = true
                false -> rbtCap6391No2021.isChecked = true
                else -> rgroupCap63912021.clearCheck()
            }
            when (cap6?.v39check22o1) {
                true -> rbtCap6391Si2022.isChecked = true
                false -> rbtCap6391No2022.isChecked = true
                else -> rgroupCap63912022.clearCheck()
            }

            when (cap6?.v39check21o2) {
                true -> rbtCap6392Si2021.isChecked = true
                false -> rbtCap6392No2021.isChecked = true
                else -> rgroupCap63922021.clearCheck()
            }
            when (cap6?.v39check22o2) {
                true -> rbtCap6392Si2022.isChecked = true
                false -> rbtCap6392No2022.isChecked = true
                else -> rgroupCap63922022.clearCheck()
            }
        }
    }



    private fun saveCap() {
        TODO("Not yet implemented")
    }
}