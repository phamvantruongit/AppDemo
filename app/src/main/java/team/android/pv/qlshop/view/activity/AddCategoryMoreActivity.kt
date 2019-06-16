package team.android.pv.qlshop.view.activity

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*
import kotlinx.android.synthetic.main.activity_add_category.*
import kotlinx.android.synthetic.main.dialog_add_category.*
import kotlinx.android.synthetic.main.toolbar.*
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.Category
import team.android.pv.qlshop.presenter.category.CategoryInteractor
import team.android.pv.qlshop.presenter.category.CategoryPresenter
import team.android.pv.qlshop.view.DividerItemDecoration
import team.android.pv.qlshop.view.adapter.AdapterBrand
import team.android.pv.qlshop.view.adapter.AdapterBrandMore
import team.android.pv.qlshop.view.adapter.AdapterCategory
import team.android.pv.qlshop.view.adapter.AdapterCategoryMore
import team.android.pv.qlshop.view.view.ViewAddCategory

class AddCategoryMoreActivity : BaseActivitys(), ViewAddCategory,
    AdapterCategoryMore.IOnClickItem, AdapterBrandMore.IOnClickItem {


    private var categoryPresenter: CategoryPresenter? = null

    private var checkCategory: Boolean = false
    private var category: Category? = Category()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_category)

        imgRight.visibility = View.VISIBLE
        var tvTitle=findViewById<TextView>(R.id.tvTitle)




        categoryPresenter = CategoryPresenter(this, CategoryInteractor())



        rv_category.layoutManager = LinearLayoutManager(this)
        rv_category.addItemDecoration(DividerItemDecoration(resources.getDrawable(R.drawable.divider)))



        checkCategory = intent.getBooleanExtra("checkCategory", false)
        if(checkCategory) {
            tvTitle.setText(getString(R.string.title_category))
        }else{
            tvTitle.setText(getString(R.string.title_brand))
        }



        categoryPresenter!!.getCategory(userEntity!!.id_shop, checkCategory)

        imgRight.setImageDrawable(resources.getDrawable(R.drawable.ic_add))

        imgRight.setOnClickListener {

            showDialog(null,false)

        }

    }


    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun setSuccess(success: String) {
        categoryPresenter!!.getCategory(1, checkCategory)
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    override fun getListCategory(listCategory: ArrayList<Category>) {
        if (checkCategory ) {
            rv_category!!.adapter = AdapterCategoryMore(listCategory, this)
        }
        if(!checkCategory ){
            rv_category!!.adapter = AdapterBrandMore(listCategory,  this)
        }

    }


    override fun onClickEditCategory(category: Category) {
        this.category = category
        showDialog(this.category!!,true)
    }

    private fun showDialog(category: Category ? ,check:Boolean) {
        var dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_add_category)
        var tvTitle = dialog.findViewById(R.id.tvTitle) as TextView
        var edCategory = dialog.findViewById(R.id.edCategory) as EditText
        dialog.show()

        dialog!!.setCancelable(false)
        if (dialog!!.window != null) {
            dialog!!.window!!.setGravity(Gravity.CENTER)
            dialog!!.window!!.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }


        if (!checkCategory) {
            edCategory.hint=getString(R.string.name_brand)
            tvTitle.text = getString(R.string.them_brand)

        }else{

            edCategory.hint=getString(R.string.name_category)
            tvTitle.text = getString(R.string.them_category)
        }


        var btnAddCategory = dialog.findViewById(R.id.btnAddCategory) as TextView
        if (check) {
            if(checkCategory){
                tvTitle.text = getString(R.string.update_categoty)
            }else{
                tvTitle.text = getString(R.string.update_brand)
            }
            edCategory.setText(category!!.name)
            btnAddCategory.text = getString(R.string.edit)
        }else{
            btnAddCategory.text = getString(R.string.add)
        }
        btnAddCategory.setOnClickListener {
            var name = edCategory.text.toString()
            if(name.length==0){
                edCategory.error=getString(R.string.enter_info)
                return@setOnClickListener
            }
            if (check) {
                categoryPresenter!!.editCategory(name, userEntity!!.id_shop, category!!.id, checkCategory)
            } else {
                categoryPresenter!!.addCategory(name, userEntity!!.id_shop, checkCategory)
            }
            dialog.dismiss()
        }

        dialog!!.btnExit.setOnClickListener {
            dialog!!.cancel()
            dialog!!.dismiss()
        }
    }


}
