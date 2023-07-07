package com.bashirli.lazastore.presentation.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bashirli.lazastore.databinding.ItemCartBinding
import com.bashirli.lazastore.domain.model.cart.CartProductModel

class CartAdapter : RecyclerView.Adapter<CartAdapter.CartAdapterViewHolder>() {

    inner class CartAdapterViewHolder(private val binding : ItemCartBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item:CartProductModel){
            binding.productData=item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapterViewHolder {
        val layout=ItemCartBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CartAdapterViewHolder(layout)
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }

    override fun onBindViewHolder(holder: CartAdapterViewHolder, position: Int) {
        val list=differ.currentList
        val item=list.get(position)
        holder.bind(item)
    }

    private val cartAdapterDiffUtil = object: DiffUtil.ItemCallback<CartProductModel>(){
        override fun areItemsTheSame(
            oldItem: CartProductModel,
            newItem: CartProductModel,
        ): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CartProductModel,
            newItem: CartProductModel,
        ): Boolean {
            return oldItem==newItem
        }

    }

    private val differ=AsyncListDiffer(this,cartAdapterDiffUtil)

    fun updateAdapter(list:List<CartProductModel>){
        differ.submitList(list)
    }

}