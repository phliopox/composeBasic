package com.example.movie.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.movie.ui.config.ComponentConfig
import com.example.movie.ui.config.DefaultComponentConfig
import com.example.movie.ui.theme.color.ColorSet
import com.example.movie.ui.theme.color.MyColors

private val LocalColors = staticCompositionLocalOf { ColorSet.Red.LightColors }

/*myColors: ColorSet = ColorSet.Red,
typography: Typography = Typography,
darkTheme: Boolean = isSystemInDarkTheme(),
shapes: Shapes = Shapes,
// Dynamic color is available on Android 12+
dynamicColor: Boolean = true,
content: @Composable () -> Unit*/
@Composable
fun MovieTheme(
    themeState: State<ComponentConfig> = mutableStateOf(
        DefaultComponentConfig.RED_THEME
    ),
    content: @Composable () -> Unit
) {
    val myTheme by remember { themeState }

    val colors = if (myTheme.isDarkTheme) {
        myTheme.colors.DarkColors
    } else {
        myTheme.colors.LightColors
    }

    val view = LocalView.current
 /*   if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }*/
    CompositionLocalProvider(LocalColors provides colors) {
        MaterialTheme(
            colorScheme = colors.material,
            typography = typography,
            shapes = shapes,
            content = content
        )
    }
}

val MaterialTheme.colorscheme: MyColors
    @Composable
    @ReadOnlyComposable
    get() = LocalColors.current