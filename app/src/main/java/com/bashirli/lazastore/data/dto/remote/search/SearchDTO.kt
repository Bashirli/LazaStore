package com.bashirli.lazastore.data.dto.remote.search


import com.google.gson.annotations.SerializedName

data class SearchDTO(
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("products")
    val searchProducts: List<SearchProduct>,
    @SerializedName("skip")
    val skip: Int,
    @SerializedName("total")
    val total: Int
)