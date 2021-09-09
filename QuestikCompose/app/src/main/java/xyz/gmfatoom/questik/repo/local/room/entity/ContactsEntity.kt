package xyz.gmfatoom.questik.repo.local.room.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "contacts")
data class ContactsEntity(
    @PrimaryKey(autoGenerate = true)
    val contact_id : Int = 0,
    var contact_name: String = "",
    var contact_phone: String = "",
    var contact_mail: String = "",
    val company_id: String = "",
    var contact_description: String = ""
)