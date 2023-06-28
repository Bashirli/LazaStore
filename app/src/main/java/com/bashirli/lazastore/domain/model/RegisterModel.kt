package com.bashirli.lazastore.domain.model

import com.google.gson.annotations.SerializedName

data class RegisterModel(
    val email: String,
    val username: String,
    val password: String,
)