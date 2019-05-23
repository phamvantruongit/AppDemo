package team.android.pv.qlshop.view.activity

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*
import kotlinx.android.synthetic.main.activity_add_category.*
import kotlinx.android.synthetic.main.activity_show_ca_br.*
import kotlinx.android.synthetic.main.dialog_add_category.*
import kotlinx.android.synthetic.main.toolbar.*
import team.android.pv.qlshop.R
import team.android.pv.qlshop.R.id.rv_category
import team.android.pv.qlshop.model.Category
import team.android.pv.qlshop.model.Product
import team.android.pv.qlshop.presenter.category.CategoryInteractor
import team.android.pv.qlshop.presenter.category.CategoryPresenter
import team.android.pv.qlshop.presenter.register.ResgisterPresenter
import team.android.pv.qlshop.view.DividerItemDecoration
import team.android.pv.qlshop.view.adapter.AdapterCategory
import team.android.pv.qlshop.view.views.ViewAddCategory

class AddCategoryActivity : AppCompatActivity() ,ViewAddCategory, AdapterCategory.IOnClickItem {
    private  var categoryPresenter: CategoryPresenter? =null

    private var check : Boolean = false
    private var nameCategory:String=""
    private var pushMore : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_category)

        imgRight.visibility=View.VISIBLE

        categoryPresenter= CategoryPresenter(this, CategoryInteractor())



        rv_category.layoutManager= LinearLayoutManager(this) as RecyclerView.LayoutManager?
        rv_category.addItemDecoration(DividerItemDecoration(resources.getDrawable(R.drawable.divider)))



         check=intent.getBooleanExtra("check",false)
         pushMore=intent.getBooleanExtra("pushMore",false)


        categoryPresenter!!.getCategory(1,check)

        imgRight.setImageDrawable(resources.getDrawable(R.drawable.ic_add))

        imgRight.visibility=View.VISIBLE
        imgRight.setOnClickListener({

            showDialog()

        })

        imgRight.setOnClickListener{
            if (nameCategory != "") {
                Log.d("Data",nameCategory)
                var intent = Intent()
                intent.putExtra("name", nameCategory)
                setResult(Activity.RESULT_OK, intent)
            }
            finish()
        }




    }

    private fun showDialog(){
        var dialog=Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_add_category)
        var tvTitle=dialog.findViewById(R.id.tvTitle) as TextView
        var edCategory = dialog.findViewById(R.id.edCategory) as EditText
        dialog.show()
        if(!check){
            tvTitle.text=getString(R.string.them_brand)

        }



        var btnAddCategory=dialog.findViewById(R.id.btnAddCategory) as Button
        btnAddCategory.setOnClickListener({
            var name=edCategory.text.toString()
            categoryPresenter!!.addCategory(name,1,check)
            dialog.dismiss()
        })

    }


    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun setSuccess(success: String) {
        categoryPresenter!!.getCategory(1,check)
        Toast.makeText(this,success,Toast.LENGTH_SHORT).show()
    }

    override fun setDataError(error: String) {
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show()
    }


    override fun getListCategory(listCategory: ArrayList<Category>) {
         rv_category!!.adapter=AdapterCategory(listCategory, pushMore,this)
    }

    override fun onClickItem(category: Category) {
        nameCategory=category.name
        var dialog=Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.show_dialog)
        //dialog.show()
        if (dialog.window != null) {
            dialog.window!!.setGravity(Gravity.BOTTOM)
            dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }



}
