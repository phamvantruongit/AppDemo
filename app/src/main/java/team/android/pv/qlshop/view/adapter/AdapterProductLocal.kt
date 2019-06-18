package team.android.pv.qlshop.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_prouct_local.view.*
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.data.database.ProductEntity
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class AdapterProductLocal(var context: Context, var iOnClick: IOnClick) :
    RecyclerView.Adapter<AdapterProductLocal.ViewHolder>() {
    var list: List<ProductEntity> = ArrayList<ProductEntity> ()
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): AdapterProductLocal.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_prouct_local, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list!!.size

    fun totalSum(sum: Double?):String{
        val formatter = NumberFormat.getInstance(Locale.US) as DecimalFormat
        formatter.applyPattern("#,###,###,###")

        return formatter.format(sum)
    }

    fun setData(list: List<ProductEntity>){
         this.list=list
    }

    override fun onBindViewHolder(viewHolder: AdapterProductLocal.ViewHolder, position: Int) {
        viewHolder.itemView.tvNameProduct.text = list!!.get(position).name
        val formatter = NumberFormat.getInstance(Locale.US) as DecimalFormat
        formatter.applyPattern("#,###,###,###")
        var price= formatter.format(list!!.get(position).price_out)
        viewHolder.itemView.tvPrice.text = "Giá: " +  price
        var amount:Int = list!!.get(position).amount
        viewHolder.itemView.tvAmount.setText(amount.toString())
        viewHolder.itemView.tvsize.text="Size :" + list!!.get(position).size
        var sum =list!!.get(position).price_out * amount

        if(list!!.get(position).sale>0){
            var sale=list!!.get(position).sale.toDouble()/100.0
            var temp:Double = (1.0-sale) * sum.toDouble()
            viewHolder.itemView.tvSum.text = totalSum(temp)
        }else{
            viewHolder.itemView.tvSum.text = totalSum(sum.toDouble())
        }








        viewHolder.itemView.btnTang.setOnClickListener {

            var amount = viewHolder.itemView.tvAmount.text.toString().toInt()
            var amounts = list!!.get(position).amounts
            amount++
            if (amount > amounts) {
                viewHolder.itemView.tvAmount.setText(amounts.toString())
                iOnClick.iOnClick(amounts, list!!.get(position).uid)
            } else {
                viewHolder.itemView.tvAmount.setText(amount.toString())
                iOnClick.iOnClick(amount, list!!.get(position).uid)

            }
        }

        viewHolder.itemView.btnGiam.setOnClickListener {
            var amount = viewHolder.itemView.tvAmount.text.toString().toInt()
            amount--
            if (amount == 0) {
                viewHolder.itemView.tvAmount.setText("1")
                iOnClick.iOnClick(1,list!!.get(position).uid)
            } else {
                viewHolder.itemView.tvAmount.setText(amount.toString())
                iOnClick.iOnClick(amount, list!!.get(position).uid)

            }


        }

        viewHolder.itemView.tvSaleProduct.setOnClickListener {
            iOnClick.sale(list!!.get(position).uid)
        }

        viewHolder.itemView.tvDeleteProduct.setOnClickListener {
            iOnClick.delete(list!!.get(position).uid)
        }

        viewHolder.itemView.tvSaleProduct.setOnClickListener {
            iOnClick.sale(list.get(position).uid)
        }

        viewHolder.itemView.tvDeleteProduct.setOnClickListener {
            iOnClick.delete(list.get(position).uid)
        }
    }


    interface IOnClick {
        fun iOnClick(amount: Int, id: Int)
        fun sum()
        fun sale(id: Int)
        fun delete(id: Int)


    }


}