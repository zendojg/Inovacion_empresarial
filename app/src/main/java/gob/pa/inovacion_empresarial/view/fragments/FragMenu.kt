package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.databinding.MenuFragBinding
import gob.pa.inovacion_empresarial.model.DVModel
import gob.pa.inovacion_empresarial.model.Mob

class FragMenu : Fragment() {

    private lateinit var formbinding: MenuFragBinding
    private lateinit var ctx: Context
    private val dvmMenu: DVModel by viewModels()

    private var idCap1:Int? = null
    private var nCont:Int? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        formbinding = MenuFragBinding.inflate(layoutInflater)
        ctx = requireContext()

        formbinding.versionsearch.text = Mob.version

        return formbinding.root
    }


    override fun onResume() {
        super.onResume()

        action()
    }

    private fun action() {
        with(formbinding) {
            btEncuestaExpandMain.setOnClickListener {
                if (!linearEncuestaMain.isVisible) { //-- expandir
                    linearEncuestaMain.visibility = View.VISIBLE
                    linearModuloMain.visibility = View.GONE
                    layoutpiemain.visibility = View.GONE
                    btEncuestaExpandMain.setImageDrawable(
                        ContextCompat.getDrawable(ctx, android.R.drawable.arrow_up_float))


                } else { //-- contraer
                    layoutpiemain.visibility = View.VISIBLE
                    linearEncuestaMain.visibility = View.GONE
                    btEncuestaExpandMain.setImageDrawable(
                        ContextCompat.getDrawable(ctx, android.R.drawable.arrow_down_float))
                }
            }
            //-------------------------------------------------------------------------------------- " " Modulo
            btModuloExpandMain.setOnClickListener {
                if (!linearModuloMain.isVisible) { //-- expandir
                    linearModuloMain.visibility = View.VISIBLE
                    linearEncuestaMain.visibility = View.GONE

                    btModuloExpandMain.setImageDrawable(
                        ContextCompat.getDrawable(ctx, android.R.drawable.arrow_up_float))
                    btEncuestaExpandMain.setImageDrawable(
                        ContextCompat.getDrawable(ctx, android.R.drawable.arrow_down_float))
                } else { //-- contraer
                    linearModuloMain.visibility = View.GONE

                    btModuloExpandMain.setImageDrawable(
                        ContextCompat.getDrawable(ctx, android.R.drawable.arrow_down_float))
                }
            }
            //--------------------------------------------------------------------------------------
            //-------------------------------------------------------------------------------------- Comenzar Encuesta
            btEncuestaMain.setOnClickListener { viewpager(Mob.CAP1P01) }
            btcap01main.setOnClickListener { viewpager(Mob.CAP1P01) }
            btcap02main.setOnClickListener { viewpager(Mob.CAP2P02) }
            btcap03main.setOnClickListener { viewpager(Mob.CAP3P04) }
            btcap04main.setOnClickListener { viewpager(Mob.CAP4P05) }
            btcap05main.setOnClickListener { viewpager(Mob.CAP5P06) }
            btcap06main.setOnClickListener { viewpager(Mob.CAP6P08) }
            btcap07main.setOnClickListener { viewpager(Mob.CAP7P12) }
            btcap08main.setOnClickListener { viewpager(Mob.CAP8P15) }
            btcap09main.setOnClickListener { viewpager(Mob.CAP9P17) }
            btcap10main.setOnClickListener { viewpager(Mob.CAPXP19) }
            //-------------------------------------------------------------------------------------- Comenzar Modulo
            btModuloMain.setOnClickListener { viewpager(Mob.SEC1P20) }
            btsec01main.setOnClickListener { viewpager(Mob.SEC1P20) }
            btsec02main.setOnClickListener { viewpager(Mob.SEC2P21) }
            btsec03main.setOnClickListener { viewpager(Mob.SEC3P22) }
            btsec04main.setOnClickListener { viewpager(Mob.SEC4P23) }
            btsec05main.setOnClickListener { viewpager(Mob.OBSP24) }
        }
    }

    private fun viewpager(pos: Int) {
        Mob.indiceEnc = pos
        val pager = activity?.findViewById<ViewPager2>(R.id.viewpager)
        pager?.setCurrentItem(pos, false)

    }
}