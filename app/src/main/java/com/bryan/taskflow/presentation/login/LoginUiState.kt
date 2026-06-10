package com.bryan.taskflow.presentation.login

/**
 * Estado de la pantalla de login.
 *
 * Este estado es observado por Compose
 * mediante StateFlow para mantener UI reactiva
 */
data class LoginUiState (
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isLoggedIn: Boolean = false
)