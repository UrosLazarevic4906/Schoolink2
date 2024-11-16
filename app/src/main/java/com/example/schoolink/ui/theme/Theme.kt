package com.example.schoolink.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = Red,
    secondary = Green,
    tertiary = Yellow,

    background = White,
    surface = Cream,

    onPrimary = White,
    onSecondary = White,
    onTertiary = Black,

    onBackground = Black,
    onSurface = Black,

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun SchoolinkTheme(
    content: @Composable () -> Unit
) {
    val colorScheme =LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = SfProTypography,
        content = content
    )
}