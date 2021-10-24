package ru.rinet.questik.models

data class JobsModel(
    var id: String = "",
    var name: String = "",
    var price: String = "",
    var metrics_id: String = "",
    var category_id: String = "",
    var price_inzh: String = "",
    var price_nalog_zp: String = "",
    var price_zp: String = ""
)
