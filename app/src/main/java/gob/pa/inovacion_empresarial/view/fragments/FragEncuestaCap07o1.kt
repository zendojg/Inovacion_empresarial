package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo07Part1Binding
import gob.pa.inovacion_empresarial.function.CreateIncon
import gob.pa.inovacion_empresarial.function.Functions.hideKeyboard
import gob.pa.inovacion_empresarial.function.Functions.toEditable
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelCap7

class FragEncuestaCap07o1 : Fragment() {

    private lateinit var bindingcap7o1: EncuestaCapitulo07Part1Binding
    private lateinit var ctx: Context

    private var indice01 = 0
    private var indice02 = 0
    private var indice03 = 0
    private var indice04 = 0
    private var indice05 = 0
    private var indice06 = 0
    private var indice07 = 0
    private var indice08 = 0
    private var indice09 = 0
    private var indice10 = 0
    private var indice11 = 0
    private var indice12 = 0
    private var indice13 = 0
    private var indice14 = 0
    private var indice15 = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingcap7o1 = EncuestaCapitulo07Part1Binding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingcap7o1.root
    }

    override fun onResume() {
        super.onResume()

        Mob.indiceFormulario = Mob.CAP7P12
        if (Mob.seecap07o1) fillOut()
        else onAction()
    }

    private fun onAction() {
        with(bindingcap7o1) {
            lowCap7o1.setOnClickListener { saveCap() }

            layoutCap751.isVisible = rbtCap750Si.isChecked
            txtCap75112021ly.isVisible = rbtCap7511Si2021.isChecked
            txtCap75112022ly.isVisible = rbtCap7511Si2022.isChecked
            txtCap75122021ly.isVisible = rbtCap7512Si2021.isChecked
            txtCap75122022ly.isVisible = rbtCap7512Si2022.isChecked

            rgroupCap750.setOnCheckedChangeListener { _, id ->
                hideKeyboard()
                when (id) {
                    rbtCap750Si.id -> layoutCap751.visibility = View.VISIBLE
                    rbtCap750No.id -> layoutCap751.visibility = View.GONE
                }
            }

            rgroupCap75112021.setOnCheckedChangeListener { _, id ->
                hideKeyboard()
                when (id) {
                    rbtCap7511Si2021.id -> txtCap75112021ly.visibility = View.VISIBLE
                    rbtCap7511No2021.id -> txtCap75112021ly.visibility = View.GONE
                }
            }
            rgroupCap75112022.setOnCheckedChangeListener { _, id ->
                hideKeyboard()
                when (id) {
                    rbtCap7511Si2022.id -> txtCap75112022ly.visibility = View.VISIBLE
                    rbtCap7511No2022.id -> txtCap75112022ly.visibility = View.GONE
                }
            }
            rgroupCap75122021.setOnCheckedChangeListener { _, id ->
                hideKeyboard()
                when (id) {
                    rbtCap7512Si2021.id -> txtCap75122021ly.visibility = View.VISIBLE
                    rbtCap7512No2021.id -> txtCap75122021ly.visibility = View.GONE
                }
            }
            rgroupCap75122022.setOnCheckedChangeListener { _, id ->
                hideKeyboard()
                when (id) {
                    rbtCap7512Si2022.id -> txtCap75122022ly.visibility = View.VISIBLE
                    rbtCap7512No2022.id -> txtCap75122022ly.visibility = View.GONE
                }
            }

            val imp52Adp = ArrayAdapter(ctx, R.layout.style_box, Mob.arrImp)
            imp52Adp.setDropDownViewResource(R.layout.style_list)

            spinCap7521.adapter = imp52Adp
            spinCap7522.adapter = imp52Adp
            spinCap7523.adapter = imp52Adp
            spinCap7524.adapter = imp52Adp
            spinCap7525.adapter = imp52Adp
            spinCap7526.adapter = imp52Adp
            spinCap7527.adapter = imp52Adp
            spinCap7528.adapter = imp52Adp
            spinCap7529.adapter = imp52Adp
            spinCap75210.adapter = imp52Adp
            spinCap75211.adapter = imp52Adp
            spinCap75212.adapter = imp52Adp
            spinCap75213.adapter = imp52Adp
            spinCap75214.adapter = imp52Adp
            spinCap75215.adapter = imp52Adp
            spinsAction()
        }
    }

    private fun spinsAction() {
        with(bindingcap7o1) {

            spinCap7521.setSelection(indice01)
            spinCap7522.setSelection(indice02)
            spinCap7523.setSelection(indice03)
            spinCap7524.setSelection(indice04)
            spinCap7525.setSelection(indice05)

            spinCap7526.setSelection(indice06)
            spinCap7527.setSelection(indice07)
            spinCap7528.setSelection(indice08)
            spinCap7529.setSelection(indice09)
            spinCap75210.setSelection(indice10)

            spinCap75211.setSelection(indice11)
            spinCap75212.setSelection(indice12)
            spinCap75213.setSelection(indice13)
            spinCap75214.setSelection(indice14)
            spinCap75215.setSelection(indice15)

            spinCap7521.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice01 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
            spinCap7522.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice02 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
            spinCap7523.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice03 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
            spinCap7524.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice04 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
            spinCap7525.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice05 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
            spinCap7526.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice06 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
            spinCap7527.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice07 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
            spinCap7528.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice08 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
            spinCap7529.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice09 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
            spinCap75210.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice10 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
            spinCap75211.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice11 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
            spinCap75212.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice12 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
            spinCap75213.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice13 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
            spinCap75214.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice14 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
            spinCap75215.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice15 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
        }
    }


    private fun fillOut() {
        val cap7 = Mob.formComp?.cap7

        when (cap7?.v50check) {
            true -> bindingcap7o1.rbtCap750Si.isChecked = true
            false -> bindingcap7o1.rbtCap750No.isChecked = true
            else -> bindingcap7o1.rgroupCap750.clearCheck()
        }

        fillOut51(cap7)
        indice01 = try {  cap7?.v52txt01?.toInt() ?: 0 }
        catch (e: java.lang.NumberFormatException) { 0 }
        indice02 = try {  cap7?.v52txt02?.toInt() ?: 0 }
        catch (e: java.lang.NumberFormatException) { 0 }
        indice03 = try {  cap7?.v52txt03?.toInt() ?: 0 }
        catch (e: java.lang.NumberFormatException) { 0 }
        indice04 = try {  cap7?.v52txt04?.toInt() ?: 0 }
        catch (e: java.lang.NumberFormatException) { 0 }
        indice05 = try {  cap7?.v52txt05?.toInt() ?: 0 }
        catch (e: java.lang.NumberFormatException) { 0 }
        indice06 = try {  cap7?.v52txt06?.toInt() ?: 0 }
        catch (e: java.lang.NumberFormatException) { 0 }
        indice07 = try {  cap7?.v52txt07?.toInt() ?: 0 }
        catch (e: java.lang.NumberFormatException) { 0 }
        indice08 = try {  cap7?.v52txt08?.toInt() ?: 0 }
        catch (e: java.lang.NumberFormatException) { 0 }
        indice09 = try {  cap7?.v52txt09?.toInt() ?: 0 }
        catch (e: java.lang.NumberFormatException) { 0 }
        indice10 = try {  cap7?.v52txt10?.toInt() ?: 0 }
        catch (e: java.lang.NumberFormatException) { 0 }
        indice11 = try {  cap7?.v52txt11?.toInt() ?: 0 }
        catch (e: java.lang.NumberFormatException) { 0 }
        indice12 = try {  cap7?.v52txt12?.toInt() ?: 0 }
        catch (e: java.lang.NumberFormatException) { 0 }
        indice13 = try {  cap7?.v52txt13?.toInt() ?: 0 }
        catch (e: java.lang.NumberFormatException) { 0 }
        indice14 = try {  cap7?.v52txt14?.toInt() ?: 0 }
        catch (e: java.lang.NumberFormatException) { 0 }
        indice15 = try {  cap7?.v52txt15?.toInt() ?: 0 }
        catch (e: java.lang.NumberFormatException) { 0 }
        Mob.seecap07o1 = false
        onAction()
    }

    private fun fillOut51(cap7: ModelCap7?) {

        val blank = "".toEditable()
        with(bindingcap7o1) {
            when (cap7?.v51check21o1) {
                true -> rbtCap7511Si2021.isChecked = true
                false -> rbtCap7511No2021.isChecked = true
                else -> rgroupCap75112021.clearCheck()
            }
            when (cap7?.v51check22o1) {
                true -> rbtCap7511Si2022.isChecked = true
                false -> rbtCap7511No2022.isChecked = true
                else -> rgroupCap75112022.clearCheck()
            }

            when (cap7?.v51check21o2) {
                true -> rbtCap7512Si2021.isChecked = true
                false -> rbtCap7512No2021.isChecked = true
                else -> rgroupCap75122021.clearCheck()
            }
            when (cap7?.v51check22o2) {
                true -> rbtCap7512Si2022.isChecked = true
                false -> rbtCap7512No2022.isChecked = true
                else -> rgroupCap75122022.clearCheck()
            }
            txtCap75112021.text = cap7?.v51num21o1?.toEditable() ?: blank
            txtCap75112022.text = cap7?.v51num22o1?.toEditable() ?: blank
            txtCap75122021.text = cap7?.v51num21o2?.toEditable() ?: blank
            txtCap75122022.text = cap7?.v51num22o2?.toEditable() ?: blank
        }
    }


    fun saveCap(): List<String> {
        with (bindingcap7o1) {

            Mob.cap7 = ModelCap7(
                Mob.cap7?.id,
                Mob.cap7?.ncontrol,
                if (rbtCap750Si.isChecked) true else if (rbtCap750No.isChecked) false else null,    //--50
                if (rbtCap7511Si2021.isChecked && rbtCap750Si.isChecked) true else
                    if (rbtCap7511No2021.isChecked && rbtCap750Si.isChecked) false else null,       //--51-1-2021
                if (rbtCap7512Si2021.isChecked && rbtCap750Si.isChecked) true else
                    if (rbtCap7512No2021.isChecked && rbtCap750Si.isChecked) false else null,       //--51-2-2021
                if (rbtCap7511Si2021.isChecked && rbtCap750Si.isChecked)
                    txtCap75112021.text.toString() else null,           //--51-1-2021 %
                if (rbtCap7512Si2021.isChecked && rbtCap750Si.isChecked)
                    txtCap75122021.text.toString() else null,           //--51-2-2021 %
                if (rbtCap7511Si2022.isChecked && rbtCap750Si.isChecked) true else
                    if (rbtCap7511No2022.isChecked && rbtCap750Si.isChecked) false else null,       //--51-1-2022
                if (rbtCap7512Si2022.isChecked && rbtCap750Si.isChecked) true else
                    if (rbtCap7512No2022.isChecked && rbtCap750Si.isChecked) false else null,       //--51-2-2022
                if (rbtCap7511Si2022.isChecked && rbtCap750Si.isChecked)
                    txtCap75112021.text.toString() else null,           //--51-1-2022 %
                if (rbtCap7512Si2022.isChecked && rbtCap750Si.isChecked)
                    txtCap75122021.text.toString() else null,           //--51-2-2022 %

                if (spinCap7521.selectedItemPosition == 0) null else
                    spinCap7521.selectedItemPosition.toString(),
                if (spinCap7522.selectedItemPosition == 0) null else
                    spinCap7522.selectedItemPosition.toString(),
                if (spinCap7523.selectedItemPosition == 0) null else
                    spinCap7523.selectedItemPosition.toString(),
                if (spinCap7524.selectedItemPosition == 0) null else
                    spinCap7524.selectedItemPosition.toString(),
                if (spinCap7525.selectedItemPosition == 0) null else
                    spinCap7525.selectedItemPosition.toString(),
                if (spinCap7526.selectedItemPosition == 0) null else
                    spinCap7526.selectedItemPosition.toString(),
                if (spinCap7527.selectedItemPosition == 0) null else
                    spinCap7527.selectedItemPosition.toString(),
                if (spinCap7528.selectedItemPosition == 0) null else
                    spinCap7528.selectedItemPosition.toString(),
                if (spinCap7529.selectedItemPosition == 0) null else
                    spinCap7529.selectedItemPosition.toString(),
                if (spinCap75210.selectedItemPosition == 0) null else
                    spinCap75210.selectedItemPosition.toString(),
                if (spinCap75211.selectedItemPosition == 0) null else
                    spinCap75211.selectedItemPosition.toString(),
                if (spinCap75212.selectedItemPosition == 0) null else
                    spinCap75212.selectedItemPosition.toString(),
                if (spinCap75213.selectedItemPosition == 0) null else
                    spinCap75213.selectedItemPosition.toString(),
                if (spinCap75214.selectedItemPosition == 0) null else
                    spinCap75214.selectedItemPosition.toString(),
                if (txtCap75215Otro.text.isNullOrEmpty()) null else txtCap75215Otro.text.toString(),
                if (spinCap75215.selectedItemPosition == 0) null else
                    spinCap75215.selectedItemPosition.toString(),
                Mob.cap7?.v53num21a,//----
                Mob.cap7?.v53num21b,
                Mob.cap7?.v53num21c,
                Mob.cap7?.v53num21d,
                Mob.cap7?.v53num21e,
                Mob.cap7?.v53num21f,
                Mob.cap7?.v53num21g,
                Mob.cap7?.v53num1T21,
                Mob.cap7?.v53num22a,
                Mob.cap7?.v53num22b,
                Mob.cap7?.v53num22c,
                Mob.cap7?.v53num22d,
                Mob.cap7?.v53num22e,
                Mob.cap7?.v53num22f,
                Mob.cap7?.v53num22g,
                Mob.cap7?.v53num1T22,
                Mob.cap7?.v53txtgdesc,
                Mob.cap7?.v54txt01,
                Mob.cap7?.v54txt02,
                Mob.cap7?.v54txt03,
                Mob.cap7?.v54txt04,
                Mob.cap7?.v54txt05,
                Mob.cap7?.v54txt06,
                Mob.cap7?.v54txt07,
                Mob.cap7?.v54txt08,
                Mob.cap7?.v54txt09,
                Mob.cap7?.v54txt10,
                Mob.cap7?.v54txt11,
                Mob.cap7?.v54txt12,
                Mob.cap7?.v54txt13,
                Mob.cap7?.v54txt14,
                Mob.cap7?.v54txt15,
                Mob.cap7?.v54txt15desc,
                Mob.cap7?.v55txt1a,
                Mob.cap7?.v55txt1b,
                Mob.cap7?.v55txt1c,
                Mob.cap7?.v55txt2a,
                Mob.cap7?.v55txt2b,
                Mob.cap7?.v55txt2c,
                Mob.cap7?.v55txt2d,
                Mob.cap7?.v55txt3a,
                Mob.cap7?.v55txt3b,
                Mob.cap7?.v55txt4a,
                Mob.cap7?.v55txt4b,
                Mob.cap7?.v55txt4c,

            )
        }
        return viewCap()
    }

    private fun viewCap(): List<String> {
        with(Mob) {
            val returnList: ArrayList<String> = ArrayList()
            if (cap7?.v50check == null)
                returnList.add(CreateIncon.inconsistencia(ctx, "90") ?: "")
            if (cap7?.v50check == true && cap7?.v51check21o1 == null)
                returnList.add(CreateIncon.inconsistencia(ctx, "91") ?: "")
            if (cap7?.v50check == true && cap7?.v51check22o1 == null)
                returnList.add(CreateIncon.inconsistencia(ctx, "92") ?: "")
            if (cap7?.v50check == true && cap7?.v51check21o2 == null)
                returnList.add(CreateIncon.inconsistencia(ctx, "93") ?: "")
            if (cap7?.v50check == true && cap7?.v51check22o2 == null)
                returnList.add(CreateIncon.inconsistencia(ctx, "94") ?: "")

            if (cap7?.v50check == true &&
                cap7?.v51check21o1 == true &&
                cap7?.v51num21o1.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "95") ?: "")
            if (cap7?.v50check == true &&
                cap7?.v51check22o1 == true &&
                cap7?.v51num22o1.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "96") ?: "")
            if (cap7?.v50check == true &&
                cap7?.v51check21o2 == true &&
                cap7?.v51num21o2.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "97") ?: "")
            if (cap7?.v50check == true &&
                cap7?.v51check22o2 == true &&
                cap7?.v51num22o2.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx, "98") ?: "")

            icap07o1 = returnList.isNotEmpty()
            println("---------Is not empty: $icap07o1--$cap7")
            return returnList
        }
    }


}