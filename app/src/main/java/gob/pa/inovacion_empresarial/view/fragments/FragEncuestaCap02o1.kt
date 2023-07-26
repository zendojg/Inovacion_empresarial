package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo021DatosDeLaEmpresaBinding
import gob.pa.inovacion_empresarial.function.Functions.toEditable
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelCap2

class FragEncuestaCap02o1 : Fragment() {


    private lateinit var bindingcap2o1: EncuestaCapitulo021DatosDeLaEmpresaBinding
    private lateinit var ctx: Context
    private var seecap = true
    private var frameview = false
    //private val dvmCap2o1: DVModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingcap2o1 = EncuestaCapitulo021DatosDeLaEmpresaBinding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingcap2o1.root
    }

    override fun onResume() {
        super.onResume()
        Mob.encuestaFrag = this
        Mob.indiceEnc = Mob.CAP2P02
        if (seecap) fillOut()
        else onAction()
    }

    private fun onAction() {
        with(bindingcap2o1) {
            frameCap29Tel2.isVisible = frameview
            btCap29Tel1.setOnClickListener {
                frameCap29Tel2.visibility = View.VISIBLE
                frameview = true
                btCap29Tel1.isEnabled = false
            }
            btCap29Tel2.setOnClickListener {
                txtCap292.text?.clear()
                frameCap29Tel2.visibility = View.GONE
                frameview = false
                btCap29Tel1.isEnabled = true
            }
            lowCap2o1.setOnClickListener { saveCap() }
        }
    }

    private fun fillOut() {
        val cap2 = Mob.formComp?.cap2
        val blank = "".toEditable()
        with(bindingcap2o1) {
            txtCap25.text = cap2?.v05nameLtxt?.toEditable() ?: blank
            txtCap26.text = cap2?.v06razontxt?.toEditable() ?: blank
            txtCap27.text = cap2?.v07ructxt?.toEditable() ?: blank
            txtCap27DV.text = cap2?.v07dvtxt?.toEditable() ?: blank
            txtCap28.text = cap2?.v08dirtxt?.toEditable() ?: blank
            txtCap281.text = cap2?.v08dirreftxt?.toEditable() ?: blank
            txtCap291.text = cap2?.v09tel1txt?.toEditable() ?: blank
            txtCap292.text = cap2?.v09tel2txt?.toEditable() ?: blank
            txtCap210.text = cap2?.v10celtxt?.toEditable() ?: blank
            txtCap211.text = cap2?.v11emailtxt?.toEditable() ?: blank
            txtCap212.text = cap2?.v12webtxt?.toEditable() ?: blank
            txtCap213.text = cap2?.v13nclavetxt?.toEditable() ?: blank
            txtCap214.text = cap2?.v14nlNum?.toEditable() ?: blank
            txtCap215.text = cap2?.v15nlNum?.toEditable() ?: blank
            txtCap2151.text = cap2?.v15nldescNum?.toEditable() ?: blank

            frameview = !txtCap292.text.isNullOrBlank()
        }
        seecap = false
        onAction()
    }

    fun saveCap() {
        with (bindingcap2o1) {
            Mob.cap2 = ModelCap2(
                Mob.cap2?.id,
                Mob.cap2?.ncontrol,
                txtCap25.text.toString(),
                txtCap26.text.toString(),
                txtCap27.text.toString(),
                txtCap27DV.text.toString(),
                txtCap28.text.toString(),
                txtCap281.text.toString(),
                txtCap291.text.toString(),
                txtCap292.text.toString(),
                txtCap210.text.toString(),
                txtCap211.text.toString(),
                txtCap212.text.toString(),
                txtCap213.text.toString(),
                txtCap214.text.toString(),
                txtCap215.text.toString(),
                txtCap2151.text.toString(),
                Mob.cap2?.v16infonametxt,
                Mob.cap2?.v17cargotxt,
                Mob.cap2?.v18dirtxt,
                Mob.cap2?.v18dirreftxt,
                Mob.cap2?.v19tel1txt,
                Mob.cap2?.v19tel2txt,
                Mob.cap2?.v20celtxt,
                Mob.cap2?.v21emailtxt
            )
            println("------------${Mob.cap2}")
        }
    }

}