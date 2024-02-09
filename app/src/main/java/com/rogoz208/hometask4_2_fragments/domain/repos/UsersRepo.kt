package com.rogoz208.hometask4_2_fragments.domain.repos

import com.rogoz208.hometask4_2_fragments.domain.entities.UserEntity

interface UsersRepo {

    fun getUsers(callback: (List<UserEntity>) -> Unit)

    fun createUser(user: UserEntity)

    fun deleteUser(uId: String)

    fun updateUser(uId: String, user: UserEntity, position: Int)
}