package team.android.pv.qlshop.view.activity

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_more.*
import kotlinx.android.synthetic.main.toolbar.*
import team.android.pv.qlshop.R

class MoreActivity :BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tvTitle.text=this.resources.getText(R.string.title_more)

        rl_user.setOnClickListener({
            startActivity(Intent(this,AddProductActivity::class.java))
        })

        rl_customer.setOnClickListener({
            startActivity(Intent(this,AddProductActivity::class.java))
        })

        rl_product.setOnClickListener({
            startActivity(Intent(this,AddProductActivity::class.java))
        })

        rl_category.setOnClickListener({
            startActivity(Intent(this,AddCategoryActivity::class.java))
        })

        rl_brand.setOnClickListener({
            startActivity(Intent(this,AddCategoryActivity::class.java))
        })


    }

    override fun getContentView(): Int {
        return R.layout.activity_more
    }

    override fun getNavigationMenuItemId(): Int {
        return R.id.navigation_more
    }
}