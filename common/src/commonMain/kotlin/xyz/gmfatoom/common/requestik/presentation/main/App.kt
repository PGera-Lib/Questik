package xyz.gmfatoom.common.requestik.presentation.main


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.MutableStateFlow
import moe.tlaster.precompose.navigation.BackHandler
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModel
import xyz.gmfatoom.common.core.presentation.ReQuestikTheme
import xyz.gmfatoom.common.core.presentation.navigation.AppBarWithArrow
import xyz.gmfatoom.common.core.presentation.navigation.BottomNavigationUI
import xyz.gmfatoom.common.core.presentation.navigation.Navigation
import xyz.gmfatoom.common.core.presentation.navigation.NavigationScreen
import xyz.gmfatoom.common.core.presentation.navigation.currentNavTitele
import xyz.gmfatoom.common.core.presentation.navigation.currentRoute
import xyz.gmfatoom.common.core.presentation.navigation.isBackButtonEnable
import xyz.gmfatoom.common.di.AppModule
import xyz.gmfatoom.common.requestik.presentation.request.RequestListEvent
import xyz.gmfatoom.common.requestik.presentation.request.RequestListViewModel
import xyz.gmfatoom.common.utils.DateTimeUtil.getDataList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    darkTheme: Boolean, dynamicColor: Boolean,
    appModule: AppModule
) {
    val appViewModel = viewModel {
        AppMainViewModel()
    }

    val state by appViewModel.state.collectAsState()


    val navigator = rememberNavigator()
  ReQuestikTheme(
        darkTheme = darkTheme, dynamicColor = dynamicColor
    ) {
              val isAppBarVisible = remember { mutableStateOf(true) }
              val searchProgressBar = remember { mutableStateOf(false) }

              BackHandler(isAppBarVisible.value.not()) {
                  isAppBarVisible.value = true
              }

              Scaffold(topBar = {
                  when (currentRoute(navigator)) {
                      NavigationScreen.HomeScreen.route,
                      NavigationScreen.ProfileScreen.route,
                      NavigationScreen.SettingsScreen.route,
                      NavigationScreen.RequestsScreen.route
                          .plus(NavigationScreen.RequestsScreen.objectPath),
                      NavigationScreen.GuideScreen.route -> {
                          AppBarWithArrow(
                              currentNavTitele(navigator),
                              isBackEnable = isBackButtonEnable(navigator),
                              navigation = navigator,
                              pressOnBack = {navigator.goBack()}
                          )
                      }

                  }

                  if (isAppBarVisible.value.not()) {

                  } else {

                  }
              }, bottomBar = {
                  when (currentRoute(navigator)) {
                      NavigationScreen.HomeScreen.route,
                      NavigationScreen.ProfileScreen.route,
                      NavigationScreen.SettingsScreen.route,
                      NavigationScreen.RequestsScreen.route
                          .plus(NavigationScreen.RequestsScreen.objectPath),
                      NavigationScreen.GuideScreen.route -> {
                          BottomNavigationUI(navigator=navigator)
                      }
                  }

              }) {innerPadding ->
                  Box(modifier = Modifier.padding(innerPadding)){        Navigation(navigator, appModule, appMainState = state)  }



              }


      }


}




