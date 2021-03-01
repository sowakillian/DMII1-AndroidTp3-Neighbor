package com.gmail.killian.tp03_sowa_killian.communicators

import com.gmail.killian.tp03_sowa_killian.models.Neighbor

interface MainCommunicator {
    fun passNeighbor(neighbor: Neighbor)
}
