package team.android.pv.qlshop.view.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.realm.Realm
import io.realm.kotlin.createObject
import kotlinx.android.synthetic.main.activity_register.*
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.User
import team.android.pv.qlshop.model.data.SharedPreferencesManager
import team.android.pv.qlshop.presenter.register.RegisterInteractor
import team.android.pv.qlshop.presenter.register.ResgisterPresenter
import team.android.pv.qlshop.view.views.ViewRegister

class RegisterActivity : AppCompatActivity() ,ViewRegister {
    private lateinit var register : ResgisterPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        register=ResgisterPresenter(this, RegisterInteractor())

        var check_admin=intent.getIntExtra("check_admin",0)

        btnRegister.setOnClickListener{

            var email =ed_Email.text.toString()
            var password=ed_Pass.text.toString()
            var name_shop=edNameShop.text.toString()
            var name=edName.text.toString()
            var phone=edPhone.text.toString()

            var user:User?=User()
            user!!.email=email
            user!!.name_shop=name_shop
            user!!.name=name
            user!!.phone=phone
            user!!.password=password
            if(check_admin==0){
                SharedPreferencesManager.getInstanceSharedPreferencesManager(this)
                val user=SharedPreferencesManager.getUser()
                user!!.id_shop=user.id_shop
            }
            register.registerUser(user!!,check_admin)
        }

        toolbar.setOnClickListener{
            finish()
        }
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun setData(user: User) {
        SharedPreferencesManager.getInstanceSharedPreferencesManager(this)
        SharedPreferencesManager.saveUser(user)
        startActivity(Intent(this,HomeActivity::class.java))
    }

    override fun setDataError(error: String) {
          Toast.makeText(this,error,Toast.LENGTH_SHORT).show()
    }
}
