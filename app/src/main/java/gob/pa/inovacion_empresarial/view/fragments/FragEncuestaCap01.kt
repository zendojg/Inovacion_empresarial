package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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

    private lateinit var fragCap1: EncuestaCapitulo01Binding
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragCap1 = EncuestaCapitulo01Binding.inflate(layoutInflater)
        ctx = requireContext()
        return fragCap1.root
    }


    override fun onResume() {
        super.onResume()
        if (Mob.seecap1)
            fillOut(Mob.formComp?.cap1)
        else actions()
    }

    private fun actions() {
        val room = RoomView(dvmCap1, ctx)
        var prov: String = ctx.getString(R.string.blank)
        var dist: String = ctx.getString(R.string.blank)
        var corre: String = ctx.getString(R.string.blank)
        var lugarp: String = ctx.getString(R.string.blank)
        CoroutineScope(Dispatchers.IO).launch {
            if (arrayProv.isEmpty())
                arrayProv = room.getProv()
            if (idprov.isNotEmpty()) {
                prov = room.getProvName(idprov)
                arrayDist = room.getDist(idprov)
                if (iddist.isNotEmpty()) {
                    dist = room.getDistName(idprov, iddist)
                    arrayCorre = room.getCorre(idprov, iddist)
                    if (idcorre.isNotEmpty()) {
                        corre = room.getCorreName(idprov, iddist, idcorre)
                        arrayLugarP = room.getLugarP(idprov, iddist, idcorre)
                        if (idlugarp.isNotEmpty())
                            lugarp = room.getLPName(idprov, iddist, idcorre, idlugarp)
                    }
                }
            }
            activity?.runOnUiThread {
                fragCap1.txtCap11ID.text = idprov
                fragCap1.txtCap12ID.text = iddist
                fragCap1.txtCap13ID.text = idcorre
                fragCap1.txtCap14ID.text = idlugarp

                fragCap1.txtCap11.setAdapter(ArrayAdapter(ctx, R.layout.style_list, arrayProv))
                fragCap1.txtCap11.setText(prov,false)

                fragCap1.txtCap12.setAdapter(ArrayAdapter(ctx, R.layout.style_list, arrayDist))
                fragCap1.txtCap12.setText(dist,false)

                fragCap1.txtCap13.setAdapter(ArrayAdapter(ctx, R.layout.style_list, arrayCorre))
                fragCap1.txtCap13.setText(corre,false)

                fragCap1.txtCap14.setAdapter(ArrayAdapter(ctx, R.layout.style_list, arrayLugarP))
                fragCap1.txtCap14.setText(lugarp,false)
            }
        }

        fragCap1.txtCap11.onItemClickListener = AdapterView.OnItemClickListener { prn, _, pos, _ ->
            CoroutineScope(Dispatchers.IO).launch {
                idprov = room.getProvID(prn.getItemAtPosition(pos).toString())
                arrayDist = room.getDist(idprov)
                iddist = ""
                idcorre = ""
                idlugarp = ""
                activity?.runOnUiThread {
                    fragCap1.txtCap11ID.text = idprov
                    fragCap1.txtCap12ID.text = iddist
                    fragCap1.txtCap13ID.text = idcorre
                    fragCap1.txtCap14ID.text = idlugarp

                    fragCap1.txtCap12.setAdapter(ArrayAdapter(ctx, R.layout.style_list, arrayDist))
                    fragCap1.txtCap13.setAdapter(ArrayAdapter(ctx, R.layout.style_list, Mob.empArr))
                    fragCap1.txtCap14.setAdapter(ArrayAdapter(ctx, R.layout.style_list, Mob.empArr))
                    fragCap1.txtCap12.setText("", false)
                    fragCap1.txtCap13.setText("", false)
                    fragCap1.txtCap14.setText("", false)
                }
            }
        }
        fragCap1.txtCap12.onItemClickListener = AdapterView.OnItemClickListener { prn, _, pos, _ ->
            CoroutineScope(Dispatchers.IO).launch {
                iddist = room.getDistID(idprov, prn.getItemAtPosition(pos).toString())
                arrayCorre = room.getCorre(idprov, iddist)
                idcorre = ""
                idlugarp = ""
                activity?.runOnUiThread {
                    fragCap1.txtCap12ID.text = iddist
                    fragCap1.txtCap13ID.text = idcorre
                    fragCap1.txtCap14ID.text = idlugarp

                    fragCap1.txtCap13.setAdapter(ArrayAdapter(ctx, R.layout.style_list, arrayCorre))
                    fragCap1.txtCap14.setAdapter(ArrayAdapter(ctx, R.layout.style_list, Mob.empArr))
                    fragCap1.txtCap13.setText("", false)
                    fragCap1.txtCap14.setText("", false)
                }
            }
        }

        fragCap1.txtCap13.onItemClickListener = AdapterView.OnItemClickListener { prn, _, pos, _ ->
            CoroutineScope(Dispatchers.IO).launch {
                idcorre = room.getCorreID(idprov, iddist, prn.getItemAtPosition(pos).toString())
                arrayLugarP = room.getLugarP(idprov, iddist, idcorre)
                idlugarp = ""
                activity?.runOnUiThread {
                    fragCap1.txtCap13ID.text = idcorre
                    fragCap1.txtCap14ID.text = idlugarp

                    fragCap1.txtCap14.setAdapter(ArrayAdapter(ctx,R.layout.style_list,arrayLugarP))
                    fragCap1.txtCap14.setText("", false)
                }
            }
        }
        fragCap1.txtCap14.onItemClickListener = AdapterView.OnItemClickListener { prn, _, pos, _ ->
            CoroutineScope(Dispatchers.IO).launch {
                idlugarp = room.getLPID(idprov, iddist, idcorre,prn.getItemAtPosition(pos).toString())
                activity?.runOnUiThread {
                    fragCap1.txtCap14ID.text = idlugarp
                }
            }
        }
        fragCap1.viewCap1.setOnClickListener { savedCap() }
    }

    private fun fillOut(cap1: ModelCap1?) {
        arrayDist = emptyArray()
        arrayCorre = emptyArray()
        arrayLugarP = emptyArray()
        idprov = cap1?.v01provtxt ?: ""
        iddist = cap1?.v02disttxt ?: ""
        idcorre = cap1?.v03corretxt ?: ""
        idlugarp = cap1?.v04lugartxt ?: ""

        fragCap1.txtCap11ID.text = idprov
        fragCap1.txtCap12ID.text = iddist
        fragCap1.txtCap13ID.text = idcorre
        fragCap1.txtCap14ID.text = idlugarp

        Mob.seecap1 = false
        actions()
    }

    private fun savedCap() {

    }

}