package xyz.gmfatoom.questik.repo.local.room.dao

import androidx.paging.PagingSource
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import xyz.gmfatoom.questik.repo.local.room.entity.JobsEntity

@Dao
interface JobsDao {
    @Query("SELECT COUNT(id) FROM jobs")
    fun getJobsCount(): Int

    @Query("SELECT COUNT(id) FROM jobs")
    fun getJobsCountFlow(): Flow<Int>

    @Query("SELECT * FROM jobs")
    fun getAllJobs(): PagingSource<Int, JobsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllJobs(items:List<JobsEntity>)

    @Query("DELETE FROM jobs")
     fun clearJobs()
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJob(job: JobsEntity)

    @Update
    fun updateJob(job: JobsEntity)

    @Delete
    fun deleteJob(job: JobsEntity)

    @Query("SELECT * FROM jobs WHERE id == :id")
    fun getJobById(id: Int): List<JobsEntity>

    @Query("SELECT * FROM jobs")
    fun getJobs(): List<JobsEntity>

    @Query("SELECT * FROM jobs WHERE category_id == :id")
    fun getJobByCategoryId(id: String): List<JobsEntity>

}