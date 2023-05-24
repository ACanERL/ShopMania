package com.example.shopmania.ui.Home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shopmania.R
import com.example.shopmania.databinding.CategoryItemBinding
import com.example.shopmania.util.DiffUtilCallback

class HomeAdapter (private var onCategoryClick: (String) -> Unit): ListAdapter<String, HomeAdapter.ViewHolder>(
    DiffUtilCallback<String>(
        itemsTheSame = { oldItem, newItem ->
            oldItem == newItem
        },
        contentsTheSame = { oldItem, newItem ->
            oldItem == newItem
        }
    )
) {
    private lateinit var binding: CategoryItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        holder.bind(currentList[position], categoryToDrawableMap[categories[position]] ?: 0)
    }
    inner class ViewHolder():RecyclerView.ViewHolder(binding.root){
        fun bind(category: String, drawableResId: Int) {
            binding.categoryName.text = category
            if (drawableResId != 0) {
                binding.categoryImage.setImageResource(drawableResId)
            }
            binding.root.setOnClickListener {
                onCategoryClick(category)
            }
        }
    }


    private val categories = listOf(
        "Electronics",
        "Jewelry",
        "Men's Clothes",
        "Women's Clothes"
    )

    private val categoryToDrawableMap = mapOf(
        "Electronics" to R.drawable.electronics,
        "Jewelry" to R.drawable.jewelry,
        "Men's Clothes" to R.drawable.mens_clothing,
        "Women's Clothes" to R.drawable.womans_clothing
    )
}