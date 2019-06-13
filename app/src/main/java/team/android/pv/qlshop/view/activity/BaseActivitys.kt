package team.android.pv.qlshop.view.activity

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
}