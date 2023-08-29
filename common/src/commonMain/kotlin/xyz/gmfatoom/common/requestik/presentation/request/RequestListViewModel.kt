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
                lastVisibleDate=it
            }
        if (lastVisibleDate==null){
            lastVisibleDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
        }

    lastVisibleDate?.let {date ->
        println("init update state - lastvisdate is $lastVisibleDate")
        _state.update {
            it.copy(dataList = getDataList(date).toMutableList())
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
                _state.update {
                    it.copy(sellectedDay = event.selectedDay)
                }
            }

            is RequestListEvent.onUpdateDateList -> {
                lastVisibleDate = event.value
                viewModelScope.launch {
                    val oldList = state.value.dataList
                    val newList = mutableListOf<LocalDate>()
                   newList.apply {
                      getDataList(event.value).forEach {
                           if (!oldList.contains(it)) oldList.add(it)
                       }
                   this.addAll(oldList)}
                    _state.update {
                        it.copy(
                            dataList = newList,
                            lastVisibleDay = event.value
                        )
                    }
                    delay(300L)
                    println("onUpdateDataList run, newData list first item ${state.value.dataList.first()} and last item ${_state.value.dataList.last()} ")
                }

            }

            is RequestListEvent.onDataListUpdateFirstItem -> {
                val newDay = 10
                println("on update first new day - $newDay")
                viewModelScope.launch {
                    val newDay = _state.value.dataList.first().minus(1, DateTimeUnit.DAY)
                    val newList = state.value.dataList
                    if (!newList.contains(newDay)) {
                        newList.removeFirst()
                        delay(300L)
                        newList.add(newDay)
                        delay(300L)

                        _state.update {
                            it.copy(dataList = newList, lastVisibleDay = newDay)
                        }

                        delay(300L)
                        println("Added new Date - ${newDay}")
                    }
                    delay(300L)
                }
            }

            is RequestListEvent.onDataListUpdateLastItem -> {
                val newList = state.value.dataList

                val newDay = state.value.dataList.last().plus(1, DateTimeUnit.DAY)
                lastVisibleDate = newDay
                if (!newList.contains(newDay)) {
                    newList.removeFirst()
                    newList.add(newDay)
                    _state.update {
                        it.copy(dataList = newList, lastVisibleDay = newDay)
                    }
                    println("Added new Date - ${newDay}")
                }
                println("onDataUpdateLast")
            }


            else -> Unit

        }
    }

}