package team.android.pv.qlshop.view.activity

import android.os.Bundle
import kotlinx.android.synthetic.main.toolbar.*
import team.android.pv.qlshop.R

class HomeActivity :BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tvTitle.text=this.resources.getText(R.string.title_home)

    }
    override fun getContentView(): Int {
        return R.layout.activity_home
    }

    override fun getNavigationMenuItemId(): Int {
         return R.id.navigation_home
    }
}