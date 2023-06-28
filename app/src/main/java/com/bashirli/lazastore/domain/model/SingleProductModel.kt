package com.bashirli.lazastore.domain.model

import com.google.gson.annotations.SerializedName

data class SingleProductModel(
    val brand: String,
    val category: String,
    val description: String,
    val discountPercentage: Double,
    val id: Int,
    val price:Int,
    val images: List<String>,
    val rating: Double,
    val thumbnail: String,
    val title: String
)
