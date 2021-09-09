package xyz.gmfatoom.questik.ui.screens.chernovik.items.change

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import xyz.gmfatoom.questik.repo.local.room.entity.CategoryEntity
import xyz.gmfatoom.questik.repo.local.room.entity.JobsEntity
import xyz.gmfatoom.questik.repo.local.room.entity.MaterialEntity

import xyz.gmfatoom.questik.ui.screens.chernovik.ChernovikVM
import xyz.gmfatoom.questik.ui.screens.chernovik.items.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CustomDialogCheckItems(
    viewModel: ChernovikVM = viewModel(),
    jobsFullState: State<MutableMap<CategoryEntity, List<JobsEntity>>>,
    materialsFullState: State<MutableMap<CategoryEntity, List<MaterialEntity>>>,
    onDismiss: () -> Unit,
    onPositiveClick: () -> Unit
) {
    val pagerState = rememberPagerState()
    Dialog(onDismissRequest = onDismiss) {
        Card{
            Column(modifier = Modifier
                .padding(4.dp)/*
                .background(Color.White)*/) {
                Text(text = "Выберите необходимые позиции из списка: ")

                var text by remember { mutableStateOf(TextFieldValue("")) }
                val expandedCat = viewModel.expandableCategoryList.collectAsState()
                OutlinedTextField(
                    value = text,
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "searchIcon"
                        )
                    },
                    //trailingIcon = { Icon(imageVector = Icons.Default.Add, contentDescription = null) },
                    onValueChange = {
                        text = it
                            viewModel.serchedFilter(text.text)
                    },
                    label = { Text(text = "Поиск по каталогу") },
                    placeholder = { Text(text = "Введите текст поиска") },
                )

                Divider(color = Color.LightGray, thickness = 1.dp)


                Column(Modifier.weight(0.5f)) {
                    DialogTabsWithSwiping(
                        viewModel = viewModel,
                        expandState = expandedCat,
                        jobsMapState =jobsFullState,
                        materialsMapState=materialsFullState,
                        pagerState = pagerState
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(onClick = onDismiss) {
                        Text(text = "Отмена")
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    TextButton(onClick = {
                        onPositiveClick()
                    }) {
                        Text(text = "Выбрать")
                    }
                }
            }
        }
        }
}


/*
@Composable
@Preview
fun PreviewDialog() {
    CustomDialogCheckItems(onDismiss = {}, onPositiveClick = {})
}*/
@SuppressLint("CoroutineCreationDuringComposition")
@ExperimentalPagerApi // 1.
@Composable
fun DialogTabsWithSwiping(
    viewModel: ChernovikVM = viewModel(),
    expandState: State<List<Int>>,
    jobsMapState: State<MutableMap<CategoryEntity, List<JobsEntity>>>,
    materialsMapState: State<MutableMap<CategoryEntity, List<MaterialEntity>>>,
    pagerState: PagerState
) {
    var tabIndex by remember { mutableStateOf(0) }
    val scope = rememberCoroutineScope()
    val tabTitles = listOf("Работы:", "Материалы:")
    Column {
        ScrollableTabRow(selectedTabIndex = tabIndex,
            modifier = Modifier.wrapContentWidth(),
            indicator = { tabPositions ->
                tabIndex = pagerState.currentPage
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(
                        pagerState,
                        tabPositions
                    )
                )
            }) {
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
                0 -> Column(modifier = Modifier.fillMaxSize()) {
                    SelectDialogJobsTabListGrouped(
                        viewModel = viewModel,
                        expandState = expandState,
                        catalogMapState = jobsMapState
                    )
                }
                1 -> Column(modifier = Modifier.fillMaxSize()) {
                    SelectDialogMaterialsTabListGrouped(
                        viewModel = viewModel,
                        expandState = expandState,
                        catalogMapState = materialsMapState
                    )
                }
            }
        }
    }
}