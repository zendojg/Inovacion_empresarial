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
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo063InovacionOrganizacionalBinding
import gob.pa.inovacion_empresarial.function.CreateIncon
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelCap6

class FragEncuestaCap06o3 : Fragment() {

    private lateinit var bindingcap6o3: EncuestaCapitulo063InovacionOrganizacionalBinding
    private lateinit var ctx: Context

    var checkNo46: MutableList<Boolean> =
        mutableListOf(false, false, false, false, false, false)
    private var indice01 = 0
    private var indice02 = 0
    private var indice03 = 0
    private var indice04 = 0
    private var indice05 = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingcap6o3 = EncuestaCapitulo063InovacionOrganizacionalBinding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingcap6o3.root
    }

    override fun onResume() {
        super.onResume()

        Mob.indiceFormulario = Mob.CAP6P10
        if (Mob.seecap06o3) fillOut()
        else onAction()
    }

    private fun onAction() {
        val gr47Adp = ArrayAdapter(ctx, R.layout.style_box, Mob.arrGrade)
        gr47Adp.setDropDownViewResource(R.layout.style_list)

        with(bindingcap6o3) {
            lowCap6o3.setOnClickListener { saveCap() }

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
            spinCap6471.adapter = gr47Adp
            spinCap6472.adapter = gr47Adp
            spinCap6473.adapter = gr47Adp
            spinCap6474.adapter = gr47Adp
            spinCap6475.adapter = gr47Adp

            spinsAction()
        }
    }
    private fun check46() {
        val allItems: (Boolean) -> Boolean = { it }
        with(bindingcap6o3) {
            layoutCap647.isVisible = !checkNo46.all(allItems)
        }
    }

    private fun spinsAction() {
        with(bindingcap6o3) {
            spinCap6471.setSelection(indice01)
            spinCap6472.setSelection(indice02)
            spinCap6473.setSelection(indice03)
            spinCap6474.setSelection(indice04)
            spinCap6475.setSelection(indice05)

            spinCap6471.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice01 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
            spinCap6472.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice02 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
            spinCap6473.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice03 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
            spinCap6474.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice04 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
            spinCap6475.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice05 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
        }
    }

    private fun fillOut() {
        val cap6 = Mob.formComp?.cap6
        fillOut46(cap6)
        indice01 = try { cap6?.v47txtGrado1?.toInt() ?: 0 }
        catch (e: java.lang.NumberFormatException) { 0 }
        indice02 = try { cap6?.v47txtGrado2?.toInt() ?: 0 }
        catch (e: java.lang.NumberFormatException) { 0 }
        indice03 = try { cap6?.v47txtGrado3?.toInt() ?: 0 }
        catch (e: java.lang.NumberFormatException) { 0 }
        indice04 = try { cap6?.v47txtGrado4?.toInt() ?: 0 }
        catch (e: java.lang.NumberFormatException) { 0 }
        indice05 = try { cap6?.v47txtGrado5?.toInt() ?: 0 }
        catch (e: java.lang.NumberFormatException) { 0 }

        Mob.seecap06o3 = false
        onAction()
    }

    private fun fillOut46(cap6: ModelCap6?) {
        with(bindingcap6o3) {
            when (cap6?.v46check21o1) {
                true -> rbtCap6461Si2021.isChecked = true
                false -> rbtCap6461No2021.isChecked = true
                else -> rgroupCap64612021.clearCheck()
            }
            when (cap6?.v46check22o1) {
                true -> rbtCap6461Si2022.isChecked = true
                false -> rbtCap6461No2022.isChecked = true
                else -> rgroupCap64612022.clearCheck()
            }
            when (cap6?.v46check21o2) {
                true -> rbtCap6462Si2021.isChecked = true
                false -> rbtCap6462No2021.isChecked = true
                else -> rgroupCap64622021.clearCheck()
            }
            when (cap6?.v46check22o2) {
                true -> rbtCap6462Si2022.isChecked = true
                false -> rbtCap6462No2022.isChecked = true
                else -> rgroupCap64622022.clearCheck()
            }
            when (cap6?.v46check21o3) {
                true -> rbtCap6463Si2021.isChecked = true
                false -> rbtCap6463No2021.isChecked = true
                else -> rgroupCap64632021.clearCheck()
            }
            when (cap6?.v46check22o3) {
                true -> rbtCap6463Si2022.isChecked = true
                false -> rbtCap6463No2022.isChecked = true
                else -> rgroupCap64632022.clearCheck()
            }
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


            icap06o3 = returnList.isNotEmpty()
            println("---------Is not empty: $icap06o3--$cap6")
            return returnList
        }
    }

}