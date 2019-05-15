package team.android.pv.appdemo

import android.app.Application
import team.android.pv.appdemo.api.ApiClient
import team.android.pv.appdemo.api.ApiInterface

class MyApplication : Application() {
   companion object {
       var apiClient= ApiClient.getInstance()!!.create(ApiInterface::class.java)
    }
    override fun onCreate() {
        super.onCreate()
    }
}