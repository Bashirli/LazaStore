package com.bashirli.lazastore.domain.model.cart

import com.bashirli.lazastore.data.dto.cart.Cart
import com.google.gson.annotations.SerializedName

data class CartMainModel (
    val carts: List<CartModel>,
    val limit: Int,
    val total: Int
        )