package xyz.gmfatoom.questik.repo.local.room.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "objects")
data class ObjectsEntity(
    @PrimaryKey(autoGenerate = true)
    val objects_id : Int = 0,
    val objects_login: String = "",
    var objects_name: String = "",
    var objects_adress: String = "",
    var objects_adm_contacts: String = ""
)