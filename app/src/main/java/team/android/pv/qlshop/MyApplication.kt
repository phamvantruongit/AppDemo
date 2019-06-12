package team.android.pv.qlshop

import android.app.Application
import android.arch.persistence.room.Room
import io.realm.Realm
import io.realm.RealmConfiguration
import team.android.pv.qlshop.api.ApiClient
import team.android.pv.qlshop.api.ApiInterface
import team.android.pv.qlshop.model.data.SharedPreferencesManager
import team.android.pv.qlshop.model.data.database.AppDatabase

class MyApplication : Application() {
   companion object {
       var  apiClient= ApiClient.getClient()!!.create(ApiInterface::class.java)
       lateinit var realmMyApplication:Realm
       lateinit var appDatabase: AppDatabase
   }

    override fun onCreate() {
        super.onCreate()


        appDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "myshop.db"
        ).allowMainThreadQueries().build()

        SharedPreferencesManager.getInstanceSharedPreferencesManager(this)

        Realm.init(this)
        val config = RealmConfiguration.Builder().
            schemaVersion(2).build()
        Realm.setDefaultConfiguration(config)
        realmMyApplication = Realm.getDefaultInstance()
        realmMyApplication.beginTransaction()
        realmMyApplication.commitTransaction()















    }


}