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
import gob.pa.inovacion_empresarial.function.CreateIncon
import gob.pa.inovacion_empresarial.function.Functions.hideKeyboard
import gob.pa.inovacion_empresarial.function.Functions.toEditable
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelCap8

class FragEncuestaCap08 : Fragment() {

    private lateinit var bindingcap8o1: EncuestaCapitulo08Binding
    private lateinit var ctx: Context
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

        Mob.indiceFormulario = Mob.CAP8P15
        if (Mob.seecap08o1) fillOut()
        else onAction()
    }

    private fun onAction() {
        with(bindingcap8o1) {
            lowCap8o1.setOnClickListener { saveCap() }

            if (rbtCap856Si.isChecked) {
                Mob.p56stat = true
                layoutCap857.isVisible = true
            }
            else if (rbtCap856No.isChecked) {
                Mob.p56stat = false
                layoutCap857.isVisible = false
            }

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

            spinsAction()
        }
    }

    private fun spinsAction() {
        with(bindingcap8o1) {

            spinCap8571A.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice1a = pos
                    txtCap8571AMontoly.isVisible = Mob.arrOBT[pos] == Mob.arrOBT[Mob.SOLICITUDYES2]
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
            spinCap8571B.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice1b = pos
                    txtCap8571BMontoly.isVisible = Mob.arrOBT[pos] == Mob.arrOBT[Mob.SOLICITUDYES2]
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
            spinCap8571C.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice1c = pos
                    txtCap8571CMontoly.isVisible = Mob.arrOBT[pos] == Mob.arrOBT[Mob.SOLICITUDYES2]
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
            spinCap8572A.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice2a = pos
                    txtCap8572AMontoly.isVisible = Mob.arrOBT[pos] == Mob.arrOBT[Mob.SOLICITUDYES2]
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
            spinCap8572B.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice2b = pos
                    txtCap8572BMontoly.isVisible = Mob.arrOBT[pos] == Mob.arrOBT[Mob.SOLICITUDYES2]
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
            spinCap8572C.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice2c = pos
                    txtCap8572CMontoly.isVisible = Mob.arrOBT[pos] == Mob.arrOBT[Mob.SOLICITUDYES2]
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
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
        indice1a = try { cap8?.v57num1a?.toInt() ?: 0 } catch (e: NumberFormatException) { 0 }
        indice1b = try { cap8?.v57num1b?.toInt() ?: 0 } catch (e: NumberFormatException) { 0 }
        indice1c = try { cap8?.v57num1c?.toInt() ?: 0 } catch (e: NumberFormatException) { 0 }
        indice2a = try { cap8?.v57num2a?.toInt() ?: 0 } catch (e: NumberFormatException) { 0 }
        indice2b = try { cap8?.v57num2b?.toInt() ?: 0 } catch (e: NumberFormatException) { 0 }
        indice2c = try { cap8?.v57num2c?.toInt() ?: 0 } catch (e: NumberFormatException) { 0 }

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
                    txtCap8571AMonto.text.toString().ifEmpty { null } else null,

                if (rbtCap856Si.isChecked && spinCap8571B.selectedItemPosition != 0)
                    spinCap8571B.selectedItemPosition.toString() else null,
                if (rbtCap856Si.isChecked && spinCap8571B.selectedItemPosition == 2)
                    txtCap8571BMonto.text.toString().ifEmpty { null } else null,

                if (rbtCap856Si.isChecked && txtCap8571COtra.text.toString().isNotEmpty())
                    txtCap8571COtra.text.toString() else null,
                if (rbtCap856Si.isChecked && spinCap8571C.selectedItemPosition != 0)
                    spinCap8571C.selectedItemPosition.toString() else null,
                if (rbtCap856Si.isChecked && spinCap8571C.selectedItemPosition == 2)
                    txtCap8571CMonto.text.toString().ifEmpty { null } else null,

                if (rbtCap856Si.isChecked && spinCap8572A.selectedItemPosition != 0)
                    spinCap8572A.selectedItemPosition.toString() else null,
                if (rbtCap856Si.isChecked && spinCap8572A.selectedItemPosition == 2)
                    txtCap8572AMonto.text.toString().ifEmpty { null } else null,

                if (rbtCap856Si.isChecked && spinCap8572B.selectedItemPosition != 0)
                    spinCap8572B.selectedItemPosition.toString() else null,
                if (rbtCap856Si.isChecked && spinCap8572B.selectedItemPosition == 2)
                    txtCap8572BMonto.text.toString().ifEmpty { null } else null,

                if (rbtCap856Si.isChecked && txtCap8572COtra.text.toString().isNotEmpty())
                    txtCap8572COtra.text.toString() else null,
                if (rbtCap856Si.isChecked && spinCap8572C.selectedItemPosition != 0)
                    spinCap8572C.selectedItemPosition.toString() else null,
                if (rbtCap856Si.isChecked && spinCap8572C.selectedItemPosition == 2)
                    txtCap8572CMonto.text.toString().ifEmpty { null } else null,

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