package team.android.pv.qlshop.presenter.Inteface

import team.android.pv.qlshop.model.Category
import team.android.pv.qlshop.model.User

open interface OnFinishedListeners {
    fun onResultSuccess(success: String)
    fun onResultFail(strError : String)

}