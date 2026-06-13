package com.bryan.taskflow.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bryan.taskflow.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel(){
    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState = _uiState.asStateFlow()

    fun onFullNameChange(fullName: String){
        _uiState.value = _uiState.value.copy(
            fullName = fullName
        )
    }

    fun onEmailChange(email: String){
        _uiState.value = _uiState.value.copy(
            email = email
        )
    }

    fun onPasswordChange(password: String){
        _uiState.value = _uiState.value.copy(
            password = password
        )
    }

    fun onConfirmPasswordChange(password: String){
        _uiState.value = _uiState.value.copy(
            confirmPassword = password
        )
    }

    fun register(){
        val state = _uiState.value

        when {
            state.fullName.isBlank() ||
            state.email.isBlank() ||
                    state.password.isBlank() ||
                    state.confirmPassword.isBlank() -> {
                      _uiState.value = state.copy(
                          error = "Complete todos los campos"
                      )
                    }

            state.password != state.confirmPassword -> {
                _uiState.value = state.copy(
                    error = "Las contraseñas no coinciden"
                )
            }
        else -> {
//            _uiState.value = state.copy(
//                error = null,
//                isRegistered = true
//            )

            viewModelScope.launch {
                val result = userRepository.register(
                    fullName = state.fullName,
                    email = state.email,
                    password = state.password
                )

                result
                    .onSuccess {
                        _uiState.value = state.copy(
                            error = null,
                            isRegistered = true
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
    }
}