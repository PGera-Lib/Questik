package xyz.gmfatoom.questik.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import xyz.gmfatoom.questik.ui.theme.AppTheme

@Composable
fun LargeSpacer() {
    Spacer(modifier = Modifier.size(AppTheme.paddings.largePadding))
}

@Composable
fun NormalSpacer() {
    Spacer(modifier = Modifier.size(AppTheme.paddings.defaultPadding))
}

@Composable
fun SmallSpacer() {
    Spacer(modifier = Modifier.size(AppTheme.paddings.smallPadding))
}

@Composable
fun TinySpacer() {
    Spacer(modifier = Modifier.size(AppTheme.paddings.tinyPadding))
}
