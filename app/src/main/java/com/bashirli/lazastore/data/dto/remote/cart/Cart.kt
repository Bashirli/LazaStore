package com.bashirli.lazastore.data.dto.remote.cart


import com.google.gson.annotations.SerializedName

data class Cart(
    @SerializedName("discountedTotal")
    val discountedTotal: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("products")
    val cartProducts: List<CartProduct>,
    @SerializedName("total")
    val total: Int,
    @SerializedName("totalProducts")
    val totalProducts: Int,
    @SerializedName("totalQuantity")
    val totalQuantity: Int,
    @SerializedName("userId")
    val userId: Int
)