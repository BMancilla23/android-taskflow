package com.bryan.taskflow.data.repository

import android.content.Context
import com.bryan.taskflow.data.local.database.DatabaseProvider

object RepositoryProvider {
    fun provideUserRepository(
        context: Context
    ): UserRepositoryImpl {
        val database = DatabaseProvider.getDatabase(context)
        return UserRepositoryImpl(
            database.userDao()
        )
    }
}