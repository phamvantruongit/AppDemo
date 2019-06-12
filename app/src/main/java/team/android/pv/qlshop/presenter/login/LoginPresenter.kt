package team.android.pv.qlshop.presenter.login

import team.android.pv.qlshop.model.User
import team.android.pv.qlshop.presenter.Inteface.OnFinishedListener
import team.android.pv.qlshop.view.view.ViewLogin

class LoginPresenter(private var viewLogin: ViewLogin?, private val loginInteractor: LoginInteractor) :
    OnFinishedListener {


    fun login(user: User){
        viewLogin?.showProgress()
        loginInteractor.loginUserAPI(this,user)
    }

    override fun onResultSuccess(user: User) {
        viewLogin?.hideProgress()
        viewLogin?.setData(user)
    }

    override fun onResultFail(strError: String) {
        viewLogin?.hideProgress()
        viewLogin?.showMessage(strError)
    }

    fun onDestroy(){
        viewLogin=null
    }


}