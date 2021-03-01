package com.gmail.killian.tp03_sowa_killian.dal.memory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gmail.killian.tp03_sowa_killian.dal.InMemory_Neighbors
import com.gmail.killian.tp03_sowa_killian.dal.NeighborDataSource
import com.gmail.killian.tp03_sowa_killian.models.Neighbor

class InMemoryNeighborDataSource : NeighborDataSource {

    private val _neighbors = MutableLiveData<List<Neighbor>>()

    init {
        _neighbors.value = InMemory_Neighbors
    }

    override val neighbors: MutableLiveData<List<Neighbor>>
        get() = _neighbors

    override fun deleteNeighbor(neighbor: Neighbor) {
       // neighbors.value?.removeIf { e -> e.id == neighbor.id }
    }

    override fun createNeighbor(neighbor: Neighbor) {
        //neighbors.value?.add(neighbor)
    }

    override fun updateFavoriteStatus(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }

    override fun updateNeighbor(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }
}
