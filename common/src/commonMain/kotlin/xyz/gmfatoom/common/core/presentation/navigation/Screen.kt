package xyz.gmfatoom.common.core.presentation.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryBooks
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import xyz.gmfatoom.common.core.utils.AppString


sealed class NavigationScreen(
    val route: String,
    val title: String = AppString.APP_TITLE,
    val navIcon: (@Composable () -> Unit)={},
    val objectName: String = "",
    val objectPath: String = "",
    val actionsTopBar: (@Composable RowScope.() -> Unit) = {},
){
    object ProfileScreen : NavigationScreen("/profile_screen", "Профиль", navIcon = {
        Icon(
            imageVector=    Icons.Filled.Home,
            contentDescription = "home",
            modifier = Modifier.size(24.dp)
        )
    })
    object RequestsScreen : NavigationScreen("/requests_screen", "Задачи", navIcon = {
        Icon(
            imageVector = Icons.Filled.Home,
            contentDescription = "home",
            modifier = Modifier.size(24.dp)
        )
    },
        objectName = "id" , objectPath = "/{id}?")
    object HomeScreen : NavigationScreen("/home_screen", "Главная", navIcon = {
        Icon(
            imageVector=    Icons.Filled.Home,
            contentDescription = "home",
            modifier = Modifier.size(24.dp)
        )
    })
    object GuideScreen : NavigationScreen("/guide_screen",  "База", navIcon = {
        Icon(
            imageVector= Icons.Filled.LibraryBooks,
            contentDescription = "guide",
            modifier = Modifier.size(24.dp)

        )
    })
    object SettingsScreen : NavigationScreen("/settings_screen", "Нас-ка", navIcon = {
        Icon(
            imageVector=   Icons.Filled.Settings,
            contentDescription = "settings",
            modifier = Modifier.size(24.dp)
        )
    })
    object DetailRequestScreen: NavigationScreen("/request_screen",  "Заявка: " ,
        objectName = "id" , objectPath = "/{id}")
}
