package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
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
import gob.pa.inovacion_empresarial.databinding.StyleMsgPopupBinding
import gob.pa.inovacion_empresarial.function.CreateForm
import gob.pa.inovacion_empresarial.function.Functions
import gob.pa.inovacion_empresarial.model.DVModel
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelForm
import gob.pa.inovacion_empresarial.service.room.RoomView
import gob.pa.inovacion_empresarial.view.FormActivity
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

    override fun onPause() {
        super.onPause()
        bindingForm.searchForms
        bindingForm.spinFormsType.onItemSelectedListener = null
        if (aDialog?.isShowing == true)  aDialog?.dismiss()
    }
    override fun onResume() {
        super.onResume()

        with (bindingForm){
            adpForms = AdapterForms(list) {
                //list = list.minus(it)
                //adpForms.updateList(list)
                windBottom(it)
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

            val arrAdptSpin = ArrayAdapter(ctx, R.layout.style_box2,
                resources.getStringArray(R.array.arr_typeForms))
            arrAdptSpin.setDropDownViewResource(R.layout.style_list)
            spinFormsType.adapter = arrAdptSpin

            spinFormsType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    spinnerSelection(pos)
                }
                override fun onNothingSelected(adp: AdapterView<*>?) {
                    Toast.makeText(ctx, "test", Toast.LENGTH_SHORT).show()
                }
            }

            searchForms.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean { return search(query) }
                override fun onQueryTextChange(newText: String?) = false
            })
        }
    }

    private fun spinnerSelection(position: Int) {
        with (bindingForm) {
            var inco = 0
            barForms.visibility = View.VISIBLE
            val user: String = Mob.authData?.user ?: ""
            val listUpdate: ArrayList<ModelForm> = ArrayList()
            lifecycleScope.launch {
                if (position == 1 || position == 2) {
                    val retroData = dvmForm.formsGetUser(user)
                    if (position == 1) {
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
    }
    private fun search(query: String?): Boolean {
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
            bindingForm.txttotalInconForms.text = inco.toString()
            bindingForm.txttotalForms.text = listUpdate.size.toString()
            adpForms.updateList(listUpdate)
        }
        return true
    }

    private fun windBottom(item: ModelForm) {
        val msgLogout = AlertDialog.Builder(ctx)
        val bindmsg: StyleMsgPopupBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(ctx),
                R.layout.style_msg_popup, null, false)
        bindingForm.spinFormsType.selectedItemPosition
        with(bindmsg) {
            msgLogout.setView(bindmsg.root)
            val ncontrol = "N° de control: ${Functions.ceroLeft(item.ncontrol ?: "0",4)}"

            msgtitle.text = ncontrol
            bt1.text = getString(R.string.btcarga)
            bt2.text = getString(R.string.btmoreinfo)
            bt3.text = getString(R.string.btdelete)
            bt4.text = "xxxxxxxxxxxxxxxxxxxxxxxxxx"

            bt1.icon= ContextCompat.getDrawable(ctx, R.drawable.img_download)
            bt2.icon= ContextCompat.getDrawable(ctx, R.drawable.img_formulario)
            bt3.icon= ContextCompat.getDrawable(ctx, R.drawable.img_delete)
            bt4.icon= ContextCompat.getDrawable(ctx, R.drawable.img_plus)


            aDialog = msgLogout.create()
            aDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            aDialog?.window?.attributes?.windowAnimations =
                R.style.Animation_Design_BottomSheetDialog
            aDialog?.window?.setGravity(Gravity.BOTTOM)
            //aDialog?.setCancelable(false)
            aDialog?.show()

            bt1.setOnClickListener {
                aDialog?.dismiss()
                CreateForm().createLoad(item)
                Mob.indiceFormulario = 1
                Handler(Looper.getMainLooper()).postDelayed({
                    activity?.finish()
                    startActivity(Intent(ctx, FormActivity::class.java))
                }, (Mob.TIME100MS))
            }
        }

    }



}