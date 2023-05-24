package com.example.shopmania.ui.Product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.shopmania.data.model.ProductResponseModel
import com.example.shopmania.databinding.ProductItemBinding

class ProductAdapter (private var productOnClick: (ProductResponseModel) -> Unit): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    private lateinit var binding: ProductItemBinding
    var productList = mutableListOf<ProductResponseModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder {
        binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int=productList.size

    inner class ViewHolder():RecyclerView.ViewHolder(binding.root){
        fun bind(productResponseModel: ProductResponseModel) {
            binding.productDescp.text =productResponseModel.description
            binding.productName.text=productResponseModel.title
            binding.productPrice.text= "$ ${productResponseModel.price.toString()}"
            binding.myRating.rating= productResponseModel.rating?.rate!!.toFloat()
            binding.productImage.load(productResponseModel.image){
                crossfade(true)
                crossfade(500)
            }
            binding.root.setOnClickListener {
                productOnClick(productResponseModel)
            }
        }
    }
    fun bind(data: List<ProductResponseModel>){
        val prDiffUtils = ProductAdapter.productDiffUtils(productList, data)
        val diffUtils = DiffUtil.calculateDiff(prDiffUtils)
        productList= data as MutableList<ProductResponseModel>
        diffUtils.dispatchUpdatesTo(this)
        notifyDataSetChanged()
    }
    class productDiffUtils(private val oldItem:List<ProductResponseModel>, private val newItem:List<ProductResponseModel>) : DiffUtil.Callback(){
        override fun getOldListSize(): Int {
            return oldItem.size
        }

        override fun getNewListSize(): Int {
            return newItem.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            // === data type is compred here
            return oldItem[oldItemPosition] === newItem[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition] === newItem[newItemPosition]
        }
    }
}