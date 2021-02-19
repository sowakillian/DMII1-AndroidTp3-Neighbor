package com.gmail.killian.tp03_sowa_killian.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.killian.tp03_sowa_killian.databinding.NeighborItemBinding
import com.gmail.killian.tp03_sowa_killian.models.Neighbor

class ListNeighborsAdapter(
    items: List<Neighbor>
) : RecyclerView.Adapter<ListNeighborsAdapter.ViewHolder>() {
    private val mNeighbours: List<Neighbor> = items
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: NeighborItemBinding = NeighborItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val neighbour: Neighbor = mNeighbours[position]
        // Display Neighbour Name
        holder.binding.itemListName.text = neighbour.name
    }

    override fun getItemCount(): Int {
        return mNeighbours.size
    }

    class ViewHolder(val binding: NeighborItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

}