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
    private var seecap = true

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

        Mob.indiceEnc = Mob.CAP6P09
        if (seecap) fillOut()
        else onAction()
    }

    private fun onAction() {
        with(bindingcap6o2) {
            lowCap6o2.setOnClickListener { saveCap() }

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

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap6452.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice02 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap6453.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice03 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap6454.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice04 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap6455.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice05 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap6456.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice06 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
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
            when (cap6?.v44check) {
                "1" -> rbtCap6441.isChecked = true
                "2" -> rbtCap6442.isChecked = true
                "3" -> rbtCap6443.isChecked = true
                "4" -> rbtCap6444.isChecked = true
                else -> rgroupCap644.clearCheck()
            }
            indice01 = try {
                cap6?.v45txtGrado1?.toInt() ?: 0
            } catch (e: java.lang.NumberFormatException) {
                0
            }
            indice02 = try {
                cap6?.v45txtGrado2?.toInt() ?: 0
            } catch (e: java.lang.NumberFormatException) {
                0
            }
            indice03 = try {
                cap6?.v45txtGrado3?.toInt() ?: 0
            } catch (e: java.lang.NumberFormatException) {
                0
            }
            indice04 = try {
                cap6?.v45txtGrado4?.toInt() ?: 0
            } catch (e: java.lang.NumberFormatException) {
                0
            }
            indice05 = try {
                cap6?.v45txtGrado5?.toInt() ?: 0
            } catch (e: java.lang.NumberFormatException) {
                0
            }
            indice06 = try {
                cap6?.v45txtGrado6?.toInt() ?: 0
            } catch (e: java.lang.NumberFormatException) {
                0
            }
        }
        seecap = false
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


    private fun saveCap() {
        TODO("Not yet implemented")
    }
}