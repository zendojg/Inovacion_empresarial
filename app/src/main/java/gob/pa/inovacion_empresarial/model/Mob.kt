package gob.pa.inovacion_empresarial.model

import androidx.fragment.app.Fragment
import gob.pa.inovacion_empresarial.view.fragments.*

object Mob {
    const val URL = "http://172.16.9.27:5552/"          // urlLocal

    const val APIAUTH = "api/Auth/login"
    const val APIPROV = "api/Catalogo/prov"
    const val APIDIST = "api/Catalogo/dist"
    const val APICORRE = "api/Catalogo/corr"
    const val APILUGARP = "api/Catalogo/lug"
    const val APIGETFORM = "api/Cuestionario/full/"

    const val DATEFORMAT = "yyyy-MM-dd HH:mm:ss a"

    const val PORCENT100:Int = 100

    const val WIDTH160DP:Int = 160
    const val WIDTH180DP:Int = 180
    const val WIDTH220DP:Int = 220

    const val TIMELONG2SEG:Long = 2000L
    const val TIMELONG4SEG:Long = 4000L
    const val TIMELONG6SEG:Long = 6000L

    const val TIME10MS:Long = 10
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


    var arrEncuestas: Array<Fragment> = arrayOf(    //----Contenedor de los Fragment del Form
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

    var arrMain: Array<Fragment> = arrayOf( //----Contenedor de los Fragment del Main
        MainFragmentLogin(),    //--00--c01
        MainFragmentSearch(),   //--01--c02
    )

    var empArr: Array<Fragment> = emptyArray()

    const val GRADENOTSELECT:Int = 0
    val arrGrade: Array<String> = arrayOf(
        "Grado de importancia",
        "(1) Alta",
        "(2) Media",
        "(3) Baja",
        "(4) Irrelevante"
    )
    const val SOLICITUDNOTSELECT:Int = 0
    const val SOLICSI2:Int = 2
    val arrOBT: Array<String> = arrayOf(
        "Solicitó apoyo?",
        "(1) Sí solicitó, pero no obtuvo",
        "(2) Sí solicitó, si lo obtuvo",
        "(3) No solicitó apoyo"
    )
    const val IMPORTANCIANOTSELECT:Int = 0
    val arrImp: Array<String> = arrayOf("*","1","2","3","4","5")

    var p56stat: Boolean? = null

    var moduleContinue: Boolean? = null

    var windNow = 1
    var windPrev = 0
    var indiceEnc = 0               //-----Indice de la encuesta dado desde el Menú
    var obsEncuesta = ""            //-----Observaciones de la encuesta
    var obsModulo = ""              //-----Observaciones del Módulo
    var obsTittle = ""              //-----Muestra el título si es Encuesta o Módulo
    var version = "0.0"


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
        infoMsg = null
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

    var formComp: ModelForm? = ModelForm(
        ncontrol = null,
        obs = null,
        cond = null,
        act = null,
        rev = null,
        dateCreate = null,
        dateMod = null,
        dateModSup = null,
        modSup = null,
        creator = null,
        mod = null,
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
