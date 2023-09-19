package xyz.gmfatoom.common


import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import moe.tlaster.precompose.viewmodel.viewModel
import xyz.gmfatoom.common.di.AppModule
import xyz.gmfatoom.common.requestik.domain.model.ContactsModel
import xyz.gmfatoom.common.requestik.domain.model.CorpModel
import xyz.gmfatoom.common.requestik.domain.model.ObjectsModel
import xyz.gmfatoom.common.requestik.domain.model.RequestItemModel
import xyz.gmfatoom.common.requestik.domain.model.RequestModel
import xyz.gmfatoom.common.requestik.domain.model.UsersModel
import xyz.gmfatoom.common.requestik.presentation.main.App
import xyz.gmfatoom.common.requestik.presentation.request.RequestListScreen
import xyz.gmfatoom.common.requestik.presentation.request.RequestListViewModel
import xyz.gmfatoom.common.requestik.presentation.request.components.ItemDayCard
import xyz.gmfatoom.common.requestik.presentation.request.components.RequestListItem
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
fun ItemDayCardPreview(){
   ItemDayCard(day = nowLocalDate(), onFilterCardClick = {})
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
