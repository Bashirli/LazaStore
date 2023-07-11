package com.bashirli.lazastore.presentation.ui.category

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bashirli.lazastore.common.base.BaseFragment
import com.bashirli.lazastore.common.util.CustomProgressBar
import com.bashirli.lazastore.common.util.Status
import com.bashirli.lazastore.common.util.errorToast
import com.bashirli.lazastore.databinding.FragmentCategoryBinding
import com.bashirli.lazastore.domain.model.remote.CategoryModel
import com.bashirli.lazastore.domain.model.remote.MainProductModel
import com.bashirli.lazastore.presentation.ui.home.adapter.ProductAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentCategoryBinding>(FragmentCategoryBinding::inflate) {

    private val args by navArgs<CategoryFragmentArgs>()
    private lateinit var categoryModel: CategoryModel
    private val viewModel by viewModels<CategoryMVVM>()
    private val adapter=ProductAdapter()

    override fun onViewCreateFinished() {
    observeData()
    }

    override fun setup() {

        viewModel.getCategoryProducts(args.categoryName)
        binding.apply {
            rvProduct.adapter=adapter

            adapter.onProductClickListener={
                findNavController().navigate(CategoryFragmentDirections.actionCategoryFragmentToProductFragment(it.id))
            }

            buttonGoBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun observeData(){
        viewModel.apply {
        val pb=CustomProgressBar.progressDialog(requireContext())
            categoryProductsResponse.observe(viewLifecycleOwner){
                when(it.status){
                    Status.SUCCESS->{
                        pb.cancel()
                        it.data?.let {
                            if(it.products.isEmpty()){
                                binding.rvProduct.visibility=View.GONE
                                binding.layoutEmpty.visibility= View.VISIBLE
                            }else{
                                binding.layoutEmpty.visibility= View.GONE
                                binding.rvProduct.visibility=View.VISIBLE
                                setData(it)
                            }
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

    private fun setData(data: MainProductModel){
        adapter.updateList(data.products)
        binding.productData=data
    }

}