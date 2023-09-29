package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.databinding.ModuloSeccion03Binding
import gob.pa.inovacion_empresarial.function.CreateIncon
import gob.pa.inovacion_empresarial.function.EdittextFormat
import gob.pa.inovacion_empresarial.function.Functions.hideKeyboard
import gob.pa.inovacion_empresarial.function.Functions.toEditable
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelMod
import gob.pa.inovacion_empresarial.model.ModelTexWatchers
import java.text.DecimalFormat

class FragModuloSecc03 : Fragment() {

    private lateinit var bindingmod3: ModuloSeccion03Binding
    private lateinit var ctx: Context
    private val textWatcherList = mutableListOf<ModelTexWatchers>()
    private val decimalFormat = DecimalFormat("#,###")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingmod3 = ModuloSeccion03Binding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingmod3.root
    }

    override fun onResume() {
        super.onResume()
        Mob.indiceFormulario = Mob.SEC3P22
        if (Mob.seesecc3) fillOut()
        else onAction()
    }
    override fun onPause() {
        super.onPause()

        for (modelTexWatcher in textWatcherList) {
            modelTexWatcher.editext.removeTextChangedListener(modelTexWatcher.watcher)
        }
        textWatcherList.clear()
    }
    private fun onAction() {
        with(bindingmod3) {
            scrollForm.isVisible = Mob.capMod?.v1check != false
            if (rbtSecc034ASi.isChecked) {
                layoutSecc341.isVisible = true
                layoutSecc35.isVisible = true
            } else {
                layoutSecc341.isVisible = false
                layoutSecc35.isVisible = false
            }
            val radioGroups = listOf(
                rgroupSecc034A,
                rgroupSecc034B,
                rgroupSecc034C,
                rgroupSecc034D,
                rgroupSecc034E
            )
            val radioButtons =
                listOf(rbtSecc034ASi, rbtSecc034BSi, rbtSecc034CSi, rbtSecc034DSi, rbtSecc034ESi)
            val textViews =
                listOf(txtSecc034Aly, txtSecc034Bly, txtSecc034Cly, txtSecc034Dly, txtSecc034Ely)

            for (i in radioGroups.indices) {
                radioGroups[i].setOnCheckedListener(radioButtons[i], textViews[i])
                textViews[i].visibility =
                    if (radioButtons[i].isChecked) View.VISIBLE else View.INVISIBLE
            }
            rgroupSecc034.setOnCheckedListener(rbtSecc034Si, layoutSecc35)

            rgroupSecc034A.setOnCheckedListener(rbtSecc034ASi, txtSecc034Aly)
            rgroupSecc034B.setOnCheckedListener(rbtSecc034BSi, txtSecc034Bly)
            rgroupSecc034C.setOnCheckedListener(rbtSecc034CSi, txtSecc034Cly)
            rgroupSecc034D.setOnCheckedListener(rbtSecc034DSi, txtSecc034Dly)
            rgroupSecc034E.setOnCheckedListener(rbtSecc034ESi, txtSecc034Ely)

            txtSecc035.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) textWatcherList.add(EdittextFormat.edittextMiles(txtSecc035))
                else if (textWatcherList.size > Mob.MAXTEXWATCHER4ROWS) {
                    for (modelTextWatcher in textWatcherList) {
                        modelTextWatcher.editext.removeTextChangedListener(modelTextWatcher.watcher)
                    }
                }
            }
        }
    }

    private fun RadioGroup.setOnCheckedListener(radioButton: RadioButton, view: View) {
        setOnCheckedChangeListener { _, id ->
            hideKeyboard()
            when (id) {
                bindingmod3.rbtSecc034Si.id -> {
                    bindingmod3.layoutSecc341.isVisible = true
                    bindingmod3.layoutSecc35.isVisible = true
                }
                bindingmod3.rbtSecc034No.id -> {
                    bindingmod3.layoutSecc341.isVisible = false
                    bindingmod3.layoutSecc35.isVisible = false
                }
                else -> view.visibility = if (id == radioButton.id) View.VISIBLE else View.GONE
            }
        }
    }

    private fun fillOut() {
        val mod3 = Mob.formComp?.capMod
        val blank = "".toEditable()
        with(bindingmod3) {

            val radioButtonsMap = mapOf(
                rgroupSecc034 to mod3?.v4check,
                rgroupSecc034A to mod3?.v4check1a,
                rgroupSecc034B to mod3?.v4check1b,
                rgroupSecc034C to mod3?.v4check1c,
                rgroupSecc034D to mod3?.v4check1d,
                rgroupSecc034E to mod3?.v4check1e
            )
            for ((radioGroup, isChecked) in radioButtonsMap) {
                when (isChecked) {
                    true -> radioGroup.check(radioGroup.getChildAt(0).id)
                    false -> radioGroup.check(radioGroup.getChildAt(1).id)
                    null -> radioGroup.clearCheck()
                }
            }
            txtSecc034A.text = mod3?.v4check1aPorcent?.toEditable() ?: blank
            txtSecc034B.text = mod3?.v4check1bPorcent?.toEditable() ?: blank
            txtSecc034C.text = mod3?.v4check1cPorcent?.toEditable() ?: blank
            txtSecc034D.text = mod3?.v4check1dPorcent?.toEditable() ?: blank
            txtSecc034E.text = mod3?.v4check1ePorcent?.toEditable() ?: blank
            txtSecc034EOtro.text = mod3?.v4check1eOther?.toEditable() ?: blank

            txtSecc035.text = mod3?.v5txt?.toDouble()?.toInt()?.takeIf { it > 0 }
                ?.run { decimalFormat.format(this).toEditable() } ?: blank
        }
        Mob.seesecc3 = false
        onAction()
    }

    fun saveCap(): List<String> {
        with (bindingmod3) {
            val p4check = (Mob.capMod?.v1check != false && rbtSecc034Si.isChecked)

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
                Mob.capMod?.v3check4,//---------
                if (p4check) true else if (Mob.capMod?.v1check != false && rbtSecc034No.isChecked)
                    false else null,
                if (p4check && rbtSecc034ASi.isChecked) true else
                    if (p4check && rbtSecc034ANo.isChecked) false else null,
                if (p4check && rbtSecc034ASi.isChecked) txtSecc034A.text.toString()
                    .ifEmpty { null } else null,
                if (p4check && rbtSecc034BSi.isChecked) true else
                    if (p4check && rbtSecc034BNo.isChecked) false else null,
                if (p4check && rbtSecc034BSi.isChecked) txtSecc034B.text.toString()
                    .ifEmpty { null } else null,
                if (p4check && rbtSecc034CSi.isChecked) true else
                    if (p4check && rbtSecc034CNo.isChecked) false else null,
                if (p4check && rbtSecc034CSi.isChecked) txtSecc034C.text.toString()
                    .ifEmpty { null } else null,
                if (p4check && rbtSecc034DSi.isChecked) true else
                    if (p4check && rbtSecc034DNo.isChecked) false else null,
                if (p4check && rbtSecc034DSi.isChecked) txtSecc034D.text.toString()
                    .ifEmpty { null } else null,
                if (p4check && rbtSecc034ESi.isChecked) true else
                    if (p4check && rbtSecc034ENo.isChecked) false else null,
                if (p4check && rbtSecc034ESi.isChecked) txtSecc034E.text.toString()
                    .ifEmpty { null } else null,
                if (p4check && rbtSecc034ESi.isChecked) txtSecc034EOtro.text.toString()
                    .ifEmpty { null } else null,
                if (p4check) txtSecc035.text.toString().replace(",", "")
                    .ifEmpty { null } else null,
                Mob.capMod?.v6porcent1,//-----------
                Mob.capMod?.v6porcent2,
                Mob.capMod?.v6porcent3,
                Mob.capMod?.v6porcent4,
                Mob.capMod?.v7check,
                Mob.capMod?.v8txt,
                Mob.capMod?.v9check,
                Mob.capMod?.v9txt,
                Mob.capMod?.v10porcent1,
                Mob.capMod?.v10porcent2,
                Mob.capMod?.v10porcent3,
                Mob.capMod?.v10porcent4,
                Mob.obsModulo,
                Mob.capMod?.numControl
            )
        }
        return viewCap()
    }

    private fun viewCap(): List<String> {
        with(bindingmod3) {
            val returnList: ArrayList<String> = ArrayList()
            if (Mob.capMod?.v1check == true) {
                if (rgroupSecc034.checkedRadioButtonId == -1)
                    returnList.add(CreateIncon.inconsistencia(ctx, "294") ?: "")
                if (rbtSecc034Si.isChecked) {
                    if (rgroupSecc034A.checkedRadioButtonId == -1)
                        returnList.add(CreateIncon.inconsistencia(ctx, "295") ?: "")
                    else if (rbtSecc034ASi.isChecked && txtSecc034A.text.isNullOrEmpty())
                        returnList.add(CreateIncon.inconsistencia(ctx, "300") ?: "")

                    if (rgroupSecc034B.checkedRadioButtonId == -1)
                        returnList.add(CreateIncon.inconsistencia(ctx, "296") ?: "")
                    else if (rbtSecc034BSi.isChecked && txtSecc034B.text.isNullOrEmpty())
                        returnList.add(CreateIncon.inconsistencia(ctx, "301") ?: "")

                    if (rgroupSecc034C.checkedRadioButtonId == -1)
                        returnList.add(CreateIncon.inconsistencia(ctx, "297") ?: "")
                    else if (rbtSecc034CSi.isChecked && txtSecc034C.text.isNullOrEmpty())
                        returnList.add(CreateIncon.inconsistencia(ctx, "302") ?: "")

                    if (rgroupSecc034D.checkedRadioButtonId == -1)
                        returnList.add(CreateIncon.inconsistencia(ctx, "298") ?: "")
                    else if (rbtSecc034DSi.isChecked && txtSecc034D.text.isNullOrEmpty())
                        returnList.add(CreateIncon.inconsistencia(ctx, "303") ?: "")

                    if (rgroupSecc034E.checkedRadioButtonId == -1)
                        returnList.add(CreateIncon.inconsistencia(ctx, "299") ?: "")
                    else if (rbtSecc034ESi.isChecked && txtSecc034E.text.isNullOrEmpty())
                        returnList.add(CreateIncon.inconsistencia(ctx, "304") ?: "")

                    if (txtSecc035.text.isNullOrEmpty() || txtSecc035.text.toString() == "0")
                        returnList.add(CreateIncon.inconsistencia(ctx, "305") ?: "")
                }
            } else if (Mob.capMod?.v1check == null)
                returnList.add(CreateIncon.inconsistencia(ctx, "289") ?: "")

            Mob.isecc3 = returnList.isNotEmpty()
            println("Secc3: ${Mob.isecc3}--${Mob.capMod}")
            return returnList
        }
    }
}