package ru.rinet.questik.repo.local.room.dao

import androidx.paging.PagingSource
import androidx.room.*
import ru.rinet.questik.repo.local.room.entity.JobsEntity

@Dao
interface JobsDao {

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
}