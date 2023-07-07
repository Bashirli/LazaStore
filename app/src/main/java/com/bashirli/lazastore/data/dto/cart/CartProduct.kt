package com.bashirli.lazastore.data.dto.cart


import com.google.gson.annotations.SerializedName

data class CartProduct(
    @SerializedName("discountPercentage")
    val discountPercentage: Double,
    @SerializedName("discountedPrice")
    val discountedPrice: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("price")
    val price: Int,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("total")
    val total: Int
)