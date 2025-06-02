package com.mories.performance_monitor.presentation.navigation

enum class Screen(val route: String) {
    Home("home"), Detail("detail/{value}")
}

sealed class AppDestination(val route: String) {
    data object Home : AppDestination(Screen.Home.route)
    data object Detail : AppDestination(Screen.Detail.route) {
        fun createRoute(value: String): String = "detail/$value"
    }
}