package com.gmail.killian.tp03_sowa_killian.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
}