package com.compose.uicomponent.features.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.compose.uicomponent.core.designsystem.components.AppAvatar
import com.compose.uicomponent.core.designsystem.components.AppBadge
import com.compose.uicomponent.core.designsystem.components.AppBaseScreen
import com.compose.uicomponent.core.designsystem.components.AppBody
import com.compose.uicomponent.core.designsystem.components.AppListItem
import com.compose.uicomponent.core.designsystem.components.AppOtpField
import com.compose.uicomponent.core.designsystem.components.AppSubTitle
import com.compose.uicomponent.core.designsystem.components.AppSwitchItem
import com.compose.uicomponent.core.designsystem.components.AppTitle
import com.compose.uicomponent.core.designsystem.theme.AppTheme

@Composable
fun ProfileScreen() {
    var otpValue by remember { mutableStateOf("") }
    var notificationsEnabled by remember { mutableStateOf(true) }

    AppBaseScreen {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.large),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    AppBadge(count = 3) {
                        AppAvatar(initials = "JD", size = 80.dp)
                    }
                    Spacer(modifier = Modifier.height(AppTheme.spacing.medium))
                    AppTitle(text = "John Doe")
                    AppBody(text = "john.doe@example.com")
                }
            }

            item {
                Column(modifier = Modifier.padding(horizontal = AppTheme.spacing.small)) {
                    AppSubTitle(text = "Account Settings")
                    Spacer(modifier = Modifier.height(AppTheme.spacing.small))
                    AppListItem(
                        title = "Personal Information",
                        subtitle = "Change your name and email",
                        leadingIcon = Icons.Default.Person,
                        onClick = {}
                    )
                    AppListItem(
                        title = "Email Notifications",
                        subtitle = "Manage your email alerts",
                        leadingIcon = Icons.Default.Email,
                        onClick = {}
                    )
                    AppSwitchItem(
                        title = "Push Notifications",
                        subtitle = "Get real-time app updates",
                        checked = notificationsEnabled,
                        onCheckedChange = { notificationsEnabled = it },
                        leadingIcon = Icons.Default.Notifications
                    )
                }
            }

            item {
                Column(modifier = Modifier.padding(horizontal = AppTheme.spacing.small)) {
                    AppSubTitle(text = "Security")
                    Spacer(modifier = Modifier.height(AppTheme.spacing.small))
                    AppBody(text = "Two-Factor Authentication Code")
                    Spacer(modifier = Modifier.height(AppTheme.spacing.small))
                    AppOtpField(
                        otpText = otpValue,
                        onOtpTextChange = { otpValue = it }
                    )
                    Spacer(modifier = Modifier.height(AppTheme.spacing.medium))
                    AppListItem(
                        title = "Change Password",
                        subtitle = "Last changed 3 months ago",
                        leadingIcon = Icons.Default.Lock,
                        onClick = {}
                    )
                }
            }
        }
    }
}
