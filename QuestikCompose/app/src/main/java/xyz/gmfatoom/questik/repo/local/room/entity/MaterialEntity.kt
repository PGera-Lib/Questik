package xyz.gmfatoom.questik.repo.local.room.entity


import androidx.room.*

/**
ForeiginKey

,
foreignKeys = [ForeignKey(entity = CategoryEntity::class,
parentColumns = ["id"], childColumns = ["category_id"]), ForeignKey(entity = MetricsEntity::class,
parentColumns = ["id"], childColumns = ["metrics_id"])]

*/

@Entity(tableName = "materials", indices = [Index("name"), Index("metrics_id"),  Index("category_id"),])
data class MaterialEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    var plu: String? = "",
    var name: String = "",
    var price: String = "",
    @ColumnInfo(name = "metrics_id")
    var metrics_id: String = "",
    @ColumnInfo(name = "category_id")
    var category_id: String = "",
    @Ignore
    var isSelected: Boolean = false,
    @Ignore
    var count: String = "1"
)