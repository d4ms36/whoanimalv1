package com.whoanimal.core.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

private val MaterialLightColors = lightColorScheme(
    primary = WhoAnimalPalette.Forest,
    onPrimary = WhoAnimalPalette.Cream,
    secondary = WhoAnimalPalette.DeepTeal,
    onSecondary = WhoAnimalPalette.Cream,
    tertiary = WhoAnimalPalette.Sage,
    background = WhoAnimalPalette.Mist,
    onBackground = WhoAnimalPalette.ForestDark,
    surface = WhoAnimalPalette.Cream,
    onSurface = WhoAnimalPalette.ForestDark
)

@Composable
fun WhoAnimalTheme(
    colors: WhoAnimalColors = WhoAnimalColors(),
    typography: WhoAnimalTypography = WhoAnimalTypography(),
    shapes: WhoAnimalShapes = WhoAnimalShapes(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalWhoAnimalColors provides colors,
        LocalWhoAnimalTypography provides typography,
        LocalWhoAnimalShapes provides shapes
    ) {
        MaterialTheme(
            colorScheme = MaterialLightColors,
            content = content
        )
    }
}

object WhoAnimalTheme {
    val colors: WhoAnimalColors
        @Composable
        @ReadOnlyComposable
        get() = LocalWhoAnimalColors.current

    val typography: WhoAnimalTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalWhoAnimalTypography.current

    val shapes: WhoAnimalShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalWhoAnimalShapes.current
}
