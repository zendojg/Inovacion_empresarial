package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.adapters.AdapterForms
import gob.pa.inovacion_empresarial.databinding.FragFormsMainBinding
import gob.pa.inovacion_empresarial.model.DVModel
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelForm
import gob.pa.inovacion_empresarial.service.room.RoomView
import kotlinx.coroutines.launch
import java.lang.reflect.Type
import java.util.*
import kotlin.collections.ArrayList

class MainFragmentForms : Fragment() {
    private lateinit var bindingForm: FragFormsMainBinding
    private lateinit var ctx: Context
    private lateinit var adpForms: AdapterForms
    private var list: List<ModelForm> = emptyList()
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

        with (bindingForm){
            adpForms = AdapterForms(list) {
                //list = list.minus(it)
                adpForms.updateList(list)
            }
            recyclerdata.layoutManager = LinearLayoutManager(ctx)
            recyclerdata.adapter = adpForms
        }

        onAction()
    }

    private fun onAction() {
        with (bindingForm){
            btbackForm.setOnClickListener {
                val pager = activity?.findViewById<ViewPager2>(R.id.viewpagerMain)
                pager?.setCurrentItem(Mob.INIT01, true)
            }

            val arrAdptSpin = ArrayAdapter(ctx, R.layout.style_box,
                resources.getStringArray(R.array.arr_typeForms))
            arrAdptSpin.setDropDownViewResource(R.layout.style_list)
            spinFormsType.adapter = arrAdptSpin

            spinFormsType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    var inco = 0
                    barForms.visibility = View.VISIBLE
                    val user: String = Mob.authData?.user ?: ""
                    val listUpdate: ArrayList<ModelForm> = ArrayList()
                    lifecycleScope.launch {
                        if (pos == 1 || pos == 2) {
                            val retroData = dvmForm.formsGetUser(user)
                            if (pos == 1) {
                                list = retroData?.body ?: ArrayList()
                                for (i in list) if (i.tieneIncon == true) inco += 1
                                txttotalInconForms.text = inco.toString()
                                txttotalForms.text = list.size.toString()
                                adpForms.updateList(list)
                            } else {
                                for (i in retroData?.body ?: ArrayList())
                                    if (i.tieneIncon != null) {
                                        listUpdate.add(i)
                                        if (i.tieneIncon == true) inco += 1
                                    }
                                list = listUpdate
                                txttotalInconForms.text = inco.toString()
                                txttotalForms.text = list.size.toString()
                                adpForms.updateList(list)
                            }

                        } else {
                            val roomData = RoomView(dvmForm, ctx).getFormsUser(user)
                            for (i in roomData) {
                                val type: Type = object : TypeToken<ModelForm?>() {}.type
                                val listTest = Gson().fromJson<ModelForm>(i.saveForm, type)
                                listUpdate.add(listTest)
                            }
                            for (i in listUpdate) if (i.tieneIncon == true) inco += 1
                            list = listUpdate
                            txttotalInconForms.text = inco.toString()
                            txttotalForms.text = list.size.toString()
                            adpForms.updateList(list)
                        }
                        barForms.visibility = View.INVISIBLE
                    }
                }
                override fun onNothingSelected(adp: AdapterView<*>?) {}
            }


            searchForms.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (!query.isNullOrEmpty()) {
                        var inco = 0
                        val listUpdate: ArrayList<ModelForm> = ArrayList()
                        val search = query.lowercase(Locale.getDefault())
                        list.forEach {
                            when {
                                it.ncontrol?.lowercase(Locale.getDefault())
                                    ?.contains(search) == true -> listUpdate.add(it)
                                it.cap2?.v05nameLtxt?.lowercase(Locale.getDefault())
                                    ?.contains(search) == true -> listUpdate.add(it)
                                it.cap2?.v06razontxt?.lowercase(Locale.getDefault())
                                    ?.contains(search) == true -> listUpdate.add(it)
                                it.cap2?.v07ructxt?.lowercase(Locale.getDefault())
                                    ?.contains(search) == true -> listUpdate.add(it)
                            }
                        }
                        for (i in listUpdate)
                            if (i.tieneIncon == true) inco += 1
                        txttotalInconForms.text = inco.toString()
                        txttotalForms.text = listUpdate.size.toString()
                        adpForms.updateList(listUpdate)
                    }
                    return true
                }
                override fun onQueryTextChange(newText: String?) = false
            })
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