package xyz.gmfatoom.questik.ui.screens.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import xyz.gmfatoom.questik.ui.components.OverallView
import xyz.gmfatoom.questik.ui.components.ThemeType
import xyz.gmfatoom.questik.ui.components.TopBar
import xyz.gmfatoom.questik.ui.drawer.DrawerScreens

@Composable
fun Settings (navController: NavController, darkMode: MutableState<Boolean>,
              themeType: MutableState<ThemeType>
) {
    Scaffold(topBar = {
        TopAppBar(
            navigationIcon = {
                IconButton({navController.popBackStack()}) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Menu navigation"
                    )
                }
            },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Settings",
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .weight(1.3f)
                            .padding(5.dp)
                    )
                }
            },
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary
        )
    }) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
          //  Text(text = "Settings Page content here.")
            OverallView(darkMode, themeType)
        }
    }
}