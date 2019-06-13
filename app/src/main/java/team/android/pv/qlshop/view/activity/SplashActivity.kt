package team.android.pv.qlshop.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.data.SharedPreferencesManager

class SplashActivity : BaseActivitys() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)
        var a:Double=15.0
        var b:Double=100.0
        var c:Double=a/b
        Log.d("PPPP",c.toString())
        if( userEntity!=null && userEntity!!.email!=null){
            if(SharedPreferencesManager.checkLogin()){
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
                return
            }else {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        }
        else {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }

    }
}
