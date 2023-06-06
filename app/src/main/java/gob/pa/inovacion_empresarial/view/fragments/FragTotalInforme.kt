package gob.pa.inovacion_empresarial.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.function.Functions.Companion.toEditable
import gob.pa.inovacion_empresarial.model.MVar
import kotlinx.android.synthetic.main.modulo_total_info.view.*

class FragTotalInforme: Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val viewSecc01 = inflater.inflate(R.layout.modulo_total_info, container, false)


        return viewSecc01.rootView
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    override fun onResume() {
        super.onResume()
        //Toast.makeText(activity, "id ${MVar.idlugarp}", Toast.LENGTH_SHORT).show()

        view?.txtInfoObsEncuesta?.text = MVar.obsEncuesta.toEditable()
        view?.txtInfoObsModulo?.text = MVar.obsModulo.toEditable()
    }



}