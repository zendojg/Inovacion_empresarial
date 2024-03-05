package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo021DatosDeLaEmpresaBinding
import gob.pa.inovacion_empresarial.function.CreateIncon
import gob.pa.inovacion_empresarial.function.Functions.toEditable
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelCap2
import java.util.Locale

class FragEncuestaCap02o1 : Fragment() {

    private lateinit var bindingcap2o1: EncuestaCapitulo021DatosDeLaEmpresaBinding
    private lateinit var ctx: Context
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
        Mob.indiceFormulario = Mob.CAP2_P02
        val infoCap = Mob.infoCap.find { it.indexCap == Mob.CAP2_P02 }
        if (infoCap?.capView == false) fillOut()
        else onAction()
    }

    private fun onAction() {
        with(bindingcap2o1) {
            scrollForm.smoothScrollTo(0,0)
            if (Mob.formComp?.cap2?.v05nameLtxt.isNullOrEmpty()) txtCap25.setTextColor(Color.BLACK)
            else txtCap25.isEnabled = false
            if (Mob.formComp?.cap2?.v06razontxt.isNullOrEmpty()) txtCap26.setTextColor(Color.BLACK)
            else txtCap26.isEnabled = false
            if (Mob.formComp?.cap2?.v07ructxt.isNullOrEmpty()) txtCap27.setTextColor(Color.BLACK)
            else txtCap27.isEnabled = false
            if (Mob.formComp?.cap2?.v07dvtxt.isNullOrEmpty()) txtCap27DV.setTextColor(Color.BLACK)
            else txtCap27DV.isEnabled = false

            frameCap29Tel2.isVisible = frameview
            btCap29Tel1.setOnClickListener {
                frameCap29Tel2.visibility = View.VISIBLE
                frameview = true
                btCap29Tel1.isEnabled = false
            }
            btCap29Tel2.setOnClickListener {
                frameCap29Tel2.visibility = View.GONE
                frameview = false
                btCap29Tel1.isEnabled = true
            }
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
            txtCap215.text = cap2?.v15nlNumPma?.toEditable() ?: blank
            txtCap2151.text = cap2?.v15nlNumProv?.toEditable() ?: blank

            frameview = !txtCap292.text.isNullOrBlank()
        }
        Mob.infoCap.find { it.indexCap == Mob.CAP2_P02 }?.capView = true
        onAction()
    }

    fun saveCap(): List<String> {
        with (bindingcap2o1) {
            Mob.cap2 = ModelCap2(
                id = Mob.formComp?.cap2?.id,
                ncontrol = Mob.formComp?.cap2?.ncontrol,
                v05nameLtxt = txtCap25.text.toString().ifEmpty { null },
                v06razontxt = txtCap26.text.toString().ifEmpty { null },
                v07ructxt = txtCap27.text.toString().ifEmpty { null },
                v07dvtxt = txtCap27DV.text.toString().ifEmpty { null },
                v08dirtxt = txtCap28.text.toString().ifEmpty { null },
                v08dirreftxt = txtCap281.text.toString().ifEmpty { "Sin anotar" },
                v09tel1txt = txtCap291.text.toString().ifEmpty { null },
                v09tel2txt = if (frameview) txtCap292.text.toString().ifEmpty { null } else null,
                v10celtxt = txtCap210.text.toString().ifEmpty { null },
                v11emailtxt = txtCap211.text.toString().ifEmpty { null },
                v12webtxt = txtCap212.text.toString().ifEmpty { null },
                v13nclavetxt = txtCap213.text.toString().ifEmpty { null },
                v14nlNum = txtCap214.text.toString().ifEmpty { null },
                v15nlNumPma = txtCap215.text.toString().ifEmpty { null },
                v15nlNumProv =txtCap2151.text.toString().ifEmpty { null },
                Mob.cap2?.v16infonametxt,
                Mob.cap2?.v17cargotxt,
                Mob.cap2?.v18dirtxt,
                Mob.cap2?.v18dirreftxt,
                Mob.cap2?.v19tel1txt,
                Mob.cap2?.v19tel2txt,
                Mob.cap2?.v20celtxt,
                Mob.cap2?.v21emailtxt
            )
            return viewCap()
        }
    }
    private fun viewCap(): List<String> {
        with(Mob) {
            val returnList: ArrayList<String> = ArrayList()
            val tel = cap2?.v09tel1txt ?: "0"
            val dir = cap2?.v08dirtxt?.lowercase(Locale.getDefault()) ?: ""
            val dirRules = listOf(
                "mall",
                "centro",
                "avenida",
                "ave",
                "calle",
                "edificio",
                "edif",
                "casa",
                "piso",
                "local"
            )
            val regex = dirRules.joinToString("|").toRegex(RegexOption.IGNORE_CASE)

            if (!regex.containsMatchIn(dir))
                returnList.add(CreateIncon.inconsistencia(ctx,"606") ?: "")
            if (tel.toInt() == 0)
                returnList.add(CreateIncon.inconsistencia(ctx,"607") ?: "")
            else if (tel.length < MIN_LENGHTTEL)
                returnList.add(CreateIncon.inconsistencia(ctx,"608") ?: "")

            if (cap2?.v14nlNum.isNullOrEmpty() || cap2?.v14nlNum == "0") {
                returnList.add(CreateIncon.inconsistencia(ctx,"604") ?: "")
            } else  {
                val p14 = try { cap2?.v14nlNum?.toInt() ?: 0 }
                catch (e: NumberFormatException) { 0 }
                val p15a = try { cap2?.v15nlNumPma?.toInt() ?: 0 }
                catch (e: NumberFormatException) { 0 }
                val p15b = try { cap2?.v15nlNumProv?.toInt() ?: 0 }
                catch (e: NumberFormatException) { 0 }
                val p15 = p15a + p15b

                if (p15 == 0) {
                    returnList.add(CreateIncon.inconsistencia(ctx,"605") ?: "")
                }
                else if (p14 < p15) {
                    returnList.add(CreateIncon.inconsistencia(ctx,"4") ?: "")
                }
            }
            infoCap.find { it.indexCap == CAP2_P02 }?.incons = returnList.isNotEmpty()
            //println("Cap2_part1:--$cap2")
            return returnList
        }
    }

}