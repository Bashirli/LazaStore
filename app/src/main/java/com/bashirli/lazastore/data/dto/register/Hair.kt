package com.bashirli.lazastore.data.dto.register


import com.google.gson.annotations.SerializedName

data class Hair(
    @SerializedName("color")
    val color: String,
    @SerializedName("type")
    val type: String
)