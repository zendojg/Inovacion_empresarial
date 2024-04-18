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
    //------- Valores de conexión
    private const val URLINEC = "https://www.inec.gob.pa/"
    //private const val URLlocal = "http://172.16.9.27:5552/"
    private const val URLapi = "inno-api/api"
    //private const val URLapi2 = "api"
    var URL = URLINEC
    //-------
    const val APIAUTH = "${URLapi}/Auth/login"
    const val APIPROV = "${URLapi}/Catalogo/prov"
    const val APIDIST = "${URLapi}/Catalogo/dist"
    const val APICORRE = "${URLapi}/Catalogo/corr"
    const val APILUGARP = "${URLapi}/Catalogo/lug"
    const val APIGETFORM = "${URLapi}/Cuestionario/full/"
    const val APIUPDATEFORM = "${URLapi}/Cuestionario/update"
    const val APIFORMUSER = "${URLapi}/Cuestionario/enc/"
    const val APIINCONSISTENCIAS = "${URLapi}/Inconsistencia/"

    const val APIFORMSUPER = "${URLapi}/Supervisor/all/"
    const val APIINCONSUPER = "${URLapi}/Supervisor/inc/"

    //------- Valores de UI
    const val USERTEST:String = "USUARIO SIN INDENTIFICADOR"
    const val DATEFORMAT:String = "yyyy-MM-dd HH:mm:ss"
    const val DATEFORMATFORDOC:String = "yyyy-MM-dd__HH.mm.ss"
    const val VERSION:Int = 26
    //-------
    const val PORCENT100:Int = 100
    const val LENGHT_MSG:Int = 50
    const val SIZE_AUTOCONTROL:Int = 7
    const val CERO_COUNTLEFT:Int = 4
    const val MAX_TEXWATCHER_4ROWS:Int = 6
    const val MAX_TEXWATCHER_MANY_ROWS:Int = 15
    const val MIN_LENGHTTEL: Int = 7
    const val CODE_SUP:String = "S"
    const val CODE_EMP:String = "E"

    //-------
    const val JUMP_MODULE1:Int = 4
    const val INITIAL_YEAR:Int = 1900
    //-------
    const val CHECK1Y2021:Int = 0
    const val CHECK1Y2022:Int = 1
    const val CHECK2Y2021:Int = 2
    const val CHECK2Y2022:Int = 3
    const val CHECK3Y2021:Int = 4
    const val CHECK3Y2022:Int = 5
    const val CHECK4Y2021:Int = 6
    const val CHECK4Y2022:Int = 7

    //------- SIZES FOR WIDTH
    const val WIDTH160DP:Int = 160
    const val WIDTH180DP:Int = 180
    const val WIDTH220DP:Int = 220

    //------- TIMES
    const val TIMELONG2SEG:Long = 2000L
    const val TIMELONG4SEG:Long = 4000L
    const val TIME10MS:Long = 10
    const val TIME100MS:Long = 100
    const val TIME360MS:Long = 360
    const val TIME500MS:Long = 500
    const val TIME800MS:Long = 800
    const val TIME1S:Long = 1000
    const val TIME2S:Long = 2000

    //------- CODE RESPONSE
    const val CODE200:Int = 200
    const val CODE400:Int = 400
    const val CODE401:Int = 401
    const val CODE403:Int = 403
    const val CODE404:Int = 404
    const val CODE500:Int = 500

    //------- INDICE PAGER DE MAINACTIVITY
    const val LOGIN0:Int = 0
    const val INIT01:Int = 1
    const val PAGE02:Int = 2
    const val PAGE03:Int = 3
    const val PAGE04:Int = 4

    //------- INDICE PAGER DE FORMACTIVITY
    const val MENU_P00:Int = 0
    const val CAP1_P01:Int = 1
    const val CAP2_P02:Int = 2
    const val CAP2_P03:Int = 3
    const val CAP3_P04:Int = 4
    const val CAP4_P05:Int = 5
    const val CAP5_P06:Int = 6
    const val CAP5_P07:Int = 7
    const val CAP6_P08:Int = 8
    const val CAP6_P09:Int = 9
    const val CAP6_P10:Int = 10
    const val CAP6_P11:Int = 11
    const val CAP7_P12:Int = 12
    const val CAP7_P13:Int = 13
    const val CAP7_P14:Int = 14
    const val CAP8_P15:Int = 15
    const val CAP8_P16:Int = 16
    const val CAP9_P17:Int = 17
    const val CAP9_P18:Int = 18
    const val CAPX_P19:Int = 19
    const val SEC1_P20:Int = 20
    const val SEC2_P21:Int = 21
    const val SEC3_P22:Int = 22
    const val SEC4_P23:Int = 23
    const val OBSE_P24:Int = 24

    val titleMapTxt: Map<Int, Triple<Int, Int, Int>> = mapOf(
        CAP1_P01 to Triple(R.string.cap01, R.string.ctxt01, R.string.subcap000),
        CAP2_P02 to Triple(R.string.cap02, R.string.ctxt02, R.string.subcap021),
        CAP2_P03 to Triple(R.string.cap02, R.string.ctxt02, R.string.subcap022),
        CAP3_P04 to Triple(R.string.cap03, R.string.ctxt03, R.string.subcap000),
        CAP4_P05 to Triple(R.string.cap04, R.string.ctxt04, R.string.subcap000),
        CAP5_P06 to Triple(R.string.cap05, R.string.ctxt05, R.string.subcap051),
        CAP5_P07 to Triple(R.string.cap05, R.string.ctxt05, R.string.subcap052),
        CAP6_P08 to Triple(R.string.cap06, R.string.ctxt06, R.string.subcap061),
        CAP6_P09 to Triple(R.string.cap06, R.string.ctxt06, R.string.subcap062),
        CAP6_P10 to Triple(R.string.cap06, R.string.ctxt06, R.string.subcap063),
        CAP6_P11 to Triple(R.string.cap06, R.string.ctxt06, R.string.subcap064),
        CAP7_P12 to Triple(R.string.cap07, R.string.ctxt07, R.string.subcap001),
        CAP7_P13 to Triple(R.string.cap07, R.string.ctxt07, R.string.subcap002),
        CAP7_P14 to Triple(R.string.cap07, R.string.ctxt07, R.string.subcap003),
        CAP8_P15 to Triple(R.string.cap08, R.string.ctxt08, R.string.subcap001),
        CAP8_P16 to Triple(R.string.cap08, R.string.ctxt08, R.string.subcap002),
        CAP9_P17 to Triple(R.string.cap09, R.string.ctxt09, R.string.subcap001),
        CAP9_P18 to Triple(R.string.cap09, R.string.ctxt09, R.string.subcap002),
        CAPX_P19 to Triple(R.string.cap10, R.string.ctxt10, R.string.subcap000),
        SEC1_P20 to Triple(R.string.secc01, R.string.stxt01, R.string.subcap000),
        SEC2_P21 to Triple(R.string.secc02, R.string.stxt02, R.string.subcap000),
        SEC3_P22 to Triple(R.string.secc03, R.string.stxt03, R.string.subcap000),
        SEC4_P23 to Triple(R.string.secc04, R.string.stxt04, R.string.subcap000),
        OBSE_P24 to Triple(R.string.informe, R.string.informetxt, R.string.subcap000)
    )

    val menuToIndexMap = mapOf(
        R.id.menu_cap1 to CAP1_P01,
        R.id.menu_cap2 to CAP2_P02,
        R.id.menu_cap3 to CAP3_P04,
        R.id.menu_cap4 to CAP4_P05,
        R.id.menu_cap5 to CAP5_P06,
        R.id.menu_cap6 to CAP6_P08,
        R.id.menu_cap7 to CAP7_P12,
        R.id.menu_cap8 to CAP8_P15,
        R.id.menu_cap9 to CAP9_P17,
        R.id.menu_capx to CAPX_P19,
        R.id.menu_mod1 to SEC1_P20,
        R.id.menu_mod2 to SEC2_P21,
        R.id.menu_mod3 to SEC3_P22,
        R.id.menu_mod4 to SEC4_P23,
        R.id.nav_form to OBSE_P24
    )

    //------- Contenedor de los Fragment del FormActivity
    val arrEncuestas: Array<Fragment> = arrayOf(
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

    //------- Contenedor de los Fragment del MainActivity
    val arrMain: Array<Fragment> = arrayOf(
        MainFragmentLogin(),    //--00--c01
        MainFragmentSearch(),   //--01--c02
        MainFragmentData(),     //--02--c03
        MainFragmentForms()     //--03--c04
    )

    //------- Lista de las Condiciones
    const val CONDICION_02:Int = 2
    const val CONDICION_04:Int = 4
    const val CONDICION_08:Int = 8
    val arrCondicion: Array<String> = arrayOf(
        "Incompleta",
        "Completa",
        "Consolidada (Especifique)",
        "Cerró y no fue reemplazada",
        "Cerró y fue reemplazada (Especifique)",
        "Rehúsa",
        "No localizada",
        "No operó",
        "Otra condición (Especifique)"
    )

    val arrCond: Array<String> = arrayOf(
        "incompleta",
        "completa",
        "consolidada",
        "cerro y no fue reemplazada",
        "cerro y fue reemplazada",
        "rehusa",
        "no localizada",
        "no opero",
        "otra condicion"
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

    val paises = arrayOf(
        "Afganistán", "Albania", "Alemania", "Andorra", "Angola", "Antigua y Barbuda",
        "Arabia Saudita", "Argelia", "Argentina", "Armenia", "Australia", "Austria",
        "Azerbaiyán", "Bahamas", "Bangladés", "Barbados", "Baréin", "Bélgica", "Belice",
        "Benín", "Bielorrusia", "Birmania", "Bolivia", "Bosnia y Herzegovina", "Botsuana",
        "Brasil", "Brunéi", "Bulgaria", "Burkina Faso", "Burundi", "Bután", "Cabo Verde",
        "Camboya", "Camerún", "Canadá", "Catar", "Chad", "Chile", "China", "Chipre",
        "Ciudad del Vaticano", "Colombia", "Comoras", "Corea del Norte", "Corea del Sur",
        "Costa de Marfil", "Costa Rica", "Croacia", "Cuba", "Dinamarca", "Dominica",
        "Ecuador", "Egipto", "El Salvador", "Emiratos Árabes Unidos", "Eritrea", "Eslovaquia",
        "Eslovenia", "España", "Estados Unidos", "Estonia", "Etiopía", "Filipinas", "Finlandia",
        "Fiyi", "Francia", "Gabón", "Gambia", "Georgia", "Ghana", "Granada", "Grecia", "Guatemala",
        "Guyana", "Guinea", "Guinea ecuatorial", "Guinea-Bisáu", "Haití", "Honduras", "Hungría",
        "India", "Indonesia", "Irak", "Irán", "Irlanda", "Islandia", "Islas Marshall", "Islas Salomón",
        "Israel", "Italia", "Jamaica", "Japón", "Jordania", "Kazajistán", "Kenia", "Kirguistán",
        "Kiribati", "Kuwait", "Laos", "Lesoto", "Letonia", "Líbano", "Liberia", "Libia", "Liechtenstein",
        "Lituania", "Luxemburgo", "Madagascar", "Malasia", "Malaui", "Maldivas", "Malí", "Malta",
        "Marruecos", "Mauricio", "Mauritania", "México", "Micronesia", "Moldavia", "Mónaco", "Mongolia",
        "Montenegro", "Mozambique", "Namibia", "Nauru", "Nepal", "Nicaragua", "Níger", "Nigeria",
        "Noruega", "Nueva Zelanda", "Omán", "Países Bajos", "Pakistán", "Palaos", "Panamá",
        "Papúa Nueva Guinea", "Paraguay", "Perú", "Polonia", "Portugal", "Reino Unido", "República Centroafricana",
        "República Checa", "República del Congo", "República Democrática del Congo", "República Dominicana",
        "República Sudafricana", "Ruanda", "Rumania", "Rusia", "Samoa", "San Cristóbal y Nieves",
        "San Marino", "San Vicente y las Granadinas", "Santa Lucía", "Santo Tomé y Príncipe", "Senegal",
        "Serbia", "Seychelles", "Sierra Leona", "Singapur", "Siria", "Somalia", "Sri Lanka", "Suazilandia",
        "Sudán", "Sudán del Sur", "Suecia", "Suiza", "Surinam", "Tailandia", "Tanzania", "Tayikistán",
        "Timor Oriental", "Togo", "Tonga", "Trinidad y Tobago", "Túnez", "Turkmenistán", "Turquía",
        "Tuvalu", "Ucrania", "Uganda", "Uruguay", "Uzbekistán", "Vanuatu", "Venezuela", "Vietnam",
        "Yemen", "Yibuti", "Zambia", "Zimbabue"
    )

    //------ Variables del formulario ------
    var pass: String? = "eie2122"   //---- Password para los accesos a delete y update
    var mainWindow = 1              //-----Indice del Main
    var mainPrevWindow = 0
    var indiceFormulario = 0        //-----Indice de la encuesta dado desde el Menú
    var obsEncuesta: String? = ""   //-----Observaciones de la encuesta
    var obsModulo: String? = ""     //-----Observaciones del Módulo
    var version: String = "0.0"
    var condicionID: String? = null
    var viewIncon: Boolean = false
    var inconsArray: Array<String>? = null


    //----- Response de Token
    private var authToken: ModelAuthResult? = null
    var authData: ModelAuth? = ModelAuth(
        result = authToken,
        infoMsg = null,
        name = null,
        user = null,
        rol = null
    )

    //------  FORMULARIO
    data class InternalInfo(
        val indexCap: Int,
        var capView: Boolean,
        var incons: Boolean
    )
    var infoCap: MutableList<InternalInfo> = mutableListOf()
    init {
        for (capitulo in MENU_P00 until OBSE_P24) {
            infoCap.add(InternalInfo(
                indexCap = capitulo,
                capView = false,
                incons = false))
        }
    }

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
