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

        fun saveUser(user: User) {
            var edit: SharedPreferences.Editor = sharedPref!!.edit()
            edit.putString("name", user.name)
            edit.putString("email", user.email)
            edit.putString("id_shop", user.id_shop.toString())
            edit.putString("name_shop", user.name_shop)
            edit.putBoolean("login", true)
            edit.commit()

        }

        fun logOut(){
            var edit: SharedPreferences.Editor = sharedPref!!.edit()
            edit.putBoolean("login", false)
            edit.commit()
        }

        fun checkLogin() : Boolean{
           return sharedPref!!.getBoolean("login",false)
        }

        fun getUser(): User? {
            var name = sharedPref!!.getString("name", "")
            var email = sharedPref!!.getString("email", "")
            var name_shop = sharedPref!!.getString("name_shop", "")
            var id_shop = sharedPref!!.getString("id_shop", "")
            var user = team.android.pv.qlshop.model.User()
            if(name!=null || name!=""){
                user.name = name
                user.name_shop = name_shop
                // user.id_shop = id_shop.toInt()
                user.email = email
                return user
            }

            return null

        }
    }
}