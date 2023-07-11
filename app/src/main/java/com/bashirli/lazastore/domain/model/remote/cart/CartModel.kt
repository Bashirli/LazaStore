package com.bashirli.lazastore.domain.model.remote.cart

data class CartModel(
    val id: Int,
    val products: List<CartProductModel>,
    val total: Int,
    val totalProducts: Int,
    val totalQuantity: Int,
    val userId: Int
)