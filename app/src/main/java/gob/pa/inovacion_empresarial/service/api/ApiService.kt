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
    @GET ("${Mob.APIGETFORM}{id}") //--  Formulario GET
    suspend fun getForm(@Path("id") id:String): Response<ModelForm>
    @GET ("${Mob.APIFORMUSER}{id}") //--  Formulario GET
    suspend fun getFormsUser(@Path("id") id:String): Response<List<ModelForm>>
    @GET ("${Mob.APIINCONSISTENCIAS}{nctrl}") //--  Inconsistencias GET
    suspend fun getInconsistencias(@Path("nctrl") nctrl:String): Response<Any>

    //--------------------------  DESCARGA PARA DB
    @GET (Mob.APIPROV) suspend fun getProv(): Response<List<DBprovincia>> //---------  Provincia
    @GET (Mob.APIDIST) suspend fun getDistrito(): Response<List<DBdistritos>>//------  Distrito
    @GET (Mob.APICORRE) suspend fun getCorre(): Response<List<DBcorregimiento>>//----  Corregimiento
    @GET (Mob.APILUGARP) suspend fun getLugarP(): Response<List<DBlugarP>> //--------  Lugar Poblado
    @PUT (Mob.APIUPDATEFORM) suspend fun sendForm(@Body form: ModelForm): Response<Any>


}