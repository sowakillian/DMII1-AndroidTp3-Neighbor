package com.gmail.killian.tp03_sowa_killian.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gmail.killian.tp03_sowa_killian.R
import com.gmail.killian.tp03_sowa_killian.databinding.NeighborDetailFragmentBinding
import com.gmail.killian.tp03_sowa_killian.listeners.NavigationListener
import com.gmail.killian.tp03_sowa_killian.models.Neighbor
import java.util.concurrent.Executors

class NeighborDetailFragment : Fragment() {
    private lateinit var binding: NeighborDetailFragmentBinding
    var neighborSelected: Neighbor? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NeighborDetailFragmentBinding.inflate(inflater, container, false)
        neighborSelected = arguments?.getParcelable("neighbor")

        neighborSelected?.let { initNeighborText(it) }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? NavigationListener)?.updateTitle(R.string.new_neighbor)

        listenFavClicked()
    }

    private fun initNeighborText(neighbor: Neighbor) {
        binding.nameTv.text = neighbor.name
        binding.aboutTv.text = neighbor.aboutMe
        binding.addressTv.text = neighbor.address
        binding.phoneTv.text = neighbor.phoneNumber
        binding.websiteTv.text = neighbor.webSite
    }

    private fun listenFavClicked() {
        binding.addFavoriteButton.setOnClickListener {
            Executors.newSingleThreadExecutor().execute {
                // DI.repository.updateFavoriteStatus(neighbor)
                // it.text
            }
        }
    }
}
