package xyz.gmfatoom.common.requestik.presentation.main

import kotlinx.datetime.LocalDate

data class AppMainState(
    val dataList: List<LocalDate> = emptyList()
)