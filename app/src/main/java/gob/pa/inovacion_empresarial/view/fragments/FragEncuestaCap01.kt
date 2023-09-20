package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo01Binding
import gob.pa.inovacion_empresarial.model.DVModel
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelCap1
import gob.pa.inovacion_empresarial.service.room.RoomView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FragEncuestaCap01 : Fragment() {

    private lateinit var bindingcap1: EncuestaCapitulo01Binding
    private lateinit var ctx: Context
    private val dvmCap1: DVModel by viewModels()

    private var arrayProv: Array<String> = emptyArray()
    private var arrayDist: Array<String> = emptyArray()
    private var arrayCorre: Array<String> = emptyArray()
    private var arrayLugarP: Array<String> = emptyArray()
    private var idprov: String = ""
    private var iddist: String = ""
    private var idcorre: String = ""
    private var idlugarp: String = ""
    private var prov: String = ""
    private var dist: String = ""
    private var corre: String = ""
    private var lugarp: String = ""


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingcap1 = EncuestaCapitulo01Binding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingcap1.root
    }

    override fun onResume() {
        super.onResume()
        Mob.indiceFormulario = Mob.CAP1P01
        if (Mob.seecap01) fillOut()
        else seeData(false)
    }

    private fun onAction() {
        val room = RoomView(dvmCap1, ctx)
        with (bindingcap1){

            txtCap14.onItemClickListener = AdapterView.OnItemClickListener { adapter, _, pos, _ ->
                lifecycleScope.launch {
                    idlugarp = room.getLPID(idprov, iddist, idcorre,
                        adapter.getItemAtPosition(pos).toString())
                    activity?.runOnUiThread { txtCap14ID.text = idlugarp }
                }
            }
        }
    }

    private fun fillOut() {
        val cap1 = Mob.formComp?.cap1
        arrayDist = emptyArray()
        arrayCorre = emptyArray()
        arrayLugarP = emptyArray()
        idprov = cap1?.v01provtxt ?: ""
        iddist = cap1?.v02disttxt ?: ""
        idcorre = cap1?.v03corretxt ?: ""
        idlugarp = cap1?.v04lugartxt ?: ""

        bindingcap1.txtCap11ID.text = idprov
        bindingcap1.txtCap12ID.text = iddist
        bindingcap1.txtCap13ID.text = idcorre
        bindingcap1.txtCap14ID.text = idlugarp

        Mob.seecap01 = false
        seeData(false)
    }

    private fun seeData(editFields: Boolean) {
        val room = RoomView(dvmCap1, ctx)

        lifecycleScope.launch {
            if (editFields) {
                arrayProv = room.getProv()
                cargaEmptys()
            } else { arrayProv = emptyArray() }

            if (idprov.isEmpty()) return@launch

            prov = room.getProvName(idprov)
            arrayDist = if (editFields) room.getDist(idprov) else emptyArray()

            if (iddist.isEmpty()) return@launch

            dist = room.getDistName(idprov, iddist)
            arrayCorre = if (editFields) room.getCorre(idprov, iddist) else emptyArray()

            if (idcorre.isEmpty()) return@launch

            corre = room.getCorreName(idprov, iddist, idcorre)
            arrayLugarP = room.getLugarP(idprov, iddist, idcorre)

            if (idlugarp.isNotEmpty()) {
                lugarp = room.getLPName(idprov, iddist, idcorre, idlugarp)
            }
            updateUI()
        }
        onAction()
    }


    private fun updateUI() {
        activity?.runOnUiThread {
            bindingcap1.apply {
                txtCap11ID.text = idprov
                txtCap12ID.text = iddist
                txtCap13ID.text = idcorre
                txtCap14ID.text = idlugarp

                txtCap11.setAdapter(ArrayAdapter(ctx, R.layout.style_list, arrayProv))
                txtCap11.setText(prov, false)

                txtCap12.setAdapter(ArrayAdapter(ctx, R.layout.style_list, arrayDist))
                txtCap12.setText(dist, false)

                txtCap13.setAdapter(ArrayAdapter(ctx, R.layout.style_list, arrayCorre))
                txtCap13.setText(corre, false)

                txtCap14.setAdapter(ArrayAdapter(ctx, R.layout.style_list, arrayLugarP))
                txtCap14.setText(lugarp, false)
            }
        }
    }


    private fun cargaEmptys() {
        val room = RoomView(dvmCap1, ctx)

        fun setupAdapterAndText(
            textView: AutoCompleteTextView,
            array: Array<String>
        ) {
            textView.setOnItemClickListener { parent, _, pos, _ ->
                lifecycleScope.launch {
                    val selectedItem = parent.getItemAtPosition(pos).toString()
                    when (textView) {
                        bindingcap1.txtCap11 -> {
                            idprov = room.getProvID(selectedItem)
                            iddist = ""
                            idcorre = ""
                            idlugarp = ""
                            bindingcap1.txtCap11ID.text = idprov
                        }
                        bindingcap1.txtCap12 -> {
                            iddist = room.getDistID(idprov, selectedItem)
                            idcorre = ""
                            idlugarp = ""
                            bindingcap1.txtCap12ID.text = iddist
                        }
                        bindingcap1.txtCap13 -> {
                            idcorre = room.getCorreID(idprov, iddist, selectedItem)
                            idlugarp = ""
                            bindingcap1.txtCap13ID.text = idcorre
                        }
                        bindingcap1.txtCap14 -> {
                            idlugarp = ""
                            bindingcap1.txtCap14ID.text = idlugarp
                        }
                    }
                    val emptyArray = Mob.empArr
                    activity?.runOnUiThread {
                        textView.setAdapter(ArrayAdapter(ctx, R.layout.style_list, array))
                        textView.setText("", false)
                        if (textView === bindingcap1.txtCap12) {
                            bindingcap1.txtCap13.setAdapter(ArrayAdapter(ctx, R.layout.style_list, emptyArray))
                            bindingcap1.txtCap14.setAdapter(ArrayAdapter(ctx, R.layout.style_list, emptyArray))
                        }
                    }
                }
            }
        }
        with(bindingcap1) {
            setupAdapterAndText(txtCap11, arrayProv)
            setupAdapterAndText(txtCap12, arrayDist)
            setupAdapterAndText(txtCap13, arrayCorre)
            setupAdapterAndText(txtCap14, arrayLugarP)
        }
    }

    fun savedCap(): List<String> {
        with(bindingcap1) {
            Mob.cap1 = ModelCap1(
                Mob.formComp?.cap1?.id,
                Mob.formComp?.cap1?.ncontrol,
                txtCap11ID.text.toString().ifEmpty { null },
                txtCap12ID.text.toString().ifEmpty { null },
                txtCap13ID.text.toString().ifEmpty { null },
                txtCap14ID.text.toString().ifEmpty { null },
            )
            return viewCap()
        }
    }

    private fun viewCap(): List<String> {
        with (Mob) {
            val returnList: ArrayList<String> = ArrayList()
            if (cap1?.v01provtxt.isNullOrEmpty()) {
                returnList.add("Pregunta 1.  Provincia sin datos") }
            if (cap1?.v02disttxt.isNullOrEmpty()) {
                returnList.add("Pregunta 2.  Distrito sin datos") }
            if (cap1?.v03corretxt.isNullOrEmpty()) {
                returnList.add("Pregunta 3.  Corregimiento sin datos") }

            icap01 = returnList.isNotEmpty()
            println("Cap1: incon:$icap01--$cap1")
            if (returnList.isNotEmpty()) seeData(icap01)

            return returnList
        }
    }

}