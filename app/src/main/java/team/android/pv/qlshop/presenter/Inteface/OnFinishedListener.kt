package team.android.pv.qlshop.presenter.Inteface

import team.android.pv.qlshop.model.User

interface OnFinishedListener{
    fun onResultSuccess(user : User)
    fun onResultFail(strError : String)
}