package xyz.gmfatoom.common.requestik.presentation.request.components

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import xyz.gmfatoom.common.requestik.presentation.request.RequestListEvent
import xyz.gmfatoom.common.requestik.presentation.request.RequestListState

@Composable
fun Pagination(
    state: RequestListState, lazyListState : LazyListState, buffer: Int = 10, onEvent: (RequestListEvent) -> Unit
) {
    state.dataList?.let { datalist ->
        val layoutInfo = lazyListState.layoutInfo
        val totalItemsNumber = layoutInfo.totalItemsCount
        if (totalItemsNumber > 0) {
        val loadMoreFirst by remember {
            derivedStateOf {
                val layoutInfo1 = lazyListState.layoutInfo
                val totalItemsNumber1 = layoutInfo.totalItemsCount
                val firstVisibleItemIndex =
                    (layoutInfo1.visibleItemsInfo.firstOrNull()?.index ?: 0) + 1
                val loadMore = firstVisibleItemIndex < buffer
             //   println("totalItemsNumber: $totalItemsNumber1, firstVisibleItemIndex: $firstVisibleItemIndex, loadMoreLast: $loadMore  ")
                loadMore
            }
        }

        val loadMoreLast by remember {
            derivedStateOf {
                val layoutInfo1 = lazyListState.layoutInfo
                val totalItemsNumber1 = layoutInfo.totalItemsCount
                val lastVisibleItemIndex = (layoutInfo1.visibleItemsInfo.lastOrNull()?.index ?: 0) + 1
                val loadMore =  lastVisibleItemIndex > (totalItemsNumber1 - buffer)
               // println("totalItemsNumber: $totalItemsNumber1, lastVisibleItemIndex: $lastVisibleItemIndex, loadMoreFirst: $loadMore")
                loadMore

            }
        }
          //  println("loadMoreFirst: $loadMoreFirst,  loadMoreLast: $loadMoreLast  ")
        LaunchedEffect(loadMoreFirst) {
                snapshotFlow { loadMoreFirst }
                    .distinctUntilChanged()
                    .collect {
                        if (it) {
                            println("loadmorefirst")
                            onEvent(RequestListEvent.onUpdateDateList(datalist[(layoutInfo.visibleItemsInfo.firstOrNull()?.index ?: 0) + 1]))
                            delay(300)
                        }
                    }
            }

            LaunchedEffect(loadMoreLast) {
                snapshotFlow { loadMoreLast }
                    .collect {
                        if (it) {
                            println("loadmorelast")
                            onEvent(RequestListEvent.onUpdateDateList(datalist[(layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0) + 1]))
                            delay(300)
                        }
                    }
            }
        }
    }

}