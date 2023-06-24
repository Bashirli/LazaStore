package com.bashirli.lazastore.data.dto


import com.google.gson.annotations.SerializedName

data class AuthDTO(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("refresh_token")
    val refreshToken: String
)