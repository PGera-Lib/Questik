package xyz.gmfatoom.questik.repo.local.room.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow

import xyz.gmfatoom.questik.repo.local.room.entity.RequestsEntity


@Dao
interface RequestsDao {

    @Query("SELECT COUNT(id) FROM requests")
    fun getRequestsCount(): Int
    @Query("SELECT COUNT(id) FROM requests")
    fun getRequestsCountFlow(): Flow<Int>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllRequests(items:List<RequestsEntity>)
/*    @Query("DELETE FROM requests")
    fun clearChernovikItems()*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRequest(request: RequestsEntity):Long
    @Update
    fun updateRequest(request: RequestsEntity)
    @Delete
    fun deleteRequest(request: RequestsEntity)
    @Query("SELECT * FROM requests WHERE id == :id")
    fun getRequestById(id: Int): RequestsEntity
    @Query("SELECT * FROM requests")
    fun getRequests(): List<RequestsEntity>

}