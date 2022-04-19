package ru.rinet.questik.repo.local.room.dao

import androidx.room.*
import ru.rinet.questik.repo.local.room.entity.ChernovikEntity

@Dao
interface ChernovikDao {

    @Query("SELECT COUNT(id) FROM chernovik")
    fun getChernovikCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAllChernovik(items:List<ChernovikEntity>)

    @Query("DELETE FROM chernovik")
     fun clearChernovik()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChernovik(chernovik: ChernovikEntity)
    @Update
    fun updateChernovik(chernovik: ChernovikEntity)
    @Delete
    fun deleteChernovik(chernovik: ChernovikEntity)
    @Query("SELECT * FROM chernovik WHERE id == :id")
    fun getChernovikById(id: Int): ChernovikEntity
    @Query("SELECT * FROM chernovik")
    fun getChernovik(): List<ChernovikEntity>
    @Query("SELECT * FROM chernovik WHERE categoryId == :id")
    fun getChernovikByCategoryId(id: String): List<ChernovikEntity>
    @Query("SELECT * FROM chernovik WHERE name LIKE '%' || :search || '%' AND categoryId == :cat")
    fun searchChernovikItem(search: String?, cat: String): List<ChernovikEntity>
    @Query("SELECT * FROM chernovik WHERE name LIKE '%' || :search || '%' AND isChecked == :check AND categoryId == :cat")
    fun filteredChernovikList(search: String?, check: Boolean, cat: String): List<ChernovikEntity>
    @Query("SELECT * FROM chernovik WHERE isChecked == :check AND categoryId == :cat")
    fun getChernovikByItogShow(check: Boolean, cat: String): List<ChernovikEntity>
    @Query("SELECT * FROM chernovik WHERE id BETWEEN :start AND :end")
    fun getChernovikByIdDiapazon(start: Int, end: Int): List<ChernovikEntity>

    @Query("SELECT COUNT(id) FROM chernovik WHERE categoryId == :id")
    fun getChernovikCountByCategoryId(id: String): Int

    @Query("SELECT * FROM chernovik WHERE categoryId == :categoryId AND id BETWEEN :start AND :end")
    fun getChernovikByCategoryAndIdDiapazon(categoryId:String, start: Int, end: Int): List<ChernovikEntity>
}