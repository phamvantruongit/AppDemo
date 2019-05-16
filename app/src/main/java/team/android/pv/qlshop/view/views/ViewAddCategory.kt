package team.android.pv.qlshop.view.views

import team.android.pv.qlshop.model.Category

interface ViewAddCategory :ViewParent{
    fun setSuccess(success: String)
    fun getListCategory(listCategory: ArrayList<Category>)
}