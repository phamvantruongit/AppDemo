package team.android.pv.qlshop.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.*
import kotlinx.android.synthetic.main.item_product_sell.view.*
import kotlinx.android.synthetic.main.item_products.view.tvAmount
import kotlinx.android.synthetic.main.item_products.view.tvCategory
import kotlinx.android.synthetic.main.item_products.view.tvIDProduct
import kotlinx.android.synthetic.main.item_products.view.tvNameProduct
import kotlinx.android.synthetic.main.item_products.view.tvPrice_In
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.Product
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class AdapterSellProduct(var context: Context, var productList: ArrayList<Product>, var iOnClick: IOnClick) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_product_sell, viewGroup, false)
        return ViewHolderProduct(view)
    }

    override fun getItemCount() = productList.size

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun totalSum(sum:Long):String{
        val formatter = NumberFormat.getInstance(Locale.US) as DecimalFormat
        formatter.applyPattern("#,###,###,###")
        val total = formatter.format(sum)
        return total.toString()
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        viewHolder.itemView.tvNameProduct.text = productList.get(position).name
        viewHolder.itemView.tvAmount.text = "So luong : ${productList.get(position).amount}"
        if (!productList.get(position).category.equals("Null")) {
            viewHolder.itemView.tvCategory.text = "Danh muc : ${productList.get(position).category}"
        } else {
            viewHolder.itemView.tvCategory.text = ""
        }





        viewHolder.itemView.tvPrice_In.text = "Gia ban : " + totalSum(productList.get(position).price_in)
        viewHolder.itemView.tvIDProduct.text = "Ma SP : ${productList.get(position).id}"

        var product = productList.get(position)

        viewHolder.itemView.setOnClickListener {
            product.count=1
            viewHolder.itemView.cb_selected.text ="1"
            iOnClick.iOnCLickItem(product)
        }


    }

    class ViewHolderProduct(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface IOnClick {
        fun iOnCLickItem(product: Product)
    }

}