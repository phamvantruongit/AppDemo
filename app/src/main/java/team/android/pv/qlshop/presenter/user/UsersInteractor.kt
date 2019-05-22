package team.android.pv.qlshop.presenter.user

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import team.android.pv.qlshop.MyApplication
import team.android.pv.qlshop.MyApplication.Companion.apiClient
import team.android.pv.qlshop.model.Product
import team.android.pv.qlshop.model.User
import team.android.pv.qlshop.model.response.CategoryResponse
import team.android.pv.qlshop.model.response.UsersResponse
import team.android.pv.qlshop.presenter.Inteface.OnFinishedListeners

class UsersInteractor {


    fun getListUser(id_shop:Int,onFinishedListener: OnFinishedListener){
//        var param=HashMap<String,String>()
//        param.put("id_shop", id_shop.toString())

        var call: Call<UsersResponse> =apiClient.getUsers(id_shop)

        call.enqueue(object : Callback<UsersResponse>{


            override fun onResponse(call: Call<UsersResponse>, response: Response<UsersResponse>) {
                   if(response.body()!!.code==200){
                        onFinishedListener.onResultListProducts(response.body()!!.listUser)
                   }
            }

            override fun onFailure(call: Call<UsersResponse>, t: Throwable) {


            }

        })
    }

    interface OnFinishedListener : OnFinishedListeners {
        fun onResultListProducts(listUser: ArrayList<User>)
    }
}