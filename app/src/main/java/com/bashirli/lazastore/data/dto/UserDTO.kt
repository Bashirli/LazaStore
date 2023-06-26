package com.bashirli.lazastore.data.dto


import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("role")
    val role: String
)