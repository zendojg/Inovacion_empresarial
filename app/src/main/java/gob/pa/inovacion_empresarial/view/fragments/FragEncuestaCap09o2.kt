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



        return viewCap09o2
    }

}