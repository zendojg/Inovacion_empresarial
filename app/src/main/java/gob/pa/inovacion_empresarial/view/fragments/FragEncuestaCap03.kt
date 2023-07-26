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
import gob.pa.inovacion_empresarial.function.Functions
import gob.pa.inovacion_empresarial.function.Functions.toEditable
import gob.pa.inovacion_empresarial.model.Mob

class FragEncuestaCap03 : Fragment() {

    private lateinit var bindingcap3: EncuestaCapitulo03Binding
    private lateinit var ctx: Context
    private var seecap = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingcap3 = EncuestaCapitulo03Binding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingcap3.root
    }

    override fun onResume() {
        super.onResume()
        Mob.indiceEnc = Mob.CAP3P04
        if (seecap) fillOut()
        else onAction()
    }

    private fun onAction() {
        val year22 = ArrayAdapter(ctx, R.layout.style_box, Functions.yearArray().reversed())
        year22.setDropDownViewResource(R.layout.style_list)

        with(bindingcap3) {
            txtCap3239.isVisible = rbtCap3239.isChecked
            txtCap3256.isVisible = rbtCap3256.isChecked
            linearCap324Continuar.isVisible = rbtCap324Si.isChecked

            txtCap322.setAdapter(year22)
            rgroupCap323.setOnCheckedChangeListener { _, id ->
                txtCap3239.isVisible = rbtCap3239.id == id
            }
            rgroupCap324.setOnCheckedChangeListener { _, id ->
                when (id) {
                    rbtCap324Si.id -> linearCap324Continuar.visibility = View.VISIBLE
                    rbtCap324No.id -> linearCap324Continuar.visibility = View.GONE
                }
            }
            rgroupCap325.setOnCheckedChangeListener { _, id ->
                txtCap3256.isVisible = rbtCap3256.id == id
            }

            lowCap3.setOnClickListener { saveCap() }
        }
    }

    private fun fillOut() {
        val cap3 = Mob.formComp?.cap3
        val blank = "".toEditable()
        with(bindingcap3) {
            txtCap322.text = cap3?.v22yearNum?.toEditable() ?: blank
            fillOut23(cap3?.v23natNum)
            txtCap3239.text = cap3?.v23natdesctxt?.toEditable() ?: blank
            when (cap3?.v24check) {
                true -> rbtCap324Si.isChecked = true
                false -> rbtCap324No.isChecked = true
                else -> rgroupCap324.clearCheck()
            }
            fillOut25(cap3?.v25typeNum)
            txtCap3256.text = cap3?.v25typetxt?.toEditable() ?: blank
            txtCap326.text = cap3?.v26nametxt?.toEditable() ?: blank
            txtCap327.text = cap3?.v27countrytxt?.toEditable() ?: blank
        }
        seecap = false
        onAction()
    }

    private fun fillOut23(check: String?) {
        with(bindingcap3) {
            when (check) {
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
        }
    }

    private fun fillOut25(check: String?) {
        with(bindingcap3) {
            when (check) {
                "1" -> rbtCap3251.isChecked = true
                "2" -> rbtCap3252.isChecked = true
                "3" -> rbtCap3253.isChecked = true
                "4" -> rbtCap3254.isChecked = true
                "5" -> rbtCap3255.isChecked = true
                "6" -> rbtCap3256.isChecked = true
                else -> rgroupCap325.clearCheck()
            }
        }
    }


    fun saveCap() {}
}