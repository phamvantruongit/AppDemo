package team.android.pv.qlshop.view.views

import team.android.pv.qlshop.model.Product

interface ViewSearchBarcode :ViewParents {
    fun getListSearchProduct(listProduct:List<Product>)
}