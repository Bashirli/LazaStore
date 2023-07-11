package com.bashirli.lazastore.presentation.ui.register

import android.transition.TransitionInflater
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bashirli.lazastore.common.util.CustomProgressBar
import com.bashirli.lazastore.R
import com.bashirli.lazastore.common.base.BaseFragment
import com.bashirli.lazastore.common.util.Status
import com.bashirli.lazastore.databinding.FragmentSignUpBinding
import com.bashirli.lazastore.common.util.errorToast
import com.bashirli.lazastore.common.util.successToast
import com.bashirli.lazastore.domain.model.remote.RegisterPostModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding>(FragmentSignUpBinding::inflate) {

    private val viewModel by viewModels<SignUpMVVM>()

    override fun onViewCreateFinished() {
    observeData()
    }

    override fun setup() {
        val anim= TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition=anim
        sharedElementReturnTransition=anim
        binding.apply {
            buttonLogin.setOnClickListener {
                findNavController().popBackStack()
            }
            buttonRegister.setOnClickListener {
                register()
            }
        }

    }

    private fun register(){
        val email=binding.editEmail.text.toString()
        val password=binding.editPass.text.toString()
        val nickname=binding.editNick.text.toString()

        if(errorCheck(email.trim(),password.trim(),nickname.trim())){
            return
        }

        viewModel.register(RegisterPostModel(nickname,email,password))

    }
    private fun observeData(){
        val pb= CustomProgressBar.progressDialog(requireContext())
        with(viewModel){
            registerData.observe(viewLifecycleOwner){
                when(it.status){
                    Status.SUCCESS->{
                        pb.cancel()
                        successToast(requireActivity(),resources.getString(R.string.welcomeRegister))
                        findNavController().popBackStack()
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

    private fun errorCheck(email:String,password:String,nickname:String):Boolean{
        if(email.isEmpty()||password.isEmpty()||nickname.isEmpty()){
            errorToast(requireActivity(),resources.getString(R.string.fillTheGaps))
            return true
        }
        if(password.length<5){
            errorToast(requireActivity(),resources.getString(R.string.passShort))
            return true
        }
        return false
    }


}