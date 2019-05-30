package team.android.pv.qlshop.presenter.searchproduct

import team.android.pv.qlshop.model.Category
import team.android.pv.qlshop.model.Product
import team.android.pv.qlshop.view.views.ViewProductSearch

class SearchProductPresenter (var viewProductSearch: ViewProductSearch ,var searchProductInteractor: SearchProductInteractor):
    SearchProductInteractor.OnFinishedListenerProduct {

    fun getListCategory(id_shop: Int){
        searchProductInteractor.getListCategory(this,id_shop = id_shop)
    }

    override fun onResultCategory(listCategory: ArrayList<Category>) {
        viewProductSearch.getListCategory(listCategory)
    }

    fun searchProduct(id_shop:Int, barcode : String,  name: String){
         searchProductInteractor.getListSearchProduct(this,id_shop,barcode,name)
    }
    override fun onResultListProducts(listProduct: ArrayList<Product>) {
        viewProductSearch.showProgress()
        viewProductSearch.getListSearchProduct(listProduct)
    }

    override fun onResultFail(strError: String) {
        viewProductSearch.hideProgress()
        viewProductSearch.showMessage(strError)
    }
}