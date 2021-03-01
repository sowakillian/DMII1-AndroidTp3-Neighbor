package com.gmail.killian.tp03_sowa_killian.adapters

import com.gmail.killian.tp03_sowa_killian.models.Neighbor

interface ListNeighborHandler {
    fun onDeleteNeighbor(neighbor: Neighbor)
    fun onDetailsNeighbor(neighbor: Neighbor)
}
