package team.android.pv.qlshop.view.activity

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_search_sell_product.*
import kotlinx.android.synthetic.main.activity_supplier.*
import kotlinx.android.synthetic.main.show_dialog_nhacc.*
import kotlinx.android.synthetic.main.toolbar.*
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.Supplier
import team.android.pv.qlshop.presenter.supplier.SupplierInteractor
import team.android.pv.qlshop.presenter.supplier.SupplierPresenter
import team.android.pv.qlshop.view.DividerItemDecoration
import team.android.pv.qlshop.view.adapter.AdapterSupplier
import team.android.pv.qlshop.view.views.ViewParents
import team.android.pv.qlshop.view.views.ViewSupplier

class ActivitySupplier : BaseActivitys(), ViewParents, ViewSupplier {


    private lateinit var supplierPresenter: SupplierPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_supplier)

        supplierPresenter= SupplierPresenter(this, SupplierInteractor())
        supplierPresenter.getListSupplier(userSave!!.id_shop)
        imgRight.setImageDrawable(resources.getDrawable(R.drawable.ic_add))

        imgRight.setOnClickListener {

           var dialog = Dialog(this)
            dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog!!.setContentView(R.layout.show_dialog_nhacc)
            dialog!!.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
            dialog.imgRight.visibility=View.GONE

            dialog!!.show()
            if (dialog!!.window != null) {
                dialog!!.window!!.setLayout(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.MATCH_PARENT
                )
                dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }

            dialog.btnAddSupplier.setOnClickListener {
                var name=dialog.edName.text.toString()
                var address=dialog.edAddress.text.toString()
                var email=dialog.edEmail.text.toString()
                var phone=dialog.edPhone.text.toString()
                var description=dialog.edDesciption.text.toString()

                var supplier=Supplier()
                supplier.name=name
                supplier.address=address
                supplier.email=email
                supplier.phone=phone.toInt()
                supplier.description=description
                supplierPresenter.addSupplier(supplier)

            }


            dialog.btnCancel.setOnClickListener {


            }
        }
    }

    override fun getListSupplier(list: ArrayList<Supplier>) {
          rv_supplier.layoutManager= LinearLayoutManager(this)
          rv_supplier.addItemDecoration(DividerItemDecoration(resources.getDrawable(R.drawable.divider)))
          rv_supplier.adapter=AdapterSupplier(list)
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun showMessage(message: String) {

    }


}
