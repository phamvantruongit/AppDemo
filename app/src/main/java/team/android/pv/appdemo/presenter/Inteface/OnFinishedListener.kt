package team.android.pv.appdemo.presenter.Inteface

import team.android.pv.appdemo.model.User
import java.util.*

interface OnFinishedListener{
    fun onResultSuccess(user : User)
    fun onResultFail(strError : String)
}