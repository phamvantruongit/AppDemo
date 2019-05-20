package team.android.pv.qlshop.api
import retrofit2.Call
import retrofit2.http.*
import team.android.pv.qlshop.model.response.BaseResponse
import team.android.pv.qlshop.model.response.CategoryResponse
import team.android.pv.qlshop.model.response.LoginBaseResponse
import team.android.pv.qlshop.model.response.ProductResponse

interface ApiInterface {


    @FormUrlEncoded
    @POST("login.php")
    fun loginUser(@FieldMap user:Map<String,String>) :Call<LoginBaseResponse>

    @FormUrlEncoded
    @POST("register.php")
    fun  registerUser (@FieldMap user:Map<String,String>) :Call<LoginBaseResponse>


    @FormUrlEncoded
    @POST("add/category.php")
    fun  addCategory (@FieldMap user:Map<String,String>) :Call<BaseResponse>


    @FormUrlEncoded
    @POST("add/brand.php")
    fun  addBrand (@FieldMap user:Map<String,String>) :Call<BaseResponse>

    @FormUrlEncoded
    @POST("add/product.php")
    fun  addProduct (@FieldMap user:Map<String,String>) :Call<BaseResponse>


    @GET("get/brand.php")
    fun getBrand(@Query("id_shop") id_shop:Int) :Call<CategoryResponse>


    @GET("get/category.php")
    fun getCategory(@Query("id_shop") id_shop:Int) :Call<CategoryResponse>


    @GET("get/product.php")
    fun getProduct(@Query("id_shop") id_shop:Int) :Call<ProductResponse>






}