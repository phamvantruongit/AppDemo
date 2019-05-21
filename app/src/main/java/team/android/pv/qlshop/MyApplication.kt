package team.android.pv.qlshop

import android.app.Application
import android.content.SharedPreferences
import io.realm.Realm
import io.realm.RealmConfiguration
import team.android.pv.qlshop.api.ApiClient
import team.android.pv.qlshop.api.ApiInterface
import team.android.pv.qlshop.model.data.SharedPreferencesManager

class MyApplication : Application() {

   companion object {
       var apiClient= ApiClient.getInstance()!!.create(ApiInterface::class.java)

   }


    override fun onCreate() {
        super.onCreate()
    }
}