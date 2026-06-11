package com.bryan.taskflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import com.bryan.taskflow.data.repository.RepositoryProvider
import com.bryan.taskflow.presentation.login.LoginViewModel
import com.bryan.taskflow.presentation.login.RegisterViewModel
import com.bryan.taskflow.presentation.task.TaskViewModel
import com.bryan.taskflow.ui.screens.HomeScreen
import com.bryan.taskflow.ui.screens.LoginScreen
import com.bryan.taskflow.ui.screens.RegisterScreen
import com.bryan.taskflow.ui.screens.TaskScreen
import com.bryan.taskflow.ui.theme.TaskFlowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskFlowTheme {
               val userRepository = remember {
                   RepositoryProvider.provideUserRepository(this@MainActivity)
               }

                val loginViewModel = remember {
                    LoginViewModel(userRepository)
                }

                val registerViewModel = remember {
                    RegisterViewModel(userRepository)
                }

                var currentScreen by remember {
                    mutableStateOf("login")
                }

                when(currentScreen){
                    "login" -> LoginScreen(
//                        viewModel = LoginViewModel(),
                        viewModel = loginViewModel,
                        onLoginSuccess = {
                            currentScreen = "home"
                        },
                        onNavigationToRegister = {
                            currentScreen = "register"
                        }
                    )
                    "register"-> RegisterScreen(
//                        viewModel = RegisterViewModel(),
                        viewModel = registerViewModel,
                        onRegisterSuccess = {
                            currentScreen = "login"
                        },
                        onNavigationToLogin = {
                            currentScreen = "login"
                        }
                    )
//                    "home" -> HomeScreen()
                    "home" -> TaskScreen(
                        viewModel = TaskViewModel()
                    )
                }
            }
        }
    }
}
