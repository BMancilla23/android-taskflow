package com.bryan.taskflow.navigation

sealed class Screen {
    data object Splash : Screen()
    data object Login : Screen()
    data object Register : Screen()
    data object Tasks : Screen()
}