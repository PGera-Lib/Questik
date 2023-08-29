package xyz.gmfatoom.android

import xyz.gmfatoom.common.requestik.presentation.main.App
import android.os.Bundle
import moe.tlaster.precompose.lifecycle.PreComposeActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import moe.tlaster.precompose.lifecycle.setContent
import xyz.gmfatoom.common.di.AppModule

class MainActivity : PreComposeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
                App(  darkTheme = isSystemInDarkTheme(),
                    dynamicColor = true,
                    appModule = AppModule(LocalContext.current.applicationContext)
                )
        }
    }
}


