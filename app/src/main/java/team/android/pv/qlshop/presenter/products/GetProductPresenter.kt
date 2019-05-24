package team.android.pv.qlshop.presenter.product

import team.android.pv.qlshop.model.Product
import team.android.pv.qlshop.view.views.ViewProducts

class GetProductPresenter(var viewProduct: ViewProducts, val productInteractor: GetProductInteractor) :  GetProductInteractor.OnFinishedListener{

    override fun onResultFail(strError: String) {
       viewProduct.hideProgress()
    }

    override fun onResultListProducts(listProduct: ArrayList<Product>) {
        viewProduct.hideProgress()
        viewProduct.getListProducts(listProduct)
    }


    fun getListProducts(id_shop:Int){
        viewProduct.showProgress()
        productInteractor.getListProducts(this,id_shop)
    }



}