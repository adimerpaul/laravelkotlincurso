package com.cursos.moviles.adimer.laravelcrud

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cursos.moviles.adimer.laravelcrud.databinding.ItemUserBinding

class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemUserBinding.bind(view)
    fun bind(user: User) {
        binding.tvName.text = user.name
        binding.tvEmail.text = user.email
    }
}