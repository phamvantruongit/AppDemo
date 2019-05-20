package team.android.pv.qlshop.presenter.category

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import team.android.pv.qlshop.MyApplication.Companion.apiClient
import team.android.pv.qlshop.model.response.BaseResponse
import team.android.pv.qlshop.model.Category
import team.android.pv.qlshop.model.response.CategoryResponse
import team.android.pv.qlshop.presenter.Inteface.OnFinishedListeners

class CategoryInteractor {

    fun addCategoryToAPI(onFinishedListeners: OnFinishedListeners,name:String ,id_shop :Int ,check:Boolean){
        var param=HashMap<String,String>()
        param.put("name",name)
        param.put("id_shop", id_shop.toString())

        var call:Call<BaseResponse>?
        if(check){
           call =apiClient.addCategory(param)
        }else{
            call =apiClient.addBrand(param)
        }
        call!!.enqueue(object :Callback<BaseResponse>{


            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                    if(response.body()!!.code==200){
                         onFinishedListeners.onResultSuccess(response.body()!!.message)
                    }

            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                onFinishedListeners.onResultFail(t.message.toString())
            }

        })
    }

    fun getCategoryToAPI(onFinishedListenersCategory: OnFinishedListenersCategory,id_shop: Int,check:Boolean){
        var call:Call<CategoryResponse>?

        if(check){
            call= apiClient.getCategory(id_shop)
        }
        else{
            call= apiClient.getBrand(id_shop)
        }

        call!!.enqueue(object : Callback<CategoryResponse>{

            override fun onResponse(call: Call<CategoryResponse>, response: Response<CategoryResponse>) {
                if(response.body()!!.code==200){
                    onFinishedListenersCategory.onResultSuccess(response.body()!!.listCategory)
                }
            }

            override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
                   onFinishedListenersCategory.onResulCategorytFail(t.message.toString())
            }

        })

    }

    interface OnFinishedListenersCategory {
        fun onResulCategorytFail(strError : String)
        fun onResultSuccess(listCategory: ArrayList<Category>)

    }

}