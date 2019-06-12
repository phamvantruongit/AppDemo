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
        var call: Call<LoginBaseResponse> ?=null
        if(check_admin==0){
          param.put("id_shop", user.id_shop.toString())
          param.put("name_type","NV")
          param.put("check_admin","memmber")
          call  = apiClient.registerMemmber(param)
        }else{
            param.put("name_type","QL")
            param.put("check_admin","admin")
            call  = apiClient.registerAdmin(param)
        }



      call.enqueue(object  :Callback<LoginBaseResponse>{


          override fun onResponse(call: Call<LoginBaseResponse>, response: Response<LoginBaseResponse>) {
               if(response.code()==200){
                  onFinishedListener.onResultSuccess(response.body()!!.user)
               }

              if(response.code()==401){
                  onFinishedListener.onResultFail(response.body()!!.message)
              }

              if(response.code()==400){
                  onFinishedListener.onResultFail(response.body()!!.message)

              }
          }


          override fun onFailure(call: Call<LoginBaseResponse>, t: Throwable) {
              onFinishedListener.onResultFail(t.message.toString())
          }

      })


    }
}