package xyz.gmfatoom.questik.ui.screens.requests.item

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import xyz.gmfatoom.questik.ui.base.views.SearchView
import xyz.gmfatoom.questik.ui.screens.requests.edit.ReqTabsView
import xyz.gmfatoom.questik.ui.screens.requests.edit.RequestEditorVM

@OptIn(ExperimentalPagerApi::class)
@Composable
fun RequestEditor(reqId: String?, navController: NavController, viewModel: RequestEditorVM) {


    if (!reqId.isNullOrEmpty()) {

        viewModel.setRequestId(reqId.toInt())

    }
    val requestId = viewModel.requestId.collectAsState()


/*
    val request = viewModel.request.collectAsState()
    val contacts = viewModel.contacts.collectAsState()
    val corp = viewModel.corp.collectAsState()
    val reqObject = viewModel.reqObject.collectAsState()
    val jobsState = viewModel.requestJobsCatalog.collectAsState()
    val materialState = viewModel.requestMaterialsCatalog.collectAsState()

 */


    val textState = remember { mutableStateOf(TextFieldValue("")) }
    var searchVisibility = remember { mutableStateOf(false) }
    val pagerState = rememberPagerState()
    var searchWeght by remember {
        mutableStateOf(0.3f)
    }
    val openDialog = remember { mutableStateOf(false) }

    if (openDialog.value) {
        RequestItemsAddDialog(onPositiveClick = {
            openDialog.value = false
        }, onDismiss = {
            openDialog.value = false
        })
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton({

                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Menu navigation"
                        )
                    }
                },
                actions = {
                    if (!searchVisibility.value) {
                        println("menu search click")

                        IconButton({
                            searchVisibility.value = !searchVisibility.value
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = "Search"
                            )
                        }
                    }
                },
                title = {
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        if (searchVisibility.value) {
                            Row(modifier = Modifier.fillMaxWidth()) {
                                SearchView(textState, searchVisibility)
                                viewModel.serchedFilter(textState.value.text)
                            }
                        } else {
                            Row {
                                Text(
                                    text = "Заявки",
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier
                                        .padding(5.dp)
                                )
                            }
                        }
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary
            )
        },
        floatingActionButton = {
            when (pagerState.currentPage) {
                0 -> {
                    ExtendedFloatingActionButton(
                        text = { Text(text = "Сохранить в заявках") },
                        onClick = {
                            viewModel.saveFullRequest()
                            //  viewModel.saveFullRequest(/*request = request.value, contactsEntity = contacts.value, corp = corp.value, items = viewModel.getFullRequestItems(jobsState.value, materialState.value ), objectsEntity =reqObject.value */)
                        },
                        icon = { Icon(Icons.Filled.Add, "") }
                    )
                }
                1 -> ExtendedFloatingActionButton(
                    text = { Text(text = "Добавить") },
                    onClick = {
                        openDialog.value = true
                    },
                    icon = { Icon(Icons.Filled.Add, "") }
                )
                2 -> ExtendedFloatingActionButton(
                    text = { Text(text = "Добавить") },
                    onClick = {
                        openDialog.value = true
                    },
                    icon = { Icon(Icons.Filled.Add, "") }
                )
                3 -> {
                    ExtendedFloatingActionButton(
                        text = { Text(text = "Сохранить в заявках") },
                        onClick = {

                        },
                        icon = { Icon(Icons.Filled.Add, "") }
                    )
                }
                4 -> {
                    ExtendedFloatingActionButton(
                        text = { Text(text = "Действия") },
                        onClick = {

                        },
                        icon = { Icon(Icons.Filled.Menu, "") }
                    )
                }
            }
        },
    ) {
        Box {
            Column {
                /*            ReqTabsView(
                                viewModel = viewModel,
                                pagerState = pagerState,
                              requestState = request,
                                corpState = corp,
                                contactsState = contacts,
                                reqObjectState = reqObject,
                                reqJobsState = jobsState,
                                reqMaterialsState = materialState
                            )*/
                ReqTabsView(
                    reqId = reqId,
                    viewModel = viewModel,
                    pagerState = pagerState,
                )
            }
        }
    }
}