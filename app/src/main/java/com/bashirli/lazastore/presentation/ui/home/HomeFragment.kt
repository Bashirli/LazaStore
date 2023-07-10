package com.bashirli.lazastore.presentation.ui.home

import android.transition.TransitionInflater
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.bashirli.lazastore.R
import com.bashirli.lazastore.common.base.BaseFragment
import com.bashirli.lazastore.common.util.CustomProgressBar
import com.bashirli.lazastore.common.util.Status
import com.bashirli.lazastore.common.util.errorToast
import com.bashirli.lazastore.databinding.FragmentHomeBinding
import com.bashirli.lazastore.databinding.HeaderLayoutBinding
import com.bashirli.lazastore.domain.model.UserModel
import com.bashirli.lazastore.presentation.ui.cart.CartFragment
import com.bashirli.lazastore.presentation.ui.home.adapter.CategoryAdapter
import com.bashirli.lazastore.presentation.ui.home.adapter.ProductAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel by viewModels<HomeMVVM>()
    private lateinit var navView:NavigationView

    private val adapterCategory= CategoryAdapter()
    private val adapterProduct=ProductAdapter()


    override fun onViewCreateFinished() {
        setupAnimationEnterExit()
        observeData()
    }

    override fun setup() {

        navView=requireActivity().findViewById(R.id.navigationView)

        binding.apply {
            rvCategory.adapter=adapterCategory
            rvProduct.adapter=adapterProduct

            buttonMenu.setOnClickListener {
                requireActivity().findViewById<DrawerLayout>(R.id.drawerLayout).openDrawer(Gravity.LEFT)
            }

            adapterCategory.onCategoryClickListener={
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToCategoryFragment(it.category))
            }

            adapterProduct.onProductClickListener={
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToProductFragment(it.id))
            }



            editText.setOnClickListener{
                val extras= FragmentNavigatorExtras(
                    textInputSearch to textInputSearch.transitionName,
                    editText to editText.transitionName
                    )
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment(),extras)
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
                            adapterProduct.updateList(it.products)
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
        }
    }


    private fun setupAnimationEnterExit(){
        val anim= TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition=anim
        sharedElementReturnTransition=anim
    }

}