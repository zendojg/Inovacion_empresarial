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
        Mob.indiceEnc = Mob.CAPXP19
        if (seecap) fillOut()
        else onAction()
    }

    private fun onAction() {
        with(bindingmod3) {

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

    fun saveCap() {}
}