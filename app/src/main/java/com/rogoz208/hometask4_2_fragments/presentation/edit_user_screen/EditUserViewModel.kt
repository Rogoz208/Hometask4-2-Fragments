package com.rogoz208.hometask4_2_fragments.presentation.edit_user_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rogoz208.hometask4_2_fragments.domain.entities.UserEntity
import com.rogoz208.hometask4_2_fragments.domain.repos.UsersRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditUserViewModel @Inject constructor(
    private val usersRepo: UsersRepo
) : ViewModel() {

    private val _userSavedLiveData = MutableLiveData(false)
    val userSavedLiveData: LiveData<Boolean> = _userSavedLiveData
    private val _avatarUrlLiveData = MutableLiveData<String>()
    val avatarUrlLiveData: LiveData<String> = _avatarUrlLiveData

    fun onUserSaved(uId: String, user: UserEntity, position: Int) {
        usersRepo.updateUser(uId, user, position)
        _userSavedLiveData.postValue(true)
    }

    fun onAvatarUrlChange(avatarUrl: String) {
        _avatarUrlLiveData.postValue(avatarUrl)
    }
}