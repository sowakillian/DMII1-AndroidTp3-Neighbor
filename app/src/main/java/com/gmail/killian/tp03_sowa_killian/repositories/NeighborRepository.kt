package com.gmail.killian.tp03_sowa_killian.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.gmail.killian.tp03_sowa_killian.dal.InMemory_Neighbors
import com.gmail.killian.tp03_sowa_killian.dal.NeighborDataSource
import com.gmail.killian.tp03_sowa_killian.dal.room.RoomNeighborDataSource
import com.gmail.killian.tp03_sowa_killian.models.Neighbor

class NeighborRepository private constructor(application: Application) {
    private val dataSource: NeighborDataSource

    init {
        dataSource = RoomNeighborDataSource(application)
    }

    fun getNeighbors(): LiveData<List<Neighbor>> = dataSource.neighbors

    fun getInMemoryNeighbors(): List<Neighbor> = InMemory_Neighbors

    fun createNeighbor(neighbor: Neighbor) = dataSource.createNeighbor(neighbor)

    fun deleteNeighbor(neighbor: Neighbor) = dataSource.deleteNeighbor(neighbor)

    fun updateFavoriteStatus(neighbor: Neighbor) = dataSource.updateFavoriteStatus(neighbor)

    companion object {
        private var instance: NeighborRepository? = null

        fun getInstance(application: Application): NeighborRepository {
            if (instance == null) {
                instance = NeighborRepository(application)
            }
            return instance!!
        }
    }
}
