package gob.pa.inovacion_empresarial.model

import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.R
import gob.pa.inovacion_empresarial.view.fragments.FragEncuestaCap01
import gob.pa.inovacion_empresarial.view.fragments.FragEncuestaCap02o1
import gob.pa.inovacion_empresarial.view.fragments.FragEncuestaCap02o2
import gob.pa.inovacion_empresarial.view.fragments.FragEncuestaCap03
import gob.pa.inovacion_empresarial.view.fragments.FragEncuestaCap04
import gob.pa.inovacion_empresarial.view.fragments.FragEncuestaCap05o1
import gob.pa.inovacion_empresarial.view.fragments.FragEncuestaCap05o2
import gob.pa.inovacion_empresarial.view.fragments.FragEncuestaCap06o1
import gob.pa.inovacion_empresarial.view.fragments.FragEncuestaCap06o2
import gob.pa.inovacion_empresarial.view.fragments.FragEncuestaCap06o3
import gob.pa.inovacion_empresarial.view.fragments.FragEncuestaCap06o4
import gob.pa.inovacion_empresarial.view.fragments.FragEncuestaCap07o1
import gob.pa.inovacion_empresarial.view.fragments.FragEncuestaCap07o2
import gob.pa.inovacion_empresarial.view.fragments.FragEncuestaCap07o3
import gob.pa.inovacion_empresarial.view.fragments.FragEncuestaCap08
import gob.pa.inovacion_empresarial.view.fragments.FragEncuestaCap08end
import gob.pa.inovacion_empresarial.view.fragments.FragEncuestaCap09o1
import gob.pa.inovacion_empresarial.view.fragments.FragEncuestaCap09o2
import gob.pa.inovacion_empresarial.view.fragments.FragEncuestaCap10
import gob.pa.inovacion_empresarial.view.fragments.FragMenu
import gob.pa.inovacion_empresarial.view.fragments.FragModuloSecc01
import gob.pa.inovacion_empresarial.view.fragments.FragModuloSecc02
import gob.pa.inovacion_empresarial.view.fragments.FragModuloSecc03
import gob.pa.inovacion_empresarial.view.fragments.FragModuloSecc04
import gob.pa.inovacion_empresarial.view.fragments.FragTotalInforme
import gob.pa.inovacion_empresarial.view.fragments.MainFragmentData
import gob.pa.inovacion_empresarial.view.fragments.MainFragmentForms
import gob.pa.inovacion_empresarial.view.fragments.MainFragmentLogin
import gob.pa.inovacion_empresarial.view.fragments.MainFragmentSearch

object Mob {
    const val URL = "http://172.16.9.27:5552/"          // urlLocal

    const val APIAUTH = "api/Auth/login"
    const val APIPROV = "api/Catalogo/prov"
    const val APIDIST = "api/Catalogo/dist"
    const val APICORRE = "api/Catalogo/corr"
    const val APILUGARP = "api/Catalogo/lug"
    const val APIGETFORM = "api/Cuestionario/full/"
    const val APIUPDATEFORM = "api/Cuestionario/update"
    const val APIFORMUSER = "api/Cuestionario/enc/"

    const val USERTEST:String = "USUARIO SIN INDENTIFICADOR"
    const val DATEFORMAT:String = "yyyy-MM-dd HH:mm:ss a"
    const val PORCENT100:Int = 100
    const val LIMITMSG:Int = 50
    const val SIZEAUTOCONTROL:Int = 7
    const val FOR5DIGITS:Int = 4


    const val CHECK1Y2021:Int = 0
    const val CHECK1Y2022:Int = 1
    const val CHECK2Y2021:Int = 2
    const val CHECK2Y2022:Int = 3
    const val CHECK3Y2021:Int = 4
    const val CHECK3Y2022:Int = 5
    const val CHECK4Y2021:Int = 6
    const val CHECK4Y2022:Int = 7

    const val MAXTEXWATCHER4ROWS:Int = 6
    const val MAXTEXWATCHERMANYROWS:Int = 9

    const val JUMPMODULE1:Int = 4

