package ru.rinet.questik.models

data class CommonModel(
    var rowId: String = "",
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
    var count: String = "",
    var metrics_id: String = "",
    var category_id: String = "",
    var price_inzh: String = "",
    var price_nalog_zp: String = "",
    var price_zp: String = "",
    var isChecked: Boolean = false,
    var isShow: Boolean = false,
    var isMaterial:Boolean = false,
    var pageSize: Int = 15
) {



    override fun toString(): String {
        return name
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CommonModel

        if (rowId != other.rowId) return false
        if (id != other.id) return false
        if (username != other.username) return false
        if (phone != other.phone) return false
        if (fullname != other.fullname) return false
        if (bio != other.bio) return false
        if (status != other.status) return false
        if (photoUrl != other.photoUrl) return false
        if (text != other.text) return false
        if (type != other.type) return false
        if (from != other.from) return false
        if (timestamp != other.timestamp) return false
        if (plu != other.plu) return false
        if (name != other.name) return false
        if (price != other.price) return false
        if (count != other.count) return false
        if (metrics_id != other.metrics_id) return false
        if (category_id != other.category_id) return false
        if (price_inzh != other.price_inzh) return false
        if (price_nalog_zp != other.price_nalog_zp) return false
        if (price_zp != other.price_zp) return false
        if (isChecked != other.isChecked) return false
        if (isShow != other.isShow) return false
        if (isMaterial != other.isMaterial) return false

        return true
    }

    override fun hashCode(): Int {
        var result = rowId.hashCode()
        result = 31 * result + id.hashCode()
        result = 31 * result + username.hashCode()
        result = 31 * result + phone.hashCode()
        result = 31 * result + fullname.hashCode()
        result = 31 * result + bio.hashCode()
        result = 31 * result + status.hashCode()
        result = 31 * result + photoUrl.hashCode()
        result = 31 * result + text.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + from.hashCode()
        result = 31 * result + timestamp.hashCode()
        result = 31 * result + plu.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + price.hashCode()
        result = 31 * result + count.hashCode()
        result = 31 * result + metrics_id.hashCode()
        result = 31 * result + category_id.hashCode()
        result = 31 * result + price_inzh.hashCode()
        result = 31 * result + price_nalog_zp.hashCode()
        result = 31 * result + price_zp.hashCode()
        result = 31 * result + isChecked.hashCode()
        result = 31 * result + isShow.hashCode()
        result = 31 * result + isMaterial.hashCode()
        return result
    }
}
