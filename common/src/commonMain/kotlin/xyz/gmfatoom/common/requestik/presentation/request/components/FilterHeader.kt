package xyz.gmfatoom.common.requestik.presentation.request.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text


import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
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
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import xyz.gmfatoom.common.requestik.presentation.request.RequestListEvent
import xyz.gmfatoom.common.requestik.presentation.request.RequestListState
import xyz.gmfatoom.common.utils.getLocalDayOfWeak

@Composable
fun FilterHeader(
    modifier: Modifier = Modifier,
    state: RequestListState,
    lazyListState: LazyListState,
    onEvent: (RequestListEvent) -> Unit,
) {
    val composableScope = rememberCoroutineScope()
    //val lazyListState  = rememberLazyListState()
    println(" onHeader 1 last visible day is ${state.lastVisibleDay}  list count is ${state.dataList.size} last Date is ${state.dataList.last()}")

    /*
      currentSelectedData.let {
            print(it.toString())
            dataList.let{list->
                composableScope.launch{
                    if(lazyListState.firstVisibleItemIndex>list.indexOf(it)){
                        println("if fvi>itemindex" +
                                "first visible index - ${lazyListState.firstVisibleItemIndex} " +
                                "and selected item index - ${list.indexOf(it)}")
                        lazyListState.scrollToItem(list.indexOf(it))
                    } else if (lazyListState.firstVisibleItemIndex<list.indexOf(it)-7) {
                        println(
                            "if fvi<itemindex+14" +
                                    "first visible index - ${lazyListState.firstVisibleItemIndex} " +
                                    "and selected item index - ${list.indexOf(it)}"
                        )
                        lazyListState.scrollToItem(list.indexOf(it))
                    }
            }
        }
    }
*/
    if (!state.dataList.isNullOrEmpty()) {
    LazyRow(
            modifier = Modifier
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { delta ->
                        composableScope.launch {
                            lazyListState.scrollBy(-delta)
                        }
                    },
                ),
            state = lazyListState
        )
        {
            itemsIndexed(items = state.dataList, key = { _, item: LocalDate ->
                item.toString()
            }) { index: Int, day: LocalDate ->
                if (state.sellectedDay.toString() == day.toString()) {
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


        /*            if (state.dataList.size < lazyListState.firstVisibleItemIndex + 5) {
                        println("1 firstVis is ${lazyListState.firstVisibleItemIndex} fvis is ${lazyListState.firstVisibleItemScrollOffset}")
                        onEvent(RequestListEvent.onUpdateDateList(state.dataList[lazyListState.firstVisibleItemScrollOffset]))
                    } else if (lazyListState.firstVisibleItemIndex < 3) {
                        println("2 firstVis is ${lazyListState.firstVisibleItemIndex} fvis is ${lazyListState.firstVisibleItemScrollOffset}")
                        onEvent(RequestListEvent.onUpdateDateList(state.dataList[lazyListState.firstVisibleItemScrollOffset]))
                    }*/

        Pagination(
            listState = lazyListState,
            buffer = 10,
            onEvent = {
                if (state.lastVisibleDay==null){
                    onEvent(RequestListEvent
                        .onUpdateDateList(state.dataList.last()))
                } else {
                    onEvent(RequestListEvent
                        .onUpdateDateList(state.lastVisibleDay))
                }
                println("first it  - it ${state.dataList.first()}  last - ${state.dataList.last()}")

            })
    }
}


@Composable
fun ItemDayCard(day: LocalDate, onFilterCardClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .clip(
                RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                )
            )
            .background(Color.Transparent)
            .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
            .alpha(1f)
            .clickable { onFilterCardClick() }
    ) {


        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),

            ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                modifier = Modifier
                    .width(71.dp)
                    .height(100.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 5.dp,
                            topEnd = 5.dp,
                            bottomStart = 5.dp,
                            bottomEnd = 5.dp
                        )
                    )
                    .background(
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
                    modifier = Modifier
                        .width(59.dp)
                        .alpha(1f),
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
                    modifier = Modifier
                        .width(59.dp)
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
    Card(
        modifier = Modifier
            .padding(4.dp)
            .clip(
                RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                )
            )
            .background(Color.Transparent)
            .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
            .alpha(1f)
            .clickable { onFilterCardClick() }
    ) {


        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),

            ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                modifier = Modifier

                    .width(71.dp)
                    .height(100.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 5.dp,
                            topEnd = 5.dp,
                            bottomStart = 5.dp,
                            bottomEnd = 5.dp
                        )
                    )
                    .background(
                        Color(
                            red = 0.11372548341751099f,
                            green = 0.6809410452842712f,
                            blue = 1f,
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
                    modifier = Modifier
                        .width(59.dp)
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