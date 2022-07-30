package xyz.gmfatoom.questik.ui.screens.requests.edit.material

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import xyz.gmfatoom.questik.ui.screens.requests.edit.dialog.RequestDialogVM
import xyz.gmfatoom.questik.ui.screens.requests.views.RequestItemsCategoryHeader
import xyz.gmfatoom.questik.ui.screens.requests.views.RequestMaterialsChekedChildItem


@OptIn(
    ExperimentalFoundationApi::class, ExperimentalAnimationApi::class,
    ExperimentalMaterialApi::class
)
@Composable
fun RequestMaterialsTabListGrouped(
) {
    val viewModel = hiltViewModel<RequestDialogVM>()
/*    reqMaterialsState: State<MutableMap<CategoryEntity, List<RequestItemEntity>>>,*/
    val expandState = viewModel.expandableCategoryList.collectAsState()
    val reqMaterialsState = viewModel.requestMaterialsCatalog.collectAsState()
    val lazyListState: LazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    LazyColumn(state = lazyListState) {
        reqMaterialsState.value.forEach { (initial, itemsForInitial) ->
            stickyHeader {
                RequestItemsCategoryHeader(
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
                itemsIndexed(
                    items=itemsForInitial,
                    key={index,item->
                        5000000000+item.hashCode()
                    }
                ){index,item->
                    val state= rememberDismissState(
                        confirmStateChange = {
                            if (it== DismissValue.DismissedToStart){
                                itemsForInitial.toMutableList().remove(item)
                            }
                            true
                        }
                    )
                    SwipeToDismiss(
                        state = state,
                        background = {
                            val color=when(state.dismissDirection){
                                DismissDirection.StartToEnd -> Color.Transparent
                                DismissDirection.EndToStart -> Color.Red
                                null -> Color.Transparent
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(color)
                                    .padding(8.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = null,
                                    tint= Color.White,
                                    modifier = Modifier.align(Alignment.CenterEnd)
                                )
                            }

                        },
                        dismissContent = {

                            println("chernovik group selected item - ${item.name} is -  ${item.isChecked} ")
                            RequestMaterialsChekedChildItem(
                                item = item,
                                visible = expandState.value.contains(initial.id),
                                viewModel = viewModel
                            )
                        },
                        directions=setOf(DismissDirection.EndToStart)
                    )
                    Divider()
                }
            }
        }
    }
}
