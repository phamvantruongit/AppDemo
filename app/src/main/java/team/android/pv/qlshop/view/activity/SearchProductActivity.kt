package team.android.pv.qlshop.view.activity

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.google.android.gms.vision.barcode.Barcode
import com.notbytes.barcode_reader.BarcodeReaderActivity
import kotlinx.android.synthetic.main.activity_products.*
import kotlinx.android.synthetic.main.activity_search_product.*
import kotlinx.android.synthetic.main.show_dialog_category.*
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.Category
import team.android.pv.qlshop.model.Product
import team.android.pv.qlshop.presenter.category.CategoryPresenter
import team.android.pv.qlshop.presenter.searchproduct.SearchProductInteractor
import team.android.pv.qlshop.presenter.searchproduct.SearchProductPresenter
import team.android.pv.qlshop.view.DividerItemDecoration
import team.android.pv.qlshop.view.adapter.AdapterCategorys
import team.android.pv.qlshop.view.adapter.AdapterProduct
import team.android.pv.qlshop.view.views.ViewProductSearch


class SearchProductActivity : BaseActivitys(), ViewProductSearch, AdapterProduct.IOnClick,
    AdapterCategorys.IOnClickItem {



    private lateinit var searchProductPresenter: SearchProductPresenter
    private var barcode = ""
    var rv_category: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_product)

        searchProductPresenter = SearchProductPresenter(this, SearchProductInteractor())
        searchProduct("ao")

        edSearch.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (edSearch.text.toString().length > 0) {
                        searchProduct(edSearch.text.toString())
                    }
                    return true
                }
                return false
            }
        })
        iv_search_barcode.setOnClickListener {
            var intent = BarcodeReaderActivity.getLaunchIntent(this, true, false)
            startActivityForResult(intent, 100)
        }

        tvSearch.setOnClickListener {
            var dialog=Dialog(this)
            dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog!!.setContentView(R.layout.show_dialog_category)
            dialog!!.show()
            rv_category = dialog!!.findViewById(R.id.rv_categorys)
            searchProductPresenter.getListCategory(userSave!!.id_shop)
            if (dialog!!.window != null) {
                dialog!!.window!!.setGravity(Gravity.BOTTOM)
                dialog!!.window!!.setLayout(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT
                )
                dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
            dialog!!.tvLoadAll.setOnClickListener {
                dialog!!.dismiss()
            }
        }



    }


    override fun getListCategory(listCategory: ArrayList<Category>) {
        rv_category!!.layoutManager = LinearLayoutManager(this)
        rv_category!!.addItemDecoration(DividerItemDecoration(resources.getDrawable(R.drawable.divider)))
        rv_category!!.adapter = AdapterCategorys(listCategory, this)
    }

    override fun onClickItem(id_category: Int, selected_position: Int) {

    }

    private fun searchProduct(name: String) {
        if (barcode != null || barcode != "") {
            searchProductPresenter.searchProduct(userSave!!.id_shop, barcode, "0")
          }else{
            searchProductPresenter.searchProduct(userSave!!.id_shop, barcode, name)
        }
    }

    override fun getListSearchProduct(listProduct: List<Product>) {
        rv_product_search.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        rv_product_search.adapter = AdapterProduct(listProduct as ArrayList<Product>, this)
    }


    override fun iOnCLickItem(product: Product) {

    }

    override fun showProgress() {

    }

    override fun hideProgress() {
    }

    override fun showMessage(message: String) {

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && data != null) {
            barcode = data.getParcelableExtra<Barcode>(BarcodeReaderActivity.KEY_CAPTURED_BARCODE).rawValue
        }
    }


}