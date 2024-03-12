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
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo062InovacionProcesoBinding
import gob.pa.inovacion_empresarial.function.CreateIncon
import gob.pa.inovacion_empresarial.function.Functions.allTrue
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelCap6
import gob.pa.inovacion_empresarial.model.ModelSpinLister
import gob.pa.inovacion_empresarial.view.FormActivity

class FragEncuestaCap06o2 : Fragment() {

    private lateinit var bindingcap6o2: EncuestaCapitulo062InovacionProcesoBinding
    private lateinit var ctx: Context
    private var checkNo42: MutableList<Boolean> = MutableList(6) { false }
    private val spinList = mutableListOf<ModelSpinLister>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingcap6o2 = EncuestaCapitulo062InovacionProcesoBinding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingcap6o2.root
    }

    override fun onResume() {
        super.onResume()

        Mob.indiceFormulario = Mob.CAP6_P09
        val infoCap = Mob.infoCap.find { it.indexCap == Mob.CAP6_P09 }
        if (infoCap?.capView == false) fillOut()
        else onAction()
    }

    private fun onAction() {
        fun check42() {
            with(bindingcap6o2) {
                layoutCap643.isVisible = !checkNo42.allTrue()
                layoutCap644.isVisible = !checkNo42.allTrue()
                layoutCap645.isVisible = !checkNo42.allTrue()
            }
        }

        with(bindingcap6o2) {

            spinList.clear()
            spinList.add(ModelSpinLister(spinCap6451, Mob.cap6?.v45txtGrado1?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap6452, Mob.cap6?.v45txtGrado2?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap6453, Mob.cap6?.v45txtGrado3?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap6454, Mob.cap6?.v45txtGrado4?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap6455, Mob.cap6?.v45txtGrado5?.toIntOrNull() ?: 0))
            spinList.add(ModelSpinLister(spinCap6456, Mob.cap6?.v45txtGrado6?.toIntOrNull() ?: 0))

            checkNo42[Mob.CHECK1Y2021] = rbtCap6421No2021.isChecked
            checkNo42[Mob.CHECK1Y2022] = rbtCap6421No2022.isChecked
            checkNo42[Mob.CHECK2Y2021] = rbtCap6422No2021.isChecked
            checkNo42[Mob.CHECK2Y2022] = rbtCap6422No2022.isChecked
            checkNo42[Mob.CHECK3Y2021] = rbtCap6422No2021.isChecked
            checkNo42[Mob.CHECK3Y2022] = rbtCap6422No2022.isChecked
            check42()

            rgroupCap64212021.setOnCheckedChangeListener { _, id ->
                checkNo42[Mob.CHECK1Y2021] = rbtCap6421No2021.id == id
                check42()
            }
            rgroupCap64212022.setOnCheckedChangeListener { _, id ->
                checkNo42[Mob.CHECK1Y2022] = rbtCap6421No2022.id == id
                check42()
            }
            rgroupCap64222021.setOnCheckedChangeListener { _, id ->
                checkNo42[Mob.CHECK2Y2021] = rbtCap6422No2021.id == id
                check42()
            }
            rgroupCap64222022.setOnCheckedChangeListener { _, id ->
                checkNo42[Mob.CHECK2Y2022] = rbtCap6422No2022.id == id
                check42()
            }
            rgroupCap64232021.setOnCheckedChangeListener { _, id ->
                checkNo42[Mob.CHECK3Y2021] = rbtCap6423No2021.id == id
                check42()
            }
            rgroupCap64232022.setOnCheckedChangeListener { _, id ->
                checkNo42[Mob.CHECK3Y2022] = rbtCap6423No2022.id == id
                check42()
            }

            val gradoImport = ArrayAdapter(ctx, R.layout.style_box, Mob.arrGrade)
            gradoImport.setDropDownViewResource(R.layout.style_list)

            for (index in 0 until layoutCap645.childCount) {
                val view = layoutCap645.getChildAt(index)
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
        with(bindingcap6o2) {
            scrollForm.smoothScrollTo(0,0)
            setRBState(rbtCap6421Si2021, rbtCap6421No2021, rgroupCap64212021, cap6?.v42check21o1)
            setRBState(rbtCap6421Si2022, rbtCap6421No2022, rgroupCap64212022, cap6?.v42check22o1)

            setRBState(rbtCap6422Si2021, rbtCap6422No2021, rgroupCap64222021, cap6?.v42check21o2)
            setRBState(rbtCap6422Si2022, rbtCap6422No2022, rgroupCap64222022, cap6?.v42check22o2)

            setRBState(rbtCap6423Si2021, rbtCap6423No2021, rgroupCap64232021, cap6?.v42check21o3)
            setRBState(rbtCap6423Si2022, rbtCap6423No2022, rgroupCap64232022, cap6?.v42check22o3)

            setRBState(rbtCap6431Si, rbtCap6431No, rgroupCap6431, cap6?.v43check1)
            setRBState(rbtCap6432Si, rbtCap6432No, rgroupCap6432, cap6?.v43check2)

            when (cap6?.v44check) {
                "1" -> rbtCap6441.isChecked = true
                "2" -> rbtCap6442.isChecked = true
                "3" -> rbtCap6443.isChecked = true
                "4" -> rbtCap6444.isChecked = true
                else -> rgroupCap644.clearCheck()
            }

        }
        Mob.infoCap.find { it.indexCap == Mob.CAP6_P09 }?.capView = true
        onAction()
    }

    private fun setRBState(
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
        val check = checkNo42.all(allItems)

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

                if (check) null else if (rbtCap6431Si.isChecked) true else
                    if (rbtCap6431No.isChecked) false else null,
                if (check) null else if (rbtCap6432Si.isChecked) true else
                    if (rbtCap6432No.isChecked) false else null,
                if (check) null else when {
                    rbtCap6441.isChecked -> "1"
                    rbtCap6442.isChecked -> "2"
                    rbtCap6443.isChecked -> "3"
                    rbtCap6444.isChecked -> "4"
                    else -> null
                },
                if (check) null else if (spinCap6451.selectedItemPosition == 0) null else
                    spinCap6451.selectedItemPosition.toString(),
                if (check) null else if (spinCap6452.selectedItemPosition == 0) null else
                    spinCap6452.selectedItemPosition.toString(),
                if (check) null else if (spinCap6453.selectedItemPosition == 0) null else
                    spinCap6453.selectedItemPosition.toString(),
                if (check) null else if (spinCap6454.selectedItemPosition == 0) null else
                    spinCap6454.selectedItemPosition.toString(),
                if (check) null else if (spinCap6455.selectedItemPosition == 0) null else
                    spinCap6455.selectedItemPosition.toString(),
                if (check) null else if (spinCap6456.selectedItemPosition == 0) null else
                    spinCap6456.selectedItemPosition.toString(),
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
        return viewCap()
    }

    private fun viewCap(): List<String> {
        val allItems: (Boolean) -> Boolean = { it }
        val check = checkNo42.all(allItems)
        with(Mob) {
            val returnList: ArrayList<String> = ArrayList()
            if (cap6?.v42check21o1 == null)
                returnList.add(CreateIncon.inconsistencia(ctx, "52") ?: "")
            if (cap6?.v42check22o1 == null)
                returnList.add(CreateIncon.inconsistencia(ctx, "53") ?: "")
            if (cap6?.v42check21o2 == null)
                returnList.add(CreateIncon.inconsistencia(ctx, "54") ?: "")
            if (cap6?.v42check22o2 == null)
                returnList.add(CreateIncon.inconsistencia(ctx, "55") ?: "")
            if (cap6?.v42check21o3 == null)
                returnList.add(CreateIncon.inconsistencia(ctx, "56") ?: "")
            if (cap6?.v42check22o3 == null)
                returnList.add(CreateIncon.inconsistencia(ctx, "57") ?: "")

            if (!check && cap6?.v43check1 == null)
                returnList.add(CreateIncon.inconsistencia(ctx, "58") ?: "")
            if (!check && cap6?.v43check2 == null)
                returnList.add(CreateIncon.inconsistencia(ctx, "59") ?: "")
            if (!check && bindingcap6o2.rgroupCap644.checkedRadioButtonId == -1)
                returnList.add(CreateIncon.inconsistencia(ctx, "60") ?: "")

            if (!check && cap6?.v45txtGrado1.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "61") ?: "")
            if (!check && cap6?.v45txtGrado2.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "62") ?: "")
            if (!check && cap6?.v45txtGrado3.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "63") ?: "")
            if (!check && cap6?.v45txtGrado4.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "64") ?: "")
            if (!check && cap6?.v45txtGrado5.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "65") ?: "")
            if (!check && cap6?.v45txtGrado6.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "66") ?: "")

            infoCap.find { it.indexCap == CAP6_P09 }?.incons = returnList.isNotEmpty()
            //println("Cap6_part2: --$cap6")
            return returnList
        }
    }
}