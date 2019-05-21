package team.android.pv.qlshop.view.activity

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_more.*
import kotlinx.android.synthetic.main.toolbar.*
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.data.SharedPreferencesManager

class MoreActivity :BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tvTitle.text=this.resources.getText(R.string.title_more)

        rl_user.setOnClickListener{
            startActivity(Intent(this,AddProductActivity::class.java))
        }

        rl_customer.setOnClickListener{
            startActivity(Intent(this,AddProductActivity::class.java))
        }

        rl_product.setOnClickListener{
            startActivity(Intent(this,AddProductActivity::class.java))
        }

        rl_category.setOnClickListener{
            var intent=Intent(this,AddCategoryActivity::class.java)
            intent.putExtra("check",true)
            startActivity(intent)
        }

        rl_brand.setOnClickListener{
            var intent=Intent(this,AddCategoryActivity::class.java)
            intent.putExtra("check",false)
            startActivity(intent)
        }

        rlLogout.setOnClickListener{
            SharedPreferencesManager.getInstanceSharedPreferencesManager(this)
            SharedPreferencesManager.logOut()
            startActivity(Intent(this,LoginActivity::class.java))
        }


        rl_memmber.setOnClickListener{
            var intent=Intent(this,RegisterActivity::class.java)
            intent.putExtra("check_admin",0)
            startActivity(intent)

        }


    }

    override fun getContentView(): Int {
        return R.layout.activity_more
    }

    override fun getNavigationMenuItemId(): Int {
        return R.id.navigation_more
    }
}