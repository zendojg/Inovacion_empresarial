package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo061InovacionProductosBinding
import gob.pa.inovacion_empresarial.function.CreateIncon
import gob.pa.inovacion_empresarial.function.Functions.allFalse
import gob.pa.inovacion_empresarial.function.Functions.allTrue
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelCap6

class FragEncuestaCap06o1 : Fragment() {

    private lateinit var bindingcap6o1: EncuestaCapitulo061InovacionProductosBinding
    private lateinit var ctx: Context
    private var checkNo39: MutableList<Boolean> = MutableList(4) { false }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingcap6o1 = EncuestaCapitulo061InovacionProductosBinding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingcap6o1.root
    }

    override fun onResume() {
        super.onResume()
        Mob.indiceFormulario = Mob.CAP6P08
        if (Mob.seecap0601) fillOut()
        else onAction()
    }

    private fun onAction() {
        fun check39() {
            bindingcap6o1.layoutCap640.isVisible = !checkNo39.allTrue()
            bindingcap6o1.layoutCap641.isVisible = !checkNo39.allTrue()
        }
        with(bindingcap6o1) {

            checkNo39[Mob.CHECK1Y2021] = rbtCap6391No2021.isChecked
            checkNo39[Mob.CHECK1Y2022] = rbtCap6391No2022.isChecked
            checkNo39[Mob.CHECK2Y2021] = rbtCap6392No2021.isChecked
            checkNo39[Mob.CHECK2Y2022] = rbtCap6392No2022.isChecked
            check39()

            rgroupCap63912021.setOnCheckedChangeListener { _, id ->
                checkNo39[Mob.CHECK1Y2021] = rbtCap6391No2021.id == id
                check39()
            }
            rgroupCap63912022.setOnCheckedChangeListener { _, id ->
                checkNo39[Mob.CHECK1Y2022] = rbtCap6391No2022.id == id
                check39()
            }
            rgroupCap63922021.setOnCheckedChangeListener { _, id ->
                checkNo39[Mob.CHECK2Y2021] = rbtCap6392No2021.id == id
                check39()
            }
            rgroupCap63922022.setOnCheckedChangeListener { _, id ->
                checkNo39[Mob.CHECK2Y2022] = rbtCap6392No2022.id == id
                check39()
            }

        }
    }

    private fun fillOut() {
        val cap6 = Mob.formComp?.cap6
        with(bindingcap6o1) {
            setRadioButtonState(rbtCap6391Si2021, rbtCap6391No2021, rgroupCap63912021,
                cap6?.v39check21o1)
            setRadioButtonState(rbtCap6391Si2022, rbtCap6391No2022, rgroupCap63912022,
                cap6?.v39check22o1)

            setRadioButtonState(rbtCap6392Si2021, rbtCap6392No2021, rgroupCap63922021,
                cap6?.v39check21o2)
            setRadioButtonState(rbtCap6392Si2022, rbtCap6392No2022, rgroupCap63922022,
                cap6?.v39check22o2)

            setRadioButtonState(rbtCap6401Si, rbtCap6401No, rgroupCap6401,
                cap6?.v40check1)
            setRadioButtonState(rbtCap6402Si, rbtCap6402No, rgroupCap6402,
                cap6?.v40check2)

            when (cap6?.v41check) {
                "1" -> rbtCap6411.isChecked = true
                "2" -> rbtCap6412.isChecked = true
                "3" -> rbtCap6413.isChecked = true
                "4" -> rbtCap6414.isChecked = true
                else -> rgroupCap641.clearCheck()
            }
        }
        Mob.seecap0601 = false
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
        val check = checkNo39.all(allItems)
        with (bindingcap6o1) {
            Mob.cap6 = ModelCap6(
                Mob.cap6?.id,
                Mob.cap6?.ncontrol,
                if (rbtCap6391Si2021.isChecked) true else
                    if (rbtCap6391No2021.isChecked) false else null,
                if (rbtCap6391Si2022.isChecked) true else
                    if (rbtCap6391No2022.isChecked) false else null,
                if (rbtCap6392Si2021.isChecked) true else
                    if (rbtCap6392No2021.isChecked) false else null,
                if (rbtCap6392Si2022.isChecked) true else
                    if (rbtCap6392No2022.isChecked) false else null,
                 if (check) null else if (rbtCap6401Si.isChecked) true else
                    if (rbtCap6401No.isChecked) false else null,
                if (check) null else if (rbtCap6402Si.isChecked) true else
                    if (rbtCap6402No.isChecked) false else null,
                if (check) null else when {
                    rbtCap6411.isChecked -> "1"
                    rbtCap6412.isChecked -> "2"
                    rbtCap6413.isChecked -> "3"
                    rbtCap6414.isChecked -> "4"
                    else -> null
                },
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
        val check = checkNo39.all(allItems)
        with(Mob) {
            val returnList: ArrayList<String> = ArrayList()
            if (cap6?.v39check21o1 == null)
                returnList.add(CreateIncon.inconsistencia(ctx, "44") ?: "")
            if (cap6?.v39check22o1 == null)
                returnList.add(CreateIncon.inconsistencia(ctx, "45") ?: "")
            if (cap6?.v39check21o2 == null)
                returnList.add(CreateIncon.inconsistencia(ctx, "46") ?: "")
            if (cap6?.v39check22o2 == null)
                returnList.add(CreateIncon.inconsistencia(ctx, "47") ?: "")

            if (cap6?.v40check1 == null && !check)
                returnList.add(CreateIncon.inconsistencia(ctx, "48") ?: "")
            if (cap6?.v40check2 == null && !check)
                returnList.add(CreateIncon.inconsistencia(ctx, "49") ?: "")


            icap0601 = returnList.isNotEmpty()
            println("---------Is not empty: $icap0601--$cap6")
            return returnList
        }
    }
}