    const val INITIALYEAR:Int = 1900
    const val WIDTH160DP:Int = 160
    const val WIDTH180DP:Int = 180
    const val WIDTH220DP:Int = 220

    const val TIMELONG2SEG:Long = 2000L
    const val TIMELONG4SEG:Long = 4000L
    const val TIMELONG6SEG:Long = 6000L

    const val TIME10MS:Long = 10
    const val TIME100MS:Long = 100
    const val TIME360MS:Long = 360
    const val TIME500MS:Long = 500
    const val TIME800MS:Long = 800
    const val TIME1S:Long = 1000
    const val TIME2S:Long = 2000

    const val CODE200:Int = 200
    const val CODE400:Int = 400
    const val CODE401:Int = 401
    const val CODE403:Int = 403
    const val CODE404:Int = 404
    const val CODE500:Int = 500

    const val LOGIN0:Int = 0
    const val INIT01:Int = 1
    const val PAGE02:Int = 2
    const val PAGE03:Int = 3
    const val PAGE04:Int = 4

    const val MENUP00:Int = 0
    const val CAP1P01:Int = 1
    const val CAP2P02:Int = 2
    const val CAP2P03:Int = 3
    const val CAP3P04:Int = 4
    const val CAP4P05:Int = 5
    const val CAP5P06:Int = 6
    const val CAP5P07:Int = 7
    const val CAP6P08:Int = 8
    const val CAP6P09:Int = 9
    const val CAP6P10:Int = 10
    const val CAP6P11:Int = 11
    const val CAP7P12:Int = 12
    const val CAP7P13:Int = 13
    const val CAP7P14:Int = 14
    const val CAP8P15:Int = 15
    const val CAP8P16:Int = 16
    const val CAP9P17:Int = 17
    const val CAP9P18:Int = 18
    const val CAPXP19:Int = 19
    const val SEC1P20:Int = 20
    const val SEC2P21:Int = 21
    const val SEC3P22:Int = 22
    const val SEC4P23:Int = 23
    const val OBSP24:Int = 24

    val titleMapTxt = mapOf(
        CAP1P01 to Triple(R.string.cap01, R.string.ctxt01, R.string.subcap000 ),
        CAP2P02 to Triple(R.string.cap02, R.string.ctxt02, R.string.subcap021),
        CAP2P03 to Triple(R.string.cap02, R.string.ctxt02, R.string.subcap022),
        CAP3P04 to Triple(R.string.cap03, R.string.ctxt03, R.string.subcap000),
        CAP4P05 to Triple(R.string.cap04, R.string.ctxt04, R.string.subcap000),
        CAP5P06 to Triple(R.string.cap05, R.string.ctxt05, R.string.subcap051),
        CAP5P07 to Triple(R.string.cap05, R.string.ctxt05, R.string.subcap052),
        CAP6P08 to Triple(R.string.cap06, R.string.ctxt06, R.string.subcap061),
        CAP6P09 to Triple(R.string.cap06, R.string.ctxt06, R.string.subcap062),
        CAP6P10 to Triple(R.string.cap06, R.string.ctxt06, R.string.subcap063),
        CAP6P11 to Triple(R.string.cap06, R.string.ctxt06, R.string.subcap064),
        CAP7P12 to Triple(R.string.cap07, R.string.ctxt07, R.string.subcap001),
        CAP7P13 to Triple(R.string.cap07, R.string.ctxt07, R.string.subcap002),
        CAP7P14 to Triple(R.string.cap07, R.string.ctxt07, R.string.subcap003),
        CAP8P15 to Triple(R.string.cap08, R.string.ctxt08, R.string.subcap001),
        CAP8P16 to Triple(R.string.cap08, R.string.ctxt08, R.string.subcap002),
        CAP9P17 to Triple(R.string.cap09, R.string.ctxt09, R.string.subcap001),
        CAP9P18 to Triple(R.string.cap09, R.string.ctxt09, R.string.subcap002),
        CAPXP19 to Triple(R.string.cap10, R.string.ctxt10, R.string.subcap000),
        SEC1P20 to Triple(R.string.secc01, R.string.stxt01, R.string.subcap000),
        SEC2P21 to Triple(R.string.secc02, R.string.stxt02, R.string.subcap000),
        SEC3P22 to Triple(R.string.secc03, R.string.stxt03, R.string.subcap000),
        SEC4P23 to Triple(R.string.secc04, R.string.stxt04, R.string.subcap000),
        OBSP24 to Triple(R.string.informe, R.string.informetxt, R.string.subcap000)
    )

