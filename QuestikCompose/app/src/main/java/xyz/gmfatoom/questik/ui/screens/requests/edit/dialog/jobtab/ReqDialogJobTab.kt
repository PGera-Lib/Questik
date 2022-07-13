package xyz.gmfatoom.questik.ui.screens.requests.edit.dialog.jobtab

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import xyz.gmfatoom.questik.ui.screens.requests.edit.dialog.RequestDialogVM
import xyz.gmfatoom.questik.ui.screens.requests.views.RequestItemsCategoryHeader
import xyz.gmfatoom.questik.ui.screens.requests.views.RequestJobsChildItem

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun RequestDialogJobsTabListGrouped(
) {
    val viewModel = hiltViewModel<RequestDialogVM>()
    val expandState = viewModel.expandableCategoryList.collectAsState()
    val jobsMapState = viewModel.fullJobsCatalog.collectAsState()
    val lazyListState: LazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    LazyColumn(state = lazyListState) {
        jobsMapState.value.forEach { (initial, itemsForInitial) ->
            stickyHeader {
                RequestItemsCategoryHeader(
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
                itemsIndexed(
                    items=itemsForInitial,
                    key={index,item->
                        100000000+item.hashCode()
                    }
                ){index,item->
                    println("chernovik group selected item - ${item.name} is -  ${item.isSelected} ")
                    RequestJobsChildItem(
                        item = item,
                        visible = expandState.value.contains(initial.id),
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}
