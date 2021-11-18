package ru.rinet.questik.models

data class CommonModel(
    var id: String = "",
    var username: String = "",
    var phone: String = "",
    var fullname: String = "",
    var bio: String = "",
    var status: String = "",
    var photoUrl: String = "1",


    var text: String = "",
    var type: String = "",
    var from: String = "",
    var timestamp: Any = "",

    var plu: String = "",
    var name: String = "",
    var price: String = "",
    var metrics_id: String = "",
    var category_id: String = "",
    var price_inzh: String = "",
    var price_nalog_zp: String = "",
    var price_zp: String = ""


    ) {
    override fun equals(other: Any?): Boolean {
        return (other as CommonModel).id == id
    }
}
