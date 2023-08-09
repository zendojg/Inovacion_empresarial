package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.adapters.AdapterForms
import gob.pa.inovacion_empresarial.databinding.FragFormsMainBinding
import gob.pa.inovacion_empresarial.model.DVModel
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelForm
import kotlinx.coroutines.launch

class MainFragmentForms : Fragment() {
    private lateinit var bindingForm: FragFormsMainBinding
    private lateinit var ctx: Context
    private lateinit var list: List<ModelForm>
    private val dvmForm: DVModel by viewModels()
    var aDialog: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingForm = FragFormsMainBinding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingForm.root
    }

    override fun onResume() {
        super.onResume()


        onAction()
    }

    private fun onAction() {
        with (bindingForm){
            btbackForm.setOnClickListener {
                val pager = activity?.findViewById<ViewPager2>(R.id.viewpagerMain)
                pager?.setCurrentItem(Mob.INIT01, true)
            }

            spinFormsType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {

                }

                override fun onNothingSelected(adp: AdapterView<*>?) {

                }
            }


            lifecycleScope.launch {
                //list = RoomView(dvmForm, ctx).getFormsUser(Mob.authData?.user!!)
                val response = dvmForm.formsGetUser(Mob.authData?.user!!)
                    list = response?.body!!

                    adapterData()

            }
        }
    }

    fun adapterData() {
        with (bindingForm){
            val adptRUC = AdapterForms(list) {
                Toast.makeText(ctx, "${it.ncontrol}", Toast.LENGTH_SHORT).show()
            }
            println("-----------list")
            recyclerdata.layoutManager = LinearLayoutManager(ctx)
            recyclerdata.adapter = adptRUC
        }
    }
//
//    private fun menuClick(position: Int) {
//        val x = bindingForm.recyclerdata
//        val popupMenu = PopupMenu(ctx, )
//        popupMenu.inflate(R.menu.menu)
//        popupMenu.menu
//        popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener{
//            override fun onMenuItemClick(item: MenuItem?): Boolean {
//
//                when(item?.itemId){
//                    R.id.delete -> {
//                        return true
//                    }
//                    R.id.header2 -> {
//                        Toast.makeText(ctx , "opción 2 clicked" ,
//                            Toast.LENGTH_SHORT).show()
//                        return true
//                    }
//                    R.id.header3 -> {
//                        Toast.makeText(ctx , "opción 3 clicked" ,
//                            Toast.LENGTH_SHORT).show()
//                        return true
//                    }
//                }
//                return false
//            }
//        })
//        popupMenu.show()
//    }


}