package xyz.gmfatoom.common.requestik.presentation.request

import androidx.compose.foundation.lazy.LazyListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
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
                _state.emit(_state.value.copy(dataList = getDataList(date), firstVisibleDay = date))
            }

            viewModelScope.launch {
                _state.emit(_state.value.copy(requests = getFakeRequestList()))
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
                viewModelScope.launch {
                    _state.emit(_state.value.copy(sellectedDay = event.selectedDay))
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

private fun getFakeRequestList(): List<RequestModel> = listOf(
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
        data_start = "10:00",
        data_end = "",
        request_description = "",
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
        data_start = "10:00",
        data_end = "",
        request_description = "",
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
        data_start = "10:00",
        data_end = "",
        request_description = "",
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
        data_start = "10:00",
        data_end = "",
        request_description = "",
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
        data_start = "10:00",
        data_end = "",
        request_description = "",
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
        data_start = "10:00",
        data_end = "",
        request_description = "",
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
        data_start = "10:00",
        data_end = "",
        request_description = "",
        requestItems = listOf(
            RequestItemModel(name = "11"),
            RequestItemModel(name = "22"),
            RequestItemModel(name = "33")
        )
    ),
)
