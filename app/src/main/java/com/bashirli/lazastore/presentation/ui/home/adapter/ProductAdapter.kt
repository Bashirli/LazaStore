package com.bashirli.lazastore.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bashirli.lazastore.databinding.ItemProductBinding
import com.bashirli.lazastore.domain.model.ProductModel

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductHolder>() {

    private val arrayList=ArrayList<ProductModel>()

    inner class ProductHolder (private val binding:ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item:ProductModel){
            binding.productData=item
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val layout=ItemProductBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductHolder(layout)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val item=arrayList.get(position)
        holder.bind(item)
    }

    fun updateList(list:List<ProductModel>){
        arrayList.clear()
        arrayList.addAll(list)
        notifyDataSetChanged()
    }

}