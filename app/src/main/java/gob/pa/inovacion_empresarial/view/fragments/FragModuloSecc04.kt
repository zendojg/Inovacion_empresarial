package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import gob.pa.inovacion_empresarial.databinding.ModuloSeccion04Binding
import gob.pa.inovacion_empresarial.function.CreateIncon
import gob.pa.inovacion_empresarial.function.EdittextFormat
import gob.pa.inovacion_empresarial.function.Functions.hideKeyboard
import gob.pa.inovacion_empresarial.function.Functions.toEditable
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelMod
import gob.pa.inovacion_empresarial.model.ModelTexWatchers
import java.text.DecimalFormat

class FragModuloSecc04: Fragment() {
    private lateinit var bindingmod4: ModuloSeccion04Binding
    private lateinit var ctx: Context
    private val textWatcherList = mutableListOf<ModelTexWatchers>()
    private var row1EditTexts: List<EditText> = emptyList()
    private var row2EditTexts: List<EditText> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingmod4 = ModuloSeccion04Binding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingmod4.root
    }

    override fun onResume() {
        super.onResume()
        Mob.indiceFormulario = Mob.SEC4_P23
        val infoCap = Mob.infoCap.find { it.indexCap == Mob.SEC4_P23 }
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
        row1EditTexts = emptyList()
        row2EditTexts = emptyList()
        textWatcherList.clear()
    }

    private fun onAction() {
        with(bindingmod4) {
            scrollForm.isVisible = Mob.capMod?.v1check != false
            scrollForm.smoothScrollTo(0,0)
            row1EditTexts = listOf(txtSecc046p1, txtSecc046p2, txtSecc046p3, txtSecc046p4)
            row2EditTexts = listOf(txtSecc04101, txtSecc04102, txtSecc04103, txtSecc04104)

            fun colorTxt() {
                val p6porcent = lb6nm100.text?.toString()?.toInt()
                if (p6porcent != Mob.PORCENT100) lb6nm100.setTextColor(Color.RED)
                else lb6nm100.setTextColor(Color.GREEN)
            }
            colorTxt()
            for (index in 0 until linearSecc46Txt.childCount) {
                val view = linearSecc46Txt.getChildAt(index)
                if (view is TextInputLayout) {
                    val editText = view.editText
//                    val foundItem = textWatcherList.find { it.edittext == editText }
//                    if (foundItem == null)
                    editText?.onFocusChangeListener =
                        View.OnFocusChangeListener { _, hasFocus ->
                            if (hasFocus) {
                                if (row1EditTexts.contains(editText) && editText != null) {
                                    textWatcherList.add(
                                        EdittextFormat.edittextSum100(
                                            editText,
                                            row1EditTexts,
                                            lb6nm100
                                        )
                                    )
                                }
                            } else if (textWatcherList.size > Mob.MAX_TEXWATCHER_4ROWS)
                                deleteWatchers()
                        }
                }
            }

            for (index in 0 until layoutSecc410.childCount) {
                val view = layoutSecc410.getChildAt(index)
                if (view is TextInputLayout) {
                    val editText = view.editText
//                    val foundItem = textWatcherList.find { it.edittext == editText }
//                    if (foundItem == null)
                    editText?.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                        if (hasFocus) {
                            if (row2EditTexts.contains(editText) && editText != null) {
                                textWatcherList.add(EdittextFormat.edittextSum100(
                                    editText,
                                    row2EditTexts,
                                    txtSecc04105))
                            }
                        } else if (textWatcherList.size > Mob.MAX_TEXWATCHER_MANY_ROWS)
                            deleteWatchers()
                    }
                }
            }
            lb6nm100.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {/**/}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {/**/}
                override fun afterTextChanged(s: Editable?) {
                    textWatcherList.add(ModelTexWatchers(lb6nm100, this))
                    colorTxt()
                }
            })

            if (rbtSecc047No.isChecked) layoutSecc48.isVisible = false
            rgroupSecc047.setOnCheckedChangeListener { _, id ->
                hideKeyboard()
                when (id) {
                    rbtSecc047Si.id -> layoutSecc48.isVisible = true
                    rbtSecc047No.id -> layoutSecc48.isVisible = false
                }
            }
            if (rbtSecc049No.isChecked) {
                txtSecc049ly.isVisible = false
                layoutSecc410.isVisible = false
            } else if (rbtSecc049Si.isChecked) {
                txtSecc049ly.isVisible = true
                layoutSecc410.isVisible = true
            }
            rgroupSecc049.setOnCheckedChangeListener { _, id ->
                hideKeyboard()
                when (id) {
                    rbtSecc049Si.id -> {
                        txtSecc049ly.isVisible = true
                        layoutSecc410.isVisible = true
                    }
                    rbtSecc049No.id -> {
                        txtSecc049ly.isVisible = false
                        layoutSecc410.isVisible = false
                    }
                }
            }
            txtSecc048.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
