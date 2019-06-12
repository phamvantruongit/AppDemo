package team.android.pv.qlshop.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.google.android.gms.vision.barcode.Barcode
import com.notbytes.barcode_reader.BarcodeReaderActivity
import kotlinx.android.synthetic.main.activity_search_product.*
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.Product
import team.android.pv.qlshop.presenter.searchproduct.SearchProductInteractor
import team.android.pv.qlshop.presenter.searchproduct.SearchProductPresenter
import team.android.pv.qlshop.view.DividerItemDecoration
import team.android.pv.qlshop.view.adapter.AdapterCategorys
import team.android.pv.qlshop.view.adapter.AdapterProduct
import team.android.pv.qlshop.view.view.ViewProductSearch


class SearchProductActivity : BaseActivitys(), ViewProductSearch, AdapterProduct.IOnClick,
    AdapterCategorys.IOnClickItem {



    private lateinit var searchProductPresenter: SearchProductPresenter
    private var barcode = ""
    private var listProduct: ArrayList<Product> = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_product)

        searchProductPresenter = SearchProductPresenter(this, SearchProductInteractor())

        edSearchs.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (edSearchs.text.toString().length > 0) {
                    searchProduct(edSearchs.text.toString())
                }
            }

        })
        iv_search_barcode.setOnClickListener {
            var intent = BarcodeReaderActivity.getLaunchIntent(this, true, false)
            startActivityForResult(intent, 100)
        }



    }



    override fun onClickItem(id_category: Int, selected_position: Int) {

    }

    private fun searchProduct(name: String) {
       if(name.length>0){
            searchProductPresenter.searchProduct(userEntity!!.id_shop, barcode, name)
        }
    }

    override fun getListSearchProduct(listProduct: List<Product>) {
        rv_product_search.visibility=View.VISIBLE
        this.listProduct= listProduct as ArrayList<Product>
        rv_product_search.addItemDecoration(DividerItemDecoration(resources.getDrawable(R.drawable.divider)))
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
          rv_product_search.visibility=View.GONE
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && data != null) {
            barcode = data.getParcelableExtra<Barcode>(BarcodeReaderActivity.KEY_CAPTURED_BARCODE).rawValue
            searchProductPresenter.searchProduct(userEntity!!.id_shop, barcode, "0")
        }
    }


}