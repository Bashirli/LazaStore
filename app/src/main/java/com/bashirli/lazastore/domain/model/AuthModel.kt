package com.bashirli.lazastore.domain.model

import com.google.gson.annotations.SerializedName

data class AuthModel(
    val accessToken: String,
    val refreshToken: String
)
