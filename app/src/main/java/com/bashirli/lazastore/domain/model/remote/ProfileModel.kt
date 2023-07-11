package com.bashirli.lazastore.domain.model.remote

import com.bashirli.lazastore.data.dto.remote.user.Address

data class ProfileModel(
    val address: Address,
    val age: Int,
    val birthDate: String,
    val bloodGroup: String,
    val domain: String,
    val ein: String,
    val email: String,
    val eyeColor: String,
    val firstName: String,
    val gender: String,
    val height: Int,
    val id: Int,
    val image: String,
    val ip: String,
    val lastName: String,
    val macAddress: String,
    val maidenName: String,
    val password: String,
    val phone: String,
    val ssn: String,
    val university: String,
    val userAgent: String,
    val username: String,
    val weight: Double
)
