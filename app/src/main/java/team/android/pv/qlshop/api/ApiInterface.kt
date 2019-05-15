package team.android.pv.qlshop.api
import retrofit2.Call
import retrofit2.http.*
import team.android.pv.qlshop.model.LoginBaseResponse

interface ApiInterface {


    @FormUrlEncoded
    @POST("login.php")
    fun loginUser(@FieldMap user:Map<String,String>) :Call<LoginBaseResponse>

    @FormUrlEncoded
    @POST("login.php")
    fun  registerUser (@FieldMap user:Map<String,String>) :Call<LoginBaseResponse>
}