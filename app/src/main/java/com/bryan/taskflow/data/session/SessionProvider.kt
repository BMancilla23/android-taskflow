package com.bryan.taskflow.data.session

import android.content.Context

/**
 * Responsable de crear instancias de SessionManager.
 *
 * Actualmente es una capa simple, pero ayuda a mantener
 * consistente la forma en que obtenemos dependencias.
 *
 * Más adelante podría reemplazarse por Hilt o Koin
 */
object SessionProvider {
    /**
     * Construye un SessionManager
     */
    fun provide(
        context: Context
    ): SessionManager {
        return SessionManager(context)
    }
}