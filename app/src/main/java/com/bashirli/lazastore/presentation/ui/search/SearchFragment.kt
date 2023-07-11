package com.bashirli.lazastore.presentation.ui.search

import android.transition.TransitionInflater
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bashirli.lazastore.common.base.BaseFragment
import com.bashirli.lazastore.common.util.CustomProgressBar
import com.bashirli.lazastore.common.util.Status
import com.bashirli.lazastore.common.util.errorToast
import com.bashirli.lazastore.common.util.gone
import com.bashirli.lazastore.common.util.visible
import com.bashirli.lazastore.databinding.FragmentSearchBinding
import com.bashirli.lazastore.domain.model.remote.MainProductModel
import com.bashirli.lazastore.presentation.ui.home.adapter.ProductAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val viewModel by viewModels<SearchMVVM>()
    private val adapter=ProductAdapter()


    override fun onViewCreateFinished() {
        setupAnimationEnterExit()
        observeData()
    }

    override fun setup() {
        var searchJob: Job? =null
        binding.apply {

        rvProducts.adapter=adapter
        buttonGoBack.setOnClickListener {
            findNavController().popBackStack()
        }

        editText.addTextChangedListener {
            searchJob?.cancel()
            searchJob = lifecycleScope.launch {
                    delay(300)
                    val text=it.toString()
                    if(text.isNotEmpty()){
                        viewModel.searchProduct(text)
                    }
                }

        }

        adapter.onProductClickListener={
            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToProductFragment(it.id))
        }

    }

    }

    private fun observeData(){
        viewModel.apply {
            val pb=CustomProgressBar.progressDialog(requireContext())
            productData.observe(viewLifecycleOwner){
                when(it.status){
                    Status.SUCCESS->{
                        pb.cancel()
                        it.data?.let {
                            updateUI(it)
                        }
                        showRV()
                    }
                    Status.ERROR->{
                        showNothing()
                        pb.cancel()
                        errorToast(requireActivity(),it.message?:"Error")
                    }
                    Status.LOADING->{
                        showNothing()
                        pb.show()
                    }
                }
            }

            searchData.observe(viewLifecycleOwner){
                when(it.status){
                    Status.SUCCESS->{
                        binding.progressCircular.gone()
                        it.data?.let {
                            updateUI(it)

                        }
                    }
                    Status.ERROR->{
                        binding.progressCircular.gone()
                        binding.layoutSearchResult.gone()
                        errorToast(requireActivity(),it.message?:"Error")
                    }
                    Status.LOADING->{
                        binding.progressCircular.visible()
                        binding.layoutSearchResult.gone()
                    }
                    }
                }

        }
    }

    private fun updateUI(data: MainProductModel){
        binding.productData=data
        if(data.products.isEmpty()){
            binding.layoutEmpty.visible()
            binding.layoutSearchResult.gone()
        }else{
            binding.layoutEmpty.gone()
            binding.layoutSearchResult.visible()
            adapter.updateList(data.products)
        }

    }


    private fun setupAnimationEnterExit(){
        val anim=TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition=anim
        sharedElementReturnTransition=anim
    }

    private fun showRV(){
        binding.apply {
            layoutEmpty.gone()
            layoutSearchResult.visible()
        }
    }

    private fun showEmpty(){
        binding.apply {
            layoutEmpty.visible()
            layoutSearchResult.gone()
        }
    }

    private fun showNothing(){
        binding.apply {
            layoutEmpty.gone()
            layoutSearchResult.gone()
        }
    }

}