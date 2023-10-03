package gob.pa.inovacion_empresarial.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter


class AdapterPagerForm(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle):
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val fragmentList: ArrayList<Fragment> = ArrayList()
    //fun addFragment(fragment: Fragment) { fragmentList.add(fragment) }
    fun addListFragment(list: Array<Fragment>) { fragmentList.addAll(list) }
    override fun getItemCount(): Int = fragmentList.size
    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }



}