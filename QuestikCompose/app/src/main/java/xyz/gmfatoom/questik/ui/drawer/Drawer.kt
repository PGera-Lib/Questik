package xyz.gmfatoom.questik.ui.drawer

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*

import androidx.compose.material.*

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


import xyz.gmfatoom.questik.R
import xyz.gmfatoom.questik.ui.theme.AppTheme
import xyz.gmfatoom.questik.utils.AUTH

sealed class DrawerScreens(val title: String, val route: String) {

    /**
     * Меню Навигации
     */
    object Home : DrawerScreens("Главная", "home")
    object Request : DrawerScreens("Заявки", "requests")
    object Catalog : DrawerScreens("Каталог", "catalog")
    object Chernovik : DrawerScreens("Черновик", "chernovik")
    object Settings : DrawerScreens("Настройки", "settings")
    object Chat : DrawerScreens("Общение", "chat")
    object Faq : DrawerScreens("ЧаВо", "f.a.q")
    object Contacts : DrawerScreens("Контакты", "contacts")
    object RequestEditor : DrawerScreens("Заполнение заявки", "request")

    /**
     * Страницы перехода
     */


    object Login : DrawerScreens("Авторизация", "login")
    object Splash : DrawerScreens("Приветсвие", "splash")

}

val screens = listOf(
    DrawerScreens.Home,
    DrawerScreens.Request,
    DrawerScreens.Catalog,
    DrawerScreens.Chernovik,
    DrawerScreens.Contacts,
    DrawerScreens.Chat,
    DrawerScreens.Faq,
    DrawerScreens.Settings,

)

@Composable
fun Drawer(
    onDestinationClicked: (route: String) -> Unit
) {

    val isLogout by remember {  mutableStateOf(false) }
    val expandCard = remember { mutableStateOf(false) } // Expand State
    val rotationCardArrowState by animateFloatAsState(if (expandCard.value) 180f else 0f) // Rotation State

    val selectedItem = remember { mutableStateOf(screens[0]) }

    Box(
        modifier = Modifier
            .fillMaxWidth(0.66f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
           UserCardDrawer(rotationCardArrowState, expandCard)
            if (expandCard.value) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp, start = 4.dp, end = 4.dp)
                        .clickable {
                            AUTH.signOut()
                            if (isLogout) onDestinationClicked(DrawerScreens.Login.route) else onDestinationClicked(
                                DrawerScreens.Login.route
                            )
                        },
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "Выход",
                        fontStyle = FontStyle.Italic,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier
                            .clickable {
                                AUTH.signOut()
                                if (isLogout) onDestinationClicked(DrawerScreens.Login.route) else onDestinationClicked(
                                    DrawerScreens.Login.route
                                )
                            }
                            .padding(start = 10.dp)
                    )
                    Icon(
                        imageVector = Icons.Filled.Logout,
                        contentDescription = "logout",
                        modifier = Modifier.padding(3.dp)
                    )

                }
            }

            Spacer(Modifier.height(12.dp))
            Row {
            Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "icon",
                    modifier = Modifier.padding(3.dp)
                )
                Text(
                    text = DrawerScreens.Home.title,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .clickable {
                            onDestinationClicked(DrawerScreens.Home.route)
                        }
                        .padding(start = 10.dp)
                )

            }

            Spacer(Modifier.height(6.dp))


            Divider(color = Color.LightGray, thickness = 1.dp)


            Row {
                Icon(
                    imageVector = Icons.Filled.Collections,
                    contentDescription = "icon",
                    modifier = Modifier.padding(3.dp)
                )
                Text(
                    text = DrawerScreens.Request.title,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .clickable {
                            onDestinationClicked(DrawerScreens.Request.route)
                        }
                        .padding(start = 10.dp)
                )
            }
            Spacer(Modifier.height(12.dp))
            Row {
                Icon(
                    imageVector = Icons.Filled.Category,
                    contentDescription = "icon",
                    modifier = Modifier.padding(3.dp)
                )
                Text(
                    text = DrawerScreens.Catalog.title,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .clickable {
                            onDestinationClicked(DrawerScreens.Catalog.route)
                        }
                        .padding(start = 10.dp)
                )
            }
            Spacer(Modifier.height(12.dp))
            Row {
                Icon(
                    imageVector = Icons.Filled.Pages,
                    contentDescription = "icon",
                    modifier = Modifier.padding(3.dp)
                )
                Text(
                    text = DrawerScreens.Chernovik.title,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .clickable {
                            onDestinationClicked(DrawerScreens.Chernovik.route)
                        }
                        .padding(start = 10.dp)
                )
            }

                    Spacer(Modifier.height(6.dp))

            Divider(color = Color.LightGray, thickness = 1.dp)

            Spacer(Modifier.height(6.dp))


                    Row {
                Icon(
                    imageVector = Icons.Filled.Contacts,
                    contentDescription = "icon",
                    modifier = Modifier.padding(3.dp)
                )
                Text(
                    text = DrawerScreens.Contacts.title,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .clickable {
                            onDestinationClicked(DrawerScreens.Contacts.route)
                        }
                        .padding(start = 10.dp)
                )
            }
                   Spacer(Modifier.height(12.dp))
            Row {
                Icon(
                    imageVector = Icons.Filled.Chat,
                    contentDescription = "icon",
                    modifier = Modifier.padding(3.dp)
                )
                Text(
                    text = DrawerScreens.Chat.title,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .clickable {
                            onDestinationClicked(DrawerScreens.Chat.route)
                        }
                        .padding(start = 10.dp)
                )
            }


            Spacer(Modifier.height(6.dp))

            Divider(color = Color.LightGray, thickness = 1.dp)

            Spacer(Modifier.height(6.dp))


                  Row {
                Icon(
                    imageVector = Icons.Filled.HelpCenter,
                    contentDescription = "icon",
                    modifier = Modifier.padding(3.dp)
                )
                Text(
                    text = DrawerScreens.Faq.title,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .clickable {
                            onDestinationClicked(DrawerScreens.Faq.route)
                        }
                        .padding(start = 10.dp)
                )
            }

            Spacer(Modifier.height(12.dp))
            Row {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "icon",
                    modifier = Modifier.padding(3.dp)
                )
                Text(
                    text = DrawerScreens.Settings.title,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .clickable {
                            onDestinationClicked(DrawerScreens.Settings.route)
                        }
                        .padding(start = 10.dp)
                )
            }

        }

    }
