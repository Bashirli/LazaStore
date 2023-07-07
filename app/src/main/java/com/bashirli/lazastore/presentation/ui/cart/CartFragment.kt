package com.bashirli.lazastore.presentation.ui.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bashirli.lazastore.R
import com.bashirli.lazastore.common.base.BaseFragment
import com.bashirli.lazastore.common.util.CustomProgressBar
import com.bashirli.lazastore.common.util.Status
import com.bashirli.lazastore.common.util.gone
import com.bashirli.lazastore.common.util.visible
import com.bashirli.lazastore.databinding.FragmentCartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : BaseFragment<FragmentCartBinding>(FragmentCartBinding::inflate) {

    private val viewModel by viewModels<CartMVVM>()
    private val adapter=CartAdapter()
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
                            println(it.carts.get(0).products)
                            adapter.updateAdapter(it.carts.get(0).products)
                        }
                    }
                    Status.ERROR->{
                        pb.cancel()

                    }
                }
            }
        }
    }

}