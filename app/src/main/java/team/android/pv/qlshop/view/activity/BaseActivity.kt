package team.android.pv.qlshop.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.element_bottom_navigation.*
import team.android.pv.qlshop.R

abstract class BaseActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentView())
        navigation.setOnNavigationItemSelectedListener(this)
    }

    override fun onStart() {
        super.onStart()
        updateNavigationBarState()
    }

    private fun updateNavigationBarState() {
        val actionId = getNavigationMenuItemId()
        selectBottomNavigationBarItem(actionId)

    }

    private fun selectBottomNavigationBarItem(actionId: Int) {
        var menu: Menu = navigation.menu
        for (i in 0..menu.size()) {
            var item: MenuItem = menu.getItem(i)
            var shouldBeChecked: Boolean = item.itemId == actionId
            if (shouldBeChecked) {
                item.setCheckable(true)
                break
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var intent: Intent
        var itemId: Int = item.itemId
        if (itemId == R.id.navigation_home) {
            startActivity(Intent(this, HomeActivity::class.java))
        }
        if (itemId == R.id.navigation_sell) {
            startActivity(Intent(this, SellProductActivity::class.java))

        }
        if (itemId == R.id.navigation_products) {
            startActivity(Intent(this, ProductActivity::class.java))

        }

        if (itemId == R.id.navigation_more) {
            startActivity(Intent(this, MoreActivity::class.java))

        }
        finish()
        return true
    }

    protected abstract fun getContentView(): Int
    abstract fun getNavigationMenuItemId(): Int

}