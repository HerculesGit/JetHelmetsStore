package com.herco.jethelmetsstore.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFF35959),
    onPrimary = Color(0xFFFFFFFF),

    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF2E2E2E),
    onSurfaceVariant = Color(0xFF949494),

    outline = Color(0xFFFAFAFA),
    outlineVariant = Color(0xFF949494)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFF35959),
    onPrimary = Color(0xFFFFFFFF),

    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF2E2E2E),
    onSurfaceVariant = Color(0xFF949494),

    outline = Color(0xFFFAFAFA),
    outlineVariant = Color(0xFF949494)
)

@Composable
fun JetHelmetsStoreTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}


//@Composable
//fun JetHelmetsStoreTheme(
//    darkTheme: Boolean = isSystemInDarkTheme(),
//    // Dynamic color is available on Android 12+
//    dynamicColor: Boolean = true,
//    content: @Composable () -> Unit
//) {
//    val colorScheme = when {
//        dynamicColor -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
//
//        darkTheme -> DarkColorScheme
//        else -> LightColorScheme
//    }
//    val view = LocalView.current
//    if (!view.isInEditMode) {
//        SideEffect {
//            val window = (view.context as Activity).window
//            window.statusBarColor = colorScheme.primary.toArgb()
//            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
//        }
//    }
//
//    MaterialTheme(
//        colorScheme = colorScheme,
//        typography = Typography,
//        content = content
//    )
//}