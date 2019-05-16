package team.android.pv.qlshop.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.User
import team.android.pv.qlshop.presenter.register.RegisterInteractor
import team.android.pv.qlshop.presenter.register.ResgisterPresenter
import team.android.pv.qlshop.view.views.ViewRegister

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

        toolbar.setOnClickListener({
            finish()
        })
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun setData(user: User) {
        Toast.makeText(this,"Ok",Toast.LENGTH_SHORT).show()
    }

    override fun setDataError(error: String) {
          Toast.makeText(this,error,Toast.LENGTH_SHORT).show()
    }
}
