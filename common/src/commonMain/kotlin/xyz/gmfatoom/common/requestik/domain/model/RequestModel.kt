package xyz.gmfatoom.common.requestik.domain.model

data class RequestModel(
    val id: Long = 0L,
    val name: String = "",
    val user_creator: UsersModel?,
    val user_current: UsersModel?,
    val contactsList: List<ContactsModel>?,
    val corp: CorpModel?,
    val object_request: ObjectsModel?,
    val data_create: String = "",
    val data_start: String?,
    val data_end: String?,
    val request_description: String?,
    val status: String?,
    val requestItems: List<RequestItemModel>?
)

