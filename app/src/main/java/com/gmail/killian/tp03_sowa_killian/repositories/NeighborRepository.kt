package com.gmail.killian.tp03_sowa_killian.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.gmail.killian.tp03_sowa_killian.dal.NeighborDataSource
import com.gmail.killian.tp03_sowa_killian.dal.room.RoomNeighborDataSource
import com.gmail.killian.tp03_sowa_killian.models.Neighbor

class NeighborRepository private constructor(application: Application) {
    private val dataSource: NeighborDataSource

    init {
        dataSource = RoomNeighborDataSource(application)
    }

    fun getNeighbors(): LiveData<List<Neighbor>> = dataSource.neighbors

    fun deleteNeighbor(neighbor: Neighbor) = dataSource.deleteNeighbor(neighbor)

    companion object {
        private var instance: NeighborRepository? = null

        // On crée un méthode qui retourne l'instance courante du repository si elle existe ou en crée une nouvelle sinon
        fun getInstance(application: Application): NeighborRepository {
            if (instance == null) {
                instance = NeighborRepository(application)
            }
            return instance!!
        }
    }
}
