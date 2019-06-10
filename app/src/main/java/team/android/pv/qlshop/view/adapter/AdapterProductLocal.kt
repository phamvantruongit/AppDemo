package team.android.pv.qlshop.view.adapter

import android.content.Context
import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_sell_product.*
import kotlinx.android.synthetic.main.item_prouct_local.view.*
import team.android.pv.qlshop.MyApplication
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.data.Product
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class AdapterProductLocal(var context: Context, var list: List<Product>, var iOnClick: IOnClick) :
    RecyclerView.Adapter<AdapterProductLocal.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): AdapterProductLocal.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_prouct_local, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    fun totalSum(sum:Long):String{
        val formatter = NumberFormat.getInstance(Locale.US) as DecimalFormat
        formatter.applyPattern("#,###,###,###")
        val total = formatter.format(sum)
        return total.toString()
    }

    override fun onBindViewHolder(viewHolder: AdapterProductLocal.ViewHolder, position: Int) {
        viewHolder.itemView.tvNameProduct.text = list.get(position).name
        viewHolder.itemView.tvPrice.text = "Giá: " + list.get(position).price_out.toString()
        viewHolder.itemView.tvAmount.setText(list.get(position).amount.toString())
        var sum = list.get(position).price_out * list.get(position).amount

        viewHolder.itemView.tvSum.text = totalSum(sum)


        viewHolder.itemView.btnTang.setOnClickListener {

            var amount = viewHolder.itemView.tvAmount.text.toString().toInt()
            var amounts = list.get(position).amounts
            amount++
            if (amount > amounts) {
                viewHolder.itemView.tvAmount.setText(amounts.toString())
                iOnClick.iOnClick(amounts, list.get(position).uid)
                iOnClick.sum()
                var sum = list.get(position).price_out * amounts
                viewHolder.itemView.tvSum.text = totalSum(sum)
            } else {
                viewHolder.itemView.tvAmount.setText(amount.toString())
                iOnClick.iOnClick(amount, list.get(position).uid)
                iOnClick.sum()
                var sum = list.get(position).price_out * amount
                viewHolder.itemView.tvSum.text = totalSum(sum)



            }
        }

        viewHolder.itemView.btnGiam.setOnClickListener {
            var amount = viewHolder.itemView.tvAmount.text.toString().toInt()
            amount--
            if (amount == 0) {
                viewHolder.itemView.tvAmount.setText("1")
                iOnClick.iOnClick(1, list.get(position).uid)
                Thread.sleep(1000)
                iOnClick.sum( )
                var sum = list.get(position).price_out
                viewHolder.itemView.tvSum.text = totalSum(sum)
            } else {
                viewHolder.itemView.tvAmount.setText(amount.toString())
                iOnClick.iOnClick(amount, list.get(position).uid)
                iOnClick.sum()
                var sum = list.get(position).price_out * amount
                viewHolder.itemView.tvSum.text = totalSum(sum)
            }



        }
    }


    interface IOnClick {
        fun iOnClick(amount: Int, id: Int)
        fun sum()


    }


}