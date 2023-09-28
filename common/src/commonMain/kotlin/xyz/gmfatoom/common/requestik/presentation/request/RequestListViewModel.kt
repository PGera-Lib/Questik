package xyz.gmfatoom.common.requestik.presentation.request

import androidx.compose.foundation.lazy.LazyListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDate
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import xyz.gmfatoom.common.requestik.domain.RequestikDataSource
import xyz.gmfatoom.common.requestik.domain.model.ContactsModel
import xyz.gmfatoom.common.requestik.domain.model.RequestModel
import xyz.gmfatoom.common.requestik.domain.model.UsersModel
import xyz.gmfatoom.common.requestik.domain.model.CorpModel
import xyz.gmfatoom.common.requestik.domain.model.ObjectsModel
import xyz.gmfatoom.common.requestik.domain.model.RequestItemModel
import xyz.gmfatoom.common.utils.DateTimeUtil.getDataList

class RequestListViewModel(
    val requestikDataSource: RequestikDataSource,
) : ViewModel() {

    private var _state = MutableStateFlow(RequestListState())
    val state: StateFlow<RequestListState>
    private var firstVisibleDate: LocalDate? = null

    init {
        state = _state
        _state.value
            .firstVisibleDay?.let {
                firstVisibleDate = it
            }
        if (firstVisibleDate == null) {
            firstVisibleDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
        }
        firstVisibleDate?.let { date ->
            println("init update state - lastvisdate is $firstVisibleDate")
            viewModelScope.launch {
               val datalist =  getDataList(date)
                _state.emit(_state.value.copy(dataList = datalist, firstVisibleDay = date, requests = getFakeRequestList(dataList = datalist)))
            }


            /*
            state =  combine(
                    _state,
                    requestikDataSource.getDataListFlow(_state.value.lastVisibleDay)
            ) { state, dataList->
        /*        _state.update {
                    it.copy(dataList = dataList.toMutableList())
                }*/
                state.copy(
                    dataList = if (state.dataList.isNullOrEmpty()) dataList.toMutableList() else _state.value.dataList, sellectedDay = Clock.System.todayIn(TimeZone.currentSystemDefault())

                )
            }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), RequestListState())

    */
        }
    }


    fun onEvent(event: RequestListEvent) {
        when (event) {
            is RequestListEvent.onRequestSelectDataChanged -> {
                _state.value.dataList?.let {
                    viewModelScope.launch {
                        _state.emit(_state.value.copy(sellectedDay = event.selectedDay, requests = getFakeRequestList(it)))
                    }
                }
            }

            is RequestListEvent.onUpdateDateList -> {
                firstVisibleDate = event.value
                firstVisibleDate?.let {
                    viewModelScope.launch {
                        _state.emit(
                            _state.value
                                .copy(
                                    firstVisibleDay = firstVisibleDate,
                                    dataList = getDataList(it)
                                )
                        )
                    }
                }
            }

            else -> Unit
        }
    }

}

