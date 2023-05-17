package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.setContentView
import androidx.databinding.adapters.SearchViewBindingAdapter
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.databinding.ActivityMainBinding
import gob.pa.inovacion_empresarial.databinding.FragSearchMainBinding
import gob.pa.inovacion_empresarial.view.FormActivity

class MainFragmentSearch: Fragment() {

    private lateinit var fragSearch: FragSearchMainBinding
    private lateinit var ctx: Context

//    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState) }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
    fragSearch = FragSearchMainBinding.inflate(layoutInflater)
    ctx = requireContext()
    return fragSearch.root

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


    fun actions() {
        fragSearch.bt1main.setOnClickListener {
            startActivity(Intent(ctx, FormActivity::class.java))
        }

    }

}
