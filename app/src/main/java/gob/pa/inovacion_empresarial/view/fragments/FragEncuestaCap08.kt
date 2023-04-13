package gob.pa.inovacion_empresarial.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.R
import kotlinx.android.synthetic.main.encuesta_capitulo_08.view.*

class FragEncuestaCap08 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val viewCap08 =  inflater.inflate(R.layout.encuesta_capitulo_08, container, false)

        viewCap08.rgroupCap8_56.setOnCheckedChangeListener { _, id ->
            when (id) {
                viewCap08.rbtCap8_56_si.id -> {
                    viewCap08.linearCap8_57.visibility = View.VISIBLE
                    viewCap08.linearCap8_58.visibility = View.VISIBLE
                }
                viewCap08.rbtCap8_56_no.id -> {
                    viewCap08.spinCap8_57_1_a

                    viewCap08.spinCap8_57_1_a.setSelection(0)
                    viewCap08.spinCap8_57_1_b.setSelection(0)
                    viewCap08.spinCap8_57_1_c.setSelection(0)
                    viewCap08.txtCap8_57_1_c_otra.text.clear()

                    viewCap08.spinCap8_57_2_a.setSelection(0)
                    viewCap08.spinCap8_57_2_b.setSelection(0)
                    viewCap08.spinCap8_57_2_c.setSelection(0)
                    viewCap08.txtCap8_57_2_c_otra.text.clear()

                    viewCap08.linearCap8_57.visibility = View.GONE
                    viewCap08.linearCap8_58.visibility = View.GONE

                    viewCap08.spinCap8_58_1_a.setSelection(0)
                    viewCap08.spinCap8_58_1_b.setSelection(0)
                    viewCap08.spinCap8_58_1_c.setSelection(0)
                    viewCap08.spinCap8_58_2_a.setSelection(0)
                    viewCap08.spinCap8_58_2_b.setSelection(0)
                    viewCap08.spinCap8_58_2_c.setSelection(0)
                    viewCap08.spinCap8_58_2_d.setSelection(0)
                    viewCap08.spinCap8_58_3_a.setSelection(0)
                    viewCap08.spinCap8_58_3_b.setSelection(0)
                    viewCap08.spinCap8_58_4_a.setSelection(0)
                    viewCap08.spinCap8_58_4_b.setSelection(0)
                    viewCap08.spinCap8_58_4_c.setSelection(0)
                    viewCap08.spinCap8_58_4_d.setSelection(0)
                    viewCap08.txtCap8_58_4_d_otra.text.clear()
                }
            }
        }

        viewCap08.spinCap8_57_1_a.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    2 -> { viewCap08.txtCap8_57_1_a_monto.visibility = View.VISIBLE }
                    0, 1, 3 -> {
                        viewCap08.txtCap8_57_1_a_monto.visibility = View.GONE
                        viewCap08.txtCap8_57_1_a_monto.text?.clear()
                    }
                }
            }
        }
        viewCap08.spinCap8_57_1_b.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    2 -> { viewCap08.txtCap8_57_1_b_monto.visibility = View.VISIBLE }
                    0, 1, 3 -> {
                        viewCap08.txtCap8_57_1_b_monto.visibility = View.GONE
                        viewCap08.txtCap8_57_1_b_monto.text?.clear()
                    }
                }
            }
        }
        viewCap08.spinCap8_57_1_c.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    2 -> { viewCap08.txtCap8_57_1_c_monto.visibility = View.VISIBLE }
                    0, 1, 3 -> {
                        viewCap08.txtCap8_57_1_c_monto.visibility = View.GONE
                        viewCap08.txtCap8_57_1_c_monto.text?.clear()
                    }
                }
            }
        }

        viewCap08.spinCap8_57_2_a.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    2 -> { viewCap08.txtCap8_57_2_a_monto.visibility = View.VISIBLE }
                    0, 1, 3 -> {
                        viewCap08.txtCap8_57_2_a_monto.visibility = View.GONE
                        viewCap08.txtCap8_57_2_a_monto.text?.clear()
                    }
                }
            }
        }
        viewCap08.spinCap8_57_2_b.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    2 -> { viewCap08.txtCap8_57_2_b_monto.visibility = View.VISIBLE }
                    0, 1, 3 -> {
                        viewCap08.txtCap8_57_2_b_monto.visibility = View.GONE
                        viewCap08.txtCap8_57_2_b_monto.text?.clear()
                    }
                }
            }
        }
        viewCap08.spinCap8_57_2_c.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    2 -> { viewCap08.txtCap8_57_2_c_monto.visibility = View.VISIBLE }
                    0, 1, 3 -> {
                        viewCap08.txtCap8_57_2_c_monto.visibility = View.GONE
                        viewCap08.txtCap8_57_2_c_monto.text?.clear()
                    }
                }
            }
        }



        return viewCap08
    }

}