package gob.pa.inovacion_empresarial.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.R
import kotlinx.android.synthetic.main.encuesta_capitulo_02_1_datos_de_la_empresa.view.*
import kotlinx.android.synthetic.main.encuesta_capitulo_02_2_datos_del_informante.view.*

class FragEncuestaCap02o2 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val viewCap2o2 = inflater.inflate(R.layout.encuesta_capitulo_02_2_datos_del_informante, container, false)

        viewCap2o2.btCap2_19tel1.setOnClickListener {
            viewCap2o2.frameCap2_19_tel2.visibility = View.VISIBLE
            viewCap2o2.btCap2_19tel1.isEnabled = false


        }
        viewCap2o2.btCap2_19tel2.setOnClickListener {
            viewCap2o2.txtCap2_19_2.text?.clear()
            viewCap2o2.frameCap2_19_tel2.visibility = View.GONE
            viewCap2o2.btCap2_19tel1.isEnabled = true


        }




        return viewCap2o2
    }


}