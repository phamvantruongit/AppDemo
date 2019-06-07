package team.android.pv.qlshop.presenter.supplier

import team.android.pv.qlshop.model.Supplier
import team.android.pv.qlshop.presenter.Inteface.OnFinishedListeners
import team.android.pv.qlshop.view.views.ViewSupplier

class SupplierPresenter(var viewParents: ViewSupplier ,var supplierInteractor: SupplierInteractor) :OnFinishedListeners,
    SupplierInteractor.OnFinishedListenerSupplier {
    override fun onResultListSupplier(list: ArrayList<Supplier>) {
        viewParents.showProgress()
        viewParents.getListSupplier(list)
    }


    fun getListSupplier(id_shop: Int, checkCustomer: Boolean){
        supplierInteractor.getListSupplier(id_shop,this,checkCustomer)
    }

    fun addSupplier(supplier: Supplier, checkCustomer: Boolean){
        viewParents.showProgress()
        supplierInteractor.addSupplier(this,supplier ,checkCustomer)
    }

    override fun showMessage(success: String) {
        viewParents.hideProgress()
    }

    override fun onResultFail(strError: String) {
        viewParents.hideProgress()
        viewParents.showMessage(strError)
    }
}