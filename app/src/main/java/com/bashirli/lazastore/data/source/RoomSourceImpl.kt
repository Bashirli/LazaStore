package com.bashirli.lazastore.data.source

import com.bashirli.lazastore.common.util.Resource
import com.bashirli.lazastore.data.dto.local.FavoritesDTO
import com.bashirli.lazastore.data.dto.remote.product.ProductDTO
import com.bashirli.lazastore.data.service.local.RoomDAO
import javax.inject.Inject

class RoomSourceImpl @Inject constructor(private val dao:RoomDAO) : RoomSource{
    override suspend fun insertFavoriteItem(favoritesDTO: FavoritesDTO) {
        dao.insertFavoriteItem(favoritesDTO)
    }

    override suspend fun deleteFavoriteItem(favoritesDTO: FavoritesDTO) {
        dao.deleteFavoriteItem(favoritesDTO)
    }

    override suspend fun getAllFavorites(): Resource<List<FavoritesDTO>> {
        return try {
            Resource.success(dao.getAllFavorites())
        }catch (e:Exception){
            Resource.error(e.localizedMessage,null)
        }
    }

    override suspend fun getSingleFavoriteProduct(id:Int): Resource<List<FavoritesDTO>> {
        return try {
            Resource.success(dao.getSingleFavoriteProduct(id))
        }catch (e:Exception){
            Resource.error(e.localizedMessage,null)
        }
    }
}