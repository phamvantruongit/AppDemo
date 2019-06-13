package team.android.pv.qlshop.view.view

import team.android.pv.qlshop.model.Category

interface ViewAddCategory :ViewParents{
    fun getListCategory(listCategory: ArrayList<Category>)
    fun setSuccess(message:String)
}