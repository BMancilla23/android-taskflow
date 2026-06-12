package com.bryan.taskflow.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bryan.taskflow.data.session.SessionManager
import com.bryan.taskflow.domain.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Maneja la lógica de presentación del login.
 *
 * El ViewModel sobrevive a cambios de configuración
 * (por ejemplo rotación de pantalla) y mantiene el estado
 * de la UI separado de los composables.
 *
 */
class LoginViewModel(
    private val userRepository: UserRepository,
    private val sessionManager: SessionManager
) : ViewModel() {
    // Estado interno mutable
    // Solamente el ViewModel puede modificarlo.
    //
    // MutableStateFlow  es un contenedor reactivo de estado.
    // Cuando cambia su valor, todos los observadores reciben
    // automáticamente la actualización
    private val _uiState = MutableStateFlow(LoginUiState())
    // Exposición segura del estado.
    //
    // La UI puede observar los cambios pero NO puede modificar
    // el estado directamente
    // Patrón muy común:
    // private MutableStateFlow
    // public StateFlow
    // Similar a :
    // private set en otros lenguajes
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChange(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    fun login() {
        val state = _uiState.value
        if (
            state.email.isBlank() ||
            state.password.isBlank()
        ) {
            _uiState.value = state.copy(
                error = "Complete todos los campos"
            )

            return
        }
//        _uiState.value = _uiState.value.copy(
//            error = null,
//            isLoggedIn = true
//        )

        // viewModelScope es un CoroutineScope ligado
        // al ciclo de vida del ViewModel.
        //
        // Cuando el ViewModel es destruido.
        // las corrutinas se cancelan automáticamente
        viewModelScope.launch {
            // Result<User>
            //
            // Result encapsula dos escenarios:
            //
            // Success(User)
            // Failure(Exception)
            //
            // Evita usar try/catch en la UI.
            val result = userRepository.login(
                email = state.email,
                password = state.password
            )
            result
                // Se ejecuta solamente si la operación fue exitosa.
                .onSuccess { user ->
                    // Guarda el id del usuario en DataStora
                    // para restaurar la sesión al abrir la app.
                    sessionManager.saveSession(user.id)
                    _uiState.value = state.copy(
                        error = null,
                        isLoggedIn = true
                    )
                }
                // Se ejecuta solamente si ocurrió un error
                .onFailure {
                    _uiState.value = state.copy(
                        error = it.message
                    )
                }
        }
    }
}