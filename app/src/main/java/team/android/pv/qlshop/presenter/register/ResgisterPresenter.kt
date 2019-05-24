package team.android.pv.qlshop.presenter.register

import team.android.pv.qlshop.model.User
import team.android.pv.qlshop.presenter.Inteface.OnFinishedListener
import team.android.pv.qlshop.view.views.ViewRegister

class ResgisterPresenter(val viewRegister: ViewRegister?,val registerInteractor: RegisterInteractor): OnFinishedListener {

    fun registerUsers(user: User, check_admin:Int){
        viewRegister!!.showProgress()
        registerInteractor.registerUserAPI(this,user,check_admin)
    }
    override fun onResultSuccess(user: User) {
         viewRegister!!.setData(user)
         viewRegister.hideProgress()
    }

    override fun onResultFail(strError: String) {
        viewRegister!!.showMessage(strError)
        viewRegister.hideProgress()
    }
}