package xyz.gmfatoom.common.requestik.domain.model

data class RequestItemModel(
    var id: Long,
    var name: String,
    var categoryId: String,
    var requestId: Long,
    var jobId: String,
    var materialId: String,
    var metricsId: String,
    var itemCount: String,
    var itemPrice: String,
    var plu: String,
    var isChecked: Boolean,
    var isMaterial: Boolean
)