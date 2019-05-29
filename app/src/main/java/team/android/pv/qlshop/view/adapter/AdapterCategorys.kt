package team.android.pv.qlshop.view.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_header.view.*
import kotlinx.android.synthetic.main.layout_item_categorys_name.view.*
import team.android.pv.qlshop.model.Category


class AdapterCategorys(var listCategory: List<Category>, var iOnClickItem: IOnClickItem) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    companion object {
        var selected_position = -1

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, po: Int): RecyclerView.ViewHolder {
        var view: View? = null

        view = LayoutInflater.from(viewGroup.context)
            .inflate(team.android.pv.qlshop.R.layout.layout_item_categorys_name, viewGroup, false)
        return ViewHolderItem(view)


    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        if (selected_position == position) {
            viewHolder.itemView.ivSelects.visibility = View.VISIBLE
        } else {
            viewHolder.itemView.ivSelects.visibility = View.GONE
        }
        viewHolder.itemView.tvNameCategory.setText(listCategory.get(position).name)
        viewHolder.itemView.setOnClickListener {
            if (selected_position == position) {
                selected_position = -1
                notifyDataSetChanged()
                return@setOnClickListener
            }
            selected_position = position
            notifyDataSetChanged()


            iOnClickItem.onClickItem(listCategory.get(position).id, selected_position)


        }

    }


    override fun getItemCount() = listCategory.size


    class ViewHolderItem(itemView: View) : RecyclerView.ViewHolder(itemView)
    class ViewHolderHeader(itemView: View) : RecyclerView.ViewHolder(itemView)
    interface IOnClickItem {
        fun onClickItem(id_category: Int, selected_position: Int)
    }
}