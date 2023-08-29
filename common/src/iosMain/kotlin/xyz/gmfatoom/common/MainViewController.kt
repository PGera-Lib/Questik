package xyz.gmfatoom.common

import moe.tlaster.precompose.PreComposeApplication
import xyz.gmfatoom.common.di.AppModule
import xyz.gmfatoom.common.requestik.presentation.main.App


fun MainViewController() = PreComposeApplication("reQuestik") {
    val isDarkTheme = true
    /*    UIScreen.mainScreen.traitCollection.userInterfaceStyle ==
                UIUserInterfaceStyle.UIUserInterfaceStyleDark*/

    App(
        darkTheme = isDarkTheme,
        dynamicColor = false,
        appModule = AppModule()
    )
}