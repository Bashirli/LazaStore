package com.bashirli.lazastore.presentation.ui.product

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bashirli.lazastore.R
import com.bashirli.lazastore.common.base.BaseFragment
import com.bashirli.lazastore.common.util.CustomProgressBar
import com.bashirli.lazastore.common.util.Status
import com.bashirli.lazastore.databinding.FragmentProductBinding
import com.bashirli.lazastore.domain.model.SingleProductModel
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ouattararomuald.slider.SliderAdapter
import com.ouattararomuald.slider.loaders.glide.GlideImageLoaderFactory
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Error

@AndroidEntryPoint
class ProductFragment : BaseFragment<FragmentProductBinding>(FragmentProductBinding::inflate) {

    private val args by navArgs<ProductFragmentArgs>()
    private val viewModel by viewModels<ProductMVVM>()

    override fun onViewCreateFinished() {
    observeData()
    }

    override fun setup() {
        viewModel.getSingleProduct(args.productId)
        binding.apply {
            buttonGoBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun observeData(){
        viewModel.apply {
            val pb=CustomProgressBar.progressDialog(requireContext())
            productData.observe(viewLifecycleOwner){
                when(it.status){
                    Status.ERROR->{
                        pb.cancel()
                        val alert=MaterialAlertDialogBuilder(requireContext())
                        alert.setCancelable(false)
                            .setTitle(R.string.error)
                            .setMessage(it.message?:"Error")
                            .setPositiveButton(R.string.ok){dialog,which->
                                findNavController().popBackStack()
                            }.create().show()
                    }
                    Status.SUCCESS->{
                        pb.cancel()
                        it.data?.let {
                            setData(it)
                        }
                    }
                    Status.LOADING->{
                        pb.show()
                    }
                }
            }
        }
    }

    private fun setData(data:SingleProductModel){
        binding.apply {
            productData=data
            imageSlider.adapter= SliderAdapter(
                requireContext(),
                GlideImageLoaderFactory(),
                imageUrls = data.images
            )
        }
    }

}