package com.bashirli.lazastore.presentation.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bashirli.lazastore.common.util.Resource
import com.bashirli.lazastore.domain.model.SingleProductModel
import com.bashirli.lazastore.domain.use_case.GetSingleProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductMVVM @Inject constructor(
    private val getSingleProductUseCase: GetSingleProductUseCase
) : ViewModel() {

    private val _productData=MutableLiveData<Resource<SingleProductModel>>()
    val productData : LiveData<Resource<SingleProductModel>> get()=_productData

    fun getSingleProduct(id:Int){
        viewModelScope.launch {
            getSingleProductUseCase(id).collectLatest {
                _productData.value=it
            }
        }
    }

}