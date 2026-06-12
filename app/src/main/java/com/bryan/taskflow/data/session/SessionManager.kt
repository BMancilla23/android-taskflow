package com.bryan.taskflow.data.session

import androidx.datastore.preferences.preferencesDataStore
import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Extensión sobre Context que crea una única instancia
// de DataStore para toda la aplicación.
//
// Context es el punto de accesso a recursos del sistema:
// archivos, preferencias, bases de datos, etc.
private val Context.dataStore by preferencesDataStore(
    name = "session"
)

class SessionManager(
    // Context permite acceder el DataStore de la aplicación
    private val context: Context
) {
    companion object {
        // Clave utilizada para almacenar el id del usuario
        // dentro de DataStore.
        //
        // Equivale conceptualmente a:
        // localstorage["user_id"] en desarrollo web.
        private val USER_ID = longPreferencesKey("user_id")
    }

    // Suspend indica que esta función realiza trabajo
    // asincrono y debe ejecutarse dentro de una corrutina.
    //
    // Guarda la sesión del usuario autenticado.
    suspend fun saveSession(userId: Long) {
        context.dataStore.edit { preferences -> preferences[USER_ID] = userId }
    }

    // Elimina la sesión almacenada
    // Se utilizará durante el logout.
    suspend fun clearSession(){
        context.dataStore.edit {
            preferences -> preferences.remove(USER_ID)
        }
    }

    // Flow representa un flujo reactivo de datos
    //
    // Cada vez que USER_ID cambie,
    // los observadores recibirán automáticamente
    // el nuevo valor
    fun getUserId(): Flow<Long?>{
        return context.dataStore.data.map {
            preferences -> preferences[USER_ID]
        }
    }

    // Devuelve true si existe un usuario autenticado.
    //
    // map transforma:
    //
    // Long? -> Boolean
    //
    // userId = 5  -> true
    // userId = null -> false
    fun isLoggedIn(): Flow<Boolean>{
        return context.dataStore.data.map {
            preferences -> preferences[USER_ID] != null
        }
    }

}

