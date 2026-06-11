package com.bryan.taskflow.data.local.entity

import com.bryan.taskflow.domain.model.User

fun UserEntity.toDomain(): User {
    return User(
        id = id,
        fullName = fullName,
        email = email,
        password = password,
        created = createdAt
    )
}

fun User.toEntity(): UserEntity {
   return UserEntity(
       id = id,
       fullName = fullName,
       email = email,
       password = password,
       createdAt = created
   )
}