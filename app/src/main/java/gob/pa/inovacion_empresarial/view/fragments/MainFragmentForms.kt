package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.databinding.FragFormsMainBinding
import gob.pa.inovacion_empresarial.model.DVModel
import gob.pa.inovacion_empresarial.model.Mob

class MainFragmentForms : Fragment() {
    private lateinit var bindingForm: FragFormsMainBinding
    private lateinit var ctx: Context
    private val dvmForm: DVModel by viewModels()
    var aDialog: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingForm = FragFormsMainBinding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingForm.root
    }

    override fun onResume() {
        super.onResume()


        onAction()
    }

    private fun onAction() {
        bindingForm.btbackForm.setOnClickListener {
            val pager = activity?.findViewById<ViewPager2>(R.id.viewpagerMain)
            pager?.setCurrentItem(Mob.INIT01, true) }
    }


}