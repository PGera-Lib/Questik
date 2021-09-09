package xyz.gmfatoom.questik.ui.screens.chat

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import xyz.gmfatoom.questik.ui.components.TopBar

@Composable
fun Chat(navController: NavController,
    openDrawer: () -> Unit) {
    Scaffold(topBar = {
        TopAppBar(
            navigationIcon = {
                IconButton({ openDrawer() }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Menu navigation"
                    )
                }
            },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Chat",
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
            Text(text = "Chat Page content here.")
        }
    }
}