package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo01Binding
import gob.pa.inovacion_empresarial.model.DVModel
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelCap1

class FragEncuestaCap01 : Fragment() {

    private lateinit var fragCap1: EncuestaCapitulo01Binding
    private lateinit var ctx: Context
    private val dvmCap1: DVModel by viewModels()

    private var idCap1:Int? = null
    private var nCont:Int? = null
    private var arrayProv: Array<String> = emptyArray()
    private var arrayDist: Array<String> = emptyArray()
    private var arrayCorre: Array<String> = emptyArray()
    private var arrayLugarP: Array<String> = emptyArray()
    private var idprov: String = ""
    private var iddist: String = ""
    private var idcorre: String = ""
    private var idlugarp: String = ""


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        fragCap1 = EncuestaCapitulo01Binding.inflate(layoutInflater)
        ctx = requireContext()

        val provArray: Array<String> = resources.getStringArray((R.array.arr_provincias))
        val provAdapter = ArrayAdapter(requireContext(),  R.layout.style_list, provArray)
        fragCap1.txtCap11.setAdapter(provAdapter)

        return fragCap1.root
    }


    override fun onResume() {
        super.onResume()
        if (Mob.recap1)
            fillOut(Mob.formComp?.cap1)
        else actions()
    }

    private fun actions() {

    }

    private fun fillOut(cap1: ModelCap1?) {
        idprov = cap1?.v01provtxt ?: ""
        iddist = cap1?.v02disttxt ?: ""
        idcorre = cap1?.v03corretxt ?: ""
        idlugarp = cap1?.v04lugartxt ?: ""

        fragCap1.txtCap11ID.text = idprov
        fragCap1.txtCap12ID.text = iddist
        fragCap1.txtCap13ID.text = idcorre
        fragCap1.txtCap14ID.text = idlugarp

        Mob.recap1 = false
        actions()
    }

}