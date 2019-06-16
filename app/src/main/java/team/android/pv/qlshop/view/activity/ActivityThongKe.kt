package team.android.pv.qlshop.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import team.android.pv.qlshop.R

class ActivityThongKe : BaseActivity() {
    override fun getContentView(): Int {
        return R.layout.activity_thongke
    }

    override fun getNavigationMenuItemId(): Int {
        return  return R.id.navigation_thongke
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
