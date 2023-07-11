package com.bashirli.lazastore.presentation.ui.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bashirli.lazastore.R
import com.bashirli.lazastore.common.base.BaseFragment
import com.bashirli.lazastore.common.util.CustomProgressBar
import com.bashirli.lazastore.common.util.Status
import com.bashirli.lazastore.common.util.errorToast
import com.bashirli.lazastore.common.util.gone
import com.bashirli.lazastore.common.util.infoToast
import com.bashirli.lazastore.common.util.visible
import com.bashirli.lazastore.databinding.FragmentCartBinding
import com.bashirli.lazastore.domain.model.remote.body.UpdateCartBody
import com.bashirli.lazastore.domain.model.remote.body.UpdateProduct
import com.bashirli.lazastore.domain.model.remote.cart.CartMainModel
import com.bashirli.lazastore.domain.model.remote.cart.CartModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import www.sanju.motiontoast.MotionToast

@AndroidEntryPoint
class CartFragment : BaseFragment<FragmentCartBinding>(FragmentCartBinding::inflate) {

    private val viewModel by viewModels<CartMVVM>()
    private val adapter=CartAdapter()
    private lateinit var cartMainModel: CartMainModel
    override fun onViewCreateFinished() {
    observeData()
    }

    override fun setup() {

       binding.apply {
           rvProduct.adapter=adapter

           buttonArrow.setOnClickListener {
               if(layoutOrderInfo.visibility==View.GONE){
                   layoutOrderInfo.visible()
                   buttonArrow.setIconResource(R.drawable.baseline_keyboard_arrow_down_24)
               }else{
                   layoutOrderInfo.gone()
                   buttonArrow.setIconResource(R.drawable.baseline_keyboard_arrow_up_24)
               }

           }

           setAdapterOnClicks()


       }
    }


    private fun observeData(){
        val pb=CustomProgressBar.progressDialog(requireContext())
        viewModel.apply {
            cartData.observe(viewLifecycleOwner){
                when(it.status){
                    Status.LOADING->{
                        pb.show()

                    }
                    Status.SUCCESS->{
                        pb.cancel()
                        it.data?.let {
                            cartMainModel=it
                            updateUI(cartMainModel.carts.get(0))
                            adapter.updateAdapter(it.carts.get(0).products)
                        }
                    }
                    Status.ERROR->{
                        pb.cancel()
                        errorToast(requireActivity(),it.message?:"Error")
                    }
                }
            }

            updatedCartData.observe(viewLifecycleOwner){
                when(it.status){
                    Status.LOADING->{
                        binding.linearProgressIndicator.visible()
                    }
                    Status.SUCCESS->{
                        binding.linearProgressIndicator.gone()

                        it.data?.let {
                            adapter.updateAdapter(it.products)
                            updateUI(it)
                        }
                    }
                    Status.ERROR->{
                        binding.linearProgressIndicator.gone()
                        errorToast(requireActivity(),it.message?:"Error")
                    }
                }
            }

        }
    }


    private fun setAdapterOnClicks(){
        var job: Job? =null
        adapter.apply {
            onClickDecreaseButton={
                val itemNumber=it.quantity
                if(it.quantity<=1){
                    infoToast(requireActivity(),resources.getString(R.string.minItemCount))
                }else{
                    job?.cancel()
                    job = lifecycleScope.launch {
                        delay(300)
                        val updateCartBody= UpdateCartBody(
                            true,
                            listOf(
                                UpdateProduct(
                                id=it.id,
                                price=it.price,
                                quantity = itemNumber-1,
                            )
                            )
                        )
                        viewModel.updateUserCart(updateCartBody,cartMainModel.carts.get(0).id)
                    }

                }
            }

            onClickDeleteButton={

            }

            onClickIncreaseButton={
                 job?.cancel()
                 job = lifecycleScope.launch {
                    delay(300)
                    val updateCartBody= UpdateCartBody(
                        true,
                        listOf(
                            UpdateProduct(
                            id=it.id,
                            price=it.price,
                            quantity = it.quantity+1,
                        )
                        )
                    )
                    viewModel.updateUserCart(updateCartBody,cartMainModel.carts.get(0).id)
                }

            }

            onClickItemListener={
                findNavController().navigate(CartFragmentDirections.actionCartFragmentToProductFragment(it.id))
            }

        }
    }

    private fun updateUI(data: CartModel){
        binding.apply {
            cartModel=data
        }
    }

}