package ru.rinet.questik.repo.local.room

import androidx.paging.PagingSource
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import ru.rinet.questik.repo.local.room.entity.*

interface IDbHelper {
    /**
     * Category
     */
    fun insertCategory(category: CategoryEntity)
    fun updateCategory(category: CategoryEntity)
    fun deleteCategory(category: CategoryEntity)
    fun getCategoryById(id: Int): CategoryEntity
    fun getCategories(): List<CategoryEntity>
    fun getAllCategories(): PagingSource<Int, CategoryEntity>
    suspend fun insertAllCategories(items:List<CategoryEntity>)
    fun clearCategories()
    fun getCategoriesCount():Int

    /**
     * Jobs
     */
    fun getAllJobs(): PagingSource<Int, JobsEntity>
    suspend fun insertAllJobs(items:List<JobsEntity>)
    fun clearJobs()
    fun insertJob(job: JobsEntity)
    fun updateJob(job: JobsEntity)
    fun deleteJob(job: JobsEntity)
    fun getJobById(id: Int): List<JobsEntity>
    fun getJobs(): List<JobsEntity>
    fun getJobsCount():Int
    fun getJobByCategoryId(id: String): List<JobsEntity>

    /**
     * Material
     */
    fun insertMaterial(material: MaterialEntity)
    fun updateMaterial(material: MaterialEntity)
    fun deleteMaterial(material: MaterialEntity)
    fun getMaterialyById(id: Int): List<MaterialEntity>
    fun getMaterials(): List<MaterialEntity>
    fun getAllMaterials(): PagingSource<Int, MaterialEntity>
    suspend  fun insertAllMaterials(items:List<MaterialEntity>)
    fun clearMaterials()
    fun getMaterialsCount():Int
    fun getMaterialByCategoryId(id: String): List<MaterialEntity>


    /**
     * Metrics
     */
    fun insertMetrics(metrics: MetricsEntity)
    fun updateMetrics(metrics: MetricsEntity)
    fun deleteMetrics(metrics: MetricsEntity)
    fun getMetricsById(id: Int): List<MetricsEntity>
    fun getMetrics(): List<MetricsEntity>
    fun getAllMetrics(): PagingSource<Int, MetricsEntity>
    suspend  fun insertAllMetrics(items:List<MetricsEntity>)
    fun clearMetrics()
    fun getMetricsCount():Int
    /**
     * Users
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserEntity)
    fun updateUser(user: UserEntity)
    fun deleteUser(user: UserEntity)
    fun getUserById(id: Int): List<UserEntity>
    fun getUser(): List<UserEntity>
    fun geUsersCount():Int

    /**
     * @Chernovik
     */

    fun getChernovikCount(): Int
    fun insertAllChernovik(items:List<ChernovikEntity>)
    fun clearChernovik()
    fun insertChernovik(chernovik: ChernovikEntity)
    fun updateChernovik(chernovik: ChernovikEntity)
    fun deleteChernovik(chernovik: ChernovikEntity)
    fun getChernovikById(id: Int): List<ChernovikEntity>
    fun getChernovik(): List<ChernovikEntity>
    fun getChernovikByCategoryId(id: String): List<ChernovikEntity>
    fun searchChernovikItem(search: String?, cat: String): List<ChernovikEntity>
    fun filteredChernovikList(search: String?, check: Boolean, cat: String): List<ChernovikEntity>
    fun getChernovikByItogShow(check: Boolean, cat: String): List<ChernovikEntity>

}