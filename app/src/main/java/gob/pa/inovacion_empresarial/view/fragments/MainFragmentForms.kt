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
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
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
import gob.pa.inovacion_empresarial.databinding.StyleMsgFormBinding
import gob.pa.inovacion_empresarial.databinding.StyleMsgLocalinfoBinding
import gob.pa.inovacion_empresarial.databinding.StyleMsgPopupBinding
import gob.pa.inovacion_empresarial.function.CreateForm
import gob.pa.inovacion_empresarial.function.Functions
import gob.pa.inovacion_empresarial.function.Functions.toEditable
import gob.pa.inovacion_empresarial.model.DVModel
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelForm
import gob.pa.inovacion_empresarial.service.room.RoomView
import gob.pa.inovacion_empresarial.view.FormActivity
import kotlinx.coroutines.launch
import java.lang.reflect.Type
import java.util.Locale

class MainFragmentForms : Fragment() {
    private lateinit var bindingForm: FragFormsMainBinding
    private lateinit var ctx: Context
    private lateinit var adpForms: AdapterForms
    private lateinit var room: RoomView
    private var listofAllForms: List<ModelForm> = emptyList()
    private var listLocation = mutableListOf<Triple<String, String, String>>()
    private val dvmForm: DVModel by viewModels()
    private var aDialog: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingForm = FragFormsMainBinding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingForm.root
    }

    override fun onPause() {
        super.onPause()
        bindingForm.spinFormsType.onItemSelectedListener = null
        if (aDialog?.isShowing == true)  aDialog?.dismiss()
    }
    override fun onResume() {
        super.onResume()
        room = RoomView(dvmForm, ctx)
        with (bindingForm){
            adpForms = AdapterForms(listofAllForms) { popupBottom(it) }
            recyclerdata.layoutManager = LinearLayoutManager(ctx)
            recyclerdata.adapter = adpForms
        }
        onAction()
    }

    private fun onAction() {
        with (bindingForm){
            btexpandSearchForms.setOnClickListener {
                if (layoutSearchForm.isVisible) {
                    reset()
                    adpForms.updateList(listofAllForms)
                    layoutSearchForm.isVisible = false
                    btexpandSearchForms.setImageDrawable(
                        ContextCompat.getDrawable(ctx, R.drawable.img_expand_more))
                } else {
                    layoutSearchForm.isVisible = true
                    btexpandSearchForms.setImageDrawable(
                        ContextCompat.getDrawable(ctx, R.drawable.img_expand_less))
                }
            }
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
                    spinnerSelection(pos)
                }
                override fun onNothingSelected(adp: AdapterView<*>?) { /* Nothing */ }
            }
            searchForms.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?) = search(query)
                override fun onQueryTextChange(newText: String?) = false
            })
            searchForms.setOnCloseListener {
                searchForms.setQuery("", false)
                adpForms.updateList(listofAllForms)
                false
            }
            autoDist.onItemClickListener = AdapterView.OnItemClickListener { adapter, _, pos, _ ->  //----- Optimizar este bloque
                searchForms.setQuery("", false)
                lifecycleScope.launch {
                    val prov = if (listLocation.isNotEmpty()) listLocation.last().first else ""
                    val getDistID =
                        room.getDistID(prov, adapter.getItemAtPosition(pos).toString())
                    val listUpdate = listofAllForms.filter {
                        it.cap1?.v01provtxt?.lowercase(Locale.getDefault())
                            ?.contains(prov) == true &&
                                it.cap1.v02disttxt?.lowercase(Locale.getDefault())
                                    ?.contains(getDistID) == true
                    }
                    activity?.runOnUiThread {
                        val hintDist = "Distrito: $getDistID"
                        autoCorre.let {
                            it.hint = getString(R.string.corre)
                            it.setText(getString(R.string.corre), false)
                        }
                        autoDistly.hint = hintDist
                        adpForms.updateList(listUpdate)
                    }
                }
            }




        }
    }

    private fun reset() {
        with(bindingForm) {
            autoDistly.hint = getString(R.string.dist)
            autoCorre.hint = getString(R.string.corre)
            autoDist.setText(getString(R.string.dist), false)
            autoCorre.setText(getString(R.string.corre), false)
            autoCorre.setAdapter(ArrayAdapter(ctx, R.layout.style_list, emptyArray<String>()))
        }
    }

    private fun spinnerSelection(position: Int) {
        with (bindingForm) {
            barForms.visibility = View.VISIBLE
            reset()
            var conteoIncon = 0
            val user: String = Mob.authData?.user ?: ""
            val listUpdate: ArrayList<ModelForm> = ArrayList()
            lifecycleScope.launch {
                val localitation = mutableListOf<Triple<String, String, String>>()
                if (position > 0) {
                    val retroData = dvmForm.formsGetUser(user)
                    if (position == 1) {
                        listofAllForms = retroData?.body ?: ArrayList()
                        for (i in listofAllForms) {
                            localitation.add(Triple(
                                    i.cap1?.v01provtxt ?: "0",
                                    i.cap1?.v02disttxt ?: "0",
                                    i.cap1?.v03corretxt ?: "0")
                            )
                            if (i.tieneIncon == true) conteoIncon += 1
                        }
                        activity?.runOnUiThread {
                            txttotalInconForms.text = conteoIncon.toString()
                            txttotalForms.text = listofAllForms.size.toString()
                            adpForms.updateList(listofAllForms)
                        }
                        listLocation = localitation
                    } else { //-- position == 2
                        for (i in retroData?.body ?: ArrayList())
                            if (i.tieneIncon != null) {
                                listUpdate.add(i)
                                localitation.add(
                                    Triple(
                                        i.cap1?.v01provtxt ?: "0",
                                        i.cap1?.v02disttxt ?: "0",
                                        i.cap1?.v03corretxt ?: "0"
                                    )
                                )
                                if (i.tieneIncon == true) conteoIncon += 1
                            }
                        listofAllForms = listUpdate
                        activity?.runOnUiThread {
                            txttotalInconForms.text = conteoIncon.toString()
                            txttotalForms.text = listofAllForms.size.toString()
                            adpForms.updateList(listofAllForms)
                        }
                        listLocation = localitation
                    }

                } else {
                    val listofRoom = RoomView(dvmForm, ctx).getFormsUser(user)
                    val gson = Gson()
                    val type: Type = object : TypeToken<ModelForm?>() {}.type
                    for (i in listofRoom) {
                        val listTest = gson.fromJson<ModelForm>(i.saveForm, type)
                        listUpdate.add(listTest)
                    }
                    for (i in listUpdate) {
                        localitation.add(
                            Triple(
                                i.cap1?.v01provtxt ?: "0",
                                i.cap1?.v02disttxt ?: "0",
                                i.cap1?.v03corretxt ?: "0"
                            )
                        )
                        if (i.tieneIncon == true) conteoIncon += 1
                    }
                    listofAllForms = listUpdate
                    txttotalInconForms.text = conteoIncon.toString()
                    txttotalForms.text = listofAllForms.size.toString()
                    adpForms.updateList(listofAllForms)

                    listLocation = localitation
                }
                barForms.visibility = View.INVISIBLE

                if (listLocation.isNotEmpty()) {
                    val arrayDist = room.getDist(listLocation.last().first)
                    autoDist.setAdapter(ArrayAdapter(ctx, R.layout.style_list, arrayDist))
                }
            }
        }
    }
    private fun search(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            var inco = 0
            val listUpdate: ArrayList<ModelForm> = ArrayList()
            val search = query.lowercase(Locale.getDefault())
            val listFilter = adpForms.list.ifEmpty { listofAllForms }
            listUpdate.addAll(
                listFilter.filter {
                    it.ncontrol?.lowercase(Locale.getDefault())?.contains(search) == true ||
                            it.cap2?.v05nameLtxt?.lowercase(Locale.getDefault())
                                ?.contains(search) == true ||
                            it.cap2?.v06razontxt?.lowercase(Locale.getDefault())
                                ?.contains(search) == true ||
                            it.cap2?.v07ructxt?.lowercase(Locale.getDefault())
                                ?.contains(search) == true
                }
            )
            for (i in listUpdate)
                if (i.tieneIncon == true) inco += 1
            bindingForm.txttotalInconForms.text = inco.toString()
            bindingForm.txttotalForms.text = listUpdate.size.toString()
            adpForms.updateList(listUpdate)
        }
        return true
    }

    private fun popupBottom(item: ModelForm) {
        val msgOpcions = AlertDialog.Builder(ctx)
        val bindmsg: StyleMsgPopupBinding =
            DataBindingUtil.inflate(LayoutInflater.from(ctx),
                R.layout.style_msg_popup, null, false)
        val selection = bindingForm.spinFormsType.selectedItemPosition
        val ncontrol = "N° de control: ${
            Functions.ceroLeft(item.ncontrol ?: "0",Mob.FOR_5_DIGITS)}"
        with(bindmsg) {
            msgOpcions.setView(bindmsg.root)

            msgtitle.text = ncontrol
            bt1.text = getString(R.string.btcarga)    //-- Cargar formulario
            bt2.text = getString(R.string.btmoreinfo) //-- Ver formulario completo
            bt3.text = getString(R.string.viewIncon)  //-- Ver Inconsistencias
            bt4.text = getString(R.string.btdelete)   //-- Borrar formulario

            bt1.icon = ContextCompat.getDrawable(ctx, R.drawable.img_download)
            bt2.icon = ContextCompat.getDrawable(ctx, R.drawable.img_formulario)
            bt3.icon = ContextCompat.getDrawable(ctx, R.drawable.img_warning1)
            bt4.icon = ContextCompat.getDrawable(ctx, R.drawable.img_delete)

            if (selection == 1 || selection == 2) bt4.isEnabled = false
            if (item.tieneIncon != true) bt3.isEnabled = false
            aDialog = msgOpcions.create()
            aDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            aDialog?.window?.attributes?.windowAnimations =
                R.style.Animation_Design_BottomSheetDialog
            aDialog?.window?.setGravity(Gravity.BOTTOM)
            //aDialog?.setCancelable(false)
            aDialog?.show()

            bt1.setOnClickListener {
                aDialog?.dismiss()
                chargeForm(item)
            }

            bt2.setOnClickListener {
                aDialog?.dismiss()
                Handler(Looper.getMainLooper()).postDelayed({
                    viewForm(item)
                }, (Mob.TIME100MS))
            }

            bt3.setOnClickListener {
                aDialog?.dismiss()
                iconForm(item)
            }

            bt4.setOnClickListener {
                aDialog?.dismiss()
                Handler(Looper.getMainLooper()).postDelayed({
                    if (selection == 1 || selection == 2) msgBallon("Imposible borrar")
                    else deleteForm(item)
                }, (Mob.TIME100MS))
            }
        }
    }
    private fun iconForm(item: ModelForm) {
        Toast.makeText(ctx, "Cargando inconsistencias...", Toast.LENGTH_SHORT).show()
    }

    private fun chargeForm(item: ModelForm) {
        fun charge(form: ModelForm) {
            CreateForm.createLoad(form)
            Mob.indiceFormulario = Mob.CAP1_P01
            Handler(Looper.getMainLooper()).postDelayed({
                aDialog?.dismiss()
                activity?.finish()
                val intent = Intent(ctx, FormActivity::class.java)
                intent.addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP or
                            Intent.FLAG_ACTIVITY_CLEAR_TASK or
                            Intent.FLAG_ACTIVITY_NEW_TASK
                )
                startActivity(intent)
            }, (Mob.TIME1S))
        }
        val selection = bindingForm.spinFormsType.selectedItemPosition
        val win = AlertDialog.Builder(ctx)
        aDialog = win.create()
        aDialog?.setCancelable(false)
        aDialog?.show()
        bindingForm.barForms.isVisible = true
        lifecycleScope.launch {
            if (selection == 1 || selection == 2) {
                val callForm = dvmForm.formGet(item.ncontrol ?: "0")
                if (callForm?.code == 200 && callForm.body != null) {  //--------------------------- Mejorar control de errores con el server
                    charge(callForm.body)
                }
            } else charge(item)
        }
    }

    private fun deleteForm(item: ModelForm) {
        val msgLogout = AlertDialog.Builder(ctx)
        val bindSend: StyleMsgFormBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(ctx),
                R.layout.style_msg_form, null, false
            )
        with(bindSend) {
            lbtittlestyleF.text = getString(R.string.deleteTittle)
            txtmsgStyle.text = getString(R.string.deleteSpec)
            txt1styleF.visibility = View.GONE
            txt2styleFly.visibility = View.GONE
            txt0styleFly.visibility = View.VISIBLE
            txtmsgStyle.visibility = View.VISIBLE

            msgLogout.setView(view)
            msgLogout.setView(bindSend.root)
            aDialog = msgLogout.create()
            aDialog?.setCancelable(false)
            aDialog?.show()

            btEnd.text = getString(R.string.delete)
            btCancel.icon = ContextCompat.getDrawable(ctx, R.drawable.img_backs)
            btEnd.icon = ContextCompat.getDrawable(ctx, R.drawable.img_delete)
            btEnd.backgroundTintList =
                ContextCompat.getColorStateList(ctx, R.color.dark_red)

            btCancel.setOnClickListener { aDialog?.dismiss() }
            btEnd.setOnClickListener {
                txt0styleF.imeOptions = EditorInfo.IME_ACTION_DONE
                Handler(Looper.getMainLooper()).postDelayed({
                if (txt0styleF.text.isNullOrEmpty()) {
                    txt0styleFly.error = "Ingrese la clave de acceso"
                } else if (txt0styleF.text.toString() == Mob.pass) {
                    aDialog?.dismiss()
                    val screen = AlertDialog.Builder(ctx)
                    aDialog = screen.create()
                    aDialog?.setCancelable(false)
                    aDialog?.show()

                    //------------ ADD DELETE IF NOT SEND FORM
                    aDialog?.dismiss()
                    listofAllForms = listofAllForms.minus(item)
                    adpForms.updateList(listofAllForms)
                    msgBallon("Formulario eliminado")
                } else { txt0styleFly.error = "Clave incorrecta" }
                }, (Mob.TIME500MS))
            }
        }
    }

    private fun msgBallon(msg: String) {
        Handler(Looper.getMainLooper()).postDelayed({
            val alert = Functions.msgBallom(msg, Mob.WIDTH160DP, ctx, R.color.dark_red)
            alert.showAlignBottom(bindingForm.txtwarningForm)
            alert.dismissWithDelay(Mob.TIMELONG2SEG)
        }, (Mob.TIME500MS))
    }

    private fun viewForm(item: ModelForm) {
        val blank = "".toEditable()
        val msgForm = AlertDialog.Builder(ctx)
        val bindmsg: StyleMsgLocalinfoBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(ctx),
                R.layout.style_msg_localinfo, null, false)
        msgForm.setView(bindmsg.root)
        lifecycleScope.launch {
            val ncontrol = Functions.ceroLeft(item.ncontrol ?: "0",Mob.FOR_5_DIGITS)
            val provincia = room.getProvName(item.cap1?.v01provtxt ?: "0")
            val distrito = room.getDistName(item.cap1?.v01provtxt ?: "0",
                item.cap1?.v02disttxt ?: "0")
            val corregimiento = room.getCorreName(item.cap1?.v01provtxt ?: "0",
                item.cap1?.v02disttxt ?: "0", item.cap1?.v03corretxt ?: "0")
            activity?.runOnUiThread {
                with(bindmsg) {
                    if (item.tieneIncon == true) lbtittlestyleF.setTextColor(Color.RED)
                    txtProvLocal.text = provincia.toEditable()
                    txtDistLocal.text = distrito.toEditable()
                    txtCorreLocal.text = corregimiento.toEditable()
                    txtNcontrolLocal.text = ncontrol.toEditable()
                    txtNameLocal.text = item.cap2?.v05nameLtxt?.toEditable() ?: blank
                    txtRazonLocal.text = item.cap2?.v06razontxt?.toEditable() ?: blank
                    txtRUCLocal.text = item.cap2?.v07ructxt?.toEditable() ?: blank
                    txtDirLocal.text = item.cap2?.v08dirtxt?.toEditable() ?: blank

                    aDialog = msgForm.create()
                    aDialog?.window?.attributes?.windowAnimations =
                        R.style.Animation_Design_BottomSheetDialog
                    aDialog?.setCancelable(false)
                    aDialog?.show()

                    btBackLocal.setOnClickListener { aDialog?.dismiss() }
                    btInconLocal.setOnClickListener {
                        aDialog?.dismiss()
                        chargeForm(item)
                    }
                }
            }
        }
    }



}