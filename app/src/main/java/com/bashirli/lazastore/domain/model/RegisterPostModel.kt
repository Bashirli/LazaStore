package com.bashirli.lazastore.domain.model

data class RegisterPostModel(
    val name:String,
    val email:String,
    val password:String,
    val avatar:String="test.test"
)