package com.bashirli.lazastore.presentation.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bashirli.lazastore.common.util.Resource
import com.bashirli.lazastore.domain.model.UserModel
import com.bashirli.lazastore.domain.use_case.GetCurrentUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainMVVM @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase
): ViewModel() {

    private val _currentData= MutableLiveData<Resource<UserModel>>()
    val currentData:LiveData<Resource<UserModel>> = _currentData

    init {
        getCurrentUser()
    }

    private fun getCurrentUser(){
        viewModelScope.launch {
            getCurrentUserUseCase().collectLatest {
                _currentData.value=it
            }
        }
    }

}