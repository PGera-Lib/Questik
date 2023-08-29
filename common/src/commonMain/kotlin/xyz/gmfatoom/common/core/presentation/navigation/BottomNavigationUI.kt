package xyz.gmfatoom.common.core.presentation.navigation

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun BottomNavigationUI(navigator: Navigator) {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf(
        NavigationScreen.RequestsScreen,
        NavigationScreen.HomeScreen,
        NavigationScreen.GuideScreen,
        NavigationScreen.ProfileScreen,
        NavigationScreen.SettingsScreen
    )


    NavigationBar(containerColor = MaterialTheme.colorScheme.primaryContainer,
    contentColor = MaterialTheme.colorScheme.onPrimaryContainer) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    item.navIcon() },
                label = { Text(item.title) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navigator.navigate(
                        item.route,
                        NavOptions(
                            launchSingleTop = true,
                        ),
                    )
                }
            )
        }
    }
}