package com.bashirli.lazastore.presentation.ui.welcome

import androidx.navigation.fragment.findNavController
import com.bashirli.lazastore.common.base.BaseFragment
import com.bashirli.lazastore.databinding.FragmentWelcomeBinding

class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>(FragmentWelcomeBinding::inflate) {
    override fun onViewCreateFinished() {

    }

    override fun setup() {
        binding.buttonStart.setOnClickListener {
            findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToLoginFragment())
        }
    }


}