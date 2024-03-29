package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo01Binding
import gob.pa.inovacion_empresarial.model.DVModel
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelCap1
import gob.pa.inovacion_empresarial.service.room.RoomView
import kotlinx.coroutines.launch

class FragEncuestaCap01 : Fragment() {

    private lateinit var bindingcap1: EncuestaCapitulo01Binding
    private lateinit var ctx: Context
    private val dvmCap1: DVModel by viewModels()

    private val emptyArray: Array<String> = emptyArray()
    private var arrayProv: Array<String> = emptyArray()
    private var arrayDist: Array<String> = emptyArray()
    private var arrayCorre: Array<String> = emptyArray()
    private var arrayLugarP: Array<String> = emptyArray()
    private var idprov: String = ""
    private var iddist: String = ""
    private var idcorre: String = ""
    private var idlugarp: String = ""
    private var nameProv: String = ""
    private var nameDist: String = ""
    private var nameCorre: String = ""
    private var nameLugarp: String = ""

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
        Mob.indiceFormulario = Mob.CAP1_P01

        val infoCap = Mob.infoCap.find { it.indexCap == Mob.CAP1_P01 }
        if (infoCap?.capView == false) fillOut()
        else seeData()
    }

    private fun onAction() {
        val room = RoomView(dvmCap1, ctx)
        bindingcap1.apply {
            txtCap11.onItemClickListener = AdapterView.OnItemClickListener { adapter, _, pos, _ ->
                if (txtCap11.isPopupShowing) lifecycleScope.launch {
                    idprov = room.getProvID(adapter.getItemAtPosition(pos).toString()) ?: ""
                    arrayDist = room.getDist(idprov)
                    activity?.runOnUiThread { autocomplete(txtCap11) }
                }
            }

            txtCap12.onItemClickListener = AdapterView.OnItemClickListener { adapter, _, pos, _ ->
                if (txtCap12.isPopupShowing) lifecycleScope.launch {
                    iddist = room.getDistID(idprov, adapter.getItemAtPosition(pos).toString()) ?: ""
                    arrayCorre = room.getCorre(idprov, iddist)
                    activity?.runOnUiThread {
                        txtCap12ID.text = iddist
                        autocomplete(txtCap12)
                    }
                }
            }

            txtCap13.onItemClickListener = AdapterView.OnItemClickListener { adapter, _, pos, _ ->
                if (txtCap13.isPopupShowing) lifecycleScope.launch {
                    idcorre = room.getCorreID(idprov, iddist,
                        adapter.getItemAtPosition(pos).toString()) ?: ""
                    arrayLugarP = room.getLugarP(idprov, iddist, idcorre)
                    activity?.runOnUiThread {
                        txtCap13ID.text = idcorre
                        autocomplete(txtCap13)
                    }
                }
            }

            txtCap14.onItemClickListener = AdapterView.OnItemClickListener { adapter, _, pos, _ ->
                lifecycleScope.launch {
                    idlugarp = room.getLPID(idprov, iddist, idcorre,
                        adapter.getItemAtPosition(pos).toString()) ?: ""
                    activity?.runOnUiThread { txtCap14ID.text = idlugarp }
                }
            }
        }
    }

    private fun autocomplete(autoCmpt: AutoCompleteTextView) {
        with(bindingcap1) {
            if (listOf(txtCap11, txtCap12, txtCap13).any { it == autoCmpt }) { //-- All corre
                idlugarp = ""
                txtCap14ID.text = idlugarp
                txtCap14.setAdapter(ArrayAdapter(ctx, R.layout.style_list,
                    if (autoCmpt == txtCap13) arrayLugarP else emptyArray))
                txtCap14.setText("", false)
                if (txtCap13 == autoCmpt) return@with
            }
            if (listOf(txtCap11, txtCap12).any { it == autoCmpt }) {  //-- Dist
                idcorre = ""
                txtCap13ID.text = idcorre
                txtCap13.setAdapter(ArrayAdapter(ctx, R.layout.style_list,
                    if (autoCmpt == txtCap12) arrayCorre else emptyArray))
                txtCap13.setText("", false)
                if (txtCap12 == autoCmpt) return@with
            }
            if (txtCap11 == autoCmpt) {  //-- Prov
                iddist = ""
                txtCap12ID.text = iddist
                txtCap11ID.text = idprov
                txtCap12.setAdapter(ArrayAdapter(ctx, R.layout.style_list, arrayDist))
                txtCap12.setText("", false)
            }
        }
    }

    private fun fillOut() {
        val cap1 = Mob.formComp?.cap1
        arrayProv = emptyArray()
        arrayDist = emptyArray()
        arrayCorre = emptyArray()
        arrayLugarP = emptyArray()

        idprov = cap1?.v01provtxt ?: ""
        iddist = cap1?.v02disttxt ?: ""
        idcorre = cap1?.v03corretxt ?: ""
        idlugarp = cap1?.v04lugartxt ?: ""

        bindingcap1.apply {
            scrollForm.smoothScrollTo(0,0)
            txtCap11ID.text = idprov
            txtCap12ID.text = iddist
            txtCap13ID.text = idcorre
            txtCap14ID.text = idlugarp
        }
        Mob.infoCap.find { it.indexCap == Mob.CAP1_P01 }?.capView = true
        seeData()
    }

    private fun seeData() {
        val room = RoomView(dvmCap1, ctx)
        val cap1 = Mob.formComp?.cap1
        lifecycleScope.launch {
            nameProv = room.getProvName(idprov) ?: ""
            nameDist = room.getDistName(idprov, iddist) ?: ""
            nameCorre = room.getCorreName(idprov, iddist, idcorre) ?: ""
            nameLugarp = room.getLPName(idprov, iddist, idcorre, idlugarp) ?: ""

            if (arrayProv.isEmpty()) arrayProv = room.getProv()
            if (arrayDist.isEmpty()) arrayDist = room.getDist(idprov)
            if (arrayCorre.isEmpty()) arrayCorre = room.getCorre(idprov, iddist)
            if (arrayLugarP.isEmpty()) arrayLugarP = room.getLugarP(idprov, iddist, idcorre)

            activity?.runOnUiThread {
                bindingcap1.apply {
                    txtCap11ID.text = idprov
                    txtCap12ID.text = iddist
                    txtCap13ID.text = idcorre
                    txtCap14ID.text = idlugarp

                    txtCap11.setAdapter(ArrayAdapter(ctx, R.layout.style_list,
                        if (cap1?.v01provtxt.isNullOrEmpty()) arrayProv else emptyArray))
                    txtCap11.setText(nameProv, false)

                    txtCap12.setAdapter(ArrayAdapter(ctx, R.layout.style_list,
                        if (cap1?.v02disttxt.isNullOrEmpty()) arrayDist else emptyArray))
                    txtCap12.setText(nameDist, false)

                    txtCap13.setAdapter(ArrayAdapter(ctx, R.layout.style_list,
                        if (cap1?.v03corretxt.isNullOrEmpty()) arrayCorre else emptyArray))
                    txtCap13.setText(nameCorre, false)

                    txtCap14.setAdapter(ArrayAdapter(ctx, R.layout.style_list, arrayLugarP))
                    txtCap14.setText(nameLugarp, false)
                }
            }
        }
        onAction()
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

            infoCap.find { it.indexCap == CAP1_P01 }?.incons = returnList.isNotEmpty()
            //println("Cap1:--$cap1")
            return returnList
        }
    }
}