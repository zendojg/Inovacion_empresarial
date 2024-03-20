package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo064InovacionComercializacionBinding
import gob.pa.inovacion_empresarial.function.CreateIncon
import gob.pa.inovacion_empresarial.function.Functions.allTrue
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelCap6
import gob.pa.inovacion_empresarial.model.ModelSpinLister

class FragEncuestaCap06o4 : Fragment() {
    private lateinit var bindingcap6o4: EncuestaCapitulo064InovacionComercializacionBinding
    private lateinit var ctx: Context
    private var checkNo48: MutableList<Boolean> = MutableList(8) { false }
    private val spinList = mutableListOf<ModelSpinLister>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingcap6o4 = EncuestaCapitulo064InovacionComercializacionBinding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingcap6o4.root
    }

    override fun onResume() {
        super.onResume()
        Mob.indiceFormulario = Mob.CAP6_P11
        val infoCap = Mob.infoCap.find { it.indexCap == Mob.CAP6_P11 }
        if (infoCap?.capView == false) fillOut()
        else onAction()
    }

    private fun onAction() {
        fun check48() { bindingcap6o4.layoutCap649.isVisible = !checkNo48.allTrue() }
        with(bindingcap6o4) {

            spinList.add(ModelSpinLister(spinCap6491, Mob.cap6?.v45txtGrado1?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap6492, Mob.cap6?.v45txtGrado2?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap6493, Mob.cap6?.v45txtGrado3?.toIntOrNull() ?: 0))

            checkNo48[Mob.CHECK1Y2021] = rbtCap6481No2021.isChecked
            checkNo48[Mob.CHECK1Y2022] = rbtCap6481No2022.isChecked
            checkNo48[Mob.CHECK2Y2021] = rbtCap6482No2021.isChecked
            checkNo48[Mob.CHECK2Y2022] = rbtCap6482No2022.isChecked
            checkNo48[Mob.CHECK3Y2021] = rbtCap6483No2021.isChecked
            checkNo48[Mob.CHECK3Y2022] = rbtCap6483No2022.isChecked
            checkNo48[Mob.CHECK4Y2021] = rbtCap6484No2021.isChecked
            checkNo48[Mob.CHECK4Y2022] = rbtCap6484No2022.isChecked
            check48()

            rgroupCap64812021.setOnCheckedChangeListener { _, id ->
                checkNo48[Mob.CHECK1Y2021] = rbtCap6481No2021.id == id
                check48()
            }
            rgroupCap64812022.setOnCheckedChangeListener { _, id ->
                checkNo48[Mob.CHECK1Y2022] = rbtCap6481No2022.id == id
                check48()
            }
            rgroupCap64822021.setOnCheckedChangeListener { _, id ->
                checkNo48[Mob.CHECK2Y2021] = rbtCap6482No2021.id == id
                check48()
            }
            rgroupCap64822022.setOnCheckedChangeListener { _, id ->
                checkNo48[Mob.CHECK2Y2022] = rbtCap6482No2022.id == id
                check48()
            }
            rgroupCap64832021.setOnCheckedChangeListener { _, id ->
                checkNo48[Mob.CHECK3Y2021] = rbtCap6483No2021.id == id
                check48()
            }
            rgroupCap64832022.setOnCheckedChangeListener { _, id ->
                checkNo48[Mob.CHECK3Y2022] = rbtCap6483No2022.id == id
                check48()
            }
            rgroupCap64842021.setOnCheckedChangeListener { _, id ->
                checkNo48[Mob.CHECK4Y2021] = rbtCap6484No2021.id == id
                check48()
            }
            rgroupCap64842022.setOnCheckedChangeListener { _, id ->
                checkNo48[Mob.CHECK4Y2022] = rbtCap6484No2022.id == id
                check48()
            }

            val gr49Adp = ArrayAdapter(ctx, R.layout.style_box, Mob.arrGrade)
            gr49Adp.setDropDownViewResource(R.layout.style_list)

            for (index in 0 until layoutCap649.childCount) {
                val view = layoutCap649.getChildAt(index)
                if (view is Spinner) { view.adapter = gr49Adp }
            }
            for (modelSpinLister in spinList) {
                val spinner = modelSpinLister.spinner
                val indice = modelSpinLister.indice
                if (indice >= 0 && indice < spinner.adapter.count) { spinner.setSelection(indice) }
            }
            for (modelSpinLister in spinList) {
                val spinner = modelSpinLister.spinner

                spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) { modelSpinLister.indice = position }
                    override fun onNothingSelected(parent: AdapterView<*>?) { /* sin selección */ }
                }
            }
        }
    }

    private fun fillOut() {
        val cap6 = Mob.formComp?.cap6
        with(bindingcap6o4) {
            scrollForm.smoothScrollTo(0,0)
            val radioButtonsMap = mapOf(
                rgroupCap64812021 to cap6?.v48check21o1,
                rgroupCap64812022 to cap6?.v48check22o1,
                rgroupCap64822021 to cap6?.v48check21o2,
                rgroupCap64822022 to cap6?.v48check22o2,
                rgroupCap64832021 to cap6?.v48check21o3,
                rgroupCap64832022 to cap6?.v48check22o3,
                rgroupCap64842021 to cap6?.v48check21o4,
                rgroupCap64842022 to cap6?.v48check22o4
            )
            for ((radioGroup, isChecked) in radioButtonsMap) {
                when (isChecked) {
                    true -> radioGroup.check(radioGroup.getChildAt(0).id)
                    false -> radioGroup.check(radioGroup.getChildAt(1).id)
                    null -> radioGroup.clearCheck()
                }
            }
        }
        Mob.infoCap.find { it.indexCap == Mob.CAP6_P11 }?.capView = true
        onAction()
    }


    fun saveCap(): List<String> {
        val allItems: (Boolean) -> Boolean = { it }
        val check = checkNo48.all(allItems)
        with (bindingcap6o4) {
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
                Mob.cap6?.v46check21o1,
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
                if (rbtCap6481Si2021.isChecked) true else
                    if (rbtCap6481No2021.isChecked) false else null,
                if (rbtCap6482Si2021.isChecked) true else
                    if (rbtCap6482No2021.isChecked) false else null,
                if (rbtCap6483Si2021.isChecked) true else
                    if (rbtCap6483No2021.isChecked) false else null,
                if (rbtCap6484Si2021.isChecked) true else
                    if (rbtCap6484No2021.isChecked) false else null,
                if (rbtCap6481Si2022.isChecked) true else
                    if (rbtCap6481No2022.isChecked) false else null,
                if (rbtCap6482Si2022.isChecked) true else
                    if (rbtCap6482No2022.isChecked) false else null,
                if (rbtCap6483Si2022.isChecked) true else
                    if (rbtCap6483No2022.isChecked) false else null,
                if (rbtCap6484Si2022.isChecked) true else
                    if (rbtCap6484No2022.isChecked) false else null,
                if (check) null else if (spinCap6491.selectedItemPosition == 0) null else
                    spinCap6491.selectedItemPosition.toString(),
                if (check) null else if (spinCap6492.selectedItemPosition == 0) null else
                    spinCap6492.selectedItemPosition.toString(),
                if (check) null else if (spinCap6493.selectedItemPosition == 0) null else
                    spinCap6493.selectedItemPosition.toString()
            )
        }
        return viewCap()
    }

    private fun viewCap(): List<String> {
        val allItems: (Boolean) -> Boolean = { it }
        val check = checkNo48.all(allItems)
        with(Mob) {
            val returnList: ArrayList<String> = ArrayList()
            if (cap6?.v48check21o1 == null)
                returnList.add(CreateIncon.inconsistencia(ctx, "79") ?: "")
            if (cap6?.v48check22o1 == null)
                returnList.add(CreateIncon.inconsistencia(ctx, "80") ?: "")
            if (cap6?.v48check21o2 == null)
                returnList.add(CreateIncon.inconsistencia(ctx, "81") ?: "")
            if (cap6?.v48check22o2 == null)
                returnList.add(CreateIncon.inconsistencia(ctx, "82") ?: "")
            if (cap6?.v48check21o3 == null)
                returnList.add(CreateIncon.inconsistencia(ctx, "83") ?: "")
            if (cap6?.v48check22o3 == null)
                returnList.add(CreateIncon.inconsistencia(ctx, "84") ?: "")
            if (cap6?.v48check21o4 == null)
                returnList.add(CreateIncon.inconsistencia(ctx, "85") ?: "")
            if (cap6?.v48check22o4 == null)
                returnList.add(CreateIncon.inconsistencia(ctx, "86") ?: "")

            if (!check && cap6?.v49txtGrado1.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "87") ?: "")
            if (!check && cap6?.v49txtGrado1.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "88") ?: "")
            if (!check && cap6?.v49txtGrado1.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "89") ?: "")

            infoCap.find { it.indexCap == CAP6_P11 }?.incons = returnList.isNotEmpty()
            //println("Cap6-part4: --$cap6")
            return returnList
        }
    }
}