package team.android.pv.qlshop.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import team.android.pv.qlshop.MyApplication
import team.android.pv.qlshop.MyApplication.Companion.realmMyApplication
import team.android.pv.qlshop.model.data.User

abstract class BaseActivitys :AppCompatActivity() {
   var userSave:User?=null

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      userSave =realmMyApplication.where(team.android.pv.qlshop.model.data.User::class.java).findFirst()
   }
}