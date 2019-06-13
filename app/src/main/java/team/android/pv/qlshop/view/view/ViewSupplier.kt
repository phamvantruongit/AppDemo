package team.android.pv.qlshop.view.view

import team.android.pv.qlshop.model.Supplier

interface ViewSupplier :ViewParents {
    fun getListSupplier(list:ArrayList<Supplier>)
}