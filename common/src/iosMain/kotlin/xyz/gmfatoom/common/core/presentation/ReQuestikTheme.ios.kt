package xyz.gmfatoom.common.core.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import xyz.gmfatoom.common.ui.theme.DarkColorScheme
import xyz.gmfatoom.common.ui.theme.LightColorScheme
import xyz.gmfatoom.common.ui.theme.Typography

@Composable
actual fun ReQuestikTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if(darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}