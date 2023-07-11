package com.bashirli.lazastore.domain.model.remote.body

import com.google.gson.annotations.SerializedName

data class UpdateProduct(
    val id: Int,
    val price: Int,
    val quantity: Int,
)
