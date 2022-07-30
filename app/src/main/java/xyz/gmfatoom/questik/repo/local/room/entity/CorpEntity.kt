package xyz.gmfatoom.questik.repo.local.room.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "corp")
data class CorpEntity(
    @PrimaryKey(autoGenerate = true)
    var corp_id: Int = 0,
    var corp_login: String = "",
    val company_name: String = "",
    val company_inn: String = "",
    val company_adress: String = "",
    var company_phone: String = "",
    var company_mail: String = "",
    var isPhis: Boolean = false
)