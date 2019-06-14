package team.android.pv.qlshop.view.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_detail_product.*
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.Product
import team.android.pv.qlshop.presenter.deleteproduct.DeleteProductPresenter
import team.android.pv.qlshop.presenter.deleteproduct.DeleteProductInteraction
import team.android.pv.qlshop.view.view.ViewDeleteProduct
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class DetailProductActivity : AppCompatActivity(), ViewDeleteProduct {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_product)

        var product: Product = intent.getParcelableExtra("product")

        tvMaSP.setText("${product.id}")
        tvNameProduct.setText(product.name)
        if (product.description != "") {
            tvdescription.setText(product.description)
        }
        if (product.barcode != "") {
            tvbarcode.setText(product.barcode)
        }
        tvAmount.setText("${product.amount}")
        tvPriceOut.setText(get(product.price_out))
        tvpriceIn.setText(get(product.price_in))
        if (product.category != "" || !product.category.equals("Null")) {
            tvcategory.setText(product.category)
        }

        if (product.brand != "" || !product.brand.equals("Null")) {
            tvbrand.setText(product.brand)
        }


        tvAddProductDetail.setOnClickListener {
            var intent = Intent(this, AddProductActivity::class.java)
            startActivity(intent)
        }

        tvEditProductDetail.setOnClickListener {
            var intent = Intent(this, AddProductActivity::class.java)
            intent.putExtra("product", product)
            startActivity(intent)
        }


        tvDeleteProductDetail.setOnClickListener {
            DeleteProductPresenter(this, DeleteProductInteraction()).deleteProduct(product.id, product.id_shop)
        }


    }

    override fun showMessageError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        finish()
    }

    fun get(number: Long): String {
        val formatter = NumberFormat.getInstance(Locale.US) as DecimalFormat
        formatter.applyPattern("#,###,###,###")
        return formatter.format(number).toString()
    }
}
