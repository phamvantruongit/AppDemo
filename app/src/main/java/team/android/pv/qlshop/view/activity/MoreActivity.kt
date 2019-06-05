package team.android.pv.qlshop.view.activity

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_more.*
import kotlinx.android.synthetic.main.toolbar.*
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.ListMore
import team.android.pv.qlshop.model.User
import team.android.pv.qlshop.model.data.SharedPreferencesManager
import team.android.pv.qlshop.view.DividerItemDecoration
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
        arrayOf("Khach hang", "Nha cung cap" , "San pham", "Danh muc", "Nhan hieu", "Them nhan vien", "Danh sach nhan vien")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()

        tvTitle.text = this.resources.getText(R.string.title_more)
        imgRight.visibility = View.VISIBLE
        imgRight.setImageDrawable(resources.getDrawable(R.drawable.ic_add))


        imgRight.setOnClickListener {
            SharedPreferencesManager.logOut(true)
            startActivity(Intent(this, LoginActivity::class.java))
        }

        rvListMore.layoutManager = LinearLayoutManager(this)
        rvListMore.addItemDecoration(DividerItemDecoration(resources.getDrawable(R.drawable.divider)))
        var listMore: ArrayList<ListMore> = ArrayList()
        for (i in 0..iv_left.size - 1) {
            var more = ListMore(iv_left[i], list_title[i], iv_right[i])
            listMore.add(more)
        }

        rvListMore.adapter = AdapterListMore(listMore, this)


    }

    private fun init() {
        tvNameUser.text = ""
        tvEmailUser.text = ""
    }

    override fun onClickItem(position: Int) {



        var intent: Intent? = null


        if (position == 0) {
            intent = Intent(this@MoreActivity, CustomerActivity::class.java)
            startActivity(intent)
        }

        if(position==1){
            var intent=Intent(this,ActivitySupplier::class.java)
            startActivity(intent)
        }

        if (position == 2) {
            intent= Intent(this@MoreActivity, AddProductActivity::class.java)
            startActivity(intent)
        }

        if (position == 3) {
            intent = Intent(this@MoreActivity, AddCategoryActivity::class.java)
            intent.putExtra("checkCategory", true)
            intent.putExtra("pushMore",true)
            startActivity(intent)
        }

        if (position == 4) {
            intent = Intent(this@MoreActivity, AddCategoryActivity::class.java)
            intent.putExtra("checkCategory", false)
            intent.putExtra("pushMore",true)
            startActivity(intent)
        }

        if (position == 5) {
            if(userSave!!.check_admin.equals("admin")){
                intent = Intent(this@MoreActivity, RegisterActivity::class.java)
                intent.putExtra("check_admin", 0)
                startActivity(intent)
            }else{
                Toast.makeText(this,"Ban khong co quyen truy cap",Toast.LENGTH_SHORT).show()
            }

        }

        if (position == 6) {
            intent= Intent(this@MoreActivity, MemmbersActivity::class.java)
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