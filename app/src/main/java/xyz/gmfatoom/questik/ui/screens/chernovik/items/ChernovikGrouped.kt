package xyz.gmfatoom.questik.ui.screens.chernovik.items

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import xyz.gmfatoom.questik.repo.local.room.entity.CategoryEntity
import xyz.gmfatoom.questik.repo.local.room.entity.JobsEntity
import xyz.gmfatoom.questik.repo.local.room.entity.MaterialEntity

import xyz.gmfatoom.questik.ui.screens.chernovik.ChernovikVM
import xyz.gmfatoom.questik.ui.screens.chernovik.items.change.MaterialsChildItem
import xyz.gmfatoom.questik.ui.screens.chernovik.items.change.MaterialsSelectedChildItem

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun JobsListGrouped(
    viewModel: ChernovikVM = viewModel(), expandState: State<List<Int>>,
    catalogMapState: State<MutableMap<CategoryEntity, List<JobsEntity>>>
) {
    val jobsMapState = viewModel.selectedJobsCatalog.collectAsState()
    val lazyListState: LazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    LazyColumn(state = lazyListState) {
        jobsMapState.value.forEach { (initial, itemsForInitial) ->
            stickyHeader {
                ChernovikCategoryHeader(
                    initial,
                    expanded = expandState.value.contains(initial.id),
                    onCardArrowClick = {
                        coroutineScope.launch {
                            if (initial.id < lazyListState.firstVisibleItemIndex) {
                                println(" 1) Индекс категории - ${initial.id} индекс первого в списке - ${lazyListState.firstVisibleItemIndex} индекс первого в офсете - ${lazyListState.firstVisibleItemScrollOffset}")
                                lazyListState.scrollToItem(index = lazyListState.firstVisibleItemIndex)

                            } else {
                                println(" 2) Индекс категории - ${initial.id} индекс первого в списке - ${lazyListState.firstVisibleItemIndex} индекс первого в офсете - ${lazyListState.firstVisibleItemScrollOffset}")
                                lazyListState.scrollToItem(index = lazyListState.firstVisibleItemIndex)

                            }
                        }
                        viewModel.cardArrowClick(initial.id)
                    },
                    viewModel = viewModel,
                    size = itemsForInitial.size

                )
            }
            if (expandState.value.contains(initial.id)) {
                items(
                    items = itemsForInitial,
                    key = { item ->
                        item.id
                    }
                ) { item ->
                        JobsSelectedChildItem(
                            item = item,
                            visible = expandState.value.contains(initial.id),
                            viewModel = viewModel
                        )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun MaterialsListGrouped(
    viewModel: ChernovikVM = viewModel(), expandState: State<List<Int>>,
    catalogMapState: State<MutableMap<CategoryEntity, List<MaterialEntity>>>
) {
    val materialsMapState = viewModel.selectedMaterialsCatalog.collectAsState()
    val lazyListState: LazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    LazyColumn(state = lazyListState) {
        materialsMapState.value.forEach { (initial, itemsForInitial) ->
            stickyHeader {
                ChernovikCategoryHeader(
                    initial,
                    expanded = expandState.value.contains(initial.id),
                    onCardArrowClick = {
                        coroutineScope.launch {
                            if (initial.id < lazyListState.firstVisibleItemIndex) {
                                println(" 1) Индекс категории - ${initial.id} индекс первого в списке - ${lazyListState.firstVisibleItemIndex} индекс первого в офсете - ${lazyListState.firstVisibleItemScrollOffset}")
                                lazyListState.scrollToItem(index = lazyListState.firstVisibleItemIndex)

                            } else {
                                println(" 2) Индекс категории - ${initial.id} индекс первого в списке - ${lazyListState.firstVisibleItemIndex} индекс первого в офсете - ${lazyListState.firstVisibleItemScrollOffset}")
                                lazyListState.scrollToItem(index = lazyListState.firstVisibleItemIndex)

                            }
                        }
                        viewModel.cardArrowClick(initial.id)
                    },
                    viewModel = viewModel,
                    size = itemsForInitial.size

                )
            }
            if (expandState.value.contains(initial.id)) {
                items(
                    items = itemsForInitial,
                    key = { item ->
                        item.id
                    }
                ) { item ->
                        MaterialsSelectedChildItem(
                            item = item,
                            visible = expandState.value.contains(initial.id),
                            viewModel = viewModel
                        )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun SelectDialogJobsTabListGrouped(
    viewModel: ChernovikVM = viewModel(), expandState: State<List<Int>>,
    catalogMapState: State<MutableMap<CategoryEntity, List<JobsEntity>>>
) {
    val jobsMapState = viewModel.jobsCurrentItems.collectAsState()
    val lazyListState: LazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    LazyColumn(state = lazyListState) {
        jobsMapState.value.forEach { (initial, itemsForInitial) ->
            stickyHeader {
                ChernovikCategoryHeader(
                    initial,
                    expanded = expandState.value.contains(initial.id),
                    onCardArrowClick = {
                        coroutineScope.launch {
                                lazyListState.scrollToItem(index = lazyListState.firstVisibleItemIndex)
                        }
                        viewModel.cardArrowClick(initial.id)
                    },
                    viewModel = viewModel,
                    size = itemsForInitial.size

                )
            }
            if (expandState.value.contains(initial.id)) {
                items(
                    items = itemsForInitial,
                    key = { item ->
                        item.id
                    }
                ) { item ->
                    println("chernovik group selected item - ${item.name} is -  ${item.isSelected} ")
                        JobsChildItem(
                            item = item,
                            visible = expandState.value.contains(initial.id),
                            viewModel = viewModel
                        )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun SelectDialogMaterialsTabListGrouped(
    viewModel: ChernovikVM = viewModel(), expandState: State<List<Int>>,
    catalogMapState: State<MutableMap<CategoryEntity, List<MaterialEntity>>>
) {
    val materialsMapState = viewModel.materialsCurrentItems.collectAsState()
    val lazyListState: LazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    LazyColumn(state = lazyListState) {
        materialsMapState.value.forEach { (initial, itemsForInitial) ->
            stickyHeader {
                ChernovikCategoryHeader(
                    initial,
                    expanded = expandState.value.contains(initial.id),
                    onCardArrowClick = {
                        coroutineScope.launch {
                            if (initial.id < lazyListState.firstVisibleItemIndex) {
                                println(" 1) Индекс категории - ${initial.id} индекс первого в списке - ${lazyListState.firstVisibleItemIndex} индекс первого в офсете - ${lazyListState.firstVisibleItemScrollOffset}")
                                lazyListState.scrollToItem(index = lazyListState.firstVisibleItemIndex)

                            } else {
                                println(" 2) Индекс категории - ${initial.id} индекс первого в списке - ${lazyListState.firstVisibleItemIndex} индекс первого в офсете - ${lazyListState.firstVisibleItemScrollOffset}")
                                lazyListState.scrollToItem(index = lazyListState.firstVisibleItemIndex)

                            }
                        }
                        viewModel.cardArrowClick(initial.id)
                    },
                    viewModel = viewModel,
                    size = itemsForInitial.size

                )
            }
            if (expandState.value.contains(initial.id)) {
                items(
                    items = itemsForInitial,
                    key = { item ->
                        item.id
                    }
                ) { item ->
                    println("chernovik group selected item - ${item.name} is -  ${item.isSelected} ")
                    MaterialsChildItem(
                        item = item,
                        visible = expandState.value.contains(initial.id),
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}


@Composable
fun SearchView(state: MutableState<TextFieldValue>) {
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
        },
        modifier = Modifier
            .fillMaxWidth()/*,
        textStyle = TextStyle(color = Color.White, fontSize = 18.sp)*/,
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )
        },
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        state.value =
                            TextFieldValue("") // Remove text from TextField when you press the 'X' icon
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        singleLine = true,
        shape = RectangleShape, // The TextFiled has rounded corners top left and right by default
/*        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            cursorColor = Color.White,
            leadingIconColor = Color.White,
            trailingIconColor = Color.White,
            backgroundColor = MaterialTheme.colors.primaryVariant,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )*/
    )
}
@Preview(showBackground = true)
@Composable
fun SearchViewPreview() {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    SearchView(textState)
}

@Preview(showBackground = true)
@Composable
fun ChernovikGroupedViewPreview() {
    JobsListGrouped(
        viewModel = viewModel(),
        catalogMapState = MutableStateFlow(mutableMapOf<CategoryEntity, List<JobsEntity>>()).collectAsState(),
        expandState = MutableStateFlow(listOf<Int>()).collectAsState()
    )
}