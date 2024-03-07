package gob.pa.inovacion_empresarial.view.fragments

import android.app.ActivityOptions
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
import android.widget.ImageView
import android.widget.SearchView
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
import com.google.gson.JsonParseException
import com.google.gson.reflect.TypeToken
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.adapters.AdapterForms
import gob.pa.inovacion_empresarial.databinding.FragFormsMainBinding
import gob.pa.inovacion_empresarial.databinding.StyleMsgAlertBinding
import gob.pa.inovacion_empresarial.databinding.StyleMsgFormBinding
import gob.pa.inovacion_empresarial.databinding.StyleMsgLocalinfoBinding
import gob.pa.inovacion_empresarial.databinding.StyleMsgPopupBinding
import gob.pa.inovacion_empresarial.function.CreateForm
import gob.pa.inovacion_empresarial.function.Functions
import gob.pa.inovacion_empresarial.function.Functions.toEditable
import gob.pa.inovacion_empresarial.model.DVModel
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelForm
import gob.pa.inovacion_empresarial.model.ModelResponse
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
        bindingForm.searchForms.setQuery("",false)
        bindingForm.searchForms.clearFocus()
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
        bindingForm.apply {
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
                override fun onQueryTextChange(newText: String?) : Boolean {
                    if (newText.isNullOrEmpty()) {
                        try {
                            val closeButton: ImageView =
                                searchForms.findViewById(androidx.appcompat.R.id.search_close_btn)
                            closeButton.performClick()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                    return true
                }
            })
            searchForms.setOnCloseListener {
                searchForms.setQuery("", false)
                adpForms.updateList(listofAllForms)
                false
            }

//            btexpandSearchForms.setOnClickListener {  //
//                if (layoutSearchForm.isVisible) {
//                    //reset()
//                    adpForms.updateList(listofAllForms)
//                    layoutSearchForm.isVisible = false
//                    btexpandSearchForms.setImageDrawable(
//                        ContextCompat.getDrawable(ctx, R.drawable.img_expand_more))
//                } else {
//                    layoutSearchForm.isVisible = true
//                    btexpandSearchForms.setImageDrawable(
//                        ContextCompat.getDrawable(ctx, R.drawable.img_expand_less))
//                }
//            }
//            autoDist.onItemClickListener = AdapterView.OnItemClickListener { adapter, _, pos, _ ->
//                searchForms.setQuery("", false)
//                lifecycleScope.launch {
//                    val prov = if (listLocation.isNotEmpty()) listLocation.last().first else ""
//                    val getDistID =
//                        room.getDistID(prov, adapter.getItemAtPosition(pos).toString())
//                    val listUpdate = listofAllForms.filter {
//                        it.cap1?.v01provtxt?.lowercase(Locale.getDefault())
//                            ?.contains(prov) == true &&
//                                it.cap1.v02disttxt?.lowercase(Locale.getDefault())
//                                    ?.contains(getDistID) == true
//                    }
//                    activity?.runOnUiThread {
//                        val hintDist = "Distrito: $getDistID"
//                        autoCorre.let {
//                            it.hint = getString(R.string.corre)
//                            it.setText(getString(R.string.corre), false)
//                        }
//                        autoDistly.hint = hintDist
//                        adpForms.updateList(listUpdate)
//                    }
//                }
//            } //----- Agregar onItemClickListener para corregimientos
            scrollUser.smoothScrollTo(0,0)
        }
    }

