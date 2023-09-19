package xyz.gmfatoom.common.requestik.presentation.request

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import kotlinx.datetime.LocalDate
import moe.tlaster.precompose.navigation.Navigator
import xyz.gmfatoom.common.requestik.domain.model.RequestModel
import xyz.gmfatoom.common.requestik.presentation.request.components.FilterHeader
import xyz.gmfatoom.common.requestik.presentation.request.components.RequestList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequestListScreen(
    viewModel: RequestListViewModel,
    onEvent: (RequestListEvent) -> Unit
) {
     viewModel.state.collectAsState().value.let {
         Column {
             FilterHeader(
                 requestListState = it,
                 onEvent = onEvent
             )
             RequestList(
                 requestListState = it,
                 onEvent = onEvent
             )
         }
     }


            }




