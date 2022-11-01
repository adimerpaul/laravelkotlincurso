package com.cursos.moviles.adimer.laravelcrud

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(private val users: List<User>): RecyclerView.Adapter<UserViewHolder>() {
    var onItemClick: ((User) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return UserViewHolder(layoutInflater.inflate(R.layout.item_user,parent,false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item:User=users[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }
}