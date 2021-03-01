package com.gmail.killian.tp03_sowa_killian.dal.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "neighbors")
class NeighborEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    var name: String,
    var avatarUrl: String,
    val address: String,
    var phoneNumber: String,
    var aboutMe: String,
    var favorite: Boolean = false,
    var webSite: String? = null
)