//    private fun reset() {
//        with(bindingForm) {
//            autoDistly.hint = getString(R.string.dist)
//            autoCorre.hint = getString(R.string.corre)
//            autoDist.setText(getString(R.string.dist), false)
//            autoCorre.setText(getString(R.string.corre), false)
//            autoCorre.setAdapter(ArrayAdapter(ctx, R.layout.style_list, emptyArray<String>()))
//        }
//    }

    //---- Selector de formularios, room o servidor
    private fun spinnerSelection(position: Int) {
        with (bindingForm) {
            barForms.visibility = View.VISIBLE
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
                    val listofRoom = RoomView(dvmForm, ctx).getFormsUser(user).reversed()
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

    //---- Filtro de busqueda del recyclerview para lista cargada
    private fun search(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            var inco = 0
            val listUpdate: ArrayList<ModelForm> = ArrayList()
            val search = query.lowercase(Locale.getDefault())

            val listFilter = adpForms.list.ifEmpty { listofAllForms }
            listUpdate.addAll(
                if (search == "inconsistencia")
                     listFilter.filter { it.tieneIncon == true }
                else listFilter.filter {
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

    //---- Ventana de opciones para los formularios
    private fun popupBottom(item: ModelForm) {
        val msgOpcions = AlertDialog.Builder(ctx)
        val bindmsg: StyleMsgPopupBinding =
            DataBindingUtil.inflate(LayoutInflater.from(ctx),
                R.layout.style_msg_popup, null, false)
        val selection = bindingForm.spinFormsType.selectedItemPosition
        val ncontrol = "N° de control: ${
            Functions.ceroLeft(item.ncontrol ?: "0",Mob.CERO_COUNTLEFT)}"
        with(bindmsg) {
            msgOpcions.setView(bindmsg.root)
            msgtitle.text = ncontrol
            bt1.apply { //---- Cargar formulario
                text = getString(R.string.btcarga)
                icon = ContextCompat.getDrawable(ctx, R.drawable.img_download)
                setOnClickListener {
                    aDialog?.dismiss()
                    chargeForm(item)
                }
            }
            bt2.apply { //---- Ver formulario completo
                text = getString(R.string.btmoreinfo)
                icon = ContextCompat.getDrawable(ctx, R.drawable.img_formulario)
                setOnClickListener {
                    aDialog?.dismiss()
                    Handler(Looper.getMainLooper()).postDelayed({ viewForm(item) }, (Mob.TIME100MS))
                }
            }
            bt3.apply { //---- Ver Inconsistencias
                text = getString(R.string.viewIncon)
                icon = ContextCompat.getDrawable(ctx, R.drawable.img_warning1)
                setOnClickListener {
                    aDialog?.dismiss()
                    Handler(Looper.getMainLooper()).postDelayed({
                        lifecycleScope.launch { iconForm(item) } }, (Mob.TIME100MS))
                }
            }
            bt4.apply { //---- Borrar formulario
                text = getString(R.string.btdelete)
                icon = ContextCompat.getDrawable(ctx, R.drawable.img_delete)
                setOnClickListener {
                    aDialog?.dismiss()
                    Handler(Looper.getMainLooper()).postDelayed({
                        if (selection == 1 || selection == 2) msgBallon("Imposible borrar")
                        else deleteForm(item)
                    }, (Mob.TIME100MS))
                }
            }
            if (selection == 1 || selection == 2) bt4.isEnabled = false
            //if (item.tieneIncon != true) bt3.isEnabled = false
            aDialog = msgOpcions.create()
            aDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            aDialog?.window?.attributes?.windowAnimations =
                R.style.Animation_Design_BottomSheetDialog
            aDialog?.window?.setGravity(Gravity.BOTTOM)
            //aDialog?.setCancelable(false)
            aDialog?.show()
        }
    }

    //---- Carga del fomulario completa
    private fun chargeForm(item: ModelForm) {
        fun charge(form: ModelForm) {
            CreateForm.createLoad(form)
            Mob.indiceFormulario = Mob.CAP1_P01
            Handler(Looper.getMainLooper()).postDelayed({
                aDialog?.dismiss()
                activity?.finish()
                val options = ActivityOptions.makeCustomAnimation(
                    ctx,
                    R.animator.slide_in_from_right,
                    R.animator.slide_out_to_left
                )
                val intent = Intent(ctx, FormActivity::class.java)
                intent.addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP or
                            Intent.FLAG_ACTIVITY_CLEAR_TASK or
                            Intent.FLAG_ACTIVITY_NEW_TASK
                )
                startActivity(intent, options.toBundle())
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
                if (callForm?.code == Mob.CODE200 && callForm.body != null) {
                    //--------------------------- Agregar mas controles de errores con el server
                    charge(callForm.body)
                }
            } else charge(item)
        }
    }

    //---- Previsualización del formulario
    private fun viewForm(item: ModelForm) {
        val msgForm = AlertDialog.Builder(ctx)
        val bindmsg: StyleMsgLocalinfoBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(ctx),
                R.layout.style_msg_localinfo, null, false)
        msgForm.setView(bindmsg.root)
        lifecycleScope.launch {
            val ncontrol = Functions.ceroLeft(item.ncontrol ?: "0",Mob.CERO_COUNTLEFT)
            val tel1 = item.cap2?.v09tel1txt ?: "No registrado"
            val tel2 = item.cap2?.v09tel2txt ?: "No registrado"
            val email = item.cap2?.v11emailtxt ?: "No registrado"
            activity?.runOnUiThread {
                with(bindmsg) {
                    if (item.tieneIncon == true) lbtittlestyleF.setTextColor(Color.RED)
                    val blank = "".toEditable()
                    txtTel1Local.text = tel1.toEditable()
                    txtTel2Local.text = tel2.toEditable()
                    txtEmailLocal.text = email.toEditable()
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

    //---- Carga de inconsistencias
    private suspend fun iconForm(item: ModelForm) {
        bindingForm.barForms.visibility = View.VISIBLE
        val resp = dvmForm.getIncon(item.ncontrol ?: "")
        val respGson: String = try { Gson().toJson(resp.body) as String
        } catch (e: JsonParseException) { "" }
        val type: Type = object : TypeToken<ModelResponse?>() {}.type
        val jsonModel: ModelResponse? = Gson().fromJson<ModelResponse>(respGson, type)

         if (jsonModel != null) activity?.runOnUiThread {
             val msgSend = AlertDialog.Builder(ctx)
             val bindSend: StyleMsgAlertBinding =
                 DataBindingUtil.inflate(LayoutInflater.from(context),
                     R.layout.style_msg_alert, null, false)
             msgSend.setView(bindSend.root)
             val ncont = Functions.ceroLeft(jsonModel.ncontrol?.toString() ?: "0", Mob.CERO_COUNTLEFT)
             val tittle = "Número de control: $ncont"
             bindSend.apply {
                 msgtitle.text = tittle
                 msg1.text = getString(R.string.registerIncon)
                 msg2.visibility = View.GONE
                 msg6.apply {
                     visibility = View.VISIBLE
                     text = jsonModel.inconsistencias?.joinToString("\n\n")?.toEditable()
                 }
                 btnegativo.apply {
                     icon = ContextCompat.getDrawable(ctx, R.drawable.img_download)
                     text = getString(R.string.btcarga)
                     setOnClickListener {
                         aDialog?.dismiss()
                         Mob.viewIncon = true
                         Mob.inconsArray = jsonModel.inconsistencias
                         chargeForm(item)
                     }
                 }
                 btpositivo.apply {
                     icon = ContextCompat.getDrawable(ctx, R.drawable.img_close)
                     text = getString(R.string.cancel)
                     setOnClickListener { aDialog?.dismiss() }
                 }
             }
             aDialog = msgSend.create()
             aDialog?.show()
         }
         else if (!resp.server.isNullOrEmpty()) {
             val msg = resp.server.replace("warningMessage", "")
                 .replace("Inconsistencias no encontradas para el numero de",
                     "Sin inconsistencias para n°")
                 .replace("\"", "")
                 .replace(":", "")
                 .replace("{", "")
                 .replace("}", "")
             msgBallon(msg)
         } else { msgBallon("Error: ${resp.code}") }
        bindingForm.barForms.visibility = View.INVISIBLE
    }

    //---- Borrado del formulario
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

            btCancel.apply {
                text = getString(R.string.cancel)
                icon = ContextCompat.getDrawable(ctx, R.drawable.img_close)
                setOnClickListener { aDialog?.dismiss() }
            }
            btEnd.apply {
                text = getString(R.string.delete)
                backgroundTintList =
                    ContextCompat.getColorStateList(ctx, R.color.dark_red)
                icon = ContextCompat.getDrawable(ctx, R.drawable.img_delete)
                setOnClickListener {
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
                            lifecycleScope.launch{
                                val deleteResponse = RoomView(dvmForm, ctx).deleteForm(
                                    Mob.authData?.user ?: "",
                                    item.ncontrol ?: "")  ?: 0
                                if (deleteResponse > 0) activity?.runOnUiThread { //-- Deleted
                                    btCancel.isClickable = false
                                    btEnd.isClickable = false
                                    listofAllForms = listofAllForms.minus(item)
                                    adpForms.updateList(listofAllForms)
                                    aDialog?.dismiss()
                                    msgBallon("Formulario eliminado")
                                } else  activity?.runOnUiThread {
                                    aDialog?.dismiss()
                                    msgBallon("Imposible eliminar")
                                }

                            }
                        } else { txt0styleFly.error = "Clave incorrecta" }
                    }, (Mob.TIME500MS))
                }
            }
        }
    }

    //---- toast personalizado
    private fun msgBallon(msg: String) {
        val medida = msg.length * 7
        Handler(Looper.getMainLooper()).postDelayed({
            val alert = Functions.msgBallom(msg, medida, ctx, R.color.dark_red)
            alert.showAlignBottom(bindingForm.txtwarningForm)
            alert.dismissWithDelay(Mob.TIMELONG2SEG)
        }, (Mob.TIME1S))
    }
}