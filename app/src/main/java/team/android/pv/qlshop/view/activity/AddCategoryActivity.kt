package team.android.pv.qlshop.view.activity

import android.app.Dialog
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
import kotlinx.android.synthetic.main.dialog_add_category.*
import kotlinx.android.synthetic.main.toolbar.*
import team.android.pv.qlshop.R
import team.android.pv.qlshop.R.id.rv_category
import team.android.pv.qlshop.model.Category
import team.android.pv.qlshop.model.Product
import team.android.pv.qlshop.presenter.category.CategoryInteractor
import team.android.pv.qlshop.presenter.category.CategoryPresenter
import team.android.pv.qlshop.presenter.register.ResgisterPresenter
import team.android.pv.qlshop.view.adapter.AdapterCategory
import team.android.pv.qlshop.view.views.ViewAddCategory

class AddCategoryActivity : AppCompatActivity() ,ViewAddCategory, AdapterCategory.IOnClickItem {
    private  var categoryPresenter: CategoryPresenter? =null

    var check : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_category)

        categoryPresenter= CategoryPresenter(this, CategoryInteractor())



        rv_category.layoutManager=LinearLayoutManager(this)



         check=intent.getBooleanExtra("check",false)


        categoryPresenter!!.getCategory(1,check)

        imgRight.setImageDrawable(resources.getDrawable(R.drawable.ic_add))

        imgRight.visibility=View.VISIBLE
        imgRight.setOnClickListener({

            showDialog()


        })



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
         rv_category!!.adapter=AdapterCategory(listCategory,this)
    }


    override fun onClickItem(category: Category) {
        var dialog=Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.show_dialog)
        dialog.show()
        if (dialog.window != null) {
            dialog.window!!.setGravity(Gravity.BOTTOM)
            dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        }

        var tvAdd =dialog.findViewById<TextView>(R.id.tvAddProduct)
        var tvEdit = dialog.findViewById<TextView>(R.id.tvEdiProduct)
        var tvCancel= dialog.findViewById<TextView>(R.id.tvCancel)

        tvAdd.setOnClickListener{
            showDialog()
            dialog.dismiss()
        }

        tvEdit.setOnClickListener{
            showDialog()
            dialog.dismiss()
        }

        tvCancel.setOnClickListener {
            dialog.dismiss()
        }


    }


}
