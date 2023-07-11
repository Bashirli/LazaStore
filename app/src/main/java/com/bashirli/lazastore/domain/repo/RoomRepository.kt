package com.bashirli.lazastore.domain.repo

import com.bashirli.lazastore.common.util.Resource
import com.bashirli.lazastore.data.dto.local.FavoritesDTO
import com.bashirli.lazastore.data.dto.remote.product.ProductDTO
import com.bashirli.lazastore.domain.model.local.FavoritesModel
import com.bashirli.lazastore.domain.model.remote.ProductModel
import kotlinx.coroutines.flow.Flow

interface RoomRepository {

    suspend fun insertFavoriteItem(favoritesDTO: FavoritesDTO)


    suspend fun deleteFavoriteItem(favoritesDTO: FavoritesDTO)


    suspend fun getAllFavorites(): Flow<Resource<List<FavoritesModel>>>

    suspend fun getSingleFavoritesProduct(id:Int): Flow<Resource<List<FavoritesModel>>>


}