package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo07Part3Binding
import gob.pa.inovacion_empresarial.function.CreateIncon
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelCap7

class FragEncuestaCap07o3 : Fragment() {

    private lateinit var bindingcap7o3: EncuestaCapitulo07Part3Binding
    private lateinit var ctx: Context

    private var indice1a = 0
    private var indice1b = 0
    private var indice1c = 0
    private var indice2a = 0
    private var indice2b = 0
    private var indice2c = 0
    private var indice2d = 0
    private var indice3a = 0
    private var indice3b = 0
    private var indice4a = 0
    private var indice4b = 0
    private var indice4c = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingcap7o3 = EncuestaCapitulo07Part3Binding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingcap7o3.root
    }

    override fun onResume() {
        super.onResume()

        Mob.indiceFormulario = Mob.CAP7P14
        if (Mob.seecap07o3) fillOut()
        else onAction()
    }

    private fun onAction() {
        with(bindingcap7o3) {
            lowCap7o3.setOnClickListener { saveCap() }

            val imp55Adp = ArrayAdapter(ctx, R.layout.style_box, Mob.arrGrade)
            imp55Adp.setDropDownViewResource(R.layout.style_list)

            spinCap7551A.adapter = imp55Adp
            spinCap7551B.adapter = imp55Adp
            spinCap7551C.adapter = imp55Adp
            spinCap7552A.adapter = imp55Adp
            spinCap7552B.adapter = imp55Adp
            spinCap7552C.adapter = imp55Adp
            spinCap7552D.adapter = imp55Adp
            spinCap7553A.adapter = imp55Adp
            spinCap7553B.adapter = imp55Adp
            spinCap7554A.adapter = imp55Adp
            spinCap7554B.adapter = imp55Adp
            spinCap7554C.adapter = imp55Adp
            spinsAction()
        }
    }

    private fun spinsAction() {
        with(bindingcap7o3) {

            spinCap7551A.setSelection(indice1a)
            spinCap7551B.setSelection(indice1b)
            spinCap7551C.setSelection(indice1c)
            spinCap7552A.setSelection(indice2a)
            spinCap7552B.setSelection(indice2b)
            spinCap7552C.setSelection(indice2c)
            spinCap7552D.setSelection(indice2d)
            spinCap7553A.setSelection(indice3a)
            spinCap7553B.setSelection(indice3b)
            spinCap7554A.setSelection(indice4a)
            spinCap7554B.setSelection(indice4b)
            spinCap7554C.setSelection(indice4c)

            spinCap7551A.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice1a = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
            spinCap7551B.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice1b = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
            spinCap7551C.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice1c = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
            spinCap7552A.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice2a = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
            spinCap7552B.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice2b = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
            spinCap7552C.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice2c = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
            spinCap7552D.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice2d = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
            spinCap7553A.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice3a = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
            spinCap7553B.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice3b = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
            spinCap7554A.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice4a = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
            spinCap7554B.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice4b = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
            spinCap7554C.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice4c = pos
                }
                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
        }
    }


    private fun fillOut() {
        val cap7 = Mob.formComp?.cap7

        indice1a = try {  cap7?.v55txt1a?.toInt() ?: 0 } catch (e: NumberFormatException) { 0 }
        indice1b = try {  cap7?.v55txt1b?.toInt() ?: 0 } catch (e: NumberFormatException) { 0 }
        indice1c = try {  cap7?.v55txt1c?.toInt() ?: 0 } catch (e: NumberFormatException) { 0 }
        indice2a = try {  cap7?.v55txt2a?.toInt() ?: 0 } catch (e: NumberFormatException) { 0 }
        indice2b = try {  cap7?.v55txt2b?.toInt() ?: 0 } catch (e: NumberFormatException) { 0 }
        indice2c = try {  cap7?.v55txt2c?.toInt() ?: 0 } catch (e: NumberFormatException) { 0 }
        indice2d = try {  cap7?.v55txt2d?.toInt() ?: 0 } catch (e: NumberFormatException) { 0 }
        indice3a = try {  cap7?.v55txt3a?.toInt() ?: 0 } catch (e: NumberFormatException) { 0 }
        indice3b = try {  cap7?.v55txt3b?.toInt() ?: 0 } catch (e: NumberFormatException) { 0 }
        indice4a = try {  cap7?.v55txt4a?.toInt() ?: 0 } catch (e: NumberFormatException) { 0 }
        indice4b = try {  cap7?.v55txt4b?.toInt() ?: 0 } catch (e: NumberFormatException) { 0 }
        indice4c = try {  cap7?.v55txt4c?.toInt() ?: 0 } catch (e: NumberFormatException) { 0 }

        Mob.seecap07o3 = false
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

            icap07o3 = returnList.isNotEmpty()
            println("---------Is not empty: $icap07o3--$cap7")
            return returnList
        }
    }
}