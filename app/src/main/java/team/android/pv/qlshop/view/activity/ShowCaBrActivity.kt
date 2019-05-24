package team.android.pv.qlshop.view.activity

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_show_ca_br.*
import kotlinx.android.synthetic.main.toolbar.*
import org.json.JSONObject
import team.android.pv.qlshop.R

class ShowCaBrActivity : AppCompatActivity() {
    private var nameCategory = ""
    private var nameBrand = ""
    private var dataJson = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_ca_br)
        imgRight.visibility = View.VISIBLE



        fl_category.setOnClickListener {
            var intent = Intent(this, AddCategoryActivity::class.java)
            intent.putExtra("check", true)
            intent.putExtra("pushMore",false)
            startActivityForResult(intent, 100)
        }


        fl_brand.setOnClickListener {
            var intent = Intent(this, AddCategoryActivity::class.java)
            intent.putExtra("check", false)
            intent.putExtra("pushMore",false)
            startActivityForResult(intent, 101)
        }

        imgRight.setOnClickListener {

            if (dataJson != "") {
                var intent = Intent()
                intent.putExtra("data", dataJson)
                setResult(Activity.RESULT_OK, intent)

            }
            finish()

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            tvCategory.text = data!!.getStringExtra("name")
            nameCategory = data.getStringExtra("name")

        }

        if (requestCode == 101 && resultCode == Activity.RESULT_OK) {
            tvBrand.text = data!!.getStringExtra("name")
            nameBrand = data.getStringExtra("name")
        }

        var json = JSONObject()
        json.put("brand", nameBrand)
        json.put("category", nameCategory)
        dataJson = json.toString()
    }
}
