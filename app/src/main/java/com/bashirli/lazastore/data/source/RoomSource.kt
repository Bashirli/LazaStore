package com.bashirli.lazastore.data.source

import com.bashirli.lazastore.common.util.Resource
import com.bashirli.lazastore.data.dto.local.FavoritesDTO
import com.bashirli.lazastore.data.dto.remote.product.ProductDTO

interface RoomSource {

    suspend fun insertFavoriteItem(favoritesDTO: FavoritesDTO)


    suspend fun deleteFavoriteItem(favoritesDTO: FavoritesDTO)


    suspend fun getAllFavorites(): Resource<List<FavoritesDTO>>

    suspend fun getSingleFavoriteProduct(id:Int):Resource<List<FavoritesDTO>>

}