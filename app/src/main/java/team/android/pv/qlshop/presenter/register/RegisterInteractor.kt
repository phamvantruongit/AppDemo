package team.android.pv.qlshop.presenter.register

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import team.android.pv.qlshop.MyApplication.Companion.apiClient
import team.android.pv.qlshop.model.response.LoginBaseResponse
import team.android.pv.qlshop.model.User
import team.android.pv.qlshop.presenter.Inteface.OnFinishedListener

class RegisterInteractor {
    fun registerUserAPI(onFinishedListener: OnFinishedListener, user: User ,check_admin:Int) {

        var param = HashMap<String, String>()
        param.put("email", user.email)
        param.put("name_shop", user.name_shop)
        param.put("name", user.name)
        param.put("check_admin", check_admin.toString())
        param.put("password", user.password)
        param.put("id_shop", user.id_shop.toString())


        val call: Call<LoginBaseResponse> = apiClient.registerUser(param)

        call.enqueue(object : Callback<LoginBaseResponse> {
            override fun onResponse(call: Call<LoginBaseResponse>, response: Response<LoginBaseResponse>) {
                if (response.body()!!.code == 200) {
                    onFinishedListener.onResultSuccess(user)
                }

                if (response.body()!!.code == 400) {
                    onFinishedListener.onResultFail(response.body()!!.message)
                }

                if (response.body()!!.code == 401) {
                    onFinishedListener.onResultFail(response.body()!!.message)
                }
            }

            override fun onFailure(call: Call<LoginBaseResponse>, t: Throwable) {
                onFinishedListener.onResultFail(t.message.toString())
            }

        })

    }
}