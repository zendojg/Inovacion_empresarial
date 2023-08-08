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
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo062InovacionProcesoBinding
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelCap6

class FragEncuestaCap06o2 : Fragment() {

    private lateinit var bindingcap6o2: EncuestaCapitulo062InovacionProcesoBinding
    private lateinit var ctx: Context

    private var check44: String? = ""
    private var indice01 = 0
    private var indice02 = 0
    private var indice03 = 0
    private var indice04 = 0
    private var indice05 = 0
    private var indice06 = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingcap6o2 = EncuestaCapitulo062InovacionProcesoBinding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingcap6o2.root
    }

    override fun onResume() {
        super.onResume()

        Mob.indiceFormulario = Mob.CAP6P09
        if (Mob.seecap06o2) fillOut()
        else onAction()
    }

    private fun onAction() {
        with(bindingcap6o2) {
            lowCap6o2.setOnClickListener { saveCap() }
            rgroupCap644.setOnCheckedChangeListener { _, id ->
                check44 = when (id) {
                    rbtCap6441.id -> "1"
                    rbtCap6442.id -> "2"
                    rbtCap6443.id -> "3"
                    rbtCap6444.id -> "4"
                    else -> null
                }
            }
            val gr45Adp = ArrayAdapter(ctx, R.layout.style_box, Mob.arrGrade)
            gr45Adp.setDropDownViewResource(R.layout.style_list)

            spinCap6451.adapter = gr45Adp
            spinCap6452.adapter = gr45Adp
            spinCap6453.adapter = gr45Adp
            spinCap6454.adapter = gr45Adp
            spinCap6455.adapter = gr45Adp
            spinCap6456.adapter = gr45Adp

            sipnAction()
        }
    }

    private fun sipnAction() {
        with(bindingcap6o2) {
            spinCap6451.setSelection(indice01)
            spinCap6452.setSelection(indice02)
            spinCap6453.setSelection(indice03)
            spinCap6454.setSelection(indice04)
            spinCap6455.setSelection(indice05)
            spinCap6456.setSelection(indice06)

            spinCap6451.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice01 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) { println("---NO selection") }
            }
            spinCap6452.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice02 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) { println("---NO selection") }
            }
            spinCap6453.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice03 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) { println("---NO selection") }
            }
            spinCap6454.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice04 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) { println("---NO selection")
                }
            }
            spinCap6455.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice05 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) { println("---NO selection") }
            }
            spinCap6456.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice06 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) { println("---NO selection") }
            }
        }
    }


    private fun fillOut() {
        val cap6 = Mob.formComp?.cap6
        with(bindingcap6o2) {
            fillOut42(cap6)
            when (cap6?.v43check1) {
                true -> rbtCap6431Si.isChecked = true
                false -> rbtCap6431No.isChecked = true
                else -> rgroupCap6431.clearCheck()
            }
            when (cap6?.v43check2) {
                true -> rbtCap6432Si.isChecked = true
                false -> rbtCap6432No.isChecked = true
                else -> rgroupCap6432.clearCheck()
            }
            check44 = cap6?.v44check
            when (cap6?.v44check) {
                "1" -> rbtCap6441.isChecked = true
                "2" -> rbtCap6442.isChecked = true
                "3" -> rbtCap6443.isChecked = true
                "4" -> rbtCap6444.isChecked = true
                else -> {
                    check44 = null
                    rgroupCap644.clearCheck()
                }
            }
            indice01 = try { cap6?.v45txtGrado1?.toInt() ?: 0 }
            catch (e: java.lang.NumberFormatException) { 0 }
            indice02 = try { cap6?.v45txtGrado2?.toInt() ?: 0 }
            catch (e: java.lang.NumberFormatException) { 0 }
            indice03 = try { cap6?.v45txtGrado3?.toInt() ?: 0 }
            catch (e: java.lang.NumberFormatException) { 0 }
            indice04 = try { cap6?.v45txtGrado4?.toInt() ?: 0 }
            catch (e: java.lang.NumberFormatException) { 0 }
            indice05 = try { cap6?.v45txtGrado5?.toInt() ?: 0 }
            catch (e: java.lang.NumberFormatException) { 0 }
            indice06 = try { cap6?.v45txtGrado6?.toInt() ?: 0 }
            catch (e: java.lang.NumberFormatException) { 0 }
        }
        Mob.seecap06o2 = false
        onAction()
    }

    private fun fillOut42(cap6: ModelCap6?) {
        with(bindingcap6o2) {
            when (cap6?.v42check21o1) {
                true -> rbtCap6421Si2021.isChecked = true
                false -> rbtCap6421No2021.isChecked = true
                else -> rgroupCap64212021.clearCheck()
            }
            when (cap6?.v42check22o1) {
                true -> rbtCap6421Si2022.isChecked = true
                false -> rbtCap6421No2022.isChecked = true
                else -> rgroupCap64212022.clearCheck()
            }

            when (cap6?.v42check21o2) {
                true -> rbtCap6422Si2021.isChecked = true
                false -> rbtCap6422No2021.isChecked = true
                else -> rgroupCap64222021.clearCheck()
            }
            when (cap6?.v42check22o2) {
                true -> rbtCap6422Si2022.isChecked = true
                false -> rbtCap6422No2022.isChecked = true
                else -> rgroupCap64222022.clearCheck()
            }

            when (cap6?.v42check21o3) {
                true -> rbtCap6423Si2021.isChecked = true
                false -> rbtCap6423No2021.isChecked = true
                else -> rgroupCap64232021.clearCheck()
            }
            when (cap6?.v42check22o3) {
                true -> rbtCap6423Si2022.isChecked = true
                false -> rbtCap6423No2022.isChecked = true
                else -> rgroupCap64232022.clearCheck()
            }
        }
    }


    fun saveCap() {
        with (bindingcap6o2) {
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
                if (rbtCap6421Si2021.isChecked) true else
                    if (rbtCap6421No2021.isChecked) false else null,
                if (rbtCap6422Si2021.isChecked) true else
                    if (rbtCap6422No2021.isChecked) false else null,
                if (rbtCap6423Si2021.isChecked) true else
                    if (rbtCap6423No2021.isChecked) false else null,
                if (rbtCap6421Si2022.isChecked) true else
                    if (rbtCap6421No2022.isChecked) false else null,
                if (rbtCap6422Si2022.isChecked) true else
                    if (rbtCap6422No2022.isChecked) false else null,
                if (rbtCap6423Si2022.isChecked) true else
                    if (rbtCap6423No2022.isChecked) false else null,
                if (rbtCap6431Si.isChecked) true else
                    if (rbtCap6431No.isChecked) false else null,
                if (rbtCap6432Si.isChecked) true else
                    if (rbtCap6432No.isChecked) false else null,
                check44,
                if (indice01 == 0) null else indice01.toString(),
                if (indice02 == 0) null else indice02.toString(),
                if (indice03 == 0) null else indice03.toString(),
                if (indice04 == 0) null else indice04.toString(),
                if (indice05 == 0) null else indice05.toString(),
                if (indice06 == 0) null else indice06.toString(),
                //if (indice06 == 0) null else spinCap6456.selectedItemPosition.toString(),
                Mob.cap6?.v46check21o1,//
                Mob.cap6?.v46check21o2,
                Mob.cap6?.v46check21o3,
                Mob.cap6?.v46check22o1,
                Mob.cap6?.v46check22o2,
                Mob.cap6?.v46check22o3,
                Mob.cap6?.v47txtGrado1,
                Mob.cap6?.v47txtGrado2,
                Mob.cap6?.v47txtGrado3,
                Mob.cap6?.v47txtGrado4,
                Mob.cap6?.v47txtGrado5,
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