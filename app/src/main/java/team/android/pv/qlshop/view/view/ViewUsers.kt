package team.android.pv.qlshop.view.view

import team.android.pv.qlshop.model.User

interface ViewUsers : ViewParents {
    fun showUsers(users: ArrayList<User>)
}