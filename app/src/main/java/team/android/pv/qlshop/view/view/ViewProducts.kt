package team.android.pv.qlshop.view.view

import team.android.pv.qlshop.model.Category
import team.android.pv.qlshop.model.Product

interface ViewProducts :ViewParents {
    fun getListProducts(
        productList: ArrayList<Product>,
        load: Boolean,
        current_page: Float
    )

    fun getListProducts(
        productList: ArrayList<Product>
    )
    fun getListNameCategory(category: ArrayList<Category>)
    fun showError(error:String)
}