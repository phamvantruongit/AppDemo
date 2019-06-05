package team.android.pv.qlshop.view.views

import team.android.pv.qlshop.model.Supplier

interface ViewSupplier :ViewParents {
    fun getListSupplier(list:ArrayList<Supplier>)
}