package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo10Binding
import gob.pa.inovacion_empresarial.function.CreateIncon
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelCap10
import gob.pa.inovacion_empresarial.view.FormActivity

class FragEncuestaCap10 : Fragment() {

    private lateinit var bindingcap10: EncuestaCapitulo10Binding
    private lateinit var ctx: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingcap10 = EncuestaCapitulo10Binding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingcap10.root
    }

    override fun onResume() {
        super.onResume()
        Mob.indiceFormulario = Mob.CAPX_P19
        val infoCap = Mob.infoCap.find { it.indexCap == Mob.CAPX_P19 }
        if (infoCap?.capView == false) fillOut()
//        else onAction()
    }
//    private fun onAction() {
//    }

    private fun fillOut() {
        val cap10 = Mob.formComp?.capx
        with(bindingcap10) {
            bindingcap10.scrollForm.smoothScrollTo(0,0)
            val radioButtonsMap = mapOf(
                rgroupCap10661 to cap10?.v66check1,
                rgroupCap10662 to cap10?.v66check2,
                rgroupCap10663 to cap10?.v66check3,
                rgroupCap10664 to cap10?.v66check4
            )
            for ((radioGroup, isChecked) in radioButtonsMap) {
                when (isChecked) {
                    true -> radioGroup.check(radioGroup.getChildAt(1).id)
                    false -> radioGroup.check(radioGroup.getChildAt(2).id)
                    else -> radioGroup.clearCheck()
                }
            }
        }
        Mob.infoCap.find { it.indexCap == Mob.CAPX_P19 }?.capView = true
//        onAction()
    }

    fun saveCap(): List<String> {
        with(bindingcap10) {
            Mob.capx = ModelCap10(
                Mob.capx?.id,
                Mob.capx?.ncontrol,
                if (rbtCap10661Si.isChecked) true else if (rbtCap10661No.isChecked) false else null,
                if (rbtCap10662Si.isChecked) true else if (rbtCap10662No.isChecked) false else null,
                if (rbtCap10663Si.isChecked) true else if (rbtCap10663No.isChecked) false else null,
                if (rbtCap10664Si.isChecked) true else if (rbtCap10664No.isChecked) false else null,
            )
        }
        return viewCap()
    }

    private fun viewCap(): List<String> {
        with(bindingcap10) {
            val returnList: ArrayList<String> = ArrayList()
            if (rgroupCap10661.checkedRadioButtonId == -1)
                returnList.add(CreateIncon.inconsistencia(ctx, "191") ?: "")
            if (rgroupCap10662.checkedRadioButtonId == -1)
                returnList.add(CreateIncon.inconsistencia(ctx, "192") ?: "")
            if (rgroupCap10663.checkedRadioButtonId == -1)
                returnList.add(CreateIncon.inconsistencia(ctx, "193") ?: "")
            if (rgroupCap10664.checkedRadioButtonId == -1)
                returnList.add(CreateIncon.inconsistencia(ctx, "194") ?: "")

            Mob.infoCap.find { it.indexCap == Mob.CAPX_P19 }?.incons = returnList.isNotEmpty()
            //println("Cap10: --${Mob.capx}")
            return returnList
        }
    }
}
