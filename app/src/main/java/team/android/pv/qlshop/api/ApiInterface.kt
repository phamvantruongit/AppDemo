package team.android.pv.qlshop.api
import retrofit2.Call
import retrofit2.http.*
import team.android.pv.qlshop.model.BaseResponse
import team.android.pv.qlshop.model.CategoryResponse
import team.android.pv.qlshop.model.LoginBaseResponse

interface ApiInterface {


    @FormUrlEncoded
    @POST("login/login.php")
    fun loginUser(@FieldMap user:Map<String,String>) :Call<LoginBaseResponse>

    @FormUrlEncoded
    @POST("login/register.php")
    fun  registerUser (@FieldMap user:Map<String,String>) :Call<LoginBaseResponse>


    @FormUrlEncoded
    @POST("add/category.php")
    fun  addCategory (@FieldMap user:Map<String,String>) :Call<BaseResponse>


    @FormUrlEncoded
    @POST("add/brand.php")
    fun  addBrand (@FieldMap user:Map<String,String>) :Call<BaseResponse>


    @GET("get/brand.php")
    fun getBrand(@Query("id_shop") id_shop:Int) :Call<CategoryResponse>


    @GET("get/category.php")
    fun getCategory(@Query("id_shop") id_shop:Int) :Call<CategoryResponse>



}