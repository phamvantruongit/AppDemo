package team.android.pv.qlshop.view.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.vision.barcode.Barcode
import com.notbytes.barcode_reader.BarcodeReaderActivity
import kotlinx.android.synthetic.main.activity_add_product.*
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.Product
import team.android.pv.qlshop.model.User
import team.android.pv.qlshop.model.data.SharedPreferencesManager
import team.android.pv.qlshop.presenter.product.AddProductInteractor
import team.android.pv.qlshop.presenter.product.AddProductPresenter
import team.android.pv.qlshop.view.views.ViewProduct

class AddProductActivity : AppCompatActivity(), ViewProduct {


    private  var productPresenter: AddProductPresenter? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)
        productPresenter= AddProductPresenter(this, AddProductInteractor())
        imgBarcode.setOnClickListener({
            launchBarCodeActivity()
        })

        rlfeature.setOnClickListener({

        })

        rlCategory.setOnClickListener({

            var intent=Intent(this,ShowCaBrActivity::class.java)
            startActivity(intent)

        })

        btnAddProduct.setOnClickListener({

            var product =Product()
            product.name=edNameProduct.text.toString()
            product.description=edDesciptionProduct.text.toString()
            product.barcode=edBarcode.text.toString()
            product.amount=1
            product.price_in= 1000
            product.price_out= edPrice_out.text.toString().toInt().toLong()
            product.price_outs= edPrice_outs.text.toString().toInt().toLong()

            SharedPreferencesManager.getInstanceSharedPreferencesManager(this)
            val user:User= SharedPreferencesManager.getUser()!!
            product.id_shop=user.id_shop

            productPresenter!!.addProduct(product)


        })
    }
    private fun launchBarCodeActivity(){
        var intent= BarcodeReaderActivity.getLaunchIntent(this,true,false)
        startActivityForResult(intent,100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == 100 && data != null) {
            val barcode = data.getParcelableExtra<Barcode>(BarcodeReaderActivity.KEY_CAPTURED_BARCODE)
        }
    }


    override fun setSuccess(success: String) {
        Toast.makeText(this,success ,Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun setDataError(error: String) {
      Toast.makeText(this,error,Toast.LENGTH_SHORT).show()
    }

}
