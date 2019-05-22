package team.android.pv.qlshop.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_more.*
import kotlinx.android.synthetic.main.toolbar.*
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.ListMore
import team.android.pv.qlshop.model.User
import team.android.pv.qlshop.model.data.SharedPreferencesManager
import team.android.pv.qlshop.view.adapter.AdapterListMore

class MoreActivity : BaseActivity(), AdapterListMore.IOnClick {


    val iv_right: IntArray = intArrayOf(
        R.drawable.icon_right_more,
        R.drawable.icon_right_more,
        R.drawable.icon_right_more,
        R.drawable.icon_right_more,
        R.drawable.icon_right_more,
        R.drawable.icon_right_more,
        R.drawable.icon_right_more
    )
    val iv_left: IntArray = intArrayOf(
        R.drawable.icon_home,
        R.drawable.icon_home,
        R.drawable.icon_home,
        R.drawable.icon_home,
        R.drawable.icon_home,
        R.drawable.icon_home,
        R.drawable.icon_home
    )
    var list_title: Array<String> =
        arrayOf("Khach hang", "San pham", "Danh muc", "Nhan hieu", "Them nhan vien", "Danh sach nhan vien", "Dang xuat")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()

        tvTitle.text = this.resources.getText(R.string.title_more)

        rvListMore.layoutManager = LinearLayoutManager(this)
        var listMore: ArrayList<ListMore> = ArrayList()
        for (i in 0..iv_left.size - 1) {
            var more = ListMore(iv_left[i], list_title[i], iv_right[i]);
            listMore!!.add(more)
        }

        rvListMore.adapter = AdapterListMore(listMore, this)


    }

    private fun init() {
        tvNameUser.text = ""
        tvEmailUser.text = ""
    }

    override fun onClickItem(position: Int) {

        SharedPreferencesManager.getInstanceSharedPreferencesManager(this)
        val user: User?=SharedPreferencesManager.getUser()

        var intent: Intent? = null


        if (position == 0) {
            intent = Intent(this@MoreActivity, CustomerActivity::class.java)
            startActivity(intent)
        }

        if (position == 1) {
            intent= Intent(this@MoreActivity, AddProductActivity::class.java)
            startActivity(intent)
        }

        if (position == 2) {
            intent = Intent(this@MoreActivity, AddCategoryActivity::class.java)
            intent.putExtra("check", true)
            startActivity(intent)
        }

        if (position == 3) {
            intent = Intent(this@MoreActivity, AddCategoryActivity::class.java)
            intent.putExtra("check", false)
            startActivity(intent)
        }

        if (position == 4) {
            if(user!!.check_admin.equals("admin")){
                intent = Intent(this@MoreActivity, RegisterActivity::class.java)
                intent.putExtra("check_admin", 0)
                startActivity(intent)
            }else{
                Toast.makeText(this,"Ban khong co quyen truy cap",Toast.LENGTH_SHORT).show()
            }

        }

        if (position == 5) {
            intent= Intent(this@MoreActivity, MemmbersActivity::class.java)
            startActivity(intent)
        }

        if (position == 6) {
            SharedPreferencesManager.getInstanceSharedPreferencesManager(this)
            SharedPreferencesManager.logOut()
            startActivity(Intent(this, LoginActivity::class.java))
        }


    }

    override fun getContentView(): Int {
        return R.layout.activity_more
    }

    override fun getNavigationMenuItemId(): Int {
        return R.id.navigation_more
    }
}