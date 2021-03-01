package com.gmail.killian.tp03_sowa_killian.dal.room.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gmail.killian.tp03_sowa_killian.dal.room.entities.NeighborEntity

@Dao
interface NeighborDao {
    @Query("SELECT * from neighbors")
    fun getNeighbors(): LiveData<List<NeighborEntity>>

    @Insert
    fun createNeighbor(entity: NeighborEntity)

    @Delete
    fun deleteNeighbor(entity: NeighborEntity)
}
