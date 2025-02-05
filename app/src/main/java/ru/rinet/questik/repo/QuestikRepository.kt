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
    fun getMaterialByCategoryId(id: String) : List<MaterialEntity> = dbHelper.getMaterialByCategoryId(id)

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

    /**
     * @Chernovik
     */

    fun getChernovikCount(): Int = dbHelper.getChernovikCount()
    fun insertAllChernovik(items:List<ChernovikEntity>) = dbHelper.insertAllChernovik(items)
    fun clearChernovik()= dbHelper.clearChernovik()
    fun insertChernovik(chernovik: ChernovikEntity)= dbHelper.insertChernovik(chernovik)
    fun updateChernovik(chernovik: ChernovikEntity)= dbHelper.updateChernovik(chernovik)
    fun deleteChernovik(chernovik: ChernovikEntity)= dbHelper.deleteChernovik(chernovik)
    fun getChernovikById(id: Int): ChernovikEntity = dbHelper.getChernovikById(id)
    fun getChernovik(): List<ChernovikEntity> = dbHelper.getChernovik()
    fun getChernovikByCategoryId(id: String): List<ChernovikEntity> = dbHelper.getChernovikByCategoryId(id)
    fun searchChernovikItem(search: String?, cat: String): List<ChernovikEntity> = dbHelper.searchChernovikItem(search, cat)
    fun filteredChernovikList(
        search: String?,
        check: Boolean, cat: String
    ): List<ChernovikEntity>  = dbHelper.filteredChernovikList(search, check, cat)
    fun getChernovikByItogShow(check: Boolean, cat: String): List<ChernovikEntity>  = dbHelper.getChernovikByItogShow(check, cat)
    fun getChernovikByIdDiapazon(start: Int, end: Int): List<ChernovikEntity> = dbHelper.getChernovikByIdDiapazon(start, end)
    fun getChernovikCountByCategoryId(id: String): Int = dbHelper.getChernovikCountByCategoryId(id)
    fun getChernovikByCategoryAndIdDiapazon(categoryId:String, start: Int, end: Int): List<ChernovikEntity> = dbHelper.getChernovikByCategoryAndIdDiapazon(categoryId, start, end)
}