package com.bashirli.lazastore.presentation.ui.favorites

import android.graphics.Canvas
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bashirli.lazastore.R
import com.bashirli.lazastore.common.base.BaseFragment
import com.bashirli.lazastore.common.util.CustomProgressBar
import com.bashirli.lazastore.common.util.Status
import com.bashirli.lazastore.common.util.errorToast
import com.bashirli.lazastore.common.util.gone
import com.bashirli.lazastore.common.util.visible
import com.bashirli.lazastore.data.dto.local.FavoritesDTO
import com.bashirli.lazastore.databinding.FragmentFavoritesBinding
import com.bashirli.lazastore.domain.model.local.FavoritesModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator

@AndroidEntryPoint
class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>(FragmentFavoritesBinding::inflate) {

    private val viewModel by viewModels<FavoritesMVVM>()
    private val adapter=FavoritesAdapter()

    override fun onViewCreateFinished() {
    observeData()
    }

    override fun setup() {
        binding.apply {
            rvFavorites.adapter=adapter

            adapter.onClickFavoritesItem={
                findNavController().navigate(FavoritesFragmentDirections.actionFavoritesFragmentToProductFragment(it.id))
            }
            setRVDecorator()
        }
    }

    private fun observeData(){
        val pb=CustomProgressBar.progressDialog(requireContext())
        viewModel.apply {
            favoriteProducts.observe(viewLifecycleOwner){
                when(it.status){
                    Status.SUCCESS->{
                        pb.cancel()
                        it.data?.let {
                            updateUI(it)
                        }
                    }
                    Status.ERROR->{
                        pb.cancel()
                        errorToast(requireActivity(),it.message?:"Error")
                    }
                    Status.LOADING->{
                        pb.show()
                    }
                }
            }
        }
    }

    private fun updateUI(list:List<FavoritesModel>){
        if(list.isEmpty()){
            binding.layoutEmpty.visible()
            binding.rvFavorites.gone()
        }else{
            adapter.updateList(list)
            binding.layoutEmpty.gone()
            binding.rvFavorites.visible()
        }
    }

    private fun setRVDecorator(){
        val callback=object:ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position=viewHolder.bindingAdapterPosition
                val list=ArrayList(adapter.getDifferList())
                val currentItem=list.get(position)
                val data=FavoritesDTO(
                    currentItem.id,
                    currentItem.title,
                    currentItem.imageURL,
                    currentItem.description,
                    currentItem.price
                )

                list.removeAt(position)
                adapter.updateList(list)
                viewModel.deleteItemFromFavorites(data)

                Snackbar.make(requireView(),R.string.itemRemoved,Snackbar.LENGTH_LONG).setAction(R.string.undo){
                        list.add(position,currentItem)
                        adapter.updateList(list)
                        viewModel.addItemToFavorites(data)
                }.show()


            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )

                RecyclerViewSwipeDecorator.Builder(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                ).addBackgroundColor(ContextCompat.getColor(requireContext(),R.color.red))
                    .addActionIcon(R.drawable.baseline_delete_outline_24)
                    .addCornerRadius(0,10)
                    .create().decorate()

            }

        }

        ItemTouchHelper(callback).attachToRecyclerView(binding.rvFavorites)

    }



}