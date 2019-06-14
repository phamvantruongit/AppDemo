package team.android.pv.qlshop.presenter.deleteproduct

import team.android.pv.qlshop.presenter.Inteface.OnFinishedListeners
import team.android.pv.qlshop.view.view.ViewDeleteProduct


class DeleteProductPresenter(var viewParent: ViewDeleteProduct, var deleteProduct: DeleteProductInteraction) :
    OnFinishedListeners {


    fun deleteProduct(id: Int, id_shop:Int){
         deleteProduct.deleteProduct(this,id,id_shop)
    }

    override fun showMessage(success: String) {
        viewParent.showMessage(success)
    }

    override fun onResultFail(strError: String) {
        viewParent.showMessageError(strError)
    }
}