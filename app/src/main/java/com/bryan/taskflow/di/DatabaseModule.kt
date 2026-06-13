package com.bryan.taskflow.di

import android.content.Context
import androidx.room.Room
import com.bryan.taskflow.data.local.dao.TaskDao
import com.bryan.taskflow.data.local.dao.UserDao
import com.bryan.taskflow.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     * Hilt utilizará este método para construir
     * una única instancia de AppDatabase.
     *
     * @Singleton significa:
     * Solo existirá una instancia durante toda
     * la vida de la aplicación
     *
     * Esto evita:
     * - múltiples conexiones
     * - consumo innecesario de memoria
     * - inconsistencias
     */
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase{
       return Room.databaseBuilder(
           context,
           AppDatabase::class.java,
           "taskflow.db"
       ).fallbackToDestructiveMigration(false)
           .build()
    }

    /**
     * Obtiene UserDao desde la instancia
     * principal de AppDatabase
     */
    @Provides
    fun provideUserDao(
        database: AppDatabase
    ): UserDao {
        return database.userDao()
    }

    /**
     * Obtiene TaskDao desde la instancia
     * principal de AppDatabase
     */
    @Provides
    fun provideTaskDao(
        database: AppDatabase
    ): TaskDao{
        return database.taskDao()
    }
}