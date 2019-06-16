package team.android.pv.qlshop.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_category.view.*
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.Category

class AdapterSize(var listCategory: List<Category>, var iOnClickItem: IOnClickItem) :RecyclerView.Adapter<AdapterSize.ViewHolder>() {
    companion object {
        var selected_position = -1

    }

    override fun onCreateViewHolder(viewgroup: ViewGroup, position: Int): AdapterSize.ViewHolder {
        val view=LayoutInflater.from(viewgroup.context).inflate(R.layout.item_category,viewgroup,false)
        return ViewHolder(view)
    }

    override fun getItemCount() = listCategory.size

    override fun onBindViewHolder(viewHolder: AdapterSize.ViewHolder, position: Int) {
        viewHolder.bindData(listCategory.get(position))
        var check_visible:Boolean


        if(selected_position==position){
            viewHolder.itemView.ivSelect.visibility=View.VISIBLE
        }else{
            viewHolder.itemView.ivSelect.visibility=View.GONE
        }

        viewHolder.itemView.bottom_wrapper.visibility=View.GONE


        viewHolder.itemView.setOnClickListener {

           if(selected_position==position){
                check_visible=false
                selected_position=-1
                notifyDataSetChanged()
                return@setOnClickListener
            }
            check_visible=true
            selected_position=position
            notifyDataSetChanged()
            iOnClickItem.onClickItem(listCategory.get(position)  ,check_visible)

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
       fun onClickItem(category: Category, check_visible: Boolean)
       fun onClickEditCategory(category: Category)
    }
}