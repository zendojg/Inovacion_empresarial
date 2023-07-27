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
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo08CoclusionBinding
import gob.pa.inovacion_empresarial.function.Functions.toEditable
import gob.pa.inovacion_empresarial.model.Mob

class FragEncuestaCap08end : Fragment() {

    private lateinit var bindingcap8o2: EncuestaCapitulo08CoclusionBinding
    private lateinit var ctx: Context
    private var seecap = true

    private var indice1a = 0
    private var indice1b = 0
    private var indice1c = 0

    private var indice2a = 0
    private var indice2b = 0
    private var indice2c = 0
    private var indice2d = 0

    private var indice3a = 0
    private var indice3b = 0

    private var indice4a = 0
    private var indice4b = 0
    private var indice4c = 0
    private var indice4d = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingcap8o2 = EncuestaCapitulo08CoclusionBinding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingcap8o2.root
    }

    override fun onResume() {
        super.onResume()

        Mob.indiceEnc = Mob.CAP8P16
        if (seecap) fillOut()
        else onAction()
    }

    private fun onAction() {
        with(bindingcap8o2) {
            lowCap8o2.setOnClickListener { saveCap() }
            if (Mob.p56stat == true) tb58.isVisible = true
            else if (Mob.p56stat == false) tb58.isVisible = false

            val grade58 = ArrayAdapter(ctx, R.layout.style_box, Mob.arrGrade)
            grade58.setDropDownViewResource(R.layout.style_list)

            spinCap8581A.adapter = grade58
            spinCap8581B.adapter = grade58
            spinCap8581C.adapter = grade58
            spinCap8582A.adapter = grade58
            spinCap8582B.adapter = grade58
            spinCap8582C.adapter = grade58
            spinCap8582D.adapter = grade58
            spinCap8583A.adapter = grade58
            spinCap8583B.adapter = grade58
            spinCap8584A.adapter = grade58
            spinCap8584B.adapter = grade58
            spinCap8584C.adapter = grade58
            spinCap8584D.adapter = grade58

            spinCap8581A.setSelection(indice1a)
            spinCap8581B.setSelection(indice1b)
            spinCap8581C.setSelection(indice1c)
            spinCap8582A.setSelection(indice2a)
            spinCap8582B.setSelection(indice2b)
            spinCap8582C.setSelection(indice2c)
            spinCap8582D.setSelection(indice2d)
            spinCap8583A.setSelection(indice3a)
            spinCap8583B.setSelection(indice3b)
            spinCap8584A.setSelection(indice4a)
            spinCap8584B.setSelection(indice4b)
            spinCap8584C.setSelection(indice4c)
            spinCap8584D.setSelection(indice4d)

            //txtCap8571AMontoly.isVisible = spinCap8571A.selectedItem.toString() == Mob.arrOBT[Mob.SOLICSI2]
            spinsAction()
        }
    }

    private fun spinsAction() {
        with(bindingcap8o2) {

            spinCap8581A.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice1a = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap8581B.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice1b = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap8581C.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice1c = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap8582A.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice2a = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap8582B.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice2b = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap8582C.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice2c = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap8582D.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice2d = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap8583A.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice3a = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap8583B.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice3b = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap8582A.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice4a = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap8582B.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice4b = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap8582C.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice4c = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap8582D.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice4d = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
        }
    }

    private fun fillOut() {
        val cap8 = Mob.formComp?.cap8

        indice1a = try { cap8?.v58num1a ?: 0 } catch (e: java.lang.NumberFormatException) { 0 }
        indice1b = try { cap8?.v58num1b ?: 0 } catch (e: java.lang.NumberFormatException) { 0 }
        indice1c = try { cap8?.v58num1c ?: 0 } catch (e: java.lang.NumberFormatException) { 0 }
        indice2a = try { cap8?.v58num2a ?: 0 } catch (e: java.lang.NumberFormatException) { 0 }
        indice2b = try { cap8?.v58num2b ?: 0 } catch (e: java.lang.NumberFormatException) { 0 }
        indice2c = try { cap8?.v58num2c ?: 0 } catch (e: java.lang.NumberFormatException) { 0 }
        indice2d = try { cap8?.v58num2d ?: 0 } catch (e: java.lang.NumberFormatException) { 0 }
        indice3a = try { cap8?.v58num3a ?: 0 } catch (e: java.lang.NumberFormatException) { 0 }
        indice3b = try { cap8?.v58num3b ?: 0 } catch (e: java.lang.NumberFormatException) { 0 }
        indice4a = try { cap8?.v58num4a ?: 0 } catch (e: java.lang.NumberFormatException) { 0 }
        indice4b = try { cap8?.v58num4b ?: 0 } catch (e: java.lang.NumberFormatException) { 0 }
        indice4c = try { cap8?.v58num4c ?: 0 } catch (e: java.lang.NumberFormatException) { 0 }
        indice4d = try { cap8?.v58num4d ?: 0 } catch (e: java.lang.NumberFormatException) { 0 }

        bindingcap8o2.txtCap8584DOtra.text = cap8?.v58desc4d?.toEditable() ?: "".toEditable()

        seecap = false
        onAction()
    }


    fun saveCap() {
    }

}