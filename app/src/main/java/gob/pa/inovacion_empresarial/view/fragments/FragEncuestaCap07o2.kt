package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo07Part2Binding
import gob.pa.inovacion_empresarial.function.ClassFunctions
import gob.pa.inovacion_empresarial.function.CreateIncon
import gob.pa.inovacion_empresarial.function.Functions.toEditable
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelCap7
import gob.pa.inovacion_empresarial.model.ModelTexWatchers

class FragEncuestaCap07o2 : Fragment() {

    private lateinit var bindingcap7o2: EncuestaCapitulo07Part2Binding
    private lateinit var ctx: Context
    private val textWatcherList = mutableListOf<ModelTexWatchers>()
    private var row1EditTexts: List<EditText> = emptyList()
    private var row2EditTexts: List<EditText> = emptyList()
    private var indice01 = 0
    private var indice02 = 0
    private var indice03 = 0
    private var indice04 = 0
    private var indice05 = 0
    private var indice06 = 0
    private var indice07 = 0
    private var indice08 = 0
    private var indice09 = 0
    private var indice10 = 0
    private var indice11 = 0
    private var indice12 = 0
    private var indice13 = 0
    private var indice14 = 0
    private var indice15 = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingcap7o2 = EncuestaCapitulo07Part2Binding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingcap7o2.root
    }

    override fun onResume() {
        super.onResume()
        Mob.indiceFormulario = Mob.CAP7P13
        if (Mob.seecap07o2) fillOut()
        else onAction()
    }

    override fun onPause() {
        super.onPause()
        for (edittext in row1EditTexts)
            edittext.onFocusChangeListener = null
        for (edittext in row2EditTexts)
            edittext.onFocusChangeListener = null

        for (modelTexWatcher in textWatcherList) {
            modelTexWatcher.editext.removeTextChangedListener(modelTexWatcher.watcher)
        }
        textWatcherList.clear()
    }
    private fun onAction() {
        with(bindingcap7o2) {
            lowCap7o2.setOnClickListener { saveCap() }

            row1EditTexts = listOf(
                txtCap753A2021,
                txtCap753B2021,
                txtCap753C2021,
                txtCap753D2021,
                txtCap753E2021,
                txtCap753F2021,
                txtCap753G2021,)

            row2EditTexts = listOf(
                txtCap753A2022,
                txtCap753B2022,
                txtCap753C2022,
                txtCap753D2022,
                txtCap753E2022,
                txtCap753F2022,
                txtCap753G2022,)

            for (index in 0 until tb53.childCount) {
                val view = tb53.getChildAt(index)
                if (view is TextInputLayout) {
                    val editText = view.editText
                    editText?.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                        if (hasFocus) {
                            if (row1EditTexts.contains(editText) && editText != null) {
                                val modelTexWatchers =
                                    ClassFunctions.actionEdittextSum(
                                        editText,
                                        row1EditTexts,
                                        txtCap753T2021)
                                textWatcherList.add(modelTexWatchers)
                            } else if (row2EditTexts.contains(editText) && editText != null) {
                                val modelTexWatchers =
                                    ClassFunctions.actionEdittextSum(
                                        editText,
                                        row2EditTexts,
                                        txtCap753T2022)
                                textWatcherList.add(modelTexWatchers)
                            }
                        } else {
                            if (textWatcherList.size > Mob.MAXTEXWATCHER4ROWS) {
                                for (modelTexWatcher in textWatcherList) {
                                    modelTexWatcher.editext.removeTextChangedListener(
                                        modelTexWatcher.watcher
                                    )
                                }
                            }
                            Log.i("-------textWatcher:", "${textWatcherList.size}")
                        }
                    }
                }
            }



            val imp54Adp = ArrayAdapter(ctx, R.layout.style_box, Mob.arrImp)
            imp54Adp.setDropDownViewResource(R.layout.style_list)

            spinCap7541.adapter = imp54Adp
            spinCap7542.adapter = imp54Adp
            spinCap7543.adapter = imp54Adp
            spinCap7544.adapter = imp54Adp
            spinCap7545.adapter = imp54Adp
            spinCap7546.adapter = imp54Adp
            spinCap7547.adapter = imp54Adp
            spinCap7548.adapter = imp54Adp
            spinCap7549.adapter = imp54Adp
            spinCap75410.adapter = imp54Adp
            spinCap75411.adapter = imp54Adp
            spinCap75412.adapter = imp54Adp
            spinCap75413.adapter = imp54Adp
            spinCap75414.adapter = imp54Adp
            spinCap75415.adapter = imp54Adp
            spinsAction()
        }
    }

    private fun spinsAction() {
        with(bindingcap7o2) {

            spinCap7541.setSelection(indice01)
            spinCap7542.setSelection(indice02)
            spinCap7543.setSelection(indice03)
            spinCap7544.setSelection(indice04)
            spinCap7545.setSelection(indice05)

            spinCap7546.setSelection(indice06)
            spinCap7547.setSelection(indice07)
            spinCap7548.setSelection(indice08)
            spinCap7549.setSelection(indice09)
            spinCap75410.setSelection(indice10)

            spinCap75411.setSelection(indice11)
            spinCap75412.setSelection(indice12)
            spinCap75413.setSelection(indice13)
            spinCap75414.setSelection(indice14)
            spinCap75415.setSelection(indice15)

            spinCap7541.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice01 = pos
                }
                override fun onNothingSelected(adp: AdapterView<*>?) {/* Sin acción */ }
            }
            spinCap7542.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice02 = pos
                }
                override fun onNothingSelected(adp: AdapterView<*>?) {/* Sin acción */ }
            }
            spinCap7543.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice03 = pos
                }
                override fun onNothingSelected(adp: AdapterView<*>?) {/* Sin acción */ }
            }
            spinCap7544.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice04 = pos
                }
                override fun onNothingSelected(adp: AdapterView<*>?) {/* Sin acción */ }
            }
            spinCap7545.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice05 = pos
                }
                override fun onNothingSelected(adp: AdapterView<*>?) {/* Sin acción */ }
            }
            spinCap7546.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice06 = pos
                }
                override fun onNothingSelected(adp: AdapterView<*>?) {/* Sin acción */ }
            }
            spinCap7547.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice07 = pos
                }
                override fun onNothingSelected(adp: AdapterView<*>?) {/* Sin acción */ }
            }
            spinCap7548.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice08 = pos
                }
                override fun onNothingSelected(adp: AdapterView<*>?) {/* Sin acción */ }
            }
            spinCap7549.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice09 = pos
                }
                override fun onNothingSelected(adp: AdapterView<*>?) {/* Sin acción */ }
            }
            spinCap75410.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice10 = pos
                }
                override fun onNothingSelected(adp: AdapterView<*>?) {/* Sin acción */ }
            }
            spinCap75411.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice11 = pos
                }
                override fun onNothingSelected(adp: AdapterView<*>?) {/* Sin acción */ }
            }
            spinCap75412.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice12 = pos
                }
                override fun onNothingSelected(adp: AdapterView<*>?) {/* Sin acción */ }
            }
            spinCap75413.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice13 = pos
                }
                override fun onNothingSelected(adp: AdapterView<*>?) {/* Sin acción */ }
            }
            spinCap75414.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice14 = pos
                }
                override fun onNothingSelected(adp: AdapterView<*>?) {/* Sin acción */ }
            }
            spinCap75415.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice15 = pos
                }
                override fun onNothingSelected(adp: AdapterView<*>?) {/* Sin acción */ }
            }
        }
    }


    private fun fillOut() {
        val cap7 = Mob.formComp?.cap7
        fillOut53(cap7)

        indice01 = try {  cap7?.v54txt01?.toInt() ?: 0 }
        catch (e: java.lang.NumberFormatException) { 0 }
        indice02 = try {  cap7?.v54txt02?.toInt() ?: 0 }
        catch (e: java.lang.NumberFormatException) { 0 }
        indice03 = try {  cap7?.v54txt03?.toInt() ?: 0 }
        catch (e: java.lang.NumberFormatException) { 0 }
        indice04 = try {  cap7?.v54txt04?.toInt() ?: 0 }
        catch (e: java.lang.NumberFormatException) { 0 }
        indice05 = try {  cap7?.v54txt05?.toInt() ?: 0 }
        catch (e: java.lang.NumberFormatException) { 0 }
        indice06 = try {  cap7?.v54txt06?.toInt() ?: 0 }
        catch (e: java.lang.NumberFormatException) { 0 }
        indice07 = try {  cap7?.v54txt07?.toInt() ?: 0 }
        catch (e: java.lang.NumberFormatException) { 0 }
        indice08 = try {  cap7?.v54txt08?.toInt() ?: 0 }
        catch (e: java.lang.NumberFormatException) { 0 }
        indice09 = try {  cap7?.v54txt09?.toInt() ?: 0 }
        catch (e: java.lang.NumberFormatException) { 0 }
        indice10 = try {  cap7?.v54txt10?.toInt() ?: 0 }
        catch (e: java.lang.NumberFormatException) { 0 }
        indice11 = try {  cap7?.v54txt11?.toInt() ?: 0 }
        catch (e: java.lang.NumberFormatException) { 0 }
        indice12 = try {  cap7?.v54txt12?.toInt() ?: 0 }
        catch (e: java.lang.NumberFormatException) { 0 }
        indice13 = try {  cap7?.v54txt13?.toInt() ?: 0 }
        catch (e: java.lang.NumberFormatException) { 0 }
        indice14 = try {  cap7?.v54txt14?.toInt() ?: 0 }
        catch (e: java.lang.NumberFormatException) { 0 }
        indice15 = try {  cap7?.v54txt15?.toInt() ?: 0 }
        catch (e: java.lang.NumberFormatException) { 0 }
        Mob.seecap07o2 = false
        onAction()
    }

    private fun fillOut53(cap7: ModelCap7?) {
        val blank = "".toEditable()
        with(bindingcap7o2) {
            txtCap753A2021.text = cap7?.v53num21a?.toEditable() ?: blank
            txtCap753A2022.text = cap7?.v53num22a?.toEditable() ?: blank
            txtCap753B2021.text = cap7?.v53num21b?.toEditable() ?: blank
            txtCap753B2022.text = cap7?.v53num22b?.toEditable() ?: blank
            txtCap753C2021.text = cap7?.v53num21c?.toEditable() ?: blank
            txtCap753C2022.text = cap7?.v53num22c?.toEditable() ?: blank
            txtCap753D2021.text = cap7?.v53num21d?.toEditable() ?: blank
            txtCap753D2022.text = cap7?.v53num22d?.toEditable() ?: blank
            txtCap753E2021.text = cap7?.v53num21e?.toEditable() ?: blank
            txtCap753E2022.text = cap7?.v53num22e?.toEditable() ?: blank
            txtCap753F2021.text = cap7?.v53num21f?.toEditable() ?: blank
            txtCap753F2022.text = cap7?.v53num22f?.toEditable() ?: blank
            txtCap753G2021.text = cap7?.v53num21g?.toEditable() ?: blank
            txtCap753G2022.text = cap7?.v53num22g?.toEditable() ?: blank
            txtCap753GOtro.text = cap7?.v53txtgdesc?.toEditable() ?: blank

            txtCap753T2021.text = cap7?.v53num1T21?.toEditable() ?: blank
            txtCap753T2022.text = cap7?.v53num1T22?.toEditable() ?: blank
        }
    }



    fun saveCap(): List<String> {
        with (bindingcap7o2) {
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
                Mob.cap7?.v52txt15,//
                txtCap753A2021.text.toString().ifEmpty { null },
                txtCap753B2021.text.toString().ifEmpty { null },
                txtCap753C2021.text.toString().ifEmpty { null },
                txtCap753D2021.text.toString().ifEmpty { null },
                txtCap753E2021.text.toString().ifEmpty { null },
                txtCap753F2021.text.toString().ifEmpty { null },
                txtCap753G2021.text.toString().ifEmpty { null },
                txtCap753T2021.text.toString().ifEmpty { null },
                txtCap753A2022.text.toString().ifEmpty { null },
                txtCap753B2022.text.toString().ifEmpty { null },
                txtCap753C2022.text.toString().ifEmpty { null },
                txtCap753D2022.text.toString().ifEmpty { null },
                txtCap753E2022.text.toString().ifEmpty { null },
                txtCap753F2022.text.toString().ifEmpty { null },
                txtCap753G2022.text.toString().ifEmpty { null },
                txtCap753T2022.text.toString().ifEmpty { null },
                txtCap753GOtro.text.toString().ifEmpty { null },
                if (spinCap7541.selectedItemPosition == 0) null else
                    spinCap7541.selectedItemPosition.toString(),
                if (spinCap7542.selectedItemPosition == 0) null else
                    spinCap7542.selectedItemPosition.toString(),
                if (spinCap7543.selectedItemPosition == 0) null else
                    spinCap7543.selectedItemPosition.toString(),
                if (spinCap7544.selectedItemPosition == 0) null else
                    spinCap7544.selectedItemPosition.toString(),
                if (spinCap7545.selectedItemPosition == 0) null else
                    spinCap7545.selectedItemPosition.toString(),
                if (spinCap7546.selectedItemPosition == 0) null else
                    spinCap7546.selectedItemPosition.toString(),
                if (spinCap7547.selectedItemPosition == 0) null else
                    spinCap7547.selectedItemPosition.toString(),
                if (spinCap7548.selectedItemPosition == 0) null else
                    spinCap7548.selectedItemPosition.toString(),
                if (spinCap7549.selectedItemPosition == 0) null else
                    spinCap7549.selectedItemPosition.toString(),
                if (spinCap75410.selectedItemPosition == 0) null else
                    spinCap75410.selectedItemPosition.toString(),
                if (spinCap75411.selectedItemPosition == 0) null else
                    spinCap75411.selectedItemPosition.toString(),
                if (spinCap75412.selectedItemPosition == 0) null else
                    spinCap75412.selectedItemPosition.toString(),
                if (spinCap75413.selectedItemPosition == 0) null else
                    spinCap75413.selectedItemPosition.toString(),
                if (spinCap75414.selectedItemPosition == 0) null else
                    spinCap75414.selectedItemPosition.toString(),
                if (spinCap75415.selectedItemPosition == 0) null else
                    spinCap75415.selectedItemPosition.toString(),
                txtCap75415Otro.text.toString().ifEmpty { null },
                Mob.cap7?.v55txt1a,//
                Mob.cap7?.v55txt1b,
                Mob.cap7?.v55txt1c,
                Mob.cap7?.v55txt2a,
                Mob.cap7?.v55txt2b,
                Mob.cap7?.v55txt2c,
                Mob.cap7?.v55txt2d,
                Mob.cap7?.v55txt3a,
                Mob.cap7?.v55txt3b,
                Mob.cap7?.v55txt4a,
                Mob.cap7?.v55txt4b,
                Mob.cap7?.v55txt4c,

                )
        }
        return viewCap()
    }

    private fun viewCap(): List<String> {
        with(Mob) {
            val returnList: ArrayList<String> = ArrayList()
//            if (cap7?.v53num1T21.isNullOrEmpty())
//                returnList.add(CreateIncon.inconsistencia(ctx, "114") ?: "")
//            if (cap7?.v53num1T22.isNullOrEmpty())
//                returnList.add(CreateIncon.inconsistencia(ctx, "115") ?: "")
            if (cap7?.v54txt01.isNullOrEmpty() || cap7?.v54txt01 == "0")
                returnList.add(CreateIncon.inconsistencia(ctx, "116") ?: "")
            if (cap7?.v54txt02.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "117") ?: "")
            if (cap7?.v54txt03.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "118") ?: "")
            if (cap7?.v54txt04.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "119") ?: "")
            if (cap7?.v54txt05.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "120") ?: "")
            if (cap7?.v54txt06.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "121") ?: "")
            if (cap7?.v54txt07.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "122") ?: "")
            if (cap7?.v54txt08.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "123") ?: "")
            if (cap7?.v54txt09.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "124") ?: "")
            if (cap7?.v54txt10.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "125") ?: "")
            if (cap7?.v54txt11.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "126") ?: "")
            if (cap7?.v54txt12.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "127") ?: "")
            if (cap7?.v54txt13.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "128") ?: "")
            if (cap7?.v54txt14.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "129") ?: "")
            if (cap7?.v54txt15.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "130") ?: "")
            if (cap7?.v54txt15.isNullOrEmpty() && cap7?.v54txt15?.isNotEmpty() == true)
                returnList.add(CreateIncon.inconsistencia(ctx, "130") ?: "")


            icap07o2 = returnList.isNotEmpty()
            println("---------Is not empty: $icap07o2--$cap7")
            return returnList
        }
    }

}