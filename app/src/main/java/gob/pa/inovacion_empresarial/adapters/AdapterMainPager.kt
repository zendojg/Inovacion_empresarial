package gob.pa.inovacion_empresarial.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.fragment.app.FragmentManager

import androidx.lifecycle.Lifecycle


class AdapterMainPager(
    windows: Array<Fragment>,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
):
    FragmentStateAdapter( fragmentManager, lifecycle) {

    private val fragmentList = windows
//    private val fragmentList: ArrayList<Fragment> = ArrayList()
//
//    fun addFragment(fragment: Fragment) {
//        fragmentList.add(fragment)
//    }
//    fun addListFragment(list: Array<Fragment>) {
//        fragmentList.addAll(list)
//    }

    override fun getItemCount(): Int = fragmentList.size
    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}