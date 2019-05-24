package team.android.pv.qlshop.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_memmbers.view.*
import team.android.pv.qlshop.R
import team.android.pv.qlshop.model.User

class AdapterMemmbers(var listUser: List<User> ,var iOnClickItem: IOnClickItem) : RecyclerView.Adapter<AdapterMemmbers.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_memmbers,viewGroup,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
         viewHolder.bindData(listUser.get(position))
         viewHolder.itemView.tvDeleteUser.setOnClickListener {
               iOnClickItem.onClickDeleteUser(listUser.get(position))
          }
        viewHolder.itemView.tvEditUser.setOnClickListener {
            iOnClickItem.onClickEditUser(listUser.get(position))
         }

        viewHolder.itemView.tvAddUser.setOnClickListener {
             iOnClickItem.onClickAddUser()
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       fun bindData(user: User){
           itemView.tvNameUser.text=user.name
           itemView.tvEmailUser.text=user.email
       }
    }

    interface IOnClickItem{
        fun onClickDeleteUser(user: User)
        fun onClickEditUser(user: User)
        fun onClickAddUser()
    }
}