package team.android.pv.qlshop.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder
import com.google.gson.Gson
import team.android.pv.qlshop.BuildConfig


class ApiClient {
    companion object {
        val BASE_URL=BuildConfig.BASE_URL
        fun getInstance(): Retrofit? {
            val gson = GsonBuilder()
                .setLenient()
                .create()
            var retrofit: Retrofit? = null
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }
}