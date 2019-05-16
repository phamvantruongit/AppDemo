package team.android.pv.qlshop.presenter.product

import team.android.pv.qlshop.model.Product
import team.android.pv.qlshop.presenter.Inteface.OnFinishedListeners
import team.android.pv.qlshop.view.views.ViewProduct

class ProductPresenter(var viewProduct: ViewProduct, var productInteractor: ProductInteractor) : OnFinishedListeners,ProductInteractor.OnFinishedListener {


    fun addProduct(product: Product) {
        viewProduct.showProgress()
        productInteractor.addProduct(this, product)
    }

    fun getListProducts(id_shop:Int){
        viewProduct.showProgress()
        productInteractor.getListProducts(this,id_shop)
    }

    override fun onResultListProducts(listProduct: ArrayList<Product>) {
        viewProduct.hideProgress()
        viewProduct.getListProducts(listProduct)
    }


    override fun onResultSuccess(success: String) {
        viewProduct.hideProgress()
        viewProduct.setSuccess(success)

    }

    override fun onResultFail(strError: String) {
        viewProduct.hideProgress()
    }
}