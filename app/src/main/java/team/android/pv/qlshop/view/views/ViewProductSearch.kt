package team.android.pv.qlshop.view.views

import team.android.pv.qlshop.model.Category
import team.android.pv.qlshop.model.Product

interface ViewProductSearch : ViewParents {
    fun getListSearchProduct(listProduct:List<Product>)
}