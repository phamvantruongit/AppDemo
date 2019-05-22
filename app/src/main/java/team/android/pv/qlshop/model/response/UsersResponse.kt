package team.android.pv.qlshop.model.response

import com.google.gson.annotations.SerializedName
import team.android.pv.qlshop.model.Product
import team.android.pv.qlshop.model.User

class UsersResponse : BaseResponse() {
    @SerializedName("data")
    lateinit var listUser:ArrayList<User>
}