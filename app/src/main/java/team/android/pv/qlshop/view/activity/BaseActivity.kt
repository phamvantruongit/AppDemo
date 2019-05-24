package team.android.pv.qlshop.view.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.element_bottom_navigation.*
import team.android.pv.qlshop.MyApplication
import team.android.pv.qlshop.R

abstract class BaseActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    var userSave = MyApplication.realmMyApplication.where(team.android.pv.qlshop.model.data.User::class.java).findFirst()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentView())
        navigation.setOnNavigationItemSelectedListener(this)
    }

    override fun onStart() {
        super.onStart()
        updateNavigationBarState()
    }

    public override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }


    private fun updateNavigationBarState() {
        val actionId = getNavigationMenuItemId()
        selectBottomNavigationBarItem(actionId)
    }

    internal fun selectBottomNavigationBarItem(itemId: Int) {
        val menu = navigation.menu
        var i = 0
        val size = menu.size()
        while (i < size) {
            val item = menu.getItem(i)
            val shouldBeChecked = item.itemId == itemId
            if (shouldBeChecked) {
                item.isChecked = true
                break
            }
            i++
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

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Do you want to exit ?")
        builder.setPositiveButton("Yes") { dialog, which ->
            overridePendingTransition(
                R.anim.anim_slide_in_left,
                R.anim.anim_slide_out_left
            )
            finish()
        }
        builder.setNegativeButton(
            "No"
        ) { dialog, which -> dialog.dismiss() }
        builder.create().show()
    }

}