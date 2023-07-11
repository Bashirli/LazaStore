package com.bashirli.lazastore.data.dto.remote.cart


import com.google.gson.annotations.SerializedName

data class CartDTO(
    @SerializedName("carts")
    val carts: List<Cart>,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("skip")
    val skip: Int,
    @SerializedName("total")
    val total: Int
)