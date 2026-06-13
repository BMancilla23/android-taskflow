package com.bryan.taskflow.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bryan.taskflow.data.local.dao.TaskDao
import com.bryan.taskflow.data.local.dao.UserDao
import com.bryan.taskflow.data.local.entity.TaskEntity
import com.bryan.taskflow.data.local.entity.UserEntity

/**
 * AppDatabase representa la base de datos principal
 * de la aplicación
 *
 * Room utiliza esta clase para generar automáticamente
 * toda la implementación interna de SQLite.
 *
 * Nosotros definimos el contrato.
 * Room genera el código real
 *
 * Equivale conceptualmente a:
 * Backend:
 * ConnectionFactory
 */
@Database(entities = [UserEntity::class, TaskEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase(){
    abstract fun userDao(): UserDao
    abstract fun taskDao(): TaskDao
}