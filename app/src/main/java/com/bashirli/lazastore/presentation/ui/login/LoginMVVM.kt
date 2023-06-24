package com.bashirli.lazastore.presentation.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bashirli.lazastore.common.util.Resource
import com.bashirli.lazastore.domain.model.AuthModel
import com.bashirli.lazastore.domain.use_case.LoginUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginMVVM @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase
) : ViewModel() {

    private val _loginData=MutableLiveData<Resource<AuthModel>>()
    val loginData:LiveData<Resource<AuthModel>> get()=_loginData

    fun loginUser(email:String,password:String){
       viewModelScope.launch {
           loginUserUseCase(email, password)
               .collectLatest {
            _loginData.value=it
           }
       }
    }

}