package com.bashirli.lazastore.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bashirli.lazastore.common.util.Resource
import com.bashirli.lazastore.domain.model.CategoryModel
import com.bashirli.lazastore.domain.model.MainProductModel
import com.bashirli.lazastore.domain.model.ProductModel
import com.bashirli.lazastore.domain.model.UserModel
import com.bashirli.lazastore.domain.use_case.GetCategoriesUseCase
import com.bashirli.lazastore.domain.use_case.GetCurrentUserUseCase
import com.bashirli.lazastore.domain.use_case.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeMVVM @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {

    private val _productData=MutableLiveData<Resource<MainProductModel>>()
    val productData:LiveData<Resource<MainProductModel>> get()=_productData

    private val _categoryData=MutableLiveData<Resource<List<CategoryModel>>>()
    val categoryData:LiveData<Resource<List<CategoryModel>>> get()=_categoryData

    private val _currentUser=MutableLiveData<Resource<UserModel>>()
    val currentUser:LiveData<Resource<UserModel>> get()=_currentUser

    init {
        getCurrentUser()
        getProducts()
        getCategories()
    }

    private fun getCurrentUser(){
        viewModelScope.launch {
            getCurrentUserUseCase().collectLatest {
                _currentUser.value=it
            }
        }
    }

    private fun getProducts(){
        viewModelScope.launch {
            getProductsUseCase().collectLatest {
                _productData.postValue(it)
            }
        }
    }

    private fun getCategories(){
        viewModelScope.launch {
            getCategoriesUseCase().collectLatest {
                _categoryData.value=it
            }
        }
    }


}