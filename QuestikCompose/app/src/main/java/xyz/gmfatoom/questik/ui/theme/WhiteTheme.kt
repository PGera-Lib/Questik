package xyz.gmfatoom.questik.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import xyz.gmfatoom.questik.ui.theme.attr.*

@SuppressLint("ConflictingOnColor")
private val WhiteDarkColorPalette = darkColors(
    primary = Gray,
    onPrimary = White,
    primaryVariant = LightGray,
    secondary = Gray,
    surface = White
)

@SuppressLint("ConflictingOnColor")
private val WhiteLightColorPalette = lightColors(
    primary = LightGray,
    onPrimary= Black,
    primaryVariant = LightGray,
    secondary = White,
    onSecondary = Black,
    surface = LightGray,


 //   background = Color.White,
/*    surface = Color.White,*/
/*    onSecondary = Color.Black,*/
    onBackground = Color.Black,
    onSurface = Color.Black,

)

@Composable
fun WhiteTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    AppTheme(
        lightColorPalette = WhiteLightColorPalette,
        darkColorPalette = WhiteDarkColorPalette,
        darkTheme = darkTheme,
        content = content
    )
}
