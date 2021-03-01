package com.gmail.killian.tp03_sowa_killian.dal.room

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.gmail.killian.tp03_sowa_killian.dal.NeighborDataSource
import com.gmail.killian.tp03_sowa_killian.dal.room.daos.NeighborDao
import com.gmail.killian.tp03_sowa_killian.dal.utilis.toNeighbor
import com.gmail.killian.tp03_sowa_killian.models.Neighbor

class RoomNeighborDataSource(application: Application) : NeighborDataSource {
    private val database: NeighborDataBase = NeighborDataBase.getDataBase(application)
    private val dao: NeighborDao = database.neighborDao()

    private val _neighors = MediatorLiveData<List<Neighbor>>()

    init {
        _neighors.addSource(dao.getNeighbors()) { entities ->
            _neighors.value = entities.map { entity ->
                entity.toNeighbor()
            }
        }
    }

    override val neighbors: LiveData<List<Neighbor>>
        get() = _neighors

    override fun deleteNeighbor(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }

    override fun createNeighbor(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }

    override fun updateFavoriteStatus(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }

    override fun updateNeighbor(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }
}