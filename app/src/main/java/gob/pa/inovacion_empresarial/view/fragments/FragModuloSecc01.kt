package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.databinding.ModuloSeccion01Binding
import gob.pa.inovacion_empresarial.function.CreateIncon
import gob.pa.inovacion_empresarial.function.Functions.hideKeyboard
import gob.pa.inovacion_empresarial.function.Functions.toEditable
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelMod

class FragModuloSecc01 : Fragment() {


    private lateinit var bindingmod1: ModuloSeccion01Binding
    private lateinit var ctx: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingmod1 = ModuloSeccion01Binding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingmod1.root
    }

    override fun onResume() {
        super.onResume()
        Mob.indiceFormulario = Mob.SEC1P20
        if (Mob.seesecc1) fillOut()
        else onAction()
    }

    private fun onAction() {
        with(bindingmod1) {
            if (rbtSecc011No.isChecked) {
                Mob.seccON = false
                linearSecc012.isVisible = false
            }

            rgroupSecc011.setOnCheckedChangeListener { _, id ->
                hideKeyboard()
                when (id) {
                    rbtSecc011Si.id -> {
                        Mob.seccON = true
                        linearSecc012.isVisible = true
                    }
                    rbtSecc011No.id -> {
                        Mob.seccON = false
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

            lowMod1.setOnClickListener { saveCap() }
        }
    }

    private fun fillOut() {
        val mod1 = Mob.formComp?.capMod
        val blank = "".toEditable()
        with(bindingmod1) {
            Mob.seccON = mod1?.v1check
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
        Mob.seesecc1 = false
        onAction()
    }

    fun saveCap(): List<String> {
        with (bindingmod1) {
            Mob.capMod = ModelMod(
                Mob.capMod?.id,
                if (rbtSecc011Si.isChecked) true else if (rbtSecc011No.isChecked) false else null,
                if (Mob.seccON == true && rbtSecc012Si.isChecked) true else
                    if (Mob.seccON == true && rbtSecc012No.isChecked) false else null,
                if (Mob.seccON == true && checkSecc012A.isChecked) true else null,
                if (Mob.seccON == true && checkSecc012B.isChecked) true else null,
                if (Mob.seccON == true && checkSecc012C.isChecked) true else null,
                if (Mob.seccON == true && checkSecc012D.isChecked) true else null,
                if (Mob.seccON == true && checkSecc012D.isChecked)
                    txtSecc012DOtra.text.toString() else null,
                if (Mob.seccON == true && checkSecc0122.isChecked) true else null,
                //Mob.capMod?.v2txtnull: String?,
                if (Mob.seccON == true) Mob.capMod?.v3check1 else null,//---------
                if (Mob.seccON == true) Mob.capMod?.v3check2 else null,
                if (Mob.seccON == true) Mob.capMod?.v3check3 else null,
                if (Mob.seccON == true) Mob.capMod?.v3check4 else null,
                if (Mob.seccON == true) Mob.capMod?.v4check else null,
                if (Mob.seccON == true) Mob.capMod?.v4check1a else null,
                if (Mob.seccON == true) Mob.capMod?.v4check1aPorcent else null,
                if (Mob.seccON == true) Mob.capMod?.v4check1b else null,
                if (Mob.seccON == true) Mob.capMod?.v4check1bPorcent else null,
                if (Mob.seccON == true) Mob.capMod?.v4check1c else null,
                if (Mob.seccON == true) Mob.capMod?.v4check1cPorcent else null,
                if (Mob.seccON == true) Mob.capMod?.v4check1d else null,
                if (Mob.seccON == true) Mob.capMod?.v4check1dPorcent else null,
                if (Mob.seccON == true) Mob.capMod?.v4check1e else null,
                if (Mob.seccON == true) Mob.capMod?.v4check1ePorcent else null,
                if (Mob.seccON == true) Mob.capMod?.v4check1eOther else null,
                if (Mob.seccON == true) Mob.capMod?.v5txt else null,
                if (Mob.seccON == true) Mob.capMod?.v6porcent1 else null,
                if (Mob.seccON == true) Mob.capMod?.v6porcent2 else null,
                if (Mob.seccON == true) Mob.capMod?.v6porcent3 else null,
                if (Mob.seccON == true) Mob.capMod?.v6porcent4 else null,
                if (Mob.seccON == true) Mob.capMod?.v7check else null,
                if (Mob.seccON == true) Mob.capMod?.v8txt else null,
                if (Mob.seccON == true) Mob.capMod?.v9check else null,
                if (Mob.seccON == true) Mob.capMod?.v9txt else null,
                if (Mob.seccON == true) Mob.capMod?.v10porcent1 else null,
                if (Mob.seccON == true) Mob.capMod?.v10porcent2 else null,
                if (Mob.seccON == true) Mob.capMod?.v10porcent3 else null,
                if (Mob.seccON == true) Mob.capMod?.v10porcent4 else null,
                if (Mob.seccON == true) Mob.obsModulo else null,
                Mob.capMod?.numControl
            )
        }
        return viewCap()
    }

    private fun viewCap(): List<String> {
        with(bindingmod1) {
            val returnList: ArrayList<String> = ArrayList()
            if (!rbtSecc011Si.isChecked && !rbtSecc011No.isChecked)
                returnList.add(CreateIncon.inconsistencia(ctx, "289") ?: "")


            Mob.isecc1 = returnList.isNotEmpty()
            println("---------Is not empty: ${Mob.isecc1}--${Mob.capMod}")
            return returnList
        }
    }
}