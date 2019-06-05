package team.android.pv.qlshop.presenter.supplier

import team.android.pv.qlshop.model.Supplier
import team.android.pv.qlshop.presenter.Inteface.OnFinishedListeners
import team.android.pv.qlshop.view.views.ViewParents

class SupplierPresenter(var viewParents: ViewParents ,var supplierInteractor: SupplierInteractor) :OnFinishedListeners  {


    fun addSupplier(supplier: Supplier){
        viewParents.showProgress()
        supplierInteractor.addSupplier(this,supplier)
    }

    override fun showMessage(success: String) {
        viewParents.hideProgress()
    }

    override fun onResultFail(strError: String) {
        viewParents.hideProgress()
        viewParents.showMessage(strError)
    }
}