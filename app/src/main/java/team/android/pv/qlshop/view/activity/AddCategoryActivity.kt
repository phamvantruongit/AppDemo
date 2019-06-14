package team.android.pv.qlshop.view.activity

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
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

class AddCategoryActivity : BaseActivitys(), ViewAddCategory, AdapterCategory.IOnClickItem, AdapterBrand.IOnClickItem,
    AdapterCategoryMore.IOnClickItem, AdapterBrandMore.IOnClickItem {


    private var categoryPresenter: CategoryPresenter? = null

    private var checkCategory: Boolean = false

    private var category: Category? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_category)

        imgRight.visibility = View.VISIBLE

        categoryPresenter = CategoryPresenter(this, CategoryInteractor())



        rv_category.layoutManager = LinearLayoutManager(this)
        rv_category.addItemDecoration(DividerItemDecoration(resources.getDrawable(R.drawable.divider)))



        checkCategory = intent.getBooleanExtra("checkCategory", false)




        categoryPresenter!!.getCategory(userEntity!!.id_shop, checkCategory)

        imgRight.setImageDrawable(resources.getDrawable(R.drawable.ic_add))

        imgRight.setOnClickListener {
            category = Category()

            showDialog(this.category!!)

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

        if ( checkCategory) {
            rv_category!!.adapter = AdapterCategory(listCategory, this)
        } else {
            rv_category!!.adapter = AdapterBrand(listCategory, this)
        }
    }

    override fun onClickItem(category: Category, check_visible: Boolean) {


        var intent = Intent()
        intent.putExtra("category", category)
        setResult(Activity.RESULT_OK, intent)
        finish()


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
        var btnAddCategory = dialog.findViewById(R.id.btnAddCategory) as Button

        edCategory.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s!!.trim().length>0){
                    btnAddCategory.setBackgroundResource(R.drawable.boder_login_select)
                    btnAddCategory.setTextColor(resources.getColor(R.color.white))
                }else{
                    btnAddCategory.setBackgroundResource(R.drawable.boder_login_btn)
                    btnAddCategory.setTextColor(resources.getColor(R.color.black))
                }
            }

        })

        dialog.show()
        if (dialog!!.window != null) {
            dialog!!.window!!.setGravity(Gravity.CENTER)
            dialog!!.window!!.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }


        if (!checkCategory) {
            tvTitle.text = getString(R.string.them_brand)
        }


        if (category != null) {
            edCategory.setText(category.name)
            btnAddCategory.text = "Edit"
        }
        btnAddCategory.setOnClickListener {
            var name = edCategory.text.toString()
            if (name.length == 0) {
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
