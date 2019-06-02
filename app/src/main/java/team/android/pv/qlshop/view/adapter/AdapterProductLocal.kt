package team.android.pv.qlshop.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_prouct_local.view.*
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.data.Product

class AdapterProductLocal(var context: Context, var list: List<Product> ,var iOnClick: IOnClick) :
    RecyclerView.Adapter<AdapterProductLocal.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): AdapterProductLocal.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_prouct_local, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size



    override fun onBindViewHolder(viewHolder: AdapterProductLocal.ViewHolder, position: Int) {
         viewHolder.itemView.tvNameProduct.text=list.get(position).name
         viewHolder.itemView.tvPrice.text=list.get(position).price_out.toString()
         viewHolder.itemView.edAmount.setText(list.get(position).amount.toString())
         viewHolder.itemView.edAmount.addTextChangedListener(object : TextWatcher{
             override fun afterTextChanged(s: Editable?) {

             }

             override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

             }

             override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                 var ed = viewHolder.itemView.edAmount.text.toString()

                 if (ed.length > 0) {

                     var amount = viewHolder.itemView.edAmount.text.toString().toInt()
                     var amounts = list.get(position).amounts


                     if (amount > amounts) {
                         return
                     }

                     if (amount <= 0) {
                         viewHolder.itemView.edAmount.setText("1")
                     }

                     if (amount > 0) {
                         iOnClick.iOnClick(viewHolder.itemView.edAmount.text.toString().toInt(), list.get(position).uid)
                     }
                 }
             }

         })
    }


    interface IOnClick{
        fun iOnClick(amount: Int,id:Int)
    }


}