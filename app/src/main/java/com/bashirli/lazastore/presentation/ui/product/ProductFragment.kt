package com.bashirli.lazastore.presentation.ui.product

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bashirli.lazastore.R
import com.bashirli.lazastore.common.base.BaseFragment
import com.bashirli.lazastore.common.util.CustomProgressBar
import com.bashirli.lazastore.common.util.Status
import com.bashirli.lazastore.data.dto.local.FavoritesDTO
import com.bashirli.lazastore.databinding.FragmentProductBinding
import com.bashirli.lazastore.domain.model.local.FavoritesModel
import com.bashirli.lazastore.domain.model.remote.SingleProductModel
import com.denzcoskun.imageslider.constants.AnimationTypes
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductFragment : BaseFragment<FragmentProductBinding>(FragmentProductBinding::inflate) {

    private val args by navArgs<ProductFragmentArgs>()
    private val viewModel by viewModels<ProductMVVM>()
    private lateinit var singleProductModel: SingleProductModel
    private var isFav=false

    override fun onViewCreateFinished() {
    observeData()
    }

    override fun setup() {
        viewModel.getSingleProduct(args.productId)
        viewModel.getSingleFavoriteProduct(args.productId)
        binding.apply {
            buttonGoBack.setOnClickListener {
                findNavController().popBackStack()
            }
            buttonFav.setOnClickListener {
                val data=FavoritesDTO(
                    singleProductModel.id,
                    singleProductModel.title,
                    singleProductModel.images.get(0),
                    singleProductModel.description,
                    singleProductModel.price.toString()
                )
                if(isFav){
                    viewModel.deleteItemFromFavorites(data)
                    buttonFav.setIconResource(R.drawable.heart_ico)
                }else{
                    viewModel.addItemToFavorites(data)
                    buttonFav.setIconResource(R.drawable.heart_selected_ico)
                }
                isFav=!isFav
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
            favoriteData.observe(viewLifecycleOwner){
                when(it.status){
                    Status.ERROR->{
                        val alert=MaterialAlertDialogBuilder(requireContext())
                        alert.setCancelable(false)
                            .setTitle(R.string.error)
                            .setMessage(it.message?:"Error")
                            .setPositiveButton(R.string.ok){dialog,which->
                                findNavController().popBackStack()
                            }.create().show()
                    }
                    Status.SUCCESS->{
                        it.data?.let {
                            checkIsOnFav(it)
                        }
                    }
                    Status.LOADING->{

                    }
                }
            }
        }
    }

    private fun setData(data: SingleProductModel){
        singleProductModel=data
        binding.apply {
            productData=data

            val imageList=ArrayList<SlideModel>()
            data.images.forEach {
                imageList.add(SlideModel(it))
            }
            imageSlider.setImageList(imageList,ScaleTypes.CENTER_INSIDE)
            imageSlider.setSlideAnimation(AnimationTypes.FIDGET_SPINNER)
        }
    }

    private fun checkIsOnFav(list:List<FavoritesModel>){
        isFav = list.isNotEmpty()
        if(isFav){
            binding.buttonFav.setIconResource(R.drawable.heart_selected_ico)
        }else{
            binding.buttonFav.setIconResource(R.drawable.heart_ico)
        }
    }

}