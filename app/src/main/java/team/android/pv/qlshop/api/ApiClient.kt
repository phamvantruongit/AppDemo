package team.android.pv.qlshop.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import team.android.pv.qlshop.BuildConfig
import okhttp3.OkHttpClient






class ApiClient {
    companion object {
        val BASE_URL=BuildConfig.BASE_URL
        fun getInstance(): Retrofit? {
            var retrofit: Retrofit? = null
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(getRequestHeader())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }

        fun getRequestHeader(): OkHttpClient {

            val httpClient = OkHttpClient.Builder()

            httpClient.addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .build()
                chain.proceed(request)
            }
                .connectTimeout(100, java.util.concurrent.TimeUnit.SECONDS)
                .writeTimeout(100, java.util.concurrent.TimeUnit.SECONDS)
                .readTimeout(300, java.util.concurrent.TimeUnit.SECONDS)

            return httpClient.build()
        }
    }
}