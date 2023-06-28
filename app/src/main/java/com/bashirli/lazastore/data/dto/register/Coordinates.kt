package com.bashirli.lazastore.data.dto.register


import com.google.gson.annotations.SerializedName

data class Coordinates(
    @SerializedName("lat")
    val lat: Any,
    @SerializedName("lng")
    val lng: Any
)