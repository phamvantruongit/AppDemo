package team.android.pv.qlshop.view.activity

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.CompoundButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.toolbar
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.User
import team.android.pv.qlshop.presenter.register.RegisterInteractor
import team.android.pv.qlshop.presenter.register.ResgisterPresenter
import team.android.pv.qlshop.view.view.ViewRegister

class RegisterActivity : BaseActivitys() ,ViewRegister {
    private lateinit var register : ResgisterPresenter
    private  var check_admin=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        register=ResgisterPresenter(this, RegisterInteractor())

        check_admin=intent.getIntExtra("check_admin",0)

        var user=intent.getParcelableExtra<User>("user")
        if(user!=null){
            edName.setText(user.name)
            ed_Email.setText(user.email)
            edNameShop.setText(user.name_shop)
        }


        ck_pass.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if(isChecked){
                    ed_Pass.transformationMethod=HideReturnsTransformationMethod.getInstance()
                }else{
                    ed_Pass.transformationMethod=PasswordTransformationMethod.getInstance()

                }
            }

        })


        if(check_admin==0){
            edNameShop.setText(userEntity!!.name_shop)
        }

        btnRegister.setOnClickListener{

            var email =ed_Email.text.toString()
            var password=ed_Pass.text.toString()
            var name_shop=edNameShop.text.toString()
            var name=edName.text.toString()
            var phone=edPhone.text.toString()



            if(name_shop==""){
                edNameShop.error=getString(R.string.enter_info)
                return@setOnClickListener
            }


            if(name==""){
                edName.error=getString(R.string.enter_info)
                return@setOnClickListener
            }




            if(phone==""){
                edPhone.error=getString(R.string.enter_info)
                return@setOnClickListener
            }

            if(password==""){
                ed_Pass.error=getString(R.string.enter_info)
                return@setOnClickListener
            }

            var user:User?=User()
            user!!.email=email
            user.name_shop=name_shop
            user.name=name
            user.phone=phone
            user.password=password
            if(check_admin==0){
                user.id_shop= userEntity!!.id_shop
            }
            register.registerUsers(user,check_admin)
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

        if(check_admin==0){
            startActivity(Intent(this,MemmbersActivity::class.java))
            finish()
            return
        }

        var intent=Intent(this,LoginActivity::class.java)
        intent.putExtra("email",user.email)
        startActivity(intent)
        finish()
    }

    override fun showMessage(message: String) {
          Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
}
