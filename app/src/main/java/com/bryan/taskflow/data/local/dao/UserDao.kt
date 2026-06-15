package com.bryan.taskflow.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.bryan.taskflow.data.local.entity.UserEntity

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: UserEntity)

    @Query(
        """
            SELECT * FROM users WHERE email = :email LIMIT 1
        """
    )suspend fun getByEmail(
        email: String
    ): UserEntity?

    @Query(
        """
            SELECT * FROM users WHERE id = :userId LIMIT 1
        """
    )
    suspend fun getById(
        userId: Long
    ): UserEntity?
}