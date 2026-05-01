package com.compose.uicomponent.core.designsystem.tokens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.compose.uicomponent.core.designsystem.theme.AppTheme

/**
 * Standard modifiers to ensure consistency across the app.
 */

@Composable
fun Modifier.standardCardStyle(): Modifier = this
    .fillMaxWidth()
    .shadow(elevation = AppTheme.elevation.level2, shape = MaterialTheme.shapes.large)
    .background(MaterialTheme.colorScheme.surface, shape = MaterialTheme.shapes.large)
    .clip(MaterialTheme.shapes.large)
    .padding(AppTheme.spacing.medium)

@Composable
fun Modifier.appScreenPadding(): Modifier = this
    .padding(AppTheme.spacing.medium)

@Composable
fun Modifier.roundedBackground(color: Color = MaterialTheme.colorScheme.surfaceVariant): Modifier = this
    .background(color, shape = RoundedCornerShape(12.dp))
    .clip(RoundedCornerShape(12.dp))
    .padding(12.dp)
