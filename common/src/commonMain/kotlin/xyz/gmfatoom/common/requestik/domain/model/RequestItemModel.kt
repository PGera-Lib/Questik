package xyz.gmfatoom.common.requestik.domain.model

data class RequestItemModel(
    var id: Long = 0L,
    var name: String ="",
    var categoryId: String = "",
    var requestId: Long = 0L,
    var jobId: String = "",
    var materialId: String = "",
    var metricsId: String = "",
    var itemCount: String = "",
    var itemPrice: String = "",
    var plu: String = "",
    var isChecked: Boolean = false,
    var isMaterial: Boolean = false
)