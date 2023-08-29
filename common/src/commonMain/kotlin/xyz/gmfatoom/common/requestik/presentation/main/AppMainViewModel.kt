package xyz.gmfatoom.common.requestik.presentation.main

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import xyz.gmfatoom.common.utils.DateTimeUtil.getDataList


class AppMainViewModel(
): ViewModel() {

    private val _state = MutableStateFlow(AppMainState())

    val state = _state.stateIn(viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        AppMainState())

}