package xyz.gmfatoom.common.requestik.domain.model


data class MessagesModel(
    val from: String,
    val id: Long,
    val text: String,
    val timestamp: Long,
    val type: String
)