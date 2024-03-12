package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo09Part2Binding
import gob.pa.inovacion_empresarial.function.CreateIncon
import gob.pa.inovacion_empresarial.function.Functions
import gob.pa.inovacion_empresarial.function.Functions.allFalse
import gob.pa.inovacion_empresarial.function.Functions.hideKeyboard
import gob.pa.inovacion_empresarial.function.Functions.toEditable
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelCap9
import gob.pa.inovacion_empresarial.view.FormActivity


class FragEncuestaCap09o2 : Fragment() {

    private lateinit var bindingcap9o2: EncuestaCapitulo09Part2Binding
    private lateinit var ctx: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingcap9o2 = EncuestaCapitulo09Part2Binding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingcap9o2.root
    }

    override fun onResume() {
        super.onResume()
        Mob.indiceFormulario = Mob.CAP9_P18
        val infoCap = Mob.infoCap.find { it.indexCap == Mob.CAP9_P18 }
        if (infoCap?.capView == false) fillOut()
        else onAction()
    }

    private fun onAction() {
        with(bindingcap9o2) {
            if (rbtCap962No.isChecked) layoutCap963.isVisible = false

            rgroupCap962.setOnCheckedChangeListener { _, id ->
                hideKeyboard()
                when (id) {
                    rbtCap962Si.id -> layoutCap963.isVisible = true
                    rbtCap962No.id -> layoutCap963.isVisible = false
                }
            }

            txtCap9631.isVisible = checkCap9631.isChecked
            txtCap9632.isVisible = checkCap9632.isChecked
            txtCap9633.isVisible = checkCap9633.isChecked
            txtCap9634.isVisible = checkCap9634.isChecked
            txtCap9635.isVisible = checkCap9635.isChecked
            if (!checkCap9635.isChecked) txtCap9635Otro.text?.clear()

            checkCap9631.setOnClickListener { txtCap9631.isVisible = checkCap9631.isChecked }
            checkCap9632.setOnClickListener { txtCap9632.isVisible = checkCap9632.isChecked }
            checkCap9633.setOnClickListener { txtCap9633.isVisible = checkCap9633.isChecked }
            checkCap9634.setOnClickListener { txtCap9634.isVisible = checkCap9634.isChecked }
            checkCap9635.setOnClickListener {
                txtCap9635.isVisible = checkCap9635.isChecked
                txtCap9635Otro.isEnabled = checkCap9635.isChecked
                if (!checkCap9635.isChecked) txtCap9635Otro.text?.clear()
            }

            if (rbtCap964No.isChecked) layoutCap965.isVisible = false

            rgroupCap964.setOnCheckedChangeListener { _, id ->
                hideKeyboard()
                when (id) {
                    rbtCap964Si.id -> layoutCap965.isVisible = true
                    rbtCap964No.id -> layoutCap965.isVisible = false
                }
            }
            val year65 = ArrayAdapter(ctx, R.layout.style_box, Functions.yearArray().reversed())
            year65.setDropDownViewResource(R.layout.style_list)
            txtCap9651Year.setAdapter(year65)
            txtCap9652Year.setAdapter(year65)

            lowCap9o2.setOnClickListener { saveCap() }
        }
    }

    private fun fillOut() {
        val cap9 = Mob.formComp?.cap9
        val blank = "".toEditable()
        with(bindingcap9o2) {
            scrollForm.smoothScrollTo(0,0)
            when (cap9?.v62check) {
                true -> rbtCap962Si.isChecked = true
                false -> rbtCap962No.isChecked = true
                else -> rgroupCap962.clearCheck()
            }
            if (!cap9?.v63num1.isNullOrEmpty()) {
                checkCap9631.isChecked = true
                txtCap9631.text = cap9?.v63num1?.toEditable()
            } else {
                checkCap9631.isChecked = false
                txtCap9631.text.clear()
            }
            if (!cap9?.v63num2.isNullOrEmpty()) {
                checkCap9632.isChecked = true
                txtCap9632.text = cap9?.v63num2?.toEditable()
            } else {
                checkCap9632.isChecked = false
                txtCap9632.text.clear()
            }
            if (!cap9?.v63num3.isNullOrEmpty()) {
                checkCap9633.isChecked = true
                txtCap9633.text = cap9?.v63num3?.toEditable()
            } else {
                checkCap9633.isChecked = false
                txtCap9633.text.clear()
            }
            if (!cap9?.v63num4.isNullOrEmpty()) {
                checkCap9634.isChecked = true
                txtCap9634.text = cap9?.v63num4?.toEditable()
            } else {
                checkCap9634.isChecked = false
                txtCap9634.text.clear()
            }
            if (!cap9?.v63num5.isNullOrEmpty()) {
                checkCap9635.isChecked = true
                txtCap9635Otro.text = cap9?.v63des5?.toEditable() ?: blank
                txtCap9635.text = cap9?.v63num5?.toEditable()
            } else {
                checkCap9635.isChecked = false
                txtCap9635Otro.text?.clear()
                txtCap9635.text.clear()
            }

            when (cap9?.v64check) {
                true -> rbtCap964Si.isChecked = true
                false -> rbtCap964No.isChecked = true
                else -> rgroupCap964.clearCheck()
            }

            txtCap9651.text = cap9?.v65txt1?.toEditable() ?: blank
            txtCap9651Year.setText((cap9?.v65num1?.toEditable() ?: blank),false)

            txtCap9652.text = cap9?.v65txt2?.toEditable() ?: blank
            txtCap9652Year.setText((cap9?.v65num2?.toEditable() ?: blank),false)
        }
        Mob.infoCap.find { it.indexCap == Mob.CAP9_P18 }?.capView = true
        onAction()
    }




    fun saveCap(): List<String> {
        with(bindingcap9o2) {
            Mob.cap9 = ModelCap9(
                Mob.cap9?.id,
                Mob.cap9?.ncontrol,
                Mob.cap9?.v59check,
                Mob.cap9?.v59num,
                //Mob.cap9?.v60num,

                Mob.cap9?.v60check1,
                Mob.cap9?.v60check2,
                Mob.cap9?.v60check3,
                Mob.cap9?.v60check4,
                Mob.cap9?.v60check5,
                Mob.cap9?.v60check6,

                Mob.cap9?.v60txtotro,
                Mob.cap9?.v61check1,
                Mob.cap9?.v61check2,
                Mob.cap9?.v61check3,
                Mob.cap9?.v61check4,
                Mob.cap9?.v61check5,
                Mob.cap9?.v61check6,
                Mob.cap9?.v61check7,
                Mob.cap9?.v61check8,//--
                if (rbtCap962Si.isChecked) true else if (rbtCap962No.isChecked) false else null,
                if (rbtCap962Si.isChecked && checkCap9631.isChecked)
                    txtCap9631.text.toString().ifEmpty { null } else null,
                if (rbtCap962Si.isChecked && checkCap9632.isChecked)
                    txtCap9632.text.toString().ifEmpty { null } else null,
                if (rbtCap962Si.isChecked && checkCap9633.isChecked)
                    txtCap9633.text.toString().ifEmpty { null } else null,
                if (rbtCap962Si.isChecked && checkCap9634.isChecked)
                    txtCap9634.text.toString().ifEmpty { null } else null,
                if (rbtCap962Si.isChecked && checkCap9635.isChecked)
                    txtCap9635.text.toString().ifEmpty { null } else null,
                if (rbtCap962Si.isChecked && checkCap9635.isChecked)
                    txtCap9635Otro.text.toString().ifEmpty { null } else null,
                if (rbtCap964Si.isChecked) true else if (rbtCap964No.isChecked) false else null,

                if (rbtCap964Si.isChecked) txtCap9651.text.toString().ifEmpty { null } else null,
                if (rbtCap964Si.isChecked) txtCap9651Year.text.toString()
                    .ifEmpty { null } else null,
                if (rbtCap964Si.isChecked) txtCap9652.text.toString().ifEmpty { null } else null,
                if (rbtCap964Si.isChecked) txtCap9652Year.text.toString()
                    .ifEmpty { null } else null,
            )

        }
        return viewCap()
    }

    private fun viewCap(): List<String> {
        with(bindingcap9o2) {
            val returnList: ArrayList<String> = ArrayList()
            val p63checks = listOf(
                checkCap9631.isChecked, checkCap9632.isChecked,
                checkCap9633.isChecked, checkCap9634.isChecked)

            if (rgroupCap962.checkedRadioButtonId == -1)
                returnList.add(CreateIncon.inconsistencia(ctx, "180") ?: "")
            else if (rbtCap962Si.isChecked) {
                if (p63checks.allFalse())
                    returnList.add(CreateIncon.inconsistencia(ctx, "181") ?: "")
                else {
                    if (checkCap9631.isChecked && txtCap9631.text.isNullOrEmpty())
                        returnList.add(CreateIncon.inconsistencia(ctx, "182") ?: "")
                    if (checkCap9632.isChecked && txtCap9632.text.isNullOrEmpty())
                        returnList.add(CreateIncon.inconsistencia(ctx, "183") ?: "")
                    if (checkCap9633.isChecked && txtCap9633.text.isNullOrEmpty())
                        returnList.add(CreateIncon.inconsistencia(ctx, "184") ?: "")
                    if (checkCap9634.isChecked && txtCap9634.text.isNullOrEmpty())
                        returnList.add(CreateIncon.inconsistencia(ctx, "185") ?: "")
                    if (checkCap9635.isChecked && txtCap9635.text.isNullOrEmpty())
                        returnList.add(CreateIncon.inconsistencia(ctx, "186") ?: "")
                }
            }
            if (rgroupCap964.checkedRadioButtonId == -1)
                returnList.add(CreateIncon.inconsistencia(ctx, "187") ?: "")
            else if (rbtCap964Si.isChecked) {
                if (txtCap9651.text.isNullOrEmpty() && txtCap9652.text.isNullOrEmpty()) {
                    returnList.add(CreateIncon.inconsistencia(ctx, "188") ?: "")
                } else
                    if (txtCap9651Year.text.isNullOrEmpty() && !txtCap9651.text.isNullOrEmpty())
                        returnList.add(CreateIncon.inconsistencia(ctx, "189") ?: "")
                    else if (txtCap9652Year.text.isNullOrEmpty() && !txtCap9652.text.isNullOrEmpty())
                        returnList.add(CreateIncon.inconsistencia(ctx, "190") ?: "")

            }
            Mob.infoCap.find { it.indexCap == Mob.CAP9_P18 }?.incons = returnList.isNotEmpty()
            //println("Cap9-part2: --${Mob.cap9}")
            return returnList
        }
    }
}