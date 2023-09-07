package xyz.gmfatoom.common.requestik.presentation.request.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text


import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import xyz.gmfatoom.common.requestik.presentation.request.RequestListEvent
import xyz.gmfatoom.common.requestik.presentation.request.RequestListViewModel
import xyz.gmfatoom.common.utils.getLocalDayOfWeak

@Composable
fun FilterHeader(
    modifier: Modifier = Modifier,
    viewModel: RequestListViewModel,
    onEvent: (RequestListEvent) -> Unit,
) {
    val lazyListState = rememberLazyListState()
    val composableScope = rememberCoroutineScope()
    viewModel.state.collectAsState().value.let {
        println(
            " onHeader 1 first visible day is ${it.firstVisibleDay}  " +
                    "list count is ${it.dataList?.size} last Date is ${it.dataList?.last()}"
        )
        if (!it.dataList.isNullOrEmpty()) {
                LazyRow(
                    modifier = Modifier.draggable(
                        orientation = Orientation.Horizontal,
                        state = rememberDraggableState { delta ->
                            composableScope.launch {
                                lazyListState.scrollBy(-delta)
                            }
                        },
                    ), state = lazyListState
                ) {
                    itemsIndexed(items = it.dataList, key = { _, item: LocalDate ->
                        item.toString()
                    }) { index: Int, day: LocalDate ->
                        if (it.sellectedDay.toString() == day.toString()) {
                            CurrentItemDayCard(day = day, onFilterCardClick = {
                                onEvent(RequestListEvent.onRequestSelectDataChanged(day))
                            })
                        } else {
                            ItemDayCard(day = day, onFilterCardClick = {
                                onEvent(RequestListEvent.onRequestSelectDataChanged(day))
                            })
                        }
                    }
                }
                LaunchedEffect(Unit) {
                    lazyListState.scrollToItem(index = it.dataList.indexOf(it.firstVisibleDay))
                    println("Filter Header update LAST, scroll to first visible day - ${it.firstVisibleDay} ")
                }

                Pagination(state = it, lazyListState = lazyListState, onEvent = onEvent)

 /*               val loadMoreLast by remember {
                    derivedStateOf {
                        lazyListState.firstVisibleItemIndex > 15
                    }
                }
                LaunchedEffect(loadMoreLast) {
                    onEvent(RequestListEvent.onUpdateDateList(it.dataList.last()))
                    println("Filter Header update LAST, first it  - it ${it.dataList.first()}  last - ${it.dataList.last()}")
                }
                val loadMoreFirst by remember {
                    derivedStateOf {
                        lazyListState.firstVisibleItemIndex < 15
                    }
                }
                LaunchedEffect(loadMoreFirst) {
                    onEvent(RequestListEvent.onUpdateDateList(it.dataList.first()))
                    println("Filter Header update FIRST, first it  - it ${it.dataList.first()}  last - ${it.dataList.last()}")
                }*/

            }
    }
}


