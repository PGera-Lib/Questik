package xyz.gmfatoom.common


import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import xyz.gmfatoom.common.di.AppModule
import xyz.gmfatoom.common.requestik.presentation.main.App
import xyz.gmfatoom.common.requestik.presentation.request.components.ItemDayCard
import xyz.gmfatoom.common.utils.DateTimeUtil.nowLocalDate

@Preview
@Composable
fun AppPreview() {
    Surface {
        App(darkTheme = true, dynamicColor = false, appModule = AppModule())
    }
}

@Preview
@Composable
fun ItemDayCardPreview() {
    ItemDayCard(
        day = nowLocalDate(),
        dayCount = 1,
        readyCount = 2,
        notReadyCount = 3
    ) {}
}

@Preview
@Composable
private fun PreviewRequestListItem() {
    /*   RequestListItem(request = RequestModel(
          name = "Тестовая задача 1",
          user_creator = UsersModel(),
          user_current = UsersModel(),
          contactsList = listOf(
             ContactsModel(contact_name = "1"),
             ContactsModel(contact_name = "2"),
             ContactsModel(contact_name = "3")),
          corp = CorpModel(company_adress = "Высотная 13"),
          object_request = ObjectsModel(),
          data_start = "10:00",
          data_end = "",
          request_description = "",
          requestItems = listOf(
             RequestItemModel(name = "11"),
             RequestItemModel(name = "22"),
             RequestItemModel(name = "33") )
       ))*/
}
