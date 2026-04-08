package com.app.base.ui

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class AppDimensions(
    val screenPadding: Dp = 16.dp,
    val defaultPadding :Dp = 16.dp,
    val miniPadding :Dp = 8.dp,
    val rowPadding :Dp = 12.dp,
    val gridSpacing: Dp = 8.dp,
    val cardElevation: Dp = 4.dp,
    val iconSmall: Dp = 24.dp,
    val roundedCornerShape:Dp = 32.dp,
    val iconMedium: Dp = 32.dp,
    val buttonHeight: Dp = 48.dp,
    val spacerSmall: Dp = 8.dp,
    val spacerMedium: Dp = 16.dp,
    val spacerLarge: Dp = 32.dp
)

// Creamos el Local para que el tema lo pueda proveer
val LocalAppDimensions = staticCompositionLocalOf { AppDimensions() }