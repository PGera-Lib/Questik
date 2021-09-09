package xyz.gmfatoom.questik.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import xyz.gmfatoom.questik.ui.theme.AppTheme


@Composable
fun OverallView(
    darkMode: MutableState<Boolean>,
    themeType: MutableState<ThemeType>
) {
    ProvideWindowInsets {
        Column {
            Scaffold(
                modifier = Modifier.weight(1f),/*
                topBar = { MyTopAppBar(themeType.value) },*/
                isFloatingActionButtonDocked = true,
                floatingActionButton = { MyFloatingButton() }
            ) {
                Surface(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.padding(
                            AppTheme.paddings.largePadding
                        )
                    ) {
                        SettingView(darkMode = darkMode, themeType = themeType)
                       // MainView(Modifier.weight(1f))
                    }
                }
            }
            Spacer(
                modifier = Modifier
                    .background(color = AppTheme.colors.primaryVariant)
                    .fillMaxWidth()
                    .navigationBarsPadding()
            )
        }
    }
}

@Composable
fun MyTopAppBar(theme: ThemeType) {
    Column {
        Spacer(
            modifier = Modifier
                .background(color = AppTheme.colors.primaryVariant)
                .fillMaxWidth()
                .statusBarsPadding()
        )
        TopAppBar(
            title = { Text(theme.name) },
            backgroundColor = AppTheme.colors.primary
        )
    }
}

@Composable
fun MyFloatingButton() {
    val scope = rememberCoroutineScope()
    FloatingActionButton(onClick = {
    }) {
        Text("X")
    }
}
