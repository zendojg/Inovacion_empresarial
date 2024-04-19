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

class AdapterSuper(
    var list: List<Map<*, *>>,
    private val onItemClick:(Map<*, *>) -> Unit):
    RecyclerView.Adapter<AdapterSuper.FormViewHolder>() {

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
        fun render(item: Map<*, *>, onItemClick:(Map<*, *>) -> Unit) {
            with(binding) {
                val ncontol = (item["numControl"] as? Double ?: 0).toInt()
                val cond = item["condicion"] as? String

                val comer = item["nombComerc"] as? String ?: "Desconocido"
                val razon = item["razSocial"] as? String ?: "No registrada"
                val ruc = item["ruc"] as? String ?: "0-0-0"

                txtncontrolRC.text = Functions.ceroLeft(ncontol.toString(),4)
                txtrucRC.text = ruc
                lbnameLRC.text = comer

                if (cond != null){
                    val indexCond =
                        if (cond.toInt() > Mob.arrCondicion.size || cond.toInt() < 0) 0
                        else cond.toInt()
                    txtconditionRC.text = Mob.arrCondicion[indexCond]
                }
                else txtconditionRC.text = "Sin condición asignada"

                txtrazonRC.text = razon
                lbinconsistenciasRC.apply {
                    text = item["encuestador"] as? String ?: "No registrada"
                    setTextColor(Color.parseColor("#4d6b73")) // azul
                    isVisible = true
                }

                layoutItemForm.setOnClickListener {
                    //layoutItemForm.setBackgroundResource(R.drawable.background_shadow_holoblue2)
                    onItemClick(item)
                }
            }
        }

    }
    fun updateList(newList: List<Map<*, *>>) {
        val diffUtilForms = DiffUtilSuper(list, newList)
        val calculated = DiffUtil.calculateDiff(diffUtilForms)
        list = newList
        calculated.dispatchUpdatesTo(this)
    }


}