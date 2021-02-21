package com.gmail.killian.tp03_sowa_killian.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gmail.killian.tp03_sowa_killian.NavigationListener
import com.gmail.killian.tp03_sowa_killian.R
import com.gmail.killian.tp03_sowa_killian.data.NeighborRepository
import com.gmail.killian.tp03_sowa_killian.databinding.AddNeighborBinding
import com.gmail.killian.tp03_sowa_killian.models.Neighbor

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

        (activity as? NavigationListener)?.updateTitle(R.string.new_neighbor)

        listenSaveClicked()
    }

    private fun listenSaveClicked() {
        binding.saveButton.setOnClickListener {
            println("listenSaveClicked2")
            NeighborRepository.getInstance().createNeighbor(Neighbor(5, "Jack", "http://google.fr", "66 rote de ee", "056666666", "Je suis un gland", false, "zz"))
            println(NeighborRepository.getInstance().getNeighbors())
        }
    }
}