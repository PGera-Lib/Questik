package xyz.gmfatoom.questik.ui.screens.requests

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import xyz.gmfatoom.questik.repo.local.room.entity.ContactsEntity
import xyz.gmfatoom.questik.repo.local.room.entity.CorpEntity
import xyz.gmfatoom.questik.repo.local.room.entity.ObjectsEntity
import xyz.gmfatoom.questik.ui.drawer.DrawerScreens
import xyz.gmfatoom.questik.ui.screens.requests.item.RequestScreenItem

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun RequestsScreen(
    navController: NavController,
    openDrawer: () -> Unit,
    viewModel: RequestScreeenVM
) {

    val requestsListstate = viewModel.requestsListState.collectAsState()

    viewModel.refreshRequestList()
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
                        text = "Заявки",
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
    },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = "Добавить задачу") },
                onClick = {
                    navController.navigate(DrawerScreens.RequestEditor.route + "?reqId=")
                },
                icon = { Icon(Icons.Filled.Add, "") }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val lazyListState: LazyListState = rememberLazyListState()
            val coroutineScope = rememberCoroutineScope()

            LazyColumn(state = lazyListState) {
                if (requestsListstate.value.isNotEmpty()) {
                    items(
                        items = requestsListstate.value,
                        key = { item ->
                            item.id
                        }
                    ) { item ->
                        val reqObject = remember { mutableStateOf(ObjectsEntity()) }
                        val reqCorp = remember { mutableStateOf(CorpEntity()) }
                        val reqContacts = remember { mutableStateOf(ContactsEntity()) }
                        coroutineScope.launch(Dispatchers.Default) {
                            val objects = viewModel.getRequestObject(item.object_id)
                            val corp = viewModel.getRequestCorp(item.corp_id)
                            val contact = viewModel.getRequestContacts(item.contacts_id)

                            delay(400)
                            reqCorp.value = corp
                            reqObject.value = objects
                            reqContacts.value = contact

                        }
                        RequestScreenItem(
                            req = item,
                            navController = navController,
                            reqObject = reqObject.value,
                            corp = reqCorp.value,
                            contacts = reqContacts.value
                        )
                    }
                } else {
                    /*                   item {
                                           RequestScreenItem(req = RequestsEntity(
                                               id =0,
                                               name = "test-test",
                                               user_id = 1,
                                               corp_id = 1,
                                               object_id = 1,
                                               data_create = "02.02.2022 22:22",
                                               data_start = "02.02.2022 22:22",
                                               data_end = "02.02.2022 22:22"
                                           ), navController = navController
                                           )
                                       }*/
                }
            }
        }
    }
}