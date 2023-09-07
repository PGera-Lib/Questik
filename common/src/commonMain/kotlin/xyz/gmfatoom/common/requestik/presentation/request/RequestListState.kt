package xyz.gmfatoom.common.requestik.presentation.request

import androidx.compose.foundation.lazy.LazyListState
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import xyz.gmfatoom.common.requestik.domain.model.RequestModel
import xyz.gmfatoom.common.requestik.domain.model.UsersModel

data class RequestListState(
    val sellectedDay: LocalDate = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault()).date,
    val requests: List<RequestModel> = emptyList(),
    val selectedRequest: RequestModel? = null,
    val firstVisibleDay: LocalDate? = null,
    val dataList: List<LocalDate>? = null,
    val isSelectContactListSheetOpen: Boolean = false,
    val isSelectCompanySheetOpen: Boolean = false,
    val isSelectObjectSheetOpen: Boolean = false,
    val isAddRequestSheetOpen: Boolean = false,
    val isSelectedRequestSheetOpen: Boolean = false,

    val isDataCreateChangeSheetOpen: Boolean = false,
    val isDataStartChangeSheetOpen: Boolean = false,
    val isDataEndChangeSheetOpen: Boolean = false,

    val isSelectRequestItemsSheetOpen: Boolean = false,

    val nameError: String? = null,
    val userCreatorError : UsersModel? = null,
    val createDataError: String? = null
)