package com.example.coroutines.domain

interface UserRepository {
    suspend fun getUser(id: Int): Result<User>
}