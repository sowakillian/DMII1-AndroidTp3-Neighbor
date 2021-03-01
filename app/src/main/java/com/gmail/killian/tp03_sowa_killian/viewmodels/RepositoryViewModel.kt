package com.gmail.killian.tp03_sowa_killian.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gmail.killian.tp03_sowa_killian.di.DI
import com.gmail.killian.tp03_sowa_killian.models.Neighbor
import com.gmail.killian.tp03_sowa_killian.repositories.NeighborRepository

class NeighborViewModel : ViewModel() {
    private val repository: NeighborRepository = DI.repository

    // On fait passe plat sur le résultat retourné par le repository
    val neighbors: LiveData<List<Neighbor>>
        get() = repository.getNeighbors()
}
