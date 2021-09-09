package xyz.gmfatoom.questik

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.insets.statusBarsPadding

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import xyz.gmfatoom.questik.repo.QuestikRepository

import xyz.gmfatoom.questik.ui.base.SplashScreen
import xyz.gmfatoom.questik.ui.base.views.ProgressBarLoader
import xyz.gmfatoom.questik.ui.components.ThemeType
import xyz.gmfatoom.questik.ui.components.ThemeType.*
import xyz.gmfatoom.questik.ui.drawer.Drawer
import xyz.gmfatoom.questik.ui.drawer.DrawerScreens
import xyz.gmfatoom.questik.ui.drawer.screens
import xyz.gmfatoom.questik.ui.screens.catalog.Catalog
import xyz.gmfatoom.questik.ui.screens.catalog.CatalogViewModel
import xyz.gmfatoom.questik.ui.screens.chat.Chat
import xyz.gmfatoom.questik.ui.screens.chernovik.Chernovik
import xyz.gmfatoom.questik.ui.screens.chernovik.ChernovikVM
import xyz.gmfatoom.questik.ui.screens.contacts.Contacts
import xyz.gmfatoom.questik.ui.screens.faq.Faq
import xyz.gmfatoom.questik.ui.screens.home.Home
import xyz.gmfatoom.questik.ui.screens.login.LoginScreen
import xyz.gmfatoom.questik.ui.screens.requests.RequestsScreen
import xyz.gmfatoom.questik.ui.screens.requests.item.RequestEditor
import xyz.gmfatoom.questik.ui.screens.requests.edit.dialog.RequestDialogVM
import xyz.gmfatoom.questik.ui.screens.requests.RequestScreeenVM
import xyz.gmfatoom.questik.ui.screens.requests.edit.RequestEditorVM
import xyz.gmfatoom.questik.ui.screens.settings.Settings
import xyz.gmfatoom.questik.ui.theme.*
import xyz.gmfatoom.questik.utils.APP_ACTIVITY
import xyz.gmfatoom.questik.utils.AUTH
import xyz.gmfatoom.questik.utils.initFirebaseCompose
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var repository: QuestikRepository
    val catalogViewModel by viewModels<CatalogViewModel>()
    val chernovikVM by viewModels<ChernovikVM>()
    val mainViewModel: MainViewModel by viewModels()
    val requestsVM by viewModels<RequestScreeenVM>()
    var isUpdatable:Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        mainViewModel.getDatabaseCount()
        initFirebaseCompose()
        mainViewModel.updateUserAuth()
        isUpdatable = mainViewModel.authIs.value
        updateDatabase()

        APP_ACTIVITY = this
        setContent {
            val darkMode = remember { mutableStateOf(false) }
            val themeType = remember { mutableStateOf(GRAYGREEN) }

            val themeFunction: @Composable (
                isDarkMode: Boolean, content: @Composable () -> Unit
            ) -> Unit =
                when (themeType.value) {
                    GRAYGREEN -> { isDarkMode, content -> GreyGreenTheme(isDarkMode, content) }
                    YELLOW -> { isDarkMode, content -> YellowTheme(isDarkMode, content) }
                    WHITE -> { isDarkMode, content -> WhiteTheme(isDarkMode, content) }
          /*          PINK -> { isDarkMode, content -> PinkTheme(isDarkMode, content) }*/
                }

            themeFunction.invoke(darkMode.value) {

            //    OverallView(darkMode, themeType)

                Surface() {
                    //  val databaseCount = mainViewModel.databaseCount.collectAsState()
                    val showProgress = mainViewModel.progresIsShow.collectAsState()
                    val progresStatus = mainViewModel.progressStatus.collectAsState()
                    println("ProgressStatus is - ${progresStatus.value}, showProgress is - ${showProgress.value}")
                    Column(modifier = Modifier.fillMaxSize())
                    {
                        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top) {
                            Spacer(
                                modifier = Modifier
                                    .background(color = AppTheme.colors.secondaryVariant)
                                    .fillMaxWidth()
                                    .statusBarsPadding()
                            )
                        }
                            AppMainScreen(darkMode, themeType)

                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            ProgressBarLoader(isShown = showProgress.value, text = progresStatus)
                        }
                    }
                }
            }




