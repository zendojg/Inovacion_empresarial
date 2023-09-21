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
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo08Binding
import gob.pa.inovacion_empresarial.function.EdittextFormat
import gob.pa.inovacion_empresarial.function.CreateIncon
import gob.pa.inovacion_empresarial.function.Functions.hideKeyboard
import gob.pa.inovacion_empresarial.function.Functions.toEditable
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelCap8
import gob.pa.inovacion_empresarial.model.ModelSpinLister
import gob.pa.inovacion_empresarial.model.ModelTexWatchers
import java.text.DecimalFormat

class FragEncuestaCap08 : Fragment() {

    private lateinit var bindingcap8o1: EncuestaCapitulo08Binding
    private lateinit var ctx: Context
    private val spinList = mutableListOf<ModelSpinLister>()
    private val textWatcherList = mutableListOf<ModelTexWatchers>()
    private var rowEditTexts: List<EditText> = emptyList()
    private val decimalFormat = DecimalFormat("#,###")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingcap8o1 = EncuestaCapitulo08Binding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingcap8o1.root
    }

    override fun onResume() {
        super.onResume()

        Mob.indiceFormulario = Mob.CAP8P15
        if (Mob.seecap08o1) fillOut()
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
        with(bindingcap8o1) {
            lowCap8o1.setOnClickListener { saveCap() }
            spinList.clear()
            spinList.add(ModelSpinLister(spinCap8571A, Mob.cap8?.v57num1a?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap8571B, Mob.cap8?.v57num1b?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap8571C, Mob.cap8?.v57num1c?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap8572A, Mob.cap8?.v57num2a?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap8572B, Mob.cap8?.v57num2b?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap8572C, Mob.cap8?.v57num2c?.toIntOrNull() ?: 0))

            if (rbtCap856Si.isChecked) { layoutCap857.isVisible = true }
            else if (rbtCap856No.isChecked) { layoutCap857.isVisible = false }

            rgroupCap856.setOnCheckedChangeListener { _, id ->
                hideKeyboard()
                when (id) {
                    rbtCap856Si.id -> { layoutCap857.visibility = View.VISIBLE }
                    rbtCap856No.id -> { layoutCap857.visibility = View.GONE }
                }
            }

            val solic57 = ArrayAdapter(ctx, R.layout.style_box, Mob.arrOBT)
            solic57.setDropDownViewResource(R.layout.style_list)

            for (index in 0 until tb57.childCount) {
                val view = tb57.getChildAt(index)
                if (view is Spinner) { view.adapter = solic57 }
                else if (view is TextInputLayout) {
                    val editText = view.editText
                    editText?.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                        if (hasFocus) {
                            if (editText != null)
                                textWatcherList.add(EdittextFormat.edittextMiles(editText))
                        }
                        else {
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
            for (modelSpinLister in spinList) {
                val spinner = modelSpinLister.spinner
                val index = modelSpinLister.indice
                if (index >= 0 && index < spinner.adapter.count) {
                    spinner.setSelection(index)
                    if (index == 2) montoVisibility(spinner, true)
                    else montoVisibility(spinner, false)
                }
            }
            for (modelSpinLister in spinList) {
                val spinner = modelSpinLister.spinner
                spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        hideKeyboard()
                        modelSpinLister.indice = position
                        if (position == 2) montoVisibility(spinner, true)
                        else montoVisibility(spinner, false)
                    }
                    override fun onNothingSelected(parent: AdapterView<*>?) { /* sin selección */ }
                }
            }
        }
    }

    private fun montoVisibility(spinner: Spinner?, visibility: Boolean) {
        with (bindingcap8o1) {
            when (spinner) {
                spinCap8571A -> txtCap8571AMontoly.isVisible = visibility
                spinCap8571B -> txtCap8571BMontoly.isVisible = visibility
                spinCap8571C -> txtCap8571CMontoly.isVisible = visibility
                spinCap8572A -> txtCap8572AMontoly.isVisible = visibility
                spinCap8572B -> txtCap8572BMontoly.isVisible = visibility
                spinCap8572C -> txtCap8572CMontoly.isVisible = visibility
            }
        }
    }


    private fun fillOut() {
        val cap8 = Mob.formComp?.cap8
        when (cap8?.v56check) {
            true -> { bindingcap8o1.rbtCap856Si.isChecked = true }
            false -> { bindingcap8o1.rbtCap856No.isChecked = true }
            else -> { bindingcap8o1.rgroupCap856.clearCheck() }
        }
        with(bindingcap8o1) {
            txtCap8571COtra.text = cap8?.v57desc1c?.toEditable() ?: "".toEditable()
            txtCap8572COtra.text = cap8?.v57desc2c?.toEditable() ?: "".toEditable()

            val txtCapsMap = mapOf(
                txtCap8571AMonto to cap8?.v57monto1a,
                txtCap8571BMonto to cap8?.v57monto1b,
                txtCap8571CMonto to cap8?.v57monto1c,
                txtCap8572AMonto to cap8?.v57monto2a,
                txtCap8572BMonto to cap8?.v57monto2b,
                txtCap8572CMonto to cap8?.v57monto2c,
            )
            for ((txt, vCap) in txtCapsMap) {
                txt.text = vCap?.toDouble()?.toInt()?.takeIf { it > 0 }
                    ?.run { decimalFormat.format(this).toEditable() } ?: "0".toEditable()
            }
        }
        Mob.seecap08o1 = false
        onAction()
    }

    fun saveCap(): List<String> {
        with(bindingcap8o1) {
            Mob.cap8 = ModelCap8(
                Mob.cap8?.id,
                Mob.cap8?.ncontrol,
                if (rbtCap856Si.isChecked) true else if (rbtCap856No.isChecked) false else null,

                if (rbtCap856Si.isChecked && spinCap8571A.selectedItemPosition != 0)
                    spinCap8571A.selectedItemPosition.toString() else null,
                if (rbtCap856Si.isChecked && spinCap8571A.selectedItemPosition == 2)
                    txtCap8571AMonto.text.toString().
                    replace(",", "").ifEmpty { null } else null,

                if (rbtCap856Si.isChecked && spinCap8571B.selectedItemPosition != 0)
                    spinCap8571B.selectedItemPosition.toString() else null,
                if (rbtCap856Si.isChecked && spinCap8571B.selectedItemPosition == 2)
                    txtCap8571BMonto.text.toString().
                    replace(",", "").ifEmpty { null } else null,

                if (rbtCap856Si.isChecked && txtCap8571COtra.text.toString().isNotEmpty())
                    txtCap8571COtra.text.toString() else null,
                if (rbtCap856Si.isChecked && spinCap8571C.selectedItemPosition != 0)
                    spinCap8571C.selectedItemPosition.toString() else null,
                if (rbtCap856Si.isChecked && spinCap8571C.selectedItemPosition == 2)
                    txtCap8571CMonto.text.toString().
                    replace(",", "").ifEmpty { null } else null,

                if (rbtCap856Si.isChecked && spinCap8572A.selectedItemPosition != 0)
                    spinCap8572A.selectedItemPosition.toString() else null,
                if (rbtCap856Si.isChecked && spinCap8572A.selectedItemPosition == 2)
                    txtCap8572AMonto.text.toString().
                    replace(",", "").ifEmpty { null } else null,

                if (rbtCap856Si.isChecked && spinCap8572B.selectedItemPosition != 0)
                    spinCap8572B.selectedItemPosition.toString() else null,
                if (rbtCap856Si.isChecked && spinCap8572B.selectedItemPosition == 2)
                    txtCap8572BMonto.text.toString().
                    replace(",", "").ifEmpty { null } else null,

                if (rbtCap856Si.isChecked && txtCap8572COtra.text.toString().isNotEmpty())
                    txtCap8572COtra.text.toString() else null,
                if (rbtCap856Si.isChecked && spinCap8572C.selectedItemPosition != 0)
                    spinCap8572C.selectedItemPosition.toString() else null,
                if (rbtCap856Si.isChecked && spinCap8572C.selectedItemPosition == 2)
                    txtCap8572CMonto.text.toString().
                    replace(",", "").ifEmpty { null } else null,

                Mob.cap8?.v58num1a,//
                Mob.cap8?.v58num1b,
                Mob.cap8?.v58num1c,
                Mob.cap8?.v58num2a,
                Mob.cap8?.v58num2b,
                Mob.cap8?.v58num2c,
                Mob.cap8?.v58num2d,
                Mob.cap8?.v58num3a,
                Mob.cap8?.v58num3b,
                Mob.cap8?.v58num4a,
                Mob.cap8?.v58num4b,
                Mob.cap8?.v58num4c,
                Mob.cap8?.v58desc4d,
                Mob.cap8?.v58num4d
            )
        }
        return viewCap()
    }

    private fun viewCap(): List<String> {
        with(Mob) {
            val returnList: ArrayList<String> = ArrayList()
            if (cap8?.v56check == null)
                returnList.add(CreateIncon.inconsistencia(ctx, "143") ?: "")
            else if (cap8?.v56check == true) {
                if (cap8?.v57num1a.isNullOrEmpty() || cap8?.v57num1a == "0")
                    returnList.add(CreateIncon.inconsistencia(ctx, "144") ?: "")
                if (cap8?.v57num1b.isNullOrEmpty() || cap8?.v57num1b == "0")
                    returnList.add(CreateIncon.inconsistencia(ctx, "145") ?: "")


                if (!cap8?.v57desc1c.isNullOrEmpty()) {
                    if (cap8?.v57num1c.isNullOrEmpty() || cap8?.v57num1c == "0")
                        returnList.add(CreateIncon.inconsistencia(ctx, "146") ?: "")
                } else if (!cap8?.v57num2c.isNullOrEmpty() || cap8?.v57num2c != "0")
                    if (!cap8?.v57desc2c.isNullOrEmpty())
                        returnList.add(CreateIncon.inconsistencia(ctx, "146") ?: "")


                if (cap8?.v57num2a.isNullOrEmpty() || cap8?.v57num2a == "0")
                    returnList.add(CreateIncon.inconsistencia(ctx, "147") ?: "")
                if (cap8?.v57num2b.isNullOrEmpty() || cap8?.v57num2b == "0")
                    returnList.add(CreateIncon.inconsistencia(ctx, "148") ?: "")


                if (!cap8?.v57desc2c.isNullOrEmpty()) {
                    if (cap8?.v57num2c.isNullOrEmpty() || cap8?.v57num2c == "0")
                        returnList.add(CreateIncon.inconsistencia(ctx, "149") ?: "")
                } else if (!cap8?.v57num2c.isNullOrEmpty() || cap8?.v57num2c != "0")
                    if (!cap8?.v57desc2c.isNullOrEmpty())
                        returnList.add(CreateIncon.inconsistencia(ctx, "149") ?: "")


                if (cap8?.v57num1a == "2" && cap8?.v57monto1a.isNullOrEmpty())
                    returnList.add(CreateIncon.inconsistencia(ctx, "150") ?: "")
                if (cap8?.v57num1b == "2" && cap8?.v57monto1b.isNullOrEmpty())
                    returnList.add(CreateIncon.inconsistencia(ctx, "151") ?: "")
                if (cap8?.v57num1c == "2" && cap8?.v57monto1c.isNullOrEmpty())
                    returnList.add(CreateIncon.inconsistencia(ctx, "152") ?: "")

                if (cap8?.v57num2a == "2" && cap8?.v57monto2a.isNullOrEmpty())
                    returnList.add(CreateIncon.inconsistencia(ctx, "153") ?: "")
                if (cap8?.v57num2b == "2" && cap8?.v57monto2a.isNullOrEmpty())
                    returnList.add(CreateIncon.inconsistencia(ctx, "154") ?: "")
                if (cap8?.v57num2c == "2" && cap8?.v57monto2a.isNullOrEmpty())
                    returnList.add(CreateIncon.inconsistencia(ctx, "155") ?: "")
            }
            icap08o1 = returnList.isNotEmpty()
            println("---------Is not empty: $icap08o1--$cap8")
            return returnList
        }
    }

}