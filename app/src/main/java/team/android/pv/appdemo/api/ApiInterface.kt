package team.android.pv.appdemo.api
import retrofit2.Call
import retrofit2.http.*
import team.android.pv.appdemo.model.LoginBaseResponse
import team.android.pv.appdemo.model.User

interface ApiInterface {


    @FormUrlEncoded
    @POST("login.php")
    fun loginUser(@FieldMap user:Map<String,String>) :Call<LoginBaseResponse>

    @FormUrlEncoded
    @POST("login.php")
    fun  registerUser (@FieldMap user:Map<String,String>) :Call<LoginBaseResponse>
}