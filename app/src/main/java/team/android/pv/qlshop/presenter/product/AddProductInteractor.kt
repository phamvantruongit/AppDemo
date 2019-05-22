package team.android.pv.qlshop.presenter.product

import retrofit2.Call
import retrofit2.Response
import team.android.pv.qlshop.model.Product
import team.android.pv.qlshop.MyApplication.Companion.apiClient
import team.android.pv.qlshop.model.response.BaseResponse
import team.android.pv.qlshop.model.response.ProductResponse
import team.android.pv.qlshop.presenter.Inteface.OnFinishedListeners

class AddProductInteractor {

    fun addProduct(onFinishedListener: OnFinishedListeners, product: Product){

        var param=HashMap<String,String>()
        param.put("name",product.name)
        param.put("desciption",product.description)
        param.put("barcode",product.barcode)
        param.put("price_outs", product.price_outs.toString())
        param.put("price_out", product.price_out.toString())
        param.put("price_in",product.price_in.toString())
        param.put("amount", product.amount.toString())
        param.put("id_shop",product.id_shop.toString())


        var call:Call<BaseResponse> = apiClient.addProduct(param)

        call!!.enqueue(object : retrofit2.Callback<BaseResponse> {


            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                if(response.body()!!.code==200){
                    onFinishedListener.onResultSuccess(response.body()!!.message)
                }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                onFinishedListener.onResultFail(t.message.toString())
            }

        })

    }


    fun getListProducts(onFinishedListener: OnFinishedListener,id_shop:Int){
        var call:Call<ProductResponse> = apiClient.getProducts(id_shop)
        call.enqueue(object :retrofit2.Callback<ProductResponse>{
            override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>) {

                if(response.body()!!.code==200){
                    onFinishedListener.onResultListProducts(response.body()!!.listProduct)
                }

            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {

                onFinishedListener.onResultFail(t.message.toString())

            }


        })
    }

    interface OnFinishedListener : OnFinishedListeners{
        fun onResultListProducts(listProduct: ArrayList<Product>)

    }

}