package com.rogoz208.hometask4_2_fragments.presentation.users_list_screen.recycler

import androidx.recyclerview.widget.DiffUtil
import com.rogoz208.hometask4_2_fragments.domain.entities.UserEntity

class UsersDiffCallback(
    private val oldList: List<UserEntity>,
    private val newList: List<UserEntity>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].uId == newList[newItemPosition].uId

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].avatarUrl == newList[newItemPosition].avatarUrl
                && oldList[oldItemPosition].firstName == newList[newItemPosition].firstName
                && oldList[oldItemPosition].lastName == newList[newItemPosition].lastName
                && oldList[oldItemPosition].phoneNumber == newList[newItemPosition].phoneNumber

}