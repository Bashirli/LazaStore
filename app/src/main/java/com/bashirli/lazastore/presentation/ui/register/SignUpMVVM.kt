package com.bashirli.lazastore.presentation.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bashirli.lazastore.common.util.Resource
import com.bashirli.lazastore.domain.model.remote.RegisterModel
import com.bashirli.lazastore.domain.model.remote.RegisterPostModel
import com.bashirli.lazastore.domain.use_case.RegisterUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpMVVM @Inject  constructor(
    private val registerUseCase:RegisterUserUseCase
) : ViewModel() {

    private val _registerData=MutableLiveData<Resource<RegisterModel>>()
    val registerData:LiveData<Resource<RegisterModel>> get()=_registerData

    fun register(registerPostModel: RegisterPostModel){
        viewModelScope.launch {
            val response=registerUseCase.invoke(registerPostModel)

            response.collectLatest {
                _registerData.value=it
            }
        }
    }

}