package team.android.pv.qlshop.view.activity

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_size.*
import kotlinx.android.synthetic.main.show_dialog_size.*
import kotlinx.android.synthetic.main.toolbar.*
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.Category
import team.android.pv.qlshop.presenter.category.CategoryInteractor
import team.android.pv.qlshop.presenter.category.CategoryPresenter
import team.android.pv.qlshop.view.adapter.AdapterCategory
import team.android.pv.qlshop.view.view.ViewAddCategory

class SizeActivity : BaseActivitys(), ViewAddCategory, AdapterCategory.IOnClickItem {


    var categoryPresenter: CategoryPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_size)

        rv_size.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?

        categoryPresenter = CategoryPresenter(this, CategoryInteractor())
        categoryPresenter!!.getSize(userEntity!!.id_shop)




        imgRight.setOnClickListener {
            var dialog = Dialog(this)
            dialog.setContentView(R.layout.show_dialog_size)
            dialog!!.show()
            if (dialog!!.window != null) {
                dialog!!.window!!.setGravity(Gravity.CENTER)
                dialog!!.window!!.setLayout(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT
                )
                dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }

            var edSize = dialog.findViewById<EditText>(R.id.edSize)
            var btnAddSize = dialog.findViewById<Button>(R.id.btnAddSize)
            edSize.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {

                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s!!.trim().length > 0) {
                        btnAddSize.setBackgroundResource(R.drawable.boder_login_select)
                        btnAddSize.setTextColor(resources.getColor(R.color.white))
                    } else {
                        btnAddSize.setBackgroundResource(R.drawable.boder_login_btn)
                        btnAddSize.setTextColor(resources.getColor(R.color.black))
                    }
                }

            })

            btnAddSize.setOnClickListener {
                if(edSize.text.trim().length>0) {
                    categoryPresenter!!.addSize(edSize.text.toString(), userEntity!!.id_shop)
                }
            }
        }
    }

    override fun getListCategory(listCategory: ArrayList<Category>) {
        rv_size.adapter=AdapterCategory(listCategory,this)
    }

    override fun onClickItem(category: Category, check_visible: Boolean) {

    }

    override fun onClickEditCategory(category: Category) {

    }




    override fun setSuccess(message: String) {

    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun showMessage(message: String) {

    }
}
