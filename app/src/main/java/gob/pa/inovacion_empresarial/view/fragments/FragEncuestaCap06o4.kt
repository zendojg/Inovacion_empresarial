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
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo064InovacionComercializacionBinding
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelCap6

class FragEncuestaCap06o4 : Fragment() {
    private lateinit var bindingcap6o4: EncuestaCapitulo064InovacionComercializacionBinding
    private lateinit var ctx: Context
    private lateinit var gr49Adp: ArrayAdapter<String>
    private var gr49Arr: Array<String> = emptyArray()
    private var seecap = true

    private var indice01 = 0
    private var indice02 = 0
    private var indice03 = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingcap6o4 = EncuestaCapitulo064InovacionComercializacionBinding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingcap6o4.root
    }

    override fun onResume() {
        super.onResume()


        Mob.indiceEnc = Mob.CAP6P11
        if (seecap) fillOut()
        else onAction()
    }

    private fun onAction() {
        with(bindingcap6o4) {
            lowCap6o4.setOnClickListener { saveCap() }

            gr49Arr = ctx.resources.getStringArray(R.array.arr_hightolow)
            gr49Adp = ArrayAdapter(ctx, R.layout.style_box, gr49Arr)
            gr49Adp.setDropDownViewResource(R.layout.style_list)

            spinCap6491.adapter = gr49Adp
            spinCap6492.adapter = gr49Adp
            spinCap6493.adapter = gr49Adp
            spinsAction()
        }
    }

    private fun spinsAction() {
        with(bindingcap6o4) {

            spinCap6491.setSelection(indice01)
            spinCap6492.setSelection(indice02)
            spinCap6493.setSelection(indice03)

            spinCap6491.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice01 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---$indice01")
                }
            }
            spinCap6492.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice02 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---$indice02")
                }
            }
            spinCap6493.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice03 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---$indice03")
                }
            }
        }
    }

    private fun fillOut() {
        val cap6 = Mob.formComp?.cap6
            fillOut48(cap6)
            indice01 = try {
                cap6?.v49txtGrado1?.toInt() ?: 0
            } catch (e: java.lang.NumberFormatException) {
                0
            }
            indice02 = try {
                cap6?.v49txtGrado2?.toInt() ?: 0
            } catch (e: java.lang.NumberFormatException) {
                0
            }
            indice03 = try {
                cap6?.v49txtGrado3?.toInt() ?: 0
            } catch (e: java.lang.NumberFormatException) {
                0
            }

        seecap = false
        onAction()
    }

    private fun fillOut48(cap6: ModelCap6?) {
        with(bindingcap6o4) {
            when (cap6?.v48check21o1) {
                true -> rbtCap6481Si2021.isChecked = true
                false -> rbtCap6481No2021.isChecked = true
                else -> rgroupCap64812021.clearCheck()
            }
            when (cap6?.v48check22o1) {
                true -> rbtCap6481Si2022.isChecked = true
                false -> rbtCap6481No2022.isChecked = true
                else -> rgroupCap64812022.clearCheck()
            }

            when (cap6?.v48check21o2) {
                true -> rbtCap6482Si2021.isChecked = true
                false -> rbtCap6482No2021.isChecked = true
                else -> rgroupCap64822021.clearCheck()
            }
            when (cap6?.v46check22o2) {
                true -> rbtCap6482Si2022.isChecked = true
                false -> rbtCap6482No2022.isChecked = true
                else -> rgroupCap64822022.clearCheck()
            }

            when (cap6?.v48check21o3) {
                true -> rbtCap6483Si2021.isChecked = true
                false -> rbtCap6483No2021.isChecked = true
                else -> rgroupCap64832021.clearCheck()
            }
            when (cap6?.v48check22o3) {
                true -> rbtCap6483Si2022.isChecked = true
                false -> rbtCap6483No2022.isChecked = true
                else -> rgroupCap64832022.clearCheck()
            }

            when (cap6?.v48check21o4) {
                true -> rbtCap6484Si2021.isChecked = true
                false -> rbtCap6484No2021.isChecked = true
                else -> rgroupCap64842021.clearCheck()
            }
            when (cap6?.v48check22o4) {
                true -> rbtCap6484Si2022.isChecked = true
                false -> rbtCap6484No2022.isChecked = true
                else -> rgroupCap64842022.clearCheck()
            }
        }
    }


    private fun saveCap() {
        TODO("Not yet implemented")
    }

}