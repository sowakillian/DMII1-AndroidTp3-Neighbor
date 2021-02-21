package com.gmail.killian.tp03_sowa_killian.data

import com.gmail.killian.tp03_sowa_killian.data.datasource.InMemoryNeighborDataSource
import com.gmail.killian.tp03_sowa_killian.data.datasource.NeighborDataSource
import com.gmail.killian.tp03_sowa_killian.models.Neighbor

class NeighborRepository {
    private val dataSource: NeighborDataSource

    init {
        dataSource = InMemoryNeighborDataSource()
    }

    // On ne veut pas qu'on puisse instancier le repository de l'extérieur; pour cela on rend son constructeur private
    private constructor() {

    }

    init {

    }

    // Méthode qui retourne la liste des voisins
    fun getNeighbors(): List<Neighbor> = dataSource.neighbors

    fun deleteNeighbor(neighbor: Neighbor) {
        dataSource.deleteNeighbor(neighbor)
    }

    fun createNeighbor(neighbor: Neighbor) {
        dataSource.createNeighbor(neighbor)
    }

    companion object {
        private var instance: NeighborRepository? = null

        fun getInstance(): NeighborRepository {
            if (instance == null) {
                instance = NeighborRepository()
            }
            return instance!!
        }
    }
}