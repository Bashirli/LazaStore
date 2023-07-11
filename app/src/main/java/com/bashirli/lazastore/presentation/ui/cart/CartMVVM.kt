package com.bashirli.lazastore.presentation.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bashirli.lazastore.common.util.Resource
import com.bashirli.lazastore.domain.model.remote.body.UpdateCartBody
import com.bashirli.lazastore.domain.model.remote.cart.CartMainModel
import com.bashirli.lazastore.domain.model.remote.cart.CartModel
import com.bashirli.lazastore.domain.repo.ApiRepository
import com.bashirli.lazastore.domain.use_case.GetCurrentProfileUseCase
import com.bashirli.lazastore.domain.use_case.GetCurrentUserCartUseCase
import com.bashirli.lazastore.domain.use_case.UpdateUserCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartMVVM @Inject constructor(
    private val getCurrentUserCartUseCase: GetCurrentUserCartUseCase,
    private val updateUserCartUseCase: UpdateUserCartUseCase
) : ViewModel() {

    private val _cartData=MutableLiveData<Resource<CartMainModel>>()
    val cartData:LiveData<Resource<CartMainModel>> get()=_cartData

    private val _updatedCartData=MutableLiveData<Resource<CartModel>>()
    val updatedCartData:LiveData<Resource<CartModel>> get()=_updatedCartData


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

    fun updateUserCart(updateCartBody: UpdateCartBody, cartId:Int){
        viewModelScope.launch {
            updateUserCartUseCase(updateCartBody, cartId).collectLatest {
                _updatedCartData.value=it
            }
        }
    }

}