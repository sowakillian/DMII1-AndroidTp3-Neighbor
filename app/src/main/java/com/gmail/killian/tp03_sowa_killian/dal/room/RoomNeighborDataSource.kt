package com.gmail.killian.tp03_sowa_killian.dal.room

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.gmail.killian.tp03_sowa_killian.dal.NeighborDataSource
import com.gmail.killian.tp03_sowa_killian.dal.room.daos.NeighborDao
import com.gmail.killian.tp03_sowa_killian.dal.utilis.toEntity
import com.gmail.killian.tp03_sowa_killian.dal.utilis.toNeighbor
import com.gmail.killian.tp03_sowa_killian.models.Neighbor

class RoomNeighborDataSource(application: Application) : NeighborDataSource {
    private val database: NeighborDataBase = NeighborDataBase.getDataBase(application)
    private val dao: NeighborDao = database.neighborDao()

    private val _neighbors = MediatorLiveData<List<Neighbor>>()

    init {
        _neighbors.addSource(dao.getNeighbors()) { entities ->
            _neighbors.value = entities.map { entity ->
                entity.toNeighbor()
            }
        }
    }

    override val neighbors: LiveData<List<Neighbor>>
        get() = _neighbors

    override fun deleteNeighbor(neighbor: Neighbor) {
        dao.deleteNeighbor(neighbor.toEntity())
    }

    override fun createNeighbor(neighbor: Neighbor) {
        dao.createNeighbor(neighbor.toEntity())
    }

    override fun updateFavoriteStatus(neighbor: Neighbor) {
        if (neighbor.favorite) {
            dao.updateNeighborFavoriteStatus(neighbor.toEntity(favorite = false))
        } else {
            dao.updateNeighborFavoriteStatus(neighbor.toEntity(favorite = true))
        }
    }

    override fun updateNeighbor(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }
}
