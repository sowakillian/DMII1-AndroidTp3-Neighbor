package com.gmail.killian.tp03_sowa_killian.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Neighbor(
    val id: Long,
    val name: String,
    val avatarUrl: String,
    val address: String,
    val phoneNumber: String,
    val aboutMe: String,
    val favorite: Boolean,
    val webSite: String
) : Parcelable
