package com.gmail.killian.tp03_sowa_killian.dal.utilis

import com.gmail.killian.tp03_sowa_killian.dal.room.entities.NeighborEntity
import com.gmail.killian.tp03_sowa_killian.models.Neighbor

fun NeighborEntity.toNeighbor() = Neighbor(
    id = id,
    name = name,
    avatarUrl = avatarUrl,
    address = address,
    phoneNumber = phoneNumber,
    aboutMe = aboutMe,
    favorite = favorite,
    webSite = webSite ?: ""
)

fun Neighbor.toEntity(favorite: Boolean = false) = NeighborEntity(
    id = id,
    name = name,
    avatarUrl = avatarUrl,
    address = address,
    phoneNumber = phoneNumber,
    aboutMe = aboutMe,
    favorite = favorite ?: false,
    webSite = webSite
)

