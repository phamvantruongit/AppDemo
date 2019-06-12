package team.android.pv.qlshop.model.data

import android.content.Context
import android.content.SharedPreferences
import team.android.pv.qlshop.model.User


class SharedPreferencesManager {
    companion object {
        var sharedPref: SharedPreferences? = null
        fun getInstanceSharedPreferencesManager(context: Context) {
            sharedPref = context.getSharedPreferences("User", 0)
        }

        fun logOut(check:Boolean){
            var edit: SharedPreferences.Editor = sharedPref!!.edit()
            edit.putBoolean("login", check)
            edit.commit()
        }

        fun checkLogin() : Boolean{
           return sharedPref!!.getBoolean("login",false)
        }


    }
}