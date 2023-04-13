package gob.pa.inovacion_empresarial.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo01Binding
import kotlinx.android.synthetic.main.encuesta_capitulo_01.view.*

class FragEncuestaCap01 : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val viewCap01 = inflater.inflate(R.layout.encuesta_capitulo_01, container, false)
        val provArray: Array<String> = resources.getStringArray((R.array.arr_provincias))

        val provAdapter = ArrayAdapter(requireContext(),  R.layout.style_list, provArray)
        viewCap01.txtCap1_1.setAdapter(provAdapter)

        return viewCap01.rootView
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }





}