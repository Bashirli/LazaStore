package com.bashirli.lazastore.domain.model

import com.bashirli.lazastore.data.dto.Category
import com.google.gson.annotations.SerializedName

data class ProductModel(
    val category: ProductCategoryModel,
    val description: String,
    val id: Int,
    val images: List<String>,
    val price: Int,
    val title: String
)
