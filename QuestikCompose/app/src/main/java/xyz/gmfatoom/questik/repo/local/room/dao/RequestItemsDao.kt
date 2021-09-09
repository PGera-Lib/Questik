package xyz.gmfatoom.questik.repo.local.room.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import xyz.gmfatoom.questik.repo.local.room.entity.RequestItemEntity

@Dao
interface RequestItemsDao {

    @Query("SELECT COUNT(id) FROM request_items")
    fun getRequestItemsCount(): Int
    @Query("SELECT COUNT(id) FROM request_items")
    fun getRequestItemsCountFlow(): Flow<Int>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAllRequestItems(items:List<RequestItemEntity>)
    @Query("DELETE FROM request_items")
     fun clearRequestItems()
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRequestItem(chernovik: RequestItemEntity)
    @Update
    fun updateRequestItem(chernovik: RequestItemEntity)
    @Delete
    fun deleteRequestItem(chernovik: RequestItemEntity)
    @Query("SELECT * FROM request_items WHERE id == :id")
    fun getRequestItemById(id: Int): RequestItemEntity
    @Query("SELECT * FROM request_items")
    fun getRequestItems(): List<RequestItemEntity>

    @Query("SELECT * FROM request_items WHERE categoryId == :id")
    fun getRequestItemsByCategoryId(id: String): List<RequestItemEntity>

    @Query("SELECT * FROM request_items WHERE name LIKE '%' || :search || '%' AND categoryId == :cat")
    fun searchRequestItem(search: String?, cat: String): List<RequestItemEntity>
    @Query("SELECT * FROM request_items WHERE name LIKE '%' || :search || '%' AND isChecked == :check AND categoryId == :cat")
    fun filteredRequestItemsList(search: String?, check: Boolean, cat: String): List<RequestItemEntity>
    @Query("SELECT * FROM request_items WHERE isChecked == :check AND categoryId == :cat")
    fun getRequestItemByItogShow(check: Boolean, cat: String): List<RequestItemEntity>
    @Query("SELECT * FROM request_items WHERE id BETWEEN :start AND :end")
    fun getRequestItemsByIdDiapazon(start: Int, end: Int): List<RequestItemEntity>
    @Query("SELECT COUNT(id) FROM request_items WHERE categoryId == :id")
    fun getRequestItemsCountByCategoryId(id: String): Int
    @Query("SELECT * FROM request_items WHERE categoryId == :categoryId AND id BETWEEN :start AND :end")
    fun getRequestItemsByCategoryAndIdDiapazon(categoryId:String, start: Int, end: Int): List<RequestItemEntity>

    @Query("SELECT * FROM request_items WHERE requestId == :id AND categoryId == :categoryId ")
    fun getRequestItemsByProjectId(id: Int, categoryId:String): List<RequestItemEntity>




}