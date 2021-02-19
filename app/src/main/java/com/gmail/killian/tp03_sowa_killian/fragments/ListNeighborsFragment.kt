package com.gmail.killian.tp03_sowa_killian.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.killian.tp03_sowa_killian.adapters.ListNeighborHandler
import com.gmail.killian.tp03_sowa_killian.adapters.ListNeighborsAdapter
import com.gmail.killian.tp03_sowa_killian.data.NeighborRepository
import com.gmail.killian.tp03_sowa_killian.databinding.ListNeighborsFragmentBinding
import com.gmail.killian.tp03_sowa_killian.models.Neighbor

class ListNeighborsFragment: Fragment(), ListNeighborHandler {
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
        val neighbors = NeighborRepository.getInstance().getNeighbors()
        val adapter = ListNeighborsAdapter(neighbors, this)
        binding.neighborsList.adapter = adapter
    }

    override fun onDeleteNeighbor(neighbor: Neighbor) {
        displayAlertDialog(neighbor)
    }

    private fun displayAlertDialog(neighbor: Neighbor) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Confirmation")
        builder.setMessage("Voulez-vous supprimer ce voisin ?")

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            NeighborRepository.getInstance().deleteNeighbor(neighbor)
            binding.neighborsList.adapter?.notifyDataSetChanged()
        }

        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            Toast.makeText(context,
                "Le voisin n'a pas été supprimé", Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }
}