package team.android.pv.qlshop.model.response

import com.google.gson.annotations.SerializedName
import team.android.pv.qlshop.model.User
import team.android.pv.qlshop.model.response.BaseResponse

class LoginBaseResponse : BaseResponse() {
    @SerializedName("user")
    lateinit var user:User
}