package team.android.pv.qlshop.view.activity

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_products.*
import kotlinx.android.synthetic.main.show_dialog_category.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.tvTitle
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.Category
import team.android.pv.qlshop.model.Product
import team.android.pv.qlshop.presenter.product.GetProductInteractor
import team.android.pv.qlshop.presenter.product.GetProductPresenter
import team.android.pv.qlshop.view.DividerItemDecoration
import team.android.pv.qlshop.view.LoadMoreScroll
import team.android.pv.qlshop.view.adapter.AdapterCategorys
import team.android.pv.qlshop.view.adapter.AdapterProduct
import team.android.pv.qlshop.view.view.ViewProducts


class ProductActivity : BaseActivity(), ViewProducts, AdapterCategorys.IOnClickItem, AdapterProduct.IOnClick,
    LoadMoreScroll.ILoadMoreScroll {
    override fun getListProducts(productList: ArrayList<Product>) {

    }

    var isLoadAll : Boolean ?=false
    private var page: Int = 0
    var id_category = 0
    private var isLoad: Boolean = false
    var dialog: Dialog? = null
    var rv_category: RecyclerView? = null
    var iv_check:ImageView?=null
    private lateinit var getProductPresenter: GetProductPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progress_bar.visibility=View.VISIBLE
        getProductPresenter = GetProductPresenter(this, GetProductInteractor())
        tvTitle.text = this.resources.getText(R.string.title_products)
        imgRight.visibility = View.VISIBLE
        imgRight.setImageDrawable(resources.getDrawable(R.drawable.menu_right))

        dialog = Dialog(this)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setContentView(R.layout.show_dialog_category)
        iv_check=dialog!!.findViewById(R.id.iv_check)

        imgRight.setOnClickListener {

            getProductPresenter.getListCategoty(userEntity!!.id_shop)

            rv_category = dialog!!.findViewById(R.id.rv_categorys)
            dialog!!.show()
            if (dialog!!.window != null) {
                dialog!!.window!!.setGravity(Gravity.BOTTOM)
                dialog!!.window!!.setLayout(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT
                )
                dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }

            dialog!!.tvLoadAll.setOnClickListener {
                isLoadAll=true
                iv_check!!.visibility=View.VISIBLE
                getProductPresenter.getListProducts(userEntity!!.id_shop, 0, this.page)
                dialog!!.dismiss()
            }


        }

        imgBarcode.visibility = View.VISIBLE
        imgBarcode.setImageDrawable(resources.getDrawable(R.drawable.ic_search))
        imgBarcode.setOnClickListener {

            startActivity(Intent(this,SearchProductActivity::class.java))

        }
        page = 1
        getProductPresenter.getListProducts(userEntity!!.id_shop, id_category, page)

    }


    override fun getContentView(): Int {
        return R.layout.activity_products
    }

    override fun getNavigationMenuItemId(): Int {
        return R.id.navigation_products
    }


    override fun getListProducts(
        productList: ArrayList<Product>,
        load: Boolean,
        current_page: Float
    ) {
        isLoad = load
        page = current_page.toInt()
        ln_add.visibility = View.GONE
        rv_product.visibility = View.VISIBLE
        rv_product.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        rv_product.addItemDecoration(DividerItemDecoration(resources.getDrawable(team.android.pv.qlshop.R.drawable.divider)))
        rv_product.adapter = AdapterProduct(productList, this)
        rv_product.addOnScrollListener(LoadMoreScroll(rv_product.layoutManager as LinearLayoutManager, this))

    }


    override fun loadMore(isScroll: Boolean) {
        if (isScroll && isLoad) {
            page++
            getProductPresenter.getListProducts(userEntity!!.id_shop, this.id_category, page)
        }
    }

    override fun iOnCLickItem(product: Product) {
        getProductPresenter.deleteProduct(product.id, product.id_shop)
    }

    override fun getListNameCategory(category: ArrayList<Category>) {
        rv_category!!.layoutManager = LinearLayoutManager(this)
        rv_category!!.addItemDecoration(DividerItemDecoration(resources.getDrawable(R.drawable.divider)))
        rv_category!!.adapter = AdapterCategorys(category, this, isLoadAll)

    }

    override fun onClickItem(id_category: Int, selected_position: Int) {
        dialog!!.dismiss()
        if(dialog!=null){
            iv_check!!.visibility=View.GONE
        }
        this.id_category = id_category
        getProductPresenter.getListProducts(userEntity
        !!.id_shop, this.id_category, page)

    }


    override fun showProgress() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_bar.visibility = View.GONE
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showError(error: String) {
        rv_product.visibility = View.GONE
        ln_add.visibility = View.VISIBLE
        ln_add.setOnClickListener {
            var intent = Intent(this, AddProductActivity::class.java)
            intent.putExtra("data", "data")
            startActivity(intent)
        }

    }


}