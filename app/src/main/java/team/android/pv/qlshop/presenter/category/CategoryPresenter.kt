package team.android.pv.qlshop.presenter.category

import team.android.pv.qlshop.model.Category
import team.android.pv.qlshop.presenter.Inteface.OnFinishedListeners
import team.android.pv.qlshop.view.views.ViewAddCategory

class CategoryPresenter(val viewAddCategory: ViewAddCategory, val categoryInteractor: CategoryInteractor) :
    OnFinishedListeners, CategoryInteractor.OnFinishedListenersCategory {


    fun getCategory(id_shop: Int, check: Boolean) {
        viewAddCategory.showProgress()
        categoryInteractor.getCategoryToAPI(this, id_shop, check)
    }

    override fun onResulCategorytFail(strError: String) {
        viewAddCategory.hideProgress()
        viewAddCategory.setDataError(strError)
    }

    override fun onResultSuccess(listCategory: ArrayList<Category>) {
        viewAddCategory.hideProgress()
        viewAddCategory.getListCategory(listCategory)
    }


    fun addCategory(name: String, id_shop: Int, check: Boolean) {
        categoryInteractor.addCategoryToAPI(this, name, id_shop, check)
    }

    override fun onResultSuccess(success: String) {
        viewAddCategory.setSuccess(success)
    }

    override fun onResultFail(strError: String) {
        viewAddCategory.setDataError(strError)
    }
}