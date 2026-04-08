package com.app.base.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.app.base.ui.AppDimensions
import com.app.base.ui.LocalAppDimensions
/**
 * @author : DeyberParra
 * @description : App theme configurations*/
@Composable
fun DevJourneyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {

    val LightColorScheme = lightColorScheme(
        primary = PrimaryBlue,
        onPrimary = Color.White,
        primaryContainer = PrimaryBlueContainer,
        onPrimaryContainer = OnPrimaryBlueContainer,
        secondary = SecondarySlate,
        secondaryContainer = SecondarySlateContainer,
        tertiary = TertiaryGold,
        tertiaryContainer = TertiaryGoldContainer,
        background = SurfaceWhite,
        surface = SurfaceWhite,
        surfaceVariant = SurfaceVariantGray,
        onSurface = OnSurfaceBlack,
        onSurfaceVariant = OnSurfaceVariantGray,
        outline = Color(0xFF757684)
    )

    val DarkColorScheme = darkColorScheme(
        primary = Color(0xFFADC6FF),
        onPrimary = Color(0xFF001D6F),
        primaryContainer = Color(0xFF24389C),
        onPrimaryContainer = Color(0xFFDDE1FF),
        background = Color(0xFF1A1B22),
        surface = Color(0xFF1A1B22),
        onSurface = Color(0xFFE3E1EA),
        surfaceVariant = Color(0xFF454652),
        onSurfaceVariant = Color(0xFFC5C5D4)
    )
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else ->  LightColorScheme

    }


    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

object AppTheme {
    val dimensions: AppDimensions
        @Composable
        get() = LocalAppDimensions.current
}