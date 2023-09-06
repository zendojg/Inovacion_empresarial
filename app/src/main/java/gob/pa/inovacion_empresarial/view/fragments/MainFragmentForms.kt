package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
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
import gob.pa.inovacion_empresarial.function.Functions.hideKeyboard
import gob.pa.inovacion_empresarial.function.Functions.toEditable
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
            adpForms = AdapterForms(list) { windBottom(it) }
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

    private fun windBottom(item: ModelForm) { //----------------------------------------------------------- ARREGLAR LA BUSQUEDA QUE TRAE UN LIST INCOMPLETO
        val msgOpcions = AlertDialog.Builder(ctx)
        val bindmsg: StyleMsgPopupBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(ctx),
                R.layout.style_msg_popup, null, false)
        val selection = bindingForm.spinFormsType.selectedItemPosition
        with(bindmsg) {
            msgOpcions.setView(bindmsg.root)
            val ncontrol = "N° de control: ${Functions.ceroLeft(item.ncontrol ?: "0",4)}"

            msgtitle.text = ncontrol
            bt1.text = getString(R.string.btcarga)
            bt2.text = getString(R.string.btmoreinfo)
            bt3.text = getString(R.string.btdelete)
            bt4.text = "Opción por agregar"

            bt1.icon= ContextCompat.getDrawable(ctx, R.drawable.img_download)
            bt2.icon= ContextCompat.getDrawable(ctx, R.drawable.img_formulario)
            bt3.icon= ContextCompat.getDrawable(ctx, R.drawable.img_delete)
            bt4.icon= ContextCompat.getDrawable(ctx, R.drawable.img_plus)

            if (selection == 1 || selection == 2) bt3.isEnabled = false
            aDialog = msgOpcions.create()
            aDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            aDialog?.window?.attributes?.windowAnimations =
                R.style.Animation_Design_BottomSheetDialog
            aDialog?.window?.setGravity(Gravity.BOTTOM)
            //aDialog?.setCancelable(false)
            aDialog?.show()

            bt1.setOnClickListener {
                aDialog?.dismiss()
                CreateForm.createLoad(item)
                Mob.indiceFormulario = Mob.CAP1P01
                Handler(Looper.getMainLooper()).postDelayed({
                    activity?.finish()
                    val intent = Intent(ctx, FormActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or
                            Intent.FLAG_ACTIVITY_CLEAR_TASK  or
                            Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }, (Mob.TIME100MS))
            }

            bt2.setOnClickListener {
                aDialog?.dismiss()
                Handler(Looper.getMainLooper()).postDelayed({
                    viewForm(item)
                }, (Mob.TIME100MS))
            }

            bt3.setOnClickListener {
                aDialog?.dismiss()
                Handler(Looper.getMainLooper()).postDelayed({
                    if (selection == 1 || selection == 2) msgBallon("Imposible borrar")
                    else deleteForm(item)
                }, (Mob.TIME100MS))
            }

            bt4.setOnClickListener {
                aDialog?.dismiss()
            }
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
                    list = list.minus(item)
                    adpForms.updateList(list)
                    msgBallon("Formulario eliminado")
                } else {
                    txt0styleFly.error = "Clave incorrecta"
                }
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
        val msgForm = AlertDialog.Builder(ctx)
        val bindmsg: StyleMsgLocalinfoBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(ctx),
                R.layout.style_msg_localinfo, null, false)
        msgForm.setView(bindmsg.root)
        lifecycleScope.launch {
            val ncontrol = Functions.ceroLeft(item.ncontrol ?: "0",Mob.CEROLEFT)
            val room = RoomView(dvmForm, ctx)
            val provincia = room.getProvName(item.cap1?.v01provtxt ?: "0")
            val distrito = room.getDistName(item.cap1?.v01provtxt ?: "0",
                item.cap1?.v02disttxt ?: "0")
            val corregimiento = room.getCorreName(item.cap1?.v01provtxt ?: "0",
                item.cap1?.v02disttxt ?: "0", item.cap1?.v03corretxt ?: "0")
            activity?.runOnUiThread {
                with(bindmsg) {
                    txtProvLocal.text = provincia.toEditable()
                    txtDistLocal.text = distrito.toEditable()
                    txtCorreLocal.text = corregimiento.toEditable()
                    txtNcontrolLocal.text = ncontrol.toEditable()
                    txtNameLocal.text = item.cap2?.v05nameLtxt?.toEditable() ?: "".toEditable()
                    txtRazonLocal.text = item.cap2?.v06razontxt?.toEditable() ?: "".toEditable()
                    txtRUCLocal.text = item.cap2?.v07ructxt?.toEditable() ?: "".toEditable()

                    aDialog = msgForm.create()
                    aDialog?.window?.attributes?.windowAnimations =
                        R.style.Animation_Design_BottomSheetDialog
                    aDialog?.setCancelable(false)
                    aDialog?.show()

                    btBackLocal.setOnClickListener { aDialog?.dismiss() }
                    btInconLocal.setOnClickListener {
                        aDialog?.dismiss()
                        CreateForm.createLoad(item)
                        Mob.indiceFormulario = 1
                        Handler(Looper.getMainLooper()).postDelayed({
                            activity?.finish()
                            val intent = Intent(ctx, FormActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK  or
                                    Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        }, (Mob.TIME100MS))
                    }
                }
            }
        }

    }



}