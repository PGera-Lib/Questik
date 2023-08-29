package xyz.gmfatoom.common.requestik.domain.model


data class ContactsModel(
    val contact_id: Long = 0L,
    var contact_name: String = "",
    var contact_mail: String = "",
    val company_id: Long = 0L,
    val object_id: Long = 0L,
    var contact_description: String = ""
)
