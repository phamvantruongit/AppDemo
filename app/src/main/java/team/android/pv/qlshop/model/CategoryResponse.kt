package team.android.pv.qlshop.model

import com.google.gson.annotations.SerializedName

class CategoryResponse :BaseResponse() {
    @SerializedName("list")
    lateinit var listCategory:ArrayList<Category>
}