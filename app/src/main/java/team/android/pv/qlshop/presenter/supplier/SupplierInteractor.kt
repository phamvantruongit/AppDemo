package team.android.pv.qlshop.presenter.supplier

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import team.android.pv.qlshop.MyApplication
import team.android.pv.qlshop.model.Supplier
import team.android.pv.qlshop.model.response.BaseResponse
import team.android.pv.qlshop.presenter.Inteface.OnFinishedListeners

class SupplierInteractor {

    fun addSupplier(listeners : OnFinishedListeners, supplier: Supplier) {
        var param = HashMap<String, String>()
        param.put("name", supplier.name)
        param.put("address", supplier.address)
        param.put("phone", supplier.phone.toString())
        param.put("email", supplier.email)
        param.put("description", supplier.description)

        MyApplication.apiClient.addSupplier(param)
            .enqueue(object : Callback<BaseResponse> {
                override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                    if(response.body()!!.code==200){
                         listeners.showMessage(response.body()!!.message)
                    }else{
                        listeners.onResultFail(response.body()!!.message)
                    }
                }

                override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                    listeners.onResultFail(t.message.toString())
                }
            })
    }




}