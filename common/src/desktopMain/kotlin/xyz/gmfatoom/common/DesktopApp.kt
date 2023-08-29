package xyz.gmfatoom.common

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import xyz.gmfatoom.common.di.AppModule
import xyz.gmfatoom.common.requestik.presentation.main.App

@Preview
@Composable
fun AppPreview() {
   Surface {
      App(darkTheme = true, dynamicColor = false, appModule = AppModule())
   }
}