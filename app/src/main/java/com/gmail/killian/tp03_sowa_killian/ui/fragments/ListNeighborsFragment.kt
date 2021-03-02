package com.gmail.killian.tp03_sowa_killian.ui.fragments

import android.app.AlertDialog
import android.app.Application
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.killian.tp03_sowa_killian.R
import com.gmail.killian.tp03_sowa_killian.adapters.ListNeighborHandler
import com.gmail.killian.tp03_sowa_killian.adapters.ListNeighborsAdapter
import com.gmail.killian.tp03_sowa_killian.communicators.MainCommunicator
import com.gmail.killian.tp03_sowa_killian.databinding.ListNeighborsFragmentBinding
import com.gmail.killian.tp03_sowa_killian.di.DI
import com.gmail.killian.tp03_sowa_killian.listeners.NavigationListener
import com.gmail.killian.tp03_sowa_killian.models.Neighbor
import com.gmail.killian.tp03_sowa_killian.viewmodels.NeighborViewModel
import java.util.concurrent.Executors

class ListNeighborsFragment : Fragment(), ListNeighborHandler {
    private lateinit var binding: ListNeighborsFragmentBinding
    private lateinit var viewModel: NeighborViewModel
    private lateinit var comm: MainCommunicator

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

        comm = activity as MainCommunicator
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        viewModel = ViewModelProvider(this).get(NeighborViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu);

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()

        listenAddNeighborClicked()

        (activity as? NavigationListener)?.updateTitle(R.string.neighbor_list)
    }

    private fun setData() {
        viewModel.neighbors.observe(
            viewLifecycleOwner,
            Observer<List<Neighbor>> { t ->
                val adapter = ListNeighborsAdapter(t, this@ListNeighborsFragment)
                binding.neighborsList.adapter = adapter
            }
        )
    }

    override fun onDetailsNeighbor(neighbor: Neighbor) {
        comm.passNeighbor(neighbor)
    }

    override fun onDeleteNeighbor(neighbor: Neighbor) {
        displayAlertDialog(neighbor)
    }

    private fun listenAddNeighborClicked() {
        binding.addNeighbor.setOnClickListener {
            (activity as? NavigationListener)?.showFragment(AddNeighborFragment())
        }
    }

    private fun displayAlertDialog(neighbor: Neighbor) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(getString(R.string.confirmation))
        builder.setMessage(getString(R.string.want_you_to_delete_neighbor))

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            val application: Application? = activity?.application
            if (application != null) {
                Executors.newSingleThreadExecutor().execute {
                    DI.repository.deleteNeighbor(neighbor)
                }

                binding.neighborsList.adapter?.notifyDataSetChanged()
            }
        }

        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            Toast.makeText(
                context,
                getString(R.string.neighbor_has_not_been_deleted), Toast.LENGTH_SHORT
            ).show()
        }
        builder.show()
    }
}
