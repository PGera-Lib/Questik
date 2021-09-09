package xyz.gmfatoom.questik.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import xyz.gmfatoom.questik.ui.theme.attr.*

@SuppressLint("ConflictingOnColor")
private val GreyGreenDarkColorPalette = darkColors(
    primary = GrayGreenDarkPrimary,
    onPrimary = Color.White,

    secondary = GrayGreenLightPrimary,
    primaryVariant = GrayGreen200,
    surface = GrayGreen400

)

@SuppressLint("ConflictingOnColor")
private val GreyGreenLightColorPalette = lightColors(
    primary = GrayGreenLightPrimary,
    onPrimary = Color.White,

    secondary = GrayGreen400,
    primaryVariant = GrayGreen500,
    surface = GrayGreen200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun GreyGreenTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    AppTheme(
        lightColorPalette = GreyGreenLightColorPalette,
        darkColorPalette = GreyGreenDarkColorPalette,
        darkTheme = darkTheme,
        content = content
    )
}
