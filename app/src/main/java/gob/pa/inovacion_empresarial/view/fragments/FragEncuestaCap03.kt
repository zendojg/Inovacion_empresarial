package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo03Binding
import gob.pa.inovacion_empresarial.function.CreateIncon
import gob.pa.inovacion_empresarial.function.Functions
import gob.pa.inovacion_empresarial.function.Functions.toEditable
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelCap3

class FragEncuestaCap03 : Fragment() {

    private lateinit var bindingcap3: EncuestaCapitulo03Binding
    private lateinit var ctx: Context
    private lateinit var question23Map: Map<String, RadioButton>
    private lateinit var question24Map: Map<Boolean, RadioButton>
    private lateinit var question25Map: Map<String, RadioButton>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingcap3 = EncuestaCapitulo03Binding.inflate(layoutInflater)
        ctx = requireContext()

        with(bindingcap3) {
            question23Map = mapOf(
                "1" to rbtCap3231,
                "2" to rbtCap3232,
                "3" to rbtCap3233,
                "4" to rbtCap3234,
                "5" to rbtCap3235,
                "6" to rbtCap3236,
                "7" to rbtCap3237,
                "8" to rbtCap3238,
                "9" to rbtCap3239
            )
            question24Map = mapOf(
                true to rbtCap324Si,
                false to rbtCap324No
            )
            question25Map = mapOf(
                "1" to rbtCap3251,
                "2" to rbtCap3252,
                "3" to rbtCap3253,
                "4" to rbtCap3254,
                "5" to rbtCap3255,
                "6" to rbtCap3256
            )
        }
        return bindingcap3.root
    }

    override fun onResume() {
        super.onResume()
        Mob.indiceFormulario = Mob.CAP3_P04
        val infoCap = Mob.infoCap.find { it.indexCap == Mob.CAP3_P04 }
        if (infoCap?.capView == false) fillOut()
        else onAction()
    }

    private fun onAction() {
        val year22 = ArrayAdapter(ctx, R.layout.style_box, Functions.yearArray().reversed())
        year22.setDropDownViewResource(R.layout.style_list)

        with(bindingcap3) {
            scrollForm.smoothScrollTo(0,0)
            txtCap3239ly.isVisible = rbtCap3239.isChecked
            txtCap3256ly.isVisible = rbtCap3256.isChecked
            if (rbtCap324No.isChecked) linearCap324Continuar.isVisible = false

            txtCap322.setAdapter(year22)
            rgroupCap323.setOnCheckedChangeListener { _, id ->
                txtCap3239ly.isVisible = rbtCap3239.id == id
            }

            rgroupCap324.setOnCheckedChangeListener { _, id ->
                when (id) {
                    rbtCap324Si.id -> linearCap324Continuar.visibility = View.VISIBLE
                    rbtCap324No.id -> linearCap324Continuar.visibility = View.GONE
                }
            }

            rgroupCap325.setOnCheckedChangeListener { _, id ->
                if (rbtCap3256.id == id) txtCap3256ly.visibility = View.VISIBLE
                 else txtCap3256ly.visibility = View.GONE
            }

            rgroupCap325.setOnCheckedChangeListener { _, id ->
                txtCap3256ly.isVisible = rbtCap3256.id == id
            }
        }
    }

    private fun fillOut() {
        val cap3 = Mob.formComp?.cap3
        with(bindingcap3) {

            val cap3Map = mapOf(
                txtCap322 to (cap3?.v22yearNum?.toEditable() ?: "".toEditable()),
                rgroupCap323 to (question23Map[cap3?.v23natNum]),
                txtCap3239 to (cap3?.v23natdesctxt?.toEditable() ?: "".toEditable()),
                rgroupCap324 to (question24Map[cap3?.v24check]),
                rgroupCap325 to (question25Map[cap3?.v25typeNum]),
                txtCap3256 to (cap3?.v25typetxt?.toEditable() ?: "".toEditable()),
                txtCap326 to (cap3?.v26nametxt?.toEditable() ?: "".toEditable()),
                txtCap327 to (cap3?.v27countrytxt?.toEditable() ?: "".toEditable())
            )

            for ((view, value) in cap3Map) {
                when (view) {
                    is EditText -> view.text = value as Editable
                    is RadioGroup ->
                        if (value is RadioButton) value.isChecked = true
                        else view.clearCheck()
                }
            }

        }
        Mob.infoCap.find { it.indexCap == Mob.CAP3_P04 }?.capView = true
        onAction()
    }


    fun saveCap(): List<String> {
        with(bindingcap3) {
            fun indice23(radioButton: RadioButton?): String? {
                for ((indice, rb) in question23Map) if (rb == radioButton) return indice
                return null
            }
            fun indice25(radioButton: RadioButton?): String? {
                for ((indice, rb) in question25Map) if (rb == radioButton) return indice
                return null
            }

            val cap3Id = Mob.cap3?.id
            val ncontrol = Mob.cap3?.ncontrol
            val txtCap322Value = txtCap322.text.toString()
            val rbtCap323Value = indice23(root.findViewById(rgroupCap323.checkedRadioButtonId))
            val txtCap3239Value =
                if (rbtCap3239.isChecked) txtCap3239.text.toString().ifEmpty { null } else null
            val rbtCap324Value =
                if (rbtCap324Si.isChecked) true else if (rbtCap324No.isChecked) false else null
            val rbtCap325Value = indice25(root.findViewById(rgroupCap325.checkedRadioButtonId))
            val txtCap3256Value =
                if (rbtCap3256.isChecked && rbtCap324Value == true)
                    txtCap3256.text.toString().ifEmpty { null } else null
            val txtCap326Value =
                if (rbtCap324Value == true && txtCap326.text.toString().isNotEmpty())
                    txtCap326.text.toString() else null
            val txtCap327Value =
                if (rbtCap324Value == true && txtCap327.text.toString().isNotEmpty())
                    txtCap327.text.toString() else null

            Mob.cap3 = ModelCap3(
                cap3Id,
                ncontrol,
                txtCap322Value,
                rbtCap323Value,
                txtCap3239Value,
                rbtCap324Value,
                rbtCap325Value,
                txtCap3256Value,
                txtCap326Value,
                txtCap327Value
            )
            return viewCap()
        }
    }

    private fun viewCap(): List<String> {
        val returnList: ArrayList<String> = ArrayList()
        with (Mob) {
            if (cap3?.v22yearNum.isNullOrEmpty() || cap3?.v22yearNum == "0")
                returnList.add(CreateIncon.inconsistencia(ctx, "7") ?: "")
            if (cap3?.v23natNum.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "8") ?: "")
            if (cap3?.v24check == null)
                returnList.add(CreateIncon.inconsistencia(ctx, "9") ?: "")
            if (cap3?.v24check == true && cap3?.v25typeNum.isNullOrEmpty() )
                returnList.add(CreateIncon.inconsistencia(ctx, "10") ?: "")
            if (cap3?.v24check == true && cap3?.v26nametxt.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "11") ?: "")
            if (cap3?.v24check == true && cap3?.v27countrytxt.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "12") ?: "")


            infoCap.find { it.indexCap == CAP3_P04 }?.incons = returnList.isNotEmpty()
            //println("Cap3: --$cap3")
            return returnList
        }
    }

}