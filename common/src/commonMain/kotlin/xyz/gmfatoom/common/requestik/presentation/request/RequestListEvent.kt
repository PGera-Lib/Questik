package xyz.gmfatoom.common.requestik.presentation.request

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import xyz.gmfatoom.common.requestik.domain.model.ContactsModel
import xyz.gmfatoom.common.requestik.domain.model.CorpModel
import xyz.gmfatoom.common.requestik.domain.model.ObjectsModel
import xyz.gmfatoom.common.requestik.domain.model.RequestItemModel
import xyz.gmfatoom.common.requestik.domain.model.RequestModel
import xyz.gmfatoom.common.requestik.domain.model.UsersModel

sealed interface RequestListEvent {

    data class onRequestSelectDataChanged(val selectedDay: LocalDate):RequestListEvent

    object onDataListUpdateFirstItem:RequestListEvent
    object onDataListUpdateLastItem:RequestListEvent
    data class onUpdateDateList(val value: LocalDate):RequestListEvent

    object OnAddNewRequestClick: RequestListEvent

    object DismissRequest: RequestListEvent

    data class OnNameChanged(val value: String): RequestListEvent
    data class OnUserCurrentChanged(val value: UsersModel): RequestListEvent

    object OnSelectContactListClicked: RequestListEvent
    data class OnContactListChanged(val value: List<ContactsModel>): RequestListEvent

    object OnCompanyChangeClicked: RequestListEvent
    data class onCompanyChanged(val value: CorpModel): RequestListEvent

    object OnObjectChangeClicked: RequestListEvent
    data class onObjectChanged(val value: ObjectsModel): RequestListEvent


    object OnDataCreateChangeClicked: RequestListEvent
    data class onDataCreateChanged(val value: String): RequestListEvent

    object OnDataStartChangeClicked: RequestListEvent
    data class onDataStartChanged(val value: String): RequestListEvent

    object OnDataEndChangeClicked: RequestListEvent
    data class onDataEndChanged(val value: String): RequestListEvent

    data class onDescriptionChanged(val value: String): RequestListEvent

    object OnSelectRequestItemsListClicked: RequestListEvent
    data class OnRequestItemsListChanged(val value: List<RequestItemModel>): RequestListEvent

    object SaveRequest: RequestListEvent
    data class SelectRequest(val request: RequestModel): RequestListEvent
    data class EditRequest(val request: RequestModel): RequestListEvent
    object DeleteRequest: RequestListEvent

}