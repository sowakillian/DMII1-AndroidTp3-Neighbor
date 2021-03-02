package com.gmail.killian.tp03_sowa_killian.dal.room.daos

import androidx.lifecycle.LiveData
import androidx.room.* // ktlint-disable no-wildcard-imports
import com.gmail.killian.tp03_sowa_killian.dal.room.entities.NeighborEntity

@Dao
interface NeighborDao {
    @Query("SELECT * from neighbors")
    fun getNeighbors(): LiveData<List<NeighborEntity>>

    @Insert
    fun createNeighbor(entity: NeighborEntity)

    @Delete
    fun deleteNeighbor(entity: NeighborEntity)

    @Update
    fun updateNeighborFavoriteStatus(entity: NeighborEntity)
}
