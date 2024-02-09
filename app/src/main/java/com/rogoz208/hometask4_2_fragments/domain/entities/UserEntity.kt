package com.rogoz208.hometask4_2_fragments.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserEntity(
    val uId: String? = null,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val avatarUrl: String,
) : Parcelable
