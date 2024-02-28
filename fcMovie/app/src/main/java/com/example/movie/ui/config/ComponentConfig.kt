package com.example.movie.ui.config

import androidx.compose.material3.Shapes
import com.example.movie.ui.theme.color.ColorSet

data class ComponentConfig(
    val colors: ColorSet,
    val typography: Typography,
    val shapes: Shapes,
    val isDarkTheme: Boolean
)
