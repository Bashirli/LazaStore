package com.bashirli.lazastore.domain.model

import com.google.gson.annotations.SerializedName

data class ProductCategoryModel(
    val id: Int,
    val image: String,
    val name: String
)