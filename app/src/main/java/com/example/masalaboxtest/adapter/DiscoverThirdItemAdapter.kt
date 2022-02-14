package com.example.masalaboxtest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.masalaboxtest.data.ItemDetail
import com.example.masalaboxtest.databinding.ThirdItemBinding

class DiscoverThirdItemAdapter(private var listItem: List<ItemDetail>) :
    RecyclerView.Adapter<DiscoverThirdItemAdapter.ThirdItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThirdItemViewHolder {
        val binding = ThirdItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ThirdItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ThirdItemViewHolder, position: Int) {
        val currentItem = listItem[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    fun updateList(data: List<ItemDetail>) {
        listItem = data
        notifyDataSetChanged()
    }

    inner class ThirdItemViewHolder(private var binding: ThirdItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currentItem: ItemDetail) {
            binding.textOne.text = currentItem.textOne
            binding.textTwo.text = currentItem.textTwo
            binding.coverImage.load(currentItem.middleImage)
            binding.smallImage.load(currentItem.smallImage)
        }
    }
}