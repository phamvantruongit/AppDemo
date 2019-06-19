package team.android.pv.qlshop.presenter.login
import android.content.Context
import android.util.Log
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import team.android.pv.qlshop.MyApplication.Companion.apiClient
import team.android.pv.qlshop.model.response.LoginBaseResponse
import team.android.pv.qlshop.model.User
import team.android.pv.qlshop.presenter.Inteface.OnFinishedListener

class LoginInteractor {
    fun loginUserAPI(onFinishedListener: OnFinishedListener, user: User) {

        var param = HashMap<String, String>()
        param.put("email", user.email)
        param.put("password",user.password)

       var call:Call<LoginBaseResponse> = apiClient.loginUser(param)
       call.enqueue(object :Callback<LoginBaseResponse>{

           override fun onResponse(call: Call<LoginBaseResponse>, response: Response<LoginBaseResponse>) {

               if(response.body()!!.code==200){
                   onFinishedListener.onResultSuccess(response.body()!!.user)
               }

               if(response.body()!!.code==400){
                   onFinishedListener.onResultFail(response.body()!!.message)
               }

               if(response.body()!!.code==401){
                   onFinishedListener.onResultFail(response.body()!!.message)
               }

           }

           override fun onFailure(call: Call<LoginBaseResponse>, t: Throwable) {
                onFinishedListener.onResultFail(t.message.toString())
           }

       })

    }
}