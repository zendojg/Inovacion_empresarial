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
    private lateinit var textWatcher: Array<TextWatcher>
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
        Mob.indiceFormulario = Mob.CAP5P06
        if (Mob.seecap05o1) fillOut()
        else onAction()
    }

    override fun onPause() {
        super.onPause()
        with (bindingcap5o1) {

            txtCap530A2021.onFocusChangeListener = null
            txtCap530A2022.onFocusChangeListener = null
            txtCap530B2021.onFocusChangeListener = null
            txtCap530B2022.onFocusChangeListener = null

        }

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

            lowCap5o1.setOnClickListener { saveCap() }
        }
    }

    private fun actionTxtSum(txt: EditText) {
        txt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, st: Int, c: Int, a: Int) {}
            override fun onTextChanged(s: CharSequence, st: Int, b: Int, c: Int) {
                if (s.isNotEmpty()) {
                    when (txt) {
                        bindingcap5o1.txtCap530A2021 -> {
                            ventaA21 = try { s.toString().toInt() }
                            catch (e: java.lang.NumberFormatException) { 0 }}
                        bindingcap5o1.txtCap530A2022 -> {
                            ventaA22 = try { s.toString().toInt() }
                            catch (e: java.lang.NumberFormatException) { 0 }}
                        bindingcap5o1.txtCap530B2021 -> {
                            ventaB21 = try { s.toString().toInt() }
                            catch (e: java.lang.NumberFormatException) { 0 }}
                        bindingcap5o1.txtCap530B2022 -> {
                            ventaB22 = try { s.toString().toInt() }
                            catch (e: java.lang.NumberFormatException) { 0 }}
                    }
                } else {
                    when (txt) {
                        bindingcap5o1.txtCap530A2021 -> ventaA21 = 0
                        bindingcap5o1.txtCap530A2022 -> ventaA22 = 0
                        bindingcap5o1.txtCap530B2021 -> ventaB21 = 0
                        bindingcap5o1.txtCap530B2022 -> ventaB22 = 0
                    }
                }
            }
            override fun afterTextChanged(s: Editable?) {
                venta21 = ventaA21 + ventaB21
                venta22 = ventaA22 + ventaB22
                bindingcap5o1.txtCap53012021.text = venta21.toString().toEditable()
                bindingcap5o1.txtCap53012022.text = venta22.toString().toEditable()
                textWatcher += this
            }
        })
    }

    private fun fillOut() {
        val cap5 = Mob.formComp?.cap5
        val blank = "".toEditable()
        with(bindingcap5o1) {
            val a21: Int? = try { cap5?.v30txt21a?.toDouble()?.toInt()
            } catch (e:java.lang.NumberFormatException) { 0 }
            val a22: Int? = try { cap5?.v30txt22a?.toDouble()?.toInt()
            } catch (e:java.lang.NumberFormatException) { 0 }

            val b21: Int? = try { cap5?.v30txt21b?.toDouble()?.toInt()
            } catch (e:java.lang.NumberFormatException) { 0 }
            val b22: Int? = try { cap5?.v30txt22b?.toDouble()?.toInt()
            } catch (e:java.lang.NumberFormatException) { 0 }

            val t21: Int? = try { cap5?.v30txt21T?.toDouble()?.toInt()
            } catch (e:java.lang.NumberFormatException) { 0 }
            val t22: Int? = try { cap5?.v30txt22T?.toDouble()?.toInt()
            } catch (e:java.lang.NumberFormatException) { 0 }

            txtCap53012021.text = t21?.toString()?.toEditable() ?: blank
            txtCap53012022.text = t22?.toString()?.toEditable() ?: blank
            txtCap530A2021.text = a21?.toString()?.toEditable() ?: blank
            txtCap530A2022.text = a22?.toString()?.toEditable() ?: blank
            txtCap530B2021.text = b21?.toString()?.toEditable() ?: blank
            txtCap530B2022.text = b22?.toString()?.toEditable() ?: blank
            fillOut31(cap5)
            fillOut32(cap5)

            txtCap53312021.text = cap5?.v33txt1s21?.toEditable() ?: blank
            txtCap53322021.text = cap5?.v33txt2s21?.toEditable() ?: blank
            txtCap53332021.text = cap5?.v33txt3s21?.toEditable() ?: blank

            txtCap53312022.text = cap5?.v33txt1s22?.toEditable() ?: blank
            txtCap53322022.text = cap5?.v33txt2s22?.toEditable() ?: blank
            txtCap53332022.text = cap5?.v33txt3s22?.toEditable() ?: blank

            fillOut34(cap5)
            Mob.seecap05o1 = false
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
                else ->  rgroupCap5342021.clearCheck()
            }
            when {
                cap5?.v34check1o22 == true -> rbtCap53412022.isChecked = true
                cap5?.v34check2o22 == true -> rbtCap53422022.isChecked = true
                cap5?.v34check3o22 == true -> rbtCap53432022.isChecked = true
                else ->  rgroupCap5342022.clearCheck()
            }
        }
    }

    fun saveCap() {
        with(bindingcap5o1) {
            Mob.cap5 = ModelCap5(
                Mob.cap5?.id,
                Mob.cap5?.ncontrol,
                txtCap530A2021.text.toString().ifEmpty { null },
                txtCap530B2021.text.toString().ifEmpty { null },
                txtCap53012021.text.toString().ifEmpty { null },
                txtCap530A2022.text.toString().ifEmpty { null },
                txtCap530B2022.text.toString().ifEmpty { null },
                txtCap53012022.text.toString().ifEmpty { null },
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
        println("----------${Mob.cap5}")
    }
}