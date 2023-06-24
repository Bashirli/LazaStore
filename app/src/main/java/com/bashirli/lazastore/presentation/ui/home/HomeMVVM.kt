package com.bashirli.lazastore.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bashirli.lazastore.common.util.Resource
import com.bashirli.lazastore.domain.model.ProductModel
import com.bashirli.lazastore.domain.use_case.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeMVVM @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _productData=MutableLiveData<Resource<List<ProductModel>>>()
    val productData:LiveData<Resource<List<ProductModel>>> get()=_productData

    init {
        getProducts()
    }

    private fun getProducts(){
        viewModelScope.launch {
            getProductsUseCase().collectLatest {
                _productData.value=it
            }
        }
    }

}