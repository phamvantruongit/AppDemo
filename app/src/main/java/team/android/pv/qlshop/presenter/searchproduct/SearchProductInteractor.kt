package team.android.pv.qlshop.presenter.searchproduct

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Query
import team.android.pv.qlshop.MyApplication.Companion.apiClient
import team.android.pv.qlshop.model.Category
import team.android.pv.qlshop.model.Product
import team.android.pv.qlshop.model.response.CategoryResponse
import team.android.pv.qlshop.model.response.ProductResponse
import team.android.pv.qlshop.presenter.Inteface.OnFinishedListenerFail
import team.android.pv.qlshop.presenter.category.CategoryInteractor

class SearchProductInteractor {

    fun getListSearchProduct( listener  :OnFinishedListenerProduct , id_shop:Int, barcode : String,  name: String){

       apiClient.searchProduct(id_shop,barcode,name).enqueue(object :Callback<ProductResponse>{
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


    fun getListCategory(listeners: OnFinishedListenerProduct, id_shop: Int){
        apiClient.getCategorys(id_shop).enqueue(object : Callback<CategoryResponse>{

            override fun onResponse(call: Call<CategoryResponse>, response: Response<CategoryResponse>) {
                if(response.body()!!.code==200){
                    listeners.onResultCategory(response.body()!!.listCategory)
                }else{
                    listeners.onResultFail(response.body()!!.message)
                }
            }

            override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
                listeners.onResultFail(t.message.toString())
            }

        })

    }


    interface OnFinishedListenerProduct : OnFinishedListenerFail {
        fun onResultListProducts( listProduct: ArrayList<Product>)
        fun onResultCategory(listCategory: ArrayList<Category>)
    }


}