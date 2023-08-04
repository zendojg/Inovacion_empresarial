package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.databinding.ModuloSeccion03Binding
import gob.pa.inovacion_empresarial.function.Functions.hideKeyboard
import gob.pa.inovacion_empresarial.function.Functions.toEditable
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelMod

class FragModuloSecc03 : Fragment() {

    private lateinit var bindingmod3: ModuloSeccion03Binding
    private lateinit var ctx: Context
    private var seecap = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingmod3 = ModuloSeccion03Binding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingmod3.root
    }

    override fun onResume() {
        super.onResume()
        Mob.indiceFormulario = Mob.SEC3P22
        if (seecap) fillOut()
        else onAction()
    }

    private fun onAction() {
        with(bindingmod3) {
            scrollForm.isVisible = Mob.seccON != false
            layoutSecc341.isVisible = rbtSecc034ASi.isChecked

            rgroupSecc034.setOnCheckedChangeListener { _, id ->
                hideKeyboard()
                when (id) {
                    rbtSecc034Si.id -> {
                        layoutSecc341.isVisible = true
                        layoutSecc35.isVisible = true
                    }
                    rbtSecc034No.id -> {
                        layoutSecc35.isVisible = false
                        layoutSecc341.isVisible = false
                    }
                }
            }

            if (rbtSecc034ASi.isChecked) txtSecc034Aly.visibility = View.VISIBLE
            else txtSecc034Aly.visibility = View.INVISIBLE

            if (rbtSecc034BSi.isChecked) txtSecc034Bly.visibility = View.VISIBLE
            else txtSecc034Bly.visibility = View.INVISIBLE

            if (rbtSecc034CSi.isChecked) txtSecc034Cly.visibility = View.VISIBLE
            else txtSecc034Cly.visibility = View.INVISIBLE

            if (rbtSecc034DSi.isChecked) txtSecc034Dly.visibility = View.VISIBLE
            else txtSecc034Dly.visibility = View.INVISIBLE

            if (rbtSecc034ESi.isChecked) txtSecc034Ely.visibility = View.VISIBLE
            else txtSecc034Ely.visibility = View.INVISIBLE



            rgroupSecc034A.setOnCheckedChangeListener { _, id ->
                hideKeyboard()
                when (id) {
                    rbtSecc034ASi.id -> txtSecc034Aly.visibility = View.VISIBLE
                    rbtSecc034ANo.id -> txtSecc034Aly.visibility = View.INVISIBLE
                }
            }
            rgroupSecc034B.setOnCheckedChangeListener { _, id ->
                hideKeyboard()
                when (id) {
                    rbtSecc034BSi.id -> txtSecc034Bly.visibility = View.VISIBLE
                    rbtSecc034BNo.id -> txtSecc034Bly.visibility = View.INVISIBLE
                }
            }
            rgroupSecc034C.setOnCheckedChangeListener { _, id ->
                hideKeyboard()
                when (id) {
                    rbtSecc034CSi.id -> txtSecc034Cly.visibility = View.VISIBLE
                    rbtSecc034CNo.id -> txtSecc034Cly.visibility = View.INVISIBLE
                }
            }
            rgroupSecc034D.setOnCheckedChangeListener { _, id ->
                hideKeyboard()
                when (id) {
                    rbtSecc034DSi.id -> txtSecc034Dly.visibility = View.VISIBLE
                    rbtSecc034DNo.id -> txtSecc034Dly.visibility = View.INVISIBLE
                }
            }
            rgroupSecc034E.setOnCheckedChangeListener { _, id ->
                hideKeyboard()
                when (id) {
                    rbtSecc034ESi.id -> txtSecc034Ely.visibility = View.VISIBLE
                    rbtSecc034ENo.id -> txtSecc034Ely.visibility = View.INVISIBLE
                }
            }


            if (rbtSecc034ANo.isChecked) layoutSecc35.isVisible = false

            lowMod3.setOnClickListener { saveCap() }
        }
    }

    private fun fillOut() {
        val mod3 = Mob.formComp?.capMod
        val blank = "".toEditable()
        with(bindingmod3) {

            when (mod3?.v4check) {
                true -> rbtSecc034Si.isChecked = true
                false -> rbtSecc034No.isChecked = true
                else -> rgroupSecc034.clearCheck()
            }
            when (mod3?.v4check1a) {
                true -> rbtSecc034ASi.isChecked = true
                false -> rbtSecc034ANo.isChecked = true
                else -> rgroupSecc034A.clearCheck()
            }
            when (mod3?.v4check1b) {
                true -> rbtSecc034BSi.isChecked = true
                false -> rbtSecc034BNo.isChecked = true
                else -> rgroupSecc034B.clearCheck()
            }
            when (mod3?.v4check1c) {
                true -> rbtSecc034CSi.isChecked = true
                false -> rbtSecc034CNo.isChecked = true
                else -> rgroupSecc034C.clearCheck()
            }
            when (mod3?.v4check1d) {
                true -> rbtSecc034DSi.isChecked = true
                false -> rbtSecc034DNo.isChecked = true
                else -> rgroupSecc034D.clearCheck()
            }
            when (mod3?.v4check1e) {
                true -> rbtSecc034ESi.isChecked = true
                false -> rbtSecc034ENo.isChecked = true
                else -> rgroupSecc034E.clearCheck()
            }
            txtSecc034A.text = mod3?.v4check1aPorcent?.toEditable() ?: blank
            txtSecc034B.text = mod3?.v4check1bPorcent?.toEditable() ?: blank
            txtSecc034C.text = mod3?.v4check1cPorcent?.toEditable() ?: blank
            txtSecc034D.text = mod3?.v4check1dPorcent?.toEditable() ?: blank
            txtSecc034E.text = mod3?.v4check1ePorcent?.toEditable() ?: blank

            txtSecc034EOtro.text = mod3?.v4check1eOther?.toEditable() ?: blank

            txtSecc035.text = mod3?.v5txt?.toEditable() ?: blank
        }
        seecap = false
        onAction()
    }

    fun saveCap() {
        with (bindingmod3) {
            val p4check = (Mob.seccON != false && rbtSecc034Si.isChecked)

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
                //Mob.capMod?.v2txtnull: String?,
                Mob.capMod?.v3check1,
                Mob.capMod?.v3check2,
                Mob.capMod?.v3check3,
                Mob.capMod?.v3check4,//---------
                if (p4check) true else if (Mob.seccON != false && rbtSecc034No.isChecked)
                    false else null,
                if (p4check && rbtSecc034ASi.isChecked) true else
                    if (p4check && rbtSecc034ANo.isChecked) false else null,
                if (p4check && rbtSecc034ASi.isChecked) txtSecc034A.text.toString() else null,
                if (p4check && rbtSecc034BSi.isChecked) true else
                    if (p4check && rbtSecc034BNo.isChecked) false else null,
                if (p4check && rbtSecc034BSi.isChecked) txtSecc034B.text.toString() else null,
                if (p4check && rbtSecc034CSi.isChecked) true else
                    if (p4check && rbtSecc034CNo.isChecked) false else null,
                if (p4check && rbtSecc034CSi.isChecked) txtSecc034C.text.toString() else null,
                if (p4check && rbtSecc034DSi.isChecked) true else
                    if (p4check && rbtSecc034DNo.isChecked) false else null,
                if (p4check && rbtSecc034DSi.isChecked) txtSecc034D.text.toString() else null,
                if (p4check && rbtSecc034ESi.isChecked) true else
                    if (p4check && rbtSecc034ENo.isChecked) false else null,
                if (p4check && rbtSecc034ESi.isChecked) txtSecc034E.text.toString() else null,
                if (p4check && rbtSecc034ESi.isChecked) txtSecc034EOtro.text.toString() else null,
                if (p4check) txtSecc035.text.toString().ifEmpty { null } else null,
                Mob.capMod?.v6porcent1,//-----------
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
        println("----------${Mob.capMod}")
    }
}