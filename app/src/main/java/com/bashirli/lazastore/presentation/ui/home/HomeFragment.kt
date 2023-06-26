package com.bashirli.lazastore.presentation.ui.home

import android.view.Gravity
import androidx.core.view.GravityCompat
import androidx.fragment.app.viewModels
import com.bashirli.lazastore.common.base.BaseFragment
import com.bashirli.lazastore.common.util.CustomProgressBar
import com.bashirli.lazastore.common.util.Status
import com.bashirli.lazastore.common.util.errorToast
import com.bashirli.lazastore.databinding.FragmentHomeBinding
import com.bashirli.lazastore.databinding.HeaderLayoutBinding
import com.bashirli.lazastore.domain.model.UserModel
import com.bashirli.lazastore.presentation.ui.home.adapter.CategoryAdapter
import com.bashirli.lazastore.presentation.ui.home.adapter.ProductAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel by viewModels<HomeMVVM>()

    private val adapterCategory= CategoryAdapter()
    private val adapterProduct=ProductAdapter()

    private lateinit var currentUserData:UserModel

    override fun onViewCreateFinished() {
    observeData()
    }

    override fun setup() {
        binding.apply {
            rvCategory.adapter=adapterCategory
            rvProduct.adapter=adapterProduct

            buttonMenu.setOnClickListener {
                drawerLayout.openDrawer(GravityCompat.START)
            }


        }
    }

    private fun observeData(){
        val pb=CustomProgressBar.progressDialog(requireContext())
        with(viewModel){
            productData.observe(viewLifecycleOwner){
                when(it.status){
                    Status.ERROR->{
                        pb.cancel()
                        errorToast(requireActivity(),it.message?:"Error")
                    }
                    Status.SUCCESS->{
                        pb.cancel()
                        it.data?.let {
                            adapterProduct.updateList(it)
                        }
                    }
                    Status.LOADING->{
                        pb.show()
                    }
                }
            }

            categoryData.observe(viewLifecycleOwner){
                when(it.status){
                    Status.ERROR->{
                        pb.cancel()
                        errorToast(requireActivity(),it.message?:"Error")
                    }
                    Status.SUCCESS->{
                        pb.cancel()
                        it.data?.let {
                            adapterCategory.updateList(it)
                        }
                    }
                    Status.LOADING->{
                        pb.show()
                    }
                }
            }

            currentUser.observe(viewLifecycleOwner){
                when(it.status){
                    Status.ERROR->{
                        pb.cancel()
                        errorToast(requireActivity(),it.message?:"Error")
                    }
                    Status.SUCCESS->{
                        pb.cancel()
                        it.data?.let {
                            setUserData(it)
                        }
                    }
                    Status.LOADING->{
                        pb.show()
                    }
                }
            }

        }
    }

    private fun setUserData(data:UserModel){
        currentUserData=data
        val headerView =  binding.navigationView.getHeaderView(0)
        val headerBinding=HeaderLayoutBinding.bind(headerView)

        headerBinding.apply {
            userModel=currentUserData
        }

    }

}