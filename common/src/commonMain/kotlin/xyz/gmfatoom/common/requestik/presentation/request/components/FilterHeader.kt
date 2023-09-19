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
import androidx.compose.material3.MaterialTheme
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import xyz.gmfatoom.common.requestik.presentation.request.RequestListEvent
import xyz.gmfatoom.common.requestik.presentation.request.RequestListState
import xyz.gmfatoom.common.requestik.presentation.request.RequestListViewModel
import xyz.gmfatoom.common.utils.getLocalDayOfWeak
import xyz.gmfatoom.common.utils.getLocalMonth

@Composable
fun FilterHeader(
    modifier: Modifier = Modifier,
    requestListState: RequestListState,
    onEvent: (RequestListEvent) -> Unit,
) {
    val lazyListState = rememberLazyListState()
    val composableScope = rememberCoroutineScope()
    requestListState.let {
        println(
            " onHeader 1 first visible day is ${it.firstVisibleDay}  " +
                    "list count is ${it.dataList?.size} selected Date is ${it.sellectedDay}"
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
                onEvent(RequestListEvent.onUpdateDateList(it.sellectedDay))
                //delay(500L)
                lazyListState.scrollToItem(index = it.dataList.indexOf(it.firstVisibleDay))
            }
            Pagination(state = it, lazyListState = lazyListState, onEvent = onEvent)
        }
    }
}


@Composable
fun ItemDayCard(day: LocalDate, onFilterCardClick: () -> Unit) {
    Card(modifier = Modifier
        .padding(4.dp)
        .clickable { onFilterCardClick() }) {
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
            ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                modifier = Modifier
                    .clip(RoundedCornerShape(
                        topStart = 5.dp, topEnd = 5.dp, bottomStart = 5.dp, bottomEnd = 5.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(start = 4.dp, top = 4.dp, end = 4.dp, bottom = 4.dp)
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
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.SemiBold,
                    fontStyle = FontStyle.Normal,
                )
                Text(
                    text = "${day.dayOfMonth.toString()} ${day.getLocalMonth()}",
                    textAlign = TextAlign.Center,
                    fontSize = 10.sp,
                    textDecoration = TextDecoration.None,
                    letterSpacing = 0.sp,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .width(59.dp)
                        .alpha(1f),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
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
                        .alpha(1f),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Medium,
                    fontStyle = FontStyle.Normal,
                )
            }
        }
    }
}

@Composable
fun CurrentItemDayCard(day: LocalDate, onFilterCardClick: () -> Unit) {
    Card(modifier = Modifier
        .padding(4.dp)
        .clickable { onFilterCardClick() }) {
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                modifier = Modifier
                    .clip(RoundedCornerShape(
                        topStart = 5.dp, topEnd = 5.dp, bottomStart = 5.dp, bottomEnd = 5.dp
                    )
                    )
                    .background(MaterialTheme.colorScheme.tertiaryContainer)
                    .padding(start = 4.dp, top = 4.dp, end = 4.dp, bottom = 4.dp)
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
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                    fontWeight = FontWeight.SemiBold,
                    fontStyle = FontStyle.Normal,
                )
                Text(
                    text = "${day.dayOfMonth.toString()} ${day.getLocalMonth()}",
                    textAlign = TextAlign.Center,
                    fontSize = 10.sp,
                    textDecoration = TextDecoration.None,
                    letterSpacing = 0.sp,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .width(59.dp)
                        .alpha(1f),
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
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
                        .alpha(1f),
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                    fontWeight = FontWeight.Medium,
                    fontStyle = FontStyle.Normal,
                )
            }
        }
    }
}