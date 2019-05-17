package team.android.pv.qlshop.view.activity

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.dialog_add_category.*
import kotlinx.android.synthetic.main.toolbar.*
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.Category
import team.android.pv.qlshop.presenter.category.CategoryInteractor
import team.android.pv.qlshop.presenter.category.CategoryPresenter
import team.android.pv.qlshop.view.adapter.AdapterCategory
import team.android.pv.qlshop.view.views.ViewAddCategory

class AddCategoryActivity : AppCompatActivity() ,ViewAddCategory, AdapterCategory.IOnClickItem {
    private lateinit var categoryPresenter: CategoryPresenter
    private  var rv_category:RecyclerView?=null
    private  var check=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_category)

        categoryPresenter= CategoryPresenter(this, CategoryInteractor())

        rv_category!!.layoutManager=LinearLayoutManager(this)



         check=intent.getBooleanExtra("check",false)


        categoryPresenter.getCategory(1,check)

        imgRight.setImageDrawable(resources.getDrawable(R.drawable.ic_add))

        imgRight.visibility=View.VISIBLE
        imgRight.setOnClickListener({
            var dialog=Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.dialog_add_category)
            var tvTitle=dialog.findViewById(R.id.tvTitle) as TextView
            var edCategory = dialog.findViewById(R.id.edCategory) as EditText
            dialog.show()
            if(!check){
                tvTitle.text=getString(R.string.them_brand)

            }

            var name=edCategory.text.toString()
            if(TextUtils.isEmpty(name)){
                return@setOnClickListener
            }


            categoryPresenter.addCategory(name,1,check)



        })


    }


    override fun showProgress() {

    }

    override fun hideProgress() {

    }


    override fun setSuccess(success: String) {
        categoryPresenter.getCategory(1,check)
        Toast.makeText(this,success,Toast.LENGTH_SHORT).show()
    }

    override fun setDataError(error: String) {
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show()
    }


    override fun getListCategory(listCategory: ArrayList<Category>) {
         rv_category!!.adapter=AdapterCategory(listCategory,this)
    }


    override fun onClickItem(category: Category) {

    }


}
