package xyz.gmfatoom.questik.repo.local.room.dao

import androidx.paging.PagingSource
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import xyz.gmfatoom.questik.repo.local.room.entity.ContactsEntity
import xyz.gmfatoom.questik.repo.local.room.entity.ObjectsEntity

@Dao
interface ObjectsDao {
    @Query("SELECT COUNT(objects_id) FROM objects")
    fun getObjectsCount(): Int
    @Query("SELECT COUNT(objects_id) FROM objects")
    fun getObjectsCountFlow(): Flow<Int>


/*    @Query("SELECT * FROM contacts")
    fun getAllObjects(): PagingSource<Int, ContactsEntity>*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllObjects(items:List<ObjectsEntity>)

    @Query("DELETE FROM objects")
    fun clearObjects()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertObjects(objects: ObjectsEntity):Long

    @Update
    fun updateObjects(objects: ObjectsEntity)

    @Delete
    fun deleteObjects(objects: ObjectsEntity)

    @Query("SELECT * FROM objects WHERE objects_id == :id")
    fun getObjectsById(id: Int): ObjectsEntity

    @Query("SELECT * FROM objects")
    fun getObjects(): List<ObjectsEntity>




}