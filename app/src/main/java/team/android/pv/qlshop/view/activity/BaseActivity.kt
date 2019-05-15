package team.android.pv.qlshop.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import team.android.pv.qlshop.R

abstract class BaseActivity :AppCompatActivity() ,BottomNavigationView.OnNavigationItemSelectedListener  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentView())
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var int:Intent
        var itemId:Int=item.itemId
        if(itemId== R.id.navigation_home){
        }
        return true
    }

    protected abstract fun getContentView():Int
    abstract fun getNavigationMenuItemId() : Int

}