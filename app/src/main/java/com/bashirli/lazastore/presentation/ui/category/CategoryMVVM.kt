package com.bashirli.lazastore.presentation.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bashirli.lazastore.common.util.Resource
import com.bashirli.lazastore.domain.model.ProductModel
import com.bashirli.lazastore.domain.use_case.GetCategoryProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryMVVM @Inject constructor(
    private val getCategoryProductsUseCase: GetCategoryProductsUseCase
) :ViewModel(){

    private val _categoryProductsResponse=MutableLiveData<Resource<List<ProductModel>>>()
    val categoryProductsResponse:LiveData<Resource<List<ProductModel>>> get()=_categoryProductsResponse

    fun getCategoryProducts(id:Int){
        viewModelScope.launch {
            getCategoryProductsUseCase(id).collectLatest {
                _categoryProductsResponse.value=it
            }
        }
    }

}