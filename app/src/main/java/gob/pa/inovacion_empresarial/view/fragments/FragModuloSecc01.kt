package gob.pa.inovacion_empresarial.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.R
import kotlinx.android.synthetic.main.encuesta_capitulo_01.view.*
import kotlinx.android.synthetic.main.encuesta_capitulo_09_part1.view.*
import kotlinx.android.synthetic.main.modulo_seccion_01.view.*

class FragModuloSecc01 : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val viewSecc01 = inflater.inflate(R.layout.modulo_seccion_01, container, false)


        viewSecc01.rgroupSecc01_1.setOnCheckedChangeListener { _, id ->
            when (id) {
                viewSecc01.rbtSecc01_1_si.id -> {
                    viewSecc01.linearSecc01_2.visibility = View.VISIBLE
                }
                viewSecc01.rbtSecc01_1_no.id -> {
                    viewSecc01.linearSecc01_2.visibility = View.GONE
                    viewSecc01.linearSecc01_2_1.visibility = View.GONE

                    viewSecc01.rgroupSecc01_2_1.clearCheck()
                    viewSecc01.checkSecc01_2_a.isChecked = false
                    viewSecc01.checkSecc01_2_b.isChecked = false
                    viewSecc01.checkSecc01_2_c.isChecked = false
                    viewSecc01.checkSecc01_2_d.isChecked = false

                    viewSecc01.txtSecc01_2_d_otra.text.clear()
                    viewSecc01.checkSecc01_2_2.isChecked = false

                }
            }
        }

        viewSecc01.rgroupSecc01_2_1.setOnCheckedChangeListener { _, id ->
            when (id) {
                viewSecc01.rbtSecc01_2_si.id -> {
                    viewSecc01.linearSecc01_2_1.visibility = View.VISIBLE
                    viewSecc01.rgroupSecc01_2_2.visibility = View.GONE
                    viewSecc01.checkSecc01_2_2.isChecked = false
                }
                viewSecc01.rbtSecc01_2_no.id -> {
                    viewSecc01.linearSecc01_2_1.visibility = View.GONE
                    viewSecc01.rgroupSecc01_2_2.visibility = View.VISIBLE

                    viewSecc01.checkSecc01_2_a.isChecked = false
                    viewSecc01.checkSecc01_2_b.isChecked = false
                    viewSecc01.checkSecc01_2_c.isChecked = false
                    viewSecc01.checkSecc01_2_d.isChecked = false

                    viewSecc01.txtSecc01_2_d_otra.visibility = View.GONE
                    viewSecc01.txtSecc01_2_d_otra.text.clear()
                }
            }
        }
        viewSecc01.checkSecc01_2_d.setOnClickListener {
            viewSecc01.txtSecc01_2_d_otra.isVisible = viewSecc01.checkSecc01_2_d.isChecked
        }


        return viewSecc01.rootView
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }





}