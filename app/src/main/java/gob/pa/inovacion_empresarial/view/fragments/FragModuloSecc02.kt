package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.databinding.ModuloSeccion02Binding
import gob.pa.inovacion_empresarial.function.CreateIncon
import gob.pa.inovacion_empresarial.function.Functions.allFalse
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelMod

class FragModuloSecc02 : Fragment() {

    private lateinit var bindingmod2: ModuloSeccion02Binding
    private lateinit var ctx: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingmod2 = ModuloSeccion02Binding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingmod2.root
    }

    override fun onResume() {
        super.onResume()
        Mob.indiceFormulario = Mob.SEC2P21
        if (Mob.seesecc2) fillOut()
        else onAction()
    }

    private fun onAction() {
        with(bindingmod2) {
            scrollForm.isVisible = Mob.capMod?.v1check != false
        }
    }

    private fun fillOut() {
        val mod2 = Mob.formComp?.capMod
        with(bindingmod2) {
            checkSecc231.isChecked = mod2?.v3check1 == true
            checkSecc232.isChecked = mod2?.v3check2 == true
            checkSecc233.isChecked = mod2?.v3check3 == true
            checkSecc234.isChecked = mod2?.v3check4 == true
        }
        Mob.seesecc2 = false
        onAction()
    }

    fun saveCap(): List<String> {
        with (bindingmod2) {
            Mob.capMod = ModelMod(
                Mob.capMod?.id,
                Mob.capMod?.v1check,
                Mob.capMod?.v2check1,
                Mob.capMod?.v2check1a,
                Mob.capMod?.v2check1b,
                Mob.capMod?.v2check1c,
                Mob.capMod?.v2check1d,
                Mob.capMod?.v2txtDesc1d,
                Mob.capMod?.v2check2,
                //Mob.capMod?.v2txtnull: String?, //---------
                if (Mob.capMod?.v1check != false && checkSecc231.isChecked) true else null,
                if (Mob.capMod?.v1check != false && checkSecc232.isChecked) true else null,
                if (Mob.capMod?.v1check != false && checkSecc233.isChecked) true else null,
                if (Mob.capMod?.v1check != false && checkSecc234.isChecked) true else null,
                Mob.capMod?.v4check,//----------
                Mob.capMod?.v4check1a,
                Mob.capMod?.v4check1aPorcent,
                Mob.capMod?.v4check1b,
                Mob.capMod?.v4check1bPorcent,
                Mob.capMod?.v4check1c,
                Mob.capMod?.v4check1cPorcent,
                Mob.capMod?.v4check1d,
                Mob.capMod?.v4check1dPorcent,
                Mob.capMod?.v4check1e,
                Mob.capMod?.v4check1ePorcent,
                Mob.capMod?.v4check1eOther,
                Mob.capMod?.v5txt,
                Mob.capMod?.v6porcent1,
                Mob.capMod?.v6porcent2,
                Mob.capMod?.v6porcent3,
                Mob.capMod?.v6porcent4,
                Mob.capMod?.v7check,
                Mob.capMod?.v8txt,
                Mob.capMod?.v9check,
                Mob.capMod?.v9txt,
                Mob.capMod?.v10porcent1,
                Mob.capMod?.v10porcent2,
                Mob.capMod?.v10porcent3,
                Mob.capMod?.v10porcent4,
                Mob.obsModulo,
                Mob.capMod?.numControl
            )
        }
        return viewCap()
    }

    private fun viewCap(): List<String> {
        with(bindingmod2) {
            val allCheckSecc02 = listOf(
                checkSecc231.isChecked,
                checkSecc232.isChecked,
                checkSecc233.isChecked,
                checkSecc234.isChecked,
            )
            val returnList: ArrayList<String> = ArrayList()
            if (Mob.capMod?.v1check == true && allCheckSecc02.allFalse())
                returnList.add(CreateIncon.inconsistencia(ctx, "293") ?: "")
            else if (Mob.capMod?.v1check == null)
                returnList.add(CreateIncon.inconsistencia(ctx, "289") ?: "")

            Mob.isecc2 = returnList.isNotEmpty()
            println("Secc2: ${Mob.isecc2}--${Mob.capMod}")
            return returnList
        }
    }
}