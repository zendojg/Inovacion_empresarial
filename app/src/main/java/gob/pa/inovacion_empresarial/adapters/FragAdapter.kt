package gob.pa.inovacion_empresarial.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.util.ArrayList

class FragAdapter(fa: FragmentActivity, arr: Array<Fragment>, position: Int): FragmentStateAdapter(fa) {


    var pos: Int = 0
    var filterList = ArrayList<Fragment>()

    init {
        filterList.addAll(arr)
        pos = position
    }





    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }


    override fun getItemCount(): Int = filterList.size
    override fun createFragment(position: Int): Fragment {
        return filterList[position]
    }



}