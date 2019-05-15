package team.android.pv.appdemo.presenter.register

import team.android.pv.appdemo.model.User
import team.android.pv.appdemo.presenter.Inteface.OnFinishedListener
import team.android.pv.appdemo.view.views.ViewRegister

class ResgisterPresenter(val viewRegister: ViewRegister?,val registerInteractor: RegisterInteractor): OnFinishedListener {

    fun registerUser(user: User){
        viewRegister!!.showProgress()
        registerInteractor.registerUserAPI(this,user)
    }
    override fun onResultSuccess(user: User) {
         viewRegister!!.setData(user)
         viewRegister!!.hideProgress()
    }

    override fun onResultFail(strError: String) {
        viewRegister!!.setDataError(strError)
        viewRegister!!.hideProgress()
    }
}