package team.android.pv.qlshop.presenter.product

import team.android.pv.qlshop.model.Category
import team.android.pv.qlshop.model.Product
import team.android.pv.qlshop.presenter.Inteface.OnFinishedListeners
import team.android.pv.qlshop.view.view.ViewProducts

class GetProductPresenter(var viewProduct: ViewProducts, val productInteractor: GetProductInteractor) :  GetProductInteractor.OnFinishedListenerProduct,
    OnFinishedListeners, GetProductInteractor.OnFinishedListenerSearchProduct {
    override fun onResultListProducts(listProduct: ArrayList<Product>) {
        viewProduct.getListProducts(listProduct)
    }

    override fun showMessage(success: String) {
        viewProduct.showMessage(success)
    }


    fun getListSearch(id_shop:Int, barcode : String, name: String){
        productInteractor.getListSearchProduct(this,id_shop,barcode,name)
    }

    fun getListCategoty(id_shop: Int ){
        productInteractor.getListNameCategory(this,id_shop )
    }

    override fun onResultSuccess(listCategory: ArrayList<Category>) {
       viewProduct.getListNameCategory(listCategory)
    }

    override fun onResultFail(strError: String) {
       viewProduct.showError(strError)
       viewProduct.hideProgress()
    }

    override fun onResultListProducts(
        listProduct: ArrayList<Product>,
        load: Boolean,
        current_page: Float
    ) {
        viewProduct.hideProgress()
        viewProduct.getListProducts(listProduct,load,current_page)
    }


    fun getListProducts(id_shop:Int ,id_category:Int,page:Int){
        viewProduct.showProgress()
        productInteractor.getListProducts(this,id_shop ,id_category,page)
    }

}