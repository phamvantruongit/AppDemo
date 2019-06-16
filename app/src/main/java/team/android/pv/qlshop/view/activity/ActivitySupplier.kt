package team.android.pv.qlshop.view.activity

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_supplier.*
import kotlinx.android.synthetic.main.show_dialog_nhacc.*
import kotlinx.android.synthetic.main.toolbar.*
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.Supplier
import team.android.pv.qlshop.presenter.supplier.SupplierInteractor
import team.android.pv.qlshop.presenter.supplier.SupplierPresenter
import team.android.pv.qlshop.view.DividerItemDecoration
import team.android.pv.qlshop.view.adapter.AdapterSupplier
import team.android.pv.qlshop.view.view.ViewParents
import team.android.pv.qlshop.view.view.ViewSupplier

class ActivitySupplier : BaseActivitys(), ViewParents, ViewSupplier, AdapterSupplier.IOnCLick {



    private lateinit var supplierPresenter: SupplierPresenter
    private var checkCustomer: Boolean? = false
    private var dialog: Dialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_supplier)

        dialog = Dialog(this)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.setContentView(R.layout.show_dialog_nhacc)

        checkCustomer = intent.getBooleanExtra("checkCustomer", false)
        supplierPresenter = SupplierPresenter(this, SupplierInteractor())
        supplierPresenter.getListSupplier(userEntity!!.id_shop, checkCustomer!!)

        imgRight.setImageDrawable(resources.getDrawable(R.drawable.ic_add))

        imgRight.setOnClickListener {
            showDialogApp(null)
        }


    }

    override fun onClick(supplier: Supplier) {
         var intent=Intent()
         intent.putExtra("supplier",supplier)
         setResult(Activity.RESULT_OK,intent)
         finish()
    }

    private fun showDialogApp(supplier: Supplier?) {

        dialog!!.show()
        if (dialog!!.window != null) {
            dialog!!.window!!.setGravity(Gravity.CENTER)
            dialog!!.window!!.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
            )
            dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        if (supplier != null) {
            dialog!!.edName.setText(supplier.name)
            dialog!!.edAddress.setText(supplier.address)
            dialog!!.edEmail.setText(supplier.email)
            dialog!!.edPhone.setText(supplier.phone.toString())
            dialog!!.edDesciption.setText(supplier.description)
            dialog!!.btnAddSupplier.text = getString(R.string.edit_info)
        }

        if (checkCustomer as Boolean) {
           // dialog!!.tvNameCustomer.text = "Ten KH"
        }

        dialog!!.btnAddSupplier.setOnClickListener {
            var name = dialog!!.edName.text.toString()
            var address = dialog!!.edAddress.text.toString()
            var email = dialog!!.edEmail.text.toString()
            var phone = dialog!!.edPhone.text.toString()
            var description = dialog!!.edDesciption.text.toString()

            if (name == "") {
                dialog!!.edName.error = getString(R.string.enter_info)
                return@setOnClickListener
            }

            if (address == "") {
                dialog!!.edAddress.error = getString(R.string.enter_info)
                return@setOnClickListener
            }

            if (phone == "") {
                dialog!!.edPhone.error = getString(R.string.enter_info)
                return@setOnClickListener
            }
            var suppliers = Supplier()
            suppliers.name = name
            suppliers.address = address
            suppliers.email = email
            suppliers.phone = phone.toInt()
            suppliers.description = description
            suppliers.id_shop = userEntity!!.id_shop

            if (supplier != null) {
                suppliers.id = supplier.id
                supplierPresenter.editSupplier(suppliers, checkCustomer!!)
            } else
                supplierPresenter.addSupplier(suppliers, checkCustomer!!)


        }


        dialog!!.btnCancel.setOnClickListener {

            dialog!!.cancel()

        }

    }

    override fun getListSupplier(list: ArrayList<Supplier>) {
        rv_supplier.layoutManager = LinearLayoutManager(this)
        rv_supplier.addItemDecoration(DividerItemDecoration(resources.getDrawable(R.drawable.divider)))
        rv_supplier.adapter = AdapterSupplier(list, this)
    }

    override fun delete(id: Int) {
        checkCustomer?.let { supplierPresenter.deleteInfo(userEntity!!.id_shop, id, it) }
    }

    override fun edit(supplier: Supplier) {
        showDialogApp(supplier)
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        reset()
    }

    fun reset() {
        dialog!!.edName.setText("")
        dialog!!.edAddress.setText("")
        dialog!!.edEmail.setText("")
        dialog!!.edPhone.setText("")
        dialog!!.edDesciption.setText("")
        dialog!!.edName.requestFocus()

    }


}
