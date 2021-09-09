package xyz.gmfatoom.questik.repo.local.room.dao

import androidx.paging.PagingSource
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import xyz.gmfatoom.questik.repo.local.room.entity.MaterialEntity

@Dao
interface MaterialDao {

    @Query("SELECT COUNT(id) FROM materials")
    fun getMaterialsCount(): Int
    @Query("SELECT COUNT(id) FROM materials")
    fun getMaterialsCountFlow(): Flow<Int>

    @Query("SELECT * FROM materials")
    fun getAllMaterials(): PagingSource<Int, MaterialEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAllMaterials(items:List<MaterialEntity>)

    @Query("DELETE FROM jobs")
     fun clearMaterials()


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMaterial(material: MaterialEntity)

    @Update
    fun updateMaterial(material: MaterialEntity)

    @Delete
    fun deleteMaterial(material: MaterialEntity)

    @Query("SELECT * FROM materials WHERE id == :id")
    fun getMaterialyById(id: Int): List<MaterialEntity>

    @Query("SELECT * FROM materials")
    fun getMaterials(): List<MaterialEntity>

    @Query("SELECT * FROM materials WHERE category_id == :id")
    fun getMaterialByCategoryId(id: String): List<MaterialEntity>
}