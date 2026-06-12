package com.bryan.taskflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import com.bryan.taskflow.data.repository.RepositoryProvider
import com.bryan.taskflow.data.session.SessionManager
import com.bryan.taskflow.data.session.SessionProvider
import com.bryan.taskflow.navigation.Screen
import com.bryan.taskflow.presentation.login.LoginViewModel
import com.bryan.taskflow.presentation.login.RegisterViewModel
import com.bryan.taskflow.presentation.splash.SplashScreen
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

                val sessionManager = SessionProvider.provide(this@MainActivity)

                val loginViewModel = remember {
                    LoginViewModel(userRepository, sessionManager)
                }

                val registerViewModel = remember {
                    RegisterViewModel(userRepository)
                }

                val taskViewModel = remember {
                    TaskViewModel()
                }

                val isLoggedIn by sessionManager
                    .isLoggedIn()
                    .collectAsState(initial = false)

                var currentScreen by remember {
//                    mutableStateOf("login")
                    mutableStateOf<Screen>(
                        Screen.Splash
                    )
                }

                LaunchedEffect(isLoggedIn) {
//                    if (isLoggedIn){
//                        currentScreen = "home"
//                    }
                    currentScreen =
                        if (isLoggedIn){
                            Screen.Tasks
                        }else {
                            Screen.Login
                        }
                }

                when(currentScreen){
                    Screen.Splash -> {
                        SplashScreen()
                    }
                    Screen.Login -> LoginScreen(
//                        viewModel = LoginViewModel(),
                        viewModel = loginViewModel,
                        onLoginSuccess = {
                            currentScreen = Screen.Tasks
                        },
                        onNavigationToRegister = {
                            currentScreen = Screen.Register
                        }
                    )
                    Screen.Register-> RegisterScreen(
//                        viewModel = RegisterViewModel(),
                        viewModel = registerViewModel,
                        onRegisterSuccess = {
                            currentScreen = Screen.Login
                        },
                        onNavigationToLogin = {
                            currentScreen = Screen.Login
                        }
                    )
//                    "home" -> HomeScreen()
                    Screen.Tasks -> TaskScreen(
                        viewModel = taskViewModel
                    )
                }
            }
        }
    }
}
