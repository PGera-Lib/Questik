package xyz.gmfatoom.questik.repo.local.room.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import xyz.gmfatoom.questik.repo.local.room.entity.UserEntity

@Dao
interface UserDao {
    @Query("SELECT COUNT(id) FROM users")
    fun getUsersCount(): Int

    @Query("SELECT COUNT(id) FROM users")
    fun getUsersCountFlow(): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserEntity)

    @Update
    fun updateUser(user: UserEntity)

    @Delete
    fun deleteUser(user: UserEntity)

    @Query("SELECT * FROM users WHERE id == :id")
    fun getUserById(id: Int): List<UserEntity>

    @Query("SELECT * FROM users")
    fun getUser(): List<UserEntity>
}