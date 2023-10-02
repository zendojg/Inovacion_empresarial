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
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo03Binding
import gob.pa.inovacion_empresarial.function.CreateIncon
import gob.pa.inovacion_empresarial.function.Functions
import gob.pa.inovacion_empresarial.function.Functions.toEditable
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelCap3

class FragEncuestaCap03 : Fragment() {

    private lateinit var bindingcap3: EncuestaCapitulo03Binding
    private lateinit var ctx: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingcap3 = EncuestaCapitulo03Binding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingcap3.root
    }

    override fun onResume() {
        super.onResume()
        Mob.indiceFormulario = Mob.CAP3P04
        if (Mob.seecap03) fillOut()
        else onAction()
    }

    private fun onAction() {
        val year22 = ArrayAdapter(ctx, R.layout.style_box, Functions.yearArray().reversed())
        year22.setDropDownViewResource(R.layout.style_list)

        with(bindingcap3) {
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
            txtCap322.text = cap3?.v22yearNum?.toEditable() ?: "".toEditable()

            when (cap3?.v23natNum) {
                "1" -> rbtCap3231.isChecked = true
                "2" -> rbtCap3232.isChecked = true
                "3" -> rbtCap3233.isChecked = true
                "4" -> rbtCap3234.isChecked = true
                "5" -> rbtCap3235.isChecked = true
                "6" -> rbtCap3236.isChecked = true
                "7" -> rbtCap3237.isChecked = true
                "8" -> rbtCap3238.isChecked = true
                "9" -> rbtCap3239.isChecked = true
                else -> rgroupCap323.clearCheck()
            }
            txtCap3239.text = cap3?.v23natdesctxt?.toEditable() ?: "".toEditable()

            when (cap3?.v24check) {
                true -> rbtCap324Si.isChecked = true
                false -> rbtCap324No.isChecked = true
                else -> rgroupCap324.clearCheck()
            }

            when (cap3?.v25typeNum) {
                "1" -> rbtCap3251.isChecked = true
                "2" -> rbtCap3252.isChecked = true
                "3" -> rbtCap3253.isChecked = true
                "4" -> rbtCap3254.isChecked = true
                "5" -> rbtCap3255.isChecked = true
                "6" -> rbtCap3256.isChecked = true
                else -> rgroupCap325.clearCheck()
            }

            txtCap3256.text = cap3?.v25typetxt?.toEditable() ?: "".toEditable()
            txtCap326.text = cap3?.v26nametxt?.toEditable() ?: "".toEditable()
            txtCap327.text = cap3?.v27countrytxt?.toEditable() ?: "".toEditable()
        }
        Mob.seecap03 = false
        onAction()
    }

    fun saveCap(): List<String> {
        with(bindingcap3) {
            val cap3Id = Mob.cap3?.id
            val ncontrol = Mob.cap3?.ncontrol
            val txtCap322Value = txtCap322.text.toString()
            val rbtCap323Value = when {
                rbtCap3231.isChecked -> "1"
                rbtCap3232.isChecked -> "2"
                rbtCap3233.isChecked -> "3"
                rbtCap3234.isChecked -> "4"
                rbtCap3235.isChecked -> "5"
                rbtCap3236.isChecked -> "6"
                rbtCap3237.isChecked -> "7"
                rbtCap3238.isChecked -> "8"
                rbtCap3239.isChecked -> "9"
                else -> null
            }
            val txtCap3239Value =
                if (rbtCap3239.isChecked) txtCap3239.text.toString().ifEmpty { null } else null
            val rbtCap324Value =
                if (rbtCap324Si.isChecked) true else if (rbtCap324No.isChecked) false else null
            val rbtCap325Value = if (rbtCap324Si.isChecked) when {
                rbtCap3251.isChecked -> "1"
                rbtCap3252.isChecked -> "2"
                rbtCap3253.isChecked -> "3"
                rbtCap3254.isChecked -> "4"
                rbtCap3255.isChecked -> "5"
                rbtCap3256.isChecked -> "6"
                else -> null
            } else null

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
            icap03 = returnList.isNotEmpty()
            println("Cap3: incon:$icap03--$cap3")
            return returnList
        }
    }

}