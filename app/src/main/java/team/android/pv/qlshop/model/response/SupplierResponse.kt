package team.android.pv.qlshop.model.response

import com.google.gson.annotations.SerializedName
import team.android.pv.qlshop.model.Supplier

class SupplierResponse :BaseResponse() {
    @SerializedName("data")
    lateinit var listSupplier:ArrayList<Supplier>
}