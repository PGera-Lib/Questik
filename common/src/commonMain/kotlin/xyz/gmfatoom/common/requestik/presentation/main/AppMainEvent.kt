package xyz.gmfatoom.common.requestik.presentation.main

import kotlinx.datetime.LocalDate
import xyz.gmfatoom.common.requestik.domain.model.ContactsModel
import xyz.gmfatoom.common.requestik.domain.model.CorpModel
import xyz.gmfatoom.common.requestik.domain.model.ObjectsModel
import xyz.gmfatoom.common.requestik.domain.model.RequestItemModel
import xyz.gmfatoom.common.requestik.domain.model.RequestModel
import xyz.gmfatoom.common.requestik.domain.model.UsersModel

sealed interface AppMainEvent {

    data class onRequestDataChanged(val selectedDay: LocalDate):AppMainEvent


}