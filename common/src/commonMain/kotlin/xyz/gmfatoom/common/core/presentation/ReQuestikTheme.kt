package xyz.gmfatoom.common.core.presentation

import androidx.compose.runtime.Composable

@Composable
expect fun ReQuestikTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
)