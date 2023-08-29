package xyz.gmfatoom.common.requestik.domain.model

data class CorpModel(
    var corp_id: Long = 0L,
    var corp_login: String = "",
    val company_name: String = "",
    val company_inn: String = "",
    val company_adress: String = "",
    var company_phone: String = "",
    var company_contatcts: List<ContactsModel> = emptyList(),
    var company_mail: String = "",
    var isPhis: Boolean = false
)