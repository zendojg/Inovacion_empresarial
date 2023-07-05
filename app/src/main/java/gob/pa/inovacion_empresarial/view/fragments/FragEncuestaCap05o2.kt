package gob.pa.inovacion_empresarial.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.function.Functions.hideKeyboard
import kotlinx.android.synthetic.main.encuesta_capitulo_05_2_recursos_humanos.view.*

class FragEncuestaCap05o2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val viewCap05o2 = inflater.inflate(R.layout.encuesta_capitulo_05_2_recursos_humanos, container, false)


        viewCap05o2.rgroupCap5_37.setOnCheckedChangeListener { _, id ->
            when (id) {
                viewCap05o2.rbtCap5_37_si.id -> {
                    viewCap05o2.layoutCap5_38.visibility = View.VISIBLE }
                viewCap05o2.rbtCap5_37_no.id -> {
                    hideKeyboard()
                    viewCap05o2.layoutCap5_38.visibility = View.GONE
                    viewCap05o2.checkCap5_38_1.isChecked = false
                    viewCap05o2.checkCap5_38_2.isChecked = false
                    viewCap05o2.checkCap5_38_3.isChecked = false
                    viewCap05o2.checkCap5_38_4.isChecked = false

                    viewCap05o2.txtCap5_38_1.text.clear()
                    viewCap05o2.txtCap5_38_2.text.clear()
                    viewCap05o2.txtCap5_38_3.text.clear()
                    viewCap05o2.txtCap5_38_4.text.clear()
                    viewCap05o2.txtCap5_38_4_otro.text?.clear()

                }
            }
        }

        viewCap05o2.checkCap5_38_1.setOnClickListener {
            viewCap05o2.txtCap5_38_1.isEnabled = viewCap05o2.checkCap5_38_1.isChecked
            if (viewCap05o2.checkCap5_38_1.isChecked)
                 viewCap05o2.txtCap5_38_1.visibility = View.VISIBLE
            else viewCap05o2.txtCap5_38_1.visibility = View.INVISIBLE
        }
        viewCap05o2.checkCap5_38_2.setOnClickListener {
            viewCap05o2.txtCap5_38_2.isEnabled = viewCap05o2.checkCap5_38_2.isChecked
            if (viewCap05o2.checkCap5_38_2.isChecked)
                 viewCap05o2.txtCap5_38_2.visibility = View.VISIBLE
            else viewCap05o2.txtCap5_38_2.visibility = View.INVISIBLE
        }
        viewCap05o2.checkCap5_38_3.setOnClickListener {
            viewCap05o2.txtCap5_38_3.isEnabled = viewCap05o2.checkCap5_38_3.isChecked
            if (viewCap05o2.checkCap5_38_3.isChecked)
                 viewCap05o2.txtCap5_38_3.visibility = View.VISIBLE
            else viewCap05o2.txtCap5_38_3.visibility = View.INVISIBLE
        }
        viewCap05o2.checkCap5_38_4.setOnClickListener {
            viewCap05o2.txtCap5_38_4.isEnabled = viewCap05o2.checkCap5_38_4.isChecked
            viewCap05o2.txtCap5_38_4_otro.isEnabled = viewCap05o2.checkCap5_38_4.isChecked
            if (viewCap05o2.checkCap5_38_4.isChecked)
                viewCap05o2.txtCap5_38_4.visibility = View.VISIBLE
            else {
                viewCap05o2.txtCap5_38_4.visibility = View.INVISIBLE
                viewCap05o2.txtCap5_38_4_otro.text?.clear()
            }
        }


        return viewCap05o2
    }

}