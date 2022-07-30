package xyz.gmfatoom.questik.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.insets.statusBarsPadding
import xyz.gmfatoom.questik.MainViewModel
import xyz.gmfatoom.questik.ui.theme.AppTheme
import xyz.gmfatoom.questik.utils.APP_ACTIVITY
import xyz.gmfatoom.questik.utils.AUTH

@Composable
fun Home(mainViewModel: MainViewModel, navController: NavController, openDrawer: () -> Unit) {

    if (!APP_ACTIVITY.isUpdatable) {
        APP_ACTIVITY.updateDatabase()
        mainViewModel.updateUserAuth()
    }

    HomeContent(openDrawer)
}

@Composable
private fun HomeContent(openDrawer: () -> Unit) {
    Scaffold(topBar = {
        Spacer(
            modifier = Modifier
                .background(color = AppTheme.colors.onError)
                .fillMaxWidth()
                .statusBarsPadding()
        )
        TopAppBar(
            navigationIcon = {
                IconButton({ openDrawer() }) {
                    Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.Center) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Menu navigation"
                    )
                    }
                }
            },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Home",
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
            Text(text = "Home Page content here.")
        }
    }
}
@Preview
@Composable
fun ContentPreview(){
/*    AppTheme {
        HomeContent({})
    }*/
}