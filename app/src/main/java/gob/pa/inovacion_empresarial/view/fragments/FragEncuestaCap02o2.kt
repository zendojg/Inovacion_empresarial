package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo022DatosDelInformanteBinding
import gob.pa.inovacion_empresarial.function.Functions.toEditable
import gob.pa.inovacion_empresarial.model.Mob

class FragEncuestaCap02o2 : Fragment() {

    private lateinit var bindingcap2o2: EncuestaCapitulo022DatosDelInformanteBinding
    private lateinit var ctx: Context
    private var seecap = true
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
        Mob.indiceEnc = Mob.CAP2P03
        if (seecap) fillOut()
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
            lowCap2o2.setOnClickListener { savedCap() }
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
        seecap = false
        onAction()
    }

    private fun savedCap() { }

}