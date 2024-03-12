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
import gob.pa.inovacion_empresarial.function.Functions.allFalse
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
        Mob.indiceFormulario = Mob.SEC1_P20
        val infoCap = Mob.infoCap.find { it.indexCap == Mob.SEC1_P20 }
        if (infoCap?.capView == false) fillOut()
        else onAction()
    }

    private fun onAction() {
        bindingmod1.apply {
            if (rbtSecc011No.isChecked) linearSecc012.isVisible = false
            if (checkSecc012D.isChecked) txtSecc012DOtra.isVisible = true

            rgroupSecc011.setOnCheckedChangeListener { _, id ->
                hideKeyboard()
                when (id) {
                    rbtSecc011Si.id -> linearSecc012.isVisible = true
                    rbtSecc011No.id -> linearSecc012.isVisible = false
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
        }
    }

    private fun fillOut() {
        val mod1 = Mob.formComp?.capMod
        bindingmod1.apply {
            scrollForm.smoothScrollTo(0,0)
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
            txtSecc012DOtra.text = mod1?.v2txtDesc1d?.toEditable() ?: "".toEditable()
            checkSecc0122.isChecked = mod1?.v2check2 == true
        }
        Mob.infoCap.find { it.indexCap == Mob.SEC1_P20 }?.capView = true
        onAction()
    }

    fun saveCap(): List<String> {
        with (bindingmod1) {
            val check = rbtSecc011Si.isChecked
            Mob.capMod = ModelMod(
                Mob.capMod?.id,
                if (check) true else if (rbtSecc011No.isChecked) false else null,
                if (Mob.capMod?.v1check == true && rbtSecc012Si.isChecked) true else
                    if (Mob.capMod?.v1check == true && rbtSecc012No.isChecked) false else null,
                if (check &&
                    rbtSecc012Si.isChecked &&
                    checkSecc012A.isChecked) true else null,
                if (check &&
                    rbtSecc012Si.isChecked &&
                    checkSecc012B.isChecked) true else null,
                if (check &&
                    rbtSecc012Si.isChecked &&
                    checkSecc012C.isChecked) true else null,
                if (check &&
                    rbtSecc012Si.isChecked &&
                    checkSecc012D.isChecked) true else null,
                if (check &&
                    rbtSecc012Si.isChecked &&
                    checkSecc012D.isChecked)
                    txtSecc012DOtra.text.toString().ifEmpty { null } else null,
                if (check && checkSecc0122.isChecked) true else null,
                //Mob.capMod?.v2txtnull: String?,
                if (check) Mob.capMod?.v3check1 else null,//---------
                if (check) Mob.capMod?.v3check2 else null,
                if (check) Mob.capMod?.v3check3 else null,
                if (check) Mob.capMod?.v3check4 else null,
                if (check) Mob.capMod?.v4check else null,
                if (check) Mob.capMod?.v4check1a else null,
                if (check) Mob.capMod?.v4check1aPorcent else null,
                if (check) Mob.capMod?.v4check1b else null,
                if (check) Mob.capMod?.v4check1bPorcent else null,
                if (check) Mob.capMod?.v4check1c else null,
                if (check) Mob.capMod?.v4check1cPorcent else null,
                if (check) Mob.capMod?.v4check1d else null,
                if (check) Mob.capMod?.v4check1dPorcent else null,
                if (check) Mob.capMod?.v4check1e else null,
                if (check) Mob.capMod?.v4check1ePorcent else null,
                if (check) Mob.capMod?.v4check1eOther else null,
                if (check) Mob.capMod?.v5txt else null,
                if (check) Mob.capMod?.v6porcent1 else null,
                if (check) Mob.capMod?.v6porcent2 else null,
                if (check) Mob.capMod?.v6porcent3 else null,
                if (check) Mob.capMod?.v6porcent4 else null,
                if (check) Mob.capMod?.v7check else null,
                if (check) Mob.capMod?.v8txt else null,
                if (check) Mob.capMod?.v9check else null,
                if (check) Mob.capMod?.v9txt else null,
                if (check) Mob.capMod?.v10porcent1 else null,
                if (check) Mob.capMod?.v10porcent2 else null,
                if (check) Mob.capMod?.v10porcent3 else null,
                if (check) Mob.capMod?.v10porcent4 else null,
                if (check) Mob.obsModulo else null,
                Mob.capMod?.numControl
            )
        }
        return viewCap()
    }

    private fun viewCap(): List<String> {

        with(bindingmod1) {
            val allCheckSecc01P2 = listOf(
                checkSecc012A.isChecked,
                checkSecc012B.isChecked,
                checkSecc012C.isChecked,
                checkSecc012D.isChecked,
                )
            val returnList: ArrayList<String> = ArrayList()
            if (rgroupSecc011.checkedRadioButtonId == -1)
                returnList.add(CreateIncon.inconsistencia(ctx, "289") ?: "")
            if (rbtSecc011Si.isChecked) {
                if (rgroupSecc0121.checkedRadioButtonId == -1)
                    returnList.add(CreateIncon.inconsistencia(ctx, "290") ?: "")
                else if (rbtSecc012Si.isChecked && allCheckSecc01P2.allFalse())
                    returnList.add(CreateIncon.inconsistencia(ctx, "291") ?: "")
                else if (rbtSecc012No.isChecked && !checkSecc0122.isChecked)
                    returnList.add(CreateIncon.inconsistencia(ctx, "292") ?: "")
            }
            Mob.infoCap.find { it.indexCap == Mob.SEC1_P20 }?.incons = returnList.isNotEmpty()
            return returnList
        }
    }
}