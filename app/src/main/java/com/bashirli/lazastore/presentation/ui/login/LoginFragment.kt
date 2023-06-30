package com.bashirli.lazastore.presentation.ui.login

import android.transition.TransitionInflater
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.bashirli.lazastore.common.util.CustomProgressBar
import com.bashirli.lazastore.R
import com.bashirli.lazastore.common.base.BaseFragment
import com.bashirli.lazastore.databinding.FragmentLoginBinding
import com.bashirli.lazastore.common.util.errorToast
import com.bashirli.lazastore.common.TokenManager
import com.bashirli.lazastore.common.util.Status
import com.bashirli.lazastore.common.util.successToast
import com.bashirli.lazastore.domain.model.AuthModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    @Inject
    lateinit var tokenManager: TokenManager

    private val viewModel by viewModels<LoginMVVM>()

    override fun onViewCreateFinished() {
    observeData()
    }

    private fun observeData(){
        val pb= CustomProgressBar.progressDialog(requireContext())
        viewModel.apply {
            loginData.observe(viewLifecycleOwner){
                when(it.status){
                    Status.ERROR->{
                        pb.cancel()
                        errorToast(requireActivity(),it.message?:"Error")
                    }
                    Status.SUCCESS->{
                        pb.cancel()
                        successToast(requireActivity(),resources.getString(R.string.welcome))
                        it.data?.let {
                            setData(it)
                        }
                    }
                    Status.LOADING->{
                    pb.show()
                    }

                }
            }
        }
    }

    override fun setup() {
        val anim=TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition=anim
        sharedElementReturnTransition=anim


    binding.apply {
        buttonRegister.setOnClickListener {
            val extras= FragmentNavigatorExtras(
                buttonRegister to "buttonRegisterTransition",
                buttonLogin to "loginTransition"
            )
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment(),extras)
        }
        buttonLogin.setOnClickListener {
            login()
        }
    }

    }


    private fun login(){
        val username=binding.editUsername.text.toString()
        val password=binding.editPass.text.toString()

        if(errorCheck(username.trim(),password.trim())){
            return
        }

        viewModel.loginUser(username,password)

    }

    private fun errorCheck(username:String,password:String):Boolean{
        if(username.isEmpty()||password.isEmpty()){
            errorToast(requireActivity(),resources.getString(R.string.fillTheGaps))
            return true
        }
        if(password.length<5){
            errorToast(requireActivity(),resources.getString(R.string.passShort))
            return true
        }
        return false
    }

    private fun setData(data:AuthModel){
        val token=data.accessToken
        tokenManager.setToken(token)
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
    }


}