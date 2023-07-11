package com.bashirli.lazastore.data.service.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bashirli.lazastore.data.dto.local.FavoritesDTO
import com.bashirli.lazastore.data.dto.remote.product.Product
import com.bashirli.lazastore.data.dto.remote.product.ProductDTO

@Dao
interface RoomDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteItem(favoritesDTO: FavoritesDTO)

    @Delete
    suspend fun deleteFavoriteItem(favoritesDTO: FavoritesDTO)

    @Query("select * from favoriteProductTable")
    suspend fun getAllFavorites():List<FavoritesDTO>

    @Query("select * from favoriteProductTable where id=:id")
    suspend fun getSingleFavoriteProduct(id:Int):List<FavoritesDTO>



}