package xyz.gmfatoom.questik.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Applier
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import xyz.gmfatoom.questik.ui.theme.attr.*

private val PurpleDarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200,
    onPrimary = Color.White
)

private val PurpleLightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun PurpleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    AppTheme(
        lightColorPalette = PurpleLightColorPalette,
        darkColorPalette = PurpleDarkColorPalette,
        darkTheme = darkTheme,
        content = content
    )
}
