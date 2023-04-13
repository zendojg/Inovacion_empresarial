package gob.pa.inovacion_empresarial.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.R
import kotlinx.android.synthetic.main.encuesta_capitulo_04.view.*

class FragEncuestaCap04 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {


        val viewCap4 = inflater.inflate(R.layout.encuesta_capitulo_04, container, false)

        viewCap4.btCap4_29_plus.setOnClickListener {

            if (!viewCap4.layoutCap4_29_2.isVisible) {
                viewCap4.layoutCap4_29_2.visibility = View.VISIBLE
                viewCap4.btCap4_29_remove.visibility = View.VISIBLE
            }
            else if (!viewCap4.layoutCap4_29_3.isVisible) {
                viewCap4.layoutCap4_29_3.visibility = View.VISIBLE
            }
            else if (!viewCap4.layoutCap4_29_4.isVisible) {
                viewCap4.layoutCap4_29_4.visibility = View.VISIBLE
            }

        }

        viewCap4.btCap4_29_remove.setOnClickListener {

            when {
                viewCap4.layoutCap4_29_4.isVisible -> {
                    viewCap4.layoutCap4_29_4.visibility = View.GONE
                    viewCap4.txtCap4_29_4.text?.clear()
                    viewCap4.txtCap4_29_cinu4.text?.clear()
                }
                viewCap4.layoutCap4_29_3.isVisible -> {
                    viewCap4.layoutCap4_29_3.visibility = View.GONE
                    viewCap4.txtCap4_29_3.text?.clear()
                    viewCap4.txtCap4_29_cinu3.text?.clear()
                }
                viewCap4.layoutCap4_29_2.isVisible -> {
                    viewCap4.txtCap4_29_2.text?.clear()
                    viewCap4.txtCap4_29_cinu2.text?.clear()
                    viewCap4.layoutCap4_29_2.visibility = View.GONE
                    viewCap4.btCap4_29_remove.visibility = View.GONE
                }
            }

        }




        return viewCap4
    }

}