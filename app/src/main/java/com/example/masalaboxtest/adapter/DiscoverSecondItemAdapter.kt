package com.example.masalaboxtest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.masalaboxtest.R
import com.example.masalaboxtest.data.MediumImage
import com.example.masalaboxtest.databinding.SecondImageItemBinding

class DiscoverSecondItemAdapter(
    private var listItem: List<MediumImage>
) : RecyclerView.Adapter<DiscoverSecondItemAdapter.SecondItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SecondItemViewHolder {
        val binding =
            SecondImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SecondItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SecondItemViewHolder, position: Int) {
        val currentItem = listItem[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return listItem.size
    }


    inner class SecondItemViewHolder(private var binding: SecondImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currentItem: MediumImage) {
            binding.secondImage.load(currentItem.middleImage) {
                placeholder(R.drawable.ic_baseline_sentiment_satisfied_alt_24)
            }
        }
    }


}