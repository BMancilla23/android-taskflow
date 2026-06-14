package com.bryan.taskflow.navigation

sealed class Screen(
    val route: String
) {
//    data object Splash : Screen()
//    data object Login : Screen()
//    data object Register : Screen()
//    data object Tasks : Screen()

    data object Splash : Screen("splash")
    data object Login : Screen("login")
    data object Register : Screen("register")
    data object Tasks : Screen("tasks")
    data object CreateTask : Screen("tasks/create")
    data object EditTask : Screen("tasks/edit/{taskId}"){
        fun createRoute(
            taskId: Long
        ) = "tasks/edit/$taskId"
    }
}