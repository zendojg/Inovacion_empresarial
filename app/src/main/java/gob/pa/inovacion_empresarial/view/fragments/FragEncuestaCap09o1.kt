package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo09Part1Binding
import gob.pa.inovacion_empresarial.function.CreateIncon
import gob.pa.inovacion_empresarial.function.Functions.hideKeyboard
import gob.pa.inovacion_empresarial.function.Functions.toEditable
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelCap9


class FragEncuestaCap09o1 : Fragment() {

    private lateinit var bindingcap9o1: EncuestaCapitulo09Part1Binding
    private lateinit var ctx: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingcap9o1 = EncuestaCapitulo09Part1Binding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingcap9o1.root
    }

    override fun onResume() {
        super.onResume()
        Mob.indiceFormulario = Mob.CAP9_P17
        val infoCap = Mob.infoCap.find { it.indexCap == Mob.CAP9_P17 }
        if (infoCap?.capView == false) fillOut()
        else onAction()
    }

    private fun onAction() {
        with(bindingcap9o1) {
            scrollForm.smoothScrollTo(0,0)
            if (rbtCap959No.isChecked) {
                txtCap959ly.isVisible = false
                layoutCap960.isVisible = false
                layoutCap961.isVisible = false
            } else if (rbtCap959Si.isChecked) txtCap959ly.isVisible = true

            txtCap9606Otra.isEnabled = rbtCap9606.isChecked
            if (rbtCap9606.isChecked) {
                txtCap9606Otra.isEnabled
                txtCap9606Otra
            }
            else {
                txtCap9606Otra.text.clear()
                txtCap9606Otra.isEnabled = false
            }
            rgroupCap959.setOnCheckedChangeListener { _, id ->
                hideKeyboard()
                when (id) {
                    rbtCap959Si.id -> {
                        txtCap959ly.isVisible = true
                        layoutCap960.isVisible = true
                        layoutCap961.isVisible = true }
                    rbtCap959No.id -> {
                        txtCap959ly.isVisible = false
                        layoutCap960.isVisible = false
                        layoutCap961.isVisible = false }
                }
            }
            lowCap9o1.setOnClickListener { saveCap() }
        }
    }

    private fun fillOut() {
        val cap9 = Mob.formComp?.cap9
        val blank = "".toEditable()
        with(bindingcap9o1) {

            txtCap959.text = cap9?.v59num?.toEditable() ?: blank

            val buttonsMap60 = mapOf(
                rbtCap9601 to cap9?.v60check1,
                rbtCap9602 to cap9?.v60check2,
                rbtCap9603 to cap9?.v60check3,
                rbtCap9604 to cap9?.v60check4,
                rbtCap9605 to cap9?.v60check5,
                rbtCap9606 to cap9?.v60check6
            )
            for ((checkBt, isChecked) in buttonsMap60) {
                when (isChecked) {
                    true -> checkBt.isChecked = true
                    false -> checkBt.isChecked = false
                    else -> checkBt.isChecked = false
                }
            }

            txtCap9606Otra.text = cap9?.v60txtotro?.toEditable() ?: blank

            val buttonsMap61 = mapOf(
                rgroupCap959 to cap9?.v59check,
                rgroupCap9611 to cap9?.v61check1,
                rgroupCap9612 to cap9?.v61check2,
                rgroupCap9613 to cap9?.v61check3,
                rgroupCap9614 to cap9?.v61check4,
                rgroupCap9615 to cap9?.v61check5,
                rgroupCap9616 to cap9?.v61check6,
                rgroupCap9617 to cap9?.v61check7,
                rgroupCap9618 to cap9?.v61check8
            )
            for ((radioGroup, isChecked) in buttonsMap61) {
                when (isChecked) {
                    true -> radioGroup.check(radioGroup.getChildAt(0).id)
                    false -> radioGroup.check(radioGroup.getChildAt(1).id)
                    else -> radioGroup.clearCheck()
                }
            }
        }
        Mob.infoCap.find { it.indexCap == Mob.CAP9_P17 }?.capView = true
        onAction()
    }


    fun saveCap(): List<String> {
        with(bindingcap9o1) {
            Mob.cap9 = ModelCap9(
                Mob.cap9?.id,
                Mob.cap9?.ncontrol,
                if (rbtCap959Si.isChecked) true else if (rbtCap959No.isChecked) false else null,
                if (rbtCap959Si.isChecked) txtCap959.text.toString().ifEmpty { null } else null,

                //Mob.cap9?.v60num,
                if (rbtCap959Si.isChecked) rbtCap9601.isChecked else null,
                if (rbtCap959Si.isChecked) rbtCap9602.isChecked else null,
                if (rbtCap959Si.isChecked) rbtCap9603.isChecked else null,
                if (rbtCap959Si.isChecked) rbtCap9604.isChecked else null,
                if (rbtCap959Si.isChecked) rbtCap9605.isChecked else null,
                if (rbtCap959Si.isChecked) rbtCap9606.isChecked else null,
                if (rbtCap959Si.isChecked && rbtCap9606.isChecked) txtCap9606Otra.text.toString()
                else null,

                if (rbtCap959Si.isChecked && rbtCap9611Si.isChecked) true else
                    if (rbtCap959Si.isChecked && rbtCap9611No.isChecked) false else null,
                if (rbtCap959Si.isChecked && rbtCap9612Si.isChecked) true else
                    if (rbtCap959Si.isChecked && rbtCap9612No.isChecked) false else null,
                if (rbtCap959Si.isChecked && rbtCap9613Si.isChecked) true else
                    if (rbtCap959Si.isChecked && rbtCap9613No.isChecked) false else null,
                if (rbtCap959Si.isChecked && rbtCap9614Si.isChecked) true else
                    if (rbtCap959Si.isChecked && rbtCap9614No.isChecked) false else null,
                if (rbtCap959Si.isChecked && rbtCap9615Si.isChecked) true else
                    if (rbtCap959Si.isChecked && rbtCap9615No.isChecked) false else null,
                if (rbtCap959Si.isChecked && rbtCap9616Si.isChecked) true else
                    if (rbtCap959Si.isChecked && rbtCap9616No.isChecked) false else null,
                if (rbtCap959Si.isChecked && rbtCap9617Si.isChecked) true else
                    if (rbtCap959Si.isChecked && rbtCap9617No.isChecked) false else null,
                if (rbtCap959Si.isChecked && rbtCap9618Si.isChecked) true else
                    if (rbtCap959Si.isChecked && rbtCap9618No.isChecked) false else null,
                Mob.cap9?.v62check,//--
                Mob.cap9?.v63num1,
                Mob.cap9?.v63num2,
                Mob.cap9?.v63num3,
                Mob.cap9?.v63num4,
                Mob.cap9?.v63num5,
                Mob.cap9?.v63des5,
                Mob.cap9?.v64check,
                Mob.cap9?.v65txt1,
                Mob.cap9?.v65num1,
                Mob.cap9?.v65txt2,
                Mob.cap9?.v65num2,
            )
        }
        return viewCap()
    }

    private fun viewCap(): List<String> {
        with(bindingcap9o1) {
            val p60 = listOf(
                rbtCap9601.isChecked,
                rbtCap9602.isChecked,
                rbtCap9603.isChecked,
                rbtCap9604.isChecked,
                rbtCap9605.isChecked,
                rbtCap9606.isChecked
            )
            val returnList: ArrayList<String> = ArrayList()
            if (rgroupCap959.checkedRadioButtonId == -1)
                returnList.add(CreateIncon.inconsistencia(ctx, "169") ?: "")
            if (rbtCap959Si.isChecked) {
                if (txtCap959.text.toString() == "0")
                    returnList.add(CreateIncon.inconsistencia(ctx, "170") ?: "")
                else if (rbtCap959Si.isChecked && txtCap959.text.toString().isEmpty())
                    returnList.add(CreateIncon.inconsistencia(ctx, "170") ?: "")

                if (rbtCap959Si.isChecked && !p60.contains(true))
                    returnList.add(CreateIncon.inconsistencia(ctx, "171") ?: "")

                if (rgroupCap9611.checkedRadioButtonId == -1)
                    returnList.add(CreateIncon.inconsistencia(ctx, "172") ?: "")
                if (rgroupCap9612.checkedRadioButtonId == -1)
                    returnList.add(CreateIncon.inconsistencia(ctx, "173") ?: "")
                if (rgroupCap9613.checkedRadioButtonId == -1)
                    returnList.add(CreateIncon.inconsistencia(ctx, "174") ?: "")
                if (rgroupCap9614.checkedRadioButtonId == -1)
                    returnList.add(CreateIncon.inconsistencia(ctx, "175") ?: "")
                if (rgroupCap9615.checkedRadioButtonId == -1)
                    returnList.add(CreateIncon.inconsistencia(ctx, "176") ?: "")
                if (rgroupCap9616.checkedRadioButtonId == -1)
                    returnList.add(CreateIncon.inconsistencia(ctx, "177") ?: "")
                if (rgroupCap9617.checkedRadioButtonId == -1)
                    returnList.add(CreateIncon.inconsistencia(ctx, "178") ?: "")
                if (rgroupCap9618.checkedRadioButtonId == -1)
                    returnList.add(CreateIncon.inconsistencia(ctx, "179") ?: "")
            }
            Mob.infoCap.find { it.indexCap == Mob.CAP9_P17 }?.incons = returnList.isNotEmpty()
            //println("Cap9-part1: --${Mob.cap9}")
            return returnList
        }
    }
}