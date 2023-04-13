package gob.pa.inovacion_empresarial.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.function.Functions.Companion.hideKeyboard
import kotlinx.android.synthetic.main.encuesta_capitulo_07_part1.view.*

class FragEncuestaCap07o1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val viewCap07o1 = inflater.inflate(R.layout.encuesta_capitulo_07_part1, container, false)



        viewCap07o1.rgroupCap7_50.setOnCheckedChangeListener { _, id ->
            when (id) {
                viewCap07o1.rbtCap7_50_si.id -> {
                    println("------------------")
                    viewCap07o1.linearCap7o1_51.visibility = View.VISIBLE
                }
                viewCap07o1.rbtCap7_50_no.id -> {
                    viewCap07o1.rbtCap7_51_1_no_2021.isChecked = true
                    viewCap07o1.rbtCap7_51_1_no_2022.isChecked = true
                    viewCap07o1.rbtCap7_51_2_no_2021.isChecked = true
                    viewCap07o1.rbtCap7_51_2_no_2022.isChecked = true
                    viewCap07o1.rgroupCap7_51_1_2021.clearCheck()
                    viewCap07o1.rgroupCap7_51_1_2022.clearCheck()
                    viewCap07o1.rgroupCap7_51_2_2021.clearCheck()
                    viewCap07o1.rgroupCap7_51_2_2022.clearCheck()
                    viewCap07o1.linearCap7o1_51.visibility = View.GONE
                }
            }
        }


        viewCap07o1.rgroupCap7_51_1_2021.setOnCheckedChangeListener { _, id ->
            when (id) {
                viewCap07o1.rbtCap7_51_1_si_2021.id -> {
                    viewCap07o1.txtCap7_51_1_2021.visibility = View.VISIBLE }
                viewCap07o1.rbtCap7_51_1_no_2021.id -> {
                    hideKeyboard()
                    viewCap07o1.txtCap7_51_1_2021.text.clear()
                    viewCap07o1.txtCap7_51_1_2021.visibility = View.GONE }
            }
        }
        viewCap07o1.rgroupCap7_51_1_2022.setOnCheckedChangeListener { _, id ->
            when (id) {
                viewCap07o1.rbtCap7_51_1_si_2022.id -> {
                    viewCap07o1.txtCap7_51_1_2022.visibility = View.VISIBLE }
                viewCap07o1.rbtCap7_51_1_no_2022.id -> {
                    hideKeyboard()
                    viewCap07o1.txtCap7_51_1_2022.text.clear()
                    viewCap07o1.txtCap7_51_1_2022.visibility = View.GONE }
            }
        }
        viewCap07o1.rgroupCap7_51_2_2021.setOnCheckedChangeListener { _, id ->
            when (id) {
                viewCap07o1.rbtCap7_51_2_si_2021.id -> {
                    viewCap07o1.txtCap7_51_2_2021.visibility = View.VISIBLE }
                viewCap07o1.rbtCap7_51_2_no_2021.id -> {
                    hideKeyboard()
                    viewCap07o1.txtCap7_51_2_2021.text.clear()
                    viewCap07o1.txtCap7_51_2_2021.visibility = View.GONE }
            }
        }
        viewCap07o1.rgroupCap7_51_2_2022.setOnCheckedChangeListener { _, id ->
            when (id) {
                viewCap07o1.rbtCap7_51_2_si_2022.id -> {
                    viewCap07o1.txtCap7_51_2_2022.visibility = View.VISIBLE }
                viewCap07o1.rbtCap7_51_2_no_2022.id -> {
                    hideKeyboard()
                    viewCap07o1.txtCap7_51_2_2022.text.clear()
                    viewCap07o1.txtCap7_51_2_2022.visibility = View.GONE }
            }
        }



        return viewCap07o1
    }

}