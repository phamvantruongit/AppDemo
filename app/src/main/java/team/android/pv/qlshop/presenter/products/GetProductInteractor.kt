package team.android.pv.qlshop.presenter.product

import retrofit2.Call
import retrofit2.Response
import team.android.pv.qlshop.model.Product
import team.android.pv.qlshop.MyApplication.Companion.apiClient
import team.android.pv.qlshop.model.response.ProductResponse
import team.android.pv.qlshop.presenter.Inteface.OnFinishedListenerFail

class GetProductInteractor {


    fun getListProducts(onFinishedListener: OnFinishedListener, id_shop: Int) {
        var call: Call<ProductResponse> = apiClient.getProducts(id_shop)
        call.enqueue(object : retrofit2.Callback<ProductResponse> {
            override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>) {

                if (response.body()!!.code == 200) {
                    onFinishedListener.onResultListProducts(response.body()!!.listProduct)
                }


                if (response.body()!!.code == 400) {
                    onFinishedListener.onResultFail(response.body()!!.message)
                }

            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {

                onFinishedListener.onResultFail(t.message.toString())

            }


        })
    }

    interface OnFinishedListener : OnFinishedListenerFail {
        fun onResultListProducts(listProduct: ArrayList<Product>)

    }

}