    val menuToIndexMap = mapOf(
        R.id.menu_cap1 to CAP1P01,
        R.id.menu_cap2 to CAP2P02,
        R.id.menu_cap3 to CAP3P04,
        R.id.menu_cap4 to CAP4P05,
        R.id.menu_cap5 to CAP5P06,
        R.id.menu_cap6 to CAP6P08,
        R.id.menu_cap7 to CAP7P12,
        R.id.menu_cap8 to CAP8P15,
        R.id.menu_cap9 to CAP9P17,
        R.id.menu_capx to CAPXP19,
        R.id.menu_mod1 to SEC1P20,
        R.id.menu_mod2 to SEC2P21,
        R.id.menu_mod3 to SEC3P22,
        R.id.menu_mod4 to SEC4P23,
        R.id.nav_form to OBSP24
    )

    val arrEncuestas: Array<Fragment> = arrayOf(    //----Contenedor de los Fragment del Form
        FragMenu(),             //--00--Menu
        FragEncuestaCap01(),    //--01--c01 --- Encuesta
        FragEncuestaCap02o1(),  //--02--c02
        FragEncuestaCap02o2(),  //--03
        FragEncuestaCap03(),    //--04--c03
        FragEncuestaCap04(),    //--05--c04
        FragEncuestaCap05o1(),  //--06--c05
        FragEncuestaCap05o2(),  //--07
        FragEncuestaCap06o1(),  //--08--c06
        FragEncuestaCap06o2(),  //--09
        FragEncuestaCap06o3(),  //--10
        FragEncuestaCap06o4(),  //--11
        FragEncuestaCap07o1(),  //--12--c07
        FragEncuestaCap07o2(),  //--13
        FragEncuestaCap07o3(),  //--14
        FragEncuestaCap08(),    //--15--c08
        FragEncuestaCap08end(), //--16
        FragEncuestaCap09o1(),  //--17--c09
        FragEncuestaCap09o2(),  //--18
        FragEncuestaCap10(),    //--19--c10 --- Encuesta
        FragModuloSecc01(),     //--20--s01 --- Modulo
        FragModuloSecc02(),     //--21--s02
        FragModuloSecc03(),     //--22--s03
        FragModuloSecc04(),     //--23--s04 --- Modulo
        FragTotalInforme()      //--24--Informe
    )

    val arrMain: Array<Fragment> = arrayOf( //----Contenedor de los Fragment del Main
        MainFragmentLogin(),    //--00--c01
        MainFragmentSearch(),   //--01--c02
        MainFragmentData(),     //--02--c03
        MainFragmentForms()     //--03--c04
    )

    val empArr: Array<Fragment> = emptyArray()

    const val CONDICION02:Int = 1
    const val CONDICION04:Int = 3
    const val CONDICION08:Int = 7
    val arrCondicion: Array<String> = arrayOf(
        "Completa",
        "Consolidada (Especifique)",
        "Cerró y no fue reemplazada",
        "Cerró y fue reemplazada (Especifique)",
        "Rehúsa",
        "No localizada",
        "No operó",
        "Otra condición (Especifique)"
    )

    const val GRADENOTSELECT:Int = 0
    val arrGrade: Array<String> = arrayOf(
        "Grado de importancia",
        "(1) Alta",
        "(2) Media",
        "(3) Baja",
        "(4) Irrelevante"
    )
    const val SOLICITUDNOTSELECT:Int = 0
    const val SOLICITUDYES2:Int = 2
    val arrOBT: Array<String> = arrayOf(
        "Solicitó apoyo?",
        "(1) Sí solicitó, pero no obtuvo",
        "(2) Sí solicitó, si lo obtuvo",
        "(3) No solicitó apoyo"
    )
    const val IMPORTANCIANOTSELECT:Int = 0
    val arrImp: Array<String> = arrayOf("*","1","2","3","4","5")

