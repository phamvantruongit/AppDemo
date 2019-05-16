package team.android.pv.qlshop.view.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.gms.vision.barcode.Barcode
import com.notbytes.barcode_reader.BarcodeReaderActivity
import kotlinx.android.synthetic.main.activity_add_product.*
import team.android.pv.qlshop.R

class AddProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)
        imgBarcode.setOnClickListener({
            launchBarCodeActivity()
        })

        rlfeature.setOnClickListener({

        })

        rlCategory.setOnClickListener({

            var intent=Intent(this,ShowCaBrActivity::class.java)
            startActivity(intent)

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
            Toast.makeText(this, barcode.rawValue, Toast.LENGTH_SHORT).show()
        }
    }

}
