package team.android.pv.qlshop.view.activity

import android.os.Bundle
import kotlinx.android.synthetic.main.toolbar.*
import team.android.pv.qlshop.R

class MoreActivity :BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tvTitle.text=this.resources.getText(R.string.title_more)
    }

    override fun getContentView(): Int {
        return R.layout.activity_more
    }

    override fun getNavigationMenuItemId(): Int {
        return R.id.navigation_more
    }
}