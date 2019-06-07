package team.android.pv.qlshop.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_supplier.view.*
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.Supplier

class AdapterSupplier(var list: List<Supplier> , var iOnCLick :IOnCLick) : androidx.recyclerview.widget.RecyclerView.Adapter<AdapterSupplier.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, p: Int): ViewHolder {
        var view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_supplier, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(viewHolder: ViewHolder, p: Int) {
        viewHolder.itemView.tvName.text = list.get(p).name
        viewHolder.itemView.tvAddress.text = list.get(p).address
        viewHolder.itemView.tvPhone.text = list.get(p).phone.toString()
        viewHolder.itemView.tvEdit.setOnClickListener {
            iOnCLick.edit(list.get(p))
        }

        viewHolder.itemView.tvDelete.setOnClickListener {
           iOnCLick.delete(list.get(p).id)
        }
    }

    class ViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView)

    public interface IOnCLick{
        fun delete(id:Int)
        fun edit(supplier: Supplier)
    }
}