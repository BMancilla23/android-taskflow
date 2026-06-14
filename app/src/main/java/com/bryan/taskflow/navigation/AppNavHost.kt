package com.bryan.taskflow.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bryan.taskflow.presentation.login.LoginScreen
import com.bryan.taskflow.presentation.register.RegisterScreen
import com.bryan.taskflow.presentation.splash.SplashScreen
import com.bryan.taskflow.presentation.task.TaskScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                viewModel = hiltViewModel(),
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Splash.route) {
                            inclusive = true
                        }
                    }
                },
                onNavigateToTasks = {
                    navController.navigate(Screen.Tasks.route) {
                        popUpTo(Screen.Splash.route) {
                            inclusive = true
                        }
                    }
                }

            )
        }
        composable(Screen.Login.route) {
            LoginScreen(
                viewModel = hiltViewModel(),
                onLoginSuccess = {
                    navController.navigate(Screen.Tasks.route) {
                        popUpTo(Screen.Login.route) {
                            inclusive = true
                        }
                    }
                },
                onNavigationToRegister = {
                    navController.navigate(Screen.Register.route)
                }
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen(
                viewModel = hiltViewModel(),
                onRegisterSuccess = {
                    navController.popBackStack()
                },
                onNavigationToLogin = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.Tasks.route){
            TaskScreen(
                viewModel = hiltViewModel()
            )
        }
    }
}