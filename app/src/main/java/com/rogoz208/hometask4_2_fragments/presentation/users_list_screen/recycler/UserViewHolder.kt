package com.rogoz208.hometask4_2_fragments.presentation.users_list_screen.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import coil.transform.CircleCropTransformation
import com.rogoz208.hometask4_2_fragments.R
import com.rogoz208.hometask4_2_fragments.databinding.ItemUserViewHolderBinding
import com.rogoz208.hometask4_2_fragments.domain.entities.UserEntity

class UserViewHolder(parent: ViewGroup, private val clickListener: UserItemClickListener) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user_view_holder, parent, false)
    ) {

    private val binding by viewBinding(ItemUserViewHolderBinding::bind)

    fun bind(user: UserEntity) {
        val userName = "${user.firstName} ${user.lastName}"

        with(binding){
            userAvatarImageView.load(user.avatarUrl) {
                transformations(CircleCropTransformation())
            }
            nameTextView.text = userName
            phoneNumberTextView.text = user.phoneNumber
        }

        itemView.setOnClickListener {
            clickListener.onItemClick(user, this.layoutPosition)
        }
    }
}