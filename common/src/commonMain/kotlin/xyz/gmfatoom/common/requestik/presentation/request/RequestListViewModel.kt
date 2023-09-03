package xyz.gmfatoom.common.requestik.presentation.request

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import xyz.gmfatoom.common.requestik.domain.RequestikDataSource
import xyz.gmfatoom.common.requestik.domain.model.RequestModel
import xyz.gmfatoom.common.utils.DateTimeUtil.getDataList

class RequestListViewModel(
    val requestikDataSource: RequestikDataSource,
) : ViewModel() {

    private var _state = MutableStateFlow(RequestListState())
    val state: StateFlow<RequestListState>
    private var lastVisibleDate: LocalDate? = null


    init {
        state = _state
        _state.value.lastVisibleDay?.let {
            lastVisibleDate = it
        }
        if (lastVisibleDate == null) {
            lastVisibleDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
        }

        lastVisibleDate?.let { date ->
            println("init update state - lastvisdate is $lastVisibleDate")
            viewModelScope.launch {
                _state.emit(_state.value.copy(dataList = getDataList(date).toMutableList()))
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
                lastVisibleDate = event.value
                lastVisibleDate?.let {
                    viewModelScope.launch {
                        viewModelScope.launch {
                            _state.emit(
                                _state.value
                                    .copy(
                                        lastVisibleDay = lastVisibleDate,
                                        dataList = getDataList(it).toMutableList()
                                    )
                            )
                        }
                    }
                }


            }

            is RequestListEvent.onDataListUpdateFirstItem -> {
                val dataList = _state.value.dataList
                dataList?.let {
                    val newFirstDay = dataList.first().minus(1, DateTimeUnit.DAY)
                    if (!it.contains(newFirstDay)) {
                        it.add(0, newFirstDay)
                        viewModelScope.launch {
                            _state.emit(_state.value.copy(dataList = it))
                        }
                    }
                }
            }

            is RequestListEvent.onDataListUpdateLastItem -> {
                val dataList = _state.value.dataList
                dataList?.let {
                    val newLastDay = dataList.last().plus(1, DateTimeUnit.DAY)
                    if (!it.contains(newLastDay)) {
                        it.add(newLastDay)
                        viewModelScope.launch {
                            _state.emit(_state.value.copy(dataList = it))
                        }
                    }
                }
            }


            else -> Unit

        }
    }

}