package xyz.gmfatoom.common.requestik.presentation.request.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import kotlinx.datetime.toLocalDateTime
import xyz.gmfatoom.common.requestik.domain.model.RequestModel
import xyz.gmfatoom.common.requestik.presentation.request.RequestListEvent
import xyz.gmfatoom.common.utils.getRequestTime


@Composable
fun RequestListItem(modifier: Modifier = Modifier, request: RequestModel, onEvent:(RequestListEvent) ->Unit) {
    Card(modifier = modifier
            .clip(shape = RoundedCornerShape(topStart = 15.dp, bottomStart = 15.dp))
           .padding(all = 8.dp)
            .clickable {
onEvent(RequestListEvent.EditRequest(request))
            }
    ) {
       /* Column(
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top)
        ) {*/
            Row(
                horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(topStart = 15.dp, bottomStart = 15.dp))
                    .background(color = Color(0xff7dfc8a))
         /*           .padding(start = 10.dp)*/
            ) {
                Text(
                    text = request.data_start?.getRequestTime() ?: "00:00",
                    color = Color(0xff564c4c),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier
                        .padding(all = 8.dp)
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
                    modifier = Modifier
                        .background(color = Color(0xffe2e2e2))

                ) {
                    Row(modifier=Modifier
                        .padding(all = 10.dp)
                    ) {
                        Text(
                            text = "Задача: ",
                            color = Color(0xff564c4c),
                            textAlign = TextAlign.Left,
                            fontSize = 16.sp,
                            modifier = Modifier
                                .wrapContentHeight(align = Alignment.CenterVertically)
                        )
                        Text(
                            text = "${request.name}",
                            color = Color(0xff564c4c),
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp,
                            modifier = Modifier
                                .fillMaxWidth(0.75f)
                                .wrapContentHeight(align = Alignment.CenterVertically)
                        )
                    }
                    Row(modifier=Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp )

                    ) {
                        Text(
                            text = "Адресс:  ",
                            color = Color(0xff7b7171),
                            fontSize = 14.sp
                        )
                        Text(
                            text = "${request.corp?.company_adress}",
                            color = Color(0xff7b7171),
                            fontSize = 14.sp
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.tertiaryContainer),
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        Text(modifier= Modifier
                            .padding(top= 10.dp, start = 10.dp, end = 10.dp, bottom = 10.dp ),
                            text = "Описание задачи: ${request.request_description}",
                            color = Color(0xff7b7171),
                            fontSize = 12.sp
                        )
                    }
                }
            }
        /*}*/
    }
}