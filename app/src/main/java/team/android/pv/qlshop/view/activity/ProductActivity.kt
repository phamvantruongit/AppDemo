package team.android.pv.qlshop.view.activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_products.*
import kotlinx.android.synthetic.main.activity_products.rv_category
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.tvTitle
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.Category
import team.android.pv.qlshop.model.Product
import team.android.pv.qlshop.presenter.product.GetProductInteractor
import team.android.pv.qlshop.presenter.product.GetProductPresenter
import team.android.pv.qlshop.view.DividerItemDecoration
import team.android.pv.qlshop.view.PaginationScrollListener
import team.android.pv.qlshop.view.adapter.AdapterCategorys
import team.android.pv.qlshop.view.adapter.AdapterProduct
import team.android.pv.qlshop.view.views.ViewProducts

class ProductActivity : BaseActivity(), ViewProducts, AdapterCategorys.IOnClickItem, AdapterProduct.IOnClick {


    private var page: Int = 1
    var isLastPage: Boolean = false
    var isLoading: Boolean = false
    private lateinit var getProductPresenter: GetProductPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getProductPresenter = GetProductPresenter(this, GetProductInteractor())
        getProductPresenter.getListCategoty(userSave!!.id_shop)
        tvTitle.text = this.resources.getText(R.string.title_products)
        imgRight.visibility = View.VISIBLE
        imgRight.setImageDrawable(resources.getDrawable(R.drawable.menu_right))
        imgRight.setOnClickListener {
            drawer_layout.openDrawer(Gravity.RIGHT)
        }
        getProductPresenter.getListProducts(userSave!!.id_shop, 0, page)

    }




    override fun getContentView(): Int {
        return R.layout.activity_products
    }

    override fun getNavigationMenuItemId(): Int {
        return R.id.navigation_products
    }


    override fun getListProducts(productList: ArrayList<Product>) {
        ln_add.visibility=View.GONE
        rv_product.visibility=View.VISIBLE
        rv_product.layoutManager = LinearLayoutManager(this)
        rv_product.addItemDecoration(DividerItemDecoration(resources.getDrawable(R.drawable.divider)))
        rv_product.adapter = AdapterProduct(productList, this)
//        rv_product.addOnScrollListener(object :
//            PaginationScrollListener(rv_product.layoutManager as LinearLayoutManager) {
//            override fun isLastPage(): Boolean {
//                return isLastPage
//            }
//
//            override fun isLoading(): Boolean {
//                return isLoading
//            }
//
//            override fun loadMoreItems(load: Boolean) {
//                getMoreItems(load)
//            }
//        })

    }


    fun getMoreItems(load: Boolean) {
        if(load) {
            page++
            getProductPresenter.getListProducts(userSave!!.id_shop, 0, page)
        }

    }

    override fun iOnCLickItem(product: Product) {
        getProductPresenter.deleteProduct(product.id,product.id_shop)
    }

    override fun getListNameCategory(category: ArrayList<Category>) {
        rv_category.layoutManager = LinearLayoutManager(this)
        rv_category.addItemDecoration(DividerItemDecoration(resources.getDrawable(R.drawable.divider)))
        rv_category.adapter = AdapterCategorys(category, this)

    }

    override fun onClickItem(id_category: Int, selected_position: Int) {
        drawer_layout.closeDrawers()
        if(id_category==0){
            return
        }
        if (selected_position == 0) {
            getProductPresenter.getListProducts(userSave!!.id_shop, 0, this.page)
        } else {
            var id_categorys = id_category - 1
            getProductPresenter.getListProducts(userSave!!.id_shop, id_categorys, page)
        }
    }


    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun showMessage(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    override fun showError(error: String) {
        rv_product.visibility=View.GONE
        ln_add.visibility=View.VISIBLE
        ln_add.setOnClickListener {
            var intent=Intent(this,AddProductActivity::class.java)
            intent.putExtra("data","data")
            startActivity(intent)
        }
    }


}