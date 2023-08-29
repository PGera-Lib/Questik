package xyz.gmfatoom.common.requestik.presentation.request.components

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import xyz.gmfatoom.common.requestik.presentation.request.RequestListEvent

@Composable
fun Pagination(
    listState: LazyListState, buffer: Int = 4, onEvent: () -> Unit
) {
    val loadMoreFirst = remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            //val totalItemsNumber = layoutInfo.totalItemsCount
            val firstVisibleItemIndex = (layoutInfo.visibleItemsInfo.firstOrNull()?.index ?: 0) + 1
            val loadMoreFirst = firstVisibleItemIndex < 15
            println("2 firstVisibleItemIndex: $firstVisibleItemIndex,  loadMore: $loadMoreFirst ")

            loadMoreFirst
        }
    }
    val loadMoreLast = remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val totalItemsNumber = layoutInfo.totalItemsCount
            val lastVisibleItemIndex = (layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0) + 1
            val loadMoreLast = lastVisibleItemIndex > (totalItemsNumber - buffer)
            println("1 totalItemsNumber: $totalItemsNumber, lastVisibleItemIndex: $lastVisibleItemIndex,  loadMore: $loadMoreLast ")
            loadMoreLast
        }
    }

/*    val endReached = remember {
        derivedStateOf {
            (listState.layoutInfo.totalItemsCount  <  listState.firstVisibleItemIndex+buffer) && (listState.isScrollInProgress)
        }
    }
    val startReached = remember {
        derivedStateOf {
            (listState.firstVisibleItemIndex<buffer) && (listState.isScrollInProgress)
        }
    }
    LaunchedEffect(endReached) {
        snapshotFlow { endReached.value }
            .distinctUntilChanged()
            .collect {
                if (it) {
                    onEvent(RequestListEvent.onDataListUpdateLastItem)
                }
            }
    }
    LaunchedEffect(startReached) {
        snapshotFlow { startReached.value }
            .distinctUntilChanged()
            .collect {
                if (it) {
                    onEvent(RequestListEvent.onDataListUpdateFirstItem)
                }
            }
    }*/
    LaunchedEffect(loadMoreFirst) {
        snapshotFlow { loadMoreFirst.value }
            .distinctUntilChanged()
            .collect {
                if (it) {
                    println("loadmorefirst")
                    /*        firstVisibleItemIndex = listState.firstVisibleItemIndex
                            lastTotalItems = listState.layoutInfo.totalItemsCount
                            println("2 lastTotalItems: $lastTotalItems, firstVisibleItemIndex: $firstVisibleItemIndex,  loadMore: $loadMore ")*/
                    onEvent()
                    delay(300)
                    println("scroll")
                    //listState.scrollToItem(1)
                }
            }
    }


    LaunchedEffect(loadMoreLast) {
        snapshotFlow { loadMoreLast.value }
            .distinctUntilChanged()
            .collect {
                if (it) {
            /*        firstVisibleItemIndex = listState.firstVisibleItemIndex
                    lastTotalItems = listState.layoutInfo.totalItemsCount
                    println("2 lastTotalItems: $lastTotalItems, firstVisibleItemIndex: $firstVisibleItemIndex,  loadMore: $loadMore ")*/
                    onEvent()
                    delay(300)
                        println("scroll")
                      listState.scrollToItem(1)
                }
            }
    }





}