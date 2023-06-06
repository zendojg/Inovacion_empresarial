package gob.pa.inovacion_empresarial.service.api

import gob.pa.inovacion_empresarial.model.MVar
import gob.pa.inovacion_empresarial.model.ModelAuth
import gob.pa.inovacion_empresarial.model.ModelForm
import gob.pa.inovacion_empresarial.model.ModelLog
import gob.pa.inovacion_empresarial.service.room.DBcorregimiento
import gob.pa.inovacion_empresarial.service.room.DBdistritos
import gob.pa.inovacion_empresarial.service.room.DBlugarP
import gob.pa.inovacion_empresarial.service.room.DBprovincia
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    //-----------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------  LOGIN
    @POST (MVar.apiAut)
    fun loginResp(@Body Form: ModelLog): Call<ModelAuth>
    //----------------------------------------------------------------------------------------------  TOKEN
    @GET (MVar.apiAut)
    suspend fun loginToken(): Response<Void>


    //----------------------------------------------------------------------------------------------  BUSCAR CUESTIONARIO
    @GET ("${MVar.apiGetForm}{id}")//---------------------------------------------------------------  Formulario GET
    suspend fun getForm(@Path("id") id:String): Response<ModelForm>


    //----------------------------------------------------------------------------------------------  DESCARGA PARA DB
    @GET (MVar.apiProv)//---------------------------------------------------------------------------  Provincia
    suspend fun getProv(): Response<List<DBprovincia>>
    @GET (MVar.apiDist)//---------------------------------------------------------------------------  Distrito
    suspend fun getDistrito(): Response<List<DBdistritos>>
    @GET (MVar.apiCorre) //-------------------------------------------------------------------------  Corregimiento
    suspend fun getCorre(): Response<List<DBcorregimiento>>
    @GET (MVar.apiLugarP) //------------------------------------------------------------------------  Lugar Poblado
    suspend fun getLugarP(): Response<List<DBlugarP>>

}