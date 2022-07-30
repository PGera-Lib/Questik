package xyz.gmfatoom.questik.repo.local.room.entity

import androidx.room.*

@Entity(tableName = "categories", indices = [Index("name"), Index("id")])
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "name")
    var name: String = "",
    @Ignore
    var isExpand: Boolean = false
) {
    override fun toString(): String {
        return name
    }
}
