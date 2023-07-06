package com.bashirli.lazastore.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bashirli.lazastore.databinding.ItemCategoryBinding
import com.bashirli.lazastore.domain.model.CategoryModel

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {

    var onCategoryClickListener:(CategoryModel)->Unit={}


    inner class CategoryHolder(private val binding:ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item:CategoryModel){
            binding.categoryData=item
        }
        fun find(item:CategoryModel,onCategoryClickListener:(CategoryModel)->Unit={}){
            binding.cardCategory.setOnClickListener {
                onCategoryClickListener(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val layout=ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CategoryHolder(layout)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        val list=differ.currentList
        val item=list.get(position)
        holder.bind(item)
        holder.find(item,onCategoryClickListener)
    }

    private val categoryDiffer=object:DiffUtil.ItemCallback<CategoryModel>(){
        override fun areItemsTheSame(oldItem: CategoryModel, newItem: CategoryModel): Boolean {
            return oldItem.category==newItem.category
        }

        override fun areContentsTheSame(oldItem: CategoryModel, newItem: CategoryModel): Boolean {
            return oldItem==newItem
        }

    }

    private val differ=AsyncListDiffer(this,categoryDiffer)

    fun updateList(list:List<CategoryModel>){
        differ.submitList(list)
    }

}