/*
    screens.forEach { screen ->
            Spacer(Modifier.height(12.dp))
        }
        */

}

@Composable
fun UserCardDrawer(
    rotationCardArrowState: Float,
    expandCard: MutableState<Boolean>
) {
    Card(
        modifier = Modifier
            .padding(top = 4.dp, start = 4.dp, end = 4.dp)
            .fillMaxWidth()
            .fillMaxHeight(0.17f)

    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize()
        ) {
            Row {
                Image(
                    painter = painterResource(R.drawable.ic_baseline_android_24),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 4.dp, start = 4.dp)
                        .size(80.dp)
                        .clip(CircleShape)
                        .border(
                            width = 4.dp,
                            color = MaterialTheme.colors.primary,
                            shape = CircleShape
                        )
                )

                Column(
                    Modifier
                        .padding(4.dp)
                        .weight(2f),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = "User",
                        modifier = Modifier
                            .padding(start = 8.dp, top = 8.dp),
                        fontStyle = FontStyle.Italic,
                        style = MaterialTheme.typography.h6,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "mail@user.ru", modifier = Modifier
                            .padding(start = 8.dp, top = 2.dp), fontStyle = FontStyle.Italic
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Bottom

            ) {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Расширение карты",
                    modifier = Modifier
                        .padding(
                            horizontal = 4.dp,
                            vertical = 4.dp
                        )
                        .clip(CircleShape)
                        .size(20.dp)
                        .rotate(rotationCardArrowState)
                        .clickable(onClick = { expandCard.value = !expandCard.value})
                )
            }
        }
    }
}

@Composable
fun NavigationDrawerItem(icon: () -> Unit, label: () -> Unit, selected: Boolean, onClick: () -> Unit, modifier: Any) {

}

@Preview
@Composable
fun DrawerPreview() {
/*    AppTheme {
        Drawer {}
    }*/
}