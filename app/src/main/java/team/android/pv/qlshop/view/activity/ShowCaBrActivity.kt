package team.android.pv.qlshop.view.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_show_ca_br.*
import kotlinx.android.synthetic.main.toolbar.*
import team.android.pv.qlshop.R

class ShowCaBrActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_ca_br)

        fl_category.setOnClickListener({
            var intent=Intent(this,AddCategoryActivity::class.java)
            intent.putExtra("check",true)
            startActivityForResult(intent,100)
        })


        fl_brand.setOnClickListener({
            var intent=Intent(this,AddCategoryActivity::class.java)
            intent.putExtra("check",false)
            startActivityForResult(intent,101)
        })



    }
}
