package team.android.pv.appdemo.presenter.login

import team.android.pv.appdemo.model.User
import team.android.pv.appdemo.presenter.Inteface.OnFinishedListener
import team.android.pv.appdemo.view.views.ViewLogin

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
        viewLogin?.setDataError(strError)
    }

    fun onDestroy(){
        viewLogin=null
    }


}