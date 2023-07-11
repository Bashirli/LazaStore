package com.bashirli.lazastore.domain.model.remote

import com.google.gson.annotations.SerializedName

data class ProductModel(
    val brand: String,
    val category: String,
    val description: String,
    val discountPercentage: Double,
    val id: Int,
    val images: List<String>,
    val price: Int,
    val rating: Double,
    val thumbnail: String,
    val title: String
)
