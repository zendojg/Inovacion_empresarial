package gob.pa.inovacion_empresarial.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.R

import android.widget.LinearLayout
import kotlinx.android.synthetic.main.encuesta_capitulo_08.view.*
import kotlinx.android.synthetic.main.encuesta_capitulo_09_part1.view.*


class FragEncuestaCap09o1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val viewCap9o1 = inflater.inflate(R.layout.encuesta_capitulo_09_part1, container, false)


        viewCap9o1.checkCap9_60_6.setOnClickListener {
            viewCap9o1.txtCap9_60_6_otra.isEnabled = viewCap9o1.checkCap9_60_6.isChecked

        }

        viewCap9o1.rgroupCap9_59.setOnCheckedChangeListener { _, id ->
            when (id) {
                viewCap9o1.rbtCap9_59_si.id -> {
                    viewCap9o1.linearCap9_60.visibility = View.VISIBLE
                    viewCap9o1.linearCap9_61.visibility = View.VISIBLE
                }
                viewCap9o1.rbtCap9_59_no.id -> {
                    viewCap9o1.linearCap9_60.visibility = View.GONE
                    viewCap9o1.linearCap9_61.visibility = View.GONE

                    viewCap9o1.checkCap9_60_1.isChecked = false
                    viewCap9o1.checkCap9_60_2.isChecked = false
                    viewCap9o1.checkCap9_60_3.isChecked = false
                    viewCap9o1.checkCap9_60_4.isChecked = false
                    viewCap9o1.checkCap9_60_5.isChecked = false
                    viewCap9o1.checkCap9_60_6.isChecked = false
                    viewCap9o1.txtCap9_60_6_otra.text.clear()

                    viewCap9o1.rgroupCap9_61_1.clearCheck()
                    viewCap9o1.rgroupCap9_61_2.clearCheck()
                    viewCap9o1.rgroupCap9_61_3.clearCheck()
                    viewCap9o1.rgroupCap9_61_4.clearCheck()
                    viewCap9o1.rgroupCap9_61_5.clearCheck()
                    viewCap9o1.rgroupCap9_61_6.clearCheck()
                    viewCap9o1.rgroupCap9_61_7.clearCheck()
                    viewCap9o1.rgroupCap9_61_8.clearCheck()

                }
            }
        }

        return viewCap9o1
    }

}