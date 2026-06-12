package com.bryan.taskflow.di

import com.bryan.taskflow.data.local.dao.UserDao
import com.bryan.taskflow.data.repository.UserRepositoryImpl
import com.bryan.taskflow.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideUserRepository(
        userDao: UserDao
    ): UserRepository {
        return UserRepositoryImpl(userDao)
    }
}