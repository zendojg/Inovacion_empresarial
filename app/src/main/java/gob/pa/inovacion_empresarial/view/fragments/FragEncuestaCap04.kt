package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo04Binding
import gob.pa.inovacion_empresarial.function.CreateInconsistecia
import gob.pa.inovacion_empresarial.function.Functions.toEditable
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelCap4

class FragEncuestaCap04 : Fragment() {

    private lateinit var bindingcap4: EncuestaCapitulo04Binding
    private lateinit var ctx: Context
    private var frameview2 = false
    private var frameview3 = false
    private var frameview4 = false
    //private val dvmCap4: DVModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingcap4 = EncuestaCapitulo04Binding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingcap4.root
    }

    override fun onResume() {
        super.onResume()
        Mob.indiceFormulario = Mob.CAP4P05
        if (Mob.seecap04) fillOut()
        else onAction()
    }
    private fun onAction() {
        with(bindingcap4) {

            if (frameview2) {
                layoutCap4292.visibility = View.VISIBLE
                btCap429Remove.visibility = View.VISIBLE
            }
            if (frameview3) {
                layoutCap4293.visibility = View.VISIBLE
                btCap429Remove.visibility = View.VISIBLE
            }
            if (frameview4) {
                layoutCap4294.visibility = View.VISIBLE
                btCap429Remove.visibility = View.VISIBLE
            }

            btCap429Plus.setOnClickListener {
                if (!layoutCap4292.isVisible) {
                    layoutCap4292.visibility = View.VISIBLE
                    btCap429Remove.visibility = View.VISIBLE
                } else if (!layoutCap4293.isVisible) {
                    layoutCap4293.visibility = View.VISIBLE
                } else if (!layoutCap4294.isVisible) {
                    layoutCap4294.visibility = View.VISIBLE
                }
            }

            btCap429Remove.setOnClickListener {
                when {
                    layoutCap4294.isVisible -> {
                        layoutCap4294.visibility = View.GONE
                        txtCap4294.text?.clear()
                        txtCap429Cinu4.text?.clear()
                    }
                    layoutCap4293.isVisible -> {
                        layoutCap4293.visibility = View.GONE
                        txtCap4293.text?.clear()
                        txtCap429Cinu3.text?.clear()
                    }
                    layoutCap4292.isVisible -> {
                        txtCap4292.text?.clear()
                        txtCap429Cinu2.text?.clear()
                        layoutCap4292.visibility = View.GONE
                        btCap429Remove.visibility = View.GONE
                    }
                }
            }
            lowCap4.setOnClickListener { saveCap() }
        }
    }

    private fun fillOut() {
        val cap4 = Mob.formComp?.cap4
        val blank = "".toEditable()
        with(bindingcap4) {
            txtCap428.text = cap4?.v28acttxt?.toEditable() ?: blank
            txtCap4281.text = cap4?.v28cinutxt?.toEditable() ?: blank
            txtCap4291.text = cap4?.v29act1txt?.toEditable() ?: blank
            txtCap4292.text = cap4?.v29act2txt?.toEditable() ?: blank
            txtCap4293.text = cap4?.v29act3txt?.toEditable() ?: blank
            txtCap4294.text = cap4?.v29act4txt?.toEditable() ?: blank

            txtCap429Cinu1.text = cap4?.v29cinu1txt?.toEditable() ?: blank
            txtCap429Cinu2.text = cap4?.v29cinu2txt?.toEditable() ?: blank
            txtCap429Cinu3.text = cap4?.v29cinu3txt?.toEditable() ?: blank
            txtCap429Cinu4.text = cap4?.v29cinu4txt?.toEditable() ?: blank

            frameview2 = !txtCap4292.text.isNullOrEmpty()
            frameview3 = !txtCap4293.text.isNullOrEmpty()
            frameview4 = !txtCap4294.text.isNullOrEmpty()
        }
        Mob.seecap04 = false
        onAction()
    }

    fun saveCap(): List<String> {
        with (bindingcap4) {
            Mob.cap4 = ModelCap4(
                Mob.cap4?.id,
                Mob.cap4?.ncontrol,
                txtCap428.text.toString().ifEmpty { null },
                txtCap4281.text.toString().ifEmpty { null },
                txtCap4291.text.toString().ifEmpty { null },
                txtCap429Cinu1.text.toString().ifEmpty { null },
                txtCap4292.text.toString().ifEmpty { null },
                txtCap429Cinu2.text.toString().ifEmpty { null },
                txtCap4293.text.toString().ifEmpty { null },
                txtCap429Cinu3.text.toString().ifEmpty { null },
                txtCap4294.text.toString().ifEmpty { null },
                txtCap429Cinu4.text.toString().ifEmpty { null },
            )
        }
        println("------------${Mob.cap4}")
        return viewCap()
    }

    private fun viewCap(): List<String> {
        with (bindingcap4) {
            val returnList: ArrayList<String> = ArrayList()
            if (txtCap4281.text.toString().isNotEmpty() && txtCap428.text.toString().isEmpty())
                returnList.add(CreateInconsistecia.inconsistencia(ctx,"13") ?: "")

            println("---------Is not empty: ${Mob.icap02o2}--${Mob.cap2}")
            Mob.icap02o1 = returnList.isNotEmpty()
            return returnList
        }
    }
}