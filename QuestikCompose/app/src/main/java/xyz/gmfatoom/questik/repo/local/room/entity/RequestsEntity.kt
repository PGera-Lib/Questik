package xyz.gmfatoom.questik.repo.local.room.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "requests", indices = [Index("name")])
data class RequestsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val user_id: Int = 0,
    val contacts_id: Int = 0,
    val corp_id: Int = 0,
    val object_id: Int = 0,
    val data_create: String = "",
    val data_start: String = "",
    val data_end: String = "",
)