package team.android.pv.qlshop

import android.app.Application
import android.arch.persistence.room.Room
import team.android.pv.qlshop.api.ApiClient
import team.android.pv.qlshop.api.ApiInterface
import team.android.pv.qlshop.model.data.SharedPreferencesManager
import team.android.pv.qlshop.model.data.database.AppDatabase

class MyApplication : Application() {
   companion object {
       var  apiClient= ApiClient.getClient()!!.create(ApiInterface::class.java)
       lateinit var appDatabase: AppDatabase
   }

    override fun onCreate() {
        super.onCreate()
        appDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "myshop.db"
        ).allowMainThreadQueries().build()

        SharedPreferencesManager.getInstanceSharedPreferencesManager(this)

    }


}