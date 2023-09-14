package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import gob.pa.inovacion_empresarial.databinding.ModuloSeccion04Binding
import gob.pa.inovacion_empresarial.function.ClassFunctions
import gob.pa.inovacion_empresarial.function.CreateIncon
import gob.pa.inovacion_empresarial.function.Functions.hideKeyboard
import gob.pa.inovacion_empresarial.function.Functions.toEditable
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelMod
import gob.pa.inovacion_empresarial.model.ModelTexWatchers

class FragModuloSecc04: Fragment() {
    private lateinit var bindingmod4: ModuloSeccion04Binding
    private lateinit var ctx: Context
    private val textWatcherList = mutableListOf<ModelTexWatchers>()
    private var rowEditTexts: List<EditText> = emptyList()
    private var venta1 = 0
    private var venta2 = 0
    private var venta3 = 0
    private var venta4 = 0
    private var ventat = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingmod4 = ModuloSeccion04Binding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingmod4.root
    }

    override fun onResume() {
        super.onResume()
        Mob.indiceFormulario = Mob.SEC4P23
        if (Mob.seesecc4) fillOut()
        else onAction()
    }
    override fun onPause() {
        super.onPause()
        for (edittext in rowEditTexts)
            edittext.onFocusChangeListener = null

        for (modelTexWatcher in textWatcherList) {
            modelTexWatcher.editext.removeTextChangedListener(modelTexWatcher.watcher)
        }
        rowEditTexts = emptyList()
        textWatcherList.clear()
    }

    private fun onAction() {
        with(bindingmod4) {
            scrollForm.isVisible = Mob.capMod?.v1check != false
            rowEditTexts = listOf(txtSecc046p1, txtSecc046p2, txtSecc046p3, txtSecc046p4)

            venta1 = try { txtSecc046p1.text.toString().toInt() }
            catch (e: java.lang.NumberFormatException) { 0 }

            venta2 = try { txtSecc046p2.text.toString().toInt() }
            catch (e: java.lang.NumberFormatException) { 0 }

            venta3 = try { txtSecc046p3.text.toString().toInt() }
            catch (e: java.lang.NumberFormatException) { 0 }

            venta4 = try { txtSecc046p4.text.toString().toInt() }
            catch (e: java.lang.NumberFormatException) { 0 }

            ventat = venta1 + venta2 + venta3 + venta4
            lb6nm100.text = ventat.toString().toEditable()

            if (ventat == Mob.PORCENT100) lb6nm100.setTextColor(Color.GREEN)
            else lb6nm100.setTextColor(Color.RED)

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
            actionPorcent()
        }
    }

    private fun actionPorcent() {
        fun bodyTxt(editText: TextInputEditText, hasFocus: Boolean) {
            if (hasFocus) {
                if (editText.text.toString() == "0") editText.text?.clear()
                actionTxtPorcent(editText)
            } else if (editText.text.isNullOrEmpty()) {
                editText.text = "0".toEditable()
                if (textWatcherList.size > Mob.MAXTEXWATCHER4ROWS) {
                    for (modelTexWatcher in textWatcherList) {
                        modelTexWatcher.editext.removeTextChangedListener(modelTexWatcher.watcher)
                    }
                }
            }
        }

        with(bindingmod4) {
            txtSecc046p1.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                bodyTxt(txtSecc046p1, hasFocus)
            }
            txtSecc046p2.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                bodyTxt(txtSecc046p2, hasFocus)
            }
            txtSecc046p3.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                bodyTxt(txtSecc046p3, hasFocus)
            }
            txtSecc046p4.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                bodyTxt(txtSecc046p4, hasFocus)
            }
        }


    }

    private fun actionTxtPorcent(txt: TextInputEditText) {
        txt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, st: Int, c: Int, a: Int) {/* Nada */}
            override fun onTextChanged(s: CharSequence, st: Int, b: Int, c: Int) {/* Nada */}
            override fun afterTextChanged(s: Editable?) {
                if (!s.isNullOrEmpty()) {
                    when (txt) {
                        bindingmod4.txtSecc046p1 -> { venta1 = try { txt.text.toString().toInt() }
                            catch (e: java.lang.NumberFormatException) { 0 }}
                        bindingmod4.txtSecc046p2 -> { venta2 = try { txt.text.toString().toInt() }
                            catch (e: java.lang.NumberFormatException) { 0 }}
                        bindingmod4.txtSecc046p3 -> { venta3 = try { txt.text.toString().toInt() }
                            catch (e: java.lang.NumberFormatException) { 0 }}
                        bindingmod4.txtSecc046p4 -> { venta4 = try { txt.text.toString().toInt() }
                            catch (e: java.lang.NumberFormatException) { 0 }}
                    }
                    ventat = venta1 + venta2 + venta3 + venta4
                    bindingmod4.lb6nm100.text = ventat.toString().toEditable()
                    if (ventat == Mob.PORCENT100) bindingmod4.lb6nm100.setTextColor(Color.GREEN)
                    else bindingmod4.lb6nm100.setTextColor(Color.RED)
                } else {
                    when (txt) {
                        bindingmod4.txtSecc046p1 -> venta1 = 0
                        bindingmod4.txtSecc046p2 -> venta2 = 0
                        bindingmod4.txtSecc046p3 -> venta3 = 0
                        bindingmod4.txtSecc046p4 -> venta4 = 0
                    }
                    ventat = venta1 + venta2 + venta3 + venta4
                    bindingmod4.lb6nm100.text = ventat.toString().toEditable()
                    if (ventat == Mob.PORCENT100) bindingmod4.lb6nm100.setTextColor(Color.GREEN)
                    else bindingmod4.lb6nm100.setTextColor(Color.RED)
                }
                val watchers = ModelTexWatchers(txt, this)
                textWatcherList.add(watchers)
            }
        })
    }

    private fun fillOut() {
        val mod4 = Mob.formComp?.capMod
        val blank = "".toEditable()
        with(bindingmod4) {

            txtSecc046p1.text = mod4?.v6porcent1?.toEditable() ?: blank
            txtSecc046p2.text = mod4?.v6porcent2?.toEditable() ?: blank
            txtSecc046p3.text = mod4?.v6porcent3?.toEditable() ?: blank
            txtSecc046p4.text = mod4?.v6porcent4?.toEditable() ?: blank

            when (mod4?.v7check) {
                true -> rbtSecc047Si.isChecked = true
                false -> rbtSecc047No.isChecked = true
                else -> rgroupSecc047.clearCheck()
            }

            txtSecc048.text = mod4?.v8txt?.toEditable() ?: blank

            when (mod4?.v9check) {
                true -> rbtSecc049Si.isChecked = true
                false -> rbtSecc049No.isChecked = true
                else -> rgroupSecc047.clearCheck()
            }

            txtSecc049.text = mod4?.v9txt?.toEditable() ?: blank

            txtSecc04101.text = mod4?.v10porcent1?.toEditable() ?: blank
            txtSecc04102.text = mod4?.v10porcent2?.toEditable() ?: blank
            txtSecc04103.text = mod4?.v10porcent3?.toEditable() ?: blank
            txtSecc04104.text = mod4?.v10porcent4?.toEditable() ?: blank

        }
        Mob.seesecc4 = false
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
                    txtSecc046p1.text.toString().ifEmpty { "0" } else null,
                if (Mob.capMod?.v1check != false)
                    txtSecc046p2.text.toString().ifEmpty { "0" } else null,
                if (Mob.capMod?.v1check != false)
                    txtSecc046p3.text.toString().ifEmpty { "0" } else null,
                if (Mob.capMod?.v1check != false)
                    txtSecc046p4.text.toString().ifEmpty { "0" } else null,
                if (Mob.capMod?.v1check != false && rbtSecc047Si.isChecked) true else
                    if (Mob.capMod?.v1check != false && rbtSecc047No.isChecked) false else null,
                if (Mob.capMod?.v1check != false && rbtSecc047Si.isChecked)
                    txtSecc048.text.toString().ifEmpty { null } else null,
                if (Mob.capMod?.v1check != false && rbtSecc049Si.isChecked) true else
                    if (Mob.capMod?.v1check != false && rbtSecc049No.isChecked) false else null,
                if (Mob.capMod?.v1check != false && rbtSecc049Si.isChecked)
                    txtSecc049.text.toString().ifEmpty { null } else null,
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
        with(Mob) {
            val returnList: ArrayList<String> = ArrayList()
            if (capMod?.v6porcent1.isNullOrEmpty() && capMod?.v6porcent2.isNullOrEmpty())
                if (capMod?.v6porcent3.isNullOrEmpty() && capMod?.v6porcent4.isNullOrEmpty())
                    returnList.add(CreateIncon.inconsistencia(ctx, "306") ?: "")
            if (ventat < PORCENT100 || ventat > PORCENT100)
                returnList.add(CreateIncon.inconsistencia(ctx, "307") ?: "")

            isecc4 = returnList.isNotEmpty()
            println("---------Is not empty: $isecc4--$capMod")
            return returnList
        }
    }
}