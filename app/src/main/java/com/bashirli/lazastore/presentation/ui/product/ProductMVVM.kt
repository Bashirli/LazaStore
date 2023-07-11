package com.bashirli.lazastore.presentation.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bashirli.lazastore.common.util.Resource
import com.bashirli.lazastore.data.dto.local.FavoritesDTO
import com.bashirli.lazastore.domain.model.local.FavoritesModel
import com.bashirli.lazastore.domain.model.remote.SingleProductModel
import com.bashirli.lazastore.domain.use_case.AddFavoritesUseCase
import com.bashirli.lazastore.domain.use_case.DeleteFavoritesUseCase
import com.bashirli.lazastore.domain.use_case.GetSingleFavoriteProductUseCase
import com.bashirli.lazastore.domain.use_case.GetSingleProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductMVVM @Inject constructor(
    private val getSingleProductUseCase: GetSingleProductUseCase,
    private val getSingleFavoriteProductUseCase: GetSingleFavoriteProductUseCase,
    private val addFavoritesUseCase: AddFavoritesUseCase,
    private val deleteFavoritesUseCase: DeleteFavoritesUseCase
) : ViewModel() {

    private val _productData=MutableLiveData<Resource<SingleProductModel>>()
    val productData : LiveData<Resource<SingleProductModel>> get()=_productData


    private val _favoriteData=MutableLiveData<Resource<List<FavoritesModel>>>()
    val favoriteData : LiveData<Resource<List<FavoritesModel>>> get()=_favoriteData



    fun getSingleProduct(id:Int){
        viewModelScope.launch {
            getSingleProductUseCase(id).collectLatest {
                _productData.value=it
            }
        }
    }

     fun getSingleFavoriteProduct(id:Int){
        viewModelScope.launch {
            getSingleFavoriteProductUseCase(id).collectLatest {
                _favoriteData.value=it
            }
        }
    }

    fun addItemToFavorites(favoritesDTO: FavoritesDTO){
        viewModelScope.launch {
            addFavoritesUseCase(favoritesDTO)
        }
    }

    fun deleteItemFromFavorites(favoritesDTO: FavoritesDTO){
        viewModelScope.launch {
            deleteFavoritesUseCase(favoritesDTO)
        }
    }


}