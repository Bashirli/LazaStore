package com.bashirli.lazastore.domain.model.remote.cart

import com.google.gson.annotations.SerializedName

data class CartProductModel(
    val discountPercentage: Double,
    val discountedPrice: Int,
    val id: Int,
    val price: Int,
    val quantity: Int,
    val title: String,
    val total: Int
    )
