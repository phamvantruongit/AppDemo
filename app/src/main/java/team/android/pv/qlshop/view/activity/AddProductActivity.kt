package team.android.pv.qlshop.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.google.android.gms.vision.barcode.Barcode
import com.notbytes.barcode_reader.BarcodeReaderActivity
import kotlinx.android.synthetic.main.layout_product.*
import kotlinx.android.synthetic.main.toolbar.*
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.Category
import team.android.pv.qlshop.model.Product
import team.android.pv.qlshop.model.Supplier
import team.android.pv.qlshop.presenter.product.AddProductInteractor
import team.android.pv.qlshop.presenter.product.AddProductPresenter
import team.android.pv.qlshop.view.adapter.AdapterBrand
import team.android.pv.qlshop.view.adapter.AdapterCategory
import team.android.pv.qlshop.view.views.ViewProduct
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*


class AddProductActivity : BaseActivitys(), ViewProduct {
    private var barcode: String = ""
    private var id_category=0
   private  var products:Product?=null

    private var productPresenter: AddProductPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)
        productPresenter = AddProductPresenter(this,AddProductInteractor())

        getData()

        imgRight?.visibility=View.VISIBLE
        imgRight.setImageResource(R.drawable.ic_add)


        imgBarcodes.setOnClickListener{
            launchBarCodeActivity()
        }


        imgCategory.setOnClickListener {
            var intent = Intent(this, AddCategoryActivity::class.java)
            intent.putExtra("checkCategory",true)
            startActivityForResult(intent, 101)
        }


        imgBrand.setOnClickListener {
            var intent = Intent(this, AddCategoryActivity::class.java)
            intent.putExtra("checkCategory",false)
            startActivityForResult(intent, 102)
        }


        imgSupplier.setOnClickListener {

            var intent=Intent(this,ActivitySupplier::class.java)
            startActivityForResult(intent, 103)

        }

        edPrice_in.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                edPrice_in.removeTextChangedListener(this)
                try {
                    var originalString = s.toString()

                    val longval: Long?
                    if (originalString.contains(",")) {
                        originalString = originalString.replace(",".toRegex(), "")
                    }
                    longval = java.lang.Long.parseLong(originalString)

                    val formatter = NumberFormat.getInstance(Locale.US) as DecimalFormat
                    formatter.applyPattern("#,###,###,###")
                    val formattedString = formatter.format(longval)

                    edPrice_in.setText(formattedString)
                    edPrice_in.setSelection(edPrice_in.text.length)
                } catch (nfe: NumberFormatException) {
                    nfe.printStackTrace()
                }

                edPrice_in.addTextChangedListener(this)
            }

        })


        edPrice_out.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                edPrice_out.removeTextChangedListener(this)
                try {
                    var originalString = s.toString()

                    val longval: Long?
                    if (originalString.contains(",")) {
                        originalString = originalString.replace(",".toRegex(), "")
                    }
                    longval = java.lang.Long.parseLong(originalString)

                    val formatter = NumberFormat.getInstance(Locale.US) as DecimalFormat
                    formatter.applyPattern("#,###,###,###")
                    val formattedString = formatter.format(longval)

                    edPrice_out.setText(formattedString)
                    edPrice_out.setSelection(edPrice_out.text.length)
                } catch (nfe: NumberFormatException) {
                    nfe.printStackTrace()
                }

                edPrice_out.addTextChangedListener(this)
            }

        })


        imgRight.setOnClickListener{

            var product = Product()

            var name=edNameProduct.text.toString()
            var barcode=edBarcode.text.toString()
            var amount=edAmount.text.toString()
            var price_in=edPrice_in.text.toString().replace(",","")
            var price_out=edPrice_out.text.toString().replace(",","")

            if(TextUtils.isEmpty(name)){
                edNameProduct.error=getString(R.string.enter_info)
                return@setOnClickListener
            }

           if(TextUtils.isEmpty(edAmount.text.toString())){
               edAmount.error=getString(R.string.enter_info)
               return@setOnClickListener
           }

           if(amount.toInt()<=0 ){
               edAmount.error=getString(R.string.amout)
               return@setOnClickListener

           }

           if(TextUtils.isEmpty(edPrice_in.text.toString())){
               edPrice_in.error=getString(R.string.enter_info)
               return@setOnClickListener
           }

           if(price_in.toLong()<=0L){
               edPrice_in.error=getString(R.string.price)
               return@setOnClickListener
           }

           if(TextUtils.isEmpty(edPrice_out.text.toString())){
               edPrice_out.error=getString(R.string.enter_info)
               return@setOnClickListener
           }



           if(price_out.toLong()<=0L ){
             edPrice_out.error=getString(R.string.prices)
             return@setOnClickListener
           }


            product.name=name
            if(barcode!=""){
                product.barcode=barcode
            }


            product.barcode=edBarcode.text.toString()
            product.description=edDesciptionProduct.text.toString()
            product.category=edCategory.text.toString()
            product.brand=edBrand.text.toString()
            product.amount=amount.toInt()
            product.price_in= price_in.toLong()
            product.price_out=price_out.toLong()
            product.id_shop=userSave!!.id_shop
            product.id_category=id_category

            if(products!=null){
                 product.id=products!!.id
                 productPresenter!!.editProduct(product)

            }else {

                productPresenter!!.addProduct(product)
            }


        }
    }

    fun totalSum(sum:Long):String{
        val formatter = NumberFormat.getInstance(Locale.US) as DecimalFormat
        formatter.applyPattern("#,###,###,###")
        val total = formatter.format(sum)
        return total.toString()
    }

    private fun getData() {
        products=intent.getParcelableExtra<Product>("product")
        if(products!=null){
            tvTitle.text="Sua san pham"
            edNameProduct.setText(products!!.name)
            if(!products!!.description.equals("Null")) {
                edDesciptionProduct.setText(products!!.description)
            }
            edBarcode.setText(products!!.barcode)
            edPrice_in.setText(totalSum(products!!.price_in))
            edPrice_out.setText(totalSum(products!!.price_out))
            edAmount.setText(products!!.amount.toString())
            if(!products!!.category.equals("Null")) {
                edCategory.setText(products!!.category)
            }
            if(!products!!.brand.equals("Null")) {
                edBrand.setText(products!!.brand)
            }
        }else{
            tvTitle.text="Them san pham"
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
            var category=data!!.getParcelableExtra<Category>("category")
            id_category=category.id
            edCategory.setText(category.name)
        }


        if ( requestCode==102 && resultCode == Activity.RESULT_OK) {
            var category=data!!.getParcelableExtra<Category>("category")
            edBrand.setText(category.name)
        }

        if ( requestCode==103 && resultCode == Activity.RESULT_OK) {
            var supplier=data!!.getParcelableExtra<Supplier>("supplier")
            edSupplier.setText(supplier.name)
        }
    }


    override fun setSuccess(success: String) {
        Toast.makeText(this, success, Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        if( AdapterBrand.selected_position>0){
            AdapterBrand.selected_position=-1
        }
        if( AdapterCategory.selected_position>0) {
            AdapterCategory.selected_position = -1
        }
    }

}
