@file:OptIn(ExperimentalPagerApi::class)

package xyz.gmfatoom.questik.ui.screens.chernovik


import android.annotation.SuppressLint


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape



import androidx.compose.runtime.*

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


import androidx.compose.foundation.clickable
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.*


import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import xyz.gmfatoom.questik.repo.local.room.entity.CategoryEntity
import xyz.gmfatoom.questik.repo.local.room.entity.JobsEntity
import xyz.gmfatoom.questik.repo.local.room.entity.MaterialEntity
import xyz.gmfatoom.questik.ui.base.views.SearchView


import xyz.gmfatoom.questik.ui.screens.chernovik.items.change.CustomDialogCheckItems

import xyz.gmfatoom.questik.ui.screens.chernovik.items.JobsListGrouped
import xyz.gmfatoom.questik.ui.screens.chernovik.items.MaterialsListGrouped

import xyz.gmfatoom.questik.ui.screens.chernovik.itogi.ItogScreen




@OptIn(ExperimentalPagerApi::class)
@Composable
fun Chernovik(
    navController: NavController,
    viewModel: ChernovikVM = viewModel(),
    openDrawer: () -> Unit
) {
    val expandedCatalog = viewModel.expandableCategoryList.collectAsState()
    viewModel.getSelectedJobsCatalog()
    viewModel.getSelectedMaterialsCatalog()
    val selectedJobsMap = viewModel.selectedJobsCatalog.collectAsState()
    val selectedMaterialsMap = viewModel.selectedMaterialsCatalog.collectAsState()
    val catalogJobsMap = viewModel.jobsCurrentItems.collectAsState()
    val catalogMaterialsMap = viewModel.materialsCurrentItems.collectAsState()
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    var searchVisibility = remember { mutableStateOf(false) }
    val pagerState = rememberPagerState()
    var searchWeght by remember {
        mutableStateOf(0.3f)
    }
    val openDialog = remember { mutableStateOf(false) }

    if (openDialog.value) {
        CustomDialogCheckItems(onPositiveClick = {
            viewModel.getSelectedJobsCatalog()
            openDialog.value = false
        }, onDismiss = {
            openDialog.value = false
        }, viewModel = viewModel, jobsFullState = catalogJobsMap, materialsFullState = catalogMaterialsMap)
    }



    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton({ openDrawer() }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
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
                                    text = "Черновик",
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
                4 -> {  ExtendedFloatingActionButton(
                    text = { Text(text = "Действия") },
                    onClick = {

                    },
                    icon = { Icon(Icons.Filled.Menu, "") }
                )}
            }
                               },
    ) {
        Box {
            Column {
                ChernovikTabsWithSwiping(
                    viewModel = viewModel,
                    expandState = expandedCatalog,
                    jobsMapState = selectedJobsMap,
                    materialsMapState = selectedMaterialsMap,
                    pagerState=pagerState
                )
            }

        }

    }
}



