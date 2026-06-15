package com.bryan.taskflow.domain.repository

import com.bryan.taskflow.domain.model.User

interface UserRepository {
    suspend fun register(
        fullName: String,
        email: String,
        password: String
    ): Result<Unit>

    suspend fun login(
        email: String,
        password: String
    ): Result<User>

    suspend fun getUserByEmail(
        email: String
    ): User?

    suspend fun getUserById(
        userId: Long
    ): User?

}