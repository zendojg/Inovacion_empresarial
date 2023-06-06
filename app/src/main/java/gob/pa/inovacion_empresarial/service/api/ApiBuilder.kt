package gob.pa.inovacion_empresarial.service.api


import gob.pa.inovacion_empresarial.model.MVar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiBuilder {
    object ServiceBuilder {
        private val getRetrofit = Retrofit.Builder()
            .baseUrl(MVar.url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getclient())
            .build()

        private fun getclient(): OkHttpClient {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY //<-----------------------  LOG
            return OkHttpClient.Builder()
                .callTimeout(360,TimeUnit.SECONDS)
                .connectTimeout(10,TimeUnit.SECONDS)
                .addInterceptor(ApiHeader())
                .addInterceptor(logging)
                .build()
        }

        fun <T> buildService(service: Class<T>): T {
            return getRetrofit.create(service)
        }
    }
}