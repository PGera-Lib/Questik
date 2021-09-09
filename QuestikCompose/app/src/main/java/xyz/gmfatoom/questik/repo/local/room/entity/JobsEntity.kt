package xyz.gmfatoom.questik.repo.local.room.entity

import androidx.room.*


/*,
foreignKeys = [ForeignKey(entity = CategoryEntity::class,
parentColumns = ["id"], childColumns = ["category_id"]),ForeignKey(entity = MetricsEntity::class,
parentColumns = ["id"], childColumns = ["metrics_id"])]*/
@Entity(tableName = "jobs", indices = [Index("name"), Index("metrics_id"),  Index("category_id"),])
data class JobsEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Int = 0,
    @ColumnInfo(name = "name")
    var name: String = "",
    var price: String = "",
    @ColumnInfo(name = "metrics_id")
    var metrics_id: String = "",
    @ColumnInfo(name = "category_id")
    var category_id: String = "",
    var price_inzh: String = "",
    var price_nalog_zp: String? = "",
    var price_zp: String? = "",
    @Ignore
    var isSelected: Boolean = false,
    @Ignore
    var count: String = "1"
)