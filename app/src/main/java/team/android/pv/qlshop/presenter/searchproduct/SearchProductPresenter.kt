package team.android.pv.qlshop.presenter.searchproduct

import team.android.pv.qlshop.model.Category
import team.android.pv.qlshop.model.Product
import team.android.pv.qlshop.view.views.ViewProductSearch
import team.android.pv.qlshop.view.views.ViewSearchBarcode

class SearchProductPresenter :
    SearchProductInteractor.OnFinishedListenerProduct, SearchProductInteractor.OnFinishedListenerProductBarcode {


    var viewProductSearch: ViewProductSearch ?=null
     var searchProductInteractor: SearchProductInteractor?=null
     var viewSearchBarcode:ViewSearchBarcode ?=null




    constructor(viewProductSearch: ViewProductSearch?, searchProductInteractor: SearchProductInteractor?) {
        this.viewProductSearch = viewProductSearch
        this.searchProductInteractor = searchProductInteractor
    }

    constructor(searchProductInteractor: SearchProductInteractor?, viewSearchBarcode: ViewSearchBarcode?) {
        this.searchProductInteractor = searchProductInteractor
        this.viewSearchBarcode = viewSearchBarcode
    }


    fun getListCategory(id_shop: Int){
        searchProductInteractor!!.getListCategory(this,id_shop = id_shop)
    }


    override fun onResultListProductBarcode(listProduct: ArrayList<Product>) {
          viewSearchBarcode!!.getListSearchProduct(listProduct)
    }

    override fun onResultCategory(listCategory: ArrayList<Category>) {
        viewProductSearch!!.getListCategory(listCategory)
    }

    fun searchProduct(id_shop:Int, barcode : String,  name: String){
         searchProductInteractor!!.getListSearchProduct(this,id_shop,barcode,name)
    }

    fun searchProductBarocde(id_shop:Int, barcode : String,  name: String){
        searchProductInteractor!!.getListSearchProductBarocde(this,id_shop,barcode,name)
    }


    override fun onResultListProducts(listProduct: ArrayList<Product>) {
        viewProductSearch!!.showProgress()
        viewProductSearch!!.getListSearchProduct(listProduct)
    }

    override fun onResultFail(strError: String) {
//        viewProductSearch!!.hideProgress()
//        viewProductSearch!!.showMessage(strError)
    }
}