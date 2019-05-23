package team.android.pv.qlshop.view.activity

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.vision.barcode.Barcode
import com.notbytes.barcode_reader.BarcodeReaderActivity
import kotlinx.android.synthetic.main.activity_add_product.*
import kotlinx.android.synthetic.main.toolbar.*
import org.json.JSONObject
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.Product
import team.android.pv.qlshop.model.User
import team.android.pv.qlshop.model.data.SharedPreferencesManager
import team.android.pv.qlshop.presenter.product.AddProductInteractor
import team.android.pv.qlshop.presenter.product.AddProductPresenter
import team.android.pv.qlshop.view.views.ViewProduct

class AddProductActivity : AppCompatActivity(), ViewProduct {
    private var dataJson: String = ""
    private var barcode: String = ""

    private var productPresenter: AddProductPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)
        productPresenter = AddProductPresenter(this, AddProductInteractor())



        imgRight?.visibility=View.VISIBLE
        imgRight.setImageResource(R.drawable.ic_add)


        imgBarcodes.setOnClickListener{
            launchBarCodeActivity()
        }



        rlCategory.setOnClickListener{

            var intent = Intent(this, ShowCaBrActivity::class.java)
            startActivityForResult(intent, 101)

        }

       imgRight.setOnClickListener{

            var product = Product()
            var category = ""
            var brand = ""
            if(dataJson!="") {
                var json = JSONObject(dataJson)
                category = json.getString("category")
                brand = json.getString("brand")
            }

            product.name=edNameProduct.text.toString()
            if(barcode!=""){
                product.barcode=barcode
                Log.d("PPP",barcode)
            }
            return@setOnClickListener
            product.description=edDesciptionProduct.text.toString()
            product.category=category
            product.brand=brand
            product.amount=edAmount.text.toString().toInt()
            product.price_in= 1
            product.price_out= edPrice_out.text.toString().toInt().toLong()
            product.price_outs= edPrice_outs.text.toString().toInt().toLong()
            product.note=edNote.text.toString()
            product.unit=edNote.text.toString()

            SharedPreferencesManager.getInstanceSharedPreferencesManager(this)
            val users:User= SharedPreferencesManager.getUser()!!
            product.id_shop=users.id_shop

            productPresenter!!.addProduct(product)


        }
    }

    private fun launchBarCodeActivity() {
        var intent = BarcodeReaderActivity.getLaunchIntent(this, true, false)
        startActivityForResult(intent, 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == 100 && data != null) {
            barcode = data.getParcelableExtra<Barcode>(BarcodeReaderActivity.KEY_CAPTURED_BARCODE).rawValue
            edBarcode.setTextColor(resources.getColor(R.color.black))
            edBarcode.setText(barcode)
        }


        if (requestCode == 101 && resultCode == Activity.RESULT_OK) {
            dataJson = data!!.getStringExtra("data")
        }
    }


    override fun setSuccess(success: String) {
        Toast.makeText(this, success, Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun setDataError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

}
