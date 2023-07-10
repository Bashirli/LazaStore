package com.bashirli.lazastore.presentation.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bashirli.lazastore.common.util.Resource
import com.bashirli.lazastore.domain.model.MainProductModel
import com.bashirli.lazastore.domain.model.ProductModel
import com.bashirli.lazastore.domain.use_case.GetProductsUseCase
import com.bashirli.lazastore.domain.use_case.GetSearchProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchMVVM @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getSearchProductsUseCase: GetSearchProductsUseCase
): ViewModel() {

    private val _productData=MutableLiveData<Resource<MainProductModel>>()
    val productData:LiveData<Resource<MainProductModel>> get()=_productData

    private val _searchData=MutableLiveData<Resource<MainProductModel>>()
    val searchData:LiveData<Resource<MainProductModel>> get()=_searchData

    init {
        getInitialData()
    }

    private fun getInitialData(){
        viewModelScope.launch {
            getProductsUseCase().collectLatest {
                _productData.value=it
            }
        }
    }

    fun searchProduct(searchText:String){
        viewModelScope.launch {
            getSearchProductsUseCase(searchText).collectLatest {
                _searchData.value=it
            }
        }
    }

}
