package com.example.noteappfirebasecrud.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

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
fun NoteAppFirebaseCRUDTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.Transparent.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography.copy(
            titleSmall = MaterialTheme.typography.titleSmall.copy(
                fontFamily = noteAppFirebaseCrudFamily
            ),
            titleMedium = MaterialTheme.typography.titleMedium.copy(
                fontFamily = noteAppFirebaseCrudFamily
            ),
            labelMedium = MaterialTheme.typography.labelMedium.copy(
                fontFamily = noteAppFirebaseCrudFamily
            ),
            labelLarge = MaterialTheme.typography.labelLarge.copy(
                fontFamily = noteAppFirebaseCrudFamily
            ),
            headlineSmall = MaterialTheme.typography.headlineSmall.copy(
                fontFamily = noteAppFirebaseCrudFamily
            ),
            headlineMedium = MaterialTheme.typography.headlineMedium.copy(
                fontFamily = noteAppFirebaseCrudFamily
            ),
            headlineLarge = MaterialTheme.typography.headlineLarge.copy(
                fontFamily = noteAppFirebaseCrudFamily
            ),
            displaySmall = MaterialTheme.typography.displaySmall.copy(
                fontFamily = noteAppFirebaseCrudFamily
            ),
            displayMedium = MaterialTheme.typography.displayMedium.copy(
                fontFamily = noteAppFirebaseCrudFamily
            ),
            displayLarge = MaterialTheme.typography.displayLarge.copy(
                fontFamily = noteAppFirebaseCrudFamily
            ),
            bodySmall = MaterialTheme.typography.bodySmall.copy(
                fontFamily = noteAppFirebaseCrudFamily
            ),
            bodyMedium = MaterialTheme.typography.bodyMedium.copy(
                fontFamily = noteAppFirebaseCrudFamily
            ),
            bodyLarge = MaterialTheme.typography.bodyLarge.copy(
                fontFamily = noteAppFirebaseCrudFamily
            ),
            labelSmall = MaterialTheme.typography.labelSmall.copy(
                fontFamily = noteAppFirebaseCrudFamily
            ),
            titleLarge = MaterialTheme.typography.titleLarge.copy(
                fontFamily = noteAppFirebaseCrudFamily
            ),

        ),
        content = content
    )
}