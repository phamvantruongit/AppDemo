package team.android.pv.qlshop.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_list_more.view.*
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.ListMore

class AdapterListMore(var listMore: List<ListMore>  , var clickItem:IOnClick ) :RecyclerView.Adapter<AdapterListMore.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
       val view= LayoutInflater.from(viewGroup.context).inflate(R.layout.item_list_more,viewGroup,false)
       return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listMore.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bindData(listMore.get(position))
        viewHolder.itemView.setOnClickListener{
            clickItem.onClickItem(position)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
           fun bindData(listMore: ListMore){
               itemView.tvTitle.text=listMore.title
               itemView.iv_left.setImageResource(listMore.icon_left)
               itemView.iv_right.setImageResource(listMore.icon_right)
          }
    }

    interface IOnClick{
        fun onClickItem(position:Int)
    }
}