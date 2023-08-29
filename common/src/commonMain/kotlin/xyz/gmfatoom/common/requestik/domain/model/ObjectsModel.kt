package xyz.gmfatoom.common.requestik.domain.model


data class ObjectsModel(
    val objects_id: Long = 0L,
    val objects_login: String = "",
    var objects_name: String = "",
    var objects_adress: String = "",
    var objects_adm_phone: String = "",
    var objects_contatcts: List<ContactsModel> = emptyList(),
)
