package com.example.weathertrackerproject.app.ui

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import com.example.compose.ui.theme.*

private val DarkColorPalette = darkColorScheme(
    background = DarckBlue,
    primary = DarckBluePrimary,
    primaryContainer = BLueDeap,
    secondary = White,
    tertiary = Gray
)

private val LightColorPalette = lightColorScheme(
    background = LightBlue,
    primary = LightBluePrimary,
    primaryContainer = BLueDeap,
    secondary = Black,
    tertiary = Gray
)

@Composable
fun ComposeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    val view = LocalView.current
    val window = (view.context as Activity).window
    window.statusBarColor = colors.background.toArgb()
    window.navigationBarColor = colors.background.toArgb()

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}