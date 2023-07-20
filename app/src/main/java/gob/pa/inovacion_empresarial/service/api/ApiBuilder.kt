package gob.pa.inovacion_empresarial.service.api


import gob.pa.inovacion_empresarial.model.Mob
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiBuilder {
    object ServiceBuilder {
        private val getRetrofit = Retrofit.Builder()
            .baseUrl(Mob.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getclient())
            .build()

        private fun getclient(): OkHttpClient {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY //<-----------------------  LOG
            return OkHttpClient.Builder()
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