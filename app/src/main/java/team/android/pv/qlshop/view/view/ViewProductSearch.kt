package team.android.pv.qlshop.view.view

import team.android.pv.qlshop.model.Product

interface ViewProductSearch : ViewParents {
    fun getListSearchProduct(listProduct:List<Product>)
}