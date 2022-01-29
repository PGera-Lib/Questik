package ru.rinet.questik.repo

import androidx.paging.PagingSource
import ru.rinet.questik.repo.local.room.AppDatabase
import ru.rinet.questik.repo.local.room.DbHelper
import ru.rinet.questik.repo.local.room.entity.*
import javax.inject.Inject

class QuestikRepository @Inject constructor(
    private val dbHelper: DbHelper,
    private val appDatabase: AppDatabase

):Repository{
    /**
     * Category
     */
    fun insertCategory(category: CategoryEntity) = dbHelper.insertCategory(category)
    fun updateCategory(category: CategoryEntity) = dbHelper.updateCategory(category)
    fun deleteCategory(category: CategoryEntity) = dbHelper.deleteCategory(category)
    fun getCategoryById(id: Int): CategoryEntity = dbHelper.getCategoryById(id)
    fun getCategories(): List<CategoryEntity> = dbHelper.getCategories()
    fun getAllCategories(): PagingSource<Int, CategoryEntity> = dbHelper.getAllCategories()
    suspend fun insertAllCategories(items:List<CategoryEntity>) = dbHelper.insertAllCategories(items)
    fun clearCategories() = dbHelper.clearCategories()
    fun getCategoriesCount(): Int = dbHelper.getCategoriesCount()

    /**
     * Jobs
     */
    fun getAllJobs(): PagingSource<Int, JobsEntity> = dbHelper.getAllJobs()
    suspend fun installJobData(data: MutableList<JobsEntity>) =
        dbHelper.insertAllJobs(data)
    fun clearJobs()= dbHelper.clearJobs()
    fun insertJob(job: JobsEntity)= dbHelper.insertJob(job)
    fun updateJob(job: JobsEntity)= dbHelper.updateJob(job)
    fun deleteJob(job: JobsEntity)= dbHelper.deleteJob(job)
    fun getJobById(id: Int): List<JobsEntity> = dbHelper.getJobById(id)
    fun getJobs(): List<JobsEntity> = dbHelper.getJobs()
    fun getJobsCount(): Int = dbHelper.getJobsCount()
    fun getJobsByCategoryId(id: String) : List<JobsEntity> = dbHelper.getJobByCategoryId(id)

    /**
     * Material
     */
    fun insertMaterial(material: MaterialEntity) = dbHelper.insertMaterial(material)
    fun updateMaterial(material: MaterialEntity) = dbHelper.updateMaterial(material)
    fun deleteMaterial(material: MaterialEntity)  = dbHelper.deleteMaterial(material)
    fun getMaterialyById(id: Int): List<MaterialEntity> = dbHelper.getMaterialyById(id)
    fun getMaterials(): List<MaterialEntity> = dbHelper.getMaterials()
    fun getAllMaterials(): PagingSource<Int, MaterialEntity> = dbHelper.getAllMaterials()
    suspend  fun insertAllMaterials(items:List<MaterialEntity>) = dbHelper.insertAllMaterials(items)
    fun clearMaterials() = dbHelper.clearMaterials()
    fun getMaterialsCount(): Int = dbHelper.getMaterialsCount()


    /**
     * Metrics
     */
    fun insertMetrics(metrics: MetricsEntity) = dbHelper.insertMetrics(metrics)
    fun updateMetrics(metrics: MetricsEntity) = dbHelper.updateMetrics(metrics)
    fun deleteMetrics(metrics: MetricsEntity) = dbHelper.deleteMetrics(metrics)
    fun getMetricsById(id: Int): List<MetricsEntity> = dbHelper.getMetricsById(id)
    fun getMetrics(): List<MetricsEntity> = dbHelper.getMetrics()
    fun getAllMetrics(): PagingSource<Int, MetricsEntity> = dbHelper.getAllMetrics()
    suspend  fun insertAllMetrics(items:List<MetricsEntity>) = dbHelper.insertAllMetrics(items)
    fun clearMetrics() = dbHelper.clearMetrics()
    fun getMetricsCount(): Int = dbHelper.getMetricsCount()
    /**
     * Users
     */

    fun insertUser(user: UserEntity) = dbHelper.insertUser(user)
    fun updateUser(user: UserEntity) = dbHelper.updateUser(user)
    fun deleteUser(user: UserEntity) = dbHelper.deleteUser(user)
    fun getUserById(id: Int): List<UserEntity> = dbHelper.getUserById(id)
    fun getUser(): List<UserEntity> = dbHelper.getUser()
    fun getUsersCount(): Int = dbHelper.geUsersCount()
}