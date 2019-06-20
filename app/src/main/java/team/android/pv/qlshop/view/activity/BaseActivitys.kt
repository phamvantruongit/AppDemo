package team.android.pv.qlshop.view.activity

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import team.android.pv.qlshop.MyApplication.Companion.appDatabase
import team.android.pv.qlshop.model.data.database.UserEntity

abstract class BaseActivitys :AppCompatActivity() {

   var userEntity:UserEntity?=null

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)


      userEntity= appDatabase.userDao().getUser()
   }


   fun hasNetwork(): Boolean {
      var isConnected: Boolean? = false
      val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
      val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
      if (activeNetwork != null && activeNetwork.isConnected)
         isConnected = true
      return isConnected!!
   }
}