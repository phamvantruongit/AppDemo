package team.android.pv.qlshop.presenter.user

import team.android.pv.qlshop.model.User
import team.android.pv.qlshop.view.views.ViewUsers

class UsersPresenter(var viewUsers: ViewUsers, var usersInteractor: UsersInteractor) : UsersInteractor.OnFinishedListener {


    fun getListUser(id_shop:Int){
         usersInteractor.getListUser(id_shop,this)
    }


    override fun onResultListProducts(listUser: ArrayList<User>) {
        viewUsers.showUsers(listUser)
    }

    override fun onResultSuccess(success: String) {

    }

    override fun onResultFail(strError: String) {

    }
}