package com.example.masalaboxtest.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.masalaboxtest.data.SmallImage
import com.example.masalaboxtest.databinding.TopImageItemBinding

class DiscoverTopItemAdapter(private var topData: List<SmallImage>) :
    RecyclerView.Adapter<DiscoverTopItemAdapter.TopItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DiscoverTopItemAdapter.TopItemViewHolder {
        val binding =
            TopImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopItemViewHolder, position: Int) {
        val currentItem = topData[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return topData.size
    }

    fun submitList(data: List<SmallImage>) {
        topData = data
        notifyDataSetChanged()
    }

    inner class TopItemViewHolder(private var binding: TopImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currentItem: SmallImage) {
            binding.topImage.load(currentItem.smallImage)
        }
    }

    companion object {
        private const val TAG = "DiscoverTopItemAdapter"
    }

}