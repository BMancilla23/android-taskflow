package com.bryan.taskflow.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bryan.taskflow.R

@Composable
fun SplashScreen(
    viewModel: SplashViewModel,
    onNavigateToLogin: () -> Unit,
    onNavigateToTasks: () -> Unit
){

    /**
     * collectAsState() conecta un Flow/StateFlow con Compose
     * El stateFlow vive en el ViewModel.
     * Compose lo convierte en un state observable
     *
     * Cada vez que uiState emite un nuevo valor:
     *
     * SplashViewModel
     *     |
     * StateFlow
     *    |
     * collectAsState()
     *   |
     * Recomposition
     *
     * Similar conceptualmente a:
     * React:
     * useState + useEffect
     *
     * Angular:
     * Observable + async pipe
     *
     *
     */
    val state by viewModel.uiState.collectAsState()

    /**
     * LaunchedEffect ejecuta una corrutina ligada al ciclo
     * de vida del composable.
     *
     * Se vuelve a ejecutar únicamente cuando cambia la key
     * que recibe como parámetro.
     *
     * En este caso:
     *
     * LaunchedEffect(state.isLoading)
     *
     * se ejecutará cuando isLoading cambie.
     *
     * Es similar a:
     *
     * React:
     * useEffect(..., [isLoading])
     */
    LaunchedEffect(state.isLoading) {
        /**
         * Mientras isLoading sea true
         * seguimos mostrando el Splash.
         *
         * Cuando el ViewModel termina de verificar
         * la sesión:
         *
         * isLoading = false
         *
         * entonces navegamos a la pantalla adecuada.
         */
        if(!state.isLoading){
            if(state.isLoggedIn){
                onNavigateToTasks()
            }else{
                onNavigateToLogin()
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.taskflow_logo
                ),
                contentDescription = "TaskFlow Logo",
                modifier = Modifier.size(180.dp)
            )
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            Text(
                text = "TaskFlow",
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}