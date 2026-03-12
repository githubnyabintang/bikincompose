package com.example.bikincompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme =
        darkColorScheme(
                primary = GreenPrimary,
                secondary = GreenAccent,
                tertiary = TealAction,
                background = TextPrimary,
                surface = TextPrimary,
        )

private val LightColorScheme =
        lightColorScheme(
                primary = GreenPrimary,
                secondary = GreenAccent,
                tertiary = TealAction,
                background = BackgroundWhite,
                surface = SurfaceCard,
                onPrimary = Color.White,
                onSecondary = TextPrimary,
                onBackground = TextPrimary,
                onSurface = TextPrimary,
        )

@Composable
fun BikincomposeTheme(
        darkTheme: Boolean = isSystemInDarkTheme(),
        dynamicColor: Boolean = false,
        content: @Composable () -> Unit
) {
        val colorScheme =
                when {
                        darkTheme -> DarkColorScheme
                        else -> LightColorScheme
                }

        MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}
