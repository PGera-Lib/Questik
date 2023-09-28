import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.application
import moe.tlaster.precompose.PreComposeWindow
import xyz.gmfatoom.common.requestik.presentation.main.App
import xyz.gmfatoom.common.di.AppModule


fun main() = application {
    PreComposeWindow(onCloseRequest = ::exitApplication, title = "ReQuestik") {
    App(darkTheme = true, dynamicColor = false, appModule = AppModule())
    }
}
@Preview
@Composable
private fun AppPreview() {
    App(darkTheme = true, dynamicColor = false, appModule = AppModule())
}