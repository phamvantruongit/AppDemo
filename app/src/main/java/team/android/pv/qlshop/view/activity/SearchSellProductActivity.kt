package team.android.pv.qlshop.view.activity

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_search_sell_product.*
import kotlinx.android.synthetic.main.show_dialog_category.*
import team.android.pv.qlshop.MyApplication
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.Category
import team.android.pv.qlshop.model.Product
import team.android.pv.qlshop.model.data.database.ProductEntity
import team.android.pv.qlshop.presenter.product.GetProductInteractor
import team.android.pv.qlshop.presenter.product.GetProductPresenter
import team.android.pv.qlshop.view.DividerItemDecoration
import team.android.pv.qlshop.view.LoadMoreScroll
import team.android.pv.qlshop.view.adapter.AdapterCategorys
import team.android.pv.qlshop.view.adapter.AdapterSellProduct
import team.android.pv.qlshop.view.view.ViewProducts

class SearchSellProductActivity : BaseActivitys(), ViewProducts, AdapterSellProduct.IOnClick,
    AdapterCategorys.IOnClickItem, LoadMoreScroll.ILoadMoreScroll {


    private val id_category: Int = 0
    private var page = 0
    var rv_category: RecyclerView? = null
    var dialog: Dialog? = null
    var iv_check: ImageView? = null
    var isLoadAll: Boolean? = false
    private var isLoad: Boolean = false

    private var listProducts: List<Product>? = null
    private var listProductSaveLocal: List<Product>? = null
    private lateinit var getProductPresenter: GetProductPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_sell_product)



        listProducts = ArrayList<Product>()
        listProductSaveLocal = ArrayList<Product>()

        getProductPresenter = GetProductPresenter(this, GetProductInteractor())

        page = 1
        getProductPresenter.getListProducts(userEntity!!.id_shop, id_category, page)


        edSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (edSearch.text.toString().length > 0) {
                    searchProduct(edSearch.text.toString())
                }
            }

        })
        dialog = Dialog(this)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setContentView(R.layout.show_dialog_category)
        iv_check = dialog!!.findViewById(R.id.iv_check)


        tvSearch.setOnClickListener {

            dialog!!.show()

            rv_category = dialog!!.findViewById(R.id.rv_categorys)
            getProductPresenter.getListCategoty(userEntity!!.id_shop)
            if (dialog!!.window != null) {
                dialog!!.window!!.setGravity(Gravity.BOTTOM)
                dialog!!.window!!.setLayout(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT
                )
                dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
            dialog!!.tvLoadAll.setOnClickListener {
                isLoadAll = true
                iv_check!!.visibility = View.VISIBLE
                getProductPresenter.getListProducts(userEntity!!.id_shop, id_category, page)
                dialog!!.dismiss()
            }
        }


        tvSelect.setOnClickListener {

            finish()

        }


    }

    private fun searchProduct(name: String) {
        getProductPresenter.getListSearch(userEntity!!.id_shop, "", name,name.toInt())
    }


    override fun getListProducts(productList: ArrayList<Product>) {
        rv_product_sell_search.visibility = View.VISIBLE
        rv_product_sell_search.addItemDecoration(DividerItemDecoration(resources.getDrawable(R.drawable.divider)))
        rv_product_sell_search.layoutManager = LinearLayoutManager(this)
        rv_product_sell_search.adapter = AdapterSellProduct(this, productList, this)

    }


    override fun getListProducts(productList: ArrayList<Product>, load: Boolean, current_page: Float) {
        listProducts = productList
        isLoad = load
        rv_product_sell_search.visibility = View.VISIBLE
        rv_product_sell_search.addItemDecoration(DividerItemDecoration(resources.getDrawable(R.drawable.divider)))
        rv_product_sell_search.layoutManager = LinearLayoutManager(this)
        rv_product_sell_search.adapter = AdapterSellProduct(this, listProducts as ArrayList<Product>, this)
        rv_product_sell_search.addOnScrollListener(
            LoadMoreScroll(
                rv_product_sell_search.layoutManager as LinearLayoutManager,
                this
            )
        )

    }

    override fun loadMore(isScroll: Boolean) {
        if (isScroll && isLoad) {
            page++
            getProductPresenter.getListProducts(userEntity!!.id_shop, id_category, page)

        }

    }

    override fun iOnCLickItem(product: Product, cb_selected: RadioButton) {
        saveProduct(product)
        update(cb_selected)
    }

    fun update(cb_selected: RadioButton) {
        var listData = MyApplication.appDatabase.productDao().getAllListProduct()
        for (i in 0..listData.size - 1) {
              if(listData.get(i).amount<=listData.get(i).amounts) {
                  cb_selected.setText(listData.get(i).amount.toString())
              }
        }

    }


    private fun saveProduct(product: Product) {


        var entity = MyApplication.appDatabase.productDao().getProduct(product.id)
        if  (entity!=null) {
            if( entity.uid==product.id ) {
                var amount=entity.amount + 1
                MyApplication.appDatabase.productDao().updateAmount(amount, product.id)
            }
            else {
                var productEntity = ProductEntity()
                productEntity.uid = product.id
                productEntity.name = product.name
                productEntity.amount = 1
                productEntity.amounts = product.amount
                productEntity.price_out = product.price_out
                MyApplication.appDatabase.productDao().addProduct(productEntity)
            }
        } else {
            var productEntity = ProductEntity()
            productEntity.uid = product.id
            productEntity.name = product.name
            productEntity.amount = 1
            productEntity.amounts = product.amount
            productEntity.price_out = product.price_out

            MyApplication.appDatabase.productDao().addProduct(productEntity)
        }


    }

    override fun getListNameCategory(listCategory: ArrayList<Category>) {
        rv_category!!.layoutManager = LinearLayoutManager(this)
        rv_category!!.addItemDecoration(DividerItemDecoration(resources.getDrawable(R.drawable.divider)))
        rv_category!!.adapter = AdapterCategorys(listCategory, this, isLoadAll)
    }

    override fun onClickItem(id_category: Int, selected_position: Int) {
        dialog!!.dismiss()
        iv_check!!.visibility = View.GONE
        getProductPresenter.getListProducts(userEntity!!.id_shop, id_category, page)
    }

    override fun showError(error: String) {
        rv_product_sell_search.visibility = View.GONE
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun showMessage(message: String) {

    }

}