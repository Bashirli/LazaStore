package com.bashirli.lazastore.presentation.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bashirli.lazastore.common.util.Resource
import com.bashirli.lazastore.domain.model.cart.CartMainModel
import com.bashirli.lazastore.domain.repo.ApiRepository
import com.bashirli.lazastore.domain.use_case.GetCurrentProfileUseCase
import com.bashirli.lazastore.domain.use_case.GetCurrentUserCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartMVVM @Inject constructor(
    private val getCurrentUserCartUseCase: GetCurrentUserCartUseCase
) : ViewModel() {

    private val _cartData=MutableLiveData<Resource<CartMainModel>>()
    val cartData:LiveData<Resource<CartMainModel>> get()=_cartData

    init {
        getData()
    }

    private fun getData(){
        viewModelScope.launch {
            getCurrentUserCartUseCase().collectLatest {
                _cartData.value=it
            }
        }
    }

}