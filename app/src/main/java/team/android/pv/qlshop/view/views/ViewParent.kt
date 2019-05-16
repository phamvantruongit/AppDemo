package team.android.pv.qlshop.view.views

interface ViewParent {
    fun showProgress()
    fun hideProgress()
    fun setDataError(error: String)
}