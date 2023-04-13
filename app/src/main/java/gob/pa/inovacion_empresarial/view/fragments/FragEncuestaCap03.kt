package gob.pa.inovacion_empresarial.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.function.Functions.Companion.aString
import gob.pa.inovacion_empresarial.function.Functions.Companion.hideKeyboard
import gob.pa.inovacion_empresarial.function.Functions.Companion.myDate
import gob.pa.inovacion_empresarial.function.Functions.Companion.yearArray
import kotlinx.android.synthetic.main.encuesta_capitulo_01.view.*
import kotlinx.android.synthetic.main.encuesta_capitulo_03.view.*
import kotlinx.android.synthetic.main.encuesta_capitulo_07_part1.view.*
import java.util.*

class FragEncuestaCap03 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val viewCap03 = inflater.inflate(R.layout.encuesta_capitulo_03, container, false)


        val yearList: List<Int> = yearArray().reversed()
        val adptYear = ArrayAdapter(requireContext(), R.layout.style_list, yearList)
        viewCap03.txtCap3_22.setAdapter(adptYear)

        viewCap03.rgroupCap3_24.setOnCheckedChangeListener { _, id ->
            when (id) {
                viewCap03.rbtCap3_24_si.id -> {
                    viewCap03.linearCap3_24_continuar.visibility = View.VISIBLE }
                viewCap03.rbtCap3_24_no.id -> {
                    hideKeyboard()
                    viewCap03.linearCap3_24_continuar.visibility = View.GONE
                    viewCap03.rgroupCap3_25.clearCheck()
                    viewCap03.txtCap3_25_6.text?.clear()
                    viewCap03.txtCap3_26.text?.clear()
                    viewCap03.txtCap3_27.text?.clear()
                }
            }
        }



        return viewCap03
    }



}