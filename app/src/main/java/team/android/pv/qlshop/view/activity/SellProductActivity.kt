package team.android.pv.qlshop.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.vision.barcode.Barcode
import com.notbytes.barcode_reader.BarcodeReaderActivity
import kotlinx.android.synthetic.main.activity_sell_product.*
import kotlinx.android.synthetic.main.toolbar.*
import team.android.pv.qlshop.MyApplication
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.Product
import team.android.pv.qlshop.presenter.searchproduct.SearchProductInteractor
import team.android.pv.qlshop.presenter.searchproduct.SearchProductPresenter
import team.android.pv.qlshop.view.DividerItemDecoration
import team.android.pv.qlshop.view.adapter.AdapterProductLocal
import team.android.pv.qlshop.view.views.ViewSearchBarcode

class SellProductActivity :BaseActivity(), ViewSearchBarcode, AdapterProductLocal.IOnClick {



    val BARCODE_READER_ACTIVITY_REQUEST :Int=100
    private lateinit var searchProductPresenter: SearchProductPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        searchProductPresenter = SearchProductPresenter( SearchProductInteractor() ,this)


        tvTitle.text=this.resources.getText(R.string.title_sell)
        imgBarcode.visibility=View.VISIBLE
        imgBarcode.setOnClickListener{
            launchBarCodeActivity()
        }

        imgRight.visibility=View.VISIBLE
        imgRight.setImageDrawable(resources.getDrawable(R.drawable.ic_search))
        imgRight.setOnClickListener {
            var intent=Intent(this,SearchSellProductActivity::class.java)
            startActivityForResult(intent,200)
        }

    }

    override fun onResume() {
        super.onResume()
        getDataLocal()
    }

    private fun getDataLocal() {
        var list = MyApplication.realmMyApplication.where(team.android.pv.qlshop.model.data.Product::class.java).findAll()
        rv_product_local.layoutManager = LinearLayoutManager(this)
        rv_product_local.adapter=AdapterProductLocal(this,list,this)
        rv_product_local.addItemDecoration(DividerItemDecoration(resources.getDrawable(R.drawable.divider)))
    }

    override fun iOnClick(amount: Int, id: Int) {
        if(amount==0) {
            Toast.makeText(this, "So luong >0", Toast.LENGTH_SHORT).show()
            return
        }
        MyApplication.realmMyApplication.executeTransactionAsync {

            var product=it.where(team.android.pv.qlshop.model.data.Product::class.java).equalTo("uid",id).findFirst()
            product!!.amount=amount
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

    private fun launchBarCodeActivity(){
        var intent=BarcodeReaderActivity.getLaunchIntent(this,true,false)
        startActivityForResult(intent,BARCODE_READER_ACTIVITY_REQUEST)
    }

      override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == BARCODE_READER_ACTIVITY_REQUEST && data != null) {
            val barcode = data!!.getParcelableExtra<Barcode>(BarcodeReaderActivity.KEY_CAPTURED_BARCODE)
            searchProductPresenter.searchProductBarocde(userSave!!.id_shop ,barcode = barcode.rawValue, name = "0")
        }
    }
}