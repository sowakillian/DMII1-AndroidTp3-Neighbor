package com.gmail.killian.tp03_sowa_killian.di

import android.app.Application
import com.gmail.killian.tp03_sowa_killian.repositories.NeighborRepository

object DI {
    lateinit var repository: NeighborRepository
    fun inject(application: Application) {
        repository = NeighborRepository.getInstance(application)
    }
}
