package xyz.gmfatoom.common.core.presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toLocalDate
import kotlinx.datetime.toLocalDateTime
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.viewmodel.viewModel
import xyz.gmfatoom.common.di.AppModule
import xyz.gmfatoom.common.requestik.presentation.main.AppMainEvent
import xyz.gmfatoom.common.requestik.presentation.main.AppMainState
import xyz.gmfatoom.common.requestik.presentation.request.RequestListEvent
import xyz.gmfatoom.common.requestik.presentation.request.RequestListScreen
import xyz.gmfatoom.common.requestik.presentation.request.RequestListState
import xyz.gmfatoom.common.requestik.presentation.request.RequestListViewModel


@Composable
fun Navigation(navigator: Navigator, appModule: AppModule, appMainState: AppMainState,
               actionsTopBar: (@Composable RowScope.() -> Unit) = {},) {
    NavHost(navigator = navigator, initialRoute = NavigationScreen.HomeScreen.route) {
        scene(route = NavigationScreen.HomeScreen.route) {
            //  HomeScreen(navigator)
            Column {
                Text(text = "Home")
            }
        }

        scene(route = NavigationScreen.ProfileScreen.route) {
            Column {
                Text(text = "profile")
            }
        }

        scene(route = NavigationScreen.GuideScreen.route) {
            Column {
                Text(text = "Guide")
            }
        }
        scene(route = NavigationScreen.SettingsScreen.route) {
            Column {
                Text(text = "Settings")
            }
        }


        scene(route = NavigationScreen.RequestsScreen.route.plus(NavigationScreen.RequestsScreen.objectPath)) { backStackEntry ->
            backStackEntry.path<String>(NavigationScreen.RequestsScreen.objectName).let { pathid ->
                val requestViewModel = viewModel {
                    RequestListViewModel(appModule.requestikDataSource)
                }
                val onEvent = requestViewModel::onEvent
/*                val state by requestViewModel.state.collectAsState()*/
                        RequestListScreen(
                            viewModel = requestViewModel,
                            onEvent = onEvent
                        )
            }
        }



        scene(route = NavigationScreen.DetailRequestScreen.route.plus(NavigationScreen.DetailRequestScreen.objectPath)) { backStackEntry ->
            val id: Int? = backStackEntry.path<Int>(NavigationScreen.DetailRequestScreen.objectName)
            id?.let {
                //  MovieDetail(navigator, it)
            }
        }

    }
}

@Composable
fun currentRoute(navigator: Navigator): String? {
    return navigator.currentEntry.collectAsState(null).value?.route?.route
}

@Composable
fun currentNavTitele(navigator: Navigator): String? {
    return when (currentRoute(navigator)) {
        NavigationScreen.HomeScreen.route -> {
            NavigationScreen.HomeScreen.title
        }

        NavigationScreen.ProfileScreen.route -> {
            NavigationScreen.ProfileScreen.title
        }

        NavigationScreen.SettingsScreen.route -> {
            NavigationScreen.SettingsScreen.title
        }

        NavigationScreen.RequestsScreen.route.plus(NavigationScreen.RequestsScreen.objectPath) -> {
            NavigationScreen.RequestsScreen.title
        }

        NavigationScreen.GuideScreen.route -> {
            NavigationScreen.GuideScreen.title
        }

        else -> {
            "NoTitle"
        }
    }
}

