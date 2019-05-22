package team.android.pv.qlshop.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_memmbers.*
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.User
import team.android.pv.qlshop.presenter.login.LoginPresenter
import team.android.pv.qlshop.presenter.user.UsersInteractor
import team.android.pv.qlshop.presenter.user.UsersPresenter
import team.android.pv.qlshop.view.adapter.AdapterMemmbers
import team.android.pv.qlshop.view.views.ViewUsers

class MemmbersActivity :AppCompatActivity(), ViewUsers {


    private lateinit var usersPresenter: UsersPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memmbers)

        usersPresenter= UsersPresenter(this, UsersInteractor())
        usersPresenter.getListUser(1)
    }

    override fun showUsers(users: ArrayList<User>) {
        rv_memmbers.layoutManager=LinearLayoutManager(this)
        rv_memmbers.adapter=AdapterMemmbers(users)
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun setDataError(error: String) {

    }
}
