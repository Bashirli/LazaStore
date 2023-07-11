package com.bashirli.lazastore.data.dto.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoriteProductTable")
data class FavoritesDTO(
    @PrimaryKey(autoGenerate = false)
    val id:Int,
    @ColumnInfo("title") val title:String,
    @ColumnInfo("imageURL") val imageURL:String,
    @ColumnInfo("description") val description:String,
    @ColumnInfo("price") val price:String
)
