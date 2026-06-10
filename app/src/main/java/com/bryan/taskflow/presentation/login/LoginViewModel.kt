package com.bryan.taskflow.presentation.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Maneja la lógica de presentación del login.
 *
 * La UI observa uiState mediante StateFlow  y se recompone automáticamente cuando el estado cambia
 */
class LoginViewModel: ViewModel(){
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChange(email: String){
        _uiState.value = _uiState.value.copy(email = email)
    }

    fun onPasswordChange(password: String){
        _uiState.value = _uiState.value.copy(password = password)
    }

    fun login(){
        if (
           _uiState.value.email.isBlank() ||
            _uiState.value.password.isBlank()
        ){
            _uiState.value = _uiState.value.copy(
                error = "Complete todos los campos"
            )

            return
        }
        _uiState.value = _uiState.value.copy(
            error = null,
            isLoggedIn = true
        )
    }
}