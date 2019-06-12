package team.android.pv.qlshop.view.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.CompoundButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import team.android.pv.qlshop.MyApplication.Companion.appDatabase
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.User
import team.android.pv.qlshop.model.data.SharedPreferencesManager
import team.android.pv.qlshop.model.data.database.UserEntity
import team.android.pv.qlshop.presenter.login.LoginInteractor
import team.android.pv.qlshop.presenter.login.LoginPresenter
import team.android.pv.qlshop.view.view.ViewLogin



class LoginActivity : BaseActivitys(), ViewLogin {


    private lateinit var login: LoginPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        login = LoginPresenter(this, LoginInteractor())

        val email=intent.getStringExtra("email")
        if(!TextUtils.isEmpty(email)){
             edEmail.setText(email)
        }



        if( userSave!=null && userSave!!.email!=null){

            edEmail.setText(userSave!!.email)

        }


        ckpass.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if(isChecked){
                    edPass.transformationMethod= HideReturnsTransformationMethod.getInstance()
                }else{
                    edPass.transformationMethod= PasswordTransformationMethod.getInstance()

                }
            }

        })



        tvRegister.setOnClickListener {
            var intent=Intent(this,RegisterActivity::class.java)
            intent.putExtra("check_admin",1)
            startActivity(intent)
        }

        btnLogin.setOnClickListener{
            val email = edEmail.text.toString()
            val password = edPass.text.toString()
            if (TextUtils.isEmpty(email)) {
                edEmail.error=getString(R.string.enter_email)
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(password)) {
                edPass.error=getString(R.string.enter_password)
                return@setOnClickListener
            }
            var user = User()
            user.email = email
            user.password = password
            login.login(user)

        }
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }


    override fun setData(user: User) {
        SharedPreferencesManager.logOut(false)
        var userEntity=UserEntity()
        userEntity.id=user.id
        userEntity.email=user.email
        userEntity.id_shop=user.id_shop
        userEntity.name=user.name
        userEntity.name_shop=user.name_shop
        userEntity.check_admin=user.check_admin

        appDatabase.userDao().addUser(userEntity)

        var user= appDatabase.userDao().getUser()



//        realmMyApplication.executeTransaction {
//
//            var users=it.createObject(team.android.pv.qlshop.model.data.User::class.java)
//            users.id=user.id
//            users.email=user.email
//            users.id_shop=user.id_shop
//            users.name=user.name
//            users.name_shop=user.name_shop
//            users.check_admin=user.check_admin
//
//
//
//        }
        startActivity(Intent(this@LoginActivity,HomeActivity::class.java))
        finish()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }
}
