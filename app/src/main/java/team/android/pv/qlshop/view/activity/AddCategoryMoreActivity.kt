package team.android.pv.qlshop.view.activity

import android.app.Activity
import android.app.Dialog
import android.content.Intent
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
    private var category: Category? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_category)

        imgRight.visibility = View.VISIBLE

        categoryPresenter = CategoryPresenter(this, CategoryInteractor())



        rv_category.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        rv_category.addItemDecoration(DividerItemDecoration(resources.getDrawable(R.drawable.divider)))



        checkCategory = intent.getBooleanExtra("checkCategory", false)



        categoryPresenter!!.getCategory(userEntity!!.id_shop, checkCategory)

        imgRight.setImageDrawable(resources.getDrawable(R.drawable.ic_add))

        imgRight.setOnClickListener {
            category = Category()

            showDialog(this!!.category!!)

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
        showDialog(this.category!!)
    }

    private fun showDialog(category: Category) {
        var dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_add_category)
        var tvTitle = dialog.findViewById(R.id.tvTitle) as TextView
        var edCategory = dialog.findViewById(R.id.edCategory) as EditText
        dialog.show()


        if (!checkCategory) {
            tvTitle.text = getString(R.string.them_brand)

        }


        var btnAddCategory = dialog.findViewById(R.id.btnAddCategory) as Button
        if (category != null) {
            edCategory.setText(category.name)
            btnAddCategory.setText("Edit")
        }
        btnAddCategory.setOnClickListener {
            var name = edCategory.text.toString()
            if(name.length==0){
                dialog.dismiss()
                return@setOnClickListener
            }
            if (category.name != "") {
                categoryPresenter!!.editCategory(name, userEntity!!.id_shop, category.id, checkCategory)
            } else {
                categoryPresenter!!.addCategory(name, userEntity!!.id_shop, checkCategory)
            }
            dialog.dismiss()
        }
    }


}
