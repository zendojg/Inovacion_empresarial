package gob.pa.inovacion_empresarial.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import gob.pa.inovacion_empresarial.databinding.StyleItemFormsBinding
import gob.pa.inovacion_empresarial.function.Functions
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelForm

class AdapterForms(var list: List<ModelForm>, private val onItemRemove:(ModelForm) -> Unit):
    RecyclerView.Adapter<AdapterForms.FormViewHolder>() {

    override fun getItemCount() = list.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FormViewHolder {
        val binding = StyleItemFormsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FormViewHolder(binding)
    }
    override fun onBindViewHolder(holder: FormViewHolder, position: Int) {
        holder.render(list[position], onItemRemove)
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
                txtinconsistenciasRC.text = when (form.tieneIncon) {
                    true  -> {
                        lbinconsistenciasRC.setTextColor(Color.parseColor("#C3584C"))
                        txtinconsistenciasRC.setTextColor(Color.parseColor("#C3584C"))
                        "Sí" }
                    false -> {
                        lbinconsistenciasRC.setTextColor(Color.parseColor("#2B577B"))
                        txtinconsistenciasRC.setTextColor(Color.parseColor("#2B577B"))
                        "No" }
                    else  -> {
                        lbinconsistenciasRC.setTextColor(Color.parseColor("#2B577B"))
                        txtinconsistenciasRC.setTextColor(Color.parseColor("#2B577B"))
                        "No registradas" }
                }

                layoutItemForm.setOnClickListener { onItemClick(form) }
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