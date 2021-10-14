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
    var timestamp: Any = ""

)
