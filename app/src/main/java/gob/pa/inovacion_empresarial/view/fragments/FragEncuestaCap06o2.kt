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
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo062InovacionProcesoBinding
import gob.pa.inovacion_empresarial.function.CreateIncon
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelCap6

class FragEncuestaCap06o2 : Fragment() {

    private lateinit var bindingcap6o2: EncuestaCapitulo062InovacionProcesoBinding
    private lateinit var ctx: Context
    private var checkNo42: MutableList<Boolean> = mutableListOf(false,false,false,false,false,false)
    private var indice01 = 0
    private var indice02 = 0
    private var indice03 = 0
    private var indice04 = 0
    private var indice05 = 0
    private var indice06 = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingcap6o2 = EncuestaCapitulo062InovacionProcesoBinding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingcap6o2.root
    }

    override fun onResume() {
        super.onResume()

        Mob.indiceFormulario = Mob.CAP6P09
        if (Mob.seecap06o2) fillOut()
        else onAction()
    }

    private fun onAction() {
        with(bindingcap6o2) {
            lowCap6o2.setOnClickListener { saveCap() }

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

            val gr45Adp = ArrayAdapter(ctx, R.layout.style_box, Mob.arrGrade)
            gr45Adp.setDropDownViewResource(R.layout.style_list)

            spinCap6451.adapter = gr45Adp
            spinCap6452.adapter = gr45Adp
            spinCap6453.adapter = gr45Adp
            spinCap6454.adapter = gr45Adp
            spinCap6455.adapter = gr45Adp
            spinCap6456.adapter = gr45Adp
            sipnAction()
        }
    }
    private fun check42() {
        val allItems: (Boolean) -> Boolean = { it }
        with(bindingcap6o2) {
            layoutCap643.isVisible = !checkNo42.all(allItems)
            layoutCap644.isVisible = !checkNo42.all(allItems)
            layoutCap645.isVisible = !checkNo42.all(allItems)
        }
    }

    private fun sipnAction() {
        with(bindingcap6o2) {
            spinCap6451.setSelection(indice01)
            spinCap6452.setSelection(indice02)
            spinCap6453.setSelection(indice03)
            spinCap6454.setSelection(indice04)
            spinCap6455.setSelection(indice05)
            spinCap6456.setSelection(indice06)

            spinCap6451.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice01 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
            spinCap6452.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice02 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
            spinCap6453.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice03 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
            spinCap6454.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice04 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
            spinCap6455.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice05 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
            spinCap6456.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice06 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }
        }
    }


    private fun fillOut() {
        val cap6 = Mob.formComp?.cap6
        with(bindingcap6o2) {
            fillOut42(cap6)
            when (cap6?.v43check1) {
                true -> rbtCap6431Si.isChecked = true
                false -> rbtCap6431No.isChecked = true
                else -> rgroupCap6431.clearCheck()
            }
            when (cap6?.v43check2) {
                true -> rbtCap6432Si.isChecked = true
                false -> rbtCap6432No.isChecked = true
                else -> rgroupCap6432.clearCheck()
            }
            when (cap6?.v44check) {
                "1" -> rbtCap6441.isChecked = true
                "2" -> rbtCap6442.isChecked = true
                "3" -> rbtCap6443.isChecked = true
                "4" -> rbtCap6444.isChecked = true
                else -> rgroupCap644.clearCheck()

            }
            indice01 = try { cap6?.v45txtGrado1?.toInt() ?: 0 }
            catch (e: java.lang.NumberFormatException) { 0 }
            indice02 = try { cap6?.v45txtGrado2?.toInt() ?: 0 }
            catch (e: java.lang.NumberFormatException) { 0 }
            indice03 = try { cap6?.v45txtGrado3?.toInt() ?: 0 }
            catch (e: java.lang.NumberFormatException) { 0 }
            indice04 = try { cap6?.v45txtGrado4?.toInt() ?: 0 }
            catch (e: java.lang.NumberFormatException) { 0 }
            indice05 = try { cap6?.v45txtGrado5?.toInt() ?: 0 }
            catch (e: java.lang.NumberFormatException) { 0 }
            indice06 = try { cap6?.v45txtGrado6?.toInt() ?: 0 }
            catch (e: java.lang.NumberFormatException) { 0 }
        }
        Mob.seecap06o2 = false
        onAction()
    }

    private fun fillOut42(cap6: ModelCap6?) {
        with(bindingcap6o2) {
            when (cap6?.v42check21o1) {
                true -> rbtCap6421Si2021.isChecked = true
                false -> rbtCap6421No2021.isChecked = true
                else -> rgroupCap64212021.clearCheck()
            }
            when (cap6?.v42check22o1) {
                true -> rbtCap6421Si2022.isChecked = true
                false -> rbtCap6421No2022.isChecked = true
                else -> rgroupCap64212022.clearCheck()
            }

            when (cap6?.v42check21o2) {
                true -> rbtCap6422Si2021.isChecked = true
                false -> rbtCap6422No2021.isChecked = true
                else -> rgroupCap64222021.clearCheck()
            }
            when (cap6?.v42check22o2) {
                true -> rbtCap6422Si2022.isChecked = true
                false -> rbtCap6422No2022.isChecked = true
                else -> rgroupCap64222022.clearCheck()
            }

            when (cap6?.v42check21o3) {
                true -> rbtCap6423Si2021.isChecked = true
                false -> rbtCap6423No2021.isChecked = true
                else -> rgroupCap64232021.clearCheck()
            }
            when (cap6?.v42check22o3) {
                true -> rbtCap6423Si2022.isChecked = true
                false -> rbtCap6423No2022.isChecked = true
                else -> rgroupCap64232022.clearCheck()
            }
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

            icap06o2 = returnList.isNotEmpty()
            println("---------Is not empty: $icap06o2--$cap6")
            return returnList
        }
    }
}