@Composable
fun SetClentCard() {
    var corpName by remember { mutableStateOf("") }
    var corpUser by remember { mutableStateOf("") }
    var corpPhone by remember { mutableStateOf("") }
    var corpMail by remember { mutableStateOf("") }
    var dataStart by remember { mutableStateOf("") }
    val dataCreate by remember { mutableStateOf("01.01.2022 17:00") }
    var dataEnd by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(50f)
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Card(/*
                backgroundColor = Color.White,*/
              /*  elevation = 4.dp,*/
                shape = RoundedCornerShape(3.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 4.dp,
                        vertical = 4.dp
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row {
                        Text(
                            text = "Карточка клиента",/*
                            color = Color.Black,*/
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .weight(1f)
                                .padding(
                                    horizontal = 4.dp,
                                    vertical = 4.dp
                                )
                        )
                    }
                        OutlinedTextField(
                            value = corpName,
                            onValueChange = { corpName = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = 4.dp,
                                    vertical = 1.dp
                                ),
                            label = { Text("Наименование/логин клиента") }
                        )
                        OutlinedTextField(
                            value = corpUser,
                            onValueChange = { corpUser = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = 4.dp,
                                    vertical = 1.dp
                                ),
                            label = { Text("ФИО Заказчика: ") }
                        )
                        OutlinedTextField(
                            value = corpPhone,
                            onValueChange = { corpPhone = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = 4.dp,
                                    vertical = 1.dp
                                ),
                            label = { Text("Телефон: ") }
                        )
                        OutlinedTextField(
                            value = corpMail,
                            onValueChange = { corpMail = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = 4.dp,
                                    vertical = 1.dp
                                ),
                            label = { Text("Почта: ") }
                        )
                        Row {
                            OutlinedTextField(
                                value = dataStart,
                                onValueChange = { dataStart = it },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.CalendarToday,
                                        contentDescription = "Выбрать дату",
                                        modifier = Modifier.clickable(onClick = { println("Кликнуто на иконку выбора даты начала проекта") })
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth(0.5f)
                                    .padding(
                                        horizontal = 4.dp,
                                        vertical = 1.dp
                                    ),
                                label = { Text("Начало: ") }
                            )
                            OutlinedTextField(
                                value = dataEnd,
                                onValueChange = { dataEnd = it },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.CalendarToday,
                                        contentDescription = "Выбрать дату",
                                        modifier = Modifier.clickable(onClick = { println("Кликнуто на иконку выбора даты завершения проекта") })
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .padding(
                                        horizontal = 4.dp,
                                        vertical = 1.dp
                                    ),
                                label = { Text("Завершение: ") }
                            )
                        }
                        Text(
                            text = "Создано: $dataCreate",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = 4.dp,
                                    vertical = 4.dp
                                )
                        )
                }
            }

        }
    }
}


@Composable
@Preview
fun PreviewCompose() {
    SetClentCard()
}




@OptIn(ExperimentalPagerApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ChernovikTabsWithSwiping(
    viewModel: ChernovikVM = viewModel(),
    expandState: State<List<Int>>,
    jobsMapState: State<MutableMap<CategoryEntity, List<JobsEntity>>>,
    materialsMapState: State<MutableMap<CategoryEntity, List<MaterialEntity>>>,
    pagerState: PagerState
) {
    var tabIndex by remember { mutableStateOf(0) }
    val scope = rememberCoroutineScope()
    val tabTitles = listOf("Заявка:", "Работы:", "Материалы:", "Итоги:", "Файлы:")
    Column {
        ScrollableTabRow(selectedTabIndex = tabIndex,
            modifier = Modifier.wrapContentWidth(),
            indicator = { tabPositions ->

               tabIndex = pagerState.currentPage
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(
                        pagerState = pagerState,
                        tabPositions = tabPositions
                    )
                )
            },

            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary)
        {
            tabTitles.forEachIndexed { index, title ->
                Tab(selected = tabIndex == index,
                    onClick = { tabIndex = index
                        scope.launch {
                            pagerState.scrollToPage(tabIndex)
                        }
                              },
                    text = { Text(text = title) })
            }

        }

            HorizontalPager(
                verticalAlignment = Alignment.Top,
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxSize(),
                count = tabTitles.size,
                state = pagerState

            ) { swipeToIndex->
                when (swipeToIndex) {
                    0 -> {Column(modifier = Modifier.fillMaxSize()) {
                        SetClentCard()
                    }
                    }
                    1 -> Column(modifier = Modifier.fillMaxSize()) {
                        JobsListGrouped(
                        viewModel = viewModel,
                        expandState = expandState,
                        catalogMapState = jobsMapState
                    )
                    }
                    2 -> Column(modifier = Modifier.fillMaxSize()) {
                        MaterialsListGrouped(
                            viewModel = viewModel,
                            expandState = expandState,
                            catalogMapState = materialsMapState
                        )
                    }
                    3 -> Column(modifier = Modifier.fillMaxSize()) {
                       ItogScreen(viewModel = viewModel, catalogJobsMapState = jobsMapState, catalogMaterialsMapState = materialsMapState)}
                    4 -> Column(modifier = Modifier.fillMaxSize()) {
                        Text("Тут будут файлы")}
                }
            }
        }
}



