package gob.pa.inovacion_empresarial.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo07Part3Binding
import gob.pa.inovacion_empresarial.model.Mob

class FragEncuestaCap07o3 : Fragment() {

    private lateinit var bindingcap7o3: EncuestaCapitulo07Part3Binding
    private lateinit var ctx: Context
    private var seecap = true

    private var indice1a = 0
    private var indice1b = 0
    private var indice1c = 0
    private var indice2a = 0
    private var indice2b = 0
    private var indice2c = 0
    private var indice2d = 0
    private var indice3a = 0
    private var indice3b = 0
    private var indice4a = 0
    private var indice4b = 0
    private var indice4c = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingcap7o3 = EncuestaCapitulo07Part3Binding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingcap7o3.root
    }

    override fun onResume() {
        super.onResume()

        Mob.indiceEnc = Mob.CAP7P14
        if (seecap) fillOut()
        else onAction()
    }

    private fun onAction() {
        with(bindingcap7o3) {
            lowCap7o3.setOnClickListener { saveCap() }

            val imp55Adp = ArrayAdapter(ctx, R.layout.style_box, Mob.arrGrade)
            imp55Adp.setDropDownViewResource(R.layout.style_list)

            spinCap7551A.adapter = imp55Adp
            spinCap7551B.adapter = imp55Adp
            spinCap7551C.adapter = imp55Adp
            spinCap7552A.adapter = imp55Adp
            spinCap7552B.adapter = imp55Adp
            spinCap7552C.adapter = imp55Adp
            spinCap7552D.adapter = imp55Adp
            spinCap7553A.adapter = imp55Adp
            spinCap7553B.adapter = imp55Adp
            spinCap7554A.adapter = imp55Adp
            spinCap7554B.adapter = imp55Adp
            spinCap7554C.adapter = imp55Adp
            spinsAction()
        }
    }

    private fun spinsAction() {
        with(bindingcap7o3) {

            spinCap7551A.setSelection(indice1a)
            spinCap7551B.setSelection(indice1b)
            spinCap7551C.setSelection(indice1c)
            spinCap7552A.setSelection(indice2a)
            spinCap7552B.setSelection(indice2b)
            spinCap7552C.setSelection(indice2c)
            spinCap7552D.setSelection(indice2d)
            spinCap7553A.setSelection(indice3a)
            spinCap7553B.setSelection(indice3b)
            spinCap7554A.setSelection(indice4a)
            spinCap7554B.setSelection(indice4b)
            spinCap7554C.setSelection(indice4c)

            spinCap7551A.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice1a = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap7551B.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice1b = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap7551C.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice1c = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap7552A.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice2a = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap7552B.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice2b = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap7552C.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice2c = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap7552D.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice2d = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap7553A.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice3a = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap7553B.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice3b = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap7554A.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice4a = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap7554B.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice4b = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap7554C.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice4c = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
        }
    }


    private fun fillOut() {
        val cap7 = Mob.formComp?.cap7

        indice1a = try {
            cap7?.v55txt1a?.toInt() ?: 0
        } catch (e: java.lang.NumberFormatException) {
            0
        }
        indice1b = try {
            cap7?.v55txt1b?.toInt() ?: 0
        } catch (e: java.lang.NumberFormatException) {
            0
        }
        indice1c = try {
            cap7?.v55txt1c?.toInt() ?: 0
        } catch (e: java.lang.NumberFormatException) {
            0
        }
        indice2a = try {
            cap7?.v55txt2a?.toInt() ?: 0
        } catch (e: java.lang.NumberFormatException) {
            0
        }
        indice2b = try {
            cap7?.v55txt2b?.toInt() ?: 0
        } catch (e: java.lang.NumberFormatException) {
            0
        }
        indice2c = try {
            cap7?.v55txt2c?.toInt() ?: 0
        } catch (e: java.lang.NumberFormatException) {
            0
        }
        indice2d = try {
            cap7?.v55txt2d?.toInt() ?: 0
        } catch (e: java.lang.NumberFormatException) {
            0
        }
        indice3a = try {
            cap7?.v55txt3a?.toInt() ?: 0
        } catch (e: java.lang.NumberFormatException) {
            0
        }
        indice3b = try {
            cap7?.v55txt3b?.toInt() ?: 0
        } catch (e: java.lang.NumberFormatException) {
            0
        }
        indice4a = try {
            cap7?.v55txt4a?.toInt() ?: 0
        } catch (e: java.lang.NumberFormatException) {
            0
        }
        indice4b = try {
            cap7?.v55txt4b?.toInt() ?: 0
        } catch (e: java.lang.NumberFormatException) {
            0
        }
        indice4c = try {
            cap7?.v55txt4c?.toInt() ?: 0
        } catch (e: java.lang.NumberFormatException) {
            0
        }

        seecap = false
        onAction()
    }


    private fun saveCap() {
        TODO("Not yet implemented")
    }

}