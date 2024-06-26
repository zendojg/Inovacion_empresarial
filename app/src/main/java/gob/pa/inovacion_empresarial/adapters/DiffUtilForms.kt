package gob.pa.inovacion_empresarial.adapters

import androidx.recyclerview.widget.DiffUtil
import gob.pa.inovacion_empresarial.model.ModelForm

class DiffUtilForms(private val oldList: List<Any>,
                    private val newList: List<Any>): DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return  oldList[oldItemPosition] == newList[newItemPosition]
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return  oldList[oldItemPosition] == newList[newItemPosition]
    }


}