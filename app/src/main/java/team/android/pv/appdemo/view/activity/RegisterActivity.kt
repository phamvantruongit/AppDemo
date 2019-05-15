package team.android.pv.appdemo.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import team.android.pv.appdemo.R
import team.android.pv.appdemo.model.User
import team.android.pv.appdemo.presenter.login.LoginPresenter
import team.android.pv.appdemo.presenter.register.RegisterInteractor
import team.android.pv.appdemo.presenter.register.ResgisterPresenter
import team.android.pv.appdemo.view.views.ViewRegister

class RegisterActivity : AppCompatActivity() ,ViewRegister {
    private lateinit var register : ResgisterPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        register=ResgisterPresenter(this, RegisterInteractor())

        btnRegister.setOnClickListener({

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
            register.registerUser(user!!)
        })
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun setData(user: User) {

    }

    override fun setDataError(error: String) {

    }
}
