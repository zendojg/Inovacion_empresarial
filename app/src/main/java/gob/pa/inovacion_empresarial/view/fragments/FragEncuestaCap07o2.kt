package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo07Part2Binding
import gob.pa.inovacion_empresarial.function.EdittextFormat
import gob.pa.inovacion_empresarial.function.CreateIncon
import gob.pa.inovacion_empresarial.function.Functions.toEditable
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelCap7
import gob.pa.inovacion_empresarial.model.ModelSpinLister
import gob.pa.inovacion_empresarial.model.ModelTexWatchers
import java.text.DecimalFormat

class FragEncuestaCap07o2 : Fragment() {

    private lateinit var bindingcap7o2: EncuestaCapitulo07Part2Binding
    private lateinit var ctx: Context
    private val textWatcherList = mutableListOf<ModelTexWatchers>()
    private val spinList = mutableListOf<ModelSpinLister>()
    private var row1EditTexts: List<EditText> = emptyList()
    private var row2EditTexts: List<EditText> = emptyList()
    private val decimalFormat = DecimalFormat("#,###")


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

        with(bindingcap7o2) {

            row1EditTexts = listOf(
                txtCap753A2021, txtCap753B2021, txtCap753C2021, txtCap753D2021,
                txtCap753E2021, txtCap753F2021, txtCap753G2021,)

            row2EditTexts = listOf(
                txtCap753A2022, txtCap753B2022, txtCap753C2022, txtCap753D2022,
                txtCap753E2022, txtCap753F2022, txtCap753G2022,)

        }

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
            spinList.clear()
            spinList.add(ModelSpinLister(spinCap7541, Mob.cap7?.v54txt01?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap7542, Mob.cap7?.v54txt02?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap7543, Mob.cap7?.v54txt03?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap7544, Mob.cap7?.v54txt04?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap7545, Mob.cap7?.v54txt05?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap7546, Mob.cap7?.v54txt06?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap7547, Mob.cap7?.v54txt07?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap7548, Mob.cap7?.v54txt08?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap7549, Mob.cap7?.v54txt09?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap75410, Mob.cap7?.v54txt10?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap75411, Mob.cap7?.v54txt11?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap75412, Mob.cap7?.v54txt12?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap75413, Mob.cap7?.v54txt13?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap75414, Mob.cap7?.v54txt14?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap75415, Mob.cap7?.v54txt15?.toIntOrNull() ?: 0))

            for (index in 0 until tb53.childCount) {
                val view = tb53.getChildAt(index)
                if (view is TextInputLayout) {
                    val editText = view.editText
                    editText?.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                        if (hasFocus) {
                            if (row1EditTexts.contains(editText) && editText != null) {
                                textWatcherList.add(EdittextFormat.edittextBigSum(
                                    editText,
                                    row1EditTexts,
                                    txtCap753T2021))
                            } else if (row2EditTexts.contains(editText) && editText != null) {
                                val modelTexWatchers =
                                    EdittextFormat.edittextBigSum(
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
                        }
                    }
                }
            }

            val imp54Adp = ArrayAdapter(ctx, R.layout.style_box, Mob.arrImp)
            imp54Adp.setDropDownViewResource(R.layout.style_list)
            for (index in 0 until tb54.childCount) {
                val view = tb54.getChildAt(index)
                if (view is Spinner) { view.adapter = imp54Adp }
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
        val cap7 = Mob.formComp?.cap7
        with(bindingcap7o2) {

            txtCap753A2021.text = cap7?.v53num21a?.toDouble()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "".toEditable()
            txtCap753A2022.text = cap7?.v53num22a?.toDouble()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "".toEditable()
            txtCap753B2021.text = cap7?.v53num21b?.toDouble()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "".toEditable()
            txtCap753B2022.text = cap7?.v53num22b?.toDouble()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "".toEditable()
            txtCap753C2021.text = cap7?.v53num21c?.toDouble()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "".toEditable()
            txtCap753C2022.text = cap7?.v53num22c?.toDouble()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "".toEditable()
            txtCap753D2021.text = cap7?.v53num21d?.toDouble()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "".toEditable()
            txtCap753D2022.text = cap7?.v53num22d?.toDouble()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "".toEditable()
            txtCap753E2021.text = cap7?.v53num21e?.toDouble()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "".toEditable()
            txtCap753E2022.text = cap7?.v53num22e?.toDouble()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "".toEditable()
            txtCap753F2021.text = cap7?.v53num21f?.toDouble()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "".toEditable()
            txtCap753F2022.text = cap7?.v53num22f?.toDouble()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "".toEditable()
            txtCap753G2021.text = cap7?.v53num21g?.toDouble()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "".toEditable()
            txtCap753G2022.text = cap7?.v53num22g?.toDouble()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "".toEditable()
            txtCap753GOtro.text = cap7?.v53txtgdesc?.toDouble()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "".toEditable()

            txtCap753T2021.text = cap7?.v53num1T21?.toDouble()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "".toEditable()
            txtCap753T2022.text = cap7?.v53num1T22?.toDouble()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "".toEditable()
        }
        Mob.seecap07o2 = false
        onAction()
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
                txtCap753A2021.text.toString().replace(",", "").ifEmpty { null },
                txtCap753B2021.text.toString().replace(",", "").ifEmpty { null },
                txtCap753C2021.text.toString().replace(",", "").ifEmpty { null },
                txtCap753D2021.text.toString().replace(",", "").ifEmpty { null },
                txtCap753E2021.text.toString().replace(",", "").ifEmpty { null },
                txtCap753F2021.text.toString().replace(",", "").ifEmpty { null },
                txtCap753G2021.text.toString().replace(",", "").ifEmpty { null },
                txtCap753T2021.text.toString().replace(",", "").ifEmpty { null },
                txtCap753A2022.text.toString().replace(",", "").ifEmpty { null },
                txtCap753B2022.text.toString().replace(",", "").ifEmpty { null },
                txtCap753C2022.text.toString().replace(",", "").ifEmpty { null },
                txtCap753D2022.text.toString().replace(",", "").ifEmpty { null },
                txtCap753E2022.text.toString().replace(",", "").ifEmpty { null },
                txtCap753F2022.text.toString().replace(",", "").ifEmpty { null },
                txtCap753G2022.text.toString().replace(",", "").ifEmpty { null },
                txtCap753T2022.text.toString().replace(",", "").ifEmpty { null },
                txtCap753GOtro.text.toString().replace(",", "").ifEmpty { null },
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