package com.bashirli.lazastore.presentation.ui.home

import android.util.Log
import androidx.fragment.app.viewModels
import com.bashirli.lazastore.common.base.BaseFragment
import com.bashirli.lazastore.common.util.CustomProgressBar
import com.bashirli.lazastore.common.util.Status
import com.bashirli.lazastore.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel by viewModels<HomeMVVM>()

    override fun onViewCreateFinished() {
    observeData()
    }

    override fun setup() {

    }

    private fun observeData(){
        val pb=CustomProgressBar.progressDialog(requireContext())
        with(viewModel){
            productData.observe(viewLifecycleOwner){
                when(it.status){
                    Status.ERROR->{
                        Log.d("error",it.message.toString())
                    }
                    Status.SUCCESS->{
                        pb.cancel()
                        Log.d("data",it.data.toString())
                    }
                    Status.LOADING->{
                        pb.show()
                    }
                }
            }
        }
    }

}