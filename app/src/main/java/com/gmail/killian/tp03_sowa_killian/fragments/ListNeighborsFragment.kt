package com.gmail.killian.tp03_sowa_killian.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.killian.tp03_sowa_killian.adapters.ListNeighborsAdapter
import com.gmail.killian.tp03_sowa_killian.data.NeighborRepository
import com.gmail.killian.tp03_sowa_killian.databinding.ListNeighborsFragmentBinding

class ListNeighborsFragment: Fragment() {
    private lateinit var binding: ListNeighborsFragmentBinding

    /**
     * Fonction permettant de définir une vue à attacher à un fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ListNeighborsFragmentBinding.inflate(inflater, container, false)
        binding.neighborsList.layoutManager = LinearLayoutManager(context)
        binding.neighborsList.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val neighbors = NeighborRepository.getInstance().getNeighbours()
        val adapter = ListNeighborsAdapter(neighbors)
        binding.neighborsList.adapter = adapter
    }
}