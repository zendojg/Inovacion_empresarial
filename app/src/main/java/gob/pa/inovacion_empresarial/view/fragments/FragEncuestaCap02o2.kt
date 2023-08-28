package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo022DatosDelInformanteBinding
import gob.pa.inovacion_empresarial.function.CreateIncon
import gob.pa.inovacion_empresarial.function.Functions.toEditable
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelCap2

class FragEncuestaCap02o2 : Fragment() {

    private lateinit var bindingcap2o2: EncuestaCapitulo022DatosDelInformanteBinding
    private lateinit var ctx: Context
    private var frameview = false

    //private val dvmCap2o2: DVModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingcap2o2 = EncuestaCapitulo022DatosDelInformanteBinding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingcap2o2.root
    }

    override fun onResume() {
        super.onResume()
        Mob.indiceFormulario = Mob.CAP2P03
        if (Mob.seecap02o2) fillOut()
        else onAction()
    }
    private fun onAction() {
        with(bindingcap2o2) {
            frameCap219Tel2.isVisible = frameview
            btCap219tel1.setOnClickListener {
                if (frameview) {
                    frameCap219Tel2.visibility = View.VISIBLE
                    btCap219tel1.isEnabled = false
                    frameview = true
                }
            }
            btCap219tel2.setOnClickListener {
                txtCap2192.text?.clear()
                frameCap219Tel2.visibility = View.GONE
                btCap219tel1.isEnabled = true
                frameview = false
            }
            lowCap2o2.setOnClickListener { saveCap() }
//            txtCap221.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
//                if (!hasFocus && txtCap221.text.toString().isNotEmpty()) {
//                    if (!txtCap221.text.toString().contains("@")) {
//                        val alert = Functions.msgMark(
//                            "Correo electronico inválido",190,ctx, Color.BLUE)
//                        alert.showAlignBottom(txtCap221)
//                        alert.dismissWithDelay(2000L)
//                    }
//                }
//            }
        }
    }

    private fun fillOut() {
        val cap2 = Mob.formComp?.cap2
        val blank = "".toEditable()
        with(bindingcap2o2) {
            txtCap216.text = cap2?.v16infonametxt?.toEditable() ?: blank
            txtCap217.text = cap2?.v17cargotxt?.toEditable() ?: blank
            txtCap218.text = cap2?.v18dirtxt?.toEditable() ?: blank
            txtCap2181.text = cap2?.v18dirreftxt?.toEditable() ?: blank
            txtCap2191.text = cap2?.v19tel1txt?.toEditable() ?: blank
            txtCap2192.text = cap2?.v19tel2txt?.toEditable() ?: blank
            txtCap220.text = cap2?.v20celtxt?.toEditable() ?: blank
            frameview = !txtCap2192.text.isNullOrBlank()
        }
        Mob.seecap02o2 = false
        onAction()
    }

    fun saveCap(): List<String> {
        with (bindingcap2o2) {
            Mob.cap2 = ModelCap2(
                Mob.cap2?.id,
                Mob.cap2?.ncontrol,
                Mob.cap2?.v05nameLtxt,
                Mob.cap2?.v06razontxt,
                Mob.cap2?.v07ructxt,
                Mob.cap2?.v07dvtxt,
                Mob.cap2?.v08dirtxt,
                Mob.cap2?.v08dirreftxt,
                Mob.cap2?.v09tel1txt,
                Mob.cap2?.v09tel2txt,
                Mob.cap2?.v10celtxt,
                Mob.cap2?.v11emailtxt,
                Mob.cap2?.v12webtxt,
                Mob.cap2?.v13nclavetxt,
                Mob.cap2?.v14nlNum,
                Mob.cap2?.v15nlNumPma,
                Mob.cap2?.v15nlNumProv,
                txtCap216.text.toString().ifEmpty { null },
                txtCap217.text.toString().ifEmpty { null },
                txtCap218.text.toString().ifEmpty { null },
                txtCap2181.text.toString().ifEmpty { null },
                txtCap2191.text.toString().ifEmpty { null },
                txtCap2192.text.toString().ifEmpty { null },
                txtCap220.text.toString().ifEmpty { null },
                txtCap221.text.toString().ifEmpty { null }
            )
            return viewCap()

        }
    }

    private fun viewCap(): List<String> {
        with (Mob) {
            val returnList: ArrayList<String> = ArrayList()
            if (cap2?.v17cargotxt.isNullOrEmpty() && cap2?.v16infonametxt?.isNotEmpty() == true)
                returnList.add(CreateIncon.inconsistencia(ctx,"5") ?: "")

            icap02o2 = returnList.isNotEmpty()
            println("---------Is not empty: $icap02o2--$cap2")
            return returnList
        }
    }


}