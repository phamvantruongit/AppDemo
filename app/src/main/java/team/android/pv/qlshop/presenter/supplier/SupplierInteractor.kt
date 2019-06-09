package team.android.pv.qlshop.presenter.supplier

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import team.android.pv.qlshop.MyApplication
import team.android.pv.qlshop.model.Supplier
import team.android.pv.qlshop.model.response.BaseResponse
import team.android.pv.qlshop.model.response.SupplierResponse
import team.android.pv.qlshop.presenter.Inteface.OnFinishedListenerFail
import team.android.pv.qlshop.presenter.Inteface.OnFinishedListeners

class SupplierInteractor {

    fun addSupplier(
        listeners: OnFinishedListeners,
        supplier: Supplier,
        checkCustomer: Boolean
    ) {
        var param = HashMap<String, String>()
        param.put("id_shop",supplier.id_shop.toString())
        param.put("name", supplier.name)
        param.put("address", supplier.address)
        param.put("phone", supplier.phone.toString())
        param.put("email", supplier.email)
        param.put("description", supplier.description)
        if(checkCustomer){
            MyApplication.apiClient.addCustomer(param)
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
        }else{
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


    fun editSupplier(
        listeners: OnFinishedListeners,
        supplier: Supplier,
        checkCustomer: Boolean
    ) {
        var param = HashMap<String, String>()
        param.put("id", supplier.id.toString())
        param.put("name", supplier.name)
        param.put("address", supplier.address)
        param.put("phone", supplier.phone.toString())
        param.put("email", supplier.email)
        param.put("description", supplier.description)
        param.put("id_shop",supplier.id_shop.toString())
        if(checkCustomer){
            MyApplication.apiClient.editCustomer(param)
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
        }else{
            MyApplication.apiClient.editSupplier(param)
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


    fun getListSupplier(
        id_shop: Int,
        listeners: OnFinishedListenerSupplier,
        checkCustomer: Boolean
    ){
        if(checkCustomer){
            MyApplication.apiClient.getCustomer(id_shop).enqueue(object :Callback<SupplierResponse>{
                override fun onResponse(call: Call<SupplierResponse>, response: Response<SupplierResponse>) {
                    if(response.body()!!.code==200){
                        listeners.onResultListSupplier(response.body()!!.listSupplier)
                    }else{
                        listeners.onResultFail(response.body()!!.message)
                    }
                }

                override fun onFailure(call: Call<SupplierResponse>, t: Throwable) {
                    listeners.onResultFail(t.message.toString())
                }
            })
        }else{
            MyApplication.apiClient.getSupplier(id_shop).enqueue(object :Callback<SupplierResponse>{
                override fun onResponse(call: Call<SupplierResponse>, response: Response<SupplierResponse>) {
                    if(response.body()!!.code==200){
                        listeners.onResultListSupplier(response.body()!!.listSupplier)
                    }else{
                        listeners.onResultFail(response.body()!!.message)
                    }
                }

                override fun onFailure(call: Call<SupplierResponse>, t: Throwable) {
                    listeners.onResultFail(t.message.toString())
                }
            })
        }

    }

    fun deleteInfor(id_shop: Int,id:Int,listeners: OnFinishedListenerFail, checkCustomer: Boolean){
        if(checkCustomer) {
            MyApplication.apiClient.deleteCustomer(id_shop, id).enqueue(object : Callback<BaseResponse> {
                override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                    listeners.onResultFail(response.body()!!.message)
                }

                override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                    listeners.onResultFail(t.message.toString())
                }
            })
        }else{
            MyApplication.apiClient.deleteSupplier(id_shop, id).enqueue(object : Callback<BaseResponse> {
                override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                    listeners.onResultFail(response.body()!!.message)
                }

                override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                    listeners.onResultFail(t.message.toString())
                }
            })
        }
    }

    interface OnFinishedListenerSupplier : OnFinishedListenerFail {
        fun onResultListSupplier( list: ArrayList<Supplier>)
    }




}