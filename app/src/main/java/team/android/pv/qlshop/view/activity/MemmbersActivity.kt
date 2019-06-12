package team.android.pv.qlshop.view.activity

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_memmbers.*
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.User
import team.android.pv.qlshop.presenter.user.UsersInteractor
import team.android.pv.qlshop.presenter.user.UsersPresenter
import team.android.pv.qlshop.view.DividerItemDecoration
import team.android.pv.qlshop.view.adapter.AdapterMemmbers
import team.android.pv.qlshop.view.view.ViewUsers


class MemmbersActivity : BaseActivitys(), ViewUsers, AdapterMemmbers.IOnClickItem {


    private lateinit var usersPresenter: UsersPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memmbers)

        usersPresenter = UsersPresenter(this, UsersInteractor())
        usersPresenter.getListUser(userEntity!!.id_shop)
    }

    override fun showUsers(users: ArrayList<User>) {
        rv_memmbers.layoutManager = LinearLayoutManager(this)
        rv_memmbers.addItemDecoration(DividerItemDecoration(resources.getDrawable(R.drawable.divider)))
        rv_memmbers.adapter = AdapterMemmbers(users, this)
    }

    override fun showProgress() {
        //progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        //progress_bar.visibility = View.GONE
    }

    override fun showMessage(message: String) {
        usersPresenter.getListUser(userEntity!!.id_shop)
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    override fun onClickDeleteUser(user: User) {
        val build = AlertDialog.Builder(this)
        build.setMessage("Ban co muon xoa  user" + user.name)
        build.setCancelable(true)

        build.setPositiveButton(
            "Yes"
        ) { dialog, id ->
            usersPresenter.deleteUser(user.id_shop, user.id)
            dialog.cancel()
        }

        build.setNegativeButton(
            "No"
        ) { dialog, id ->
            dialog.cancel()
        }

        build.create().show()


    }

    override fun onClickEditUser(user: User) {
         var intent=Intent(this,RegisterActivity::class.java)
         intent.putExtra("user",user)
         startActivity(intent)
    }

    override fun onClickAddUser() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }
}
