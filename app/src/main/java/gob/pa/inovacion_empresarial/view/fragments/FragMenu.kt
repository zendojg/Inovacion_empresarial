package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.databinding.EncuestaModuloMenuBinding
import gob.pa.inovacion_empresarial.model.Mob
//import gob.pa.inovacion_empresarial.model.DVModel
//import androidx.fragment.app.viewModels

class FragMenu : Fragment() {

    private lateinit var formbinding: EncuestaModuloMenuBinding
    private lateinit var ctx: Context
    //private val dvmMenu: DVModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        formbinding = EncuestaModuloMenuBinding.inflate(layoutInflater)
        ctx = requireContext()
        formbinding.versionsearch.text = Mob.version
        return formbinding.root
    }

    override fun onResume() {
        super.onResume()
        Mob.indiceFormulario = Mob.MENU_P00
        action()
    }

    private fun action() {
        with(formbinding) {

            btEncuestaMain.setOnClickListener {
                if (!linearEncuestaMain.isVisible) { //-- expandir
                    linearEncuestaMain.visibility = View.VISIBLE
                    linearModuloMain.visibility = View.GONE
                    layoutpiemain.visibility = View.GONE
                    btEncuestaMain.icon = ContextCompat.getDrawable(ctx,R.drawable.img_expand_less)
                    btModuloMain.icon = ContextCompat.getDrawable(ctx,R.drawable.img_expand_more)

                } else { //-- contraer
                    layoutpiemain.visibility = View.VISIBLE
                    linearEncuestaMain.visibility = View.GONE
                    btEncuestaMain.icon = ContextCompat.getDrawable(ctx,R.drawable.img_expand_more)
                }
            }
            //----- " " Modulo
            btModuloMain.setOnClickListener {
                if (!linearModuloMain.isVisible) { //-- expandir
                    linearModuloMain.visibility = View.VISIBLE
                    linearEncuestaMain.visibility = View.GONE

                    btModuloMain.icon = ContextCompat.getDrawable(ctx,R.drawable.img_expand_less)
                    btEncuestaMain.icon = ContextCompat.getDrawable(ctx,R.drawable.img_expand_more)

                } else { //-- contraer
                    linearModuloMain.visibility = View.GONE
                    btModuloMain.icon = ContextCompat.getDrawable(ctx,R.drawable.img_expand_more)
                }
            }
            //-----------------------
            //----- Comenzar Encuesta
            btcap01main.setOnClickListener { viewpager(Mob.CAP1_P01) }
            btcap02main.setOnClickListener { viewpager(Mob.CAP2_P02) }
            btcap03main.setOnClickListener { viewpager(Mob.CAP3_P04) }
            btcap04main.setOnClickListener { viewpager(Mob.CAP4_P05) }
            btcap05main.setOnClickListener { viewpager(Mob.CAP5_P06) }
            btcap06main.setOnClickListener { viewpager(Mob.CAP6_P08) }
            btcap07main.setOnClickListener { viewpager(Mob.CAP7_P12) }
            btcap08main.setOnClickListener { viewpager(Mob.CAP8_P15) }
            btcap09main.setOnClickListener { viewpager(Mob.CAP9_P17) }
            btcap10main.setOnClickListener { viewpager(Mob.CAPX_P19) }
            //----- Comenzar Modulo
            btsec01main.setOnClickListener { viewpager(Mob.SEC1_P20) }
            btsec02main.setOnClickListener { viewpager(Mob.SEC2_P21) }
            btsec03main.setOnClickListener { viewpager(Mob.SEC3_P22) }
            btsec04main.setOnClickListener { viewpager(Mob.SEC4_P23) }
            btsec05main.setOnClickListener { viewpager(Mob.OBSE_P24) }


            if (Mob.indiceFormulario < Mob.SEC1_P20) {
                if (!linearEncuestaMain.isVisible) btEncuestaMain.callOnClick()
            } else {
                if (!linearModuloMain.isVisible) btModuloMain.callOnClick()
            }
        }
    }

    private fun viewpager(pos: Int) {
        Mob.indiceFormulario = pos
        val pager = activity?.findViewById<ViewPager2>(R.id.viewpager)
        pager?.setCurrentItem(pos, false)
    }
}