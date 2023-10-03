package gob.pa.inovacion_empresarial.service.api

import gob.pa.inovacion_empresarial.model.Mob
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
    //--  LOGIN
    @POST(Mob.APIAUTH)
    fun loginResp(@Body bodyLog: ModelLog): Call<ModelAuth>
    //--  TOKEN
//    @GET(Mob.APIAUTH)
//    suspend fun loginToken(): Response<Void>


    @GET ("${Mob.APIGETFORM}{id}")//--  Formulario GET
    suspend fun getForm(@Path("id") id:String): Response<ModelForm>

    @GET ("${Mob.APIFORMUSER}{id}")//--  Formulario GET
    suspend fun getFormsUser(@Path("id") id:String): Response<List<ModelForm>>


    //--------------------------  DESCARGA PARA DB
    @GET (Mob.APIPROV)//--------  Provincia
    suspend fun getProv(): Response<List<DBprovincia>>
    @GET (Mob.APIDIST)//--------  Distrito
    suspend fun getDistrito(): Response<List<DBdistritos>>
    @GET (Mob.APICORRE) //------  Corregimiento
    suspend fun getCorre(): Response<List<DBcorregimiento>>
    @GET (Mob.APILUGARP) //-----  Lugar Poblado
    suspend fun getLugarP(): Response<List<DBlugarP>>

    @PUT (Mob.APIUPDATEFORM)
    suspend fun sendForm(@Body form: ModelForm): Response<Any>

}