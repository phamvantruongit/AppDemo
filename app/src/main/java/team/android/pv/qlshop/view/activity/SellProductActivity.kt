package team.android.pv.qlshop.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.gms.vision.barcode.Barcode
import com.notbytes.barcode_reader.BarcodeReaderActivity
import kotlinx.android.synthetic.main.toolbar.*
import team.android.pv.qlshop.R

class SellProductActivity :BaseActivity() {
   val BARCODE_READER_ACTIVITY_REQUEST :Int=100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tvTitle.text=this.resources.getText(R.string.title_sell)
        imgBarcode.visibility=View.VISIBLE
        imgBarcode.setOnClickListener{
            launchBarCodeActivity()
        }

        imgRight.visibility=View.VISIBLE
        imgRight.setOnClickListener {
            var intent=Intent(this,SearchSellProductActivity::class.java)
            startActivityForResult(intent,200)
        }
    }



    override fun getContentView(): Int {
        return R.layout.activity_sell_product
    }

    override fun getNavigationMenuItemId(): Int {
        return R.id.navigation_sell
    }

    private fun launchBarCodeActivity(){
        var intent=BarcodeReaderActivity.getLaunchIntent(this,true,false)
        startActivityForResult(intent,BARCODE_READER_ACTIVITY_REQUEST)
    }

      override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == BARCODE_READER_ACTIVITY_REQUEST && data != null) {
            val barcode = data.getParcelableExtra<Barcode>(BarcodeReaderActivity.KEY_CAPTURED_BARCODE)
            Toast.makeText(this, barcode.rawValue, Toast.LENGTH_SHORT).show()
        }
    }
}