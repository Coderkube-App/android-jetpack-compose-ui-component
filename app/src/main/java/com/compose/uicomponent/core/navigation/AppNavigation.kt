package com.compose.uicomponent.core.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.compose.uicomponent.core.designsystem.layouts.ResponsiveScaffold
import com.compose.uicomponent.core.designsystem.layouts.rememberWindowSize
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.compose.uicomponent.core.designsystem.components.AppBottomNavigation
import com.compose.uicomponent.core.designsystem.components.AppTopBar
import com.compose.uicomponent.core.designsystem.components.NavigationItem
import com.compose.uicomponent.features.catalog.CatalogScreen
import com.compose.uicomponent.features.home.HomeScreen
import com.compose.uicomponent.features.profile.ProfileScreen
import com.compose.uicomponent.features.settings.SettingsScreen
import com.compose.uicomponent.features.settings.SettingsViewModel

@Composable
fun MainAppContent(settingsViewModel: SettingsViewModel = viewModel()) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val windowSize = rememberWindowSize()

    val navigationItems = listOf(
        NavigationItem(Screen.Home.route, "Home", Icons.Default.Home),
        NavigationItem(Screen.Catalog.route, "Catalog", Icons.Default.List),
        NavigationItem(Screen.Profile.route, "Profile", Icons.Default.Person),
        NavigationItem(Screen.Settings.route, "Settings", Icons.Default.Settings)
    )

    val currentTitle = navigationItems.find { it.route == currentRoute }?.title ?: "App"

    ResponsiveScaffold(
        windowSize = windowSize,
        topBar = {
            AppTopBar(title = currentTitle)
        },
        bottomBarItems = navigationItems,
        currentRoute = currentRoute,
        onNavigate = { item ->
            navController.navigate(item.route) {
                popUpTo(navController.graph.startDestinationId) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { HomeScreen() }
            composable(Screen.Catalog.route) { CatalogScreen() }
            composable(Screen.Profile.route) { ProfileScreen() }
            composable(Screen.Settings.route) { SettingsScreen(settingsViewModel) }
        }
    }
}
