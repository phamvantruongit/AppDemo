package team.android.pv.qlshop.presenter.product

import team.android.pv.qlshop.model.Product
import team.android.pv.qlshop.presenter.Inteface.OnFinishedListeners
import team.android.pv.qlshop.view.view.ViewProduct

class AddProductPresenter(var viewProduct: ViewProduct, var productInteractor: AddProductInteractor) : OnFinishedListeners {


    fun addProduct(product: Product) {
        viewProduct.showProgress()
        productInteractor.addProduct(this, product)
    }


    fun editProduct(product: Product){
        viewProduct.showProgress()
        productInteractor.editProduct(this, product)
    }

    override fun showMessage(success: String) {
        viewProduct.hideProgress()
        viewProduct.setSuccess(success)

    }

    override fun onResultFail(strError: String) {
        viewProduct.hideProgress()
        viewProduct.showMessage(strError)
    }
}