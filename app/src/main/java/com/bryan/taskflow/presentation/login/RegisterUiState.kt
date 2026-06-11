package com.bryan.taskflow.presentation.login

data class RegisterUiState (
    val fullName: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val error: String? = null,
    val isRegistered: Boolean = false
)