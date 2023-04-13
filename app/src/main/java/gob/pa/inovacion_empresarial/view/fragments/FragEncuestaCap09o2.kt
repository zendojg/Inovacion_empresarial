package gob.pa.inovacion_empresarial.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.R
import kotlinx.android.synthetic.main.encuesta_capitulo_05_2_recursos_humanos.view.*
import kotlinx.android.synthetic.main.encuesta_capitulo_09_part1.view.*
import kotlinx.android.synthetic.main.encuesta_capitulo_09_part2.view.*

class FragEncuestaCap09o2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val viewCap09o2 = inflater.inflate(R.layout.encuesta_capitulo_09_part2, container, false)


        viewCap09o2.rgroupCap9_62.setOnCheckedChangeListener { _, id ->
            when (id) {
                viewCap09o2.rbtCap9_62_si.id -> {
                    viewCap09o2.linearCap9_63.visibility = View.VISIBLE
                }
                viewCap09o2.rbtCap9_62_no.id -> {
                    viewCap09o2.linearCap9_63.visibility = View.GONE

                    viewCap09o2.checkCap9_63_1.isChecked = false
                    viewCap09o2.checkCap9_63_2.isChecked = false
                    viewCap09o2.checkCap9_63_3.isChecked = false
                    viewCap09o2.checkCap9_63_4.isChecked = false
                    viewCap09o2.checkCap9_63_5.isChecked = false

                    viewCap09o2.txtCap9_63_1_nlocal.text.clear()
                    viewCap09o2.txtCap9_63_2_nlocal.text.clear()
                    viewCap09o2.txtCap9_63_3_nlocal.text.clear()
                    viewCap09o2.txtCap9_63_4_nlocal.text.clear()
                    viewCap09o2.txtCap9_63_5_nlocal.text.clear()
                    viewCap09o2.txtCap9_63_5_otra.text.clear()

                }
            }
        }

        viewCap09o2.rgroupCap9_64.setOnCheckedChangeListener { _, id ->
            when (id) {
                viewCap09o2.rbtCap9_64_si.id -> {
                    viewCap09o2.linearCap9_65.visibility = View.VISIBLE
                }
                viewCap09o2.rbtCap9_64_no.id -> {
                    viewCap09o2.linearCap9_65.visibility = View.GONE

                    viewCap09o2.txtCap9_65_1.text.clear()
                    viewCap09o2.txtCap9_65_2.text.clear()

                    viewCap09o2.spinnerCap9_65_1.setSelection(0)
                    viewCap09o2.spinnerCap9_65_2.setSelection(0)

                }
            }
        }



        viewCap09o2.checkCap9_63_1.setOnClickListener {
            viewCap09o2.txtCap9_63_1_nlocal.isEnabled = viewCap09o2.checkCap9_63_1.isChecked
            if (viewCap09o2.checkCap9_63_1.isChecked)
                viewCap09o2.txtCap9_63_1_nlocal.visibility = View.VISIBLE
            else viewCap09o2.txtCap9_63_1_nlocal.visibility = View.INVISIBLE
        }
        viewCap09o2.checkCap9_63_2.setOnClickListener {
            viewCap09o2.txtCap9_63_2_nlocal.isEnabled = viewCap09o2.checkCap9_63_2.isChecked
            if (viewCap09o2.checkCap9_63_2.isChecked)
                viewCap09o2.txtCap9_63_2_nlocal.visibility = View.VISIBLE
            else viewCap09o2.txtCap9_63_2_nlocal.visibility = View.INVISIBLE
        }
        viewCap09o2.checkCap9_63_3.setOnClickListener {
            viewCap09o2.txtCap9_63_3_nlocal.isEnabled = viewCap09o2.checkCap9_63_3.isChecked
            if (viewCap09o2.checkCap9_63_3.isChecked)
                viewCap09o2.txtCap9_63_3_nlocal.visibility = View.VISIBLE
            else viewCap09o2.txtCap9_63_3_nlocal.visibility = View.INVISIBLE
        }
        viewCap09o2.checkCap9_63_4.setOnClickListener {
            viewCap09o2.txtCap9_63_4_nlocal.isEnabled = viewCap09o2.checkCap9_63_4.isChecked
            if (viewCap09o2.checkCap9_63_4.isChecked)
                viewCap09o2.txtCap9_63_4_nlocal.visibility = View.VISIBLE
            else viewCap09o2.txtCap9_63_4_nlocal.visibility = View.INVISIBLE
        }
        viewCap09o2.checkCap9_63_5.setOnClickListener {
            viewCap09o2.txtCap9_63_5_nlocal.isEnabled = viewCap09o2.checkCap9_63_5.isChecked
            viewCap09o2.txtCap9_63_5_otra.isEnabled = viewCap09o2.checkCap9_63_5.isChecked
            if (viewCap09o2.checkCap9_63_5.isChecked)
                viewCap09o2.txtCap9_63_5_nlocal.visibility = View.VISIBLE
            else {
                viewCap09o2.txtCap9_63_5_nlocal.visibility = View.INVISIBLE
                viewCap09o2.txtCap9_63_5_otra.text.clear()
            }
        }



        return viewCap09o2
    }

}