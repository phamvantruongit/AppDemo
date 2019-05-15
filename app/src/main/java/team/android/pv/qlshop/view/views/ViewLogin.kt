package team.android.pv.qlshop.view.views

import team.android.pv.qlshop.model.User

interface ViewLogin {
    fun showProgress()
    fun hideProgress()
    fun setData(user: User)
    fun setDataError(error: String)
}