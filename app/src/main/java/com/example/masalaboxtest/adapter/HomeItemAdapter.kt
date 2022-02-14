package com.example.masalaboxtest.adapter

import android.content.ClipData
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.masalaboxtest.data.TypeOne
import com.example.masalaboxtest.data.TypeThree
import com.example.masalaboxtest.data.TypeTwo
import com.example.masalaboxtest.databinding.OneItemsBinding
import com.example.masalaboxtest.databinding.ThreeItemsBinding
import com.example.masalaboxtest.databinding.TwoItemsBinding
import com.example.masalaboxtest.util.ItemRow

class HomeItemAdapter(
    private var nameList: List<ItemRow>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var nameListCopy:List<ItemRow> = emptyList()

    private val TYPE_ONE = 1
    private val TYPE_TWO = 2
    private val TYPE_THREE = 3

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        when (viewType) {
            TYPE_ONE -> {
                return OneItemViewHolder(
                    OneItemsBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            TYPE_TWO -> {
                return TwoItemViewHolder(
                    TwoItemsBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                return ThreeItemViewHolder(
                    ThreeItemsBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }


    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = nameList[position]
        when (holder) {
            is OneItemViewHolder -> {
                val oneItem = (currentItem as ItemRow.One).typeOne
                holder.bind(oneItem)
            }
            is TwoItemViewHolder -> {
                val twoItem = (currentItem as ItemRow.Two).typeTwo
                holder.bind(currentItem = twoItem)
            }
            is ThreeItemViewHolder -> {
                val threeItem = (currentItem as ItemRow.Three).typeThree
                holder.bind(currentItem = threeItem)
            }
        }
    }

    override fun getItemCount(): Int {
        return nameList.size
    }

    fun updateData(data:List<ItemRow>){
        nameList = data
        nameListCopy = data
        notifyDataSetChanged()
    }

    fun filterData(data: Int){
        when (data){
            0 -> run {
                nameList = nameListCopy
                notifyDataSetChanged()
            }
            1 -> run {
                nameList = nameListCopy.filterIsInstance<ItemRow.One>()
                notifyDataSetChanged()
            }
            2 -> run {
                nameList = nameListCopy.filterIsInstance<ItemRow.Two>()
                notifyDataSetChanged()
            }
            3 -> run {
                nameList = nameListCopy.filterIsInstance<ItemRow.Three>()
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (nameList[position]) {
            is ItemRow.One -> TYPE_ONE
            is ItemRow.Two -> TYPE_TWO
            is ItemRow.Three -> TYPE_THREE
        }
    }

    inner class OneItemViewHolder(private val binding: OneItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currentItem: TypeOne) {
            binding.tvTitle.text = currentItem.name
        }
    }

    inner class TwoItemViewHolder(private val binding: TwoItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currentItem: TypeTwo) {
            binding.tvTitle.text = currentItem.name
        }
    }

    inner class ThreeItemViewHolder(private val binding: ThreeItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currentItem: TypeThree) {
            binding.tvTitle.text = currentItem.name
        }
    }
}