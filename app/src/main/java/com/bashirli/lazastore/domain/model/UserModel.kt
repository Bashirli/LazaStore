package com.bashirli.lazastore.domain.model

import com.google.gson.annotations.SerializedName

data class UserModel(
    val avatar: String,
    val email: String,
    val name: String,
    val password: String,
    val role: String
)
