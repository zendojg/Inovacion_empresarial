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
import java.io.IOException

class DVModel: ViewModel() {

    //---- PRUEBA DE TOKEN
    suspend fun seeToken(): ModelResp? { //--  VERIFICADOR DE TOKEN INVENTADO
        val msg: String?
        val retrofit = ApiBuilder.ServiceBuilder.buildService(ApiService::class.java)

        val response = try {
            retrofit.getProv()
        } catch (e: IOException) {
            Log.e("-- Response error: ", e.message.toString())
            return null
        }
        msg = if (response.errorBody() != null)
            (response.errorBody()!!.charStream().readText()) else null

        return ModelResp(
            code = response.code(),
            msg = msg)
    }

    //---- ACCESS LOGIN
    suspend fun loginCall(login: ModelLog): ModelAuthResp? {
        val resp: String?
        val retrofit = ApiBuilder.ServiceBuilder.buildService(ApiService::class.java)

        val response = try {
            retrofit.loginResp(login).awaitResponse()
        } catch (e: IOException) {
            Log.e("-- Response error: ", e.message.toString())
            return null
        }
        resp = if (response.errorBody() != null)
            (response.errorBody()!!.charStream().readText()) else null

        return ModelAuthResp(
            code = response.code(),
            msg = resp,
            body = response.body()
        )
    }

    //---- DOWNLOAD FORM
    suspend fun formGet(ncontrol: String): ModelFormGet? {
        val code: Int
        val error: String?
        val body: ModelForm? = try {
            val resp =
                ApiBuilder.ServiceBuilder.buildService(ApiService::class.java).getForm(ncontrol)
            error = if (resp.errorBody() != null) (resp.errorBody()!!.charStream().readText())
            else null
            code = resp.code()
            resp.body()
        } catch (e: IOException) {
            Log.e("-- Response error: ", e.message.toString())
            return null
        }
        return ModelFormGet(
            code = code,
            resp = error,
            body = body
        )
    }
    suspend fun formsGetUser(userID: String): ModelFormsGetUser? {
        val code: Int
        val error: String?
        val body: List<ModelForm>? = try {
            val resp =
                ApiBuilder.ServiceBuilder.buildService(ApiService::class.java).getFormsUser(userID)
            error = if (resp.errorBody() != null) (resp.errorBody()!!.charStream().readText())
            else null
            code = resp.code()
            resp.body()
        } catch (e: IOException) {
            Log.e("-- Response error: ", e.message.toString())
            return null
        }
        return ModelFormsGetUser(
            code = code,
            resp = error,
            body = body
        )
    }

    //---- DOWNLOAD PROVINCIAS
    suspend fun getProv(): List<DBprovincia> = withContext(Dispatchers.IO) {
        try {
            val respuesta: Response<List<DBprovincia>> =
                ApiBuilder.ServiceBuilder.buildService(ApiService::class.java).getProv()
            respuesta.body() ?: emptyList()
        } catch (e: IOException) {
            emptyList()
        }
    }

    //---- DOWNLOAD DISTRITOS
    suspend fun getDist(): List<DBdistritos> = withContext(Dispatchers.IO) {
        try {
            val respuesta: Response<List<DBdistritos>> =
                ApiBuilder.ServiceBuilder.buildService(ApiService::class.java).getDistrito()
            respuesta.body() ?: emptyList()
        } catch (e: IOException) {
            emptyList()
        }
    }

    //---- DOWNLOAD CORREGIMIENTOS
    suspend fun getCorre(): List<DBcorregimiento> = withContext(Dispatchers.IO) {
        try {
            val respuesta: Response<List<DBcorregimiento>> =
                ApiBuilder.ServiceBuilder.buildService(ApiService::class.java).getCorre()
            respuesta.body() ?: emptyList()
        } catch (e: IOException) {
            emptyList()
        }
    }

    //---- DOWNLOAD LUGAR POBLADO
    suspend fun getLugarP(): List<DBlugarP> = withContext(Dispatchers.IO) {
        try {
            val respuesta: Response<List<DBlugarP>> =
                ApiBuilder.ServiceBuilder.buildService(ApiService::class.java).getLugarP()
            respuesta.body() ?: emptyList()
        } catch (e: IOException) {
            emptyList()
        }
    }


    suspend fun sendForm(form: ModelForm): ModelFormSend {
        var code: String
        var serverResp: String?
        val body = try {
            val resp =
                ApiBuilder.ServiceBuilder.buildService(ApiService::class.java).sendForm(form)
            serverResp = if (resp.errorBody() != null) (resp.errorBody()!!.charStream().readText())
            else null
            code = resp.code().toString()
            resp.body()
        } catch (e: IOException) {
            Log.e("-- Response error: ", e.message.toString())
            serverResp = null
            code = "0"
            null
        }
        return ModelFormSend(
            code = code,
            server = serverResp,
            body = body
        )
    }

}

