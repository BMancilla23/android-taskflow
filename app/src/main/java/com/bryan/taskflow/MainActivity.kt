package com.bryan.taskflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import com.bryan.taskflow.presentation.login.LoginViewModel
import com.bryan.taskflow.ui.screens.HomeScreen
import com.bryan.taskflow.ui.screens.LoginScreen
import com.bryan.taskflow.ui.theme.TaskFlowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskFlowTheme {
                var currentScreen by remember {
                    mutableStateOf("login")
                }

                when(currentScreen){
                    "login" -> LoginScreen(
                        viewModel = LoginViewModel(),
                        onLoginSuccess = {
                            currentScreen = "home"
                        }
                    )
                    "home" -> HomeScreen()
                }
            }
        }
    }
}
