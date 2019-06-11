package team.android.pv.qlshop.view.activity

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.TextInputEditText
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.RadioButton
import android.widget.RadioGroup
import com.google.android.gms.vision.barcode.Barcode
import com.notbytes.barcode_reader.BarcodeReaderActivity
import kotlinx.android.synthetic.main.activity_sell_product.*
import kotlinx.android.synthetic.main.toolbar.*
import team.android.pv.qlshop.MyApplication
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.Product
import team.android.pv.qlshop.model.Supplier
import team.android.pv.qlshop.presenter.searchproduct.SearchProductInteractor
import team.android.pv.qlshop.presenter.searchproduct.SearchProductPresenter
import team.android.pv.qlshop.view.DividerItemDecoration
import team.android.pv.qlshop.view.adapter.AdapterProductLocal
import team.android.pv.qlshop.view.views.ViewSearchBarcode
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*


class SellProductActivity : BaseActivity(), ViewSearchBarcode, AdapterProductLocal.IOnClick {


    val BARCODE_READER_ACTIVITY_REQUEST: Int = 101
    private lateinit var searchProductPresenter: SearchProductPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        searchProductPresenter = SearchProductPresenter(SearchProductInteractor(), this)


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

            showDialogSale()



        }

    }

    private fun showDialogSale() {
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

        var radLeft = dialog.findViewById<RadioButton>(team.android.pv.qlshop.R.id.radioLeft)
        var radRight = dialog.findViewById<RadioButton>(team.android.pv.qlshop.R.id.radioRight)
        var radio = dialog.findViewById<RadioGroup>(team.android.pv.qlshop.R.id.radio)
        var edSale = dialog.findViewById<TextInputEditText>(R.id.edSale)
        var checked = false


        radio.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                if (checkedId == R.id.radioLeft) {
                    checked = true
                    radLeft.isChecked = true
                    radRight.isChecked = false
                    radLeft.setTextColor(resources.getColor(R.color.white))
                    radRight.setTextColor(resources.getColor(R.color.black))
                } else {
                    checked = false
                    radRight.isChecked = true
                    radLeft.isChecked = false
                    radRight.setTextColor(resources.getColor(R.color.white))
                    radLeft.setTextColor(resources.getColor(R.color.black))
                }
            }

        })


        dialog.imgRight.setOnClickListener {


            dialog.dismiss()
            val sale = edSale.text.toString().toLong()
            if (checked) {
                updateSale(sale, checked)
            } else {
                updateSale(sale, checked)
            }


        }
    }

    fun updateSale(sale: Long, check: Boolean) {
        if (check) {
            MyApplication.realmMyApplication.executeTransactionAsync {

                var product: team.android.pv.qlshop.model.data.Product =
                    it.where(team.android.pv.qlshop.model.data.Product::class.java).findFirst()!!
                product!!.sale = sale

            }
        } else {
            var data = tvT.text.toString().replace(",", "").toLong()
            var sum = data - sale
            val formatter = NumberFormat.getInstance(Locale.US) as DecimalFormat
            formatter.applyPattern("#,###,###,###")
            tvT.setText(formatter.format(sum))

        }
    }

    override fun onResume() {
        super.onResume()
        getDataLocal()

    }

    private fun getDataLocal() {
        var list =
            MyApplication.realmMyApplication.where(team.android.pv.qlshop.model.data.Product::class.java).findAll()
        if (list.size > 0) {
            rl_sell_product.visibility = View.VISIBLE
            getSum()
        }
        rv_product_local!!.layoutManager = LinearLayoutManager(this)
        rv_product_local!!.addItemDecoration(DividerItemDecoration(resources.getDrawable(R.drawable.divider)))
        rv_product_local!!.adapter = AdapterProductLocal(this, list, this)
        rv_product_local!!.adapter!!.notifyDataSetChanged()
    }

    override fun sale(id: Int) {
              showDialogSale()
    }

    override fun delete(id: Int) {
        MyApplication.realmMyApplication.removeChangeListener{
            MyApplication.realmMyApplication.where(team.android.pv.qlshop.model.data.Product::class.java).equalTo(
                "uid",
                id
            ).findFirst()!!.deleteFromRealm()
        }

    }

    override fun iOnClick(amount: Int, id: Int) {
        MyApplication.realmMyApplication.executeTransactionAsync {

            var product = it.where(team.android.pv.qlshop.model.data.Product::class.java).equalTo("uid", id).findFirst()
            product!!.amount = amount

        }
    }

    override fun sum() {
        var handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                getSum()
            }

        }, 500)

    }

    private fun getSum() {
        var list =
            MyApplication.realmMyApplication.where(team.android.pv.qlshop.model.data.Product::class.java).findAll()
        var sum = 0L
        var sumSale=0L
        for (i in 0..list.size - 1) {
            sum += list.get(i)!!.amount * list.get(i)!!.price_out
            sumSale +=list.get(i)!!.sale
            Log.d("GGGG",sumSale.toString() + list.get(i)!!.sale.toString())

        }

        val formatter = NumberFormat.getInstance(Locale.US) as DecimalFormat
        formatter.applyPattern("#,###,###,###")
        if(sumSale>0){
            var temp=sum*sumSale/100
            tvT.setText(formatter.format(temp))
        }else {
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


