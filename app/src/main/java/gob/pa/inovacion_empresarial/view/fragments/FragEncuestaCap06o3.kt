package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo063InovacionOrganizacionalBinding
import gob.pa.inovacion_empresarial.function.CreateIncon
import gob.pa.inovacion_empresarial.function.Functions.allTrue
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelCap6
import gob.pa.inovacion_empresarial.model.ModelSpinLister
import gob.pa.inovacion_empresarial.view.FormActivity

class FragEncuestaCap06o3 : Fragment() {

    private lateinit var bindingcap6o3: EncuestaCapitulo063InovacionOrganizacionalBinding
    private lateinit var ctx: Context
    private val checkNo46 = MutableList(6) { false }
    private val spinList = mutableListOf<ModelSpinLister>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingcap6o3 = EncuestaCapitulo063InovacionOrganizacionalBinding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingcap6o3.root
    }

    override fun onResume() {
        super.onResume()

        Mob.indiceFormulario = Mob.CAP6_P10
        val infoCap = Mob.infoCap.find { it.indexCap == Mob.CAP6_P10 }
        if (infoCap?.capView == false) fillOut()
        else onAction()
    }

    private fun onAction() {
        fun check46() { bindingcap6o3.layoutCap647.isVisible = !checkNo46.allTrue() }

        with(bindingcap6o3) {
            scrollForm.smoothScrollTo(0,0)
            spinList.clear()
            spinList.add(ModelSpinLister(spinCap6471, Mob.cap6?.v47txtGrado1?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap6472, Mob.cap6?.v47txtGrado2?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap6473, Mob.cap6?.v47txtGrado3?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap6474, Mob.cap6?.v47txtGrado4?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap6475, Mob.cap6?.v47txtGrado5?.toIntOrNull() ?: 0))

            checkNo46[Mob.CHECK1Y2021] = rbtCap6461No2021.isChecked
            checkNo46[Mob.CHECK1Y2022] = rbtCap6461No2022.isChecked
            checkNo46[Mob.CHECK2Y2021] = rbtCap6462No2021.isChecked
            checkNo46[Mob.CHECK2Y2022] = rbtCap6462No2022.isChecked
            checkNo46[Mob.CHECK3Y2021] = rbtCap6463No2021.isChecked
            checkNo46[Mob.CHECK3Y2022] = rbtCap6463No2022.isChecked
            check46()

            rgroupCap64612021.setOnCheckedChangeListener { _, id ->
                checkNo46[Mob.CHECK1Y2021] = rbtCap6461No2021.id == id
                check46()
            }
            rgroupCap64612022.setOnCheckedChangeListener { _, id ->
                checkNo46[Mob.CHECK1Y2022] = rbtCap6461No2022.id == id
                check46()
            }
            rgroupCap64622021.setOnCheckedChangeListener { _, id ->
                checkNo46[Mob.CHECK2Y2021] = rbtCap6462No2021.id == id
                check46()
            }
            rgroupCap64622022.setOnCheckedChangeListener { _, id ->
                checkNo46[Mob.CHECK2Y2022] = rbtCap6462No2022.id == id
                check46()
            }
            rgroupCap64632021.setOnCheckedChangeListener { _, id ->
                checkNo46[Mob.CHECK3Y2021] = rbtCap6463No2021.id == id
                check46()
            }
            rgroupCap64632022.setOnCheckedChangeListener { _, id ->
                checkNo46[Mob.CHECK3Y2022] = rbtCap6463No2022.id == id
                check46()
            }

            val gradoImport = ArrayAdapter(ctx, R.layout.style_box, Mob.arrGrade)
            gradoImport.setDropDownViewResource(R.layout.style_list)

            for (index in 0 until layoutCap647.childCount) {
                val view = layoutCap647.getChildAt(index)
                if (view is Spinner) { view.adapter = gradoImport }
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
        with(bindingcap6o3) {

            setRadioButtonState(rbtCap6461Si2021, rbtCap6461No2021, rgroupCap64612021,
                cap6?.v46check21o1)
            setRadioButtonState(rbtCap6461Si2022, rbtCap6461No2022, rgroupCap64612022,
                cap6?.v46check22o1)
            setRadioButtonState(rbtCap6462Si2021, rbtCap6462No2021, rgroupCap64622021,
                cap6?.v46check21o2)
            setRadioButtonState(rbtCap6462Si2022, rbtCap6462No2022, rgroupCap64622022,
                cap6?.v46check22o2)
            setRadioButtonState(rbtCap6463Si2021, rbtCap6463No2021, rgroupCap64632021,
                cap6?.v46check21o3)
            setRadioButtonState(rbtCap6463Si2022, rbtCap6463No2022, rgroupCap64632022,
                cap6?.v46check22o3)

        }
        Mob.infoCap.find { it.indexCap == Mob.CAP6_P10 }?.capView = true
        onAction()
    }

    private fun setRadioButtonState(
        radioButtonYes: RadioButton,
        radioButtonNo: RadioButton,
        radioGroup: RadioGroup,
        isChecked: Boolean?
    ) {
        when (isChecked) {
            true -> radioButtonYes.isChecked = true
            false -> radioButtonNo.isChecked = true
            else -> radioGroup.clearCheck()
        }
    }

    fun saveCap(): List<String> {
        val allItems: (Boolean) -> Boolean = { it }
        val check = checkNo46.all(allItems)
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

                if (check) null else if (spinCap6471.selectedItemPosition == 0) null else
                    spinCap6471.selectedItemPosition.toString(),
                if (check) null else if (spinCap6472.selectedItemPosition == 0) null else
                    spinCap6472.selectedItemPosition.toString(),
                if (check) null else if (spinCap6473.selectedItemPosition == 0) null else
                    spinCap6473.selectedItemPosition.toString(),
                if (check) null else if (spinCap6474.selectedItemPosition == 0) null else
                    spinCap6474.selectedItemPosition.toString(),
                if (check) null else if (spinCap6475.selectedItemPosition == 0) null else
                    spinCap6475.selectedItemPosition.toString(),
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
        return viewCap()
    }

    private fun viewCap(): List<String> {
        val allItems: (Boolean) -> Boolean = { it }
        val check = checkNo46.all(allItems)
        with(Mob) {
            val returnList: ArrayList<String> = ArrayList()
            if (cap6?.v46check21o1 == null)
                returnList.add(CreateIncon.inconsistencia(ctx, "68") ?: "")
            if (cap6?.v46check22o1 == null)
                returnList.add(CreateIncon.inconsistencia(ctx, "69") ?: "")
            if (cap6?.v46check21o2 == null)
                returnList.add(CreateIncon.inconsistencia(ctx, "70") ?: "")
            if (cap6?.v46check22o2 == null)
                returnList.add(CreateIncon.inconsistencia(ctx, "71") ?: "")
            if (cap6?.v46check21o3 == null)
                returnList.add(CreateIncon.inconsistencia(ctx, "72") ?: "")
            if (cap6?.v46check22o3 == null)
                returnList.add(CreateIncon.inconsistencia(ctx, "73") ?: "")

            if (!check && cap6?.v47txtGrado1.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "74") ?: "")
            if (!check && cap6?.v47txtGrado2.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "75") ?: "")
            if (!check && cap6?.v47txtGrado3.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "76") ?: "")
            if (!check && cap6?.v47txtGrado4.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "77") ?: "")
            if (!check && cap6?.v47txtGrado5.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "78") ?: "")


            infoCap.find { it.indexCap == CAP6_P10 }?.incons = returnList.isNotEmpty()
            //println("Cap6_part3: --$cap6")
            return returnList
        }
    }

}