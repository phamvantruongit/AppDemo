package team.android.pv.qlshop.api

import android.content.Context
import android.text.TextUtils
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import team.android.pv.qlshop.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import java.util.concurrent.TimeUnit


class ApiClient {
//    companion object {
//        val BASE_URL=BuildConfig.BASE_URL
//        fun getInstance(): Retrofit? {
//            var retrofit: Retrofit? = null
//            if (retrofit == null) {
//                retrofit = Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .client(getRequestHeader())
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build()
//            }
//            return retrofit
//        }
//
//        fun getRequestHeader(): OkHttpClient {
//
//            val httpClient = OkHttpClient.Builder()
//
//            httpClient
//                .addInterceptor { chain ->
//                val original = chain.request()
//                val request = original.newBuilder()
//                    .build()
//                chain.proceed(request)
//            }
//                .connectTimeout(100, java.util.concurrent.TimeUnit.SECONDS)
//                .writeTimeout(100, java.util.concurrent.TimeUnit.SECONDS)
//                .readTimeout(300, java.util.concurrent.TimeUnit.SECONDS)
//
//            return httpClient.build()
//        }
//    }
    companion object {
        private var retrofit: Retrofit? = null
        private val REQUEST_TIMEOUT = 60
        private var okHttpClient: OkHttpClient? = null

        fun getClient(): Retrofit {

            if (okHttpClient == null)
                initOkHttp()

            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(okHttpClient!!)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return this.retrofit!!
        }

        private fun initOkHttp() {
            val httpClient = OkHttpClient().newBuilder()
                .connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)

            val interceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                interceptor.level = HttpLoggingInterceptor.Level.BODY
            } else {
                interceptor.level = HttpLoggingInterceptor.Level.NONE
            }

            httpClient.addInterceptor(interceptor)

//        httpClient.addInterceptor { chain ->
//            val original = chain.request()
//            val requestBuilder = original.newBuilder()
//                .addHeader("Accept", "application/json")
//                .addHeader("Content-Type", "application/json")
//
//            // Adding Authorization token (API Key)
//            // Requests will be denied without API key
//            if (!TextUtils.isEmpty(PrefUtils.getApiKey(context))) {
//                requestBuilder.addHeader("Authorization", PrefUtils.getApiKey(context))
//            }
//
//            val request = requestBuilder.build()
//            chain.proceed(request)
//        }

            okHttpClient = httpClient.build()
        }
    }
}