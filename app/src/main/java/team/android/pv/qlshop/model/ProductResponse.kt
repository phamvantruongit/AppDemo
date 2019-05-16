package team.android.pv.qlshop.model

import com.google.gson.annotations.SerializedName

class ProductResponse: BaseResponse() {
    @SerializedName("list")
    lateinit var listProduct:ArrayList<Product>
}