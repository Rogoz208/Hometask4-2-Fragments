package com.rogoz208.hometask4_2_fragments.presentation.users_list_screen.recycler

import com.rogoz208.hometask4_2_fragments.domain.entities.UserEntity

interface UserItemClickListener {

    fun onItemClick(item: UserEntity, position: Int)
}