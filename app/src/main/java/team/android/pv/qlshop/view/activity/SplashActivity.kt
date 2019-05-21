package team.android.pv.qlshop.view.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.text.TextUtils
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.User
import team.android.pv.qlshop.model.data.SharedPreferencesManager

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        SharedPreferencesManager.getInstanceSharedPreferencesManager(this)
        val user:User? =  SharedPreferencesManager.getUser()



        if(user!=null && user.email!=null && !TextUtils.isEmpty(user.email) || SharedPreferencesManager.checkLogin()){
            if(!SharedPreferencesManager.checkLogin()){
                startActivity(Intent(this,LoginActivity::class.java))
                return
            }
            startActivity(Intent(this,HomeActivity::class.java))
        }
        else {
            startActivity(Intent(this,LoginActivity::class.java))
        }

    }
}
