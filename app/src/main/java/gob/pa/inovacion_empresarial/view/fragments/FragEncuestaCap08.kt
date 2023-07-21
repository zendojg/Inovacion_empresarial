package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo08Binding
import gob.pa.inovacion_empresarial.function.Functions.hideKeyboard
import gob.pa.inovacion_empresarial.function.Functions.toEditable
import gob.pa.inovacion_empresarial.model.Mob

class FragEncuestaCap08 : Fragment() {

    private lateinit var bindingcap8o1: EncuestaCapitulo08Binding
    private lateinit var ctx: Context
    private var seecap = true

    private var indice1a = 0
    private var indice1b = 0
    private var indice1c = 0
    private var indice2a = 0
    private var indice2b = 0
    private var indice2c = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingcap8o1 = EncuestaCapitulo08Binding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingcap8o1.root
    }

    override fun onResume() {
        super.onResume()

        Mob.indiceEnc = Mob.CAP8P15
        if (seecap) fillOut()
        else onAction()
    }

    private fun onAction() {
        with(bindingcap8o1) {
            lowCap8o1.setOnClickListener { saveCap() }

            if (rbtCap856Si.isChecked) layoutCap857.isVisible = rbtCap856Si.isChecked
            else if (rbtCap856No.isChecked) layoutCap857.isVisible = false

            rgroupCap856.setOnCheckedChangeListener { _, id ->
                hideKeyboard()
                when (id) {
                    rbtCap856Si.id -> {
                        Mob.p56stat = true
                        layoutCap857.visibility = View.VISIBLE
                    }
                    rbtCap856No.id -> {
                        Mob.p56stat = false
                        layoutCap857.visibility = View.GONE
                    }
                }
            }


            val solic57 = ArrayAdapter(ctx, R.layout.style_box, Mob.arrOBT)
            solic57.setDropDownViewResource(R.layout.style_list)

            spinCap8571A.adapter = solic57
            spinCap8571B.adapter = solic57
            spinCap8571C.adapter = solic57
            spinCap8572A.adapter = solic57
            spinCap8572B.adapter = solic57
            spinCap8572C.adapter = solic57

            spinCap8571A.setSelection(indice1a)
            spinCap8571B.setSelection(indice1b)
            spinCap8571C.setSelection(indice1c)
            spinCap8572A.setSelection(indice2a)
            spinCap8572B.setSelection(indice2b)
            spinCap8572C.setSelection(indice2c)

            //txtCap8571AMontoly.isVisible = spinCap8571A.selectedItem.toString() == Mob.arrOBT[Mob.SOLICSI2]
            spinsAction()
        }
    }

    private fun spinsAction() {
        with(bindingcap8o1) {

            spinCap8571A.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice1a = pos
                    txtCap8571AMontoly.isVisible = Mob.arrOBT[pos] == Mob.arrOBT[Mob.SOLICSI2]
                    txtCap8571AMonto.requestFocus()
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap8571B.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice1b = pos
                    txtCap8571BMontoly.isVisible = Mob.arrOBT[pos] == Mob.arrOBT[Mob.SOLICSI2]
                    txtCap8571BMonto.requestFocus()
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap8571C.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice1c = pos
                    txtCap8571CMontoly.isVisible = Mob.arrOBT[pos] == Mob.arrOBT[Mob.SOLICSI2]
                    txtCap8571CMonto.requestFocus()
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap8572A.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice2a = pos
                    txtCap8572AMontoly.isVisible = Mob.arrOBT[pos] == Mob.arrOBT[Mob.SOLICSI2]
                    txtCap8572AMonto.requestFocus()
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap8572B.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice2b = pos
                    txtCap8572BMontoly.isVisible = Mob.arrOBT[pos] == Mob.arrOBT[Mob.SOLICSI2]
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap8572C.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice2c = pos
                    txtCap8572CMontoly.isVisible = Mob.arrOBT[pos] == Mob.arrOBT[Mob.SOLICSI2]
                    txtCap8572CMonto.requestFocus()
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
        }
    }


    private fun fillOut() {
        val cap8 = Mob.formComp?.cap8

        val blank = "".toEditable()
        when (cap8?.v56check) {
            true -> {
                Mob.p56stat = true
                bindingcap8o1.rbtCap856Si.isChecked = true
            }
            false -> {
                Mob.p56stat = false
                bindingcap8o1.rbtCap856No.isChecked = true
            }
            else -> {
                Mob.p56stat = null
                bindingcap8o1.rgroupCap856.clearCheck()
            }
        }
        indice1a = try { cap8?.v57num1a ?: 0 } catch (e: java.lang.NumberFormatException) { 0 }
        indice1b = try { cap8?.v57num1b ?: 0 } catch (e: java.lang.NumberFormatException) { 0 }
        indice1c = try { cap8?.v57num1c ?: 0 } catch (e: java.lang.NumberFormatException) { 0 }
        indice2a = try { cap8?.v57num2a ?: 0 } catch (e: java.lang.NumberFormatException) { 0 }
        indice2b = try { cap8?.v57num2b ?: 0 } catch (e: java.lang.NumberFormatException) { 0 }
        indice2c = try { cap8?.v57num2c ?: 0 } catch (e: java.lang.NumberFormatException) { 0 }

        with(bindingcap8o1) {
            txtCap8571COtra.text = cap8?.v57desc1c?.toEditable() ?: blank
            txtCap8572COtra.text = cap8?.v57desc2c?.toEditable() ?: blank

            txtCap8571AMonto.text = cap8?.v57monto1a?.toEditable() ?: blank
            txtCap8571BMonto.text = cap8?.v57monto1b?.toEditable() ?: blank
            txtCap8571CMonto.text = cap8?.v57monto1c?.toEditable() ?: blank
            txtCap8572AMonto.text = cap8?.v57monto2a?.toEditable() ?: blank
            txtCap8572BMonto.text = cap8?.v57monto2b?.toEditable() ?: blank
            txtCap8572CMonto.text = cap8?.v57monto2c?.toEditable() ?: blank
        }
        seecap = false
        onAction()
    }


    private fun saveCap() {
        TODO("Not yet implemented")
    }

}