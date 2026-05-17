package com.weathersnap.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(


    background = BackgroundColor,

    surface = CardColor,

    onPrimary = DarkText,

    onBackground = DarkText,

    onSurface = DarkText
)

private val LightColorScheme = lightColorScheme(


    background = BackgroundColor,

    surface = CardColor,

    onPrimary = DarkText,

    onBackground = DarkText,

    onSurface = DarkText
)

@Composable
fun WeatherSnapTheme(

    darkTheme: Boolean =
        isSystemInDarkTheme(),

    content: @Composable () -> Unit

) {

    val colorScheme =
        if (darkTheme)
            DarkColorScheme
        else
            LightColorScheme


    MaterialTheme(

        colorScheme = colorScheme,

        typography = Typography,

        content = content
    )
}