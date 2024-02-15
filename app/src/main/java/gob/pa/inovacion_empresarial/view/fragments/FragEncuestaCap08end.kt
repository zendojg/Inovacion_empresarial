package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo08CoclusionBinding
import gob.pa.inovacion_empresarial.function.CreateIncon
import gob.pa.inovacion_empresarial.function.Functions.toEditable
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelCap8
import gob.pa.inovacion_empresarial.model.ModelSpinLister
import gob.pa.inovacion_empresarial.view.FormActivity

class FragEncuestaCap08end : Fragment() {

    private lateinit var bindingcap8o2: EncuestaCapitulo08CoclusionBinding
    private lateinit var ctx: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingcap8o2 = EncuestaCapitulo08CoclusionBinding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingcap8o2.root
    }

    override fun onResume() {
        super.onResume()
        Mob.indiceFormulario = Mob.CAP8_P16
        val infoCap = Mob.infoCap.find { it.indexCap == Mob.CAP8_P16 }
        if (infoCap?.capView == false) fillOut()
        else onAction()
    }

    private fun onAction() {
        with(bindingcap8o2) {
            scrollForm.isVisible = Mob.cap8?.v56check != false

            if (Mob.cap8?.v56check == true) tb58.isVisible = true
            else if (Mob.cap8?.v56check == false) tb58.isVisible = false


            val v58txtFields = listOf(
                Mob.cap8?.v58num1a, Mob.cap8?.v58num1b, Mob.cap8?.v58num1c,
                Mob.cap8?.v58num2a, Mob.cap8?.v58num2b, Mob.cap8?.v58num2c, Mob.cap8?.v58num2d,
                Mob.cap8?.v58num3a, Mob.cap8?.v58num3b,
                Mob.cap8?.v58num4a, Mob.cap8?.v58num4b, Mob.cap8?.v58num4c, Mob.cap8?.v58num4d
            )
            val spinFields = listOf(
                spinCap8581A, spinCap8581B, spinCap8581C,
                spinCap8582A, spinCap8582B, spinCap8582C, spinCap8582D,
                spinCap8583A, spinCap8583B,
                spinCap8584A, spinCap8584B, spinCap8584C, spinCap8584D
            )

            val spinList = v58txtFields.mapIndexed { index, v58txtField ->
                val value = v58txtField?.toIntOrNull() ?: 0
                ModelSpinLister(spinFields[index], value)
            }.toMutableList()

            val gradeImportance = ArrayAdapter(ctx, R.layout.style_box, Mob.arrGrade)
            gradeImportance.setDropDownViewResource(R.layout.style_list)

            for (index in 0 until tb58.childCount) {
                val view = tb58.getChildAt(index)
                if (view is Spinner) {
                    view.adapter = gradeImportance
                }
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
        val cap8 = Mob.formComp?.cap8
        bindingcap8o2.txtCap8584DOtra.text = cap8?.v58desc4d?.toEditable() ?: "".toEditable()
        Mob.infoCap.find { it.indexCap == Mob.CAP8_P16 }?.capView = true
        onAction()
    }

    fun saveCap(): List<String> {
        with(bindingcap8o2) {
            Mob.cap8 = ModelCap8(
                Mob.cap8?.id,
                Mob.cap8?.ncontrol,
                Mob.cap8?.v56check,
                Mob.cap8?.v57num1a,
                Mob.cap8?.v57monto1a,
                Mob.cap8?.v57num1b,
                Mob.cap8?.v57monto1b,
                Mob.cap8?.v57desc1c,
                Mob.cap8?.v57num1c,
                Mob.cap8?.v57monto1c,
                Mob.cap8?.v57num2a,
                Mob.cap8?.v57monto2a,
                Mob.cap8?.v57num2b,
                Mob.cap8?.v57monto2b,
                Mob.cap8?.v57desc2c,
                Mob.cap8?.v57num2c,
                Mob.cap8?.v57monto2c,//
                if (spinCap8581A.selectedItemPosition == 0 || Mob.cap8?.v56check == false) null else
                    spinCap8581A.selectedItemPosition.toString(),
                if (spinCap8581B.selectedItemPosition == 0 || Mob.cap8?.v56check == false) null else
                    spinCap8581B.selectedItemPosition.toString(),
                if (spinCap8581C.selectedItemPosition == 0 || Mob.cap8?.v56check == false) null else
                    spinCap8581C.selectedItemPosition.toString(),
                if (spinCap8582A.selectedItemPosition == 0 || Mob.cap8?.v56check == false) null else
                    spinCap8582A.selectedItemPosition.toString(),
                if (spinCap8582B.selectedItemPosition == 0 || Mob.cap8?.v56check == false) null else
                    spinCap8582B.selectedItemPosition.toString(),
                if (spinCap8582C.selectedItemPosition == 0 || Mob.cap8?.v56check == false) null else
                    spinCap8582C.selectedItemPosition.toString(),
                if (spinCap8582D.selectedItemPosition == 0 || Mob.cap8?.v56check == false) null else
                    spinCap8582D.selectedItemPosition.toString(),
                if (spinCap8583A.selectedItemPosition == 0 || Mob.cap8?.v56check == false) null else
                    spinCap8583A.selectedItemPosition.toString(),
                if (spinCap8583B.selectedItemPosition == 0 || Mob.cap8?.v56check == false) null else
                    spinCap8583B.selectedItemPosition.toString(),
                if (spinCap8584A.selectedItemPosition == 0 || Mob.cap8?.v56check == false) null else
                    spinCap8584A.selectedItemPosition.toString(),
                if (spinCap8584B.selectedItemPosition == 0 || Mob.cap8?.v56check == false) null else
                    spinCap8584B.selectedItemPosition.toString(),
                if (spinCap8584C.selectedItemPosition == 0 || Mob.cap8?.v56check == false) null else
                    spinCap8584C.selectedItemPosition.toString(),
                if (Mob.cap8?.v56check == false) null else txtCap8584DOtra.text.toString()
                    .ifEmpty { null },
                if (spinCap8584D.selectedItemPosition == 0 || Mob.cap8?.v56check == false) null else
                    spinCap8584D.selectedItemPosition.toString(),
            )
        }
        return viewCap8part2()
    }

    private fun viewCap8part2(): List<String> {
        with(Mob) {
            val returnList: ArrayList<String> = ArrayList()
            if (cap8?.v56check == true) {

                if (cap8?.v58num1a.isNullOrEmpty() || cap8?.v58num1a == "0")
                    returnList.add(CreateIncon.inconsistencia(ctx, "156") ?: "")
                if (cap8?.v58num1b.isNullOrEmpty() || cap8?.v58num1b == "0")
                    returnList.add(CreateIncon.inconsistencia(ctx, "157") ?: "")
                if (cap8?.v58num1c.isNullOrEmpty() || cap8?.v58num1c == "0")
                    returnList.add(CreateIncon.inconsistencia(ctx, "158") ?: "")

                if (cap8?.v58num2a.isNullOrEmpty() || cap8?.v58num2a == "0")
                    returnList.add(CreateIncon.inconsistencia(ctx, "159") ?: "")
                if (cap8?.v58num2b.isNullOrEmpty() || cap8?.v58num2b == "0")
                    returnList.add(CreateIncon.inconsistencia(ctx, "160") ?: "")
                if (cap8?.v58num2c.isNullOrEmpty() || cap8?.v58num2c == "0")
                    returnList.add(CreateIncon.inconsistencia(ctx, "161") ?: "")
                if (cap8?.v58num2d.isNullOrEmpty() || cap8?.v58num2d == "0")
                    returnList.add(CreateIncon.inconsistencia(ctx, "162") ?: "")

                if (cap8?.v58num3a.isNullOrEmpty() || cap8?.v58num3a == "0")
                    returnList.add(CreateIncon.inconsistencia(ctx, "163") ?: "")
                if (cap8?.v58num3b.isNullOrEmpty() || cap8?.v58num3b == "0")
                    returnList.add(CreateIncon.inconsistencia(ctx, "164") ?: "")

                if (cap8?.v58num4a.isNullOrEmpty() || cap8?.v58num4a == "0")
                    returnList.add(CreateIncon.inconsistencia(ctx, "165") ?: "")
                if (cap8?.v58num4b.isNullOrEmpty() || cap8?.v58num4b == "0")
                    returnList.add(CreateIncon.inconsistencia(ctx, "166") ?: "")
                if (cap8?.v58num4c.isNullOrEmpty() || cap8?.v58num4c == "0")
                    returnList.add(CreateIncon.inconsistencia(ctx, "167") ?: "")
                if (cap8?.v58num4d.isNullOrEmpty() || cap8?.v58num4d == "0")
                    returnList.add(CreateIncon.inconsistencia(ctx, "168") ?: "")
                else if (cap8?.v58desc4d.isNullOrBlank() && !cap8?.v58num4d.isNullOrEmpty())
                    returnList.add(CreateIncon.inconsistencia(ctx, "168") ?: "")

            } else if (cap8?.v56check == null) {
                returnList.add(CreateIncon.inconsistencia(ctx, "610") ?: "")
                infoCap.find { it.indexCap == CAP8_P16 }?.incons = returnList.isNotEmpty()
            }
            //println("Cap8-part2: --$cap8")
            return returnList
        }
    }

}