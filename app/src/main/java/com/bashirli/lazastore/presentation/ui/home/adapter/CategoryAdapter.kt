package com.bashirli.lazastore.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bashirli.lazastore.databinding.ItemCategoryBinding
import com.bashirli.lazastore.domain.model.CategoryModel
import com.bashirli.lazastore.domain.model.ProductCategoryModel

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {

    private val arrayList=ArrayList<CategoryModel>()

    inner class CategoryHolder(private val binding:ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item:CategoryModel){
            binding.categoryData=item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val layout=ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CategoryHolder(layout)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        val item=arrayList.get(position)
        holder.bind(item)
    }

    fun updateList(list:List<CategoryModel>){
        arrayList.clear()
        arrayList.addAll(list)
        notifyDataSetChanged()
    }

}