package team.android.pv.qlshop.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_more.*
import kotlinx.android.synthetic.main.toolbar.*
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.ListMore
import team.android.pv.qlshop.model.data.SharedPreferencesManager
import team.android.pv.qlshop.view.DividerItemDecoration
import team.android.pv.qlshop.view.adapter.AdapterListMore


class MoreActivity : BaseActivity(), AdapterListMore.IOnClick {


    val iv_right: IntArray = intArrayOf(
        R.drawable.iv_arrow,
        R.drawable.iv_arrow,
        R.drawable.iv_arrow,
        R.drawable.iv_arrow,
        R.drawable.iv_arrow,
        R.drawable.iv_arrow,
        R.drawable.iv_arrow,
        R.drawable.iv_arrow
    )
    val iv_left: IntArray = intArrayOf(
        R.drawable.icon_home,
        R.drawable.icon_home,
        R.drawable.icon_home,
        R.drawable.icon_home,
        R.drawable.icon_home,
        R.drawable.icon_home,
        R.drawable.icon_home,
        R.drawable.icon_home
    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var list_title: Array<String> =
            arrayOf(getString(R.string.customer), getString(R.string.supplier) , getString(R.string.product), getString(R.string.category), getString(
                R.string.brand),getString(
                R.string.size), getString(R.string.add_em), getString(R.string.ems))


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
            var intent=Intent(this,ActivitySupplier::class.java)
            intent.putExtra("checkCustomer",true)
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
            intent = Intent(this@MoreActivity, AddCategoryMoreActivity::class.java)
            intent.putExtra("checkCategory", true)
            startActivity(intent)
        }

        if (position == 4) {
            intent = Intent(this@MoreActivity, AddCategoryMoreActivity::class.java)
            intent.putExtra("checkCategory", false)
            startActivity(intent)
        }

        if(position==5){
            intent = Intent(this, SizeActivity::class.java)
            startActivity(intent)
        }

        if (position == 6) {
            if(userEntity!!.check_admin.equals("admin")){
                intent = Intent(this@MoreActivity, RegisterActivity::class.java)
                intent.putExtra("check_admin", 0)
                startActivity(intent)
            }else{
                Toast.makeText(this,"Ban khong co quyen truy cap",Toast.LENGTH_SHORT).show()
            }

        }

        if (position == 7) {
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