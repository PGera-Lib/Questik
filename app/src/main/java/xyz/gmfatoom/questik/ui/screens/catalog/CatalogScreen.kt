package xyz.gmfatoom.questik.ui.screens.catalog

import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp

import androidx.compose.material.icons.filled.Search

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment

import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import xyz.gmfatoom.questik.repo.local.room.entity.CategoryEntity
import xyz.gmfatoom.questik.repo.local.room.entity.JobsEntity
import xyz.gmfatoom.questik.ui.base.views.SearchView
import xyz.gmfatoom.questik.ui.screens.catalog.item.CategoryHeader
import xyz.gmfatoom.questik.ui.screens.catalog.item.ChildItem

@ExperimentalAnimationApi
@Composable
fun Catalog(
    navController: NavController,
    viewModel: CatalogViewModel = viewModel(),
    openDrawer: () -> Unit
) {
    val expandedCatalog = viewModel.expandableCategoryList.collectAsState()
    val catalog = viewModel.categoryCard.collectAsState()
    var searchVisibility = remember { mutableStateOf(false) }
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    var searchWeght by remember {
        mutableStateOf(0.001f)
    }
    Scaffold(topBar = {
        Row(Modifier.fillMaxWidth()) {
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
                                    text = "Каталог",
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
        }
    }) {

        СatalogGrouped(
            viewModel = viewModel,
            expandState = expandedCatalog,
            catalogMapState = catalog
        )

    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun СatalogGrouped(
    viewModel: CatalogViewModel, expandState: State<List<Int>>,
    catalogMapState: State<MutableMap<CategoryEntity, List<JobsEntity>>>
) {
    val lazyListState: LazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    LazyColumn(state = lazyListState) {
        catalogMapState.value.forEach { (initial, itemsForInitial) ->
            stickyHeader {
                CategoryHeader(
                    initial,
                    expanded = expandState.value.contains(initial.id),
                    onCardArrowClick = {
                        coroutineScope.launch {
                            if (initial.id < lazyListState.firstVisibleItemIndex)
                                lazyListState.scrollToItem(index = initial.id)
                            else lazyListState.scrollToItem(index = lazyListState.firstVisibleItemIndex)
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
                    ChildItem(
                        item = item,
                        visible = expandState.value.contains(initial.id)
                    )
                }
            }
        }
    }
}




/*data class CategoryEntity(
    val id: Int,
    val title: String
)

data class JobsEntity(
    val id: Int,
    val categoryId: Int,
    val name: String,
    val price: String,
    val metrics: String,
    var count: String,
    var isSelected: Boolean
)*/

/*@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun PreviewCard() {
    ExpandableCard(
        card = SampleCategoryData(1, "one"),
        expanded = true,
        onCardArrowClick = { println("click") },
        itemsList =
    )
}*/

