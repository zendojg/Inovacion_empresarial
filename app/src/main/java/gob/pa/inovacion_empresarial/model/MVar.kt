package gob.pa.inovacion_empresarial.model

import androidx.fragment.app.Fragment
import com.google.gson.annotations.SerializedName
import gob.pa.inovacion_empresarial.view.fragments.*

class MVar {

    companion object {
        const val url = "http://172.16.9.27:5552/"          // urlLocal
//        const val url = "https://www.inec.gob.pa/"        // urlInec
//        const val url = "https://www.censospanama.pa/"    // urlProd

        const val apiAut = "api/Auth/login"
        const val apiProv = "api/Catalogo/prov"
        const val apiDist = "api/Catalogo/dist"
        const val apiCorre = "api/Catalogo/corr"
        const val apiLugarP = "api/Catalogo/lug"

        const val apiLogin = "api/Auth/login"
        const val apiGetForm = "api/Cuestionario/full/"
        const val apiSendForm = "api/cuestionarios"

        const val dateFormat = "yyyy-MM-dd HH:mm:ss a"

        var passdelete = "12345"
        var windNow = 1
        var windPrev = 0
        var indiceEnc = 0               //-----Indice de la encuesta dado desde el Menú
        var obsEncuesta = ""            //-----Observaciones de la encuesta
        var obsModulo = ""              //-----Observaciones del Módulo
        var obsTittle = ""              //-----Guarda si es Encuesta o Módulo
        var version = "0.0"

        var recap1 = true
        var recap2 = true
        var recap3 = true
        var recap4 = true
        var recap5 = true
        var recap6 = true
        var recap7 = true
        var recap8 = true
        var recap9 = true
        var recap10 = true
        var recapMod = true


        //------------------------------------------------------------------------------------------
        private var authInfo: ModelAuthTokenInfo? = ModelAuthTokenInfo(
            name = null,
            tokenS = null,
            expire = null)
        private var authToken: ModelAuthResult? = ModelAuthResult (
            token = null,
            infotoken = authInfo)
        var authData: ModelAuth? = ModelAuth (
            result = authToken,
            infoMsg = null)
        //------------------------------------------------------------------------------------------

        var authResp: ModelAuthResp? = ModelAuthResp (
            code = null,
            resp = null,
            body = null)

        var arrBlank: Array<Fragment> = emptyArray()
        var arrMain: Array<Fragment> = arrayOf(         //----Contenedor de los Fragment del Main
            MainFragmentLogin(),    //--00--c01
            MainFragmentSearch(),   //--01--c02
        )

        var arrEncuestas: Array<Fragment> = arrayOf(    //----Contenedor de los Fragment del Form
            FragMenu(),             //--00--Menu
            FragEncuestaCap01(),    //--01--c01
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
            FragEncuestaCap09o1(),  //--16--c09
            FragEncuestaCap09o2(),  //--17
            FragEncuestaCap10(),    //--18--c10
            FragModuloSecc01(),     //--19--s01
            FragModuloSecc02(),     //--20--s02
            FragModuloSecc03(),     //--21--s03
            FragModuloSecc04(),     //--22--s04
            FragTotalInforme()      //--23--Informe
        )

        //------------------------------------------------------------------------------------------  FORMULARIO
        var cap1: ModelCap1? = null
        var cap2: ModelCap2? = null
        var cap3: ModelCap3? = null
        var cap4: ModelCap4? = null
        var cap5: ModelCap5? = null
        var cap6: ModelCap6? = null
        var cap7: ModelCap7? = null
        var cap8: ModelCap8? = null
        var cap9: ModelCap9? = null
        var cap10: ModelCap10? = null
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
        cap10 = cap10,
        capMod = capMod )

    }
}