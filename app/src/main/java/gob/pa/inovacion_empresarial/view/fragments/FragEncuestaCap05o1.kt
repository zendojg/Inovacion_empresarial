package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo051VentasYExpoBinding
import gob.pa.inovacion_empresarial.function.Functions.toEditable
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelCap5

class FragEncuestaCap05o1 : Fragment() {

    private lateinit var bindingcap5o1: EncuestaCapitulo051VentasYExpoBinding
    private lateinit var ctx: Context
    private var seecap = true
    private var ventaA21 = 0
    private var ventaA22 = 0
    private var ventaB21 = 0
    private var ventaB22 = 0
    private var venta21 = 0
    private var venta22 = 0

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
        Mob.indiceEnc = Mob.CAP5P06
        if (seecap) fillOut()
        else onAction()
    }

    private fun onAction() {
        with(bindingcap5o1) {
            layoutCap5332021.isVisible = rbtCap532ASi.isChecked
            layoutCap5332022.isVisible = rbtCap532BSi.isChecked

            rgroupCap532a.setOnCheckedChangeListener { _, id ->
                when (id) {
                    rbtCap532ASi.id -> layoutCap5332021.isVisible = true
                    rbtCap532ANo.id -> layoutCap5332021.isVisible = false
                }
            }
            rgroupCap532b.setOnCheckedChangeListener { _, id ->
                when (id) {
                    rbtCap532BSi.id -> layoutCap5332022.isVisible = true
                    rbtCap532BNo.id -> layoutCap5332022.isVisible = false
                }
            }

            txtCap530A2021.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    if (txtCap530A2021.text.toString() == "0") txtCap530A2021.text?.clear()
                    actionTxtSum(txtCap530A2021)
                } else if (txtCap530A2021.text.isNullOrEmpty()) {
                    txtCap530A2021.text = "0".toEditable()
                }
            }
            txtCap530A2022.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    if (txtCap530A2022.text.toString() == "0") txtCap530A2022.text?.clear()
                    actionTxtSum(txtCap530A2022)
                } else if (txtCap530A2022.text.isNullOrEmpty()) {
                    txtCap530A2022.text = "0".toEditable()
                }
            }
            txtCap530B2021.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    if (txtCap530B2021.text.toString() == "0") txtCap530B2021.text?.clear()
                    actionTxtSum(txtCap530B2021)
                } else if (txtCap530B2021.text.isNullOrEmpty()) {
                    txtCap530B2021.text = "0".toEditable()
                }
            }
            txtCap530B2022.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    if (txtCap530B2022.text.toString() == "0") txtCap530B2022.text?.clear()
                    actionTxtSum(txtCap530B2022)
                } else if (txtCap530B2022.text.isNullOrEmpty()) {
                    txtCap530B2022.text = "0".toEditable()
                }
            }

            lowCap5o1.setOnClickListener { savedCap() }
        }
    }

    private fun actionTxtSum(txt: EditText) {
        txt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, st: Int, c: Int, a: Int) {}
            override fun onTextChanged(s: CharSequence, st: Int, b: Int, c: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (!s.isNullOrEmpty()) {
                    when (txt) {
                        bindingcap5o1.txtCap530A2021 -> {
                            ventaA21 = try { txt.text.toString().toInt() }
                            catch (e: java.lang.NumberFormatException) { 0 }}
                        bindingcap5o1.txtCap530A2022 -> {
                            ventaA22 = try { txt.text.toString().toInt() }
                            catch (e: java.lang.NumberFormatException) { 0 }}
                        bindingcap5o1.txtCap530B2021 -> {
                            ventaB21 = try { txt.text.toString().toInt() }
                            catch (e: java.lang.NumberFormatException) { 0 }}
                        bindingcap5o1.txtCap530B2022 -> {
                            ventaB22 = try { txt.text.toString().toInt() }
                            catch (e: java.lang.NumberFormatException) { 0 }}
                    }
                    venta21 = ventaA21 + ventaB21
                    venta22 = ventaA22 + ventaB22
                    bindingcap5o1.txtCap53012021.text = venta21.toString().toEditable()
                    bindingcap5o1.txtCap53012022.text = venta22.toString().toEditable()

                } else {
                    when (txt) {
                        bindingcap5o1.txtCap530A2021 -> ventaA21 = 0
                        bindingcap5o1.txtCap530A2022 -> ventaA22 = 0
                        bindingcap5o1.txtCap530B2021 -> ventaB21 = 0
                        bindingcap5o1.txtCap530B2022 -> ventaB22 = 0
                    }
                    venta21 = ventaA21 + ventaB21
                    venta22 = ventaA22 + ventaB22
                    bindingcap5o1.txtCap53012021.text = venta21.toString().toEditable()
                    bindingcap5o1.txtCap53012022.text = venta22.toString().toEditable()
                }
            }
        })
    }

    private fun fillOut() {
        val cap5 = Mob.formComp?.cap5
        val blank = "".toEditable()
        with(bindingcap5o1) {
            txtCap53012021.text = cap5?.v30txt21T?.toEditable() ?: blank
            txtCap53012022.text = cap5?.v30txt22T?.toEditable() ?: blank
            txtCap530A2021.text = cap5?.v30txt21a?.toEditable() ?: blank
            txtCap530A2022.text = cap5?.v30txt22a?.toEditable() ?: blank
            txtCap530B2021.text = cap5?.v30txt21b?.toEditable() ?: blank
            txtCap530B2022.text = cap5?.v30txt22b?.toEditable() ?: blank
            fillOut31(cap5)
            fillOut32(cap5)

            txtCap53312021.text = cap5?.v33txt1s21?.toEditable() ?: blank
            txtCap53322021.text = cap5?.v33txt2s21?.toEditable() ?: blank
            txtCap53332021.text = cap5?.v33txt3s21?.toEditable() ?: blank

            txtCap53312022.text = cap5?.v33txt1s22?.toEditable() ?: blank
            txtCap53322022.text = cap5?.v33txt2s22?.toEditable() ?: blank
            txtCap53332022.text = cap5?.v33txt3s22?.toEditable() ?: blank

            fillOut34(cap5)
            seecap = false
            onAction()
        }
    }

    private fun fillOut31(cap5: ModelCap5?) {
        with(bindingcap5o1) {
            when {
                cap5?.v31check21a == true -> rbtCap531A2021.isChecked = true
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
        }
    }
    private fun fillOut32(cap5: ModelCap5?) {
        with(bindingcap5o1) {
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
        }
    }
    private fun fillOut34(cap5: ModelCap5?) {
        with(bindingcap5o1) {
            when {
                cap5?.v34check1o21 == true -> rbtCap53412021.isChecked = true
                cap5?.v34check2o21 == true -> rbtCap53422021.isChecked = true
                cap5?.v34check3o21 == true -> rbtCap53432021.isChecked = true
                else -> { rgroupCap5342021.clearCheck() }
            }
            when {
                cap5?.v34check1o22 == true -> rbtCap53412022.isChecked = true
                cap5?.v34check2o22 == true -> rbtCap53422022.isChecked = true
                cap5?.v34check3o22 == true -> rbtCap53432022.isChecked = true
                else -> { rgroupCap5342022.clearCheck() }
            }
        }
    }

    private fun savedCap() { }
}