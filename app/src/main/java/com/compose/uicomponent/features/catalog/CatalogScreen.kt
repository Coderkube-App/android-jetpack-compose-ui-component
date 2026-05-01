package com.compose.uicomponent.features.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.compose.uicomponent.core.designsystem.animations.FadeInVisibility
import com.compose.uicomponent.core.designsystem.animations.SlideUpVisibility
import com.compose.uicomponent.core.designsystem.components.AppAudioPlayer
import com.compose.uicomponent.core.designsystem.components.AppAvatar
import com.compose.uicomponent.core.designsystem.components.AppBadge
import com.compose.uicomponent.core.designsystem.components.AppBarChart
import com.compose.uicomponent.core.designsystem.components.AppBaseScreen
import com.compose.uicomponent.core.designsystem.components.AppBody
import com.compose.uicomponent.core.designsystem.components.AppBottomSheet
import com.compose.uicomponent.core.designsystem.components.AppCard
import com.compose.uicomponent.core.designsystem.components.AppDialog
import com.compose.uicomponent.core.designsystem.components.AppElevatedButton
import com.compose.uicomponent.core.designsystem.components.AppFilterChip
import com.compose.uicomponent.core.designsystem.components.AppInputChip
import com.compose.uicomponent.core.designsystem.components.AppListItem
import com.compose.uicomponent.core.designsystem.components.AppOtpField
import com.compose.uicomponent.core.designsystem.components.AppOutlinedCard
import com.compose.uicomponent.core.designsystem.components.AppPrimaryButton
import com.compose.uicomponent.core.designsystem.components.AppSearchBar
import com.compose.uicomponent.core.designsystem.components.AppSecondaryButton
import com.compose.uicomponent.core.designsystem.components.AppSnackbarHost
import com.compose.uicomponent.core.designsystem.components.AppStateHandler
import com.compose.uicomponent.core.designsystem.components.AppSubTitle
import com.compose.uicomponent.core.designsystem.components.AppSwitchItem
import com.compose.uicomponent.core.designsystem.components.AppTagPicker
import com.compose.uicomponent.core.designsystem.components.AppTextButton
import com.compose.uicomponent.core.designsystem.components.AppTextField
import com.compose.uicomponent.core.designsystem.components.AppTitle
import com.compose.uicomponent.core.designsystem.components.ChartData
import com.compose.uicomponent.core.designsystem.components.ExpandableListItem
import com.compose.uicomponent.core.designsystem.components.ShimmerItem
import com.compose.uicomponent.core.designsystem.layouts.AdaptiveGrid
import com.compose.uicomponent.core.designsystem.layouts.rememberWindowSize
import com.compose.uicomponent.core.designsystem.theme.AppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen() {
    var textValue by remember { mutableStateOf("") }
    var searchQuery by remember { mutableStateOf("") }
    var tags by remember { mutableStateOf(listOf("Compose", "Kotlin", "Material 3")) }
    
    var isSearchActive by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var showSheet by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    var showAnimations by remember { mutableStateOf(false) }
    
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val windowSize = rememberWindowSize()

    AppBaseScreen(usePadding = false) {
        Column(modifier = Modifier.fillMaxSize()) {
            AppSearchBar(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                onSearch = { isSearchActive = false },
                active = isSearchActive,
                onActiveChange = { isSearchActive = it },
                placeholder = "Search system components..."
            )

            Box(modifier = Modifier.weight(1f)) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(AppTheme.spacing.medium),
                    verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.medium)
                ) {
                    item {
                        Section(title = "Data & Insights") {
                            AppBarChart(
                                data = listOf(
                                    ChartData("Mon", 40f),
                                    ChartData("Tue", 70f),
                                    ChartData("Wed", 50f),
                                    ChartData("Thu", 90f),
                                    ChartData("Fri", 65f)
                                )
                            )
                        }
                    }

                    item {
                        Section(title = "Media Player") {
                            AppAudioPlayer(title = "Midnight City", artist = "M83")
                        }
                    }

                    item {
                        Section(title = "Form Validation") {
                            var email by remember { mutableStateOf("") }
                            AppTextField(
                                value = email,
                                onValueChange = { email = it },
                                label = "Email Address",
                                placeholder = "demo@example.com",
                                isError = email.isNotEmpty() && !email.contains("@"),
                                errorMessage = if (email.isNotEmpty() && !email.contains("@")) "Invalid email" else null
                            )
                        }
                    }

                    item {
                        Section(title = "Typography Showcase") {
                            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                Text("Display Large", style = MaterialTheme.typography.displayLarge)
                                Text("Headline Medium", style = MaterialTheme.typography.headlineMedium)
                                Text("Title Large", style = MaterialTheme.typography.titleLarge)
                                Text("Body Medium", style = MaterialTheme.typography.bodyMedium)
                                Text("Label Small", style = MaterialTheme.typography.labelSmall)
                            }
                        }
                    }

                    item {
                        Section(title = "Color Palette") {
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                ColorBox("Primary", MaterialTheme.colorScheme.primary)
                                ColorBox("Secondary", MaterialTheme.colorScheme.secondary)
                                ColorBox("Tertiary", MaterialTheme.colorScheme.tertiary)
                                ColorBox("Error", MaterialTheme.colorScheme.error)
                            }
                        }
                    }

                    item {
                        Section(title = "Responsive Layouts") {
                            AdaptiveGrid(
                                items = listOf("Phone", "Tablet", "Foldable", "Desktop"),
                                windowSize = windowSize,
                                modifier = Modifier.height(120.dp)
                            ) { item ->
                                AppOutlinedCard { AppSubTitle(text = item) }
                            }
                        }
                    }

                    item {
                        Section(title = "Buttons & Input") {
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                AppPrimaryButton(text = "Primary", onClick = {})
                                AppElevatedButton(text = "Elevated", onClick = {})
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            AppTagPicker(
                                tags = tags,
                                onTagRemove = { tag -> tags = tags.filter { it != tag } }
                            )
                        }
                    }

                    item {
                        Section(title = "Feedback & State") {
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                AppPrimaryButton(text = "Snackbar", onClick = {
                                    scope.launch { snackbarHostState.showSnackbar("Action successful!") }
                                })
                                AppSecondaryButton(text = "Sheet", onClick = { showSheet = true })
                                AppTextButton(text = "Dialog", onClick = { showDialog = true })
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            AppPrimaryButton(
                                text = if (isLoading) "Loading..." else "Trigger Loader",
                                onClick = { isLoading = true }
                            )
                            
                            if (isLoading) {
                                LaunchedEffect(Unit) {
                                    delay(2000)
                                    isLoading = false
                                }
                            }
                            
                            Box(modifier = Modifier.height(60.dp)) {
                                AppStateHandler(
                                    loading = isLoading,
                                    data = if (isLoading) null else "Operation Completed",
                                    content = { AppBody(text = it) }
                                )
                            }
                        }
                    }

                    item {
                        Section(title = "Animations") {
                            AppSecondaryButton(
                                text = if (showAnimations) "Toggle Animation" else "Show Animation",
                                onClick = { showAnimations = !showAnimations }
                            )
                            FadeInVisibility(visible = showAnimations) {
                                ShimmerItem(modifier = Modifier.padding(top = 8.dp))
                            }
                        }
                    }
                }
                AppSnackbarHost(hostState = snackbarHostState)
            }
        }

        if (showDialog) {
            AppDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = { AppTextButton(text = "OK", onClick = { showDialog = false }) },
                title = "Design System",
                text = "Full implementation complete."
            )
        }

        if (showSheet) {
            AppBottomSheet(onDismissRequest = { showSheet = false }) {
                AppTitle(text = "System Actions")
                AppBody(text = "Quick access to core functions.")
                Spacer(modifier = Modifier.height(24.dp))
                AppPrimaryButton(text = "Dismiss", onClick = { showSheet = false }, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@Composable
private fun ColorBox(label: String, color: androidx.compose.ui.graphics.Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.size(48.dp).clip(MaterialTheme.shapes.small).background(color))
        Text(text = label, style = MaterialTheme.typography.labelSmall)
    }
}

@Composable
private fun Section(title: String, content: @Composable () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        AppSubTitle(text = title, modifier = Modifier.padding(bottom = AppTheme.spacing.small))
        AppCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(AppTheme.spacing.medium)) {
                content()
            }
        }
    }
}
