package xyz.gmfatoom.common.requestik.presentation.request.components

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import xyz.gmfatoom.common.requestik.domain.model.RequestModel

import xyz.gmfatoom.common.requestik.presentation.request.RequestListEvent
import xyz.gmfatoom.common.requestik.presentation.request.RequestListState
import xyz.gmfatoom.common.requestik.presentation.request.RequestListViewModel

@Composable
fun RequestList(
    modifier: Modifier = Modifier,
    requests: List<RequestModel>,
    onEvent: (RequestListEvent) -> Unit
) {
    val lazyListState = rememberLazyListState()

    val composableScope = rememberCoroutineScope()
        requests.let {
            LazyColumn (
                modifier = Modifier.draggable(
                    orientation = Orientation.Vertical,
                    state = rememberDraggableState { delta ->
                        composableScope.launch {
                            lazyListState.scrollBy(-delta)
                        }
                    },
                ), state = lazyListState
            ) {
                itemsIndexed(items = it, key = { _, item: RequestModel ->
                    item.id.toInt()
                }) { index: Int, request: RequestModel ->
                  RequestListItem(request = request, onEvent = onEvent)
                }
            }

        }
}