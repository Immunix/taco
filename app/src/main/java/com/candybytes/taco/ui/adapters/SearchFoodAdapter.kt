package com.candybytes.taco.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.candybytes.taco.databinding.ItemFoodBinding
import com.candybytes.taco.vo.Food

class SearchFoodAdapter(private val listener: OnItemClickListener) : ListAdapter<Food, SearchFoodAdapter.FoodViewHolder>(DiffCallback()) {

    inner class FoodViewHolder(private val binding: ItemFoodBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listener.onItemClick(item)
                    }
                }
            }
        }

        fun bind(food: Food) {
            binding.apply {
                (food.baseQty.toString() + food.baseUnity).also { qtyAndUnit.text = it }
                foodId.text = food.id.toString()
                description.text = food.description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        return FoodViewHolder(
            ItemFoodBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class DiffCallback : DiffUtil.ItemCallback<Food>() {
        override fun areItemsTheSame(oldItem: Food, newItem: Food) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Food, newItem: Food) =
            oldItem == newItem
    }

    interface OnItemClickListener {
        fun onItemClick(food: Food)
    }
}