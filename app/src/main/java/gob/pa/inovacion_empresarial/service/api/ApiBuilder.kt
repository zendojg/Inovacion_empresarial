package gob.pa.inovacion_empresarial.service.api


import gob.pa.inovacion_empresarial.model.Mob
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class ApiBuilder {
    object ServiceBuilder {
        private val getRetrofit = Retrofit.Builder()
            .baseUrl(Mob.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getclient())
            .build()

        private fun getclient(): OkHttpClient {
            val logging = HttpLoggingInterceptor()
            val trustAllCerts = arrayOf<TrustManager>(CustomTrustManager()) //---- x
            val sslContext = SSLContext.getInstance("SSL")          //---- x
            sslContext.init(null, trustAllCerts, SecureRandom())        //---- x
            logging.level = HttpLoggingInterceptor.Level.BODY               //---- LOG
            return OkHttpClient.Builder()
                .sslSocketFactory(sslContext.socketFactory, trustAllCerts[0] as X509TrustManager)//x
                .hostnameVerifier { _, _ -> true }                          //---- x
                .callTimeout(Mob.TIME360MS,TimeUnit.SECONDS)
                .connectTimeout(Mob.TIME10MS,TimeUnit.SECONDS)
                .addInterceptor(ApiHeader())
                .addInterceptor(logging)
                .build()
        }

        fun <T> buildService(service: Class<T>): T {
            return getRetrofit.create(service)
        }
    }
}