package com.bashirli.lazastore.domain.model.remote.cart

data class CartMainModel (
    val carts: List<CartModel>,
    val limit: Int,
    val total: Int
        )