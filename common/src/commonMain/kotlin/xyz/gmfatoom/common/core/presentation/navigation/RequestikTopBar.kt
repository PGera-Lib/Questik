package xyz.gmfatoom.common.core.presentation.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import moe.tlaster.precompose.navigation.Navigator


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarWithArrow(
    title: String?,
    isBackEnable: Boolean = false,
    navigation: Navigator,
    pressOnBack: () -> Unit,
    actions: (@Composable RowScope.() -> Unit) = {},
) {
    TopAppBar(modifier = Modifier.fillMaxWidth(),
        title = { title?.let { Text(text = it) } },
        navigationIcon = {
            Row {
                Spacer(modifier = Modifier.width(10.dp))
                if (isBackEnable) {
                    Image(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "navback",
                        modifier = Modifier.align(Alignment.CenterVertically).clickable {
                            pressOnBack()
                        },
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimaryContainer)
                    )
                }/*       Text(text = title ?: "",
                           style = MaterialTheme.typography.titleMedium
                       )*/
            }
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        actions = {
            actions()
        }

    )
}


@Composable
fun isBackButtonEnable(navigator: Navigator): Boolean {
    return when (currentRoute(navigator)) {
        NavigationScreen.HomeScreen.route,
        NavigationScreen.ProfileScreen.route,
        NavigationScreen.SettingsScreen.route,
        NavigationScreen.GuideScreen.route,
        NavigationScreen.RequestsScreen.route.plus(
            NavigationScreen.RequestsScreen.objectPath) -> {
            false
        }

        else -> {
            true
        }
    }
}
