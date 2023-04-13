package gob.pa.inovacion_empresarial.model

import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.view.fragments.*

class Mobject {

    companion object {

        var arrBlank: Array<Fragment> = emptyArray()
        var arrEncuestas: Array<Fragment> = arrayOf(
            FragEncuestaCap01(),    //--00--c01
            FragEncuestaCap02o1(),  //--01--c02
            FragEncuestaCap02o2(),  //--02
            FragEncuestaCap03(),    //--03--c03
            FragEncuestaCap04(),    //--04--c04
            FragEncuestaCap05o1(),  //--05--c05
            FragEncuestaCap05o2(),  //--06
            FragEncuestaCap06o1(),  //--07--c06
            FragEncuestaCap06o2(),  //--08
            FragEncuestaCap06o3(),  //--09
            FragEncuestaCap06o4(),  //--10
            FragEncuestaCap07o1(),  //--11--c07
            FragEncuestaCap07o2(),  //--12
            FragEncuestaCap07o3(),  //--13
            FragEncuestaCap08(),    //--14--c08
            FragEncuestaCap09o1(),  //--15--c09
            FragEncuestaCap09o2(),  //--16
            FragEncuestaCap10(),    //--17--c10
            FragModuloSecc01(),     //--18--s01
            FragModuloSecc02(),     //--19--s02
            FragModuloSecc03(),     //--20--s03
            FragModuloSecc04(),     //--21--s04
            FragTotalInforme()      //--22--Informe
        )

        var obsEncuesta = ""
        var obsModulo = ""
        var obsTittle = ""

        var idprov = ""
        var iddist = ""
        var idcorre = ""
        var idlugarp = ""
    }
}