package gob.pa.inovacion_empresarial.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gob.pa.inovacion_empresarial.databinding.StyleItemFormsBinding
import gob.pa.inovacion_empresarial.model.ModelForm

class AdapterForms(val list: List<ModelForm>, private val onItemRemove:(ModelForm) -> Unit):
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
                txtncontrolRC.text = form.ncontrol.toString()
                txtrucRC.text = form.cap2?.v07ructxt ?: "0-0-0"
                lbnameLRC.text = form.cap2?.v05nameLtxt ?: "Desconocido"
                txtconditionRC.text = form.cond ?: "Sin condición asignada"
                txtdatesaveRC.text = form.dateMod ?: "Sin fecha agregada"

                layoutItemForm.setOnClickListener { onItemClick(form) }
            }
        }

    }

    fun updateList() {}


}