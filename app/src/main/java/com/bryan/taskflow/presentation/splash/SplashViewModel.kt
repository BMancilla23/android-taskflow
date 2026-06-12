package com.bryan.taskflow.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bryan.taskflow.data.session.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val sessionManager: SessionManager): ViewModel() {

    private val _uiState = MutableStateFlow(SplashUiState())
    val uiState = _uiState.asStateFlow()
    init {
        checkSession()
    }
 private fun checkSession(){
     viewModelScope.launch {
         val isLoggedIn = sessionManager
             .isLoggedIn().first()

         _uiState.value =  SplashUiState(
                         isLoading = false,
                         isLoggedIn = isLoggedIn
                     )
     }
 }
}