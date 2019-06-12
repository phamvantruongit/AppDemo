package team.android.pv.qlshop.model.response

import com.google.gson.annotations.SerializedName
import team.android.pv.qlshop.model.Category

class CategoryResponse : BaseResponse() {
    @SerializedName("data")
    lateinit var listCategory:ArrayList<Category>
}