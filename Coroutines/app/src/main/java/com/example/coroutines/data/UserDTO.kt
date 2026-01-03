package com.example.coroutines.data

import com.example.coroutines.domain.User
import com.google.gson.annotations.SerializedName

data class UserDTO(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    @SerializedName("phone")
    val phoneNumber: String,
    val website: String
)

fun UserDTO.toDomain() = User(
    id = this.id,
    firstName = this.username,
    email = this.email
)