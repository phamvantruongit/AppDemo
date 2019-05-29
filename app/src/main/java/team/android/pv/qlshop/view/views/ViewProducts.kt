package team.android.pv.qlshop.view.views

import team.android.pv.qlshop.model.Category
import team.android.pv.qlshop.model.Product

interface ViewProducts :ViewParents {
    fun getListProducts(
        productList: ArrayList<Product>,
        load: Boolean,
        current_page: Float
    )
    fun getListNameCategory(category: ArrayList<Category>)
    fun showError(error:String)
}