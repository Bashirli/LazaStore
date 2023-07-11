package com.bashirli.lazastore.data.repo

import com.bashirli.lazastore.common.util.Resource
import com.bashirli.lazastore.common.util.Status
import com.bashirli.lazastore.data.dto.local.FavoritesDTO
import com.bashirli.lazastore.data.mapper.toFavoriteModel
import com.bashirli.lazastore.data.source.RoomSource
import com.bashirli.lazastore.domain.model.local.FavoritesModel
import com.bashirli.lazastore.domain.model.remote.ProductModel
import com.bashirli.lazastore.domain.repo.RoomRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RoomRepoImpl @Inject constructor(private val roomSource:RoomSource) : RoomRepository {
    override suspend fun insertFavoriteItem(favoritesDTO: FavoritesDTO) {
        roomSource.insertFavoriteItem(favoritesDTO)
    }

    override suspend fun deleteFavoriteItem(favoritesDTO: FavoritesDTO) {
        roomSource.deleteFavoriteItem(favoritesDTO)
    }

    override suspend fun getAllFavorites():Flow<Resource<List<FavoritesModel>>> = flow {
        emit(Resource.loading(null))
        val data=roomSource.getAllFavorites()
        when(data.status){
            Status.ERROR->{
                emit(Resource.error(data.message?:"Error",null))
            }
            Status.SUCCESS->{
                emit(Resource.success(data.data?.toFavoriteModel()))
            }
            else->{}
        }


    }

    override suspend fun getSingleFavoritesProduct(id: Int): Flow<Resource<List<FavoritesModel>>> = flow {
        emit(Resource.loading(null))
        val data=roomSource.getSingleFavoriteProduct(id)
        when(data.status){
            Status.ERROR->{
                emit(Resource.error(data.message?:"Error",null))
            }
            Status.SUCCESS->{
                emit(Resource.success(data.data?.toFavoriteModel()))
            }
            else->{}
        }


    }
}