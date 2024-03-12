package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo07Part1Binding
import gob.pa.inovacion_empresarial.function.CreateIncon
import gob.pa.inovacion_empresarial.function.EdittextFormat
import gob.pa.inovacion_empresarial.function.Functions.allFalse
import gob.pa.inovacion_empresarial.function.Functions.hideKeyboard
import gob.pa.inovacion_empresarial.function.Functions.toEditable
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelCap7
import gob.pa.inovacion_empresarial.model.ModelSpinLister
import gob.pa.inovacion_empresarial.model.ModelTexWatchers

class FragEncuestaCap07o1 : Fragment() {

    private lateinit var bindingcap7o1: EncuestaCapitulo07Part1Binding
    private lateinit var ctx: Context
    private val spinList = mutableListOf<ModelSpinLister>()
    private var p52Enable = true
    private var rowEditTexts: List<EditText> = emptyList()
    private val textWatcherList = mutableListOf<ModelTexWatchers>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingcap7o1 = EncuestaCapitulo07Part1Binding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingcap7o1.root
    }

    override fun onPause() {
        super.onPause()
        for (edittext in rowEditTexts)
            edittext.onFocusChangeListener = null

        for (modelTexWatcher in textWatcherList) {
            modelTexWatcher.edittext.removeTextChangedListener(modelTexWatcher.watcher)
        }
        rowEditTexts = emptyList()
        textWatcherList.clear()
    }

    override fun onResume() {
        super.onResume()
        Mob.indiceFormulario = Mob.CAP7_P12
        val infoCap = Mob.infoCap.find { it.indexCap == Mob.CAP7_P12 }
        if (infoCap?.capView == false) fillOut()
        else onAction()

        val allChecks = listOf(
            Mob.cap6?.v39check21o1, Mob.cap6?.v39check22o1,
            Mob.cap6?.v39check21o2, Mob.cap6?.v39check22o2,
            Mob.cap6?.v42check21o1, Mob.cap6?.v42check22o1,
            Mob.cap6?.v42check21o2, Mob.cap6?.v42check22o2,
            Mob.cap6?.v42check21o3, Mob.cap6?.v42check22o3,
            Mob.cap6?.v46check21o1, Mob.cap6?.v46check22o1,
            Mob.cap6?.v46check21o2, Mob.cap6?.v46check22o2,
            Mob.cap6?.v46check21o3, Mob.cap6?.v46check22o3,
            Mob.cap6?.v48check21o1, Mob.cap6?.v48check22o1,
            Mob.cap6?.v48check21o2, Mob.cap6?.v48check22o2,
            Mob.cap6?.v48check21o3, Mob.cap6?.v48check22o3,
            Mob.cap6?.v48check21o4, Mob.cap6?.v48check22o4
        )
        p52Enable = !allChecks.allFalse()
    }

    private fun onAction() {
        fun setRadioGroupCheckedChangeListener(
            radioGroup: RadioGroup,
            radioButtonYes: RadioButton,
            radioButtonNo: RadioButton,
            targetView: View
        ) {
            radioGroup.setOnCheckedChangeListener { _, id ->
                hideKeyboard()
                when (id) {
                    radioButtonYes.id -> targetView.visibility = View.VISIBLE
                    radioButtonNo.id -> targetView.visibility = View.GONE
                }
            }
        }
        with(bindingcap7o1) {
            rowEditTexts = listOf(txtCap75112021, txtCap75112022, txtCap75122021, txtCap75122022)
            spinList.clear()
            spinList.add(ModelSpinLister(spinCap7521, Mob.cap7?.v52txt01?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap7522, Mob.cap7?.v52txt02?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap7523, Mob.cap7?.v52txt03?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap7524, Mob.cap7?.v52txt04?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap7525, Mob.cap7?.v52txt05?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap7526, Mob.cap7?.v52txt06?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap7527, Mob.cap7?.v52txt07?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap7528, Mob.cap7?.v52txt08?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap7529, Mob.cap7?.v52txt09?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap75210, Mob.cap7?.v52txt10?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap75211, Mob.cap7?.v52txt11?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap75212, Mob.cap7?.v52txt12?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap75213, Mob.cap7?.v52txt13?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap75214, Mob.cap7?.v52txt14?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap75215, Mob.cap7?.v52txt15?.toIntOrNull() ?: 0))

            if (rbtCap750No.isChecked) layoutCap751.isVisible = false
            txtCap75112021ly.isVisible = rbtCap7511Si2021.isChecked
            txtCap75112022ly.isVisible = rbtCap7511Si2022.isChecked
            txtCap75122021ly.isVisible = rbtCap7512Si2021.isChecked
            txtCap75122022ly.isVisible = rbtCap7512Si2022.isChecked


            setRadioGroupCheckedChangeListener(rgroupCap750, rbtCap750Si, rbtCap750No, layoutCap751)
            setRadioGroupCheckedChangeListener(
                rgroupCap75112021,
                rbtCap7511Si2021,
                rbtCap7511No2021,
                txtCap75112021ly
            )
            setRadioGroupCheckedChangeListener(
                rgroupCap75112022,
                rbtCap7511Si2022,
                rbtCap7511No2022,
                txtCap75112022ly
            )
            setRadioGroupCheckedChangeListener(
                rgroupCap75122021,
                rbtCap7512Si2021,
                rbtCap7512No2021,
                txtCap75122021ly
            )
            setRadioGroupCheckedChangeListener(
                rgroupCap75122022,
                rbtCap7512Si2022,
                rbtCap7512No2022,
                txtCap75122022ly
            )

            for (index in 0 until tb51.childCount) {
                val view = tb51.getChildAt(index)
                if (view is TextInputLayout) {
                    val editText = view.editText
                    editText?.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                        if (hasFocus) {
                            if (rowEditTexts.contains(editText) && editText != null) {
                                textWatcherList.add(EdittextFormat.edittext100(editText))
                            }
                        } else if (textWatcherList.size > Mob.MAX_TEXWATCHER_4ROWS) {
                            for (modelTexWatcher in textWatcherList) {
                                modelTexWatcher.edittext.removeTextChangedListener(
                                    modelTexWatcher.watcher)
                            }
                        }
                    }
                }
            }


            val imp52Adp = ArrayAdapter(ctx, R.layout.style_box, Mob.arrImp)
            imp52Adp.setDropDownViewResource(R.layout.style_list)

            for (index in 0 until tb52.childCount) {
                val view = tb52.getChildAt(index)
                if (view is Spinner) { view.adapter = imp52Adp }
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
        with(bindingcap7o1) {
            scrollForm.smoothScrollTo(0,0)
            val radioButtonsMap = mapOf(
                rgroupCap750 to cap7?.v50check,
                rgroupCap75112021 to cap7?.v51check21o1,
                rgroupCap75112022 to cap7?.v51check22o1,
                rgroupCap75122021 to cap7?.v51check21o2,
                rgroupCap75122022 to cap7?.v51check22o2,
            )
            for ((radioGroup, isChecked) in radioButtonsMap) {
                when (isChecked) {
                    true -> radioGroup.check(radioGroup.getChildAt(0).id)
                    false -> radioGroup.check(radioGroup.getChildAt(1).id)
                    else -> radioGroup.clearCheck()
                }
            }
            txtCap75112021.text = cap7?.v51num21o1?.toEditable() ?: "".toEditable()
            txtCap75112022.text = cap7?.v51num22o1?.toEditable() ?: "".toEditable()
            txtCap75122021.text = cap7?.v51num21o2?.toEditable() ?: "".toEditable()
            txtCap75122022.text = cap7?.v51num22o2?.toEditable() ?: "".toEditable()

            txtCap75215Otro.text = cap7?.v52txt15desc?.toEditable() ?: "".toEditable()
        }

        Mob.infoCap.find { it.indexCap == Mob.CAP7_P12 }?.capView = true
        onAction()
    }


    fun saveCap(): List<String> {
        with (bindingcap7o1) {

            Mob.cap7 = ModelCap7(
                Mob.cap7?.id,
                Mob.cap7?.ncontrol,
                if (rbtCap750Si.isChecked) true else if (rbtCap750No.isChecked) false else null,    //--50
                if (rbtCap7511Si2021.isChecked && rbtCap750Si.isChecked) true else
                    if (rbtCap7511No2021.isChecked && rbtCap750Si.isChecked) false else null,       //--51-1-2021
                if (rbtCap7512Si2021.isChecked && rbtCap750Si.isChecked) true else
                    if (rbtCap7512No2021.isChecked && rbtCap750Si.isChecked) false else null,       //--51-2-2021
                if (rbtCap7511Si2021.isChecked &&
                    rbtCap750Si.isChecked &&
                    txtCap75112021.text.toString() != "0")
                    txtCap75112021.text.toString() else null,           //--51-1-2021 %
                if (rbtCap7512Si2021.isChecked &&
                    rbtCap750Si.isChecked &&
                    txtCap75122021.text.toString() != "0")
                    txtCap75122021.text.toString() else null,           //--51-2-2021 %

                if (rbtCap7511Si2022.isChecked && rbtCap750Si.isChecked) true else
                    if (rbtCap7511No2022.isChecked && rbtCap750Si.isChecked) false else null,       //--51-1-2022
                if (rbtCap7512Si2022.isChecked && rbtCap750Si.isChecked) true else
                    if (rbtCap7512No2022.isChecked && rbtCap750Si.isChecked) false else null,       //--51-2-2022

                if (rbtCap7511Si2022.isChecked &&
                    rbtCap750Si.isChecked &&
                    txtCap75112022.text.toString() != "0")
                    txtCap75112022.text.toString() else null,           //--51-1-2022 %

                if (rbtCap7512Si2022.isChecked &&
                    rbtCap750Si.isChecked &&
                    txtCap75122022.text.toString() != "0")
                    txtCap75122022.text.toString() else null,           //--51-2-2022 %

                if (spinCap7521.selectedItemPosition == 0) null else
                    spinCap7521.selectedItemPosition.toString(),
                if (spinCap7522.selectedItemPosition == 0) null else
                    spinCap7522.selectedItemPosition.toString(),
                if (spinCap7523.selectedItemPosition == 0) null else
                    spinCap7523.selectedItemPosition.toString(),
                if (spinCap7524.selectedItemPosition == 0) null else
                    spinCap7524.selectedItemPosition.toString(),
                if (spinCap7525.selectedItemPosition == 0) null else
                    spinCap7525.selectedItemPosition.toString(),
                if (spinCap7526.selectedItemPosition == 0) null else
                    spinCap7526.selectedItemPosition.toString(),
                if (spinCap7527.selectedItemPosition == 0) null else
                    spinCap7527.selectedItemPosition.toString(),
                if (spinCap7528.selectedItemPosition == 0) null else
                    spinCap7528.selectedItemPosition.toString(),
                if (spinCap7529.selectedItemPosition == 0) null else
                    spinCap7529.selectedItemPosition.toString(),
                if (spinCap75210.selectedItemPosition == 0) null else
                    spinCap75210.selectedItemPosition.toString(),
                if (spinCap75211.selectedItemPosition == 0) null else
                    spinCap75211.selectedItemPosition.toString(),
                if (spinCap75212.selectedItemPosition == 0) null else
                    spinCap75212.selectedItemPosition.toString(),
                if (spinCap75213.selectedItemPosition == 0) null else
                    spinCap75213.selectedItemPosition.toString(),
                if (spinCap75214.selectedItemPosition == 0) null else
                    spinCap75214.selectedItemPosition.toString(),
                if (txtCap75215Otro.text.isNullOrEmpty()) null else txtCap75215Otro.text.toString(),
                if (spinCap75215.selectedItemPosition == 0) null else
                    spinCap75215.selectedItemPosition.toString(),
                Mob.cap7?.v53num21a,//----
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
                Mob.cap7?.v54txt15desc,
                Mob.cap7?.v55txt1a,
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
            val porce51x1x21 = cap7?.v51num21o1.isNullOrEmpty() || cap7?.v51num21o1 == "0"
            val porce51x1x22 = cap7?.v51num22o1.isNullOrEmpty() || cap7?.v51num22o1 == "0"
            val porce51x2x21 = cap7?.v51num21o2.isNullOrEmpty() || cap7?.v51num21o2 == "0"
            val porce51x2x22 = cap7?.v51num22o2.isNullOrEmpty() || cap7?.v51num22o2 == "0"

            val returnList: ArrayList<String> = ArrayList()
            if (cap7?.v50check == null)
                returnList.add(CreateIncon.inconsistencia(ctx, "90") ?: "")
            else if (cap7?.v50check == true) {
                if (cap7?.v51check21o1 == null)
                    returnList.add(CreateIncon.inconsistencia(ctx, "91") ?: "")
                if (cap7?.v51check22o1 == null)
                    returnList.add(CreateIncon.inconsistencia(ctx, "92") ?: "")
                if (cap7?.v51check21o2 == null)
                    returnList.add(CreateIncon.inconsistencia(ctx, "93") ?: "")
                if (cap7?.v51check22o2 == null)
                    returnList.add(CreateIncon.inconsistencia(ctx, "94") ?: "")
                if (cap7?.v51check21o1 == true && porce51x1x21)
                    returnList.add(CreateIncon.inconsistencia(ctx, "95") ?: "")
                if (cap7?.v51check22o1 == true && porce51x1x22)
                    returnList.add(CreateIncon.inconsistencia(ctx, "96") ?: "")
                if (cap7?.v51check21o2 == true && porce51x2x21)
                    returnList.add(CreateIncon.inconsistencia(ctx, "97") ?: "")
                if (cap7?.v51check22o2 == true && porce51x2x22)
                    returnList.add(CreateIncon.inconsistencia(ctx, "98") ?: "")
            }
            if (cap7?.v52txt01.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "99") ?: "")
            if (cap7?.v52txt02.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "100") ?: "")
            if (cap7?.v52txt03.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "101") ?: "")
            if (cap7?.v52txt04.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "102") ?: "")
            if (cap7?.v52txt05.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "103") ?: "")
            if (cap7?.v52txt06.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "104") ?: "")
            if (cap7?.v52txt07.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "105") ?: "")
            if (cap7?.v52txt08.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "106") ?: "")
            if (cap7?.v52txt09.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "107") ?: "")
            if (cap7?.v52txt10.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "108") ?: "")
            if (cap7?.v52txt11.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "109") ?: "")
            if (cap7?.v52txt12.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "110") ?: "")
            if (cap7?.v52txt13.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "111") ?: "")
            if (cap7?.v52txt14.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "112") ?: "")

            if ((cap7?.v52txt15?.toInt() ?: 0) > 1 && cap7?.v52txt15desc.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "113") ?: "")
            else if (!cap7?.v52txt15desc.isNullOrEmpty() && cap7?.v52txt15.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "113") ?: "")


            infoCap.find { it.indexCap == CAP7_P12 }?.incons = returnList.isNotEmpty()
            //println("Cap7_1: --$cap7")
            return returnList
        }
    }


}