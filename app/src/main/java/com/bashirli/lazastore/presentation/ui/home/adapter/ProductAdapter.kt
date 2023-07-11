package com.bashirli.lazastore.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bashirli.lazastore.databinding.ItemProductBinding
import com.bashirli.lazastore.domain.model.remote.ProductModel

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductHolder>() {


    var onProductClickListener:(ProductModel)->Unit={}
    inner class ProductHolder (private val binding:ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductModel){
            binding.productData=item
            binding.executePendingBindings()
        }
        fun find(item: ProductModel, onProductClickListener:(ProductModel)->Unit={}){
            binding.linearLayout.setOnClickListener {
                onProductClickListener(item)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val layout=ItemProductBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductHolder(layout)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val list=differ.currentList
        val item=list.get(position)
        holder.bind(item)
        holder.find(item,onProductClickListener)
    }

     private val  productDiffUtil=object:DiffUtil.ItemCallback<ProductModel>(){
        override fun areItemsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
            return oldItem==newItem
        }

    }

    private val differ=AsyncListDiffer(this,productDiffUtil)

    fun updateList(list:List<ProductModel>){
        differ.submitList(list)
    }

}