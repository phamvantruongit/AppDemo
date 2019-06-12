package team.android.pv.qlshop.view.adapter

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.RecyclerView
import android.view.*
import kotlinx.android.synthetic.main.item_products.view.*
import kotlinx.android.synthetic.main.show_dialog.*
import kotlinx.android.synthetic.main.show_dialog_delete_product.*
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.Product
import team.android.pv.qlshop.view.activity.AddProductActivity
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class AdapterProduct(var productList: ArrayList<Product>, var iOnClick :IOnClick ): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var  context:Context?=null
    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): RecyclerView.ViewHolder {
        context=viewGroup.context
        val view= LayoutInflater.from(viewGroup.context).inflate(R.layout.item_products,viewGroup,false)
        return ViewHolderProduct(view)
    }

    override fun getItemCount() = productList.size

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        viewHolder.itemView.tvNameProduct.text=productList.get(position).name
        viewHolder.itemView.tvAmount.text="So luong : ${productList.get(position).amount}"
        if(!productList.get(position).category.equals("Null") ) {
            viewHolder.itemView.tvCategory.text ="Danh muc : ${productList.get(position).category}"
        }else{
            viewHolder.itemView.tvCategory.text =""
        }

        val formatter = NumberFormat.getInstance(Locale.US) as DecimalFormat
        formatter.applyPattern("#,###,###,###")
        val total = formatter.format(productList.get(position).price_in).toString()

        viewHolder.itemView.tvPrice_In.text="Gia ban : " + total
        viewHolder.itemView.tvIDProduct.text="Ma SP : ${productList.get(position).id}"



        viewHolder.itemView.tvEdProduct.setOnClickListener {
            var intent = Intent(context, AddProductActivity::class.java)
            intent.putExtra("product", productList.get(position))
            context!!.startActivity(intent)
        }


        viewHolder.itemView.tvDeProduct.setOnClickListener {
            iOnClick.iOnCLickItem(productList.get(position))
            productList.removeAt(position)
            notifyDataSetChanged()
        }


    }

    class ViewHolderProduct(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface IOnClick{
        fun iOnCLickItem(product: Product)
    }

}