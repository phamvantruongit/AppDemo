package team.android.pv.qlshop

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
import team.android.pv.qlshop.api.ApiClient
import team.android.pv.qlshop.api.ApiInterface
import team.android.pv.qlshop.model.data.SharedPreferencesManager

class MyApplication : Application() {

   companion object {
       var apiClient= ApiClient.getInstance()!!.create(ApiInterface::class.java)
       lateinit var realmMyApplication:Realm
   }

    override fun onCreate() {
        super.onCreate()


        SharedPreferencesManager.getInstanceSharedPreferencesManager(this)

        Realm.init(this)
        val config = RealmConfiguration.Builder().build()
        Realm.setDefaultConfiguration(config)


        realmMyApplication = Realm.getDefaultInstance()

        realmMyApplication.beginTransaction()
        realmMyApplication.commitTransaction()









    }


}