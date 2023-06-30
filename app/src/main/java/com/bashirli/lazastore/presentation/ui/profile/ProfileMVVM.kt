package com.bashirli.lazastore.presentation.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bashirli.lazastore.common.util.Resource
import com.bashirli.lazastore.domain.model.MainProductModel
import com.bashirli.lazastore.domain.model.ProfileModel
import com.bashirli.lazastore.domain.use_case.GetCurrentProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileMVVM @Inject constructor(
    private val getCurrentProfileUseCase: GetCurrentProfileUseCase
) :ViewModel() {

    private val _profileData= MutableLiveData<Resource<ProfileModel>>()
    val profileData: LiveData<Resource<ProfileModel>> get()=_profileData


    fun getCurrentProfile(){
        viewModelScope.launch {
            getCurrentProfileUseCase().collectLatest {
                _profileData.value=it
            }
        }
    }

}