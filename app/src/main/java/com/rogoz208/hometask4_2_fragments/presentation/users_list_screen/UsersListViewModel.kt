package com.rogoz208.hometask4_2_fragments.presentation.users_list_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rogoz208.hometask4_2_fragments.domain.entities.UserEntity
import com.rogoz208.hometask4_2_fragments.domain.repos.UsersRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UsersListViewModel @Inject constructor(
    private val usersRepo: UsersRepo
) : ViewModel() {

    private val _usersListLiveData = MutableLiveData<List<UserEntity>>()
    val usersListLiveData: LiveData<List<UserEntity>> = _usersListLiveData

    init {
        updateUserList()
    }

    fun onUserUpdated() {
        updateUserList()
    }

    private fun updateUserList() {
        usersRepo.getUsers { users ->
            _usersListLiveData.postValue(users)
        }
    }
}