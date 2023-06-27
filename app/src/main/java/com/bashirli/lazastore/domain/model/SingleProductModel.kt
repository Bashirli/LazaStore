package com.bashirli.lazastore.domain.model

import com.bashirli.lazastore.data.dto.CategoryX
import com.google.gson.annotations.SerializedName

data class SingleProductModel(
    val description: String,
    val id: Int,
    val images: List<String>,
    val price: Int,
    val title: String
)
