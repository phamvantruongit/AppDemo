package team.android.pv.qlshop.view.views

import team.android.pv.qlshop.model.Category

interface ViewAddCategory :ViewParents{
    fun getListCategory(listCategory: ArrayList<Category>)
}