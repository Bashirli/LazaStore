package com.bashirli.lazastore.presentation.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bashirli.lazastore.R
import com.bashirli.lazastore.databinding.ItemCartBinding
import com.bashirli.lazastore.domain.model.cart.CartProductModel

class CartAdapter : RecyclerView.Adapter<CartAdapter.CartAdapterViewHolder>() {

    var onClickIncreaseButton:(CartProductModel)->Unit={}
    var onClickDecreaseButton:(CartProductModel)->Unit={}
    var onClickDeleteButton:(CartProductModel)->Unit={}
    var onClickItemListener:(CartProductModel)->Unit={}

    inner class CartAdapterViewHolder(private val binding : ItemCartBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item:CartProductModel){
            binding.productData=item
            binding.executePendingBindings()
        }

        fun find(
            item:CartProductModel,
            onClickIncreaseButton:(CartProductModel)->Unit={},
            onClickDecreaseButton:(CartProductModel)->Unit={},
            onClickItemListener:(CartProductModel)->Unit={},
            onClickDeleteButton:(CartProductModel)->Unit={}
        ){
            binding.apply {
                buttonIncrease.setOnClickListener {
                    onClickIncreaseButton(item)
                }
                buttonDecrease.setOnClickListener {
                    onClickDecreaseButton(item)
                }
                buttonDelete.setOnClickListener {
                    onClickDeleteButton(item)
                }
                cardItem.setOnClickListener{
                    onClickItemListener(item)
                }
            }
        }

        fun setAnimation(){
            val anim=AnimationUtils.loadAnimation(binding.root.context, R.anim.rv_cart_anim)
            binding.cardItem.animation=anim
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
        holder.find(item, onClickIncreaseButton, onClickDecreaseButton, onClickItemListener, onClickDeleteButton)
        holder.setAnimation()
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