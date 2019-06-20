package team.android.pv.qlshop

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import team.android.pv.qlshop.api.ApiClient
import team.android.pv.qlshop.api.ApiInterface
import team.android.pv.qlshop.model.data.SharedPreferencesManager
import team.android.pv.qlshop.model.data.database.AppDatabase

class MyApplication : Application() {
   companion object {
       var  apiClient:ApiInterface ? = null
       lateinit var appDatabase: AppDatabase
   }

    override fun onCreate() {
        super.onCreate()

        appDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "myshop.db"
        ).allowMainThreadQueries().build()

        SharedPreferencesManager.getInstanceSharedPreferencesManager(this)


        apiClient= ApiClient.getClient(this)!!.create(ApiInterface::class.java)

    }

}