package com.bryan.taskflow.data.repository

import com.bryan.taskflow.data.local.dao.UserDao
import com.bryan.taskflow.data.local.entity.UserEntity
import com.bryan.taskflow.data.local.entity.toDomain
import com.bryan.taskflow.domain.model.User
import com.bryan.taskflow.domain.repository.UserRepository

class UserRepositoryImpl(private val userDao: UserDao): UserRepository{
    override suspend fun register(
        fullName: String,
        email: String,
        password: String
    ): Result<Unit> {
        val existingUser = userDao.getByEmail(email)
        if(existingUser != null){
            return Result.failure(
                Exception("El usuario ya existe")
            )
        }
        userDao.insert(
            UserEntity(
                fullName = fullName,
                email = email,
                password = password
            )
        )

        return Result.success(Unit)
    }

    override suspend fun login(
        email: String,
        password: String
    ): Result<User> {
        val user = userDao.getByEmail(email)

        if(user === null){
            return Result.failure(
                Exception("Usuario no encontrado")
            )
        }

        if(user.password != password){
            return Result.failure(
                Exception("Contraseña incorrecta")
            )
        }

        return Result.success(
            user.toDomain()
        )
    }

    override suspend fun getUserByEmail(email: String): User? {
        return userDao.getByEmail(email)?.toDomain()
    }

}