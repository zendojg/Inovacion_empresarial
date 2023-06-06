package gob.pa.inovacion_empresarial.service.api


import gob.pa.inovacion_empresarial.model.MVar
import okhttp3.Interceptor
import okhttp3.Response

class ApiHeader:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization" ,"Bearer ${MVar.authData?.result?.token}")
            .build()
        return chain.proceed(request)
    }
}