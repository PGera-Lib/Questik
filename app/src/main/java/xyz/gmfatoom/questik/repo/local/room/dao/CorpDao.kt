package xyz.gmfatoom.questik.repo.local.room.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import xyz.gmfatoom.questik.repo.local.room.entity.CorpEntity


@Dao
interface CorpDao {
    @Query("SELECT COUNT(corp_id) FROM corp")
    fun getCorpCount(): Int
    @Query("SELECT COUNT(corp_id) FROM corp")
    fun getCorpCountFlow(): Flow<Int>
/*    @Query("SELECT * FROM contacts")
    fun getAllCorp(): PagingSource<Int, CorpEntity>*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCorp(items:List<CorpEntity>)

    @Query("DELETE FROM corp")
    fun clearCorp()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCorp(corp: CorpEntity):Long

    @Update
    fun updateCorp(corp: CorpEntity)

    @Delete
    fun deleteCorp(corp: CorpEntity)

    @Query("SELECT * FROM corp WHERE corp_id == :id")
    fun getCorpById(id: Int): CorpEntity

    @Query("SELECT * FROM corp")
    fun getCorp(): List<CorpEntity>
}