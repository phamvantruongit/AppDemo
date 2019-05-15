package team.android.pv.qlshop

import android.app.Application
import team.android.pv.qlshop.api.ApiClient
import team.android.pv.qlshop.api.ApiInterface

class MyApplication : Application() {
   companion object {
       var apiClient= ApiClient.getInstance()!!.create(ApiInterface::class.java)
    }
    override fun onCreate() {
        super.onCreate()
    }
}