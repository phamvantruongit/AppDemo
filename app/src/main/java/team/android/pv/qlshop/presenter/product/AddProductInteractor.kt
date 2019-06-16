package team.android.pv.qlshop.presenter.product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import team.android.pv.qlshop.model.Product
import team.android.pv.qlshop.MyApplication.Companion.apiClient
import team.android.pv.qlshop.model.response.BaseResponse import team.android.pv.qlshop.presenter.Inteface.OnFinishedListeners

class AddProductInteractor {

    fun addProduct(onFinishedListener: OnFinishedListeners, product: Product) {

        var param = HashMap<String, String>()
        param.put("name", product.name)
       // param.put("content", product.description)
        param.put("barcode", product.barcode)
        param.put("amount", product.amount.toString())
        param.put("price_in", product.price_in.toString())
        param.put("price_out", product.price_out.toString())
        param.put("id_category", product.id_category.toString())
        param.put("category", product.category)
        param.put("brand", product.brand)
        param.put("unit",product.unit)
        param.put("id_shop",product.id_shop.toString())
        param.put("id_brand",product.id_brand.toString())
        param.put("id_size",product.id_size.toString())
        param.put("size",product.size)


        apiClient.addProduct(param).enqueue(object :Callback<BaseResponse>{


            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                if(response.body()!!.code==200){
                    onFinishedListener.showMessage(response.body()!!.message)
                }else{
                    onFinishedListener.onResultFail(response.body()!!.message)
                }

            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                onFinishedListener.onResultFail(t.message.toString())
            }

        })

    }


    fun editProduct(onFinishedListener: OnFinishedListeners, product: Product) {

        var param = HashMap<String, String>()
        param.put("id",product.id.toString())
        param.put("name", product.name)
       // param.put("desciption", product.description)
        param.put("barcode", product.barcode)
        param.put("amount", product.amount.toString())
        param.put("price_in", product.price_in.toString())
        param.put("price_out", product.price_out.toString())
        param.put("id_category", product.id_category.toString())
        param.put("category", product.category)
        param.put("brand", product.brand)
        param.put("unit",product.unit)
        param.put("id_shop",product.id_shop.toString())

        apiClient.editProduct(param).enqueue(object :Callback<BaseResponse>{


            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                if(response.body()!!.code==200){
                    onFinishedListener.showMessage(response.body()!!.message)
                }else{
                    onFinishedListener.onResultFail(response.body()!!.message)
                }

            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                onFinishedListener.onResultFail(t.message.toString())
            }

        })


    }



}


