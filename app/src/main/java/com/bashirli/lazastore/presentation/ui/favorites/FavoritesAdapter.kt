package com.bashirli.lazastore.presentation.ui.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bashirli.lazastore.common.util.diffList
import com.bashirli.lazastore.databinding.ItemFavoriteBinding
import com.bashirli.lazastore.domain.model.local.FavoritesModel
import com.bashirli.lazastore.domain.model.remote.ProductModel

class FavoritesAdapter : RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    private var myList= ArrayList<FavoritesModel>()
    var onClickFavoritesItem : (FavoritesModel)->Unit={}

    inner class FavoritesViewHolder(private val binding:ItemFavoriteBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item:FavoritesModel){
            binding.favoritesModel=item
            binding.executePendingBindings()
        }
        fun find(item:FavoritesModel,onClickFavoritesItem : (FavoritesModel)->Unit={}){
            binding.cardRoot.setOnClickListener {
                onClickFavoritesItem(item)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val layout=ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FavoritesViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val item=myList.get(position)
        holder.bind(item)
        holder.find(item,onClickFavoritesItem)
    }


    fun updateList(list:List<FavoritesModel>){
        diffList(myList,list, sameItem = {a,b-> a.id==b.id}).dispatchUpdatesTo(this)
        myList.clear()
        myList.addAll(list)
    }



    fun getDifferList():List<FavoritesModel>{
        return myList
    }

}