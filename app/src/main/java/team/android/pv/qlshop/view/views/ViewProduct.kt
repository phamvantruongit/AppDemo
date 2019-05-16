package team.android.pv.qlshop.view.views

import team.android.pv.qlshop.model.Product

interface ViewProduct :ViewParents {
    fun setSuccess(success: String)
    fun getListProducts(listProduct: ArrayList<Product>)
}