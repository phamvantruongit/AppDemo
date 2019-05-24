package team.android.pv.qlshop.view.fragment
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.Product
import team.android.pv.qlshop.presenter.product.GetProductInteractor
import team.android.pv.qlshop.presenter.product.GetProductPresenter
import team.android.pv.qlshop.view.activity.ProductActivity
import team.android.pv.qlshop.view.views.ViewProducts

class FragmentCategory : Fragment(), ViewProducts {

    private lateinit var getProductPresenter: GetProductPresenter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_category, container, false)
        getProductPresenter= GetProductPresenter(this, GetProductInteractor())
        getProductPresenter.getListProducts(1)
        return view
    }


    override fun getListProducts(productList: ArrayList<Product>) {

    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun showMessage(message: String) {

    }


}
