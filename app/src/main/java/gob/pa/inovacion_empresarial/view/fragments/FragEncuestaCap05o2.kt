package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo052RecursosHumanosBinding
import gob.pa.inovacion_empresarial.function.CreateIncon
import gob.pa.inovacion_empresarial.function.EdittextFormat
import gob.pa.inovacion_empresarial.function.Functions.hideKeyboard
import gob.pa.inovacion_empresarial.function.Functions.toEditable
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelCap5
import gob.pa.inovacion_empresarial.model.ModelTexWatchers
import java.text.DecimalFormat
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime


class FragEncuestaCap05o2 : Fragment() {

    private lateinit var bindingcap5o2: EncuestaCapitulo052RecursosHumanosBinding
    private lateinit var ctx: Context
    private val decimalFormat = DecimalFormat("#,###")
    private var row1EditTexts35: List<EditText> = emptyList()
    private var row2EditTexts35: List<EditText> = emptyList()
    private var row3EditTexts35: List<EditText> = emptyList()
    private var row4EditTexts35: List<EditText> = emptyList()
    
    private var row1EditTexts36: List<EditText> = emptyList()
    private var row2EditTexts36: List<EditText> = emptyList()
    
    private val textWatcherList = mutableListOf<ModelTexWatchers>()
    //private val dvmCap4: DVModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingcap5o2 = EncuestaCapitulo052RecursosHumanosBinding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingcap5o2.root
    }

    override fun onResume() {
        super.onResume()
        Mob.indiceFormulario = Mob.CAP5_P07
        val infoCap = Mob.infoCap.find { it.indexCap == Mob.CAP5_P07 }
        if (infoCap?.capView == false) fillOut()
        else onAction()
    }
    override fun onPause() {
        super.onPause()

        for (edittext in row1EditTexts35)
            edittext.onFocusChangeListener = null
        for (edittext in row2EditTexts35)
            edittext.onFocusChangeListener = null
        for (edittext in row3EditTexts35)
            edittext.onFocusChangeListener = null
        for (edittext in row4EditTexts35)
            edittext.onFocusChangeListener = null

        for (edittext in row1EditTexts36)
            edittext.onFocusChangeListener = null
        for (edittext in row2EditTexts36)
            edittext.onFocusChangeListener = null

        for (modelTexWatcher in textWatcherList) {
            modelTexWatcher.edittext.removeTextChangedListener(modelTexWatcher.watcher)
        }
        textWatcherList.clear()
    }

    @OptIn(ExperimentalTime::class)
    private fun onAction() {
        with (bindingcap5o2) {
            scrollForm.smoothScrollTo(0,0)
            
            row1EditTexts35 = listOf(
                txtCap535AHNac, txtCap535BHNac, txtCap535CHNac, txtCap535DHNac,
                txtCap535EHNac, txtCap535FHNac, txtCap535GHNac, txtCap535HHNac
            )
            row2EditTexts35 = listOf(
                txtCap535AHExt, txtCap535BHExt, txtCap535CHExt, txtCap535DHExt,
                txtCap535EHExt, txtCap535FHExt, txtCap535GHExt, txtCap535HHExt
            )

            row3EditTexts35 = listOf(
                txtCap535AMNac, txtCap535BMNac, txtCap535CMNac, txtCap535DMNac,
                txtCap535EMNac, txtCap535FMNac, txtCap535GMNac, txtCap535HMNac
            )
            row4EditTexts35 = listOf(
                txtCap535AMExt, txtCap535BMExt, txtCap535CMExt, txtCap535DMExt,
                txtCap535EMExt, txtCap535FMExt, txtCap535GMExt, txtCap535HMExt
            )

            row1EditTexts36 = listOf(txtCap536A2021, txtCap536B2021)
            row2EditTexts36 = listOf(txtCap536A2022, txtCap536B2022)

            if (rbtCap537No.isChecked) layoutCap538.isVisible = false
            txtCap5381.isVisible = checkCap5381.isChecked
            txtCap5382.isVisible = checkCap5382.isChecked
            txtCap5383.isVisible = checkCap5383.isChecked
            txtCap5384.isVisible = checkCap5384.isChecked

            rgroupCap537.setOnCheckedChangeListener { _, id ->
                hideKeyboard()
                when (id) {
                    rbtCap537Si.id -> { layoutCap538.visibility = View.VISIBLE }
                    rbtCap537No.id -> { layoutCap538.visibility = View.GONE }
                }
            }

            checkCap5381.setOnClickListener {
                txtCap5381.isEnabled = checkCap5381.isChecked
                if (checkCap5381.isChecked)
                    txtCap5381.visibility = View.VISIBLE
                else txtCap5381.visibility = View.INVISIBLE
            }
            checkCap5382.setOnClickListener {
                txtCap5382.isEnabled = checkCap5382.isChecked
                if (checkCap5382.isChecked)
                    txtCap5382.visibility = View.VISIBLE
                else txtCap5382.visibility = View.INVISIBLE
            }
            checkCap5383.setOnClickListener {
                txtCap5383.isEnabled = checkCap5383.isChecked
                if (checkCap5383.isChecked)
                    txtCap5383.visibility = View.VISIBLE
                else txtCap5383.visibility = View.INVISIBLE
            }
            checkCap5384.setOnClickListener {
                txtCap5384.isEnabled = checkCap5384.isChecked
                txtCap5384Otro.isEnabled = checkCap5384.isChecked
                if (checkCap5384.isChecked) txtCap5384.visibility = View.VISIBLE
                else {
                    txtCap5384.visibility = View.INVISIBLE
                    txtCap5384Otro.text?.clear()
                }
            }


            for (i in 0 until tb35.childCount) {
                val view = tb35.getChildAt(i)
                if (view is EditText) {
                    view.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                        if (hasFocus) {
                            if (view.text.toString() == "0") view.text?.clear()
                            if (row1EditTexts35.contains(view)) {
                                val modelTexWatchers =
                                    EdittextFormat.edittextBigSum(
                                        view,
                                        row1EditTexts35,
                                        txtCap5351HNac
                                    )
                                textWatcherList.add(modelTexWatchers)
                            } else if (row2EditTexts35.contains(view)) {
                                val modelTexWatchers =
                                    EdittextFormat.edittextBigSum(
                                        view,
                                        row2EditTexts35,
                                        txtCap5351HExt
                                    )
                                textWatcherList.add(modelTexWatchers)
                            } else if (row3EditTexts35.contains(view)) {
                                val modelTexWatchers =
                                    EdittextFormat.edittextBigSum(
                                        view,
                                        row3EditTexts35,
                                        txtCap5351MNac
                                    )
                                textWatcherList.add(modelTexWatchers)
                            } else if (row4EditTexts35.contains(view)) {
                                val modelTexWatchers =
                                    EdittextFormat.edittextBigSum(
                                        view,
                                        row4EditTexts35,
                                        txtCap5351MExt
                                    )
                                textWatcherList.add(modelTexWatchers)
                            }
                        } else if (view.text.isNullOrEmpty()) { //---- CERO al cambiar y esta empty
                            view.text = "0".toEditable()
                        } else { //---- Limpiar Textwatchers del view si sobrepasan cierto limite
                            if (textWatcherList.size > Mob.MAX_TEXWATCHER_MANY_ROWS) {
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

            for(i in 0 until tb36.childCount) {
                val view = tb36.getChildAt(i)
                if (view is EditText) {
                    view.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                        if (hasFocus) {
                            if (view.text.toString() == "0") view.text?.clear()
                            if (row1EditTexts36.contains(view)) {
                                val modelTexWatchers =
                                    EdittextFormat.edittextBigSum(
                                        view,
                                        row1EditTexts36,
                                        txtCap53612021)
                                textWatcherList.add(modelTexWatchers)
                            }
                            else if (row2EditTexts36.contains(view)) {
                                val modelTexWatchers =
                                    EdittextFormat.edittextBigSum(
                                        view,
                                        row2EditTexts36,
                                        txtCap53612022)
                                textWatcherList.add(modelTexWatchers)
                            }
                        }
                        else if (view.text.isNullOrEmpty()) { view.text = "0".toEditable() }
                        else {
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
        with(bindingcap5o2) {
            fillOut35(cap5)

            txtCap536A2021.text = cap5?.v36txtempNac21?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable()
            txtCap536A2022.text = cap5?.v36txtempNac22?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable()
            txtCap536B2021.text = cap5?.v36txtempExt21?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable()
            txtCap536B2022.text = cap5?.v36txtempExt22?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable()
            txtCap53612021.text = cap5?.v36txtempT21?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable()
            txtCap53612022.text = cap5?.v36txtempT22?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable()

            when (cap5?.v37check) {
                true -> rbtCap537Si.isChecked = true
                false -> rbtCap537No.isChecked = true
                else ->  rgroupCap537.clearCheck()
            }

            if (cap5?.v38check1 == true) {
                checkCap5381.isChecked = true
                txtCap5381.text = cap5.v38txt1?.toEditable() ?: "".toEditable()
            } else checkCap5381.isChecked = false

            if (cap5?.v38check2 == true) {
                checkCap5382.isChecked = true
                txtCap5382.text = cap5.v38txt2?.toEditable() ?: "".toEditable()
            } else checkCap5382.isChecked = false

            if (cap5?.v38check3 == true) {
                checkCap5383.isChecked = true
                txtCap5383.text = cap5.v38txt3?.toEditable() ?: "".toEditable()
            } else checkCap5383.isChecked = false

            if (cap5?.v38check4 == true) {
                checkCap5384.isChecked = true
                txtCap5384Otro.text = cap5.v38txt4desc?.toEditable() ?: "".toEditable()
                txtCap5384.text = cap5.v38txt4?.toEditable() ?: "".toEditable()
            } else checkCap5384.isChecked = false

        }
        Mob.infoCap.find { it.indexCap == Mob.CAP5_P07 }?.capView = true
        onAction()
    }

    private fun fillOut35(cap5: ModelCap5?) {
        with(bindingcap5o2) {
            txtCap535AHNac.text = cap5?.v35txthomNaca?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable() // A Hombres
            txtCap535AHExt.text = cap5?.v35txthomExta?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable()

            txtCap535AMNac.text = cap5?.v35txtmujNaca?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable() // A Mujeres
            txtCap535AMExt.text = cap5?.v35txtmujExta?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable()

            txtCap535BHNac.text = cap5?.v35txthomNacb?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable() // B Hombres
            txtCap535BHExt.text = cap5?.v35txthomExtb?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable()

            txtCap535BMNac.text = cap5?.v35txtmujNacb?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable() // B Mujeres
            txtCap535BMExt.text = cap5?.v35txtmujExtb?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable()

            txtCap535CHNac.text = cap5?.v35txthomNacc?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable() // C Hombres
            txtCap535CHExt.text = cap5?.v35txthomExtc?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable()

            txtCap535CMNac.text = cap5?.v35txtmujNacc?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable() // C Mujeres
            txtCap535CMExt.text = cap5?.v35txtmujExtc?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable()

            txtCap535DHNac.text = cap5?.v35txthomNacd?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable() // D Hombres
            txtCap535DHExt.text = cap5?.v35txthomExtd?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable()

            txtCap535DMNac.text = cap5?.v35txtmujNacd?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable() // D Mujeres
            txtCap535DMExt.text = cap5?.v35txtmujExtd?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable()

            txtCap535EHNac.text = cap5?.v35txthomNace?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable() // E Hombres
            txtCap535EHExt.text = cap5?.v35txthomExte?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable()

            txtCap535EMNac.text = cap5?.v35txtmujNace?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable() // E Mujeres
            txtCap535EMExt.text = cap5?.v35txtmujExte?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable()

            txtCap535FHNac.text = cap5?.v35txthomNacf?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable() // F Hombres
            txtCap535FHExt.text = cap5?.v35txthomExtf?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable()

            txtCap535FMNac.text = cap5?.v35txtmujNacf?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable() // F Mujeres
            txtCap535FMExt.text = cap5?.v35txtmujExtf?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable()

            txtCap535GHNac.text = cap5?.v35txthomNacg?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable() // G Hombres
            txtCap535GHExt.text = cap5?.v35txthomExtg?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable()

            txtCap535GMNac.text = cap5?.v35txtmujNacg?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable() // G Mujeres
            txtCap535GMExt.text = cap5?.v35txtmujExtg?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable()

            txtCap535HHNac.text = cap5?.v35txthomNach?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable() // H Hombres
            txtCap535HHExt.text = cap5?.v35txthomExth?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable()

            txtCap535HMNac.text = cap5?.v35txtmujNach?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable() // H Mujeres
            txtCap535HMExt.text = cap5?.v35txtmujExth?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable()

            txtCap5351HNac.text = cap5?.v35txthomNacT?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable() // 1 Hombres
            txtCap5351HExt.text = cap5?.v35txthomExtT?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable()

            txtCap5351MNac.text = cap5?.v35txtmujNacT?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable() // 1 Mujeres
            txtCap5351MExt.text = cap5?.v35txtmujExtT?.toDoubleOrNull()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable()
        }
    }


    fun saveCap(): List<String> {
        with(bindingcap5o2) {
            Mob.cap5 = ModelCap5(
                Mob.cap5?.id,
                Mob.cap5?.ncontrol,
                Mob.cap5?.v30txt21a,
                Mob.cap5?.v30txt21b,
                Mob.cap5?.v30txt21T,
                Mob.cap5?.v30txt22a,
                Mob.cap5?.v30txt22b,
                Mob.cap5?.v30txt22T,
                Mob.cap5?.v31check21a,
                Mob.cap5?.v31check22a,
                Mob.cap5?.v31check21b,
                Mob.cap5?.v31check22b,
                Mob.cap5?.v31check21c,
                Mob.cap5?.v31check22c,
                Mob.cap5?.v31check21d,
                Mob.cap5?.v31check22d,
                Mob.cap5?.v31check21e,
                Mob.cap5?.v31check22e,
                Mob.cap5?.v32check21,
                Mob.cap5?.v32check22,
                Mob.cap5?.v33txt1s21,
                Mob.cap5?.v33txt1s22,
                Mob.cap5?.v33txt2s21,
                Mob.cap5?.v33txt2s22,
                Mob.cap5?.v33txt3s21,
                Mob.cap5?.v33txt3s22,
                Mob.cap5?.v34check1o21,
                Mob.cap5?.v34check1o22,
                Mob.cap5?.v34check2o21,
                Mob.cap5?.v34check2o22,
                Mob.cap5?.v34check3o21,
                Mob.cap5?.v34check3o22,
                txtCap535Otro.text.toString().replace(",", "").ifEmpty { null },
                txtCap535AHNac.text.toString().replace(",", "").ifEmpty { null },
                txtCap535AHExt.text.toString().replace(",", "").ifEmpty { null },
                txtCap535BHNac.text.toString().replace(",", "").ifEmpty { null },
                txtCap535BHExt.text.toString().replace(",", "").ifEmpty { null },
                txtCap535CHNac.text.toString().replace(",", "").ifEmpty { null },
                txtCap535CHExt.text.toString().replace(",", "").ifEmpty { null },
                txtCap535DHNac.text.toString().replace(",", "").ifEmpty { null },
                txtCap535DHExt.text.toString().replace(",", "").ifEmpty { null },
                txtCap535EHNac.text.toString().replace(",", "").ifEmpty { null },
                txtCap535EHExt.text.toString().replace(",", "").ifEmpty { null },
                txtCap535FHNac.text.toString().replace(",", "").ifEmpty { null },
                txtCap535FHExt.text.toString().replace(",", "").ifEmpty { null },
                txtCap535GHNac.text.toString().replace(",", "").ifEmpty { null },
                txtCap535GHExt.text.toString().replace(",", "").ifEmpty { null },
                txtCap535HHNac.text.toString().replace(",", "").ifEmpty { null },
                txtCap535HHExt.text.toString().replace(",", "").ifEmpty { null },
                txtCap5351HNac.text.toString().replace(",", "").ifEmpty { null },
                txtCap5351HExt.text.toString().replace(",", "").ifEmpty { null },
                txtCap535AMNac.text.toString().replace(",", "").ifEmpty { null },
                txtCap535AMExt.text.toString().replace(",", "").ifEmpty { null },
                txtCap535BMNac.text.toString().replace(",", "").ifEmpty { null },
                txtCap535BMExt.text.toString().replace(",", "").ifEmpty { null },
                txtCap535CMNac.text.toString().replace(",", "").ifEmpty { null },
                txtCap535CMExt.text.toString().replace(",", "").ifEmpty { null },
                txtCap535DMNac.text.toString().replace(",", "").ifEmpty { null },
                txtCap535DMExt.text.toString().replace(",", "").ifEmpty { null },
                txtCap535EMNac.text.toString().replace(",", "").ifEmpty { null },
                txtCap535EMExt.text.toString().replace(",", "").ifEmpty { null },
                txtCap535FMNac.text.toString().replace(",", "").ifEmpty { null },
                txtCap535FMExt.text.toString().replace(",", "").ifEmpty { null },
                txtCap535GMNac.text.toString().replace(",", "").ifEmpty { null },
                txtCap535GMExt.text.toString().replace(",", "").ifEmpty { null },
                txtCap535HMNac.text.toString().replace(",", "").ifEmpty { null },
                txtCap535HMExt.text.toString().replace(",", "").ifEmpty { null },
                txtCap5351MNac.text.toString().replace(",", "").ifEmpty { null },
                txtCap5351MExt.text.toString().replace(",", "").ifEmpty { null },
                txtCap536A2021.text.toString().replace(",", "").ifEmpty { null },
                txtCap536A2022.text.toString().replace(",", "").ifEmpty { null },
                txtCap536B2021.text.toString().replace(",", "").ifEmpty { null },
                txtCap536B2022.text.toString().replace(",", "").ifEmpty { null },
                txtCap53612021.text.toString().replace(",", "").ifEmpty { null },
                txtCap53612022.text.toString().replace(",", "").ifEmpty { null },
                if (rbtCap537Si.isChecked) true else if (rbtCap537No.isChecked) false else null,
                if (rbtCap537Si.isChecked && checkCap5381.isChecked) true else null,
                if (rbtCap537Si.isChecked && checkCap5381.isChecked) txtCap5381.text.toString()
                    .ifEmpty { null } else null,
                if (rbtCap537Si.isChecked && checkCap5382.isChecked) true else null,
                if (rbtCap537Si.isChecked && checkCap5382.isChecked) txtCap5382.text.toString()
                    .ifEmpty { null } else null,
                if (rbtCap537Si.isChecked && checkCap5383.isChecked) true else null,
                if (rbtCap537Si.isChecked && checkCap5383.isChecked) txtCap5383.text.toString()
                    .ifEmpty { null } else null,
                if (rbtCap537Si.isChecked && checkCap5384.isChecked) txtCap5384Otro.text.toString()
                    .ifEmpty { null } else null,
                if (rbtCap537Si.isChecked && checkCap5384.isChecked) true else null,
                if (rbtCap537Si.isChecked && checkCap5384.isChecked) txtCap5384.text.toString()
                    .ifEmpty { null } else null,
            )
        }
        return viewCap()
    }

    private fun viewCap(): List<String> {
        with (Mob) {
            val returnList: ArrayList<String> = ArrayList()
            if (cap5?.v35txthomNacT.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx,"32") ?: "")
            if (cap5?.v35txthomExtT.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx,"33") ?: "")
            if (cap5?.v35txtmujNacT.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx,"34") ?: "")
            if (cap5?.v35txtmujExtT.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx,"35") ?: "")

            if (cap5?.v36txtempT21.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx,"36") ?: "")
            if (cap5?.v36txtempT22.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx,"37") ?: "")
            if (cap5?.v37check == null)
                returnList.add(CreateIncon.inconsistencia(ctx,"38") ?: "")

            if (cap5?.v37check == true)
                if (cap5?.v38txt1.isNullOrEmpty() &&
                    cap5?.v38txt2.isNullOrEmpty())
                    if (cap5?.v38txt3.isNullOrEmpty() &&
                        cap5?.v38txt4.isNullOrEmpty())
                        returnList.add(CreateIncon.inconsistencia(ctx,"39")?: "")

            if (cap5?.v38check1 == true && cap5?.v38txt1.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx,"40") ?: "")
            if (cap5?.v38check2 == true  && cap5?.v38txt2.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx,"41") ?: "")
            if (cap5?.v38check3 == true  && cap5?.v38txt3.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx,"42") ?: "")
            if (cap5?.v38check4 == true  && cap5?.v38txt4.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx,"43") ?: "")

            infoCap.find { it.indexCap == CAP5_P07 }?.incons = returnList.isNotEmpty()
            //println("Cap5-part2: --$cap5")
            return returnList
        }
    }
}