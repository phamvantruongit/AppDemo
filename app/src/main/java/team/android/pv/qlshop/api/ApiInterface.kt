package team.android.pv.qlshop.api
import android.graphics.pdf.PdfDocument
import okhttp3.RequestBody
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


    @FormUrlEncoded
    @POST("edit/product.php")
    fun  editProduct (@FieldMap user:Map<String,String>) :Call<BaseResponse>


    @FormUrlEncoded
    @POST("edit/category.php")
    fun  editCategory (@FieldMap user:Map<String,String>) :Call<BaseResponse>


    @FormUrlEncoded
    @POST("edit/brand.php")
    fun  editBrand (@FieldMap user:Map<String,String>) :Call<BaseResponse>


    @FormUrlEncoded
    @POST("edit/brand.php")
    fun  editUser (@FieldMap user:Map<String,String>) :Call<BaseResponse>


    @GET("get/brands.php")
    fun getBrands(@Query("id_shop") id_shop:Int) :Call<CategoryResponse>


    @GET("get/categorys.php")
    fun getCategorys(@Query("id_shop") id_shop:Int) :Call<CategoryResponse>


    @GET("get/products.php")
    fun getProducts(@Query("id_shop") id_shop:Int ,@Query("id_category") id_category : Int ,@Query("page") page: Int) :Call<ProductResponse>


    @GET("get/users.php")
    fun getUsers(@Query("id_shop") id_shop:Int) :Call<UsersResponse>


    @DELETE("delete/user.php")
    fun deleteUser(@Query("id_shop") id_shop: Int,@Query("uid") id: Int) : Call<BaseResponse>


    @DELETE("delete/product.php")
    fun deleteProduct(@Query("id_shop") id_shop: Int,@Query("uid") id: Int) : Call<BaseResponse>


    @GET("get/search.php")
    fun searchProduct(@Query("id_shop") id_shop:Int ,@Query("barcode") barcode : String ,@Query("name") name: String) :Call<ProductResponse>


}