package com.bryan.taskflow.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bryan.taskflow.domain.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Maneja la lógica de presentación del login.
 *
 * La UI observa uiState mediante StateFlow  y se recompone automáticamente cuando el estado cambia
 */
class LoginViewModel(
    private val userRepository: UserRepository
): ViewModel(){
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChange(email: String){
        _uiState.value = _uiState.value.copy(email = email)
    }

    fun onPasswordChange(password: String){
        _uiState.value = _uiState.value.copy(password = password)
    }

    fun login(){
        val state = _uiState.value
        if (
           state.email.isBlank() ||
            state.password.isBlank()
        ){
            _uiState.value = state.copy(
                error = "Complete todos los campos"
            )

            return
        }
//        _uiState.value = _uiState.value.copy(
//            error = null,
//            isLoggedIn = true
//        )
        viewModelScope.launch {
            val result = userRepository.login(
                email = state.email,
                password = state.password
            )
            result
                .onSuccess {
                    _uiState.value = state.copy(
                        error = null,
                        isLoggedIn = true
                    )
                }
                .onFailure {
                    _uiState.value = state.copy(
                        error = it.message
                    )
                }
        }
    }
}