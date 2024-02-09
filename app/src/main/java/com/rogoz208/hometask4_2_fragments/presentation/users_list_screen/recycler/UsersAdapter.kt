package com.rogoz208.hometask4_2_fragments.presentation.users_list_screen.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rogoz208.hometask4_2_fragments.domain.entities.UserEntity

class UsersAdapter : RecyclerView.Adapter<UserViewHolder>() {

    var data: List<UserEntity> = ArrayList()
        get() = ArrayList(field)

    private var clickListener: UserItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(parent, clickListener!!)

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) =
        holder.bind(getItem(position))

    override fun getItemCount(): Int = data.size

    fun getItem(position: Int): UserEntity = data[position]

    fun setOnItemClickListener(listener: UserItemClickListener) {
        clickListener = listener
    }
}