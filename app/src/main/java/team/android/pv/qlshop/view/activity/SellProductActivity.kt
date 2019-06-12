package team.android.pv.qlshop.view.activity

import android.app.Activity
import android.app.Dialog
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.TextInputEditText
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.google.android.gms.vision.barcode.Barcode
import com.notbytes.barcode_reader.BarcodeReaderActivity
import kotlinx.android.synthetic.main.activity_sell_product.*
import kotlinx.android.synthetic.main.toolbar.*
import team.android.pv.qlshop.MyApplication
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.Product
import team.android.pv.qlshop.model.Supplier
import team.android.pv.qlshop.model.data.database.ProductEntity
import team.android.pv.qlshop.presenter.searchproduct.SearchProductInteractor
import team.android.pv.qlshop.presenter.searchproduct.SearchProductPresenter
import team.android.pv.qlshop.view.DividerItemDecoration
import team.android.pv.qlshop.view.adapter.AdapterProductLocal
import team.android.pv.qlshop.view.view.ViewSearchBarcode
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*


class SellProductActivity : BaseActivity(), ViewSearchBarcode, AdapterProductLocal.IOnClick {


    val BARCODE_READER_ACTIVITY_REQUEST: Int = 101
    private lateinit var searchProductPresenter: SearchProductPresenter
    private var rv_product_local:RecyclerView?=null
    private var adapterProductLocal:AdapterProductLocal ?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        searchProductPresenter = SearchProductPresenter(SearchProductInteractor(), this)
        rv_product_local=findViewById(R.id.rv_product_local)
        rv_product_local!!.layoutManager = LinearLayoutManager(this@SellProductActivity) as RecyclerView.LayoutManager?
        rv_product_local!!.addItemDecoration(DividerItemDecoration(resources.getDrawable(R.drawable.divider)))
        adapterProductLocal = AdapterProductLocal(this@SellProductActivity, this@SellProductActivity)


        tvTitle.text = this.resources.getText(R.string.title_sell)
        imgBarcode.visibility = View.VISIBLE
        imgBarcode.setOnClickListener {
            launchBarCodeActivity()
        }

        imgRight.visibility = View.VISIBLE
        imgRight.setImageDrawable(resources.getDrawable(R.drawable.ic_search))
        imgRight.setOnClickListener {
            var intent = Intent(this, SearchSellProductActivity::class.java)
            startActivityForResult(intent, 200)
        }

        tvCustomer.setOnClickListener {
            var intent = Intent(this, ActivitySupplier::class.java)
            startActivityForResult(intent, 100)
        }

        tvSale.setOnClickListener {

            showDialogSale(0)


        }

    }

    private fun showDialogSale(id: Int) {
        var dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_sale)
        dialog.show()
        if (dialog!!.window != null) {
            dialog!!.window!!.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
            )
            dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        var radLeft = dialog.findViewById<RadioButton>(R.id.radioLeft)
        var radRight = dialog.findViewById<RadioButton>(R.id.radioRight)
        var radio = dialog.findViewById<RadioGroup>(R.id.radio)
        var edSale = dialog.findViewById<TextInputEditText>(R.id.edSale)



        radio.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                if (checkedId == R.id.radioLeft) {
                    radLeft.isChecked = true
                    radRight.isChecked = false
                    radLeft.setTextColor(resources.getColor(R.color.white))
                    radRight.setTextColor(resources.getColor(R.color.black))
                } else {
                    radRight.isChecked = true
                    radLeft.isChecked = false
                    radRight.setTextColor(resources.getColor(R.color.white))
                    radLeft.setTextColor(resources.getColor(R.color.black))
                }
            }

        })


        dialog.imgRight.setOnClickListener {

            dialog.dismiss()
            if (edSale.text.toString().length > 0) {
                val sale = edSale.text.toString().toLong()
                if (radLeft.isChecked) {
                    updateSale(sale,id)
                }
                if (radRight.isChecked) {
                    updateMoney(sale,id)
                }
            }


        }
    }

    fun updateSale(sale: Long, id: Int) {

       if(id>0){
           MyApplication.appDatabase.productDao().updateSale(sale,id)
       }else {

           MyApplication.appDatabase.productDao().updateAllSale(sale)
       }



    }

    fun updateMoney(sale: Long, id: Int) {
        if(id>0){
            MyApplication.appDatabase.productDao().updateSaleMoney(sale = sale,id = id)
        }else {
           // MyApplication.appDatabase.productDao().updateSale(sale)
        }
    }

    override fun onResume() {
        super.onResume()
        getDataLocal()

    }

    private fun getDataLocal() {


        var liveData: LiveData<List<ProductEntity>> =   MyApplication.appDatabase.productDao().getListProduct()
        liveData.observe(this,object :Observer<List<ProductEntity>>{
            override fun onChanged(list: List<ProductEntity>?) {
                if (list != null) {
                    getSum(list)
                    rl_sell_product.visibility = View.VISIBLE
                    adapterProductLocal!!.setData(list)
                    rv_product_local!!.adapter=adapterProductLocal
                    adapterProductLocal!!.notifyDataSetChanged()
                }else{
                    rl_sell_product.visibility = View.GONE
                }

            }

        })


    }

    override fun sale(id: Int) {
        showDialogSale(id)
    }

    override fun delete(id: Int) {
        MyApplication.appDatabase.productDao().deleteProduct(id)


    }

    override fun iOnClick(amount: Int, id: Int) {
        MyApplication.appDatabase.productDao().updateAmount(amount,id)
    }

    override fun sum() {

    }

    private fun getSum(list: List<ProductEntity>) {

        var sum = 0L
        var sumSale = 0L
        for (i in 0..list.size - 1) {
            sum += list.get(i)!!.amount * list.get(i)!!.price_out
            sumSale += list.get(i)!!.sale

        }
        val formatter = NumberFormat.getInstance(Locale.US) as DecimalFormat
        formatter.applyPattern("#,###,###,###")
        if (sumSale > 0) {
            var total=sum*sumSale
            tvT.setText(formatter.format(total))
        } else {
            tvT.setText(formatter.format(sum))
        }

    }


    override fun getListSearchProduct(listProduct: List<Product>) {


    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun showMessage(message: String) {

    }


    override fun getContentView(): Int {
        return R.layout.activity_sell_product
    }

    override fun getNavigationMenuItemId(): Int {
        return R.id.navigation_sell
    }

    private fun launchBarCodeActivity() {
        var intent = BarcodeReaderActivity.getLaunchIntent(this, true, false)
        startActivityForResult(intent, BARCODE_READER_ACTIVITY_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == BARCODE_READER_ACTIVITY_REQUEST && data != null) {
            val barcode = data!!.getParcelableExtra<Barcode>(BarcodeReaderActivity.KEY_CAPTURED_BARCODE)
            searchProductPresenter.searchProductBarocde(userSave!!.id_shop, barcode = barcode.rawValue, name = "0")
        }

        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            var supplier = data!!.getParcelableExtra<Supplier>("supplier")
            tvCustomer.setText("Tên KH: " + supplier.name)
        }

    }
}


