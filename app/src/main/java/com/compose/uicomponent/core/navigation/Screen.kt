package com.compose.uicomponent.core.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Catalog : Screen("catalog")
    object Profile : Screen("profile")
    object Settings : Screen("settings")
}
