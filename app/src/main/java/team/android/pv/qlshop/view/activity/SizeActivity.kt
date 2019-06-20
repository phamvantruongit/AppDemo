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
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_size.*
import kotlinx.android.synthetic.main.show_dialog_size.*
import kotlinx.android.synthetic.main.toolbar.*
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.Category
import team.android.pv.qlshop.presenter.category.CategoryInteractor
import team.android.pv.qlshop.presenter.category.CategoryPresenter
import team.android.pv.qlshop.view.DividerItemDecoration
import team.android.pv.qlshop.view.adapter.AdapterBrandMore
import team.android.pv.qlshop.view.adapter.AdapterCategory
import team.android.pv.qlshop.view.adapter.AdapterSize
import team.android.pv.qlshop.view.adapter.AdapterSizeMore
import team.android.pv.qlshop.view.view.ViewAddCategory

class SizeActivity : BaseActivitys(), ViewAddCategory, AdapterCategory.IOnClickItem, AdapterBrandMore.IOnClickItem,
    AdapterSizeMore.IOnClickItem, AdapterSize.IOnClickItem {


    var categoryPresenter: CategoryPresenter? = null
    var dialog: Dialog? = null
    var checkSize: Boolean = false
    var category: Category? = Category()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_size)

        var tvTitle = findViewById<TextView>(R.id.tvTitle)
        tvTitle.setText(getString(R.string.size))

        checkSize = intent.getBooleanExtra("checkSize", false)

        rv_size.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?

        categoryPresenter = CategoryPresenter(this, CategoryInteractor())
        if(hasNetwork()) {
            categoryPresenter!!.getSize(userEntity!!.id_shop)
        }


        imgRight.setOnClickListener {
            showDialogApp(null,false)
        }

    }

    private fun showDialogApp(category: Category?, check: Boolean) {

        dialog = Dialog(this)
        dialog!!.setContentView(R.layout.show_dialog_size)
        dialog!!.setCancelable(false)
        dialog!!.show()
        if (dialog!!.window != null) {
            dialog!!.window!!.setGravity(Gravity.CENTER)
            dialog!!.window!!.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        var edSize = dialog!!.findViewById<EditText>(R.id.edSize)
        if (check) {
            edSize.setText(category!!.name)
            dialog!!.tv_title.text = getString(R.string.update_size)
            dialog!!.btnAddSize.text = getString(R.string.edit)

        }
        dialog!!.btnExit.setOnClickListener {
            dialog!!.dismiss()
            dialog!!.cancel()
        }

        dialog!!.btnAddSize.setOnClickListener {
            if (edSize.text.trim().length > 0) {
                if(hasNetwork())
                categoryPresenter!!.addSize(edSize.text.toString(), userEntity!!.id_shop)

            } else {
                edSize.error = getString(R.string.enter_info)
            }
        }

    }

    override fun getListCategory(listCategory: ArrayList<Category>) {
        rv_size.addItemDecoration(DividerItemDecoration(resources.getDrawable(R.drawable.divider)))
        if (checkSize) {
            rv_size.adapter = AdapterSizeMore(listCategory, this)
        } else {
            rv_size.adapter = AdapterSize(listCategory, this)
        }
    }

    override fun onClickItem(category: Category, check_visible: Boolean) {
        var intent = Intent()
        intent.putExtra("size", category)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun onClickEditCategory(category: Category) {
        showDialogApp(category, false)
    }


    override fun setSuccess(message: String) {

    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun showMessage(message: String) {
        categoryPresenter!!.getSize(userEntity!!.id_shop)
       // dialog!!.dismiss()
    }
}
