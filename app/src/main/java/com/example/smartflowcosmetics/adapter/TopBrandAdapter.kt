package com.example.smartflowcosmetics.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.smartflowcosmetics.databinding.TopBrandsLayoutBinding
import com.example.smartflowcosmetics.model.ProductItem

class TopBrandAdapter : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            TopBrandsLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val topBrands = differ.currentList[position]
        holder.binding.apply {
            if (topBrands.brand != null) {
                topBrandName.text = topBrands.brand
                topBrandView.setOnClickListener {
                    onItemClickListener?.let {
                        it(topBrands)
                    }
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

class ViewHolder(val binding: TopBrandsLayoutBinding) : RecyclerView.ViewHolder(binding.root)