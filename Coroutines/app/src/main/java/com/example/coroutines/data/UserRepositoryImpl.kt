package com.example.coroutines.data

import com.example.coroutines.domain.User
import com.example.coroutines.domain.UserRepository

class UserRepositoryImpl(
    private val apiService: UserApiService
) : UserRepository {

    override suspend fun getUser(id: Int): Result<User> {
        return try {
            val response = apiService.getUserById(id).toDomain()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}