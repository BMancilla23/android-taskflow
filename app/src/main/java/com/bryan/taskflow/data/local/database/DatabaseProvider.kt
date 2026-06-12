package com.bryan.taskflow.data.local.database

import android.content.Context
import androidx.room.Room

/**
 * Responsable de crear y proporcionar
 * una única instancia de AppDatabase,
 *
 * Evita crear múltiples conexiones a la base de datos
 * durante la ejecución de la aplicación.
 *
 * Este patrón se conoce como Singleton.
 *
 * Más adelante esta responsabilidad normalmente la
 * asume un framework de inyección de dependencias
 * como Hilt o Koin
 */
object DatabaseProvider {
    // Instancia única de la base de datos.
    private var database: AppDatabase? = null

    /**
     * Obtiene la instancia de AppDatabase.
     *
     * Si ya existe, la reutiliza
     * Si no existe, la crea
     *
     * synchronized evita que dos hilos intenten crear
     * la base de datos al mismo tiempo
     */
    fun getDatabase(context: Context): AppDatabase {
        return database ?: synchronized(this){
            val instance = Room.databaseBuilder(
                // applicationContext evita fugas de memoria
                // asociadas a Activities
                context.applicationContext,
                AppDatabase::class.java,
                // Nombre físico del archivo SQLite
                "taskflow.db"
            ).build()

            database = instance
            instance
        }
    }
}