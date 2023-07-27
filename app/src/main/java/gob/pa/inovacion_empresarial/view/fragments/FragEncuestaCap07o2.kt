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
import gob.pa.inovacion_empresarial.databinding.EncuestaCapitulo07Part2Binding
import gob.pa.inovacion_empresarial.function.Functions.toEditable
import gob.pa.inovacion_empresarial.model.Mob
import gob.pa.inovacion_empresarial.model.ModelCap7

class FragEncuestaCap07o2 : Fragment() {

    private lateinit var bindingcap7o2: EncuestaCapitulo07Part2Binding
    private lateinit var ctx: Context
    private var seecap = true

    private var indice01 = 0
    private var indice02 = 0
    private var indice03 = 0
    private var indice04 = 0
    private var indice05 = 0
    private var indice06 = 0
    private var indice07 = 0
    private var indice08 = 0
    private var indice09 = 0
    private var indice10 = 0
    private var indice11 = 0
    private var indice12 = 0
    private var indice13 = 0
    private var indice14 = 0
    private var indice15 = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bindingcap7o2 = EncuestaCapitulo07Part2Binding.inflate(layoutInflater)
        ctx = requireContext()
        return bindingcap7o2.root
    }

    override fun onResume() {
        super.onResume()
        Mob.indiceEnc = Mob.CAP7P13
        if (seecap) fillOut()
        else onAction()
    }

    private fun onAction() {
        with(bindingcap7o2) {
            lowCap7o2.setOnClickListener { saveCap() }


            val imp54Adp = ArrayAdapter(ctx, R.layout.style_box, Mob.arrImp)
            imp54Adp.setDropDownViewResource(R.layout.style_list)

            spinCap7541.adapter = imp54Adp
            spinCap7542.adapter = imp54Adp
            spinCap7543.adapter = imp54Adp
            spinCap7544.adapter = imp54Adp
            spinCap7545.adapter = imp54Adp
            spinCap7546.adapter = imp54Adp
            spinCap7547.adapter = imp54Adp
            spinCap7548.adapter = imp54Adp
            spinCap7549.adapter = imp54Adp
            spinCap75410.adapter = imp54Adp
            spinCap75411.adapter = imp54Adp
            spinCap75412.adapter = imp54Adp
            spinCap75413.adapter = imp54Adp
            spinCap75414.adapter = imp54Adp
            spinCap75415.adapter = imp54Adp
            spinsAction()
        }
    }

    private fun spinsAction() {
        with(bindingcap7o2) {

            spinCap7541.setSelection(indice01)
            spinCap7542.setSelection(indice02)
            spinCap7543.setSelection(indice03)
            spinCap7544.setSelection(indice04)
            spinCap7545.setSelection(indice05)

            spinCap7546.setSelection(indice06)
            spinCap7547.setSelection(indice07)
            spinCap7548.setSelection(indice08)
            spinCap7549.setSelection(indice09)
            spinCap75410.setSelection(indice10)

            spinCap75411.setSelection(indice11)
            spinCap75412.setSelection(indice12)
            spinCap75413.setSelection(indice13)
            spinCap75414.setSelection(indice14)
            spinCap75415.setSelection(indice15)

            spinCap7541.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice01 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap7542.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice02 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap7543.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice03 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap7544.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice04 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap7545.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice05 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap7546.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice06 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap7547.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice07 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap7548.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice08 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap7549.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice09 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap75410.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice10 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap75411.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice11 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap75412.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice12 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap75413.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice13 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap75414.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice14 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
            spinCap75415.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adp: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    indice15 = pos
                }

                override fun onNothingSelected(adp: AdapterView<*>?) {
                    println("---NO selection")
                }
            }
        }
    }


    private fun fillOut() {
        val cap7 = Mob.formComp?.cap7
        fillOut53(cap7)

        indice01 = try {
            cap7?.v54txt01?.toInt() ?: 0
        } catch (e: java.lang.NumberFormatException) {
            0
        }
        indice02 = try {
            cap7?.v54txt02?.toInt() ?: 0
        } catch (e: java.lang.NumberFormatException) {
            0
        }
        indice03 = try {
            cap7?.v54txt03?.toInt() ?: 0
        } catch (e: java.lang.NumberFormatException) {
            0
        }
        indice04 = try {
            cap7?.v54txt04?.toInt() ?: 0
        } catch (e: java.lang.NumberFormatException) {
            0
        }
        indice05 = try {
            cap7?.v54txt05?.toInt() ?: 0
        } catch (e: java.lang.NumberFormatException) {
            0
        }
        indice06 = try {
            cap7?.v54txt06?.toInt() ?: 0
        } catch (e: java.lang.NumberFormatException) {
            0
        }
        indice07 = try {
            cap7?.v54txt07?.toInt() ?: 0
        } catch (e: java.lang.NumberFormatException) {
            0
        }
        indice08 = try {
            cap7?.v54txt08?.toInt() ?: 0
        } catch (e: java.lang.NumberFormatException) {
            0
        }
        indice09 = try {
            cap7?.v54txt09?.toInt() ?: 0
        } catch (e: java.lang.NumberFormatException) {
            0
        }
        indice10 = try {
            cap7?.v54txt10?.toInt() ?: 0
        } catch (e: java.lang.NumberFormatException) {
            0
        }
        indice11 = try {
            cap7?.v54txt11?.toInt() ?: 0
        } catch (e: java.lang.NumberFormatException) {
            0
        }
        indice12 = try {
            cap7?.v54txt12?.toInt() ?: 0
        } catch (e: java.lang.NumberFormatException) {
            0
        }
        indice13 = try {
            cap7?.v54txt13?.toInt() ?: 0
        } catch (e: java.lang.NumberFormatException) {
            0
        }
        indice14 = try {
            cap7?.v54txt14?.toInt() ?: 0
        } catch (e: java.lang.NumberFormatException) {
            0
        }
        indice15 = try {
            cap7?.v54txt15?.toInt() ?: 0
        } catch (e: java.lang.NumberFormatException) {
            0
        }
        seecap = false
        onAction()
    }

    private fun fillOut53(cap7: ModelCap7?) {
        val blank = "".toEditable()
        with(bindingcap7o2) {
            txtCap753A2021.text = cap7?.v53num21a?.toString()?.toEditable() ?: blank
            txtCap753A2022.text = cap7?.v53num22a?.toString()?.toEditable() ?: blank
            txtCap753B2021.text = cap7?.v53num21b?.toString()?.toEditable() ?: blank
            txtCap753B2022.text = cap7?.v53num22b?.toString()?.toEditable() ?: blank
            txtCap753C2021.text = cap7?.v53num21c?.toString()?.toEditable() ?: blank
            txtCap753C2022.text = cap7?.v53num22c?.toString()?.toEditable() ?: blank
            txtCap753D2021.text = cap7?.v53num21d?.toString()?.toEditable() ?: blank
            txtCap753D2022.text = cap7?.v53num22d?.toString()?.toEditable() ?: blank
            txtCap753E2021.text = cap7?.v53num21e?.toString()?.toEditable() ?: blank
            txtCap753E2022.text = cap7?.v53num22e?.toString()?.toEditable() ?: blank
            txtCap753F2021.text = cap7?.v53num21f?.toString()?.toEditable() ?: blank
            txtCap753F2022.text = cap7?.v53num22f?.toString()?.toEditable() ?: blank
            txtCap753G2021.text = cap7?.v53num21g?.toString()?.toEditable() ?: blank
            txtCap753G2022.text = cap7?.v53num22g?.toString()?.toEditable() ?: blank
            txtCap753GOtro.text = cap7?.v53txtgdesc?.toEditable() ?: blank

            txtCap753T2021.text = cap7?.v53num1T21?.toString()?.toEditable() ?: blank
            txtCap753T2022.text = cap7?.v53num1T22?.toString()?.toEditable() ?: blank
        }
    }



    fun saveCap() {
        with (bindingcap7o2) {
            Mob.cap7 = ModelCap7(

                Mob.cap7?.id,
                Mob.cap7?.ncontrol,
                Mob.cap7?.v50check,
                Mob.cap7?.v51check21o1,
                Mob.cap7?.v51check21o2,
                Mob.cap7?.v51num21o1,
                Mob.cap7?.v51num21o2,
                Mob.cap7?.v51check22o1,
                Mob.cap7?.v51check22o2,
                Mob.cap7?.v51num22o1,
                Mob.cap7?.v51num22o2,
                Mob.cap7?.v52txt01,
                Mob.cap7?.v52txt02,
                Mob.cap7?.v52txt03,
                Mob.cap7?.v52txt04,
                Mob.cap7?.v52txt05,
                Mob.cap7?.v52txt06,
                Mob.cap7?.v52txt07,
                Mob.cap7?.v52txt08,
                Mob.cap7?.v52txt09,
                Mob.cap7?.v52txt10,
                Mob.cap7?.v52txt11,
                Mob.cap7?.v52txt12,
                Mob.cap7?.v52txt13,
                Mob.cap7?.v52txt14,
                Mob.cap7?.v52txt15desc,
                Mob.cap7?.v52txt15,
                Mob.cap7?.v53num21a,
                Mob.cap7?.v53num21b,
                Mob.cap7?.v53num21c,
                Mob.cap7?.v53num21d,
                Mob.cap7?.v53num21e,
                Mob.cap7?.v53num21f,
                Mob.cap7?.v53num21g,
                Mob.cap7?.v53num1T21,
                Mob.cap7?.v53num22a,
                Mob.cap7?.v53num22b,
                Mob.cap7?.v53num22c,
                Mob.cap7?.v53num22d,
                Mob.cap7?.v53num22e,
                Mob.cap7?.v53num22f,
                Mob.cap7?.v53num22g,
                Mob.cap7?.v53num1T22,
                Mob.cap7?.v53txtgdesc,
                Mob.cap7?.v54txt01,
                Mob.cap7?.v54txt02,
                Mob.cap7?.v54txt03,
                Mob.cap7?.v54txt04,
                Mob.cap7?.v54txt05,
                Mob.cap7?.v54txt06,
                Mob.cap7?.v54txt07,
                Mob.cap7?.v54txt08,
                Mob.cap7?.v54txt09,
                Mob.cap7?.v54txt10,
                Mob.cap7?.v54txt11,
                Mob.cap7?.v54txt12,
                Mob.cap7?.v54txt13,
                Mob.cap7?.v54txt14,
                Mob.cap7?.v54txt15,
                Mob.cap7?.v54txt15desc,
                Mob.cap7?.v55txt1a,
                Mob.cap7?.v55txt1b,
                Mob.cap7?.v55txt1c,
                Mob.cap7?.v55txt2a,
                Mob.cap7?.v55txt2b,
                Mob.cap7?.v55txt2c,
                Mob.cap7?.v55txt2d,
                Mob.cap7?.v55txt3a,
                Mob.cap7?.v55txt3b,
                Mob.cap7?.v55txt4a,
                Mob.cap7?.v55txt4b,
                Mob.cap7?.v55txt4c,

                )
        }
        println("----------${Mob.cap7}")
    }

}