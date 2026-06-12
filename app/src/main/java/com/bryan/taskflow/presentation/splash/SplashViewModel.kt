package com.bryan.taskflow.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bryan.taskflow.data.session.SessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashViewModel(private val sessionManager: SessionManager): ViewModel() {

    private val _uiState = MutableStateFlow(SplashUiState())
    val uiState = _uiState.asStateFlow()
    init {
        checkSession()
    }
 private fun checkSession(){
     viewModelScope.launch {
         sessionManager
             .isLoggedIn()
             .collect { isLoggedIn ->
                 _uiState.value =
                     SplashUiState(
                         isLoading = false,
                         isLoggedIn = isLoggedIn
                     )
             }
     }
 }
}