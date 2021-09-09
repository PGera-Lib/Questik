package xyz.gmfatoom.questik.repo.local.room.dao

import androidx.paging.PagingSource
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import xyz.gmfatoom.questik.repo.local.room.entity.MetricsEntity

@Dao
interface MetricsDao {

    @Query("SELECT COUNT(id) FROM metrics")
    fun getMetricsCount(): Int
    @Query("SELECT COUNT(id) FROM metrics")
    fun getMetricsCountFlow(): Flow<Int>

    @Query("SELECT * FROM metrics")
    fun getAllMetrics(): PagingSource<Int, MetricsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMetrics(items:List<MetricsEntity>)

    @Query("DELETE FROM metrics")
    fun clearMetrics()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMetrics(metrics: MetricsEntity)

    @Update
    fun updateMetrics(metrics: MetricsEntity)

    @Delete
    fun deleteMetrics(metrics: MetricsEntity)

    @Query("SELECT * FROM metrics WHERE id == :id")
    fun getMetricsById(id: Int): MetricsEntity

    @Query("SELECT * FROM metrics")
    fun getMetrics(): List<MetricsEntity>
}