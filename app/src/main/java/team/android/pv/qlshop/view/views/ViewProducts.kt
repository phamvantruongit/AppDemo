package team.android.pv.qlshop.view.views

import team.android.pv.qlshop.model.Product

interface ViewProducts :ViewParents {
    fun getListProducts(productList: ArrayList<Product>)
}