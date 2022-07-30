package xyz.gmfatoom.questik.ui.screens.requests.edit

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch
import xyz.gmfatoom.questik.ui.screens.chernovik.itogi.RequestItog
import xyz.gmfatoom.questik.ui.screens.requests.edit.job.RequestJobsTabListGrouped
import xyz.gmfatoom.questik.ui.screens.requests.edit.material.RequestMaterialsTabListGrouped
import xyz.gmfatoom.questik.ui.screens.requests.edit.reqinfo.ReqClientCard
import xyz.gmfatoom.questik.ui.screens.requests.edit.dialog.RequestDialogVM


@OptIn(ExperimentalPagerApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ReqTabsView(reqId: String?,
                viewModel: RequestEditorVM,
                pagerState: PagerState
) {
/*fun ReqTabsView(
   requestState: State<RequestsEntity>,
    corpState: State<CorpEntity>,
    contactsState: State<ContactsEntity>,
    reqObjectState: State<ObjectsEntity>,
    reqJobsState: State<MutableMap<CategoryEntity, List<RequestItemEntity>>>,
    reqMaterialsState: State<MutableMap<CategoryEntity, List<RequestItemEntity>>>,
    viewModel: RequestItemVM,
    pagerState: PagerState
) {*/
/*    var expandState: State<List<Int>>
    var requestMapState: State<MutableMap<CategoryEntity, List<RequestItemEntity>>>*/
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
                0 -> {
                    Column(modifier = Modifier.fillMaxSize()) {
                        ReqClientCard(viewModel= viewModel, reqId = reqId)
                }
                }
                1 -> Column(modifier = Modifier.fillMaxSize()) {
            /*        RequestJobsTabListGrouped(reqJobsState, viewModel)*/
                    RequestJobsTabListGrouped()

                }
                2 -> Column(modifier = Modifier.fillMaxSize()) {
                 /*   RequestMaterialsTabListGrouped(reqMaterialsState,viewModel)*/
                    RequestMaterialsTabListGrouped()
                }
                3 -> Column(modifier = Modifier.fillMaxSize()) {
                    RequestItog()
                }
                4 -> Column(modifier = Modifier.fillMaxSize()) {
                    Text("Тут будут файлы")
                }
            }
        }
    }
}