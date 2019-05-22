package team.android.pv.qlshop.api
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import team.android.pv.qlshop.model.response.*

interface ApiInterface {


    @FormUrlEncoded
    @POST("login/login.php")
    fun loginUser(@FieldMap user:Map<String,String>) :Call<LoginBaseResponse>

    @FormUrlEncoded
    @POST("register/memmber.php")
    fun  registerMemmber (@FieldMap user:Map<String,String>) :Call<LoginBaseResponse>

    @FormUrlEncoded
    @POST("register/admin.php")
    fun  registerAdmin (@FieldMap user:Map<String,String>) :Call<LoginBaseResponse>


    @FormUrlEncoded
    @POST("add/category.php")
    fun  addCategory (@FieldMap user:Map<String,String>) :Call<BaseResponse>


    @FormUrlEncoded
    @POST("add/brand.php")
    fun  addBrand (@FieldMap user:Map<String,String>) :Call<BaseResponse>

    @FormUrlEncoded
    @POST("add/product.php")
    fun  addProduct (@FieldMap user:Map<String,String>) :Call<BaseResponse>


    @GET("get/brands.php")
    fun getBrands(@Query("id_shop") id_shop:Int) :Call<CategoryResponse>


    @GET("get/categorys.php")
    fun getCategorys(@Query("id_shop") id_shop:Int) :Call<CategoryResponse>


    @GET("get/products.php")
    fun getProducts(@Query("id_shop") id_shop:Int) :Call<ProductResponse>


    @GET("get/users.php")
    fun getUsers(@Query("id_shop") id_shop:Int) :Call<UsersResponse>







}