package com.bashirli.lazastore.presentation.ui.splash

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bashirli.lazastore.common.base.BaseFragment
import com.bashirli.lazastore.databinding.FragmentSplashBinding
import com.bashirli.lazastore.common.TokenManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(
    FragmentSplashBinding::inflate
) {

    @Inject
    lateinit var tokenManager: TokenManager

    override fun onViewCreateFinished() {

    }

    override fun setup() {
        val token=tokenManager.getToken()
        println(token)
        lifecycleScope.launch {
            delay(1300)
            if(token!=null){
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
            }else{
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToWelcomeFragment())
            }

        }
    }


}