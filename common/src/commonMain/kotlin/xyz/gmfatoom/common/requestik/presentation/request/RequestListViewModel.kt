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
import xyz.gmfatoom.common.utils.DateTimeUtil.getDataList

class RequestListViewModel(
    val requestikDataSource: RequestikDataSource,
) : ViewModel() {

    private var _state = MutableStateFlow(RequestListState())
    val state: StateFlow<RequestListState>
    private var firstVisibleDate: LocalDate? = null
    init {
        state = _state
        _state.value.firstVisibleDay?.let {
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