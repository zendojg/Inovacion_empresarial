package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo063InovacionOrganizacionalBinding
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelCap6

class FragEncuestaCap06o3 : Fragment() {

    private lateinit var bindingcap6o3: EncuestaCapitulo063InovacionOrganizacionalBinding
    private lateinit var ctx: Context
    private var seecap = true

    private var indice01 = 0
    private var indice02 = 0
    private var indice03 = 0
    private var indice04 = 0
    private var indice05 = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingcap6o3 = EncuestaCapitulo063InovacionOrganizacionalBinding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingcap6o3.root
    }

    override fun onResume() {
        super.onResume()

        Mob.indiceFormulario = Mob.CAP6P10
        if (seecap) fillOut()
        else onAction()
    }

    private fun onAction() {

        val gr47Adp = ArrayAdapter(ctx, R.layout.style_box, Mob.arrGrade)
        gr47Adp.setDropDownViewResource(R.layout.style_list)

        with(bindingcap6o3) {
            lowCap6o3.setOnClickListener { saveCap() }

            spinCap6471.adapter = gr47Adp
            spinCap6472.adapter = gr47Adp
            spinCap6473.adapter = gr47Adp
            spinCap6474.adapter = gr47Adp
            spinCap6475.adapter = gr47Adp

            spinsAction()
        }
    }

    private fun spinsAction() {
        with(bindingcap6o3) {
            spinCap6471.setSelection(indice01)
            spinCap6472.setSelection(indice02)
            spinCap6473.setSelection(indice03)
            spinCap6474.setSelection(indice04)
            spinCap6475.setSelection(indice05)

            spinCap6471.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice01 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap6472.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice02 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap6473.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice03 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap6474.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice04 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap6475.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice05 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
        }
    }

    private fun fillOut() {
        val cap6 = Mob.formComp?.cap6

        fillOut46(cap6)

        indice01 = try { cap6?.v47txtGrado1?.toInt() ?: 0 }
        catch (e: java.lang.NumberFormatException) { 0 }
        indice02 = try { cap6?.v47txtGrado2?.toInt() ?: 0 }
        catch (e: java.lang.NumberFormatException) { 0 }
        indice03 = try { cap6?.v47txtGrado3?.toInt() ?: 0 }
        catch (e: java.lang.NumberFormatException) { 0 }
        indice04 = try { cap6?.v47txtGrado4?.toInt() ?: 0 }
        catch (e: java.lang.NumberFormatException) { 0 }
        indice05 = try { cap6?.v47txtGrado5?.toInt() ?: 0 }
        catch (e: java.lang.NumberFormatException) { 0 }

        seecap = false
        onAction()
    }

    private fun fillOut46(cap6: ModelCap6?) {
        with(bindingcap6o3) {
            when (cap6?.v46check21o1) {
                true -> rbtCap6461Si2021.isChecked = true
                false -> rbtCap6461No2021.isChecked = true
                else -> rgroupCap64612021.clearCheck()
            }
            when (cap6?.v46check22o1) {
                true -> rbtCap6461Si2022.isChecked = true
                false -> rbtCap6461No2022.isChecked = true
                else -> rgroupCap64612022.clearCheck()
            }

            when (cap6?.v46check21o2) {
                true -> rbtCap6462Si2021.isChecked = true
                false -> rbtCap6462No2021.isChecked = true
                else -> rgroupCap64622021.clearCheck()
            }
            when (cap6?.v46check22o2) {
                true -> rbtCap6462Si2022.isChecked = true
                false -> rbtCap6462No2022.isChecked = true
                else -> rgroupCap64622022.clearCheck()
            }

            when (cap6?.v46check21o3) {
                true -> rbtCap6463Si2021.isChecked = true
                false -> rbtCap6463No2021.isChecked = true
                else -> rgroupCap64632021.clearCheck()
            }
            when (cap6?.v46check22o3) {
                true -> rbtCap6463Si2022.isChecked = true
                false -> rbtCap6463No2022.isChecked = true
                else -> rgroupCap64632022.clearCheck()
            }
        }
    }


    fun saveCap() {
        with (bindingcap6o3) {
            Mob.cap6 = ModelCap6(
                Mob.cap6?.id,
                Mob.cap6?.ncontrol,
                Mob.cap6?.v39check21o1,
                Mob.cap6?.v39check22o1,
                Mob.cap6?.v39check21o2,
                Mob.cap6?.v39check22o2,
                Mob.cap6?.v40check1,
                Mob.cap6?.v40check2,
                Mob.cap6?.v41check,
                Mob.cap6?.v42check21o1,
                Mob.cap6?.v42check21o2,
                Mob.cap6?.v42check21o3,
                Mob.cap6?.v42check22o1,
                Mob.cap6?.v42check22o2,
                Mob.cap6?.v42check22o3,
                Mob.cap6?.v43check1,
                Mob.cap6?.v43check2,
                Mob.cap6?.v44check,
                Mob.cap6?.v45txtGrado1,
                Mob.cap6?.v45txtGrado2,
                Mob.cap6?.v45txtGrado3,
                Mob.cap6?.v45txtGrado4,
                Mob.cap6?.v45txtGrado5,
                Mob.cap6?.v45txtGrado6,
                if (rbtCap6461Si2021.isChecked) true else
                    if (rbtCap6461No2021.isChecked) false else null,
                if (rbtCap6462Si2021.isChecked) true else
                    if (rbtCap6462No2021.isChecked) false else null,
                if (rbtCap6463Si2021.isChecked) true else
                    if (rbtCap6463No2021.isChecked) false else null,
                if (rbtCap6461Si2022.isChecked) true else
                    if (rbtCap6461No2022.isChecked) false else null,
                if (rbtCap6462Si2022.isChecked) true else
                    if (rbtCap6462No2022.isChecked) false else null,
                if (rbtCap6463Si2022.isChecked) true else
                    if (rbtCap6463No2022.isChecked) false else null,
                if (indice01 == 0) null else indice01.toString(),
                if (indice02 == 0) null else indice02.toString(),
                if (indice03 == 0) null else indice03.toString(),
                if (indice04 == 0) null else indice04.toString(),
                if (indice05 == 0) null else indice05.toString(),
                Mob.cap6?.v48check21o1,
                Mob.cap6?.v48check21o2,
                Mob.cap6?.v48check21o3,
                Mob.cap6?.v48check21o4,
                Mob.cap6?.v48check22o1,
                Mob.cap6?.v48check22o2,
                Mob.cap6?.v48check22o3,
                Mob.cap6?.v48check22o4,
                Mob.cap6?.v49txtGrado1,
                Mob.cap6?.v49txtGrado2,
                Mob.cap6?.v49txtGrado3
            )
        }
        println("----------${Mob.cap6}")
    }

}