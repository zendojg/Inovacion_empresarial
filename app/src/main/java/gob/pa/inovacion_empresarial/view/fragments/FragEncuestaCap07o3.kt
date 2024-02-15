package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo07Part3Binding
import gob.pa.inovacion_empresarial.function.CreateIncon
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelCap7
import gob.pa.inovacion_empresarial.model.ModelSpinLister
import gob.pa.inovacion_empresarial.view.FormActivity

class FragEncuestaCap07o3 : Fragment() {

    private lateinit var bindingcap7o3: EncuestaCapitulo07Part3Binding
    private lateinit var ctx: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingcap7o3 = EncuestaCapitulo07Part3Binding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingcap7o3.root
    }

    override fun onResume() {
        super.onResume()
        Mob.indiceFormulario = Mob.CAP7_P14
        val infoCap = Mob.infoCap.find { it.indexCap == Mob.CAP7_P14 }
        if (infoCap?.capView == false) fillOut()
        else onAction()
    }

    private fun onAction() {
        with(bindingcap7o3) {
            //val spinList = mutableListOf<ModelSpinLister>()
            val v55txtFields = listOf(
                Mob.cap7?.v55txt1a, Mob.cap7?.v55txt1b, Mob.cap7?.v55txt1c,
                Mob.cap7?.v55txt2a, Mob.cap7?.v55txt2b, Mob.cap7?.v55txt2c, Mob.cap7?.v55txt2d,
                Mob.cap7?.v55txt3a, Mob.cap7?.v55txt3b,
                Mob.cap7?.v55txt4a, Mob.cap7?.v55txt4b, Mob.cap7?.v55txt4c
            )

            val spinFields = listOf(
                spinCap7551A, spinCap7551B, spinCap7551C,
                spinCap7552A, spinCap7552B, spinCap7552C, spinCap7552D,
                spinCap7553A, spinCap7553B,
                spinCap7554A, spinCap7554B, spinCap7554C
            )
            val spinList = v55txtFields.mapIndexed { index, v55txtField ->
                val value = v55txtField?.toIntOrNull() ?: 0
                ModelSpinLister(spinFields[index], value)
            }.toMutableList()

            val imp55Adp = ArrayAdapter(ctx, R.layout.style_box, Mob.arrGrade)
            imp55Adp.setDropDownViewResource(R.layout.style_list)

            for (index in 0 until tb55.childCount) {
                val view = tb55.getChildAt(index)
                if (view is Spinner) { view.adapter = imp55Adp }
            }
            for (modelSpinLister in spinList) {
                val spinner = modelSpinLister.spinner
                val indice = modelSpinLister.indice
                if (indice >= 0 && indice < spinner.adapter.count) { spinner.setSelection(indice) }
            }

            for (modelSpinLister in spinList) {
                val spinner = modelSpinLister.spinner

                spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) { modelSpinLister.indice = position }
                    override fun onNothingSelected(parent: AdapterView<*>?) { /* sin selección */ }
                }
            }
        }
    }

    private fun fillOut() {
        Mob.infoCap.find { it.indexCap == Mob.CAP7_P14 }?.capView = true
        onAction()
    }

    fun saveCap(): List<String> {
        with (bindingcap7o3) {
            Mob.cap7 = ModelCap7(
                Mob.cap7?.id,
                Mob.cap7?.ncontrol,
                Mob.cap7?.v50check,
                Mob.cap7?.v51check21o1,
                Mob.cap7?.v51check21o2,
                Mob.cap7?.v51num21o1,
                Mob.cap7?.v51num21o2,
                Mob.cap7?.v51check22o1,
                Mob.cap7?.v51check22o2,
                Mob.cap7?.v51num22o1,
                Mob.cap7?.v51num22o2,
                Mob.cap7?.v52txt01,
                Mob.cap7?.v52txt02,
                Mob.cap7?.v52txt03,
                Mob.cap7?.v52txt04,
                Mob.cap7?.v52txt05,
                Mob.cap7?.v52txt06,
                Mob.cap7?.v52txt07,
                Mob.cap7?.v52txt08,
                Mob.cap7?.v52txt09,
                Mob.cap7?.v52txt10,
                Mob.cap7?.v52txt11,
                Mob.cap7?.v52txt12,
                Mob.cap7?.v52txt13,
                Mob.cap7?.v52txt14,
                Mob.cap7?.v52txt15desc,
                Mob.cap7?.v52txt15,
                Mob.cap7?.v53num21a,
                Mob.cap7?.v53num21b,
                Mob.cap7?.v53num21c,
                Mob.cap7?.v53num21d,
                Mob.cap7?.v53num21e,
                Mob.cap7?.v53num21f,
                Mob.cap7?.v53num21g,
                Mob.cap7?.v53num1T21,
                Mob.cap7?.v53num22a,
                Mob.cap7?.v53num22b,
                Mob.cap7?.v53num22c,
                Mob.cap7?.v53num22d,
                Mob.cap7?.v53num22e,
                Mob.cap7?.v53num22f,
                Mob.cap7?.v53num22g,
                Mob.cap7?.v53num1T22,
                Mob.cap7?.v53txtgdesc,
                Mob.cap7?.v54txt01,
                Mob.cap7?.v54txt02,
                Mob.cap7?.v54txt03,
                Mob.cap7?.v54txt04,
                Mob.cap7?.v54txt05,
                Mob.cap7?.v54txt06,
                Mob.cap7?.v54txt07,
                Mob.cap7?.v54txt08,
                Mob.cap7?.v54txt09,
                Mob.cap7?.v54txt10,
                Mob.cap7?.v54txt11,
                Mob.cap7?.v54txt12,
                Mob.cap7?.v54txt13,
                Mob.cap7?.v54txt14,
                Mob.cap7?.v54txt15,
                Mob.cap7?.v54txt15desc,//
                if (spinCap7551A.selectedItemPosition == 0) null else
                    spinCap7551A.selectedItemPosition.toString(),
                if (spinCap7551B.selectedItemPosition == 0) null else
                    spinCap7551B.selectedItemPosition.toString(),
                if (spinCap7551C.selectedItemPosition == 0) null else
                    spinCap7551C.selectedItemPosition.toString(),
                if (spinCap7552A.selectedItemPosition == 0) null else
                    spinCap7552A.selectedItemPosition.toString(),
                if (spinCap7552B.selectedItemPosition == 0) null else
                    spinCap7552B.selectedItemPosition.toString(),
                if (spinCap7552C.selectedItemPosition == 0) null else
                    spinCap7552C.selectedItemPosition.toString(),
                if (spinCap7552D.selectedItemPosition == 0) null else
                    spinCap7552D.selectedItemPosition.toString(),
                if (spinCap7553A.selectedItemPosition == 0) null else
                    spinCap7553A.selectedItemPosition.toString(),
                if (spinCap7553B.selectedItemPosition == 0) null else
                    spinCap7553B.selectedItemPosition.toString(),
                if (spinCap7554A.selectedItemPosition == 0) null else
                    spinCap7554A.selectedItemPosition.toString(),
                if (spinCap7554B.selectedItemPosition == 0) null else
                    spinCap7554B.selectedItemPosition.toString(),
                if (spinCap7554C.selectedItemPosition == 0) null else
                    spinCap7554C.selectedItemPosition.toString(),
            )
        }
        return viewCap()
    }
    private fun viewCap(): List<String> {
        val returnList: ArrayList<String> = ArrayList()
        with (Mob) {
            if (cap7?.v55txt1a.isNullOrEmpty() || cap7?.v55txt1a == "0")
                returnList.add(CreateIncon.inconsistencia(ctx, "131") ?: "")
            if (cap7?.v55txt1b.isNullOrEmpty() || cap7?.v55txt1b == "0")
                returnList.add(CreateIncon.inconsistencia(ctx, "132") ?: "")
            if (cap7?.v55txt1c.isNullOrEmpty() || cap7?.v55txt1c == "0")
                returnList.add(CreateIncon.inconsistencia(ctx, "133") ?: "")
            if (cap7?.v55txt2a.isNullOrEmpty() || cap7?.v55txt2a == "0")
                returnList.add(CreateIncon.inconsistencia(ctx, "134") ?: "")
            if (cap7?.v55txt2b.isNullOrEmpty() || cap7?.v55txt2b == "0")
                returnList.add(CreateIncon.inconsistencia(ctx, "135") ?: "")
            if (cap7?.v55txt2c.isNullOrEmpty() || cap7?.v55txt2c == "0")
                returnList.add(CreateIncon.inconsistencia(ctx, "136") ?: "")
            if (cap7?.v55txt2d.isNullOrEmpty() || cap7?.v55txt2d == "0")
                returnList.add(CreateIncon.inconsistencia(ctx, "137") ?: "")
            if (cap7?.v55txt3a.isNullOrEmpty() || cap7?.v55txt3a == "0")
                returnList.add(CreateIncon.inconsistencia(ctx, "138") ?: "")
            if (cap7?.v55txt3b.isNullOrEmpty() || cap7?.v55txt3b == "0")
                returnList.add(CreateIncon.inconsistencia(ctx, "139") ?: "")
            if (cap7?.v55txt4a.isNullOrEmpty() || cap7?.v55txt4a == "0")
                returnList.add(CreateIncon.inconsistencia(ctx, "140") ?: "")
            if (cap7?.v55txt4b.isNullOrEmpty() || cap7?.v55txt4b == "0")
                returnList.add(CreateIncon.inconsistencia(ctx, "141") ?: "")
            if (cap7?.v55txt4c.isNullOrEmpty() || cap7?.v55txt4c == "0")
                returnList.add(CreateIncon.inconsistencia(ctx, "142") ?: "")

            infoCap.find { it.indexCap == CAP7_P14 }?.incons = returnList.isNotEmpty()
            //println("Cap7_part3: --$cap7")
            return returnList
        }
    }
}