package team.android.pv.qlshop.view.activity

import android.os.Bundle
import android.util.Log
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.toolbar.*
import team.android.pv.qlshop.MyApplication
import team.android.pv.qlshop.MyApplication.Companion.realmMyApplication
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