//                    val foundItem = textWatcherList.find { it.edittext == txtSecc048 }
//                    if (foundItem == null)
                    textWatcherList.add(EdittextFormat.edittextMiles(txtSecc048))
                } else if (textWatcherList.size > Mob.MAX_TEXWATCHER_MANY_ROWS) deleteWatchers()
            }
            txtSecc049.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
//                    val foundItem = textWatcherList.find { it.edittext == txtSecc049 }
//                    if (foundItem == null)
                    textWatcherList.add(EdittextFormat.edittextMiles(txtSecc049))
                } else if (textWatcherList.size > Mob.MAX_TEXWATCHER_MANY_ROWS) deleteWatchers()
            }
        }
    }

    private fun deleteWatchers() {
        for (modelTextWatcher in textWatcherList) {
            if (modelTextWatcher.edittext != bindingmod4.lb6nm100)
                modelTextWatcher.edittext.removeTextChangedListener(modelTextWatcher.watcher)
        }
    }

    private fun fillOut() {
        val mod4 = Mob.formComp?.capMod
        val decimalFormat = DecimalFormat("#,###")
        with(bindingmod4) {
            var valor1 = mod4?.v6porcent1?.toIntOrNull() ?: 0
            var valor2 = mod4?.v6porcent2?.toIntOrNull() ?: 0
            var valor3 = mod4?.v6porcent3?.toIntOrNull() ?: 0
            var valor4 = mod4?.v6porcent4?.toIntOrNull() ?: 0
            txtSecc046p1.text = mod4?.v6porcent1?.toEditable() ?: "0".toEditable()
            txtSecc046p2.text = mod4?.v6porcent2?.toEditable() ?: "0".toEditable()
            txtSecc046p3.text = mod4?.v6porcent3?.toEditable() ?: "0".toEditable()
            txtSecc046p4.text = mod4?.v6porcent4?.toEditable() ?: "0".toEditable()
            var sumaTotal = valor1 + valor2 + valor3 + valor4
            lb6nm100.text = sumaTotal.toString().toEditable()

            when (mod4?.v7check) {
                true -> rbtSecc047Si.isChecked = true
                false -> rbtSecc047No.isChecked = true
                else -> rgroupSecc047.clearCheck()
            }
            txtSecc048.text = mod4?.v8txt?.toDouble()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "".toEditable()

            when (mod4?.v9check) {
                true -> rbtSecc049Si.isChecked = true
                false -> rbtSecc049No.isChecked = true
                else -> rgroupSecc047.clearCheck()
            }
            txtSecc049.text = mod4?.v9txt?.toDouble()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: "".toEditable()

            txtSecc04101.text = mod4?.v10porcent1?.toEditable() ?: "0".toEditable()
            txtSecc04102.text = mod4?.v10porcent2?.toEditable() ?: "0".toEditable()
            txtSecc04103.text = mod4?.v10porcent3?.toEditable() ?: "0".toEditable()
            txtSecc04104.text = mod4?.v10porcent4?.toEditable() ?: "0".toEditable()

            valor1 = mod4?.v10porcent1?.toIntOrNull() ?: 0
            valor2 = mod4?.v10porcent2?.toIntOrNull() ?: 0
            valor3 = mod4?.v10porcent3?.toIntOrNull() ?: 0
            valor4 = mod4?.v10porcent4?.toIntOrNull() ?: 0
            sumaTotal = valor1 + valor2 + valor3 + valor4
            txtSecc04105.text = sumaTotal.toString().toEditable()
        }
        Mob.infoCap.find { it.indexCap == Mob.SEC4_P23 }?.capView = true
        onAction()
    }

    fun saveCap(): List<String> {
        with (bindingmod4) {

            Mob.capMod = ModelMod(
                Mob.capMod?.id,
                Mob.capMod?.v1check,
                Mob.capMod?.v2check1,
                Mob.capMod?.v2check1a,
                Mob.capMod?.v2check1b,
                Mob.capMod?.v2check1c,
                Mob.capMod?.v2check1d,
                Mob.capMod?.v2txtDesc1d,
                Mob.capMod?.v2check2,
                //Mob.capMod?.v2txtnull: String?,
                Mob.capMod?.v3check1,
                Mob.capMod?.v3check2,
                Mob.capMod?.v3check3,
                Mob.capMod?.v3check4,
                Mob.capMod?.v4check,
                Mob.capMod?.v4check1a,
                Mob.capMod?.v4check1aPorcent,
                Mob.capMod?.v4check1b,
                Mob.capMod?.v4check1bPorcent,
                Mob.capMod?.v4check1c,
                Mob.capMod?.v4check1cPorcent,
                Mob.capMod?.v4check1d,
                Mob.capMod?.v4check1dPorcent,
                Mob.capMod?.v4check1e,
                Mob.capMod?.v4check1ePorcent,
                Mob.capMod?.v4check1eOther,
                Mob.capMod?.v5txt,//--------------
                if (Mob.capMod?.v1check != false)
                    txtSecc046p1.text.toString().ifEmpty { null } else null,
                if (Mob.capMod?.v1check != false)
                    txtSecc046p2.text.toString().ifEmpty { null } else null,
                if (Mob.capMod?.v1check != false)
                    txtSecc046p3.text.toString().ifEmpty { null } else null,
                if (Mob.capMod?.v1check != false)
                    txtSecc046p4.text.toString().ifEmpty { null } else null,
                if (Mob.capMod?.v1check != false && rbtSecc047Si.isChecked) true else
                    if (Mob.capMod?.v1check != false && rbtSecc047No.isChecked) false else null,
                if (Mob.capMod?.v1check != false && rbtSecc047Si.isChecked)
                    txtSecc048.text.toString().replace(",", "").ifEmpty { null } else null,
                if (Mob.capMod?.v1check != false && rbtSecc049Si.isChecked) true else
                    if (Mob.capMod?.v1check != false && rbtSecc049No.isChecked) false else null,
                if (Mob.capMod?.v1check != false && rbtSecc049Si.isChecked)
                    txtSecc049.text.toString().replace(",", "").ifEmpty { null } else null,
                if (Mob.capMod?.v1check != false && rbtSecc049Si.isChecked)
                    txtSecc04101.text.toString().ifEmpty { null } else null,
                if (Mob.capMod?.v1check != false && rbtSecc049Si.isChecked)
                    txtSecc04102.text.toString().ifEmpty { null } else null,
                if (Mob.capMod?.v1check != false && rbtSecc049Si.isChecked)
                    txtSecc04103.text.toString().ifEmpty { null } else null,
                if (Mob.capMod?.v1check != false && rbtSecc049Si.isChecked)
                    txtSecc04104.text.toString().ifEmpty { null } else null,
                Mob.obsModulo,
                Mob.capMod?.numControl
            )
        }
        return viewCap()
    }

    private fun viewCap(): List<String> {
        with(bindingmod4) {
            val returnList: ArrayList<String> = ArrayList()
            val porcentP6 =
                lb6nm100.text.toString().replace(",", "").toIntOrNull() ?: 0
            val porcentP10 =
                txtSecc04105.text.toString().replace(",", "").toIntOrNull() ?: 0

            if (Mob.capMod?.v1check == true) {
                if (porcentP6 == 0)
                    returnList.add(CreateIncon.inconsistencia(ctx, "306") ?: "")
                else if (porcentP6 != Mob.PORCENT100)
                    returnList.add(CreateIncon.inconsistencia(ctx, "307") ?: "")
                if (rgroupSecc047.checkedRadioButtonId == -1)
                    returnList.add(CreateIncon.inconsistencia(ctx, "308") ?: "")
                if (txtSecc048.text.isNullOrEmpty() || txtSecc048.text.toString() == "0")
                    returnList.add(CreateIncon.inconsistencia(ctx, "309") ?: "")
                if (rgroupSecc049.checkedRadioButtonId == -1)
                    returnList.add(CreateIncon.inconsistencia(ctx, "310") ?: "")
                if (porcentP10 != Mob.PORCENT100)
                    returnList.add(CreateIncon.inconsistencia(ctx, "311") ?: "")

            } else if (Mob.capMod?.v1check == null)
                returnList.add(CreateIncon.inconsistencia(ctx, "620") ?: "")

            Mob.infoCap.find { it.indexCap == Mob.SEC4_P23 }?.incons = returnList.isNotEmpty()
            //println("Secc4: --${Mob.capMod}")
            return returnList
        }
    }
}