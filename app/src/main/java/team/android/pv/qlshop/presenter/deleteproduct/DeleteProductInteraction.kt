package team.android.pv.qlshop.presenter.deleteproduct

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import team.android.pv.qlshop.MyApplication
import team.android.pv.qlshop.model.response.BaseResponse
import team.android.pv.qlshop.presenter.Inteface.OnFinishedListener
import team.android.pv.qlshop.presenter.Inteface.OnFinishedListeners

class DeleteProductInteraction {
    fun deleteProduct(listenner: OnFinishedListeners, id: Int, id_shop: Int) {
        MyApplication.apiClient!!.deleteProduct(id_shop, id).enqueue(object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                if(response.body()!!.code==200) {
                    listenner.showMessage(response.body()!!.message)
                }else{
                    listenner.onResultFail(response.body()!!.message)
                }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                listenner.onResultFail(t.toString())
            }
        })
    }
}