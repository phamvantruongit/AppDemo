package team.android.pv.qlshop.presenter.product
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import team.android.pv.qlshop.model.Product
import team.android.pv.qlshop.MyApplication.Companion.apiClient
import team.android.pv.qlshop.model.Category
import team.android.pv.qlshop.model.response.BaseResponse
import team.android.pv.qlshop.model.response.CategoryResponse
import team.android.pv.qlshop.model.response.ProductResponse
import team.android.pv.qlshop.presenter.Inteface.OnFinishedListenerFail
import team.android.pv.qlshop.presenter.Inteface.OnFinishedListeners

class GetProductInteractor {


    fun getListProducts(onFinishedListener: OnFinishedListenerProduct, id_shop: Int, id_category: Int, page: Int) {
        apiClient.getProducts(id_shop, id_category, page)
            .enqueue(object : Callback<ProductResponse> {
                override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>) {
                    if (response.body()!!.code == 200) {
                        var  total_pages:Float=response.body()!!.total_pages
                        var  current_page:Float=response.body()!!.current_page
                        var  isLoad:Boolean= current_page < total_pages
                        Log.d("Load",isLoad.toString() + total_pages.toString() + current_page.toString())
                        var listProduct = ArrayList<Product>()
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


        apiClient.getCategorys(id_shop).enqueue(object : Callback<CategoryResponse> {

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


    fun deleteProduct(listener: OnFinishedListeners, id: Int, id_shop: Int) {
        apiClient.deleteProduct(id, id_shop)
            .enqueue(object : Callback<BaseResponse> {
                override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                    if (response.body()!!.code == 200) {
                        listener.showMessage(response.body()!!.message)
                    } else {
                        listener.showMessage(response.body()!!.message)
                    }
                }

                override fun onFailure(call: Call<BaseResponse>, t: Throwable) {

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

}