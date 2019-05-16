package team.android.pv.qlshop.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_category.view.*
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.Category

class AdapterCategory(var listCategory: List<Category>,var iOnClickItem: IOnClickItem) :RecyclerView.Adapter<AdapterCategory.ViewHolder>() {

    override fun onCreateViewHolder(viewgroup: ViewGroup, position: Int): AdapterCategory.ViewHolder {
        val view=LayoutInflater.from(viewgroup.context).inflate(R.layout.item_category,viewgroup,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return listCategory.size
    }

    override fun onBindViewHolder(viewHolder: AdapterCategory.ViewHolder, position: Int) {
       viewHolder.bindData(listCategory.get(position))
       viewHolder.itemView.setOnClickListener({
           iOnClickItem.onClickItem(listCategory.get(position))
       })
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
     fun bindData(category: Category){
          itemView.tvCategory.text=category.name

     }
    }

    interface IOnClickItem{
       fun onClickItem(category: Category)
    }
}