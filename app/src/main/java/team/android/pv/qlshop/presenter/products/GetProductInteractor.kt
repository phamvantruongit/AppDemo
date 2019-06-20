package team.android.pv.qlshop.presenter.product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import team.android.pv.qlshop.model.Product
import team.android.pv.qlshop.MyApplication.Companion.apiClient
import team.android.pv.qlshop.model.Category
import team.android.pv.qlshop.model.response.CategoryResponse
import team.android.pv.qlshop.model.response.ProductResponse
import team.android.pv.qlshop.presenter.Inteface.OnFinishedListenerFail

class GetProductInteractor {
    var listProduct = ArrayList<Product>()

    fun getListProducts(onFinishedListener: OnFinishedListenerProduct, id_shop: Int, id_category: Int, page: Int) {

        apiClient!!.getProducts(id_shop, id_category, page)
            .enqueue(object : Callback<ProductResponse> {
                override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>) {
                    if (response.body()!!.code == 200) {
                        var  total_pages:Float=response.body()!!.total_pages
                        var  current_page:Float=response.body()!!.current_page
                        var  isLoad:Boolean= current_page < total_pages
                        if(current_page==1f){
                            listProduct = response.body()!!.listProduct
                        }else{
                            listProduct.addAll(response.body()!!.listProduct)
                        }
                        onFinishedListener.onResultListProducts(listProduct,isLoad,current_page)

                    } else {
                        onFinishedListener.onResultFail(response.body()!!.message)
                    }

                }

                override fun onFailure(call: Call<ProductResponse>, t: Throwable) {

                    onFinishedListener.onResultFail(t.message.toString())

                }


            })
    }


    fun getListNameCategory(onFinishedListener: OnFinishedListenerProduct, id_shop: Int) {


        apiClient!!.getCategorys(id_shop).enqueue(object : Callback<CategoryResponse> {

            override fun onResponse(call: Call<CategoryResponse>, response: Response<CategoryResponse>) {
                if (response.body()!!.code == 200) {
                    onFinishedListener.onResultSuccess(response.body()!!.listCategory)
                } else {
                    onFinishedListener.onResultFail(response.body()!!.message)
                }
            }

            override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
                onFinishedListener.onResultFail(t.message.toString())
            }

        })


    }



    fun getListSearchProduct(
        listener: OnFinishedListenerSearchProduct,
        id_shop: Int,
        barcode: String,
        name: String,
        id: String
    ){

        apiClient!!.searchProduct(id_shop,barcode,name,id).enqueue(object :Callback<ProductResponse>{
            override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>) {
                if(response.body()!!.code==200){
                    listener.onResultListProducts(response.body()!!.listProduct)
                }else{
                    listener.onResultFail(response.body()!!.message)
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                listener.onResultFail(t.message.toString())
            }
        })

    }


    interface OnFinishedListenerProduct : OnFinishedListenerFail {
        fun onResultListProducts(
            listProduct: ArrayList<Product>,
            load: Boolean,
            current_page: Float
        )
        fun onResultSuccess(listCategory: ArrayList<Category>)
    }


    interface OnFinishedListenerSearchProduct : OnFinishedListenerFail {
        fun onResultListProducts( listProduct: ArrayList<Product>)
    }

}