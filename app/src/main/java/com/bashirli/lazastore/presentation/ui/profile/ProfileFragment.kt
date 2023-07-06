package com.bashirli.lazastore.presentation.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bashirli.lazastore.R
import com.bashirli.lazastore.common.TokenManager
import com.bashirli.lazastore.common.base.BaseFragment
import com.bashirli.lazastore.common.util.CustomProgressBar
import com.bashirli.lazastore.common.util.Status
import com.bashirli.lazastore.common.util.errorToast
import com.bashirli.lazastore.common.util.reset
import com.bashirli.lazastore.databinding.FragmentProfileBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {


    private val viewModel by viewModels<ProfileMVVM>()

    @Inject
    lateinit var tokenManager: TokenManager

    override fun onViewCreateFinished() {
    observeData()
    }

    override fun setup() {
        viewModel.getCurrentProfile()
        binding.apply {
            buttonGoBack.setOnClickListener {
                findNavController().popBackStack()
            }
            buttonSignOut.setOnClickListener {
                signOut()
            }

        }
    }

    private fun observeData(){
        viewModel.apply {
            val pb=CustomProgressBar.progressDialog(requireContext())
            profileData.observe(viewLifecycleOwner){
                when(it.status){
                    Status.SUCCESS->{
                        pb.cancel()
                        binding.profileData=it.data
                    }
                    Status.LOADING->{
                        pb.show()
                    }
                    Status.ERROR->{
                        pb.cancel()
                        val alert=MaterialAlertDialogBuilder(requireContext())
                        alert.setCancelable(false).setTitle(R.string.error).setMessage(it.message?:"Error")
                            .setPositiveButton(R.string.ok){dialog,which->
                                findNavController().popBackStack()
                            }.create().show()

                    }
                }
            }
        }
    }

    private fun signOut(){
        tokenManager.removeToken()
        requireActivity().reset()
    }


}