    var pass: String? = "12345"

    var seecap01: Boolean = true
    var seecap02o1: Boolean = true
    var seecap02o2: Boolean = true
    var seecap03: Boolean = true
    var seecap04: Boolean = true
    var seecap05o1: Boolean = true
    var seecap05o2: Boolean = true
    var seecap0601: Boolean = true
    var seecap06o2: Boolean = true
    var seecap06o3: Boolean = true
    var seecap06o4: Boolean = true
    var seecap07o1: Boolean = true
    var seecap07o2: Boolean = true
    var seecap07o3: Boolean = true
    var seecap08o1: Boolean = true
    var seecap08o2: Boolean = true
    var seecap09o1: Boolean = true
    var seecap09o2: Boolean = true
    var seecap10: Boolean = true

    var seesecc1: Boolean = true
    var seesecc2: Boolean = true
    var seesecc3: Boolean = true
    var seesecc4: Boolean = true


    var icap01: Boolean = true
    var icap02o1: Boolean = true
    var icap02o2: Boolean = true
    var icap03: Boolean = true
    var icap04: Boolean = true
    var icap05o1: Boolean = true
    var icap05o2: Boolean = true
    var icap0601: Boolean = true
    var icap06o2: Boolean = true
    var icap06o3: Boolean = true
    var icap06o4: Boolean = true
    var icap07o1: Boolean = true
    var icap07o2: Boolean = true
    var icap07o3: Boolean = true
    var icap08o1: Boolean = true
    var icap08o2: Boolean = true
    var icap09o1: Boolean = true
    var icap09o2: Boolean = true
    var icap10: Boolean = true

    var isecc1: Boolean = true
    var isecc2: Boolean = true
    var isecc3: Boolean = true
    var isecc4: Boolean = true

    var mainWindow = 1
    var mainPrevWindow = 0
    var indiceFormulario = 0        //-----Indice de la encuesta dado desde el Menú
    var obsEncuesta: String? = ""   //-----Observaciones de la encuesta
    var obsModulo: String? = ""     //-----Observaciones del Módulo
    var obsTittle: String = ""      //-----Muestra el título si es Encuesta o Módulo
    var version: String = "0.0"
    var condicionID: String? = null
    var sendForm: Boolean = false


    private var authInfo: ModelAuthTokenInfo? = ModelAuthTokenInfo(
        name = null,
        tokenS = null,
        expire = null
    )
    private var authToken: ModelAuthResult? = ModelAuthResult(
        token = null,
        infotoken = authInfo
    )
    var authData: ModelAuth? = ModelAuth(
        result = authToken,
        infoMsg = null,
        name = null,
        user = null,
        rol = null
    )

    var authResp: ModelAuthResp? = ModelAuthResp(
        code = null,
        msg = null,
        body = null
    )

    //--  FORMULARIO
    var cap1: ModelCap1? = null
    var cap2: ModelCap2? = null
    var cap3: ModelCap3? = null
    var cap4: ModelCap4? = null
    var cap5: ModelCap5? = null
    var cap6: ModelCap6? = null
    var cap7: ModelCap7? = null
    var cap8: ModelCap8? = null
    var cap9: ModelCap9? = null
    var capx: ModelCap10? = null
    var capMod: ModelMod? = null

    var condicion: ModelCondicion? = null

    var formComp: ModelForm? = ModelForm(
        ncontrol = null,
        obs = null,
        cond = null,
        act = null,
        rev = null,
        tieneIncon = null,
        dateCreate = null,
        dateMod = null,
        dateModSup = null,
        modSup = null,
        creator = null,
        mod = null,
        condicion = condicion,
        cap1 = cap1,
        cap2 = cap2,
        cap3 = cap3,
        cap4 = cap4,
        cap5 = cap5,
        cap6 = cap6,
        cap7 = cap7,
        cap8 = cap8,
        cap9 = cap9,
        capx = capx,
        capMod = capMod
    )
}
