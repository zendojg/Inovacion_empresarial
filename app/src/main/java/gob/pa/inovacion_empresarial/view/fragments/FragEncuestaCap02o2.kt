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
        Mob.indiceFormulario = Mob.CAP2_P03
        val infoCap = Mob.infoCap.find { it.indexCap == Mob.CAP2_P03 }

        if (infoCap?.capView == false) fillOut()
        else onAction()
    }
    private fun onAction() {
        with(bindingcap2o2) {
            frameCap219Tel2.isVisible = frameview
            btCap219tel1.setOnClickListener {
                frameCap219Tel2.visibility = View.VISIBLE
                btCap219tel1.isEnabled = false
                frameview = true
            }
            btCap219tel2.setOnClickListener {
                frameCap219Tel2.visibility = View.GONE
                btCap219tel1.isEnabled = true
                frameview = false
            }
        }
    }

    private fun fillOut() {
        val cap2 = Mob.formComp?.cap2
        val blank = "".toEditable()
        with(bindingcap2o2) {
            scrollForm.smoothScrollTo(0,0)
            txtCap216.text = cap2?.v16infonametxt?.toEditable() ?: blank
            txtCap217.text = cap2?.v17cargotxt?.toEditable() ?: blank
            txtCap218.text = cap2?.v18dirtxt?.toEditable() ?: blank
            txtCap2181.text = cap2?.v18dirreftxt?.toEditable() ?: blank
            txtCap2191.text = cap2?.v19tel1txt?.toEditable() ?: blank
            txtCap2192.text = cap2?.v19tel2txt?.toEditable() ?: blank
            txtCap220.text = cap2?.v20celtxt?.toEditable() ?: blank
            txtCap221.text = cap2?.v21emailtxt?.toEditable() ?: blank
            frameview = !txtCap2192.text.isNullOrBlank()
        }
        Mob.infoCap.find { it.indexCap == Mob.CAP2_P03 }?.capView = true
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
            if (cap2?.v16infonametxt.isNullOrEmpty())
                returnList.add(CreateIncon.inconsistencia(ctx,"611") ?: "")
            else if (cap2?.v17cargotxt.isNullOrEmpty() && cap2?.v16infonametxt?.isNotEmpty() == true)
                returnList.add(CreateIncon.inconsistencia(ctx,"5") ?: "")

            infoCap.find { it.indexCap == CAP2_P03 }?.incons = returnList.isNotEmpty()
            //println("Cap2_par2: --$cap2")
            return returnList
        }
    }


}