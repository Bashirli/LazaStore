package com.bashirli.lazastore.data.dto


import com.google.gson.annotations.SerializedName

data class ProductDTO(
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("products")
    val products: List<Product>,
    @SerializedName("skip")
    val skip: Int,
    @SerializedName("total")
    val total: Int
)