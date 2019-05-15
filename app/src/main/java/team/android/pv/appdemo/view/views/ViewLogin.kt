package team.android.pv.appdemo.view.views

import team.android.pv.appdemo.model.User

interface ViewLogin {
    fun showProgress()
    fun hideProgress()
    fun setData(user: User)
    fun setDataError(error: String)
}