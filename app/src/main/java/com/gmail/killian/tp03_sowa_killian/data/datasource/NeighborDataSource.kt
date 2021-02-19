package com.gmail.killian.tp03_sowa_killian.data.datasource

import com.gmail.killian.tp03_sowa_killian.models.Neighbor

interface NeighborDataSource {
    /**
     * Get all my Neighbors
     * @return [List]
     */
    val neighbors: List<Neighbor>

    /**
     * Deletes a neighbor
     * @param neighbor : Neighbor
     */
    fun deleteNeighbor(neighbor: Neighbor)

    /**
     * Create a neighbour
     * @param neighbor: Neighbor
     */
    fun createNeighbor(neighbor: Neighbor)

    /**
     * Update "Favorite status" of an existing Neighbour"
     * @param neighbor: Neighbor
     */
    fun updateFavoriteStatus(neighbor: Neighbor)

    /**
     * Update modified fields of an existing Neighbour"
     * @param neighbor: Neighbor
     */
    fun updateNeighbor(neighbor: Neighbor)
}