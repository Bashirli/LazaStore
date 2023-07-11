package com.bashirli.lazastore.presentation.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bashirli.lazastore.common.util.Resource
import com.bashirli.lazastore.data.dto.local.FavoritesDTO
import com.bashirli.lazastore.domain.model.local.FavoritesModel
import com.bashirli.lazastore.domain.use_case.AddFavoritesUseCase
import com.bashirli.lazastore.domain.use_case.DeleteFavoritesUseCase
import com.bashirli.lazastore.domain.use_case.GetFavoriteProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesMVVM @Inject constructor(
       private val addFavoritesUseCase: AddFavoritesUseCase,
       private val deleteFavoritesUseCase: DeleteFavoritesUseCase,
       private val getFavoriteProductsUseCase: GetFavoriteProductsUseCase
) : ViewModel() {

    private val _favoriteProducts=MutableLiveData<Resource<List<FavoritesModel>>>()
    val favoriteProducts:LiveData<Resource<List<FavoritesModel>>> = _favoriteProducts

    init {
        getFavoritesProduct()
    }

    private fun getFavoritesProduct() {
    viewModelScope.launch {
        getFavoriteProductsUseCase().collectLatest {
            _favoriteProducts.value=it
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