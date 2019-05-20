package team.android.pv.qlshop.model.response

import com.google.gson.annotations.SerializedName
import team.android.pv.qlshop.model.response.BaseResponse

class LoginBaseResponse : BaseResponse() {
    @SerializedName("email")
    var email: String = ""
    @SerializedName("name_shop")
    var name_shop: String = ""
    @SerializedName("name")
    var name: String = ""
    @SerializedName("id_shop")
    var id_shop=0
}