package com.example.smartflowcosmetics.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.smartflowcosmetics.databinding.ProductsLayoutBinding
import com.example.smartflowcosmetics.helpers.getProgressDrawable
import com.example.smartflowcosmetics.helpers.loadImage
import com.example.smartflowcosmetics.model.ProductItem
import dagger.hilt.android.AndroidEntryPoint


class ProductAdapter(val context:Context): RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ProductsLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holderProduct: ProductViewHolder, position: Int) {
        val productList = differ.currentList[position]
        holderProduct.binding.apply {
            productTitle.text = productList.name
            productImage.loadImage(
                productList.image_link,
                getProgressDrawable(context)
            )
            productCard.setOnClickListener {
                onItemClickListener?.let {
                    it(productList)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private val diffCallback = object : DiffUtil.ItemCallback<ProductItem>() {
        override fun areItemsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, diffCallback)

    private var onItemClickListener: ((ProductItem) -> Unit)? = null
    fun setOnItemClickListener(Listener: (ProductItem) -> Unit) {
        onItemClickListener = Listener
    }


}

class ProductViewHolder(val binding:ProductsLayoutBinding) : RecyclerView.ViewHolder(binding.root)