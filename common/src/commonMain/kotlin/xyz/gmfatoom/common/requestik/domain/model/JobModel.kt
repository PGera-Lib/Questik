package xyz.gmfatoom.common.requestik.domain.model


data class JobModel(
    val categoryId: String,
    val id: Long,
    val metricsId: String,
    val name: String,
    val price: String,
    val priceInzh: String,
    val priceNalogZp: String,
    val priceZp: String
)