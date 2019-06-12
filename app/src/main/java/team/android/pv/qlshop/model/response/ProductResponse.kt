package team.android.pv.qlshop.model.response

import com.google.gson.annotations.SerializedName
import team.android.pv.qlshop.model.Product

class ProductResponse: BaseResponse() {
    @SerializedName("data")
    lateinit var listProduct:ArrayList<Product>
}