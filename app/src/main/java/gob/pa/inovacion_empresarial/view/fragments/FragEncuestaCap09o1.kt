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
    private var check60: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingcap9o1 = EncuestaCapitulo09Part1Binding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingcap9o1.root
    }

    override fun onResume() {
        super.onResume()
        Mob.indiceFormulario = Mob.CAP9P17
        if (Mob.seecap09o1) fillOut()
        else onAction()
    }

    private fun onAction() {
        with(bindingcap9o1) {
            if (rbtCap959No.isChecked) {
                txtCap959ly.isVisible = false
                layoutCap960.isVisible = false
                layoutCap961.isVisible = false
            }
            txtCap9606Otra.isEnabled = rbtCap9606.isChecked

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
            rgroupCap960.setOnCheckedChangeListener { _, id ->
                hideKeyboard()
                when (id) {
                    rbtCap9601.id -> {
                        txtCap9606Otra.isEnabled = false
                        check60 = "1"
                    }
                    rbtCap9602.id -> {
                        txtCap9606Otra.isEnabled = false
                        check60 = "2"
                    }
                    rbtCap9603.id -> {
                        txtCap9606Otra.isEnabled = false
                        check60 = "3"
                    }
                    rbtCap9604.id -> {
                        txtCap9606Otra.isEnabled = false
                        check60 = "4"
                    }
                    rbtCap9605.id -> {
                        txtCap9606Otra.isEnabled = false
                        check60 = "5"
                    }
                    rbtCap9606.id -> {
                        txtCap9606Otra.isEnabled = true
                        check60 = "6"
                    }
                    else -> {
                        check60 = null
                        txtCap9606Otra.isEnabled = false
                    }
                }
            }
            lowCap9o1.setOnClickListener { saveCap() }
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
            txtCap959.text = cap9?.v59num?.toEditable() ?: blank
            check60 = cap9?.v60num
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
        Mob.seecap09o1 = false
        onAction()
    }


    fun saveCap(): List<String> {
        with(bindingcap9o1) {
            Mob.cap9 = ModelCap9(
                Mob.cap9?.id,
                Mob.cap9?.ncontrol,
                if (rbtCap959Si.isChecked) true else if (rbtCap959No.isChecked) false else null,
                if (rbtCap959Si.isChecked) txtCap959.text.toString().ifEmpty { null } else null,
                if (rbtCap959Si.isChecked) check60 else null,
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
            val returnList: ArrayList<String> = ArrayList()
            if (!rbtCap959Si.isChecked && !rbtCap959No.isChecked)
                returnList.add(CreateIncon.inconsistencia(ctx, "169") ?: "")
            if (rbtCap959Si.isChecked && txtCap959.text.toString() == "0" )
                returnList.add(CreateIncon.inconsistencia(ctx, "170") ?: "")
            else if (rbtCap959Si.isChecked && txtCap959.text.toString().isEmpty() )
                returnList.add(CreateIncon.inconsistencia(ctx, "170") ?: "")


            Mob.icap09o1 = returnList.isNotEmpty()
            println("---------Is not empty: ${Mob.icap09o1}--${Mob.cap9}")
            return returnList
        }
    }
}