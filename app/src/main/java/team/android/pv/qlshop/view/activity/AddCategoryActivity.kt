package team.android.pv.qlshop.view.activity

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*
import kotlinx.android.synthetic.main.activity_add_category.*
import kotlinx.android.synthetic.main.toolbar.*
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.Category
import team.android.pv.qlshop.presenter.category.CategoryInteractor
import team.android.pv.qlshop.presenter.category.CategoryPresenter
import team.android.pv.qlshop.view.DividerItemDecoration
import team.android.pv.qlshop.view.adapter.AdapterCategory
import team.android.pv.qlshop.view.views.ViewAddCategory

class AddCategoryActivity : BaseActivitys() ,ViewAddCategory, AdapterCategory.IOnClickItem {


    private  var categoryPresenter: CategoryPresenter? =null

    private var check : Boolean = false
    private var nameCategory:String=""
    private var pushMore : Boolean = false
    private var category:Category?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_category)

        imgRight.visibility=View.VISIBLE

        categoryPresenter= CategoryPresenter(this, CategoryInteractor())



        rv_category.layoutManager= LinearLayoutManager(this)
        rv_category.addItemDecoration(DividerItemDecoration(resources.getDrawable(R.drawable.divider)))



         check=intent.getBooleanExtra("check",false)
         pushMore=intent.getBooleanExtra("pushMore",false)


        categoryPresenter!!.getCategory(1,check)

        imgRight.setImageDrawable(resources.getDrawable(R.drawable.ic_add))

        imgRight.setOnClickListener{
            category= Category()

            showDialog(this!!.category!!)

        }

    }



    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun setSuccess(success: String) {
        categoryPresenter!!.getCategory(1,check)
        Toast.makeText(this,success,Toast.LENGTH_SHORT).show()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
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

    override fun onClickEditCategory(category: Category) {
        this.category=category
        showDialog(this.category!!)
    }

    private fun showDialog(category: Category) {
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
        if(category!=null){
             edCategory.setText(category.name)
             btnAddCategory.setText("Edit")
        }
        btnAddCategory.setOnClickListener{
            var name=edCategory.text.toString()
            if(category.name!=""){
                categoryPresenter!!.editCategory(name,userSave!!.id_shop,category.id,check)
            }else {
                categoryPresenter!!.addCategory(name, userSave!!.id_shop, check)
            }
            dialog.dismiss()
        }
    }


}
