package xyz.gmfatoom.questik.repo.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "request_items", indices = [Index("name"), Index("id")])
data class RequestItemEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    var name: String = "",
    var categoryId: String = "",
    var requestId: Int = 0,
    var jobId: String = "",
    var materialId: String = "",
    var metricsId: String = "",
    var itemCount: String = "",
    var itemPrice: String = "",
    var plu: String = "",
    var isChecked: Boolean = false,
    var isMaterial: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RequestItemEntity

        if (id != other.id) return false
        if (name != other.name) return false
        if (categoryId != other.categoryId) return false
        if (jobId != other.jobId) return false
        if (materialId != other.materialId) return false
        if (metricsId != other.metricsId) return false
        if (itemCount != other.itemCount) return false
        if (itemPrice != other.itemPrice) return false
        if (plu != other.plu) return false
        if (isChecked != other.isChecked) return false
        if (isMaterial != other.isMaterial) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + categoryId.hashCode()
        result = 31 * result + jobId.hashCode()
        result = 31 * result + materialId.hashCode()
        result = 31 * result + metricsId.hashCode()
        result = 31 * result + itemCount.hashCode()
        result = 31 * result + itemPrice.hashCode()
        result = 31 * result + plu.hashCode()
        result = 31 * result + isChecked.hashCode()
        result = 31 * result + isMaterial.hashCode()
        return result
    }

    override fun toString(): String {
        return "ChernovikEntity(id=$id, name='$name')"
    }
}
