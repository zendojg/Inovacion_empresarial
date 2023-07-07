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


        return viewCap9o1
    }

}