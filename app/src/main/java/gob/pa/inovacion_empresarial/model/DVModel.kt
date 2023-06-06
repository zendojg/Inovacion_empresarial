package gob.pa.inovacion_empresarial.model

import android.util.Log
import androidx.lifecycle.ViewModel
import gob.pa.inovacion_empresarial.service.api.ApiBuilder
import gob.pa.inovacion_empresarial.service.api.ApiService
import gob.pa.inovacion_empresarial.service.room.DBcorregimiento
import gob.pa.inovacion_empresarial.service.room.DBdistritos
import gob.pa.inovacion_empresarial.service.room.DBlugarP
import gob.pa.inovacion_empresarial.service.room.DBprovincia
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.awaitResponse


class DVModel: ViewModel() {


    suspend fun loginToken(): String {
        val resp: String
        return try {
            val respuesta = ApiBuilder.ServiceBuilder.buildService(ApiService::class.java).
            loginToken()
            resp = respuesta.code().toString()
            resp
        } catch (e: Exception) {
            e.toString()
        }
    }

    //----------------------------------------------------------------------------------------------
    suspend fun loginCall(login: ModelLog): ModelAuthResp? {
        val resp: String?
        val retrofit = ApiBuilder.ServiceBuilder.buildService(ApiService::class.java)

        val response = try {
            retrofit.loginResp(login).awaitResponse()
        } catch (t: Throwable) {
            Log.e("-- Response error: ", t.message.toString())
            return null
        }
        resp = if (response.errorBody() != null)
            (response.errorBody()!!.charStream().readText()) else null

        return ModelAuthResp(
            code = response.code(),
            resp = resp,
            body = response.body())
    }

    //----------------------------------------------------------------------------------------------
    suspend fun formGet(ncontrol: String): ModelFormGet? {
        val code: Int
        val error: String?
        val body: ModelForm? = try {
            val resp = ApiBuilder.ServiceBuilder.buildService(ApiService::class.java).
            getForm(ncontrol)
            error = if (resp.errorBody() != null) (resp.errorBody()!!.charStream().readText())
            else null
            code = resp.code()
            resp.body()
        } catch (e: Exception) {
            Log.e("-- Response error: ", e.message.toString())
            return null
        }
        return ModelFormGet(
            code = code,
            resp = error,
            body = body)
    }

    suspend fun getProv(): List<DBprovincia> = withContext(Dispatchers.IO) {
        try {
            val respuesta: Response<List<DBprovincia>> =
                ApiBuilder.ServiceBuilder.buildService(ApiService::class.java).getProv()
            respuesta.body() ?: emptyList()
        } catch (e: Exception) { emptyList() }
    }

    suspend fun getDist(): List<DBdistritos> = withContext(Dispatchers.IO) {
        try {
            val respuesta: Response<List<DBdistritos>> =
                ApiBuilder.ServiceBuilder.buildService(ApiService::class.java).getDistrito()
            respuesta.body() ?: emptyList()
        } catch (e: Exception) { emptyList() }
    }

    suspend fun getCorre(): List<DBcorregimiento> = withContext(Dispatchers.IO) {
        try {
            val respuesta: Response<List<DBcorregimiento>> =
                ApiBuilder.ServiceBuilder.buildService(ApiService::class.java).getCorre()
            respuesta.body() ?: emptyList()
        } catch (e: Exception) { emptyList() }
    }

    suspend fun getLugarP(): List<DBlugarP> = withContext(Dispatchers.IO) {
        try {
            val respuesta: Response<List<DBlugarP>> =
                ApiBuilder.ServiceBuilder.buildService(ApiService::class.java).getLugarP()
            respuesta.body() ?: emptyList()
        } catch (e: Exception) { emptyList() }
    }

}