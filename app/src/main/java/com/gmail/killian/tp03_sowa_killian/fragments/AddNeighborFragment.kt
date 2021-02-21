package com.gmail.killian.tp03_sowa_killian.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gmail.killian.tp03_sowa_killian.MainActivity
import com.gmail.killian.tp03_sowa_killian.NavigationListener
import com.gmail.killian.tp03_sowa_killian.R
import com.gmail.killian.tp03_sowa_killian.adapters.ListNeighborsAdapter
import com.gmail.killian.tp03_sowa_killian.data.NeighborRepository
import com.gmail.killian.tp03_sowa_killian.databinding.AddNeighborBinding

class AddNeighborFragment: Fragment() {
    private lateinit var binding: AddNeighborBinding

    /**
     * Fonction permettant de définir une vue à attacher à un fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddNeighborBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val neighbors = NeighborRepository.getInstance().getNeighbors()


        (activity as? NavigationListener)?.updateTitle(R.string.new_neighbor)
    }
}