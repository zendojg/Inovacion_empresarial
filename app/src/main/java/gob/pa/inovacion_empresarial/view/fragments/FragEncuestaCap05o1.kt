package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo051VentasYExpoBinding
import gob.pa.inovacion_empresarial.function.CreateIncon
import gob.pa.inovacion_empresarial.function.EdittextFormat
import gob.pa.inovacion_empresarial.function.Functions.toEditable
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelCap5
import gob.pa.inovacion_empresarial.model.ModelTexWatchers
import java.text.DecimalFormat

class FragEncuestaCap05o1 : Fragment() {

    private lateinit var bindingcap5o1: EncuestaCapitulo051VentasYExpoBinding
    private lateinit var ctx: Context
    private val textWatcherList = mutableListOf<ModelTexWatchers>()
    private var row1EditTexts: List<EditText> = emptyList()
    private var row2EditTexts: List<EditText> = emptyList()
    //private val dvmCap4: DVModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingcap5o1 = EncuestaCapitulo051VentasYExpoBinding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingcap5o1.root
    }

    override fun onResume() {
        super.onResume()
        Mob.indiceFormulario = Mob.CAP5_P06
        val infoCap = Mob.infoCap.find { it.indexCap == Mob.CAP5_P06 }
        if (infoCap?.capView == false) fillOut()
        else onAction()
    }

    override fun onPause() {
        super.onPause()
        for (edittext in row1EditTexts)
            edittext.onFocusChangeListener = null
        for (edittext in row2EditTexts)
            edittext.onFocusChangeListener = null

        for (modelTexWatcher in textWatcherList) {
            modelTexWatcher.edittext.removeTextChangedListener(modelTexWatcher.watcher)
        }
        textWatcherList.clear()
    }

    private fun onAction() {
        with(bindingcap5o1) {
            row1EditTexts = listOf(txtCap530A2021, txtCap530B2021)
            row2EditTexts = listOf(txtCap530A2022, txtCap530B2022)
            layoutCap5332021.isVisible = rbtCap532ASi.isChecked
            layoutCap5332022.isVisible = rbtCap532BSi.isChecked

            rgroupCap532a.setOnCheckedChangeListener { _, id ->
                val isVisible = id == rbtCap532ASi.id
                layoutCap5332021.isVisible = isVisible
            }
            rgroupCap532b.setOnCheckedChangeListener { _, id ->

                when (id) {
                    rbtCap532BSi.id -> layoutCap5332022.isVisible = true
                    rbtCap532BNo.id -> layoutCap5332022.isVisible = false
                }
            }

            for(index in 0 until tb30.childCount) {
                val view = tb30.getChildAt(index)
                if (view is EditText) {
                    view.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                        if (hasFocus) {
                            if (view.text.toString() == "0") view.text?.clear()
                            if (row1EditTexts.contains(view)) {
                                val modelTexWatchers =
                                    EdittextFormat.edittextBigSum(view, row1EditTexts, txtCap53012021)
                                textWatcherList.add(modelTexWatchers)
                            }
                            else if (row2EditTexts.contains(view)) {
                                val modelTexWatchers =
                                    EdittextFormat.edittextBigSum(view, row2EditTexts, txtCap53012022)
                                textWatcherList.add(modelTexWatchers)
                            }
                        } else if (view.text.isNullOrEmpty()) {
                            view.text = "0".toEditable()
                        } else {
                            if (textWatcherList.size > Mob.MAX_TEXWATCHER_4ROWS) {
                                for (modelTexWatcher in textWatcherList) {
                                    modelTexWatcher.edittext.removeTextChangedListener(
                                        modelTexWatcher.watcher
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun fillOut() {
        val cap5 = Mob.formComp?.cap5
        with(bindingcap5o1) {
            scrollForm.smoothScrollTo(0,0)
            val decimalFormat = DecimalFormat("#,###")

            txtCap53012021.text = cap5?.v30txt21T?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable()
            txtCap53012022.text = cap5?.v30txt22T?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable()
            txtCap530A2021.text = cap5?.v30txt21a?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable()
            txtCap530A2022.text = cap5?.v30txt22a?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable()
            txtCap530B2021.text = cap5?.v30txt21b?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable()
            txtCap530B2022.text = cap5?.v30txt22b?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable()

            txtCap53312021.text = cap5?.v33txt1s21?.toEditable() ?: "".toEditable()
            txtCap53322021.text = cap5?.v33txt2s21?.toEditable() ?: "".toEditable()
            txtCap53332021.text = cap5?.v33txt3s21?.toEditable() ?: "".toEditable()

            txtCap53312022.text = cap5?.v33txt1s22?.toEditable() ?: "".toEditable()
            txtCap53322022.text = cap5?.v33txt2s22?.toEditable() ?: "".toEditable()
            txtCap53332022.text = cap5?.v33txt3s22?.toEditable() ?: "".toEditable()

            when {
                cap5?.v31check21a == true -> rbtCap531A2021.isChecked = true //--- Mejorable con map
                cap5?.v31check21b == true -> rbtCap531B2021.isChecked = true
                cap5?.v31check21c == true -> rbtCap531C2021.isChecked = true
                cap5?.v31check21d == true -> rbtCap531D2021.isChecked = true
                cap5?.v31check21e == true -> rbtCap531E2021.isChecked = true
                else -> { rgroupCap5312021.clearCheck() }
            }
            when {
                cap5?.v31check22a == true -> rbtCap531A2022.isChecked = true
                cap5?.v31check22b == true -> rbtCap531B2022.isChecked = true
                cap5?.v31check22c == true -> rbtCap531C2022.isChecked = true
                cap5?.v31check22d == true -> rbtCap531D2022.isChecked = true
                cap5?.v31check22e == true -> rbtCap531E2022.isChecked = true
                else -> { rgroupCap5312022.clearCheck() }
            }

            when (cap5?.v32check21) {
                true -> rbtCap532ASi.isChecked = true
                false -> rbtCap532ANo.isChecked = true
                else ->  rgroupCap532a.clearCheck()
            }
            when (cap5?.v32check22) {
                true -> rbtCap532BSi.isChecked = true
                false -> rbtCap532BNo.isChecked = true
                else ->  rgroupCap532b.clearCheck()
            }

            when {
                cap5?.v34check1o21 == true -> rbtCap53412021.isChecked = true
                cap5?.v34check2o21 == true -> rbtCap53422021.isChecked = true
                cap5?.v34check3o21 == true -> rbtCap53432021.isChecked = true
                else ->  rgroupCap5342021.clearCheck()
            }
            when {
                cap5?.v34check1o22 == true -> rbtCap53412022.isChecked = true
                cap5?.v34check2o22 == true -> rbtCap53422022.isChecked = true
                cap5?.v34check3o22 == true -> rbtCap53432022.isChecked = true
                else ->  rgroupCap5342022.clearCheck()
            }

            Mob.infoCap.find { it.indexCap == Mob.CAP5_P06 }?.capView = true
            onAction()
        }
    }

    fun saveCap(): List<String> {
        with(bindingcap5o1) {
            Mob.cap5 = ModelCap5(
                Mob.cap5?.id,
                Mob.cap5?.ncontrol,
                txtCap530A2021.text.toString().replace(",", "").ifEmpty { null },
                txtCap530B2021.text.toString().replace(",", "").ifEmpty { null },
                txtCap53012021.text.toString().replace(",", "").ifEmpty { null },
                txtCap530A2022.text.toString().replace(",", "").ifEmpty { null },
                txtCap530B2022.text.toString().replace(",", "").ifEmpty { null },
                txtCap53012022.text.toString().replace(",", "").ifEmpty { null },
                if (rbtCap531A2021.isChecked) true else null,
                if (rbtCap531A2022.isChecked) true else null,
                if (rbtCap531B2021.isChecked) true else null,
                if (rbtCap531B2022.isChecked) true else null,
                if (rbtCap531C2021.isChecked) true else null,
                if (rbtCap531C2022.isChecked) true else null,
                if (rbtCap531D2021.isChecked) true else null,
                if (rbtCap531D2022.isChecked) true else null,
                if (rbtCap531E2021.isChecked) true else null,
                if (rbtCap531E2022.isChecked) true else null,
                if (rbtCap532ASi.isChecked) true else if (rbtCap532ANo.isChecked) false else null,
                if (rbtCap532BSi.isChecked) true else if (rbtCap532BNo.isChecked) false else null,
                if (rbtCap532ASi.isChecked) txtCap53312021.text.toString()
                    .ifEmpty { null } else null,
                if (rbtCap532BSi.isChecked) txtCap53312022.text.toString()
                    .ifEmpty { null } else null,
                if (rbtCap532ASi.isChecked) txtCap53322021.text.toString()
                    .ifEmpty { null } else null,
                if (rbtCap532BSi.isChecked) txtCap53322022.text.toString()
                    .ifEmpty { null } else null,
                if (rbtCap532ASi.isChecked) txtCap53332021.text.toString()
                    .ifEmpty { null } else null,
                if (rbtCap532BSi.isChecked) txtCap53332022.text.toString()
                    .ifEmpty { null } else null,
                if (rbtCap53412021.isChecked) true else null,
                if (rbtCap53412022.isChecked) true else null,
                if (rbtCap53422021.isChecked) true else null,
                if (rbtCap53422022.isChecked) true else null,
                if (rbtCap53432021.isChecked) true else null,
                if (rbtCap53432022.isChecked) true else null,
                Mob.cap5?.v35txtOtro,
                Mob.cap5?.v35txthomNaca,
                Mob.cap5?.v35txthomExta,
                Mob.cap5?.v35txthomNacb,
                Mob.cap5?.v35txthomExtb,
                Mob.cap5?.v35txthomNacc,
                Mob.cap5?.v35txthomExtc,
                Mob.cap5?.v35txthomNacd,
                Mob.cap5?.v35txthomExtd,
                Mob.cap5?.v35txthomNace,
                Mob.cap5?.v35txthomExte,
                Mob.cap5?.v35txthomNacf,
                Mob.cap5?.v35txthomExtf,
                Mob.cap5?.v35txthomNacg,
                Mob.cap5?.v35txthomExtg,
                Mob.cap5?.v35txthomNach,
                Mob.cap5?.v35txthomExth,
                Mob.cap5?.v35txthomNacT,
                Mob.cap5?.v35txthomExtT,
                Mob.cap5?.v35txtmujNaca,
                Mob.cap5?.v35txtmujExta,
                Mob.cap5?.v35txtmujNacb,
                Mob.cap5?.v35txtmujExtb,
                Mob.cap5?.v35txtmujNacc,
                Mob.cap5?.v35txtmujExtc,
                Mob.cap5?.v35txtmujNacd,
                Mob.cap5?.v35txtmujExtd,
                Mob.cap5?.v35txtmujNace,
                Mob.cap5?.v35txtmujExte,
                Mob.cap5?.v35txtmujNacf,
                Mob.cap5?.v35txtmujExtf,
                Mob.cap5?.v35txtmujNacg,
                Mob.cap5?.v35txtmujExtg,
                Mob.cap5?.v35txtmujNach,
                Mob.cap5?.v35txtmujExth,
                Mob.cap5?.v35txtmujNacT,
                Mob.cap5?.v35txtmujExtT,
                Mob.cap5?.v36txtempNac21,
                Mob.cap5?.v36txtempNac22,
                Mob.cap5?.v36txtempExt21,
                Mob.cap5?.v36txtempExt22,
                Mob.cap5?.v36txtempT21,
                Mob.cap5?.v36txtempT22,
                Mob.cap5?.v37check,
                Mob.cap5?.v38check1,
                Mob.cap5?.v38txt1,
                Mob.cap5?.v38check2,
                Mob.cap5?.v38txt2,
                Mob.cap5?.v38check3,
                Mob.cap5?.v38txt3,
                Mob.cap5?.v38txt4desc,
                Mob.cap5?.v38check4,
                Mob.cap5?.v38txt4,
            )
        }
        return viewCap()
    }

    private fun viewCap(): List<String> {
        with(Mob) {
            val returnList: ArrayList<String> = ArrayList()
            if (cap5?.v30txt21T.isNullOrEmpty() || cap5?.v30txt22T.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "18") ?: "")
            else if (cap5?.v30txt21T == "0" && cap5?.v30txt22T == "0")
                returnList.add(CreateIncon.inconsistencia(ctx, "18") ?: "")

            if (cap5?.v32check21 == null || cap5?.v32check22 == null)
                returnList.add(CreateIncon.inconsistencia(ctx, "25") ?: "")
            else if (cap5?.v32check21 == true) {
                if (cap5?.v33txt1s21.isNullOrEmpty() &&
                    cap5?.v33txt2s21.isNullOrEmpty() &&
                    cap5?.v33txt3s21.isNullOrEmpty())
                    returnList.add(CreateIncon.inconsistencia(ctx, "26") ?: "")
            }
            else if (cap5?.v32check22 == true) {
                if (cap5?.v33txt1s22.isNullOrEmpty() &&
                    cap5?.v33txt2s22.isNullOrEmpty() &&
                    cap5?.v33txt3s22.isNullOrEmpty())
                    returnList.add(CreateIncon.inconsistencia(ctx, "27") ?: "")
            }

            if (bindingcap5o1.rgroupCap5342021.checkedRadioButtonId == -1 &&
                !cap5?.v30txt21T.isNullOrEmpty() && cap5?.v30txt21T != "0")
                returnList.add(CreateIncon.inconsistencia(ctx, "601") ?: "")
            if (bindingcap5o1.rgroupCap5342022.checkedRadioButtonId == -1 &&
                !cap5?.v30txt22T.isNullOrEmpty() && cap5?.v30txt22T != "0")
                returnList.add(CreateIncon.inconsistencia(ctx, "602") ?: "")

            infoCap.find { it.indexCap == CAP5_P06 }?.incons = returnList.isNotEmpty()
            //println("Cap5-part1: --$cap5")
            return returnList
        }
    }
}