@Composable
fun ItemDayCard(day: LocalDate, onFilterCardClick: () -> Unit) {
    Card(modifier = Modifier.padding(4.dp).clip(
        RoundedCornerShape(
            topStart = 0.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 0.dp
        )
    ).background(Color.Transparent).padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
        .alpha(1f).clickable { onFilterCardClick() }) {


        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),

            ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                modifier = Modifier.width(71.dp).height(100.dp).clip(
                    RoundedCornerShape(
                        topStart = 5.dp, topEnd = 5.dp, bottomStart = 5.dp, bottomEnd = 5.dp
                    )
                ).background(
                    Color(
                        red = 0.9583333134651184f,
                        green = 0.9583333134651184f,
                        blue = 0.9583333134651184f,
                        alpha = 1f
                    )
                )

                    .padding(start = 6.dp, top = 8.dp, end = 6.dp, bottom = 18.dp)

                    .alpha(1f)


            ) {


                Text(
                    text = day.getLocalDayOfWeak(),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    textDecoration = TextDecoration.None,
                    letterSpacing = 0.sp,

                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.width(59.dp).alpha(1f),
                    color = Color(red = 0f, green = 0f, blue = 0f, alpha = 1f),
                    fontWeight = FontWeight.SemiBold,
                    fontStyle = FontStyle.Normal,
                )

                Text(
                    text = day.dayOfMonth.toString(),
                    textAlign = TextAlign.Center,
                    fontSize = 10.sp,
                    textDecoration = TextDecoration.None,
                    letterSpacing = 0.sp,

                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.width(59.dp).alpha(1f),
                    color = Color(
                        red = 0.41960784792900085f,
                        green = 0.4156862795352936f,
                        blue = 0.4431372582912445f,
                        alpha = 1f
                    ),
                    fontWeight = FontWeight.Medium,
                    fontStyle = FontStyle.Normal,
                )


                Text(
                    text = day.year.toString(),
                    textAlign = TextAlign.Center,
                    fontSize = 10.sp,
                    textDecoration = TextDecoration.None,
                    letterSpacing = 0.sp,

                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier

                        .width(59.dp)

                        //.height(12.dp)

                        .alpha(1f),
                    color = Color(
                        red = 0.41960784792900085f,
                        green = 0.4156862795352936f,
                        blue = 0.4431372582912445f,
                        alpha = 1f
                    ),
                    fontWeight = FontWeight.Medium,
                    fontStyle = FontStyle.Normal,
                )
            }
        }
    }
}

@Composable
fun CurrentItemDayCard(day: LocalDate, onFilterCardClick: () -> Unit) {
    Card(modifier = Modifier.padding(4.dp).clip(
        RoundedCornerShape(
            topStart = 0.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 0.dp
        )
    ).background(Color.Transparent).padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
        .alpha(1f).clickable { onFilterCardClick() }) {


        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),

            ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                modifier = Modifier

                    .width(71.dp).height(100.dp).clip(
                        RoundedCornerShape(
                            topStart = 5.dp, topEnd = 5.dp, bottomStart = 5.dp, bottomEnd = 5.dp
                        )
                    ).background(
                        Color(
                            red = 0.11372548341751099f,
                            green = 0.6809410452842712f,
                            blue = 1f,
                            alpha = 1f
                        )
                    ).padding(start = 6.dp, top = 8.dp, end = 6.dp, bottom = 18.dp).alpha(1f)
            ) {


                Text(
                    text = day.getLocalDayOfWeak(),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    textDecoration = TextDecoration.None,
                    letterSpacing = 0.sp,

                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier

                        .width(59.dp)

                        //.height(12.dp)

                        .alpha(1f),
                    color = Color(red = 1f, green = 1f, blue = 1f, alpha = 1f),
                    fontWeight = FontWeight.SemiBold,
                    fontStyle = FontStyle.Normal,
                )

                Text(
                    text = day.dayOfMonth.toString(),
                    textAlign = TextAlign.Center,
                    fontSize = 10.sp,
                    textDecoration = TextDecoration.None,
                    letterSpacing = 0.sp,

                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.width(59.dp)
                        //.height(12.dp)
                        .alpha(1f),
                    color = Color(red = 1f, green = 1f, blue = 1f, alpha = 1f),
                    fontWeight = FontWeight.Medium,
                    fontStyle = FontStyle.Normal,
                )


                Text(
                    text = day.year.toString(),
                    textAlign = TextAlign.Center,
                    fontSize = 10.sp,
                    textDecoration = TextDecoration.None,
                    letterSpacing = 0.sp,

                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier

                        .width(59.dp)

                        //.height(12.dp)

                        .alpha(1f),
                    color = Color(red = 1f, green = 1f, blue = 1f, alpha = 1f),
                    fontWeight = FontWeight.Medium,
                    fontStyle = FontStyle.Normal,
                )
            }
        }
    }
}