/*

            AppTheme() {
                Surface() {
                    //  val databaseCount = mainViewModel.databaseCount.collectAsState()
                    val showProgress = mainViewModel.progresIsShow.collectAsState()
                    val progresStatus = mainViewModel.progressStatus.collectAsState()
                    println("ProgressStatus is - ${progresStatus.value}, showProgress is - ${showProgress.value}")
                    Box(modifier = Modifier.fillMaxSize())
                    {
                        Box {
                            AppMainScreen()
                        }
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            ProgressBarLoader(isShown = showProgress.value, text = progresStatus)
                        }
                    }
                }
            }*/
        }
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    fun AppMainScreen(darkMode: MutableState<Boolean>, themeType: MutableState<ThemeType>) {
        var currentScreen = remember { mutableStateOf(screens[0]) }
        val navController = rememberNavController()
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            val openDrawer = {
                scope.launch {
                    drawerState.open()
                }
            }
            @Composable
            fun customShape() = object : Shape {
                override fun createOutline(
                    size: Size,
                    layoutDirection: LayoutDirection,
                    density: Density
                ): Outline {
                    return Outline.Rectangle(
                        Rect(
                            left = 0f,
                            top = 0f,
                            right = size.width * 2 / 3,
                            bottom = size.height
                        )
                    )
                }
            }

            ModalDrawer(
                drawerShape = customShape(),
                drawerState = drawerState,
                gesturesEnabled = drawerState.isOpen,
                drawerContent = {
                    Drawer(
                        onDestinationClicked = { route ->
                            scope.launch {
                                drawerState.close()
                            }
                            navController.navigate(route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            ) {

if (AUTH.currentUser!=null) {
    currentScreen.value=DrawerScreens.Home
} else {
    currentScreen.value=DrawerScreens.Login
 //   navController.navigate(DrawerScreens.Login.route)
}
                // if(AUTH.currentUser!=null) { } else navController.navigate(DrawerScreens.Login.route)
                NavHost(
                    navController = navController,
                    startDestination = currentScreen.value.route
                ) {

                    composable(DrawerScreens.Splash.route) {
                        SplashScreen(
                            navController = navController
                        )
                    }

                    composable(DrawerScreens.Login.route) {
                        LoginScreen(
                            navController = navController
                        )
                    }

                    composable(DrawerScreens.Home.route) {
                        Home(mainViewModel = mainViewModel,
                            navController = navController,
                            openDrawer = {
                                openDrawer()
                            }
                        )
                    }
                    composable(DrawerScreens.Request.route) {
                        RequestsScreen(
                            navController = navController,
                            openDrawer = {
                                openDrawer()
                            }
                        , viewModel = requestsVM)
                    }

                    composable(DrawerScreens.RequestEditor.route+"?reqId={reqId}", arguments =  listOf(navArgument("reqId")
                    { defaultValue = "" })) {
                        val viewModel = hiltViewModel<RequestEditorVM>()
                        RequestEditor(
                            navController = navController,
                            viewModel = viewModel, reqId = it.arguments?.getString("reqId")
                        )
                    }

                    composable(DrawerScreens.Settings.route) {
                        currentScreen.value = DrawerScreens.Settings
                        Settings(
                            navController = navController,
                            darkMode, themeType)
                    }
                    composable(DrawerScreens.Catalog.route) {
                        Catalog(viewModel = catalogViewModel,
                            navController = navController,
                            openDrawer = {
                                openDrawer()
                            }
                        )
                    }
                    composable(DrawerScreens.Chernovik.route) {
                        Chernovik(viewModel = chernovikVM,
                            navController = navController,
                            openDrawer = {
                                openDrawer()
                            }
                        )
                    }
                    composable(DrawerScreens.Contacts.route) {
                        Contacts(
                            navController = navController,
                            openDrawer = {
                                openDrawer()
                            }
                        )
                    }
                    composable(DrawerScreens.Chat.route) {
                        Chat(
                            navController = navController,
                            openDrawer = {
                                openDrawer()
                            }
                        )
                    }
                    composable(DrawerScreens.Faq.route) {
                        Faq(
                            navController = navController,
                            openDrawer = {
                                openDrawer()
                            }
                        )
                    }
                }
            }
        }
 //   }
    @Preview(showBackground = false)
    @Composable
    fun DefaultPreview() {
/*        AppTheme() {
            AppMainScreen()
        }*/
    }
    fun updateDatabase() {
        mainViewModel.initDatabaseCatalog()
    }


}

