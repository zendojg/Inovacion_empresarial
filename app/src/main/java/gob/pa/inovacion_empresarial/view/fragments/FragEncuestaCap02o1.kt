package gob.pa.inovacion_empresarial.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo021DatosDeLaEmpresaBinding
import kotlinx.android.synthetic.main.encuesta_capitulo_02_1_datos_de_la_empresa.view.*

class FragEncuestaCap02o1 : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val viewCap2o1 = inflater.inflate(R.layout.encuesta_capitulo_02_1_datos_de_la_empresa, container, false)

        viewCap2o1.btCap2_9_tel1.setOnClickListener {
            viewCap2o1.frameCap2_9_tel2.visibility = View.VISIBLE
            viewCap2o1.btCap2_9_tel1.isEnabled = false
            //viewCap2o1.btCap2_tel1.compoundDrawables.first().setTint(Color.LTGRAY)


        }
        viewCap2o1.btCap2_9_tel2.setOnClickListener {
            viewCap2o1.txtCap2_9_2.text?.clear()
            viewCap2o1.frameCap2_9_tel2.visibility = View.GONE
            viewCap2o1.btCap2_9_tel1.isEnabled = true
            //viewCap2o1.btCap2_tel1.compoundDrawables.first().setTint(Color.BLUE)
        }



        return viewCap2o1
    }


}