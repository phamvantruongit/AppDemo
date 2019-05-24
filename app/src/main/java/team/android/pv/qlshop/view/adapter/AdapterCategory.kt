package team.android.pv.qlshop.view.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_category.view.*
import team.android.pv.qlshop.R
import team.android.pv.qlshop.api.ApiClient
import team.android.pv.qlshop.api.ApiInterface
import team.android.pv.qlshop.model.Category

class AdapterCategory(var listCategory: List<Category>,var pushMore:Boolean, var iOnClickItem: IOnClickItem) :RecyclerView.Adapter<AdapterCategory.ViewHolder>() {
    companion object {
        var selected_position = -1

    }

    override fun onCreateViewHolder(viewgroup: ViewGroup, position: Int): AdapterCategory.ViewHolder {
        val view=LayoutInflater.from(viewgroup.context).inflate(R.layout.item_category,viewgroup,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return listCategory.size
    }

    override fun onBindViewHolder(viewHolder: AdapterCategory.ViewHolder, position: Int) {
        viewHolder.bindData(listCategory.get(position))



        if(selected_position==position && !pushMore){
            viewHolder.itemView.ivSelect.visibility=View.VISIBLE
        }else{
            viewHolder.itemView.ivSelect.visibility=View.GONE
        }
        viewHolder.itemView.setOnClickListener {

//            if(selected_position==position){
//                selected_position=-1
//                notifyDataSetChanged()
//                return@setOnClickListener
//            }
//            selected_position=position
//            notifyDataSetChanged()
//            iOnClickItem.onClickItem(listCategory.get(position))
        }




        viewHolder.itemView.tvEditProduct.setOnClickListener {

            iOnClickItem.onClickEditCategory(listCategory.get(position))

        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
     fun bindData(category: Category){
          itemView.tvCategory.text=category.name

     }
    }

    interface IOnClickItem{
       fun onClickItem(category: Category)
       fun onClickEditCategory(category: Category)
    }
}