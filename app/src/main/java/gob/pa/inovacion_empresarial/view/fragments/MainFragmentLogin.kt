package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.databinding.FragLoginMainBinding

class MainFragmentLogin: Fragment() {

    private lateinit var fragLogin: FragLoginMainBinding
    private lateinit var ctx: Context

//    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState) }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
    fragLogin = FragLoginMainBinding.inflate(layoutInflater)
    ctx = requireContext()
    return fragLogin.root
    }

//    override fun onViewCreated(
//        view: View,
//        savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//    }

    override fun onResume() {
        super.onResume()
        actions()
    }

    override fun onPause() {
        super.onPause()
    }


    private fun actions() {
        fragLogin.btLogin.setOnClickListener {
            val pager = activity?.findViewById<ViewPager2>(R.id.viewpagerMain)
            fragLogin.barLogin.visibility = View.VISIBLE
            Handler(Looper.getMainLooper()).postDelayed({
                fragLogin.barLogin.visibility = View.GONE
                pager?.setCurrentItem(1, true)
            }, (500).toLong())
        }

    }

}