private fun getFakeRequestList(dataList: List<LocalDate>): Map<LocalDate, List<RequestModel>> {
    val fakeRequests = listOf(
        RequestModel(
            id = 1L,
            name = "Тестовая задача 1",
            user_creator = UsersModel(),
            user_current = UsersModel(),
            contactsList = listOf(
                ContactsModel(contact_name = "1"),
                ContactsModel(contact_name = "2"),
                ContactsModel(contact_name = "3")
            ),
            corp = CorpModel(company_adress = "Высотная 13"),
            object_request = ObjectsModel(),
            data_start = "2023-09-28T10:01:00",
            data_end = "",
            request_description = "",
            status = "ready",
            requestItems = listOf(
                RequestItemModel(name = "11"),
                RequestItemModel(name = "22"),
                RequestItemModel(name = "33")
            )
        ),
        RequestModel(
            id = 2L,
            name = "Тестовая задача 2",
            user_creator = UsersModel(),
            user_current = UsersModel(),
            contactsList = listOf(
                ContactsModel(contact_name = "1"),
                ContactsModel(contact_name = "2"),
                ContactsModel(contact_name = "3")
            ),
            corp = CorpModel(company_adress = "Высотная 14"),
            object_request = ObjectsModel(),
            data_start = "2023-09-28T10:02:00",
            data_end = "",
            request_description = "",
            status = "",
            requestItems = listOf(
                RequestItemModel(name = "11"),
                RequestItemModel(name = "22"),
                RequestItemModel(name = "33")
            )
        ),
        RequestModel(
            id = 3L,
            name = "Тестовая задача 1",
            user_creator = UsersModel(),
            user_current = UsersModel(),
            contactsList = listOf(
                ContactsModel(contact_name = "1"),
                ContactsModel(contact_name = "2"),
                ContactsModel(contact_name = "3")
            ),
            corp = CorpModel(company_adress = "Высотная 13"),
            object_request = ObjectsModel(),
            data_start = "2023-09-29T10:40:00",
            data_end = "",
            request_description = "",
            status = "",
            requestItems = listOf(
                RequestItemModel(name = "11"),
                RequestItemModel(name = "22"),
                RequestItemModel(name = "33")
            )
        ),
        RequestModel(
            id = 4L,
            name = "Тестовая задача 4",
            user_creator = UsersModel(),
            user_current = UsersModel(),
            contactsList = listOf(
                ContactsModel(contact_name = "1"),
                ContactsModel(contact_name = "2"),
                ContactsModel(contact_name = "3")
            ),
            corp = CorpModel(company_adress = "Высотная 13"),
            object_request = ObjectsModel(),
            data_start = "2023-09-28T10:13:00",
            data_end = "",
            request_description = "",
            status = "",
            requestItems = listOf(
                RequestItemModel(name = "11"),
                RequestItemModel(name = "22"),
                RequestItemModel(name = "33")
            )
        ),
        RequestModel(
            id = 5L,
            name = "Тестовая задача 5",
            user_creator = UsersModel(),
            user_current = UsersModel(),
            contactsList = listOf(
                ContactsModel(contact_name = "1"),
                ContactsModel(contact_name = "2"),
                ContactsModel(contact_name = "3")
            ),
            corp = CorpModel(company_adress = "Высотная 13"),
            object_request = ObjectsModel(),
            data_start = "2023-09-30T10:59:00",
            data_end = "",
            request_description = "",
            status = "",
            requestItems = listOf(
                RequestItemModel(name = "11"),
                RequestItemModel(name = "22"),
                RequestItemModel(name = "33")
            )
        ),
        RequestModel(
            id = 6L,
            name = "Тестовая задача 6",
            user_creator = UsersModel(),
            user_current = UsersModel(),
            contactsList = listOf(
                ContactsModel(contact_name = "1"),
                ContactsModel(contact_name = "2"),
                ContactsModel(contact_name = "3")
            ),
            corp = CorpModel(company_adress = "Высотная 13"),
            object_request = ObjectsModel(),
            data_start = "2023-09-30T10:09:00",
            data_end = "",
            request_description = "",
            status = "",
            requestItems = listOf(
                RequestItemModel(name = "11"),
                RequestItemModel(name = "22"),
                RequestItemModel(name = "33")
            )
        ),
        RequestModel(
            id = 7L,
            name = "Тестовая задача 7",
            user_creator = UsersModel(),
            user_current = UsersModel(),
            contactsList = listOf(
                ContactsModel(contact_name = "1"),
                ContactsModel(contact_name = "2"),
                ContactsModel(contact_name = "3")
            ),
            corp = CorpModel(company_adress = "Высотная 13"),
            object_request = ObjectsModel(),
            data_start = "2023-09-29T10:00:00",
            data_end = "",
            request_description = "",
            status = "ready",
            requestItems = listOf(
                RequestItemModel(name = "11"),
                RequestItemModel(name = "22"),
                RequestItemModel(name = "33")
            )
        ),
    )

    return  mutableMapOf<LocalDate, List<RequestModel>>()
        .apply { dataList.map {
            this.put(it, fakeRequests.filter { model->
                model.data_start?.toLocalDateTime()?.date == it
            })
        }
        }
}
