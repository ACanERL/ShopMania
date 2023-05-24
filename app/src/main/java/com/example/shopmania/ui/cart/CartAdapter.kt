package com.example.shopmania.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shopmania.data.model.CartModel
import com.example.shopmania.databinding.CartItemBinding
import com.example.shopmania.util.DiffUtilCallback
import com.example.shopmania.util.click

class CartAdapter(
    var onDeleteClick: (Int)->Unit = {},
    var onIncrease: (Double)->Unit = {},
    var onDecrease: (Double)->Unit = {}
) : ListAdapter<CartModel, CartAdapter.CartViewHolder>(
    DiffUtilCallback<CartModel>(
        itemsTheSame = { oldItem, newItem ->
            oldItem == newItem
        },
        contentsTheSame = { oldItem, newItem ->
            oldItem == newItem
        }
    )
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CartViewHolder (
        CartItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun onBindViewHolder(holder: CartAdapter.CartViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class CartViewHolder(val binding: CartItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(product: CartModel){
            var productCount = 1
            binding.cartItemCount.text=productCount.toString()
            binding.product = product
            binding.executePendingBindings()

            binding.cartItemDelete.click {
                onDeleteClick(product.id)
            }

            binding.cartItemPlus.click {
                product.price?.let {
                    onIncrease(it)
                }
                productCount++
                binding.product!!.count = productCount
                binding.product = product // update the view
                println(product.count)
                binding.cartItemCount.text=productCount.toString()
            }

            binding.cartItemMinus.click {
                if(productCount!=1){
                    product.price?.let {
                        onDecrease(it)
                    }
                    productCount--
                    binding.product!!.count = productCount
                    binding.product = product
                    binding.cartItemCount.text=productCount.toString()
                    println(product.count)
                }
            }
        }
    }
}