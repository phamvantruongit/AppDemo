package team.android.pv.qlshop.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_category.view.*
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.Category

class AdapterBrandMore(var listCategory: List<Category>, var iOnClickItem: IOnClickItem) :
    RecyclerView.Adapter<AdapterBrandMore.ViewHolder>() {

    override fun onCreateViewHolder(viewgroup: ViewGroup, position: Int): AdapterBrandMore.ViewHolder {
        val view = LayoutInflater.from(viewgroup.context).inflate(R.layout.item_category, viewgroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = listCategory.size


    override fun onBindViewHolder(viewHolder: AdapterBrandMore.ViewHolder, position: Int) {
        viewHolder.bindData(listCategory.get(position))

        viewHolder.itemView.ivSelect.visibility = View.GONE

        viewHolder.itemView.setOnClickListener {

            iOnClickItem.onClickEditCategory(listCategory.get(position))

        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(category: Category) {
            itemView.tvCategory.text = category.name

        }
    }

    interface IOnClickItem {
        fun onClickEditCategory(category: Category)
    }
}