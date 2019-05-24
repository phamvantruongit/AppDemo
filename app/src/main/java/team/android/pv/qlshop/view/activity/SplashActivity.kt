package team.android.pv.qlshop.view.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.data.SharedPreferencesManager

class SplashActivity : BaseActivitys() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if( userSave!=null && userSave!!.email!=null){
            if(SharedPreferencesManager.checkLogin()){
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
                return
            }
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }
        else {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }

    }
}
