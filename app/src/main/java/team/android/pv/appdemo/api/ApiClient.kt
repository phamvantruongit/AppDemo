package team.android.pv.appdemo.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        fun getInstance(): Retrofit? {
            var retrofit: Retrofit? = null
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl("")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }
}