package gob.pa.inovacion_empresarial.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import gob.pa.inovacion_empresarial.databinding.StyleItemFormsBinding
import gob.pa.inovacion_empresarial.function.Functions
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelForm

class AdapterForms(
    var list: List<ModelForm>,
    private val onItemClick:(ModelForm) -> Unit):
    RecyclerView.Adapter<AdapterForms.FormViewHolder>() {

    override fun getItemCount() = list.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FormViewHolder {
        val binding = StyleItemFormsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FormViewHolder(binding)
    }
    override fun onBindViewHolder(holder: FormViewHolder, position: Int) {
        holder.render(list[position], onItemClick)
    }
    class FormViewHolder(val binding: StyleItemFormsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun render(form: ModelForm, onItemClick:(ModelForm) -> Unit) {
            with(binding) {
                txtncontrolRC.text = Functions.ceroLeft(form.ncontrol.toString(),4)
                txtrucRC.text = form.cap2?.v07ructxt ?: "0-0-0"
                lbnameLRC.text = form.cap2?.v05nameLtxt ?: "Desconocido"

                if (form.cond != null) txtconditionRC.text = try {
                    Mob.arrCondicion[form.cond.toInt() - 1]
                } catch (e: java.lang.NumberFormatException) { "Valor desconocido" }
                else txtconditionRC.text = "Sin condición asignada"

                txtrazonRC.text = form.cap2?.v06razontxt ?: "No registrada"

                lbinconsistenciasRC.apply {
                    when (form.tieneIncon) {
                        false -> isVisible = false
                        true -> {
                            text = "con Inconsistencias"
                            setTextColor(Color.parseColor("#873945")) // rojo
                            isVisible = true
                        }
                        else -> {
                            text = "Sin modificar"
                            setTextColor(Color.parseColor("#4d6b73")) // azul
                            isVisible = true
                        }
                    }
                }

                layoutItemForm.setOnClickListener {
                    //layoutItemForm.setBackgroundResource(R.drawable.background_shadow_holoblue2)
                    onItemClick(form)
                }
            }
        }

    }
    fun updateList(newList: List<ModelForm>) {
        val diffUtilForms = DiffUtilForms(list, newList)
        val calculated = DiffUtil.calculateDiff(diffUtilForms)
        list = newList
        calculated.dispatchUpdatesTo(this)
    }


}