package com.bryan.taskflow.di

import com.bryan.taskflow.data.local.dao.TaskDao
import com.bryan.taskflow.data.local.dao.UserDao
import com.bryan.taskflow.data.repository.TaskRepositoryImpl
import com.bryan.taskflow.data.repository.UserRepositoryImpl
import com.bryan.taskflow.domain.repository.TaskRepository
import com.bryan.taskflow.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    /**
     * UserRepository es una interfaz.
     * Hilt no sabe qué implementación crear.
     * Este método le indica:
     *
     * Cuandoa alguien solicite:
     * UserRepository
     *
     * entregar:
     * UserRepositoryImpl
     */
    @Provides
    @Singleton
    fun provideUserRepository(
        userDao: UserDao
    ): UserRepository {
        return UserRepositoryImpl(userDao)
    }

    /**
     * Mismo concepto para TaskRepository.
     *
     * Hilt resuelve automáticamente:
     *
     * TaskRepository
     *    |
     * TaskRepositoryImpl
     *    |
     * TaskDao
     *    |
     * AppDatabase
     */
    @Provides
    @Singleton
    fun provideTaskRepository(
        taskDao: TaskDao
    ): TaskRepository {
        return TaskRepositoryImpl(taskDao)
    }
}