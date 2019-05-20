package team.android.pv.qlshop.view.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.toolbar.*
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.User
import team.android.pv.qlshop.presenter.login.LoginInteractor
import team.android.pv.qlshop.presenter.login.LoginPresenter
import team.android.pv.qlshop.view.views.ViewLogin

class LoginActivity : AppCompatActivity(), ViewLogin {


    private lateinit var login: LoginPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        login = LoginPresenter(this, LoginInteractor())


        tvRegister.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

        btnLogin.setOnClickListener({
            val email = edEmail.text.toString()
            val password = edPass.text.toString()
            if (TextUtils.isEmpty(email)) {
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(password)) {
                return@setOnClickListener
            }
            var user = User()
            user!!.email = email
            user!!.password = password
            login.login(user!!)

        })
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }

    override fun setData(user: User) {
        Log.d("User",user.email + user.id_shop)
        startActivity(Intent(this@LoginActivity,HomeActivity::class.java))
    }

    override fun setDataError(error: String) {
    }
}
