package ru.rinet.questik.models

data class UserModel(
    var id: String = "",
    var username: String = "",
    var phone: String = "",
    var fullname: String = "",
    var bio: String = "",
    var status: String = "",
    var photoUrl: String = ""
)