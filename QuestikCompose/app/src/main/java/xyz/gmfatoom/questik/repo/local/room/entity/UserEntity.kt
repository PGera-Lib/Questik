package xyz.gmfatoom.questik.repo.local.room.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "users", indices = [Index("username")])
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var username: String = "",
    var phone: String = "",
    var fullname: String = "",
    var bio: String = "",
    var status: String = "",
    var photoUrl: String = ""
)