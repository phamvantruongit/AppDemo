package team.android.pv.qlshop.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import team.android.pv.qlshop.MyApplication
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.data.SharedPreferencesManager



class SplashActivity : BaseActivitys() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyApplication.appDatabase.productDao().deleteAllProduct()

        setContentView(team.android.pv.qlshop.R.layout.activity_splash)



        if( userEntity!=null && userEntity!!.email!=null){
            if(MyApplication.appDatabase.userDao().getUser().login){
                startActivity(Intent(this,HomeActivity::class.java))
                finish()
                return
            }else {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
        else {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }

    }

}
